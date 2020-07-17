package com.retailsbs.logistikapp.web.ctrl.logistic;

import java.util.Calendar;
import java.util.Date;
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
import com.retailsbs.logistikapp.logistic.domain.Travel;
import com.retailsbs.logistikapp.logistic.dto.StoresNotVisitedByDateAndSupplierCriteria;
import com.retailsbs.logistikapp.logistic.dto.StoresNotVisitedByDateAndSupplierDTO;
import com.retailsbs.logistikapp.logistic.dto.TravelsToVisitedCriteria;
import com.retailsbs.logistikapp.logistic.service.LogisticService;
import com.retailsbs.logistikapp.user.domain.Access;
import com.retailsbs.logistikapp.user.domain.UserAcegi;
import com.retailsbs.logistikapp.user.service.UserService;

public class UsersNotVisitedViewCtrl implements Controller {

	public static final Log logger = LogFactory.getLog(UsersNotVisitedViewCtrl.class);
	
	private String view;
	private LogisticService logisticService;
	private UserService userService;
	
	public void setView(String view) {
		this.view = view;
	}
	public void setLogisticService(LogisticService logisticService) {
		this.logisticService = logisticService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		logger.debug("SaleDetail ==> HANDLE/REQUEST");
		
		/*-------------------------------------------------------*/
		// Se obtiene el contexto del usuario
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
		
		//Se obtiene el listado de tiendas
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, -1);
		
		//Se obtiene el listado de tiendas no visitadas
		StoresNotVisitedByDateAndSupplierCriteria criteria = new StoresNotVisitedByDateAndSupplierCriteria();
		criteria.setDate(calendar.getTime());
		criteria.setId_supplier(id_supplier);
		List<StoresNotVisitedByDateAndSupplierDTO> list = logisticService.getStoresNotVisitedByDateAndSupplier(criteria);
		
		//Se obtiene el listados de viajes apartir de hoy
		TravelsToVisitedCriteria tvcriteria = new TravelsToVisitedCriteria();
		tvcriteria.setDate(new Date());
		tvcriteria.setId_supplier(id_supplier);
		List<Travel> travels = logisticService.getTravelsToVisited(tvcriteria);
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("useracegi", useracegi);
		model.put("id_supplier", id_supplier);
		model.put("list", list);
		model.put("travels", travels);
		
		return new ModelAndView(view, model);
	}

}
