package com.retailsbs.logistikapp.web.ctrl.logistic;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
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

import com.retailsbs.logistikapp.retail.domain.Retail;
import com.retailsbs.logistikapp.retail.domain.RetailDept;
import com.retailsbs.logistikapp.retail.dto.UpdRetailDeptDTO;
import com.retailsbs.logistikapp.retail.exception.RetailDeptNotFoundException;
import com.retailsbs.logistikapp.retail.exception.RetailNotFoundException;
import com.retailsbs.logistikapp.retail.service.RetailService;
import com.retailsbs.logistikapp.user.domain.Access;
import com.retailsbs.logistikapp.user.domain.Quantities;
import com.retailsbs.logistikapp.user.domain.UserAcegi;
import com.retailsbs.logistikapp.user.dto.AccessTinyDTO;
import com.retailsbs.logistikapp.user.dto.AddNotificationDTO;
import com.retailsbs.logistikapp.user.dto.NotificationTodaySearchCriteria;
import com.retailsbs.logistikapp.user.exception.AccessNotFoundException;
import com.retailsbs.logistikapp.user.exception.UserNotFoundException;
import com.retailsbs.logistikapp.user.notification.service.UserNotificationService;
import com.retailsbs.logistikapp.user.service.UserService;

/**
 * Class controller de la vista de modificar retaildept (departamentos)
 * @author Juan Carlos Ramos Campos
 * @since 27-03-2015
 */
public class RetailDeptViewUpdCtrl extends SimpleFormController {
	
	protected final Log logger = LogFactory.getLog(getClass());

	private UserService userService;
	private RetailService retailService;
	private UserNotificationService userNotificationService;
	private Integer ORDERBY;
	
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
	public void setORDERBY(Integer oRDERBY) {
		ORDERBY = oRDERBY;
	}

	public RetailDeptViewUpdCtrl() {
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
		Long idretail = null;
		if(arg0.getParameter("id_retail")!= null && !arg0.getParameter("id_retail").equals(""))
			idretail = ServletRequestUtils.getLongParameter(arg0, "id_retail");
		logger.debug("idretail ="+idretail);
		Retail retail = retailService.getRetailById(idretail);
		logger.debug("retail nombre ="+retail.getName()+" id="+retail.getId_retail());
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
		// Se obtiene la accion
		String accion = ServletRequestUtils.getStringParameter(arg0, "accion", null);
		/*-------------------------------------------------------*/
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("useracegi", useracegi);
		model.put("accesssupplier", accesssupplier);
		model.put("nttdto", nttdto);
		
		model.put("accion", accion);
		model.put("retail", retail);

		model.put("useraccess", useraccess);
		return model;
	}

	protected Object formBackingObject(HttpServletRequest request) throws ServletException, RetailNotFoundException, RetailDeptNotFoundException{
		logger.debug("---- FORMBACKINGOBJECT ----");
		// Se obtiene objeto de dominio del retail
		Long id_retail_dept = ServletRequestUtils.getLongParameter(request, "id");
		RetailDept retaildpet  = retailService.getRetailDeptById(id_retail_dept);

		// Se setea el objeto command con los datos
		// del objeto de dominio
		UpdRetailDeptDTO command = new UpdRetailDeptDTO();
		command.setId_retail_dept(id_retail_dept);
		command.setId_retail(retaildpet.getId_retail());
		command.setContact(retaildpet.getContact());
		command.setCreated(retaildpet.getCreated());
		command.setEmail(retaildpet.getEmail());
		command.setLogin(retaildpet.getLogin());
		command.setModified(retaildpet.getModified());
		command.setName(retaildpet.getName());
		command.setOrderby(retaildpet.getOrderby());
		command.setPhone(retaildpet.getPhone());
		
		return command;
	}

	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws ServletException, RetailNotFoundException, RetailDeptNotFoundException, UserNotFoundException, AccessNotFoundException{
		logger.debug("---- ONSUBMIT ----");
		// Se obtiene el contexto del usuario
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserAcegi useracegi = (UserAcegi) auth.getPrincipal();
 
		// Se controlan los parametros active y orderby
		Integer orderby = ServletRequestUtils.getIntParameter(request, "orderby", ORDERBY);

		// Se crea un dto para actualizar retail
		UpdRetailDeptDTO dto = (UpdRetailDeptDTO) command;
		dto.setModified(new Date());
		dto.setOrderby(orderby);
		dto.setLogin( useracegi.getUserlogin() );
		dto.setOrderby(orderby);
		// Se persiste el objeto
		int rows = retailService.updRetailDept(dto);
		logger.debug("RetailDept id=" + dto.getId_retail_dept() + " actualizado " + rows + "...ok!");
		
		//Se obtiene el id_supplier del usuario conectado
		Long id_supplier = userService.getAccessByIdUser(useracegi.getId_user()).get(0).getId_supplier();
		logger.debug("id_supplier = "+id_supplier);
		// Se genera una notificacion
		AddNotificationDTO dtn = new AddNotificationDTO();
		dtn.setCreated(new Date());
		dtn.setIcon("fa fa-building");
		dtn.setId_user(useracegi.getId_user());
		dtn.setMessage("Departamento <span class='label label-success'>"+dto.getName()+"</span> actualizado");
		dtn.setPriority("1");
		dtn.setId_supplier(id_supplier);
		dtn.setLink("alertlist.htm");
		// Se persiste la notificacion
		Long idn = userNotificationService.createNotification(dtn, "");
		logger.debug("Notification, id="+idn);
		/*-------------------------------------------------------*/

		return new ModelAndView(getSuccessView()+"?id="+dto.getId_retail());
	}

}
