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
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.retailsbs.logistikapp.user.domain.Access;
import com.retailsbs.logistikapp.user.domain.Attachment;
import com.retailsbs.logistikapp.user.domain.Quantities;
import com.retailsbs.logistikapp.user.domain.UserAcegi;
import com.retailsbs.logistikapp.user.domain.UserMessage;
import com.retailsbs.logistikapp.user.dto.MessageDTO;
import com.retailsbs.logistikapp.user.dto.NotificationTodaySearchCriteria;
import com.retailsbs.logistikapp.user.service.UserService;

/**
 * Controller para la vista general de mensajes entre usuarios
 * @author JORGE
 * @since 23-01-2015
 * @modified 16-02-2015 - JORGE - Integraci�n para contador y alertas en barra navegaci�n
 */
public class InboxMsgeViewCtrl implements Controller {

	protected final Log logger = LogFactory.getLog(getClass());

	private UserService userService;
	private String view;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public void setView(String view) {
		this.view = view;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		logger.debug("---- HANDLE REQUEST ----");
		/*-------------------------------------------------------*/
		logger.debug("----- USER CONTEXT -----");
		// Se obtiene el contexto del usuario
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserAcegi useracegi = (UserAcegi) auth.getPrincipal();
		/*-------------------------------------------------------*/
		// Se obtienen los datos de acceso para el usuario
		List<Access> accesslist = userService.getAccessByIdUser(useracegi.getId_user());
		// Se obtiene el primer acceso por defecto
		Access useraccess = accesslist.get(0);
		logger.debug("----- HEADER/NOTIFICATION -----");
        NotificationTodaySearchCriteria dtoqty = new NotificationTodaySearchCriteria();
        dtoqty.setProfile(useracegi.getProfile());
        dtoqty.setFecha(new Date());
        dtoqty.setId_supplier(useraccess.getId_supplier());
        dtoqty.setId_user(useracegi.getId_user());
        
        Quantities nttdto = userService.getQtyAlertAndMessagesByIdUser(dtoqty);
        //HeaderNotificationDTO nttdto = userService.getHeaderNotificationByIdUser(useracegi.getId_user());

		logger.debug("alert="+nttdto.getAlert_qty()+" | message="+nttdto.getMessage_qty()+" | todo="+nttdto.getTodo_qty());
		/*-------------------------------------------------------*/
		
		// Se obtiene el parametro de una conversacion en particular
		Long id_user_message = null;
		if ( arg0.getParameter("idr") != null && !arg0.getParameter("idr").equals("") )
			id_user_message = ServletRequestUtils.getLongParameter(arg0, "idr");
			logger.debug("id_user_message=" + id_user_message);

		// Si no se ha seleccionado una conversacion en particular
		// por defecto se muestran los mensaje de la primera conversacion
		
				
		UserMessage user_message = null;
		List<MessageDTO> list_message = null;
				
		// Se controla que haya una conversacion previa, ya sea seleccionada o por defecto
		String user_to_image = "";
		String user_to_name = "";
		Date last_date = new Date();
		if ( id_user_message != null ) {
			user_message = userService.getUserMessageById(id_user_message);
			logger.debug("user_message, id_user="+user_message.getId_user()+", id_user_to="+user_message.getId_user_to());					
			// Se obtiene todos los mensajes de la conversacion
			list_message = userService.getMessageByIdUserMessage(id_user_message);
			// se marca la conversacion como leida
			userService.updateReadByIdUserMessage(id_user_message);
			logger.debug("list_message="+list_message.size());
		}	
		// se obtienen los datos del usuario
		if (list_message.size()>0)
		{				
				user_to_name = list_message.get(0).getUser_to_name();
				user_to_image = list_message.get(0).getUser_to_image();			
				last_date = list_message.get(list_message.size()-1).getCreated();
				for (MessageDTO msg : list_message){
					Long id_message = msg.getId_message();
					List<Attachment> attachment_files = userService.getAllAttachmentByIdMessage(id_message);
					msg.setAttachment_files(attachment_files);					
					
				}
				
		}
		/*-------------------------------------------------------*/
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("useracegi", useracegi);
		model.put("useraccess",useraccess);
		model.put("nttdto", nttdto);
		
		model.put("user_message", user_message);
		model.put("list_message", list_message);
		
		model.put("user_to_name", user_to_name);
		model.put("user_to_image", user_to_image);
		
		model.put("last_date", last_date);
		return new ModelAndView(view, model);
	}

}
