package com.retailsbs.logistikapp.web.ctrl.communication;

import java.text.Format;
import java.text.SimpleDateFormat;
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

import com.retailsbs.logistikapp.supplier.exception.SupplierNotFoundException;
import com.retailsbs.logistikapp.user.domain.UserAcegi;
import com.retailsbs.logistikapp.user.dto.AddGroupDTO;
import com.retailsbs.logistikapp.user.dto.AddMessageDTO;
import com.retailsbs.logistikapp.user.dto.AddMessageGroupDTO;
import com.retailsbs.logistikapp.user.dto.AddNotificationDTO;
import com.retailsbs.logistikapp.user.dto.AddUserGroupDTO;
import com.retailsbs.logistikapp.user.dto.UserGroupDTO;
import com.retailsbs.logistikapp.user.notification.service.UserNotificationService;
import com.retailsbs.logistikapp.user.service.UserService;

/**
 * Controller para enviar un mensaje grupal
 * @author SERGIO
 * @since 05-08-2015
 */
public class InboxMsgGroupDoSendCtrl implements Controller {

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
	
	public void setUserNotificationService(
			UserNotificationService userNotificationService) {
		this.userNotificationService = userNotificationService;
	}
	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception, SupplierNotFoundException {
		// Se obtiene el contexto del usuario
				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				UserAcegi useracegi = (UserAcegi) auth.getPrincipal();
				
		
		logger.debug("---- REFERENCEDATA ---- ");
		/*-------------------------------------------------------*/
		// Se obtiene los datos por parametro
		Long id_user=useracegi.getId_user();
		logger.info("id_user=" + id_user);
		

		String message = ServletRequestUtils.getStringParameter(arg0, "message");
		logger.info("message=" + message);
		
		//Se obtiene el id_supplier para las notificaciones
		Long id_sup = userService.getAccessByIdUser(useracegi.getId_user()).get(0).getId_supplier();
		if(arg0.getParameterValues("id_user_to") == null)
		{

			
			// Se setea el dto del mensaje
			AddMessageDTO dto_msg = new AddMessageDTO();
			dto_msg.setId_user(id_user);
			dto_msg.setCreated(new Date());
			dto_msg.setMessage(message);
			dto_msg.setRead("N");
			dto_msg.setSend("S");
			// Se persiste el objeto
			Long id_msg = userService.addMessage(dto_msg);
			logger.debug("Mensaje, id="+id_msg+" creado.. ok!");
				

			Long id_group = ServletRequestUtils.getLongParameter(arg0, "id_group");
			if (message!=null&&!message.equals(""))
			{
				// Se setea el dto de la relacion messageGroup
				AddMessageGroupDTO dto_msg_gp = new AddMessageGroupDTO();
				dto_msg_gp.setId_group(id_group);
				dto_msg_gp.setId_message(id_msg);
				dto_msg_gp.setCreated(new Date());

				Long id_msg_gp = userService.addMessageGroup(dto_msg_gp);
				logger.debug("Relacion Message-Group, id="+id_msg_gp+" creada.. ok!");
				
				List<UserGroupDTO> userList = userService.getUserByIdGroup(id_group);
				List<Integer> list = new LinkedList<Integer>();
				
				for(UserGroupDTO i : userList){
					if (useracegi.getId_user().compareTo(i.getId_user()) != 0){
						list.add(i.getId_user().intValue());
					}
				}
				
				String nameGroup = userService.getGroupById(id_group).getName();
				
				AddNotificationDTO dtn = new AddNotificationDTO();
				dtn.setCreated(new Date());
				dtn.setIcon("fa fa-building");
				dtn.setId_user(useracegi.getId_user()); 
				dtn.setMessage("Nuevo mensaje grupal de <span class='label label-info'>"+nameGroup+"</span>");
				dtn.setPriority("1");
				dtn.setId_supplier(id_sup);
				dtn.setLink("inboxmsggroup.htm?id_gp=" + id_group);
				Long id_not = userNotificationService.createNotificationWithList(dtn, list);
				logger.info("Se cre� la notificaci�n con el id === " + id_not);
				return new ModelAndView(view+"?id_gp="+id_group);
			}
			else
			{
				return new ModelAndView("inboxmsggroup.htm?id_gp="+id_group);
			}
		}
		else
		{

			String[] id_users_to = arg0.getParameterValues("id_user_to");
			logger.info("numero de usuarios destino =" + id_users_to.length);
			
			String nameGroup = ServletRequestUtils.getStringParameter(arg0, "nameGroup");
			if(nameGroup==null || nameGroup.equals(""))
			{
				Format formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
				String s = formatter.format(new Date());
				nameGroup="Nuevo Grupo "+ s;
			}
			logger.info("Nombre del grupo=" + nameGroup);
			

			
			if (message!=null&&!message.equals("")){
				// Se setea el dto de Group
				AddGroupDTO dto_gp = new AddGroupDTO();
				dto_gp.setName(nameGroup);
				dto_gp.setId_user(id_user);
				dto_gp.setActive("S");
				dto_gp.setCreated(new Date());
				dto_gp.setLogin(useracegi.getLogin());
				// Se persiste el objeto
				Long id_group =  userService.addGroup(dto_gp);
				logger.debug("Grupo, id="+id_group+" creado.. ok!");
	
				// Se setea el dto de la relacion userGroup
				AddUserGroupDTO dto_usr_gp = new AddUserGroupDTO();
				dto_usr_gp.setId_group(id_group);
				dto_usr_gp.setId_user(id_user);
				
				Long id_user_group =  userService.addUserGroup(dto_usr_gp);
				logger.debug("Relacion User-Group, id="+id_user_group+" creada.. ok!");
				for(int i=0; i<id_users_to.length; i++)
				{
					dto_usr_gp.setId_group(id_group);
					dto_usr_gp.setId_user(Long.parseLong(id_users_to[i]));
	
					id_user_group =  userService.addUserGroup(dto_usr_gp);
					logger.debug("Relacion User-Group, id="+id_user_group+" creada.. ok!");
				}
					
				// Se setea el dto del mensaje
				AddMessageDTO dto_msg = new AddMessageDTO();
				dto_msg.setId_user(id_user);
				dto_msg.setCreated(new Date());
				dto_msg.setMessage(message);
				dto_msg.setRead("N");
				dto_msg.setSend("S");
				// Se persiste el objeto
				Long id_msg = userService.addMessage(dto_msg);
				logger.debug("Mensaje, id="+id_msg+" creado.. ok!");
					
					
				
				// Se setea el dto de la relacion messageGroup
				AddMessageGroupDTO dto_msg_gp = new AddMessageGroupDTO();
				dto_msg_gp.setId_group(id_group);
				dto_msg_gp.setId_message(id_msg);
				dto_msg_gp.setCreated(new Date());
	
				Long id_msg_gp = userService.addMessageGroup(dto_msg_gp);
				logger.debug("Relacion Message-Group, id="+id_msg_gp+" creada.. ok!");
				
				AddNotificationDTO dtn = new AddNotificationDTO();
				dtn.setCreated(new Date());
				dtn.setIcon("fa fa-building");
				dtn.setId_user(useracegi.getId_user()); 
				dtn.setMessage("Nuevo mensaje grupal de <span class='label label-info'>"+nameGroup+"</span> creado");
				dtn.setPriority("1");
				dtn.setId_supplier(id_sup);
				dtn.setLink("inboxmsggroup.htm?id_gp=" + id_group);
				
				List<Integer> list = new LinkedList<Integer>();
				for (String i : id_users_to){
					list.add(Integer.parseInt(i));
				}
				
				Long id_not = userNotificationService.createNotificationWithList(dtn, list);
				logger.info("Se cre� la notificaci�n con el id = " + id_not);
				return new ModelAndView(view+"?id_gp="+id_group);
			}
			else
				return new ModelAndView("redirect:inboxnew.htm");
		
		}
		
	}

}