package com.retailsbs.logistikapp.web.ctrl.logistic;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContextHolder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.retailsbs.logistikapp.logistic.domain.Travel;
import com.retailsbs.logistikapp.logistic.domain.WayBill;
import com.retailsbs.logistikapp.logistic.service.LogisticService;
import com.retailsbs.logistikapp.user.domain.User;
import com.retailsbs.logistikapp.user.domain.UserAcegi;
import com.retailsbs.logistikapp.user.dto.AccessTinyDTO;
import com.retailsbs.logistikapp.user.dto.AddNotificationDTO;
import com.retailsbs.logistikapp.user.notification.service.UserNotificationService;
import com.retailsbs.logistikapp.user.service.UserService;
/**
 * Controlador de vista waybill
 * @author Nataly *
 * @since 24-08-2015 
 */
public class WayBillCtrl implements Controller {
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	private LogisticService logisticService;
	private UserService userService;
	private UserNotificationService userNotificationService;
	private String view;
	private String TRAVEL_CANCEL;
	private String TRAVEL_FINISHED;

	public void setLogisticService(LogisticService logisticService) {
		this.logisticService = logisticService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public void setUserNotificationService(
			UserNotificationService userNotificationService) {
		this.userNotificationService = userNotificationService;
	}
	public void setView(String view) {
		this.view = view;
	}
	
	public void setTRAVEL_CANCEL(String tRAVEL_CANCEL) {
		TRAVEL_CANCEL = tRAVEL_CANCEL;
	}
	public void setTRAVEL_FINISHED(String tRAVEL_FINISHED) {
		TRAVEL_FINISHED = tRAVEL_FINISHED;
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		logger.debug("---- HANDLE/REQUEST ----");
		/*-------------------------------------------------------*/
		logger.debug("----- USER CONTEXT -----");
		// Se obtiene el contexto del usuario
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserAcegi useracegi = (UserAcegi) auth.getPrincipal();
		/*-------------------------------------------------------*/
		
		logger.debug("----- USER ACCESS -----");
		List<AccessTinyDTO> accesssupplier = userService.getAccessSupplierByIdUser(useracegi.getId_user());
		logger.debug("accesssupplier="+accesssupplier.size());
		/*-------------------------------------------------------*/
		
		String name_viaje = null;
		if(arg0.getParameter("name_v")!=null && !arg0.getParameter("name_v").equals(""))
			name_viaje = ServletRequestUtils.getStringParameter(arg0, "name_v");
		logger.debug("nombre_viaje = "+name_viaje);
		
		String accion =  ServletRequestUtils.getRequiredStringParameter(arg0, "accion");
		Long id_waybill =  ServletRequestUtils.getLongParameter(arg0, "idw");
		Long id_travel = ServletRequestUtils.getLongParameter(arg0, "idt");
		String comment = ServletRequestUtils.getStringParameter(arg0, "comment");
		String acc = "";
		acc = accion=="CAN"?"cancelo":"finaliza";
		// Cuando se cancela o se finaliza el viaje se genera una alerta
		if(accion.equals("CAN") || accion.equals("FIN")){
			AddNotificationDTO dto_not = new AddNotificationDTO();
			dto_not.setCreated(new Date());
			dto_not.setIcon("fa fa-building");
			dto_not.setId_supplier(userService.getAccessByIdUser(useracegi.getId_user()).get(0).getId_supplier());
			dto_not.setId_user(useracegi.getId_user());
			dto_not.setMessage("Se "+acc+" <span class='label label-primary'>"+name_viaje+"</span>");
			dto_not.setPriority("1");
			dto_not.setLink("travelreport.htm?idt=" + id_travel);
				
			Long idn = userNotificationService.createNotification(dto_not, "003");
			Travel tvl = logisticService.getTravelById(id_travel);
			if (useracegi.getId_user().compareTo(tvl.getId_user()) != 0){
				List<Integer> users = new LinkedList<Integer>();
				users.add(tvl.getId_user().intValue());
				userNotificationService.createNotificationWithIdNotification(idn, users);
			}
		} // if(accion.equals("CAN") || accion.equals("FIN"))
		
		if (accion.equals("checkin")){
			WayBill waybill = new WayBill();
			waybill.setId_waybill(id_waybill);
			waybill.setCheckin(new Date());
			waybill.setLog_modified(new Date());
			waybill.setLog_modified_login(useracegi.getUserlogin());
			logisticService.updWayBillById(waybill );
		}
		else if (accion.equals("CAN")){
			acc = "cancela";
			Travel travel = new Travel();
			travel.setId_travel(id_travel);
			travel.setStatus(TRAVEL_CANCEL);
			travel.setComment(comment);
			travel.setLog_modified(new Date());
			travel.setLog_modified_login(useracegi.getUserlogin());
			logisticService.updTravelById(travel );
			
		}
		else if (accion.equals("FIN")){
			acc = "finaliza";
			Travel travel = new Travel();
			travel.setId_travel(id_travel);
			travel.setStatus(TRAVEL_FINISHED);
			travel.setComment(comment);
			travel.setFinished(new Date());
			travel.setLog_modified(new Date());
			travel.setLog_modified_login(useracegi.getUserlogin());
			logisticService.updTravelById(travel );
			
			List<WayBill> waybills = logisticService.getAllWayBillByIdTravel(id_travel);
			
			boolean outrange = false;
			Travel temp = logisticService.getTravelById(id_travel);
			Long id_driver = temp.getId_user();
			User user = userService.getUserById(id_driver);
			for (WayBill i : waybills){
				if ((i.getStatus() != null && i.getStatus().equals("N")) || (i.getOutrange() != null && i.getOutrange().equals("S"))){
					outrange = true;
					break;
				}
			}
			
			if (!outrange){
				Long id_sup = userService.getAccessByIdUser(useracegi.getId_user()).get(0).getId_supplier();
				AddNotificationDTO dtn = new AddNotificationDTO();
				dtn.setCreated(new Date());
				dtn.setIcon("fa fa-cube");
				dtn.setId_user(useracegi.getId_user());
				dtn.setMessage("El vendedor <span class='label label-info'>"+user.getUsername()+"</span> termino al 100% el viaje " + "<span class='label label-primary'>"+name_viaje+"</span> sin salir del rango permitido");
				dtn.setPriority("1");
				dtn.setId_supplier(id_sup);
				dtn.setLink("alertlist.htm");
				// Se persiste la notificacion
				Long idn = userNotificationService.createNotification(dtn, "003");
				logger.debug("Notification termino exitoso, id="+idn);
				
				dtn = new AddNotificationDTO();
				dtn.setCreated(new Date());
				dtn.setIcon("fa fa-cube");
				dtn.setId_user(useracegi.getId_user());
				dtn.setMessage(user.getUsername()+"</span> has terminado  el viaje " + "<span class='label label-primary'>"+name_viaje+"</span> con un <b>100%</b> de efectividad.</span><br>Gracias por tu esfuerzo");
				dtn.setPriority("1");
				dtn.setId_supplier(id_sup);
				dtn.setLink("alertlist.htm");
				// Se persiste la notificacion
				List<Integer> users = new LinkedList<Integer>();
				users.add(user.getId_user().intValue());
				idn = userNotificationService.createNotificationWithList(dtn, users);
				logger.debug("Notification de felicitación, id="+idn);
				
			}
			
		}
		
		return new ModelAndView(view);
	}


	
}
