package com.retailsbs.logistikapp.web.ctrl.sales;

import java.text.SimpleDateFormat;
import java.util.Date;
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

import com.retailsbs.logistikapp.financial.dto.ReportByDriCriteria;
import com.retailsbs.logistikapp.financial.dto.ReportByDriDTO;
import com.retailsbs.logistikapp.financial.service.FinancialService;
import com.retailsbs.logistikapp.user.domain.Access;
import com.retailsbs.logistikapp.user.domain.UserAcegi;
import com.retailsbs.logistikapp.user.service.UserService;

public class ReportByDriViewCtrl implements Controller {
	
	protected final Log logger = LogFactory.getLog(getClass());

	private String view;
	private FinancialService financialService;
	private UserService userService;
	
	public void setView(String view) {
		this.view = view;
	}

	public void setFinancialService(FinancialService financialService) {
		this.financialService = financialService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		
		logger.debug("---- HANDLE/REQUEST ----");
		/*-------------------------------------------------------*/
		logger.debug("----- USER CONTEXT -----");
		// Se obtiene el contexto del usuario
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserAcegi useracegi = (UserAcegi) auth.getPrincipal();
		/*-------------------------------------------------------*/
		
		////////////////////////////////////////////////////////////////////////
		//Se obtienen los datos de acceso para el usuario
		List<Access> accesslist = userService.getAccessByIdUser(useracegi.getId_user());
		//Se obtiene el primer acceso por defecto
		Access useraccess = accesslist.get(0);
		////////////////////////////////////////////////////////////////////////
		
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy"); 
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); 
		Date hoy = new Date();
		Date fini = null;		
		Date ffin = null;
		if ( arg0.getParameter("fini") != null && !arg0.getParameter("fini").equals("") &&
				arg0.getParameter("ffin") != null && !arg0.getParameter("ffin").equals("") ) {
			fini = sdf2.parse( arg0.getParameter("fini") + " 00:00:00");
			ffin = sdf2.parse( arg0.getParameter("ffin") + " 23:59:59");
		} else {
			// Por defecto el rango de fechas es el dia actual
			fini = sdf2.parse( sdf1.format(hoy)+" 00:00:00");		
			ffin = sdf2.parse( sdf1.format(hoy)+" 23:59:59");
		}
		logger.debug("REPORT ==> fini="+sdf1.format(fini));
		logger.debug("REPORT ==> ffin="+sdf1.format(ffin));
		
		// Se obtiene el listado de ventas por vendedor
		ReportByDriCriteria rbdCriteria = new ReportByDriCriteria();
		rbdCriteria.setId_supplier(useraccess.getId_supplier());
		rbdCriteria.setFini(fini);
		rbdCriteria.setFfin(ffin);
		
		List<ReportByDriDTO> list = financialService.getReportByDri(rbdCriteria);
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("useracegi", useracegi);
		model.put("useraccess", useraccess);
		model.put("fini", sdf1.format(fini));
		model.put("ffin", sdf1.format(ffin));
		model.put("list", list);
		
		return new ModelAndView(view, model);
	}

}
