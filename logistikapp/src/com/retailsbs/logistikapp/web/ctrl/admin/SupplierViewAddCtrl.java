package com.retailsbs.logistikapp.web.ctrl.admin;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContextHolder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.retailsbs.logistikapp.retail.dto.AddRetailCategoryDTO;
import com.retailsbs.logistikapp.retail.dto.AddStoreCategoryDTO;
import com.retailsbs.logistikapp.retail.service.RetailService;
import com.retailsbs.logistikapp.supplier.dto.AddSupplierDTO;
import com.retailsbs.logistikapp.supplier.service.SupplierService;
import com.retailsbs.logistikapp.user.domain.UserAcegi;
import com.retailsbs.logistikapp.user.dto.AddNotificationDTO;
import com.retailsbs.logistikapp.user.notification.service.UserNotificationService;
import com.retailsbs.logistikapp.user.service.UserService;

/**
 * Class controller para la vista de agregar proveedores
 * @author JSilva
 * @since 04-12-2014
 * @modified 24-12-2014 - JSilva - Estandarización estructura de controller
 * @modified 01-07-2015 - JSilva - Se crea una notificación para el mismo proveedor creado
 * @modified 16-07-2015 - JSilva - Por defecto se crea una categoria de comercio y local
 */
public class SupplierViewAddCtrl extends SimpleFormController {

	protected final Log logger = LogFactory.getLog( getClass() );

	String PARAM_DEFAULT_RETAIL_CATEGORY_CODE = "GRL";
	String PARAM_DEFAULT_RETAIL_CATEGORY_NAME = "General";
	String PARAM_DEFAULT_STORE_CATEGORY_CODE = "GRL";
	String PARAM_DEFAULT_STORE_CATEGORY_NAME = "General";
	
	private SupplierService supplierService;
	private UserService userService;
	private RetailService retailService;
	private UserNotificationService userNotificationService;
	private String ACTIVE;
	private String NO_ACTIVE;
	private Integer ORDERBY;

	public void setSupplierService(SupplierService supplierService) {
		this.supplierService = supplierService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public void setRetailService(RetailService retailService) {
		this.retailService = retailService;
	}
	
	public void setUserNotificationService(
			UserNotificationService userNotificationService) {
		this.userNotificationService = userNotificationService;
	}
	public void setACTIVE(String aCTIVE) {
		ACTIVE = aCTIVE;
	}
	public void setNO_ACTIVE(String nO_ACTIVE) {
		NO_ACTIVE = nO_ACTIVE;
	}
	public void setORDERBY(Integer oRDERBY) {
		ORDERBY = oRDERBY;
	}

	public SupplierViewAddCtrl() {
		setSessionForm(true);
		setBindOnNewForm(true);
	}

	protected Map<String, Object> referenceData(HttpServletRequest arg0, Object arg1, Errors arg2) throws Exception {
		logger.debug("---- REFERENCEDATA ----");
		/*-------------------------------------------------------*/
		logger.debug("----- USER CONTEXT -----");
		// Se obtiene el contexto del usuario
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserAcegi useracegi = (UserAcegi) auth.getPrincipal();
		/*-------------------------------------------------------*/
		// Se obtiene la accion
		String accion = ServletRequestUtils.getStringParameter(arg0, "accion", null);
		/*-------------------------------------------------------*/
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("useracegi", useracegi);
		model.put("accion", accion);
		model.put("ACTIVE", ACTIVE);
		return model;
	}

	protected Object formBackingObject(HttpServletRequest request) throws ServletException {
		logger.debug("---- FORMBACKINGOBJECT ----");
		AddSupplierDTO command = new AddSupplierDTO();
		return command;
	}

	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws ServletException {
		logger.debug("---- ONSUBMIT ----");
		// Se obtiene el usuario del contexto
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserAcegi useracegi = (UserAcegi) auth.getPrincipal();

		// Se controlan los parametros active y orderby
		String active = ServletRequestUtils.getStringParameter(request, "active", NO_ACTIVE);
		Integer orderby = ServletRequestUtils.getIntParameter(request, "orderby", ORDERBY);
		
		// Se setea objeto para agregar un nuevo objeto de dominio Supplier
		AddSupplierDTO dto = (AddSupplierDTO) command;
		dto.setCreated(new Date());
		dto.setLogin(useracegi.getUserlogin());
		dto.setActive(active);
		dto.setOrderby(orderby);
		// Se persiste el objeto
		Long id = supplierService.addSupplier(dto);
		logger.debug("Supplier, id=" + id + " creado OK!");

		// Se crea un objeto para insertar una alerta del nuevo proveedor 
		AddNotificationDTO dtn = new AddNotificationDTO();
		dtn.setCreated(new Date());
		dtn.setIcon("fa fa-truck");
		dtn.setId_user( useracegi.getId_user() );
		dtn.setMessage("Proveedor <span class='label label-primary'>"+dto.getName()+"</span> creado");
		dtn.setPriority("1");
		dtn.setId_supplier(id);
		// Se persiste la notificacion
		Long idn = userService.addNotification(dtn);
		logger.debug("Notification, id="+idn);

		/*-------------------------------------------------------*/
		// Por defecto se crea una categoria de comercio
		AddRetailCategoryDTO dtrc = new AddRetailCategoryDTO();
		dtrc.setActive( ACTIVE );
		dtrc.setCode( PARAM_DEFAULT_RETAIL_CATEGORY_CODE );
		dtrc.setCreated( new Date() );
		dtrc.setId_supplier( id );
		dtrc.setLogin( useracegi.getUserlogin() );
		dtrc.setName( PARAM_DEFAULT_RETAIL_CATEGORY_NAME );
		dtrc.setOrderby( ORDERBY );
		// Se persiste la categoria de comercio
		Long idrc = retailService.addRetailCategory(dtrc);
		logger.debug( "RetailCategory, id=" + idrc + " creado.. ok!" );
		
		// Se agrega una notificación
		dtn = new AddNotificationDTO();
		dtn.setCreated( new Date() );
		dtn.setIcon( "fa fa-tags" );
		dtn.setId_user( useracegi.getId_user() );
		dtn.setMessage( "Categor&iacute;a cliente <span class='label label-primary'>" + dtrc.getName() + "</span> creada" );
		dtn.setPriority( "1" );
		dtn.setId_supplier( id );
		// Se persiste la notificacion
		idn = userService.addNotification( dtn );
		logger.debug("Notification, id=" + idn );
		
		/*-------------------------------------------------------*/
		// Por defecto se crea una categoria de tienda
		AddStoreCategoryDTO dtsc = new AddStoreCategoryDTO();
		dtsc.setActive( ACTIVE );
		dtsc.setCode( PARAM_DEFAULT_STORE_CATEGORY_CODE );
		dtsc.setCreated( new Date() );
		dtsc.setId_supplier( id );
		dtsc.setLogin( useracegi.getUserlogin() );
		dtsc.setName( PARAM_DEFAULT_STORE_CATEGORY_NAME );
		dtsc.setOrderby( ORDERBY );
		// Se persiste la categoria de local
		Long id_sc = retailService.addStoreCategory(dtsc);
		logger.debug( "StoreCategory, id=" + id_sc + " creado.. ok!" );
		
		// Se agrega una notificación
		dtn = new AddNotificationDTO();
		dtn.setCreated( new Date() );
		dtn.setIcon( "fa fa-tags" );
		dtn.setId_user( useracegi.getId_user() );
		dtn.setMessage( "Categor&iacute;a tienda <span class='label label-primary'>" + dtsc.getName() + "</span> creada" );
		dtn.setPriority( "1" );
		dtn.setId_supplier( id );
		// Se persiste la notificacion
		idn = userNotificationService.createNotification(dtn, "");
		logger.debug("Notification, id=" + idn );
		
		return new ModelAndView(getSuccessView());
	}

}
