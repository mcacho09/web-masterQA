package com.retailsbs.logistikapp.web.ctrl.user;

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

import com.retailsbs.logistikapp.user.domain.User;
import com.retailsbs.logistikapp.user.domain.UserAcegi;
import com.retailsbs.logistikapp.user.dto.NewMessageTotalDTO;
import com.retailsbs.logistikapp.user.dto.lastNewMessageDTO;
import com.retailsbs.logistikapp.user.dto.lastNewMessageSearchCriteria;
import com.retailsbs.logistikapp.user.service.UserService;

/**
 * Controller para la vista message
 * @author Juan Carlos Ramos Campos
 * @since 07-01-2015
 */
public class MessageViewListCtrl implements Controller {

	protected final Log logger = LogFactory.getLog(getClass());

	private String view;
	private UserService userService;
	private int TIME_RELOAD;
	private String READ;
	private String NO_READ;
	private String SEND;
	private String NO_SEND;
	private Integer LIMIT_MSG;

	public void setLIMIT_MSG(Integer lIMIT_MSG) {
		LIMIT_MSG = lIMIT_MSG;
	}

	public void setSEND(String sEND) {
		SEND = sEND;
	}

	public void setNO_SEND(String nO_SEND) {
		NO_SEND = nO_SEND;
	}

	public void setREAD(String rEAD) {
		READ = rEAD;
	}

	public void setNO_READ(String nO_READ) {
		NO_READ = nO_READ;
	}

	public void setTIME_RELOAD(int tIME_RELOAD) {
		TIME_RELOAD = tIME_RELOAD;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setView(String view) {
		this.view = view;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		// Se obtiene el contexto del usuario
		Authentication auth = SecurityContextHolder.getContext() .getAuthentication();
		UserAcegi ua = (UserAcegi) auth.getPrincipal();
		String login = ua.getUserlogin();
		String name = ua.getFullname();
		String profile = ua.getProfile();
		Long id_user_login = ua.getId_user();
		logger.debug("login=" + login);
		logger.debug("name=" + name);
		logger.debug("profile=" + profile);
		logger.debug("id_user_login=" + id_user_login);
		/*-------------------------------------------------------*/
		//obtiene el numero de mensajes sin leer
		NewMessageTotalDTO dto_mes = new NewMessageTotalDTO();
		dto_mes.setId_user(id_user_login);
		dto_mes.setRead(NO_READ);
		int tot_message = userService.newMessageTotal(dto_mes);
		logger.info("total mensajes nuevos = "+tot_message);
		//obtiene los ultimos tres mensajes nuevos recibidos
		lastNewMessageSearchCriteria dto_last = new lastNewMessageSearchCriteria();
		dto_last.setId_user(id_user_login);
		dto_last.setLimit(LIMIT_MSG);
		dto_last.setRead(NO_READ);
		
		List<lastNewMessageDTO> list_last_msg = userService.lastNewMessage(dto_last);
		logger.info("list_last_msg ="+list_last_msg.size());
		
		int tot_newmsg = list_last_msg.size();


		/* ============ obtiene lista de usuarios ===============*/
		List<User> list_user = userService.getAllUser();
		logger.info("list_user = "+list_user.size());
		
		Long id_user_to = ServletRequestUtils.getLongParameter(arg0, "id_user_to", 0 );
		if(id_user_to == 0)
			id_user_to = list_user.get(0).getId_user();
		if(id_user_to.equals(id_user_login))
			id_user_to = list_user.get(1).getId_user();
		logger.info("id_user_to = "+id_user_to);
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("login", login);
		model.put("name", name);
		model.put("profile", profile);
		model.put("tot_message", tot_message);
		model.put("list_last_msg", list_last_msg);
		model.put("tot_newmsg", tot_newmsg);
		
		model.put("list_user", list_user);
		model.put("id_user_login", id_user_login);
		model.put("id_user_to", id_user_to);
		model.put("TIME_RELOAD", TIME_RELOAD);
		model.put("READ", READ);
		model.put("NO_READ", NO_READ);
		model.put("SEND", SEND);
		model.put("NO_SEND", NO_SEND);
		

		return new ModelAndView(view, model);
	}

}
