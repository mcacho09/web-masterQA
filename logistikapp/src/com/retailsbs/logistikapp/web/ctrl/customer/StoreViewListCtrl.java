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

import com.retailsbs.logistikapp.retail.domain.Retail;
import com.retailsbs.logistikapp.retail.dto.RetailSearchCriteria;
import com.retailsbs.logistikapp.retail.dto.StoreDTO;
import com.retailsbs.logistikapp.retail.dto.StoreSearchCriteria;
import com.retailsbs.logistikapp.retail.service.RetailService;
import com.retailsbs.logistikapp.supplier.domain.Plan;
import com.retailsbs.logistikapp.supplier.domain.Supplier;
import com.retailsbs.logistikapp.supplier.service.SupplierService;
import com.retailsbs.logistikapp.user.domain.Access;
import com.retailsbs.logistikapp.user.domain.Quantities;
import com.retailsbs.logistikapp.user.domain.UserAcegi;
import com.retailsbs.logistikapp.user.dto.NotificationTodaySearchCriteria;
import com.retailsbs.logistikapp.user.service.UserService;
/**
 * Class controller para la vista administración de tiendas
 * @author Juan
 * @since 25-11-2014
 * @modified 30-12-2014 - Jorge - Estandarización estructura de controller. Busqueda de listado de tiendas (store) a partir de un cliente (retail) especifico
 * @modified 16-11-2015 - Jorge - Actualización controller
 */
public class StoreViewListCtrl implements Controller {
	
	protected final Log logger = LogFactory.getLog(getClass());

	private RetailService retailService;
	private UserService userService;
	private SupplierService supplierService;
	

	private String view;
	private String ACTIVE;
	private String NO_ACTIVE;

	public void setRetailService(RetailService retailService) {
		this.retailService = retailService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public void setView(String view) {
		this.view = view;
	}
	public void setACTIVE(String aCTIVE) {
		ACTIVE = aCTIVE;
	}
	public void setNO_ACTIVE(String nO_ACTIVE) {
		NO_ACTIVE = nO_ACTIVE;
	}
	public void setSupplierService(SupplierService supplierService) {
		this.supplierService = supplierService;
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
		logger.debug("----- HEADER/NOTIFICATION -----");
        NotificationTodaySearchCriteria dtoqty = new NotificationTodaySearchCriteria();
        dtoqty.setProfile(useracegi.getProfile());
        dtoqty.setFecha(new Date());
        dtoqty.setId_supplier(useraccess.getId_supplier());
        dtoqty.setId_user(useracegi.getId_user());
        
        Quantities nttdto = userService.getQtyAlertAndMessagesByIdUser(dtoqty);
        //HeaderNotificationDTO nttdto = userService.getHeaderNotificationByIdUser(useracegi.getId_user());

		logger.debug("alert="+nttdto.getAlert_qty()+" | message="+nttdto.getMessage_qty()+" | todo="+nttdto.getTodo_qty());
		/*-------------------------------------------------------*/
		// Se obtiene el parametro de id_retail y su respectivo objeto de dominio
		Long id_retail = ServletRequestUtils.getLongParameter(arg0, "id");
		logger.debug("id_retail="+id_retail);
		Retail retail = retailService.getRetailById(id_retail);
		/*-------------------------------------------------------*/
		// Se obtiene el id_supplier
		Long idsupplier = retail.getId_supplier();
		logger.debug("idsupplier="+idsupplier);
		
		Supplier supplier = supplierService.getSupplierById(idsupplier);
		/*-------------------------------------------------------*/
		//obtiene valor de id_local
		Long id_store = null;
		if ( arg0.getParameter("id_store") != null && !arg0.getParameter("id_store").equals("") )
			id_store = ServletRequestUtils.getLongParameter(arg0,"id_store");
		logger.debug("id_store="+id_store);
		/*-------------------------------------------------------*/
		// Se obtiene una lista de objetos de dominio de tiendas
		StoreSearchCriteria dto = new StoreSearchCriteria();
		dto.setId_retail(id_retail);
		dto.setId_store(id_store);
		List<StoreDTO> list = retailService.getStoreByCriteria(dto);
		logger.info("list="+list.size());
		/*-------------------------------------------------------*/
		//Se obtiene la informacion del plan
      	Long id_plan = supplier.getId_plan();
      	Plan plan = supplierService.getPlanById(id_plan);
		// Se define criterio de busqueda para obtener listado de retailers
		RetailSearchCriteria dtto = new RetailSearchCriteria();
		dtto.setId_supplier(idsupplier);
		int cant = retailService.getCliQtyByCriteria(dtto);
		boolean advertencia = (cant >= plan.getCustomers() ? true : false);

				
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("useracegi", useracegi);
		model.put("useraccess",useraccess);
		model.put("nttdto", nttdto);
		model.put("advertencia",advertencia);
		
		model.put("list", list);
		model.put("retail", retail);
		model.put("id_store", id_store);
		model.put("id_retail", id_retail);
		model.put("supplier", supplier);
		
		model.put("ACTIVE", ACTIVE);
		model.put("NO_ACTIVE", NO_ACTIVE);
		
		return new ModelAndView(view, model);
	}

}
