package com.retailsbs.logistikapp.web.ctrl.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContextHolder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.retailsbs.logistikapp.supplier.dto.SupplierExtDTO;
import com.retailsbs.logistikapp.supplier.service.SupplierService;
import com.retailsbs.logistikapp.user.domain.UserAcegi;

/**
 * Controller para la vista de administración de proveedores
 * @author JSilva
 * @since 08-12-2014 - JSilva
 * @modified 01-07-2015 - JSilva - Funcionalidad para el módulo de administración
 */
public class SupplierViewListCtrl implements Controller {
	
	protected final Log logger = LogFactory.getLog(getClass());

	private SupplierService supplierService;
	private String view;
	private String ACTIVE;
	private String NO_ACTIVE;

	public void setSupplierService(SupplierService supplierService) {
		this.supplierService = supplierService;
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
		// Se obtiene el contexto del usuario
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserAcegi useracegi = (UserAcegi) auth.getPrincipal();
		/*-------------------------------------------------------*/
		// Se obtiene el listado de todos los proveedores
		List<SupplierExtDTO> list = supplierService.getSupplierExt();
		logger.info("list= "+list.size());
		/*-------------------------------------------------------*/

		Map<String,Object> model = new HashMap<String,Object>();
		model.put("useracegi", useracegi);
		
		model.put("list", list);

		model.put("ACTIVE", ACTIVE);
		model.put("NO_ACTIVE", NO_ACTIVE);
		
		return new ModelAndView(view, model);
	}

}
