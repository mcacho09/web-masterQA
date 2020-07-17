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
import com.retailsbs.logistikapp.supplier.domain.Supplier;
import com.retailsbs.logistikapp.supplier.service.SupplierService;
import com.retailsbs.logistikapp.user.domain.Access;
import com.retailsbs.logistikapp.user.domain.UserAcegi;
import com.retailsbs.logistikapp.user.service.UserService;

/**
 * Class controller para la vista de planpayment
 * @author David
 * @since 11/05/2016
 */
public class PlanPaymentCtrl implements Controller {

	protected final Log logger = LogFactory.getLog(getClass());

	private String viewsup; // Vista para usuario supplier, retail, store

	private UserService userService;
	private SupplierService supplierService;
	private String PERSONAL;
	private String GLOBAL;

	public void setViewsup(String viewsup) {
		this.viewsup = viewsup;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public void setSupplierService(SupplierService supplierService) {
		this.supplierService = supplierService;
	}
	public void setPERSONAL(String pERSONAL) {
		PERSONAL = pERSONAL;
	}
	public void setGLOBAL(String gLOBAL) {
		GLOBAL = gLOBAL;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		logger.debug("---- USER-CONTEXT ----");
		
		// -------------------------------------------------------
		// Se obtiene el contexto del usuario
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserAcegi useracegi = (UserAcegi) auth.getPrincipal();
		logger.debug("HOME ==> id_user=" + useracegi.getId_user());
		logger.debug("HOME ==> profile=" + useracegi.getProfile());
		Long id_user = useracegi.getId_user();
		Long id_supplier = null;
		Plan userplan = null;
		Long id_plan = null;
		Access useraccess = null;
		Supplier supplier_obj = null;
		
		// Se obtienen datos asociados al supplier para todos los usuarios
		// menos para los usuarios con perfil administrador
		logger.debug("---->"+useracegi.getProfile());
		if ( !useracegi.getProfile().equals("ADM") || !useracegi.getProfile().equals("SOP")) {
			
			// Se obtienen los datos de acceso para el usuario
			List<Access> accesslist = userService.getAccessByIdUser(id_user);
			// Se obtiene el primer acceso por defecto
			useraccess = accesslist.get(0);
			// Se obtiene el id del supplier
			id_supplier = useraccess.getId_supplier();
			supplier_obj = supplierService.getSupplierById(id_supplier);
			logger.debug("HOME ==> id_supplier=" + id_supplier);
			// Se obtiene informacion del plan de usuarios
			userplan = supplierService.getPlanById(supplier_obj.getId_plan());
			id_plan = userplan.getId_plan();
			logger.debug("HOME ==> userplan=" + userplan.getPlan_name());
		}
		
		// -------------------------------------------------------
		// Dependiendo del perfil de usuario se define la vista que se utilizará
		String vista = viewsup;
		logger.debug("HOME ==> view=" + vista);
		// -------------------------------------------------------
		// Se define el objeto para enviar datos a la vista
		Map<String, Object> model = new HashMap<String, Object>();

		model.put("useracegi", useracegi);
		model.put("userplan", id_plan);
		model.put("PERSONAL", PERSONAL);
		model.put("GLOBAL", GLOBAL);

		

		return new ModelAndView(vista, model);
	}

}
