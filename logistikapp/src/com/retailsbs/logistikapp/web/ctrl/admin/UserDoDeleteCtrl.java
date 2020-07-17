package com.retailsbs.logistikapp.web.ctrl.admin;

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

import com.retailsbs.logistikapp.logistic.domain.Travel;
import com.retailsbs.logistikapp.logistic.service.LogisticService;
import com.retailsbs.logistikapp.user.domain.Group;
import com.retailsbs.logistikapp.user.domain.Message;
import com.retailsbs.logistikapp.user.domain.User;
import com.retailsbs.logistikapp.user.domain.UserAcegi;
import com.retailsbs.logistikapp.user.domain.UserMessage;
import com.retailsbs.logistikapp.user.dto.AddNotificationDTO;
import com.retailsbs.logistikapp.user.notification.service.UserNotificationService;
import com.retailsbs.logistikapp.user.service.UserService;

/**
 * Controlador para eliminar usuarios
 * @author Juan Carlos Ramos
 * @since 24-11-2015
 * @modified 26-11-2015 - Jorge - Mejoras generales al controller
 */
public class UserDoDeleteCtrl implements Controller {

	protected final Log logger = LogFactory.getLog(getClass());

	private String view;
	private UserService userService;
	private UserNotificationService userNotificationService;
	private LogisticService logisticService;

	public void setView(String view) {
		this.view = view;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public void setLogisticService(LogisticService logisticService) {
		this.logisticService = logisticService;
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
		
		// obtienen los ids de los usuarios a eliminar
		String idsParam = ServletRequestUtils.getStringParameter(arg0, "ids");
		logger.debug("ids="+idsParam);
		String[] idsArray = idsParam.split(",");

		// Inicialización de variables
		Long id = 0L;
		int rows = 0;
		User user = null;
		Long id_supplier = null;
		List<Message> messages = null;
		List<UserMessage> usermessages = null;
		List<Group> groups = null;
		List<Travel> travels = null;
		AddNotificationDTO dtn = null;
		Long idn = null;
		
		// Ciclo por cada usuario
		for ( int i = 0; i < idsArray.length; i++ ) {
			id = Long.parseLong( idsArray[i] );
			logger.debug( "DELETE ==> id="+id );
			
			// Se obtiene el acceso del usuario para generar la notificacion al final
			// Se obtiene el proveedor asociado del usuario a eliminar
			id_supplier = userService.getAccessByIdUser( id ).get(0).getId_supplier();
			logger.debug("DELETE ==> id_supplier = " + id_supplier );
			
			// Se borran los datos de acceso asociado al usuario
			rows = userService.delAccessByIdUser( id );
			logger.debug("DELETE ==> eliminando " + rows + " accesos .. ok!" );
			
			// Se borran todas las notificaciones generadas por el usuario
			rows = userService.delNotificationByIdUser( id );
			logger.debug("DELETE ==> eliminando " + rows + " notificaciones .. ok!" );
			
			// Se borran todos los eventos del calendario creadas por el usuario
			rows = userService.delCalendarByIdUser( id );
			logger.debug("DELETE ==> eliminando " + rows + " eventos de calendario .. ok!" );
			
			// Se borran todas las tareas del usuario
			rows = userService.delTodoByIdUser( id );
			logger.debug("DELETE ==> eliminando " + rows + " tareas .. ok!" );
			
			// Se obtienen y se borran todos los archivos adjuntos y grupos asociados al usuario
			messages = userService.getMessageByIdUser( id );
			for ( Message message : messages ) {
				rows = userService.delAttachmentByIdMessage( message.getId_message() );
				logger.debug("DELETE ==> eliminando " + rows + " archivos adjuntos en mensajes .. ok!" );
				rows = userService.delMessageGroupByIdMessage( message.getId_message() );
				logger.debug("DELETE ==> eliminando " + rows + " grupos de mensajes .. ok!" );
			}
			
			// Se obtienen y se eliminan todos los mensajes del usuario
			usermessages = userService.getUserMessageByIdUserId_UserTo( id );
			for ( UserMessage usermessage : usermessages ) {
				rows = userService.deleteMessageByIdUserMessage( usermessage.getId_user_message() );
				logger.debug("DELETE ==> eliminando " + rows + " mensajes .. ok!" );
			}
			
			// Se eliminan todos los mensajes del usuario
			rows = userService.delMessageByIdUser( id );
			logger.debug("DELETE ==> eliminando " + rows + " mensajes .. ok!" );
			
			// Se eliminan todos los mensajes del usuario
			rows = userService.delUserMessageByIdUserIdUserTo( id );
			logger.debug("DELETE ==> eliminando " + rows + " mensajes .. ok!" );
			
			// Se eliminan todos los grupos asociados al usuario
			rows = userService.delUserGrpByIdUser( id );
			logger.debug("DELETE ==> eliminando " + rows + " grupos .. ok!" );
			
			// Se obtienen y se eliminan todos los grupos del usuario
			groups = userService.getGroupByIdUser( id );
			for ( Group group : groups ) {
				rows = userService.delMessageGrpByIdGrp( group.getId_group() );
				logger.debug("DELETE ==> eliminando " + rows + " message group .. ok!" );
				// se eliminan user_group por id_group
				rows = userService.delUserGrpByIdGrp( group.getId_group() );
				logger.debug("DELETE ==> eliminando " + rows + " user group .. ok!" );
			}
			
			// Se eliminan todos los grupos asociados al usuario
			rows = userService.delGroupByIdUser( id );
			logger.debug("DELETE ==> eliminando " + rows + " grupos .. ok!" );
			
			// Se obtienen y se eliminan todos los viajes del usuario
			travels = logisticService.getTravelByIdUser( id );
			for ( Travel travel : travels ) {
				rows = logisticService.delWayBillByIdTravel( travel.getId_travel() );
				logger.debug("DELETE ==> eliminando " + rows + " waybill .. ok!" );
			}
			
			// Se eliminan los viajes del usuario
			rows = logisticService.delTravelByIdUser( id );
			logger.debug("DELETE ==> eliminando " + rows + " viajes .. ok!" );

			// Se obtienen los datos del usuario para generar una notificacion
			user = userService.getUserById( id );
			logger.debug("DELETE ==> usuario = " + user.getUsername() );
			
			// Se elimina el usuario
			rows = userService.delUserById( id );
			logger.debug("DELETE ==> eliminando " + rows + " usuario .. ok!" );
			
			// Se genera una notificacion para la eliminación del usuario
			dtn = new AddNotificationDTO();
			dtn.setCreated( new Date() );
			dtn.setIcon( "fa fa-user" );
			dtn.setId_user( useracegi.getId_user() );
			dtn.setId_supplier( id_supplier );
			dtn.setMessage( "Usuario <span class='label label-danger'>"+user.getUsername()+"</span> eliminado");
			dtn.setPriority( "1" );
			dtn.setLink("alertlist.htm");
			// Se persiste la notificacion
			idn = userNotificationService.createNotification(dtn, "003");
			logger.debug("DELETE ==> Notification, id="+idn);
			
		}

		return new ModelAndView(view);
	}

}
