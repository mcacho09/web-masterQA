package com.retailsbs.logistikapp.web.ctrl.customer;

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

import com.retailsbs.logistikapp.manager.logistic.ManagerLogisticService;
import com.retailsbs.logistikapp.manager.logistic.dto.StoreInfoTravelDTO;
import com.retailsbs.logistikapp.retail.dto.StoreInfoSearchCriteria;
import com.retailsbs.logistikapp.supplier.domain.Supplier;
import com.retailsbs.logistikapp.supplier.service.SupplierService;
import com.retailsbs.logistikapp.user.domain.Access;
import com.retailsbs.logistikapp.user.domain.Quantities;
import com.retailsbs.logistikapp.user.domain.UserAcegi;
import com.retailsbs.logistikapp.user.dto.NotificationTodaySearchCriteria;
import com.retailsbs.logistikapp.user.service.UserService;

/**
 * Class controller para la vista busqueda de clientes
 * @author Juan Carlos Ramos Campos
 * @since 15/10/2015
 */
public class StoreSearchViewListCtrl implements Controller {
	
	protected final Log logger = LogFactory.getLog(getClass());

	private String view;
	private UserService userService;
	private SupplierService supplierService;
	private ManagerLogisticService managerlogisticService;
	private String TRAVEL_CREATED;
	private String TRAVEL_PROGRAM;
	private String TRAVEL_REVIEWED;
	private String TRAVEL_STARTED;
	private String TRAVEL_CANCEL;
	private String TRAVEL_FINISHED;
	private String GLO_CREATED;
	private String GLO_PROGRAM;
	private String GLO_REVIEWED;
	private String GLO_STARTED;
	private String GLO_CANCEL;
	private String GLO_FINISHED;

	public void setView(String view) {
		this.view = view;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public void setSupplierService(SupplierService supplierService) {
		this.supplierService = supplierService;
	}
	public void setManagerlogisticService(ManagerLogisticService managerlogisticService) {
		this.managerlogisticService = managerlogisticService;
	}
	public void setGLO_CREATED(String gLO_CREATED) {
		GLO_CREATED = gLO_CREATED;
	}
	public void setGLO_PROGRAM(String gLO_PROGRAM) {
		GLO_PROGRAM = gLO_PROGRAM;
	}
	public void setGLO_REVIEWED(String gLO_REVIEWED) {
		GLO_REVIEWED = gLO_REVIEWED;
	}
	public void setGLO_STARTED(String gLO_STARTED) {
		GLO_STARTED = gLO_STARTED;
	}
	public void setGLO_CANCEL(String gLO_CANCEL) {
		GLO_CANCEL = gLO_CANCEL;
	}
	public void setGLO_FINISHED(String gLO_FINISHED) {
		GLO_FINISHED = gLO_FINISHED;
	}
	public void setTRAVEL_CREATED(String tRAVEL_CREATED) {
		TRAVEL_CREATED = tRAVEL_CREATED;
	}
	public void setTRAVEL_PROGRAM(String tRAVEL_PROGRAM) {
		TRAVEL_PROGRAM = tRAVEL_PROGRAM;
	}
	public void setTRAVEL_REVIEWED(String tRAVEL_REVIEWED) {
		TRAVEL_REVIEWED = tRAVEL_REVIEWED;
	}
	public void setTRAVEL_STARTED(String tRAVEL_STARTED) {
		TRAVEL_STARTED = tRAVEL_STARTED;
	}
	public void setTRAVEL_CANCEL(String tRAVEL_CANCEL) {
		TRAVEL_CANCEL = tRAVEL_CANCEL;
	}
	public void setTRAVEL_FINISHED(String tRAVEL_FINISHED) {
		TRAVEL_FINISHED = tRAVEL_FINISHED;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		logger.debug("---- REFERENCEDATA ----");
		/*-------------------------------------------------------*/
		logger.debug("----- USER CONTEXT -----");
		// Se obtiene el contexto del usuario
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserAcegi useracegi = (UserAcegi) auth.getPrincipal();
		/*-------------------------------------------------------*/
		// Se obtienen los datos de acceso para el usuario
		List<Access> accesslist = userService.getAccessByIdUser(useracegi.getId_user());
		// Se obtiene el primer acceso por defecto
		Access useraccess = accesslist.get(0);
		Long id_supplier = useraccess.getId_supplier();
		logger.debug("SEARCH ==> id_supplier="+id_supplier);
		Supplier supplier = supplierService.getSupplierById(id_supplier);
		logger.debug("SEARCH ==> supplier="+supplier.getName());
		/*-------------------------------------------------------*/
		logger.debug("----- HEADER/NOTIFICATION -----");
        NotificationTodaySearchCriteria dtoqty = new NotificationTodaySearchCriteria();
        dtoqty.setProfile(useracegi.getProfile());
        dtoqty.setFecha(new Date());
        dtoqty.setId_supplier( id_supplier );
        dtoqty.setId_user(useracegi.getId_user());
        
        Quantities nttdto = userService.getQtyAlertAndMessagesByIdUser(dtoqty);
		logger.debug("alert="+nttdto.getAlert_qty()+" | message="+nttdto.getMessage_qty()+" | todo="+nttdto.getTodo_qty());
		/*-------------------------------------------------------*/
		/* Tipo de busqueda - por nombre o por codigo*/
		String searchByName = null;
		String searchByCode = null;
		String opcionRadio = null;
		String text = null;

		if ( arg0.getParameter("optionsRadios") != null && !arg0.getParameter("optionsRadios").equals("") )
				opcionRadio = ServletRequestUtils.getStringParameter(arg0, "optionsRadios");
		logger.debug("opcionRadio = "+opcionRadio);
		
		if ( arg0.getParameter("searchByName") != null && !arg0.getParameter("searchByName").equals("") )
				searchByName = ServletRequestUtils.getStringParameter(arg0,"searchByName");
				text = searchByName;
		logger.debug("SEARCH ==> searchByName="+searchByName);
		
		if(opcionRadio != null && opcionRadio.equals("option2")){
			searchByCode = ServletRequestUtils.getStringParameter(arg0,"searchByName");
			searchByName = null;
		}
		logger.debug("SEARCH ==> searchByCode="+searchByCode);

		// Se define criterio para la busqueda de stores (clientes)
		 StoreInfoSearchCriteria dto = new StoreInfoSearchCriteria();
		dto.setActive("S");
		dto.setId_supplier(id_supplier);
		dto.setStore_name(searchByName);
		dto.setStore_code(searchByCode);
		dto.setProfile( useracegi.getProfile() );
		
		searchByName = text;
		// Se obtien el listado de clientes
		List<StoreInfoTravelDTO> list = managerlogisticService.getStoreInfoTravel(dto);
		logger.debug("SEARCH ==> list = "+list.size());
		
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("useracegi", useracegi);
		model.put("useraccess", useraccess);
		model.put("supplier", supplier);
		model.put("nttdto", nttdto);

		model.put("searchByName", searchByName);
		model.put("opcionRadio", opcionRadio);
		model.put("list", list);
		
		model.put("TRAVEL_CANCEL", TRAVEL_CANCEL);
		model.put("TRAVEL_CREATED", TRAVEL_CREATED);
		model.put("TRAVEL_FINISHED", TRAVEL_FINISHED);
		model.put("TRAVEL_PROGRAM", TRAVEL_PROGRAM);
		model.put("TRAVEL_REVIEWED", TRAVEL_REVIEWED);
		model.put("TRAVEL_STARTED", TRAVEL_STARTED);
		
		model.put("GLO_CANCEL", GLO_CANCEL);
		model.put("GLO_CREATED", GLO_CREATED);
		model.put("GLO_FINISHED", GLO_FINISHED);
		model.put("GLO_PROGRAM", GLO_PROGRAM);
		model.put("GLO_REVIEWED", GLO_REVIEWED);
		model.put("GLO_STARTED", GLO_STARTED);
		
		return new ModelAndView(view, model);
	}

}
