package com.retailsbs.logistikapp.web.ctrl.config;

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

import com.retailsbs.logistikapp.manager.logistic.ManagerLogisticService;
import com.retailsbs.logistikapp.retail.domain.Retail;
import com.retailsbs.logistikapp.retail.service.RetailService;
import com.retailsbs.logistikapp.user.domain.UserAcegi;
import com.retailsbs.logistikapp.user.dto.AccessTinyDTO;
import com.retailsbs.logistikapp.user.dto.AddNotificationDTO;
import com.retailsbs.logistikapp.user.notification.service.UserNotificationService;
import com.retailsbs.logistikapp.user.service.UserService;

/**
 * Controlador para eliminar plazas
 * @author Axel Monroy
 * @since 29-02-2016
 */
public class RetailViewDelCtrl implements Controller {

	protected final Log logger = LogFactory.getLog(getClass());

	private String view;
	private UserService userService;
	private RetailService retailService;
	private ManagerLogisticService managerLogisticService;
	private UserNotificationService userNotificationService;

	public void setView(String view) {
		this.view = view;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public void setretailService(RetailService retailService) {
		this.retailService = retailService;
	}
	public void setmanagerLogisticService(ManagerLogisticService managerLogisticService){
		this.managerLogisticService = managerLogisticService;
	}
	public void setUserNotificationService(
			UserNotificationService userNotificationService) {
		this.userNotificationService = userNotificationService;
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
		Long id_supplier = ( accesssupplier.get(0) ).getId();
		logger.debug("idsupplier="+id_supplier);
		/*-------------------------------------------------------*/
		// obtienen los ids de las plazas a eliminar
		String idsParam = ServletRequestUtils.getStringParameter(arg0, "ids");
		logger.debug("ids="+idsParam);
		String[] idsArray = idsParam.split(",");
		
		// Inicializacion de variables
		Long idRetail = 0L;
		Retail retail = null;
		int rows = 0;
		String[] names = new String [idsArray.length];
		AddNotificationDTO dtn = null;
		Long idn = 0L;
		
		// Ciclo por cada plaza
		for ( int i = 0; i < idsArray.length; i++ ) {
			idRetail = Long.parseLong( idsArray[i] );
			retail = retailService.getRetailById(idRetail);
			//guarda el nombre del retail para la notificacion
			names[i] = retail.getName();
		}
		//Eliminar los retails y los stores
		rows = managerLogisticService.delRetailAndStores(idsArray);
		logger.debug("retails eliminados: "+rows);
		
		for(int i = 0; i < names.length; i++){
			/*-------------------------------------------------------*/
			// Se setea de dto para agregar un objeto de dominio Notification
			dtn = new AddNotificationDTO();
			dtn.setCreated(new Date());
			dtn.setIcon("fa fa-building");
			dtn.setId_user(useracegi.getId_user());
			dtn.setId_supplier(id_supplier);
			dtn.setMessage("Cliente <span class='label label-danger'>"+names[i]+"</span> eliminado");
			dtn.setPriority("1");
			dtn.setLink("alertlist.htm");
			// Se persiste la notificacion
			idn = userNotificationService.createNotification(dtn, "001");
			logger.debug("Notification, id="+idn);
			/*-------------------------------------------------------*/
			
		}
		return new ModelAndView(view);
	}

}