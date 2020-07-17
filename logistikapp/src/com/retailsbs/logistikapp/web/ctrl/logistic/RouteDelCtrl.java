package com.retailsbs.logistikapp.web.ctrl.logistic;
import java.util.ArrayList;
import java.util.Date;
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

import com.retailsbs.logistikapp.logistic.domain.Route;
import com.retailsbs.logistikapp.logistic.domain.Travel;
import com.retailsbs.logistikapp.logistic.service.LogisticService;
import com.retailsbs.logistikapp.user.domain.UserAcegi;
import com.retailsbs.logistikapp.user.dto.AddNotificationDTO;
import com.retailsbs.logistikapp.user.notification.service.UserNotificationService;
import com.retailsbs.logistikapp.user.service.UserService;
/**
 * Controlador para eliminar rutas
 * @author Juan Carlos Ramos
 * @since 09-10-2015 
 */
public class RouteDelCtrl implements Controller {
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	private LogisticService logisticService;
	private UserService userService;
	private UserNotificationService userNotificationService;
	private String view;

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
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		logger.debug("---- HANDLE/REQUEST ----");
		/*-------------------------------------------------------*/
		logger.debug("----- USER CONTEXT -----");
		// Se obtiene el contexto del usuario
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserAcegi useracegi = (UserAcegi) auth.getPrincipal();
		/*-------------------------------------------------------*/
		String name_route = "";
		int row = 0;
		String idsR = null;
		String[] idR = null;
		List<Long> ids_route = new ArrayList<Long>();
		if(arg0.getParameter("idsRoute")!=null && !arg0.getParameter("idsRoute").equals("")){
			idsR = ServletRequestUtils.getStringParameter(arg0, "idsRoute");
			idR = idsR.split(",");
			for(int i=0; i<idR.length;i++)
				ids_route.add(Long.parseLong(idR[i]));
		} // if(arg0.getParameter("idsRoute")!=null && !arg0.getParameter("idsRoute").equals(""))
		
		// procesa uno por uno los diferentes id_route que se quieren borrar
		for(int i=0; i<ids_route.size(); i++){
			List<Travel> list_travel = logisticService.getTravelByIdRoute(ids_route.get(i));
			logger.debug("list_travel="+list_travel);
			for(int j=0; j<list_travel.size(); j++){
				// borrado de waybill de viajes con rutas a borras
				row = logisticService.delWayBillByIdTravel(list_travel.get(j).getId_travel());
				logger.debug("waybill eliminados "+row);
				//Se elimina el travel relacionado con el id_route que se va a eliminar
				row = logisticService.delTravelById(list_travel.get(j).getId_travel());
				logger.debug("travel eliminado "+row);
			} // for j<list_travel.size()
			// se borra relacion route_store
			row = logisticService.delRouteStoreByIdRoute(ids_route.get(i));
			logger.debug("numero de route_store borrados "+row);
			// se obtiene datos del route que se eliminara
			Route route = logisticService.getRouteById(ids_route.get(i));
			name_route = route.getName();
			logger.debug("name_route = "+name_route);
			// Se borra la ruta
			row = logisticService.delRouteById(ids_route.get(i));
			//se manda notificacion que se borro la ruta
			AddNotificationDTO dto = new AddNotificationDTO();
			dto.setCreated(new Date());
			dto.setIcon("fa fa-road");
			dto.setId_supplier(userService.getAccessByIdUser(useracegi.getId_user()).get(0).getId_supplier());
			dto.setId_user(useracegi.getId_user());
			dto.setMessage("Ruta <label class='label label-danger'>"+name_route+"</label> eliminada.");
			dto.setPriority("1");
			dto.setProfile(useracegi.getProfile());
			dto.setLink("alertlist.htm");
			
			Long id_not = userNotificationService.createNotification(dto, "001");
			logger.debug("notification agregada ok con id "+id_not);
		} // for i<ids_route.size()

		return new ModelAndView(view);
	}


	
}
