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

import com.retailsbs.logistikapp.retail.domain.RetailCategory;
import com.retailsbs.logistikapp.retail.dto.UpdRetailCategoryDTO;
import com.retailsbs.logistikapp.retail.exception.RetailCategoryNotFoundException;
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
 * Class controller de la vista para modificar retail category
 * @author JORGE
 * @since 29-01-2015
 * @modified 16-02-2015 - JORGE - Integración para contador y alertas en barra navegación
 * @modified 23-06-2015 - JORGE - Se agrega id_supplier al insertar una notificacion
 */
public class RetailCategoryViewUpdCtrl extends SimpleFormController {
	
	protected final Log logger = LogFactory.getLog(getClass());

	private RetailService retailService;
	private UserService userService;
	private UserNotificationService userNotificationService;
	private String ACTIVE;
	private String NO_ACTIVE;
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
	public void setACTIVE(String aCTIVE) {
		ACTIVE = aCTIVE;
	}
	public void setNO_ACTIVE(String nO_ACTIVE) {
		NO_ACTIVE = nO_ACTIVE;
	}
	public void setORDERBY(Integer oRDERBY) {
		ORDERBY = oRDERBY;
	}

	public RetailCategoryViewUpdCtrl() {
		setSessionForm(true);
		setBindOnNewForm(true);
	}
	
	protected Map<String, Object> referenceData(HttpServletRequest arg0, Object arg1, Errors arg2) throws Exception{
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
		Long idsupplier = ( accesssupplier.get(0) ).getId();
		logger.debug("idsupplier="+idsupplier);
		/*-------------------------------------------------------*/
		NotificationTodaySearchCriteria dtoqty = new NotificationTodaySearchCriteria();
		dtoqty.setProfile(useracegi.getProfile());
		dtoqty.setFecha(new Date());
		dtoqty.setId_supplier(useraccess.getId_supplier());
		dtoqty.setId_user(useracegi.getId_user());
		logger.debug("----- HEADER/NOTIFICATION -----");
		Quantities nttdto = userService.getQtyAlertAndMessagesByIdUser(dtoqty);
		//HeaderNotificationDTO nttdto = userService.getHeaderNotificationByIdUser(useracegi.getId_user());logger.debug("alert="+nttdto.getAlert_qty()+" | message="+nttdto.getMessage_qty()+" | todo="+nttdto.getTodo_qty());
		/*-------------------------------------------------------*/
		// Se obtiene la accion
		String accion = ServletRequestUtils.getStringParameter(arg0, "accion", null);
		/*-------------------------------------------------------*/
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("useracegi", useracegi);
		model.put("accesssupplier", accesssupplier);
		model.put("nttdto", nttdto);
		model.put("idsupplier", idsupplier);

		model.put("useraccess", useraccess);
		model.put("accion", accion);
		model.put("ACTIVE", ACTIVE);
		return model;
	}

	protected Object formBackingObject(HttpServletRequest request) throws ServletException, RetailCategoryNotFoundException {
		logger.debug("---- FORMBACKINGOBJECT ----");
		// Se obtiene objeto de dominio del retail category
		Long id_retail_category = ServletRequestUtils.getLongParameter(request, "id");
		RetailCategory category  = retailService.getRetailCategoryById(id_retail_category);
		
		// Se setea el objeto command con los datos
		// del objeto de dominio
		UpdRetailCategoryDTO command = new UpdRetailCategoryDTO();
		command.setActive(category.getActive());
		command.setCode(category.getCode());
		command.setCreated(category.getCreated());
		command.setId_retail_category(category.getId_retail_category());
		command.setId_supplier(category.getId_supplier());
		command.setLogin(category.getLogin());
		command.setModified(category.getModified());
		command.setName(category.getName());
		command.setOrderby(category.getOrderby());
		return command;
	}

	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws ServletException, RetailCategoryNotFoundException, AccessNotFoundException, UserNotFoundException{
		logger.debug("---- ONSUBMIT ----");
		/*-------------------------------------------------------*/
		logger.debug("----- USER CONTEXT -----");
		// Se obtiene el contexto del usuario
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserAcegi useracegi = (UserAcegi) auth.getPrincipal();
		
		Long id_sup = userService.getAccessByIdUser(useracegi.getId_user()).get(0).getId_supplier();
		logger.debug("id_supplier = "+id_sup);
		/*-------------------------------------------------------*/
		logger.debug("----- USER ACCESS -----");
		List<AccessTinyDTO> accesssupplier = userService.getAccessSupplierByIdUser(useracegi.getId_user());
		logger.debug("accesssupplier="+accesssupplier.size());
		Long idsupplier = ( accesssupplier.get(0) ).getId();
		logger.debug("idsupplier="+idsupplier);
		/*-------------------------------------------------------*/
		
		// Se controlan los parametros active y orderby
		String active = ServletRequestUtils.getStringParameter(request, "active", NO_ACTIVE);
		Integer orderby = ServletRequestUtils.getIntParameter(request, "orderby", ORDERBY);

		// Se crea un dto para actualizar el objeto de dominio RetailCategory
		UpdRetailCategoryDTO dto = (UpdRetailCategoryDTO) command;
		dto.setActive(active);
		dto.setLogin( useracegi.getUserlogin() );
		dto.setModified(new Date());
		dto.setOrderby(orderby);
		
		// Se persiste el objeto
		int rows = retailService.updRetailCategory(dto);
		logger.debug("RetailCategory id=" + dto.getId_retail_category() + " actualizado " + rows + "...ok!");
		
		/*-------------------------------------------------------*/
		// Se setea de dto para agregar un objeto de dominio Notification
		AddNotificationDTO dtn = new AddNotificationDTO();
		dtn.setCreated(new Date());
		dtn.setIcon("fa fa-institution");
		dtn.setId_user(useracegi.getId_user());
		dtn.setMessage("Categor&iacute;a de comercio <span class='label label-success'>" + dto.getName() + "</span> actualizada");
		dtn.setPriority("1");
		dtn.setProfile(null);
		dtn.setId_supplier(id_sup);
		dtn.setLink("alertlist.htm");
		// Se persiste la notificacion
		Long idn = userNotificationService.createNotification(dtn, "003");
		logger.debug("Notification, id="+idn);
		/*-------------------------------------------------------*/
		
		return new ModelAndView(getSuccessView());
	}

}
