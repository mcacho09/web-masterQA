package com.retailsbs.logistikapp.web.ctrl.communication;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContextHolder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.retailsbs.logistikapp.supplier.exception.SupplierNotFoundException;
import com.retailsbs.logistikapp.user.domain.UserAcegi;
import com.retailsbs.logistikapp.user.dto.AddNotificationDTO;
import com.retailsbs.logistikapp.user.notification.service.UserNotificationService;
import com.retailsbs.logistikapp.user.service.UserService;

/**
 * Controller para enviar un mensaje
 * @author juan carlos
 * @since 27-01-2015
 * @modified 06-02-2015 - JORGE - Se mejora la estructura del controlador
 */
public class AlertDoSendCtrl implements Controller {

	protected final Log logger = LogFactory.getLog(getClass());

	private UserService userService;
	private UserNotificationService userNotificationService;
	private String view;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public void setView(String view) {
		this.view = view;
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception, SupplierNotFoundException {
		logger.debug("---- REFERENCEDATA ---- ");
		// Se obtiene el contexto del usuario
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserAcegi ua = (UserAcegi) auth. getPrincipal();
		/*-------------------------------------------------------*/

		String message = ServletRequestUtils.getStringParameter(arg0, "message", null);
		logger.debug("message ="+message);
		
		// Se setea el dto
		AddNotificationDTO dto = new AddNotificationDTO();
		dto.setCreated(new Date());
		dto.setId_user( ua.getId_user() );
		dto.setMessage(message);
		dto.setIcon("fa fa-comment");
		dto.setPriority("H");
		dto.setLink("alertlist.htm");
		// Se persiste el objeto
		Long id = userNotificationService.createNotification(dto, "");
		logger.debug("Notificacion id_notification="+id+" creada ok!");
		
		return new ModelAndView(view);
	}

}
