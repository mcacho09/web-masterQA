package com.retailsbs.logistikapp.web.ctrl.config;

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

import com.retailsbs.logistikapp.user.domain.UserAcegi;
import com.retailsbs.logistikapp.user.dto.UpdUserDTO;
import com.retailsbs.logistikapp.user.service.UserService;

/**
 * Controller para cambiar el estado activo de user
 * @author Juan Carlos Ramos Campos
 * @since 12-12-2014
 */
public class UserDoActiveCtrl implements Controller {
	
	protected final Log logger = LogFactory.getLog(getClass());

	public UserService userService;
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
		// Se obtiene el contexto del usuario
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserAcegi ua = (UserAcegi) auth. getPrincipal();
		String login = ua.getUserlogin();
		logger.debug("login="+login);
		
		/*-------------------------------------------------------*/
		// Se obtiene los datos por parametro
		Long id_user = ServletRequestUtils.getLongParameter(arg0, "id");
		logger.info("id_user="+id_user);
		
		String active = ServletRequestUtils.getStringParameter(arg0, "active");
		logger.info("active="+active);

		/*-------------------------------------------------------*/
		// Se obtiene el objeto de dominio
		UpdUserDTO dto = new UpdUserDTO();
		dto.setActive(active);
		dto.setId_user(id_user);
		dto.setLogin(login);
		dto.setModified(new Date());
		int rows = userService.updUser(dto);
		logger.debug("User id=" + dto.getId_user() + " actualizado " + rows + "...ok!");

		return new ModelAndView(view);
	}

}
