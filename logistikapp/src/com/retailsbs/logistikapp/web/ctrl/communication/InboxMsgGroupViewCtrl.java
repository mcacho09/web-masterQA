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
import com.retailsbs.logistikapp.user.domain.Quantities;
import com.retailsbs.logistikapp.user.domain.UserAcegi;
import com.retailsbs.logistikapp.user.dto.MessageGroupDTO;
import com.retailsbs.logistikapp.user.dto.NotificationTodaySearchCriteria;
import com.retailsbs.logistikapp.user.dto.UserGroupDTO;
import com.retailsbs.logistikapp.user.service.UserService;

/**
 * Controller para la vista general de mensajes entre usuarios
 * @author JORGE
 * @since 23-01-2015
 * @modified 16-02-2015 - JORGE - Integraci�n para contador y alertas en barra navegaci�n
 */
public class InboxMsgGroupViewCtrl implements Controller {

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
		Long id_group = null;
		if ( arg0.getParameter("id_gp") != null && !arg0.getParameter("id_gp").equals("") )
			id_group = ServletRequestUtils.getLongParameter(arg0, "id_gp");
		logger.debug("id_group=" + id_group);
		
		long id_user = useracegi.getId_user();
		logger.debug("id_user=" + id_user);
		
		List<MessageGroupDTO> list_message = null;
		list_message = userService.getMessageByIdGroup(id_group);
		
		/*-------------------------------------------------------*/
		//se obtiene el titulo de la conversacion
		String grpName=  list_message.get(0).getName();
		//Se obtienen los integrantes del grupo
		List<UserGroupDTO> integrantes = userService.getUserByIdGroup(id_group);
		String txtIntegrantes="";
		for (int i = 0; i < (integrantes.size()-1); i++) {
			txtIntegrantes+=(integrantes.get(i).getUsername()+", ");
		}
		txtIntegrantes+=(integrantes.get(integrantes.size()-1).getUsername());
		logger.debug("integrantes =" + txtIntegrantes);
		
		
		/*-------------------------------------------------------*/
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("useracegi", useracegi);
		model.put("useraccess",useraccess);
		model.put("nttdto", nttdto);
		model.put("list_message", list_message);
		model.put("grpName", grpName);
		model.put("id_user", id_user);
		model.put("id_group", id_group);
		model.put("txtIntegrantes", txtIntegrantes);
		
		return new ModelAndView(view, model);
	}

}
