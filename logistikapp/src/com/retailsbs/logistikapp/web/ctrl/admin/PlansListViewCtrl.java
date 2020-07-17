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

import com.retailsbs.logistikapp.supplier.domain.Plan;
import com.retailsbs.logistikapp.supplier.service.SupplierService;
import com.retailsbs.logistikapp.user.domain.UserAcegi;

public class PlansListViewCtrl implements Controller {
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	private String view;
	private SupplierService supplierService;

	public void setView(String view) {
		this.view = view;
	}

	public void setSupplierService(SupplierService supplierService) {
		this.supplierService = supplierService;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		logger.debug("---- USER-CONTEXT ----");

		// -------------------------------------------------------
		// Se obtiene el contexto del usuario
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserAcegi useracegi = (UserAcegi) auth.getPrincipal();
		
		// Se obtiene el listado de planes
		
		List<Plan> plans = supplierService.getAllPlans();
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("plans", plans);
		model.put("useracegi", useracegi);
		
		return new ModelAndView(view, model);
	}

}
