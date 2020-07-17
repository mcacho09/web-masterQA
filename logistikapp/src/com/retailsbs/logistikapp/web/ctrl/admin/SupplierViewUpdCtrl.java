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

import com.retailsbs.logistikapp.supplier.domain.Supplier;
import com.retailsbs.logistikapp.supplier.dto.UpdSupplierDTO;
import com.retailsbs.logistikapp.supplier.exception.SupplierNotFoundException;
import com.retailsbs.logistikapp.supplier.service.SupplierService;
import com.retailsbs.logistikapp.user.domain.UserAcegi;
import com.retailsbs.logistikapp.user.dto.AddNotificationDTO;
import com.retailsbs.logistikapp.user.notification.service.UserNotificationService;

/**
 * Class controller para la vista para modificar proveedores
 * @author JSilva
 * @since 10-12-2014
 * @modified 24-12-2014 - JSilva
 * @modified 01-07-2015 - JSilva - Se crea una notificación de actualización para el mismo proveedor
 */
public class SupplierViewUpdCtrl extends SimpleFormController {

	protected final Log logger = LogFactory.getLog(getClass());

	private SupplierService supplierService;
	private UserNotificationService userNotificationService;
	private String ACTIVE;
	private String NO_ACTIVE;
	private Integer ORDERBY;

	public void setSupplierService(SupplierService supplierService) {
		this.supplierService = supplierService;
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
	public void setUserNotificationService(
			UserNotificationService userNotificationService) {
		this.userNotificationService = userNotificationService;
	}
	public SupplierViewUpdCtrl() {
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

	protected Object formBackingObject(HttpServletRequest request) throws ServletException, SupplierNotFoundException {
		logger.debug("---- FORMBACKINGOBJECT ----");
		/*-------------------------------------------------------*/
		// Se obtiene objeto de dominio del proveedor
		Long id_supplier = ServletRequestUtils.getLongParameter(request, "id");
		Supplier record = supplierService.getSupplierById(id_supplier); 
		/*-------------------------------------------------------*/
		// Se setea command con los datos del objeto de dominio
		UpdSupplierDTO command = new UpdSupplierDTO();
		command.setActive(record.getActive());
		command.setCode(record.getCode());
		command.setCreated(record.getCreated());
		command.setId_supplier(record.getId_supplier());
		command.setLogin(record.getLogin());
		command.setModified(record.getModified());
		command.setName(record.getName());
		command.setOrderby(record.getOrderby());
		return command;
	}

	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws ServletException, SupplierNotFoundException {
		logger.debug("---- ONSUBMIT ----");
		// Se obtiene el usuario del contexto
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserAcegi useracegi = (UserAcegi) auth.getPrincipal();
		
		// Se controlan los parametros active y orderby
		String active = ServletRequestUtils.getStringParameter(request, "active", NO_ACTIVE);
		Integer orderby = ServletRequestUtils.getIntParameter(request, "orderby", ORDERBY);
		
		// Se setea y se persiste el objeto para actualizar el objeto de dominio Supplier
		UpdSupplierDTO dto = (UpdSupplierDTO) command;
		dto.setModified(new Date());
		dto.setLogin(useracegi.getUserlogin());
		dto.setActive(active);
		dto.setOrderby(orderby);
		// Se persiste el objeto
		int rows = supplierService.updSupplier(dto);
		logger.debug("Supplier, id=" + dto.getId_supplier() + " actualizado " + rows + " ...ok!");
		
		// Se crea un objeto para insertar un nuevo objeto de dominio
		// para notificar que se modifica el proveedor 
		AddNotificationDTO dtn = new AddNotificationDTO();
		dtn.setCreated(new Date());
		dtn.setIcon("fa fa-truck");
		dtn.setId_user( useracegi.getId_user() );
		dtn.setMessage("Proveedor <span class='label label-success'>"+dto.getName()+"</span> actualizado");
		dtn.setPriority("1");
		dtn.setId_supplier( dto.getId_supplier() );
		// Se persiste el objeto de dominio de notification
		Long idn = userNotificationService.createNotification(dtn, "");
		logger.debug("Notification, id="+idn+" creada.. ok!");
		
		return new ModelAndView(getSuccessView());
	}

}
