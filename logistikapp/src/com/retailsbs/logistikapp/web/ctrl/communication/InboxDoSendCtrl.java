package com.retailsbs.logistikapp.web.ctrl.communication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.retailsbs.logistikapp.supplier.exception.SupplierNotFoundException;
import com.retailsbs.logistikapp.user.dto.SendMessageDTO;
import com.retailsbs.logistikapp.user.service.UserService;

/**
 * Controller para enviar un mensajhe
 * @author JORGE
 * @since 27-01-2015
 */
public class InboxDoSendCtrl implements Controller {

	protected final Log logger = LogFactory.getLog(getClass());

	private UserService userService;
	private String view;
	private String viewmobile;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public void setView(String view) {
		this.view = view;
	}
	public void setViewmobile(String viewmobile) {
		this.viewmobile = viewmobile;
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception, SupplierNotFoundException {
		logger.debug("---- REFERENCEDATA ---- ");
		/*-------------------------------------------------------*/
		// Se obtiene los datos por parametro
		Long id_user = ServletRequestUtils.getLongParameter(arg0, "id_user");
		logger.info("id_user=" + id_user);
		
		Long id_user_to = ServletRequestUtils.getLongParameter(arg0, "id_user_to");
		logger.info("id_user_to=" + id_user_to);

		String message = ServletRequestUtils.getStringParameter(arg0, "message");
		logger.info("message=" + message);
		
		boolean ismobile = ServletRequestUtils.getBooleanParameter(arg0, "ismobile", false);
		logger.info("ismobile=" + ismobile);
		
		Long id_user_message = ServletRequestUtils.getLongParameter(arg0, "id_user_message");
		logger.info("id_user_message=" + id_user_message);

		// Se setea el dto del mensaje
		SendMessageDTO dto = new SendMessageDTO();
		dto.setId_user(id_user);
		dto.setId_user_to(id_user_to);
		dto.setMessage(message);
		
		// Se persiste el objeto
		userService.sendMesssage(dto);

		if( !ismobile ) {
			return new ModelAndView(view);
		} else {
			return new ModelAndView(viewmobile+"?idr="+id_user_message);
		}
		
	}

}
