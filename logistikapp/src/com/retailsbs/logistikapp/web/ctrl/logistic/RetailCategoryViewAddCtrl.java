package com.retailsbs.logistikapp.web.ctrl.logistic;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.retailsbs.logistikapp.retail.service.RetailService;
import com.retailsbs.logistikapp.user.domain.Access;
import com.retailsbs.logistikapp.user.domain.Quantities;
import com.retailsbs.logistikapp.user.domain.UserAcegi;
import com.retailsbs.logistikapp.user.dto.AccessTinyDTO;
import com.retailsbs.logistikapp.user.dto.AddNotificationDTO;
import com.retailsbs.logistikapp.user.dto.HeaderNotificationDTO;
import com.retailsbs.logistikapp.user.dto.NotificationTodaySearchCriteria;
import com.retailsbs.logistikapp.user.notification.service.UserNotificationService;
import com.retailsbs.logistikapp.user.service.UserService;

/**
 * Class controller de la vista de agregar retail category
 * @author JORGE
 * @since 29-01-2015
 * @modified 16-02-2015 - JORGE - Integraci�n para contador y alertas en barra navegaci�n
 */
public class RetailCategoryViewAddCtrl extends SimpleFormController {

	protected final Log logger = LogFactory.getLog(getClass());

	private RetailService retailService;
	private UserService userService;
	private UserNotificationService userNotificationService;
	private String ACTIVE;
	private String NO_ACTIVE;
	private String cancelView;
	
	public String getCancelView() {
		return cancelView;
	}
	public void setCancelView(String cancelView) {
		this.cancelView = cancelView;
	}
	public void setUserNotificationService(
			UserNotificationService userNotificationService) {
		this.userNotificationService = userNotificationService;
	}

	private Integer ORDERBY;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public void setRetailService(RetailService retailService) {
		this.retailService = retailService;
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

	public RetailCategoryViewAddCtrl() {
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
		/*-------------------------------------------------------*/
		// TODO se debe cambiar por el proveedor activo y s�lo de los que tiene acceso
		// Se obtiene la lista de categorias de productos por proveedor
		Long idsupplier = null;
		if( arg0.getParameter("idsupplier") != null && !arg0.getParameter("idsupplier").equals("") )
			idsupplier = ServletRequestUtils.getLongParameter(arg0, "idsupplier");
		else {
			// Por defecto toma el primer elemento
			// que es el caso cuando se ingresa por primera vez a la pantalla
			idsupplier = ( accesssupplier.get(0) ).getId();
		}
		logger.debug("idsupplier="+ idsupplier);
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
		
		String fromaccion = arg0.getParameter("fromaccion");
		Long id_retail = 0L;
		
		if (arg0.getParameter("id_retail") != null && !arg0.getParameter("id_retail").equals(""))
			id_retail = ServletRequestUtils.getLongParameter(arg0, "id_retail");
		
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("useracegi", useracegi);
		model.put("accesssupplier", accesssupplier);
		model.put("nttdto", nttdto);
		model.put("idsupplier", idsupplier);		
		model.put("accion", accion);
		model.put("ACTIVE", ACTIVE);
		model.put("cancelView",cancelView);
		model.put("fromaccion", fromaccion);
		model.put("id_retail", id_retail);
		model.put("useraccess", useraccess);
		
		return model;
	}

	protected Object formBackingObject(HttpServletRequest request) throws ServletException {
		logger.debug("---- FORMBACKINGOBJECT ----");
		AddRetailCategoryDTO command = new AddRetailCategoryDTO();
		
		//=================================================================================
		HttpSession sess = request.getSession();
		String name = request.getParameter("retailname");
		String code = request.getParameter("retailcode");
		String id_state = request.getParameter("retailid_state");					
		String orderby = request.getParameter("retailorderby");		
		String active = request.getParameter("retailactive");		
		
		sess.setAttribute("RTADD", "rt");
		sess.setAttribute("rt_name", name);
		sess.setAttribute("rt_code", code);
		sess.setAttribute("rt_id_state", id_state);
		sess.setAttribute("rt_orderby", orderby);
		sess.setAttribute("rt_active", active);		
		
		logger.debug("DATOS DE LA SESION --->  "+"\n"+ name + "\n" + code + "\n" + id_state + "\n" + orderby + "\n" + active);
		
 //=================================================================================
		

		return command;
	}

	protected ModelAndView onSubmit(HttpServletRequest request,HttpServletResponse response, Object command, BindException errors) throws Exception {
		logger.debug("---- ONSUBMIT ----");
		/*-------------------------------------------------------*/
		logger.debug("----- USER CONTEXT -----");
		// Se obtiene el contexto del usuario
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserAcegi useracegi = (UserAcegi) auth.getPrincipal();
		/*-------------------------------------------------------*/
		logger.debug("----- USER ACCESS -----");
		List<AccessTinyDTO> accesssupplier = userService.getAccessSupplierByIdUser(useracegi.getId_user());
		logger.debug("accesssupplier="+accesssupplier.size());
		Long idsupplier = ( accesssupplier.get(0) ).getId();
		logger.debug("idsupplier="+idsupplier);
		/*-------------------------------------------------------*/
		logger.debug("----- HEADER/NOTIFICATION -----");
		HeaderNotificationDTO nttdto = userService.getHeaderNotificationByIdUser(useracegi.getId_user());
		logger.debug("alert="+nttdto.getAlert_qty()+" | message="+nttdto.getMessage_qty()+" | todo="+nttdto.getTodo_qty());
		/*-------------------------------------------------------*/
		
		// Se controlan los parametros active y orderby
		String active = ServletRequestUtils.getStringParameter(request, "active", NO_ACTIVE);
		Integer orderby = ServletRequestUtils.getIntParameter(request, "orderby", ORDERBY);
		
		// Se setea el dto para agregar un nuevo objeto de dominio RetailCategory
		AddRetailCategoryDTO dto = (AddRetailCategoryDTO) command;

		dto.setActive(active);
		dto.setCreated(new Date());
		dto.setLogin( useracegi.getUserlogin() );
		dto.setOrderby(orderby);		
		dto.setId_supplier(idsupplier);
		// Se persiste el objeto
		Long id = retailService.addRetailCategory(dto);
		logger.debug("RetailCategory, id=" + id + " creado... OK");
		HttpSession sess = request.getSession();
		sess.setAttribute("rt_id_retail_category", id.toString());
	
		/*-------------------------------------------------------*/
		// Se setea el dto para agregar un nuevo objeto de dominio Notification
		AddNotificationDTO dtn = new AddNotificationDTO();
		dtn.setCreated(new Date());
		dtn.setIcon("fa fa-institution");
		dtn.setId_user(useracegi.getId_user());
		dtn.setMessage("Nueva categor&iacute;a de cliente <span class='label label-success'>" + dto.getName() + "</span>");
		dtn.setPriority("1");
		dtn.setId_supplier(idsupplier);		
		dtn.setLink("alertlist.htm");
		// Se persiste el objeto 
		Long idn = userNotificationService.createNotification(dtn, "023");
		logger.debug("Notification, id="+idn);
		/*-------------------------------------------------------*/
		String fromaccion = "";
		if (request.getParameter("fromacciontext")!= null){
			fromaccion = request.getParameter("fromacciontext");
		}
		String id_retail = request.getParameter("id_retailtext");
		if (fromaccion.equals("add"))
			return new ModelAndView(getSuccessView()+"?accion=" +fromaccion);
		else if (fromaccion.equals("upd")) 
			return new ModelAndView("redirect:retailupd.htm?id="+id_retail+"&accion=upd");
		
		return new ModelAndView(getSuccessView());
	}

}
