package com.retailsbs.logistikapp.web.ctrl.admin;

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

import com.retailsbs.logistikapp.retail.dto.UpdCountryDTO;
import com.retailsbs.logistikapp.retail.service.RetailService;
import com.retailsbs.logistikapp.supplier.exception.SupplierNotFoundException;
import com.retailsbs.logistikapp.user.domain.UserAcegi;

/**
 * Controller para cambiar el estado activo del pais
 * @author jorge
 * @since 10-12-2014
 */
public class CountryDoActiveCtrl implements Controller {
	
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
		Long id_country = ServletRequestUtils.getLongParameter(arg0, "id");
		logger.info("id_country="+id_country);
		
		String active = ServletRequestUtils.getStringParameter(arg0, "active");
		logger.info("active="+active);

		/*-------------------------------------------------------*/
		// Se obtiene el objeto de dominio

		UpdCountryDTO dto = new UpdCountryDTO();
		dto.setActive(active);
		dto.setId_country(id_country);
		dto.setLogin(login);
		dto.setModified(new Date());
		int rows = retailService.updCountry(dto);
		logger.debug("Country id=" + dto.getId_country() + " actualizado " + rows + "...ok!");

		return new ModelAndView(view);
	}

}
