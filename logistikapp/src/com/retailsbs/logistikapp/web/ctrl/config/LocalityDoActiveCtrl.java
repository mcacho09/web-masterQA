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

import com.retailsbs.logistikapp.retail.dto.UpdLocalityDTO;
import com.retailsbs.logistikapp.retail.service.RetailService;
import com.retailsbs.logistikapp.supplier.exception.SupplierNotFoundException;
import com.retailsbs.logistikapp.user.domain.UserAcegi;

/**
 * Controller para cambiar el estado activo del localidad
 * @author jorge
 * @since 10-12-2014
 */
public class LocalityDoActiveCtrl implements Controller {
	
	protected final Log logger = LogFactory.getLog(getClass());

	public RetailService retailService;
	private String view;

	public void setRetailService(RetailService retailService) {
		this.retailService = retailService;
	}

	public void setView(String view) {
		this.view = view;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception, SupplierNotFoundException {
		/*-------------------------------------------------------*/
		// Se obtiene el contexto del usuario
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserAcegi ua = (UserAcegi) auth. getPrincipal();
		String login = ua.getUserlogin();
		logger.debug("login="+login);
		
		/*-------------------------------------------------------*/
		// Se obtiene los datos por parametro
		Long id_locality = ServletRequestUtils.getLongParameter(arg0, "id");
		logger.info("id_locality="+id_locality);
		
		String active = ServletRequestUtils.getStringParameter(arg0, "active");
		logger.info("active="+active);

		/*-------------------------------------------------------*/
		// Se obtiene el objeto de dominio

		Long state_id = null;
		if(arg0.getParameter("state_id")!=null && !arg0.getParameter("state_id").equals(""))
			state_id = ServletRequestUtils.getLongParameter(arg0, "state_id");
		logger.info("state_id="+state_id);
		
		Long city_id = null;
		if(arg0.getParameter("city_id")!=null && !arg0.getParameter("city_id").equals(""))
			city_id = ServletRequestUtils.getLongParameter(arg0, "city_id");
		logger.info("city_id="+city_id);
		
		UpdLocalityDTO dto = new UpdLocalityDTO();
		dto.setActive(active);
		dto.setId_locality(id_locality);
		dto.setLogin(login);
		dto.setModified(new Date());
		int rows = retailService.updLocality(dto);
		logger.debug("Locality id=" + dto.getId_locality() + " actualizado " + rows + "...ok!");

		return new ModelAndView(view+"?state_id="+state_id+"&city_id="+city_id);
	}

}
