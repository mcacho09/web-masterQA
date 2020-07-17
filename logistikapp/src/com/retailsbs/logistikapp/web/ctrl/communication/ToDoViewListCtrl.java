package com.retailsbs.logistikapp.web.ctrl.communication;

import java.util.ArrayList;
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
import com.retailsbs.logistikapp.user.domain.ToDo;
import com.retailsbs.logistikapp.user.domain.UserAcegi;
import com.retailsbs.logistikapp.user.dto.NotificationTodaySearchCriteria;
import com.retailsbs.logistikapp.user.service.UserService;

/**
 * Controller para la vista general de ToDo
 * @author Juan Carlos Ramos Campos
 * @since 05-12-2014
 * @modified 20-01-2015 - JORGE - Estandarizacion estructura controller
 * @modified 16-02-2015 - JORGE - Integraci?n para contador y alertas en barra navegaci?n
 */
public class ToDoViewListCtrl implements Controller {

	protected final Log logger = LogFactory.getLog(getClass());

	private String priority;
	private UserService userService;
	private String view;
	private String ACTIVE;
	private String NO_ACTIVE;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public void setView(String view) {
		this.view = view;
	}
	public void setACTIVE(String aCTIVE) {
		ACTIVE = aCTIVE;
	}
	public void setNO_ACTIVE(String nO_ACTIVE) {
		NO_ACTIVE = nO_ACTIVE;
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
		logger.debug("----- LIST CONTEXT -----");
		if(arg0.getParameter("priority")!= null)
		{
			priority = ServletRequestUtils.getStringParameter(arg0, "priority");
		}
		else
		{
			priority = "0";
		}
		logger.debug("priority ="+ priority);
		/*-------------------------------------------------------*/
		// Se obtiene el listado de todas las tareas para el usuario segun prioridad
		List<ToDo> list = new ArrayList<ToDo>();
		if(priority.equals("0"))
		{
			list = userService.getToDoByIdUser(useracegi.getId_user());
		}
		else
		{
			list = userService.getToDoByIdUserAndPriority(useracegi.getId_user(), priority);
		}
		logger.debug("list="+list.size());
		/*-------------------------------------------------------*/

		Map<String,Object> model = new HashMap<String,Object>();
		model.put("useracegi", useracegi);
		model.put("useraccess", useraccess);
		model.put("nttdto", nttdto);
		
		model.put("list", list);
		
		model.put("ACTIVE", ACTIVE);
		model.put("NO_ACTIVE", NO_ACTIVE);
		
		model.put("priority", priority);

		return new ModelAndView(view, model);
	}

}
