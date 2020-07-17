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
import com.retailsbs.logistikapp.user.domain.Access;
import com.retailsbs.logistikapp.user.domain.UserAcegi;
import com.retailsbs.logistikapp.user.service.UserService;

public class AdmTemplateStockViewCtrl implements Controller {
	
	private Log logger = LogFactory.getLog(AdmTemplateStockViewCtrl.class);
	
	private String view;
	private UserService userService;

	public void setView(String view) {
		this.view = view;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		
		logger.debug("UserDetail ==> USER CONTEXT");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserAcegi useracegi = (UserAcegi) auth.getPrincipal();
		Long id_user = useracegi.getId_user();
		String profile = useracegi.getProfile();
		logger.debug("SaleDetail ==> id_user="+id_user);
		logger.debug("SaleDetail ==> profile="+profile);
		List<Access> useraccess = userService.getAccessByIdUser(useracegi.getId_user());
		Access access = useraccess.get(0);
		Long id_supplier = access.getId_supplier();
		logger.debug("SaleDetail ==> id_supplier="+id_supplier);
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("id_user", id_user);
		model.put("useracegi", useracegi);
		model.put("id_supplier", id_supplier);
		
		return new ModelAndView(view, model);
	}

}
