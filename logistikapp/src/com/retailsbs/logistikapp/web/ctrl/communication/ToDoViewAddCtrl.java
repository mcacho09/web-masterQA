package com.retailsbs.logistikapp.web.ctrl.communication;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContextHolder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.retailsbs.logistikapp.user.domain.Access;
import com.retailsbs.logistikapp.user.domain.Quantities;
import com.retailsbs.logistikapp.user.domain.UserAcegi;
import com.retailsbs.logistikapp.user.dto.AddToDoDTO;
import com.retailsbs.logistikapp.user.dto.NotificationTodaySearchCriteria;
import com.retailsbs.logistikapp.user.service.UserService;

/**
 * Controller para la vista para agregar Tareas
 * @author Juan Carlos Ramos Campos
 * @since 08-12-2014
 * @modified 20-01-2015 - JORGE - Estandarizacion estructura controller
 * @modified 16-02-2015 - JORGE - Integraci�n para contador y alertas en barra navegaci�n
 */
public class ToDoViewAddCtrl extends SimpleFormController {

	protected final Log logger = LogFactory.getLog(getClass());

	private UserService userService;
	private String ACTIVE;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public void setACTIVE(String aCTIVE) {
		ACTIVE = aCTIVE;
	}

	public ToDoViewAddCtrl() {
		setSessionForm(true);
		setBindOnNewForm(true);
	}

	@Override
	protected Map<String, Object> referenceData(HttpServletRequest arg0, Object arg1, Errors arg2) throws Exception {
		logger.debug("---- REFERENCEDATA ----");
		// Se obtiene el contexto del usuario
		logger.debug("----- USER CONTEXT -----");
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
		// Se obtiene la accion
		String accion = ServletRequestUtils.getStringParameter(arg0, "accion", null);
		logger.info("accion="+accion);
		/*-------------------------------------------------------*/
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("useracegi", useracegi);
		model.put("useraccess",useraccess);
		model.put("nttdto", nttdto);
		
		model.put("accion", accion);
		return model;
	}

	@Override
	protected Object formBackingObject(HttpServletRequest request) throws ServletException {
		AddToDoDTO command = new AddToDoDTO();
		return command;
	}

	@Override
	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws ServletException {
		logger.debug("---- ONSUBMIT ----");
		/*-------------------------------------------------------*/
		// Se obtiene el contexto del usuario
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserAcegi ua = (UserAcegi) auth.getPrincipal();
		
		AddToDoDTO dto = (AddToDoDTO) command;
		dto.setActive(ACTIVE);
		dto.setCreated(new Date());
		dto.setId_user(ua.getId_user());

		Long id = userService.addToDo(dto);
		logger.debug("ToDo, id=" + id + " creado OK!");

		return new ModelAndView(getSuccessView());
	}

}
