package com.retailsbs.logistikapp.web.ctrl.customer;
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

import com.retailsbs.logistikapp.logistic.service.LogisticService;
import com.retailsbs.logistikapp.retail.domain.Store;
import com.retailsbs.logistikapp.retail.service.RetailService;
import com.retailsbs.logistikapp.user.domain.UserAcegi;
import com.retailsbs.logistikapp.user.dto.AddNotificationDTO;
import com.retailsbs.logistikapp.user.notification.service.UserNotificationService;
import com.retailsbs.logistikapp.user.service.UserService;
/**
 * Controlador para eliminar rutas
 * @author Juan Carlos Ramos
 * @since 09-10-2015 
 */
public class StoreDelCtrl implements Controller {
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	private RetailService retailService;
	private LogisticService logisticService;
	private UserService userService;
	private UserNotificationService userNotificationService;
	private String view;

	public void setLogisticService(LogisticService logisticService) {
		this.logisticService = logisticService;
	}
	public void setRetailService(RetailService retailService) {
		this.retailService = retailService;
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
		//obtencion de datos
		String ids = null;
		String[] ids_str = null;
		List<Long>ids_store = new ArrayList<Long>();
		if(arg0.getParameter("ids_store")!=null && !arg0.getParameter("ids_store").equals("")){
			ids = ServletRequestUtils.getStringParameter(arg0, "ids_store");
			ids_str = ids.split(",");
			for(int i=0; i<ids_str.length; i++)
				ids_store.add(Long.parseLong(ids_str[i]));
		} // if(arg0.getParameter("")!=null && !arg0.getParameter("").equals(""))
		
		Long id_retail = null;
		if(arg0.getParameter("id_retail")!=null && !arg0.getParameter("id_retail").equals(""))
			id_retail = ServletRequestUtils.getLongParameter(arg0, "id_retail");
		logger.debug("id_retail="+id_retail);

		String name_store = "";
		int row = 0;
		for(int i=0; i<ids_store.size(); i++){
			//borrar waybill(id_store);
			row = logisticService.delWaybillByIdStore(ids_store.get(i));
			logger.debug(row+" waybill borrados ok. ");
			// borrar routeStore
			row = logisticService.delRouteStoreByIdStore(ids_store.get(i));
			logger.debug(row+" routeStore borrados ok.");
			// se obtiene datos de tienda
			Store store = retailService.getStoreById(ids_store.get(i));
			name_store = store.getName();
			//mandar notificacion store borrada
			AddNotificationDTO dto = new AddNotificationDTO();
			dto.setCreated(new Date());
			dto.setIcon("fa fa-building");
			dto.setId_supplier(userService.getAccessByIdUser(useracegi.getId_user()).get(0).getId_supplier());
			dto.setId_user(useracegi.getId_user());
			dto.setMessage("Cliente <label class='label label-danger'>"+name_store+"</label> eliminado");
			dto.setPriority("1");
			dto.setLink("alertlist.htm");
			
			Long id_notification = userNotificationService.createNotification(dto, "001");
			logger.debug("notificacion ok con id "+id_notification);
			//eliminar store
			row = retailService.delStoreById(ids_store.get(i));
			logger.debug("tiendas borradas "+row);
			
		} // for i<ids_store.size()

		return new ModelAndView(view+"?id="+id_retail);
	}
	
}
