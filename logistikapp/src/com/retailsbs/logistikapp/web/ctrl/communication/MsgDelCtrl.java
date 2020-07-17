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
public class MsgDelCtrl implements Controller {

	protected final Log logger = LogFactory.getLog(getClass());
	
	private UserService userService;
	private String view;
	private String PATH_ROOT;
	public void setPATH_ROOT(String pATH_ROOT) {
		PATH_ROOT = pATH_ROOT;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public void setView(String view) {
		this.view = view;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		/*-------------------------------------------------------*/
		// Se obtiene objeto de dominio del todo
		Long id_user_message = ServletRequestUtils.getLongParameter(arg0, "id_user_message_del");
		Long id_message = ServletRequestUtils.getLongParameter(arg0, "id_msg_del");
		logger.info("id_user_message="+id_user_message);
		/*-------------------------------------------------------*/
		
		//se borra el mensaje
		userService.setPATH_ROOT(PATH_ROOT);
		
		int row = userService.delMessageById(id_message);
		logger.debug("message, id_message=" + id_message + " eliminado " + row + " ...ok!");
				
		return new ModelAndView(view+"?idr="+id_user_message);
		
	}

}
