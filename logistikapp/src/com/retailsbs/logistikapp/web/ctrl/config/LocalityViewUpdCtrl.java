package com.retailsbs.logistikapp.web.ctrl.config;

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

import com.retailsbs.logistikapp.retail.domain.City;
import com.retailsbs.logistikapp.retail.domain.Locality;
import com.retailsbs.logistikapp.retail.dto.UpdLocalityDTO;
import com.retailsbs.logistikapp.retail.exception.LocalityNotFoundException;
import com.retailsbs.logistikapp.retail.service.RetailService;
import com.retailsbs.logistikapp.user.domain.Access;
import com.retailsbs.logistikapp.user.domain.Quantities;
import com.retailsbs.logistikapp.user.domain.UserAcegi;
import com.retailsbs.logistikapp.user.dto.NotificationTodaySearchCriteria;
import com.retailsbs.logistikapp.user.service.UserService;

public class LocalityViewUpdCtrl extends SimpleFormController {
	
	protected final Log logger = LogFactory.getLog(getClass());

	private UserService userService;
	private RetailService retailService;
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

	public LocalityViewUpdCtrl() {
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
		/*-------------------------------------------------------*/
		
		Long city_id = null;
		if(arg0.getParameter("city_id")!=null && !arg0.getParameter("city_id").equals(""))
			city_id = ServletRequestUtils.getLongParameter(arg0, "city_id");
		logger.info("city_id="+city_id);
		
		Long state_id = null;
		if(arg0.getParameter("state_id")!=null && !arg0.getParameter("state_id").equals(""))
			state_id = ServletRequestUtils.getLongParameter(arg0, "state_id");
		logger.info("state_id="+state_id);
		
		List<City> list_city = retailService.getAllCity();
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("useracegi", useracegi);
		model.put("useraccess", useraccess);
		model.put("nttdto", nttdto);
		model.put("accion", accion);
		
		model.put("list_city", list_city);
		model.put("city_id", city_id);
		model.put("state_id", state_id);

		model.put("ACTIVE", ACTIVE);
		return model;
	}

	protected Object formBackingObject(HttpServletRequest request) throws ServletException, LocalityNotFoundException{
		logger.debug("---- FORMBACKINGOBJECT ----");

		// Se obtiene id
		Long id = ServletRequestUtils.getLongParameter(request, "id");
		logger.debug("id=" + id);

		Locality loc = retailService.getLocalityById(id);
		
		UpdLocalityDTO command = new UpdLocalityDTO();
		command.setActive(loc.getActive());
		command.setCode(loc.getCode());
		command.setCreated(loc.getCreated());
		command.setId_city(loc.getId_locality());
		command.setId_locality(loc.getId_locality());
		command.setLogin(loc.getLogin());
		command.setModified(loc.getModified());
		command.setName(loc.getName());
		command.setOrderby(loc.getOrderby());

		return command;
	}

	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws ServletException, LocalityNotFoundException{
		logger.debug("---- ONSUBMIT ----");
		// Se obtiene el contexto del usuario
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserAcegi useracegi = (UserAcegi) auth.getPrincipal();
		
		// Se controlan los parametros active
		String active = ServletRequestUtils.getStringParameter(request, "active", NO_ACTIVE);

		//FIXME onsubmit locality
		
		UpdLocalityDTO dto = (UpdLocalityDTO) command;
		dto.setModified(new Date());
		dto.setActive(active);
		dto.setLogin(useracegi.getUserlogin());
		// Se persiste el objeto
		int i = retailService.updLocality(dto);
		logger.debug("Localidad, numero de registros modificados = " + i + " actualizado... OK=" + i);
		
		return new ModelAndView(getSuccessView()+"?state_id=");
	}

}
