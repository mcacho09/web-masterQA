package com.retailsbs.logistikapp.web.ctrl.communication;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContextHolder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.retailsbs.logistikapp.supplier.domain.Supplier;
import com.retailsbs.logistikapp.supplier.service.SupplierService;
import com.retailsbs.logistikapp.user.domain.Access;
import com.retailsbs.logistikapp.user.domain.Quantities;
import com.retailsbs.logistikapp.user.domain.User;
import com.retailsbs.logistikapp.user.domain.UserAcegi;
import com.retailsbs.logistikapp.user.dto.NotificationTodaySearchCriteria;
import com.retailsbs.logistikapp.user.dto.UserMessageDTO;
import com.retailsbs.logistikapp.user.dto.UserSearchCriteria;
import com.retailsbs.logistikapp.user.service.UserService;

/**
 * Controller para la vista para agregar mensajes
 * @author JORGE
 * @since 23-01-2015
 * @modified 16-02-2015 - JORGE - Integraci�n para contador y alertas en barra navegaci�n
 */
public class InboxViewAddCtrl implements Controller {

	protected final Log logger = LogFactory.getLog(getClass());

	private UserService userService;
	private SupplierService supplierService;
	private String view;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public void setSupplierService(SupplierService supplierService) {
		this.supplierService = supplierService;
	}
	public void setView(String view) {
		this.view = view;
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		logger.debug("---- HANDLE REQUEST ----");
		/*-------------------------------------------------------*/
		logger.debug("----- USER ACCESS -----");
		// Se obtiene el contexto del usuario
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserAcegi useracegi = (UserAcegi) auth.getPrincipal();
		List<Access> access = userService.getAccessByIdUser(useracegi.getId_user());
		logger.debug("access="+access.size());
		// Por defecto toma el primer elemento
		Supplier supplier = supplierService.getSupplierById( (access.get(0) ).getId_supplier() );
		logger.debug("supplier="+supplier.getName());
		logger.debug("----- USER ACCESS -----");
		// Se obtienen los datos de acceso para el usuario
		List<Access> accesslist = userService.getAccessByIdUser(useracegi.getId_user());
		// Se obtiene el primer acceso por defecto
		Access useraccess = accesslist.get(0);
		/*-------------------------------------------------------*/
		logger.debug("----- HEADER/NOTIFICATION -----");
        NotificationTodaySearchCriteria dtoqty = new NotificationTodaySearchCriteria();
        dtoqty.setProfile(useracegi.getProfile());
        dtoqty.setFecha(new Date());
        dtoqty.setId_supplier(useraccess.getId_supplier());
        dtoqty.setId_user(useracegi.getId_user());
        
        Quantities nttdto = userService.getQtyAlertAndMessagesByIdUser(dtoqty);
        //HeaderNotificationDTO nttdto = userService.getHeaderNotificationByIdUser(useracegi.getId_user());

		/*-------------------------------------------------------*/
		// Se obtiene el listado de todos los usuarios
		// con los cuales se tiene una conversacion previa
		List<UserMessageDTO> list_users = userService.getUserMessageByIdUser( useracegi.getId_user() );
		logger.debug("list_users="+list_users.size());
		/*-------------------------------------------------------*/
		// Se obtiene el listado de todos los usuarios
		// con los cuales se podr�a tener una conversacion
		//List<User> list_users_available = userService.getAllUserAvailableMessage(useracegi.getId_user() );
		
		UserSearchCriteria userSearchCriteria = new UserSearchCriteria ();
		userSearchCriteria.setId_user(useracegi.getId_user());
		userSearchCriteria.setId_supplier((userService.getAccessByIdUser(useracegi.getId_user()).get(0)).getId_supplier());
		
		List<User> list_users_available = userService.getAvailableUserByCriteria(userSearchCriteria);
		logger.debug("list_users_available="+list_users_available.size());
		/*-------------------------------------------------------*/
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("useracegi", useracegi);
		model.put("useraccess",useraccess);
		model.put("useraccess", supplier);
		model.put("nttdto", nttdto);
		
		model.put("list_users", list_users);
		model.put("list_users_available", list_users_available);
		
		return new ModelAndView(view, model);
	}

}
