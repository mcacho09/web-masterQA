package com.retailsbs.logistikapp.web.ctrl.logistic;

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

import com.retailsbs.logistikapp.logistic.domain.Travel;
import com.retailsbs.logistikapp.logistic.domain.WayBill;
import com.retailsbs.logistikapp.logistic.service.LogisticService;
import com.retailsbs.logistikapp.user.domain.UserAcegi;

/**
 * Controlador de vista para confirmar viaje
 * @author Nataly *
 * @since 24-08-2015 
 */
public class TravelConfirmCtrl implements Controller {
	
	protected final Log logger = LogFactory.getLog(getClass());

	private LogisticService logisticService;
	private String view;
	private String TRAVEL_REVIEWED;
	public void setView(String view) {
		this.view = view;
	}
	public void setLogisticService(LogisticService logisticService) {
		this.logisticService = logisticService;
	}
	public void setTRAVEL_REVIEWED(String tRAVEL_REVIEWED) {
		TRAVEL_REVIEWED = tRAVEL_REVIEWED;
	}
	
	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		logger.debug("---- ONSUBMIT ----");
		// Se obtiene el contexto del usuario
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserAcegi useracegi = (UserAcegi) auth.getPrincipal();
		//SE OBTIENE

		// se obtienen los ids
		Long id_travel = ServletRequestUtils.getRequiredLongParameter(arg0, "idt");
		String ids = ServletRequestUtils.getRequiredStringParameter(arg0, "ids");
		String []ids_waybill = ids.split(",");
		int orderby=1;
		for(String id:ids_waybill){
			Long id_waybill = Long.parseLong(id);
			WayBill waybill = new WayBill();
			waybill.setId_waybill(id_waybill);
			waybill.setOrderby(orderby);
			waybill.setLog_modified(new Date());
			waybill.setLog_modified_login(useracegi.getLogin());
			logisticService.updWayBillById(waybill);
			orderby++;			
		}
		Travel travel = logisticService.getTravelById(id_travel);
		travel.setStarted(new Date());
		travel.setStatus(TRAVEL_REVIEWED);		
		logisticService.updTravelById(travel );
		return new ModelAndView(view + "?idt="+id_travel);
	}
	
	
}
