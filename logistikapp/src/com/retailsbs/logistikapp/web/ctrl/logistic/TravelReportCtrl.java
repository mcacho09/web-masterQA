package com.retailsbs.logistikapp.web.ctrl.logistic;
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
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.retailsbs.logistikapp.logistic.domain.Travel;
import com.retailsbs.logistikapp.logistic.dto.WayBillDTO;
import com.retailsbs.logistikapp.logistic.service.LogisticService;
import com.retailsbs.logistikapp.supplier.domain.Supplier;
import com.retailsbs.logistikapp.supplier.service.SupplierService;
import com.retailsbs.logistikapp.user.domain.Access;
import com.retailsbs.logistikapp.user.domain.Quantities;
import com.retailsbs.logistikapp.user.domain.User;
import com.retailsbs.logistikapp.user.domain.UserAcegi;
import com.retailsbs.logistikapp.user.dto.AccessTinyDTO;
import com.retailsbs.logistikapp.user.dto.NotificationTodaySearchCriteria;
import com.retailsbs.logistikapp.user.service.UserService;
/**
 * Controlador de vista onway
 * @author Nataly *
 * @since 24-08-2015 
 */
public class TravelReportCtrl implements Controller {

	protected final Log logger = LogFactory.getLog(getClass());

	private UserService userService;
	private SupplierService supplierService;
	private LogisticService logisticService;
	private String view;
	private Long TRAVEL_TIME;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setView(String view) {
		this.view = view;
	}
	public void setLogisticService(LogisticService logisticService) {
		this.logisticService = logisticService;
	}

	public void setSupplierService(SupplierService supplierService){
		this.supplierService = supplierService;
	}
	
	public void setTRAVEL_TIME(Long tRAVEL_TIME) {
		TRAVEL_TIME = tRAVEL_TIME;
	}

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
		
		logger.debug("----- USER ACCESS -----");
		List<AccessTinyDTO> accesssupplier = userService.getAccessSupplierByIdUser(useracegi.getId_user());
		logger.debug("accesssupplier="+accesssupplier.size());
		NotificationTodaySearchCriteria dtoqty = new NotificationTodaySearchCriteria();
		dtoqty.setProfile(useracegi.getProfile());
		dtoqty.setFecha(new Date());
		dtoqty.setId_supplier(useraccess.getId_supplier());
		dtoqty.setId_user(useracegi.getId_user());
		logger.debug("----- HEADER/NOTIFICATION -----");
		Quantities nttdto = userService.getQtyAlertAndMessagesByIdUser(dtoqty);
		//HeaderNotificationDTO nttdto = userService.getHeaderNotificationByIdUser(useracegi.getId_user());
		logger.debug("alert="+nttdto.getAlert_qty()+" | message="+nttdto.getMessage_qty()+" | todo="+nttdto.getTodo_qty());
		/*-------------------------------------------------------*/
		
		// Se obtiene parametro id_travel
		Long id_travel;
		id_travel = ServletRequestUtils.getRequiredLongParameter(arg0, "idt");
		Travel travel = logisticService.getTravelById(id_travel);
		List<WayBillDTO> waybills = logisticService.getWayBillByIdTravel(id_travel);
		User chofer = userService.getUserById(travel.getId_user());
		Long id_sup = userService.getAccessByIdUser(useracegi.getId_user()).get(0).getId_supplier();
		logger.debug("id_supplier="+id_sup);
		
		//Se obtiene el id de supplier
		Supplier supplier = supplierService.getSupplierById(id_sup);
		Long traveltime = supplier.getTravel_time();
		if(supplier.getTravel_time() == null){
			traveltime = TRAVEL_TIME;
		}
		/*-------------------------------------------------------*/
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("useracegi", useracegi);
		model.put("accesssupplier", accesssupplier);
		model.put("nttdto", nttdto);
		model.put("viaje", travel);
		model.put("waybills", waybills);
		model.put("chofer", chofer);		
		model.put("id_sup", id_sup);		
		model.put("useraccess",useraccess);
		model.put("traveltime", traveltime);
		model.put("id_travel", id_travel);
		model.put("supplier", supplier);
		return new ModelAndView(view, model);
	}

}

