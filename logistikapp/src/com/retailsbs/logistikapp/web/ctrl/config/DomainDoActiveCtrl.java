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

import com.retailsbs.logistikapp.domain.dto.UpdDomainDTO;
import com.retailsbs.logistikapp.domain.service.DomainService;
import com.retailsbs.logistikapp.supplier.exception.SupplierNotFoundException;
import com.retailsbs.logistikapp.user.domain.UserAcegi;

/**
 * Controller para cambiar el estado activo del dominio
 * 
 * @author jorge
 * @since 10-12-2014
 */
public class DomainDoActiveCtrl implements Controller {

	protected final Log logger = LogFactory.getLog(getClass());

	public DomainService domainService;
	private String view;

	public void setDomainService(DomainService domainService) {
		this.domainService = domainService;
	}

	public void setView(String view) {
		this.view = view;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception,
			SupplierNotFoundException {
		/*-------------------------------------------------------*/
		// Se obtiene el contexto del usuario
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		UserAcegi ua = (UserAcegi) auth.getPrincipal();
		String login = ua.getUserlogin();
		logger.debug("login=" + login);

		/*-------------------------------------------------------*/
		// Se obtiene los datos por parametro
		Long id_domain = ServletRequestUtils.getLongParameter(arg0, "id");
		logger.info("id_domain=" + id_domain);

		String active = ServletRequestUtils.getStringParameter(arg0, "active");
		logger.info("active=" + active);

		/*-------------------------------------------------------*/
		// Se obtiene el objeto de dominio

		UpdDomainDTO dto = new UpdDomainDTO();
		dto.setActive(active);
		dto.setId_domain(id_domain);
		dto.setLogin(login);
		dto.setModified(new Date());
		int rows = domainService.updDomain(dto);
		logger.debug("Domain id=" + dto.getId_domain() + " actualizado " + rows
				+ "...ok!");

		return new ModelAndView(view);
	}

}
