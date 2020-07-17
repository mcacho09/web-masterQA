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

import com.retailsbs.logistikapp.domain.dto.UpdDomainContentDTO;
import com.retailsbs.logistikapp.domain.service.DomainService;
import com.retailsbs.logistikapp.supplier.exception.SupplierNotFoundException;
import com.retailsbs.logistikapp.user.domain.UserAcegi;

/**
 * Controller para cambiar el estado activo del proveedor
 * @author jorge
 * @since 10-12-2014
 */
public class DomainContentDoActiveCtrl implements Controller {
	
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
	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception, SupplierNotFoundException {
		/*-------------------------------------------------------*/
		// Se obtiene el contexto del usuario
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserAcegi ua = (UserAcegi) auth. getPrincipal();
		String login = ua.getUserlogin();
		logger.debug("login="+login);
		
		/*-------------------------------------------------------*/
		// Se obtiene los datos por parametro
		Long id_domain_content = ServletRequestUtils.getLongParameter(arg0, "id_domain_content");
		logger.info("id_domain_content="+id_domain_content);
		
		Long id_domain = ServletRequestUtils.getLongParameter(arg0, "id_domain");
		logger.info("id_domain="+id_domain);
		
		String active = ServletRequestUtils.getStringParameter(arg0, "active");
		logger.info("active="+active);

		String domain_name = ServletRequestUtils.getStringParameter(arg0, "domain_name");
		logger.info("domain_name="+domain_name);

		/*-------------------------------------------------------*/
		// Se obtiene el objeto de dominio

		UpdDomainContentDTO dto = new UpdDomainContentDTO();
		dto.setActive(active);
		dto.setId_domain_content(id_domain_content);
		dto.setLogin(login);
		dto.setModified(new Date());
		
		int rows = domainService.updDomainContent(dto);
		logger.debug("Domain Content id=" + dto.getId_domain_content() + " actualizado " + rows + "...ok!");

		return new ModelAndView(view+"?id_domain="+id_domain+"&domain_name="+domain_name);
	}

}
