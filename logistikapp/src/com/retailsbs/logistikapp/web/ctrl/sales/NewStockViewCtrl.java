package com.retailsbs.logistikapp.web.ctrl.sales;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.ibatis.common.logging.Log;
import com.ibatis.common.logging.LogFactory;
import com.retailsbs.logistikapp.financial.domain.Almacen;
import com.retailsbs.logistikapp.financial.service.FinancialService;
import com.retailsbs.logistikapp.retail.service.RetailService;
import com.retailsbs.logistikapp.user.domain.Access;
import com.retailsbs.logistikapp.user.domain.UserAcegi;
import com.retailsbs.logistikapp.user.service.UserService;

public class NewStockViewCtrl<Access> implements Controller {
	
	private static final Log logger = LogFactory.getLog(NewStockViewCtrl.class);
	
	private String view;
	private UserService userService;
	private RetailService retailService;
	private String update;
	private FinancialService financialService;

	public void setView(String view) {
		this.view = view;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public void setRetailService(RetailService retailService) {
		this.retailService = retailService;
	}
	public void setUpdate(String update) {
		this.update = update;
	}
	
	public void setFinancialService(FinancialService financialService) {
		this.financialService = financialService;
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		logger.debug("NewStockView ==> HANDLE/REQUEST");
		
		/*-------------------------------------------------------*/
		// Se obtiene el contexto del usuario
		logger.debug("UserDetail ==> USER CONTEXT");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserAcegi useracegi = (UserAcegi) auth.getPrincipal();
		Long id_user = useracegi.getId_user();
		String profile = useracegi.getProfile();
		logger.debug("SaleDetail ==> id_user="+id_user);
		logger.debug("SaleDetail ==> profile="+profile);
		List<Access> useraccess = (List<Access>) userService.getAccessByIdUser(useracegi.getId_user());
		Access access = useraccess.get(0);
		Long id_supplier = ((Almacen) access).getId_supplier();
		logger.debug("SaleDetail ==> id_supplier="+id_supplier);
		List<Almacen> almacens = financialService.getAlmacens(id_supplier);
		boolean isFirstAlmacen = (almacens == null || almacens.size() == 0);
		
		Almacen almacen = financialService.getAlmacenByIdSupplier(id_supplier);
		
		if (almacen == null || almacen.getId_user() == null) {
			isFirstAlmacen = true;
		}
		
		if (update.contentEquals("S") && almacen == null) {
			return new ModelAndView("redirect:/newstock.htm");
		}
		
		if (update.contentEquals("N") && almacen != null) {
			return new ModelAndView("redirect:/updstock.htm");
		}
			
		Long id_almacen = (almacen == null ? 0 : almacen.getId_almacen());
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("id_user", id_user);
		model.put("useracegi", useracegi);
		model.put("id_supplier", id_supplier);
		model.put("update", update);
		model.put("isFirstAlmacen", isFirstAlmacen);
		model.put("id_almacen", id_almacen);
		
		return new ModelAndView(view, model);
	}
	
	

}
