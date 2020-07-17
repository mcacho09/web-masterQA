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
import com.retailsbs.logistikapp.logistic.dto.WayBillDTO;
import com.retailsbs.logistikapp.logistic.service.LogisticService;
import com.retailsbs.logistikapp.user.domain.UserAcegi;
import com.retailsbs.logistikapp.user.dto.AddNotificationDTO;
import com.retailsbs.logistikapp.user.notification.service.UserNotificationService;
import com.retailsbs.logistikapp.user.service.UserService;
/**
 * Class controller para duplicar Travel
 * @author Nataly
 * @since 31-08-2015
 */
public class TravelCtrl implements Controller {
	
	protected final Log logger = LogFactory.getLog(getClass());

	private LogisticService logisticService;
	private UserService userService;
	private UserNotificationService userNotificationService;
	private String view;
	private String TRAVEL_CREATED;
	private String TRAVEL_CANCEL;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public void setTRAVEL_CANCEL(String tRAVEL_CANCEL) {
		TRAVEL_CANCEL = tRAVEL_CANCEL;
	}
	public LogisticService getLogisticService() {
		return logisticService;
	}
	public void setLogisticService(LogisticService logisticService) {
		this.logisticService = logisticService;
	}
	public void setView(String view) {
		this.view = view;
	}
	public void setTRAVEL_CREATED(String tRAVEL_CREATED) {
		TRAVEL_CREATED = tRAVEL_CREATED;
	}
	public void setUserNotificationService(
			UserNotificationService userNotificationService) {
		this.userNotificationService = userNotificationService;
	}
	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		logger.debug("---- ONSUBMIT ----");
		// Se obtiene el contexto del usuario
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserAcegi useracegi = (UserAcegi) auth.getPrincipal();
		// se obtienen los ids
		String action = ServletRequestUtils.getRequiredStringParameter(arg0, "accion");
		String id_travels = ServletRequestUtils.getRequiredStringParameter(arg0, "idts");
		String []ids_travel = id_travels.split(",");
		
		if (action.equals("DUP")){
			for(String id:ids_travel){
				Long id_travel = Long.parseLong(id);
				Travel travel = logisticService.getTravelById(id_travel);
				Travel newTravel = new Travel();
				newTravel.setName(travel.getName());
				newTravel.setId_route(travel.getId_route());
				newTravel.setStatus(TRAVEL_CREATED);
				newTravel.setLog_created(new Date());
				newTravel.setLog_created_login(useracegi.getUserlogin());
				newTravel.setId_user(travel.getId_user());
				Long new_idTravel = logisticService.addTravel(newTravel);
				List<WayBillDTO> store_list = logisticService.getWayBillByIdTravel(id_travel);
				for(WayBillDTO wb : store_list){
					WayBill waybill = new WayBill();
					waybill.setId_store(wb.getId_store());
					waybill.setId_travel(new_idTravel);
					waybill.setOrderby(wb.getOrderby());
					waybill.setLog_created(new Date());
					waybill.setLog_created_login(useracegi.getUserlogin());
					logisticService.addWayBill(waybill);				
				}						
			}
		}	
		else if (action.equals("CAN")){
			for(String id:ids_travel){
				Long id_travel = Long.parseLong(id);
				Travel travel = new Travel();
				travel.setId_travel(id_travel);
				travel.setStatus(TRAVEL_CANCEL);
				travel.setLog_modified(new Date());
				travel.setLog_modified_login(useracegi.getUserlogin());
				logisticService.updTravelById(travel);			
				
				Travel temp = logisticService.getTravelById(id_travel);
				
				AddNotificationDTO dto = new AddNotificationDTO();
				dto.setCreated(new Date());
				dto.setIcon("fa fa-truck");
				dto.setId_supplier(userService.getAccessByIdUser(useracegi.getId_user()).get(0).getId_supplier());
				dto.setMessage("Viaje <label class='label label-danger'>"+temp.getName()+"</label> cancelado.");
				dto.setPriority("1");
				dto.setProfile(useracegi.getProfile());
				dto.setId_user(useracegi.getId_user());
				dto.setLink("alertlist.htm");
				
				Long id_not = userNotificationService.createNotification(dto, "003");
				List<Integer> users = new LinkedList<Integer>();
				users.add(temp.getId_user().intValue());
				userNotificationService.createNotificationWithIdNotification(id_not, users);
				logger.debug("id_not="+id_not);
			}
			
		}	
		else if (action.equals("DEL")){
			int row=0;
			String name_tra = "";
			for(int i=0; i<ids_travel.length; i++){
				//borrado de waybill con id_travel que se eliminara
				row = logisticService.delWayBillByIdTravel(Long.parseLong(ids_travel[i]));
				logger.debug(row + " waybill eliminados relacionados al id_travel "+Long.parseLong(ids_travel[i]));
				row = 0;
				// se obtienen los datos del travel
				Travel travel = logisticService.getTravelById(Long.parseLong(ids_travel[i]));
				logger.debug("travel name = "+travel.getName());
				name_tra = travel.getName();
				// borrado de travel
				row = logisticService.delTravelById(Long.parseLong(ids_travel[i]));
				logger.debug(row + " travel eliminado ok!");
				
				//Se manda notificacion de la eliminacion del travel
				AddNotificationDTO dto = new AddNotificationDTO();
				dto.setCreated(new Date());
				dto.setIcon("fa fa-truck");
				dto.setId_supplier(userService.getAccessByIdUser(useracegi.getId_user()).get(0).getId_supplier());
				dto.setMessage("Viaje <label class='label label-danger'>"+name_tra+"</label> eliminado.");
				dto.setPriority("1");
				dto.setProfile(useracegi.getProfile());
				dto.setId_user(useracegi.getId_user());
				dto.setLink("alertlist.htm");

				Long id_not = userNotificationService.createNotification(dto, "003");
				logger.debug("id_not="+id_not);
				
			} // for i<list_ids.size()
		} // else if (action.equals("DEL"))
		return new ModelAndView(view);
	}
}
