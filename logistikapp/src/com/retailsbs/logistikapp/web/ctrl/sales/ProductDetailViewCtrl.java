package com.retailsbs.logistikapp.web.ctrl.sales;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContextHolder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.retailsbs.logistikapp.financial.domain.Product;
import com.retailsbs.logistikapp.financial.service.FinancialService;
import com.retailsbs.logistikapp.supplier.domain.Supplier;
import com.retailsbs.logistikapp.supplier.service.SupplierService;
import com.retailsbs.logistikapp.user.domain.Access;
import com.retailsbs.logistikapp.user.domain.UserAcegi;
import com.retailsbs.logistikapp.user.dto.HeaderNotificationDTO;
import com.retailsbs.logistikapp.user.service.UserService;

/**
 * Controller para la vista detalle de product
 * @author juan carlos ramos campos
 * @since 29-01-2015
 * @modified 16-02-2015 - JORGE - Integraci�n para contador y alertas en barra navegaci�n
 */
public class ProductDetailViewCtrl implements Controller {
	
	protected final Log logger = LogFactory.getLog(getClass());

	private UserService userService;
	private SupplierService supplierService;
	private FinancialService financialService;
	private String view;
	private String ACTIVE;
	private String IMG_PRODUCT;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public void setSupplierService(SupplierService supplierService) {
		this.supplierService = supplierService;
	}
	public void setFinancialService(FinancialService financialService) {
		this.financialService = financialService;
	}
	public void setView(String view) {
		this.view = view;
	}
	public void setACTIVE(String aCTIVE) {
		ACTIVE = aCTIVE;
	}
	public void setIMG_PRODUCT(String iMG_PRODUCT) {
		IMG_PRODUCT = iMG_PRODUCT;
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		/*-------------------------------------------------------*/
		logger.debug("----- USER ACCESS -----");
		// Se obtiene el contexto del usuario
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserAcegi useracegi = (UserAcegi) auth.getPrincipal();
		List<Access> access = userService.getAccessByIdUser(useracegi.getId_user());
		logger.debug("access="+access.size());
		// Por defecto toma el primer elemento
		Supplier supplier = supplierService.getSupplierById( (access.get(0) ).getId_supplier() );
		logger.debug("supplier="+supplier.getName());
		logger.debug("----- USER ACCESS -----");
		/*-------------------------------------------------------*/
		logger.debug("----- HEADER/NOTIFICATION -----");
		HeaderNotificationDTO nttdto = userService.getHeaderNotificationByIdUser(useracegi.getId_user());
		/*-------------------------------------------------------*/

		Long id_product = null;
		if(arg0.getParameter("id") != null && !arg0.getParameter("id").equals(""))
			id_product = ServletRequestUtils.getLongParameter(arg0, "id");
		logger.info("id_producto="+id_product);
		
		String category = null;
		if(arg0.getParameter("category") != null && !arg0.getParameter("category").equals(""))
			category = ServletRequestUtils.getStringParameter(arg0, "category");
		logger.info("category="+category);
		
		Product producto = financialService.getProductById(id_product);
		logger.info("producto obtenido id="+producto.getId_product());
		
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("useracegi", useracegi);
		model.put("useraccess", supplier);
		model.put("nttdto", nttdto);

		model.put("producto", producto);
		model.put("category", category);
		
		model.put("ACTIVE", ACTIVE);
		model.put("IMG_PRODUCT", IMG_PRODUCT);

		return new ModelAndView(view, model);
	}

}
