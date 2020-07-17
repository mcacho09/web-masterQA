package com.retailsbs.logistikapp.web.ctrl.communication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.retailsbs.logistikapp.user.service.UserService;

/**
 * Class controller para procesar inboxlist
 * @author juan carlos
 * @since 04-02-2015
 */
public class InboxDelCtrl implements Controller {

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
		/*-------------------------------------------------------*/
		
		//se borra mensajes
		
		//se borra relacion de usuarios
		String ids0="";
		String [] ids;
		ids0=arg0.getParameter("idsBorrar");
		if (arg0.getParameter("idsBorrar")!=null && !arg0.getParameter("idsBorrar").equals("")){
			ids0=ServletRequestUtils.getRequiredStringParameter(arg0, "idsBorrar");
			ids = ids0.split(",");
			for (int i = 0; i < ids.length; i++){
				//se borra mensajes
				Long id_user_message = Long.parseLong(ids[i]);
				int rows = userService.deleteMessageByIdUserMessage(id_user_message);
				logger.debug("message, id_user_message=" + id_user_message + " eliminado " + rows + " ...ok!");				
				//se borra relacion de usuarios
				int row = userService.delUserMessageById(id_user_message);
				logger.info("user message, id_user_message ="+id_user_message+" eliminado "+row+" ok");				
			}
		}		
		
		return new ModelAndView(view);
	}

}
