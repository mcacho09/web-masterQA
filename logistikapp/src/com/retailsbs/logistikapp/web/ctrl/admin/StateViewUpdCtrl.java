package com.retailsbs.logistikapp.web.ctrl.admin;

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

import com.retailsbs.logistikapp.retail.domain.Country;
import com.retailsbs.logistikapp.retail.domain.State;
import com.retailsbs.logistikapp.retail.dto.UpdStateDTO;
import com.retailsbs.logistikapp.retail.exception.StateNotFoundException;
import com.retailsbs.logistikapp.retail.service.RetailService;
import com.retailsbs.logistikapp.user.domain.Access;
import com.retailsbs.logistikapp.user.domain.Quantities;
import com.retailsbs.logistikapp.user.domain.UserAcegi;
import com.retailsbs.logistikapp.user.dto.NotificationTodaySearchCriteria;
import com.retailsbs.logistikapp.user.service.UserService;

/**
 * Controlador de clase de la vista para modificar estados
 * @author JC
 * @since 22-12-2014
 * @modified 28-12-2014 - JORGE - estandarizaci�n estructura de controller
 */
public class StateViewUpdCtrl extends SimpleFormController {
	
	protected final Log logger = LogFactory.getLog(getClass());

	private RetailService retailService;
	private UserService userService;
	private String ACTIVE;
	private String NO_ACTIVE;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public void setRetailService(RetailService retailService) {
		this.retailService = retailService;
	}
	public void setACTIVE(String aCTIVE) {
		ACTIVE = aCTIVE;
	}
	public void setNO_ACTIVE(String nO_ACTIVE) {
		NO_ACTIVE = nO_ACTIVE;
	}

	public StateViewUpdCtrl() {
		setSessionForm(true);
		setBindOnNewForm(true);
	}
	
	protected Map<String, Object> referenceData(HttpServletRequest arg0, Object arg1, Errors arg2) throws Exception{
		logger.debug("---- REFERENCEDATA ----");
		/*-------------------------------------------------------*/
		logger.debug("----- USER CONTEXT -----");
		// Se obtiene el contexto del usuario
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserAcegi useracegi = (UserAcegi) auth.getPrincipal();
		logger.debug("----- USER ACCESS -----");
		// Se obtienen los datos de acceso para el usuario
		List<Access> accesslist = userService.getAccessByIdUser(useracegi.getId_user());
		// Se obtiene el primer acceso por defecto
		Access useraccess = accesslist.get(0);
		/*-------------------------------------------------------*/
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
		//Se obtiene la lista de paises
		List<Country> list_country = retailService.getAllCountry();
		/*-------------------------------------------------------*/
		// Se obtiene la accion
		String accion = ServletRequestUtils.getStringParameter(arg0, "accion", null);
		/*-------------------------------------------------------*/
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("useracegi", useracegi);
		model.put("nttdto", nttdto);
		model.put("useraccess", useraccess);
		model.put("accion", accion);
		model.put("ACTIVE", ACTIVE);
		
		model.put("list_country", list_country);
		return model;
	}

	protected Object formBackingObject(HttpServletRequest request) throws ServletException, StateNotFoundException{
		logger.debug("---- FORMBACKINGOBJECT ----");
		/*-------------------------------------------------------*/
		// Se obtiene objeto de dominio del estado
		Long id = ServletRequestUtils.getLongParameter(request, "id");
		State record = retailService.getStateById(id);
		/*-------------------------------------------------------*/
		// Se setea command con los datos del objeto de dominio
		UpdStateDTO command = new UpdStateDTO();
		command.setActive(record.getActive());
		command.setCode(record.getCode());
		command.setCreated(record.getCreated());
		command.setId_country(record.getId_country());
		command.setId_state(record.getId_state());
		command.setLogin(record.getLogin());
		command.setModified(record.getModified());
		command.setName(record.getName());
		command.setOrderby(record.getOrderby());
		command.setPrefix(record.getPrefix());
		return command;
	}

	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws ServletException, StateNotFoundException{
		logger.debug("---- ONSUBMIT ----");
		// Se controla el parametro de active
		String active = ServletRequestUtils.getStringParameter(request, "active", NO_ACTIVE);

		UpdStateDTO dto = (UpdStateDTO) command;
		dto.setActive(active);
		dto.setModified(new Date());

		// Se persiste el objeto
		int rows = retailService.updState(dto);
		logger.debug("Supplier id=" + dto.getId_state() + " actualizado " + rows + "...ok!");
		
		return new ModelAndView(getSuccessView());
	}

}
