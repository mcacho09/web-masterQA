package com.retailsbs.logistikapp.web.ctrl.sales;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContextHolder;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.ibatis.common.logging.Log;
import com.ibatis.common.logging.LogFactory;
import com.retailsbs.logistikapp.financial.dto.InfoProductsToTicketCriteria;
import com.retailsbs.logistikapp.financial.dto.InfoProductsToTicketDTO;
import com.retailsbs.logistikapp.financial.dto.InfoTicketDTO;
import com.retailsbs.logistikapp.financial.service.FinancialService;
import com.retailsbs.logistikapp.user.domain.Access;
import com.retailsbs.logistikapp.user.domain.UserAcegi;
import com.retailsbs.logistikapp.user.service.UserService;

public class SaleDetailViewCtrl implements Controller {
	
	private static final Log logger = LogFactory.getLog(SaleDetailViewCtrl.class);

	private String view;
	private UserService userService;
	private FinancialService financialService;
	
	public void setView(String view) {
		this.view = view;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public void setFinancialService(FinancialService financialService) {
		this.financialService = financialService;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
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
		
		// Se obtiene el id de la orden
		Long id_order = ServletRequestUtils.getLongParameter(arg0, "ido", 0l);
		
		InfoTicketDTO dto = financialService.getInfoTicket(id_order);

		Long totalProducts = 0l;
		Double totalVenta = 0d;
		Long totalProductsDev = 0l;
		Double totalVentaDev = 0d;

		InfoProductsToTicketCriteria criteria = new InfoProductsToTicketCriteria();
		criteria.setId_order(id_order);
		criteria.setTypetrx("VTA");

		List<InfoProductsToTicketDTO> listvta = financialService.getInfoProductsToTicket(criteria);
		
		for (InfoProductsToTicketDTO i : listvta) {
			totalProducts += i.getQuantity();
			totalVenta += i.getSale();
		}

		criteria.setTypetrx("CHG");

		List<InfoProductsToTicketDTO> listchg = financialService.getInfoProductsToTicket(criteria);

		criteria.setTypetrx("DEV");

		List<InfoProductsToTicketDTO> listdev = financialService.getInfoProductsToTicket(criteria);
		
		for (InfoProductsToTicketDTO i : listdev) {
			totalProductsDev += i.getQuantity();
			totalVentaDev += i.getSale();
		}
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("infoticket", dto);
		model.put("listvta", listvta);
		model.put("totalProducts", totalProducts);
		model.put("totalVenta", totalVenta);
		model.put("listchg", listchg);
		model.put("listdev", listdev);
		model.put("totalProductsDev", totalProductsDev);
		model.put("totalVentaDev", totalVentaDev);
		model.put("id_order", id_order);
		model.put("useracegi", useracegi);
		model.put("id_supplier", id_supplier);
		
		return new ModelAndView(view, model);
	}

}
