package com.retailsbs.logistikapp.web.ctrl.logistic;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import com.retailsbs.logistikapp.logistic.dto.TravelDTO;
import com.retailsbs.logistikapp.logistic.dto.TravelSearchCriteria;
import com.retailsbs.logistikapp.logistic.service.LogisticService;
import com.retailsbs.logistikapp.supplier.domain.Supplier;
import com.retailsbs.logistikapp.supplier.service.SupplierService;
import com.retailsbs.logistikapp.user.domain.Access;
import com.retailsbs.logistikapp.user.domain.Quantities;
import com.retailsbs.logistikapp.user.domain.UserAcegi;
import com.retailsbs.logistikapp.user.dto.AccessTinyDTO;
import com.retailsbs.logistikapp.user.dto.NotificationTodaySearchCriteria;
import com.retailsbs.logistikapp.user.service.UserService;

/**
 * Controlador de vista para lista de viaje
 * @author Nataly *
 * @since 19-08-2015 
 */
public class TravelHistoryViewListCtrl implements Controller {
	
	protected final Log logger = LogFactory.getLog(getClass());

	private UserService userService;
	private SupplierService supplierService;
	private LogisticService logisticService;
	private String view;
	private String ACTIVE;
	private String NO_ACTIVE;
	
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public void setSupplierService(SupplierService supplierService) {
		this.supplierService = supplierService;
	}
	public void setView(String view) {
		this.view = view;
	}
	public void setLogisticService(LogisticService logisticService) {
		this.logisticService = logisticService;
	}
	public void setACTIVE(String aCTIVE) {
		ACTIVE = aCTIVE;
	}
	public void setNO_ACTIVE(String nO_ACTIVE) {
		NO_ACTIVE = nO_ACTIVE;
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
		logger.debug("----- USER ACCESS -----");
		List<AccessTinyDTO> accesssupplier = userService.getAccessSupplierByIdUser(useracegi.getId_user());
		logger.debug("accesssupplier="+accesssupplier.size());
		/*-------------------------------------------------------*/
		// Se obtiene la lista de categorias de productos por proveedor
		Long idsupplier = null;
		if( arg0.getParameter("idsupplier") != null && !arg0.getParameter("idsupplier").equals("") )
			idsupplier = ServletRequestUtils.getLongParameter(arg0, "idsupplier");
		else {
			// Por defecto toma el primer elemento
			// que es el caso cuando se ingresa por primera vez a la pantalla
			idsupplier = ( accesssupplier.get(0) ).getId();
		}
		logger.debug("idsupplier="+idsupplier);
		
		Supplier supplier = supplierService.getSupplierById(idsupplier);
		logger.debug("Supplier, name="+supplier.getName());
		/*-------------------------------------------------------*/
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
		logger.debug("TRAVEL ==> fini="+sdf1.format(fini));
		logger.debug("TRAVEL ==> ffin="+sdf1.format(ffin));
		
		TravelSearchCriteria dto = new TravelSearchCriteria();
		dto.setId_supplier(idsupplier);
		
		String profile = useracegi.getProfile();
		
		List<String> status = new ArrayList<String>();
		status.add("FIN");
		status.add("CAN");
		dto.setStatus(status);			
		dto.setFini(fini);
		dto.setFfin(ffin);
		// si el usuario es un vendedor solo se le muestra sus datos
		if(profile.equals("DRI"))
			dto.setId_user(useracegi.getId_user());

		// Se obtiene lista de viajes por supplier
		List<TravelDTO> list = logisticService.getTravelByCriteria(dto);
		logger.debug("list="+list);
		
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("useracegi", useracegi);
		model.put("accesssupplier", accesssupplier);
		model.put("nttdto", nttdto);
		model.put("list", list);
		model.put("supplier", supplier);
		model.put("profile", profile);
		model.put("ACTIVE", ACTIVE);
		model.put("NO_ACTIVE", NO_ACTIVE);
		model.put("fini", sdf1.format(fini));
		model.put("ffin", sdf1.format(ffin));
		model.put("useraccess",useraccess);
		return new ModelAndView(view, model);
	}

}
