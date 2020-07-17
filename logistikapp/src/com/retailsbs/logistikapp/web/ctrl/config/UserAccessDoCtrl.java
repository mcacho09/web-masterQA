package com.retailsbs.logistikapp.web.ctrl.config;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContextHolder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.retailsbs.logistikapp.user.domain.User;
import com.retailsbs.logistikapp.user.domain.UserAcegi;
import com.retailsbs.logistikapp.user.dto.AddNotificationDTO;
import com.retailsbs.logistikapp.user.dto.DoProcessAccessDTO;
import com.retailsbs.logistikapp.user.notification.service.UserNotificationService;
import com.retailsbs.logistikapp.user.service.UserService;

/**
 * Class controller para procesar accesos del usuario
 * @author JORGE
 * @since 24-03-2015
 */
public class UserAccessDoCtrl implements Controller {

	protected final Log logger = LogFactory.getLog(getClass());
	
	public UserService userService;
	private UserNotificationService userNotificationService;
	private String view;
	private Integer EMPRESA;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public void setView(String view) {
		this.view = view;
	}
	public void setEMPRESA(Integer eMPRESA) {
		EMPRESA = eMPRESA;
	}
	
	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		logger.debug("---- HANDLE REQUEST ----");
		/*-------------------------------------------------------*/
		logger.debug("----- USER ACCESS -----");
		// Se obtiene el contexto del usuario
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserAcegi useracegi = (UserAcegi) auth.getPrincipal();
		/*-------------------------------------------------------*/
		
		// Origen --> supplier, retail, store
		String orig = null;
		if ( arg0.getParameter("orig") != null && !arg0.getParameter("orig").equals("") )
			orig= String.valueOf( arg0.getParameter("orig") );
		logger.debug("orig=" + orig);
		
		// Id entity -->  id_supplier, id_retail, id_store
		Long ide= null;
		if ( arg0.getParameter("ide") != null && !arg0.getParameter("ide").equals("") )
			ide= Long.parseLong(arg0.getParameter("ide"));
		logger.debug("ide=" + ide);
		
		// Id acceso
		Long ida = null;
		if ( arg0.getParameter("ida") != null && !arg0.getParameter("ida").equals("") )
			ida = Long.parseLong(arg0.getParameter("ida"));
		logger.debug("ida=" + ida);

		// Id usuario
		Long idu = null;
		if ( arg0.getParameter("idu") != null && !arg0.getParameter("idu").equals("") )
			idu = Long.parseLong(arg0.getParameter("idu"));
		logger.debug("idu=" + idu);
		
		User user = userService.getUserById(idu);
		logger.debug("user=" + user.getUsername());

		// Parametro active
		String active = null;
		if ( arg0.getParameter("active") != null && !arg0.getParameter("active").equals("") )
			active = String.valueOf( arg0.getParameter("active") );
		logger.debug("active="+active);
		
		// Se crea el objeto para procesar el acceso del usuario
		DoProcessAccessDTO dto = new DoProcessAccessDTO();
		dto.setFecha( new Date() );
		dto.setLogin( useracegi.getUserlogin() );
		dto.setActive(active);
		dto.setId_access(ida);
		dto.setId_user(idu);
		dto.setId_empresa((long)EMPRESA);
		if ( orig.equals("sup") ) dto.setId_supplier(ide);
		else if ( orig.equals("ret") ) dto.setId_retail(ide);
		else if ( orig.equals("sto") ) dto.setId_store(ide);
		// Se procesa el acceso del usuario
		userService.doProcessAccess(dto);
		
		/*-------------------------------------------------------*/
		// Se genera una notificaci—n
		AddNotificationDTO dtn = new AddNotificationDTO();
		dtn.setCreated(new Date());
		dtn.setIcon("fa fa-users");
		dtn.setId_user(useracegi.getId_user());
		dtn.setMessage("Accesos de <span class='label label-success'>" + user.getUsername() + "</span> actualizado");
		dtn.setPriority("1");
		dtn.setLink("alertlist.htm");
		// Se persiste la notificacion
		Long idn = userNotificationService.createNotification(dtn, "");
		logger.debug("Notification, id="+idn);
		/*-------------------------------------------------------*/
		
		return new ModelAndView(view + "?id="+idu);
		
	}

}
