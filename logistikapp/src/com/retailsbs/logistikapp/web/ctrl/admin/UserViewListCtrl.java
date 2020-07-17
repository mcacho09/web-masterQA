package com.retailsbs.logistikapp.web.ctrl.admin;

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

import com.retailsbs.logistikapp.supplier.domain.Supplier;
import com.retailsbs.logistikapp.supplier.service.SupplierService;
import com.retailsbs.logistikapp.user.domain.UserAcegi;
import com.retailsbs.logistikapp.user.dto.AdminUserDTO;
import com.retailsbs.logistikapp.user.dto.AdminUserSearchCriteria;
import com.retailsbs.logistikapp.user.service.UserService;

/**
 * Controller para la vista de administración de usuarios
 * @author JORGE
 * @since 26-06-2015
 */
public class UserViewListCtrl implements Controller {
	
	protected final Log logger = LogFactory.getLog(getClass());

	private UserService userService;
	private SupplierService supplierService;
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
	public void setACTIVE(String aCTIVE) {
		ACTIVE = aCTIVE;
	}
	public void setNO_ACTIVE(String nO_ACTIVE) {
		NO_ACTIVE = nO_ACTIVE;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		logger.debug("---- HANDLE REQUEST ----");
		/*-------------------------------------------------------*/
		// Se obtiene el contexto del usuario
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserAcegi useracegi = (UserAcegi) auth.getPrincipal();
		
		//Se obtienen todos los proveedores
		List<Supplier> suppliers = supplierService.getAllSupplier();
		//Se obtiene el id del proveedor en caso de ser enviado
		Long id_supplier = ServletRequestUtils.getLongParameter(arg0, "ids", 0l);
		
		/*-------------------------------------------------------*/
		// Se define el criterio de busqueda para obtener una lista de usuarios 
		AdminUserSearchCriteria dto = new AdminUserSearchCriteria();
		if (id_supplier == 0) {
			if (suppliers != null & suppliers.size() > 0) {
				dto.setId_supplier(suppliers.get(0).getId_supplier());
				id_supplier = suppliers.get(0).getId_supplier();
			}
		} else {
			dto.setId_supplier(id_supplier);
		}
		
		// Se obtiene la lista de usuarios
		List<AdminUserDTO> list = userService.getAdminUserListByCriteria(dto);
		logger.debug("list= "+list.size());
		/*-------------------------------------------------------*/

		Map<String,Object> model = new HashMap<String,Object>();
		model.put("useracegi", useracegi);
		
		model.put("list", list);

		model.put("ACTIVE", ACTIVE);
		model.put("NO_ACTIVE", NO_ACTIVE);
		model.put("ids", id_supplier);
		model.put("suppliers", suppliers);
		
		return new ModelAndView(view, model);
	}
	
}
