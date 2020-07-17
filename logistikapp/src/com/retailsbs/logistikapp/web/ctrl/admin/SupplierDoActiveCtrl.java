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

import com.retailsbs.logistikapp.supplier.dto.UpdSupplierDTO;
import com.retailsbs.logistikapp.supplier.exception.SupplierNotFoundException;
import com.retailsbs.logistikapp.supplier.service.SupplierService;
import com.retailsbs.logistikapp.user.domain.UserAcegi;

/**
 * Controller para cambiar el estado activo del proveedor
 * @author jorge
 * @since 10-12-2014
 */
public class SupplierDoActiveCtrl implements Controller {
	
	protected final Log logger = LogFactory.getLog(getClass());

	public SupplierService supplierService;
	private String view;

	public void setSupplierService(SupplierService supplierService) {
		this.supplierService = supplierService;
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
		Long id_supplier = ServletRequestUtils.getLongParameter(arg0, "id");
		logger.info("id_supplier="+id_supplier);
		
		String active = ServletRequestUtils.getStringParameter(arg0, "active");
		logger.info("active="+active);

		/*-------------------------------------------------------*/
		// Se obtiene el objeto de dominio
		UpdSupplierDTO dto = new UpdSupplierDTO();
		dto.setActive(active);
		dto.setId_supplier(id_supplier);
		dto.setLogin(login);
		dto.setModified(new Date());
		int rows = supplierService.updSupplier(dto);
		logger.debug("Supplier id=" + dto.getId_supplier() + " actualizado " + rows + "...ok!");

		return new ModelAndView(view);
	}

}
