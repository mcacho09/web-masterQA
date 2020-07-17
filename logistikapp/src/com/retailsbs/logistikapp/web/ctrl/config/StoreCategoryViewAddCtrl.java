package com.retailsbs.logistikapp.web.ctrl.config;

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

import com.retailsbs.logistikapp.retail.dto.AddStoreCategoryDTO;
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
 * Class controller de la vista de agregar un categoria de local
 * @author juan carlos
 * @since 29-01-2015
 * @modified 16-02-2015 - JORGE - Integracion para contador y alertas en barra navegacion
 * @modified 29-06-2015 - SERGIO - Se agrego cancelView para retorno
 * @modified 14/04/2016 - JSI - Fix
 */
public class StoreCategoryViewAddCtrl extends SimpleFormController {

	protected final Log logger = LogFactory.getLog(getClass());

	private UserService userService;
	private RetailService retailService;
	private UserNotificationService userNotificationService;
	private String ACTIVE;
	private String NO_ACTIVE;
	private Integer ORDERBY;
	private String cancelView;
	
	private Long id_retail;
	private Long id_store;

	public String getCancelView() {
		return cancelView;
	}
	public void setCancelView(String cancelView) {
		this.cancelView = cancelView;
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
	
	public StoreCategoryViewAddCtrl() {
		setSessionForm(true);
		setBindOnNewForm(true);
	}

	protected Object formBackingObject(HttpServletRequest request) throws ServletException {
		logger.debug("---- FORMBACKINGOBJECT ----");
		AddStoreCategoryDTO command = new AddStoreCategoryDTO();
		
		return command;
	}
	
	protected Map<String, Object> referenceData(HttpServletRequest arg0, Object arg1, Errors arg2) throws Exception{
		logger.debug("---- REFERENCEDATA ----");
		/*-------------------------------------------------------*/
		logger.debug("----- USER CONTEXT -----");
		// Se obtiene el contexto del usuario
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserAcegi useracegi = (UserAcegi) auth.getPrincipal();
		/*-------------------------------------------------------*/
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
		// Se obtiene la accion
		String accion = ServletRequestUtils.getStringParameter(arg0, "accion", null);
		logger.debug("STORECATEGORYADD ==> accion = "+accion);
		/*-------------------------------------------------------*/
		
		id_retail = ServletRequestUtils.getLongParameter(arg0, "pidr");
		logger.debug("STORECATEGORYADD ==> id_retail = "+id_retail);
		
		id_store = ServletRequestUtils.getLongParameter(arg0, "pids");
		logger.debug("STORECATEGORYADD ==> id_store = "+id_store);

		logger.debug("STORECATEGORYADD ==> accion = "+accion);
		logger.debug("STORECATEGORYADD ==> cancelView = "+cancelView);
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("useracegi", useracegi);
		model.put("useraccess",useraccess);
		model.put("accesssupplier", accesssupplier);
		model.put("nttdto", nttdto);
		model.put("idsupplier", idsupplier);
		model.put("id_retail", id_retail);
		model.put("id_store", id_store);
		
		model.put("accion", accion);
		model.put("cancelView",cancelView);
		
		model.put("ACTIVE", ACTIVE);

		/*-------------------------------------------------------*/
		// Se obtienen parametros, requeridos para volver a la creacion/modificacion de clientes
		String from = ServletRequestUtils.getStringParameter(arg0, "from", null);
		
		if ( from != null ) {
			String pactive = ServletRequestUtils.getStringParameter(arg0, "pactive");
			String paddress1 = ServletRequestUtils.getStringParameter(arg0, "paddress1");
			String paddress2 = ServletRequestUtils.getStringParameter(arg0, "paddress2");
			String pcode = ServletRequestUtils.getStringParameter(arg0, "pcode");
			String pemail = ServletRequestUtils.getStringParameter(arg0, "pemail");
			Long pid_city = ServletRequestUtils.getLongParameter(arg0, "pid_city");
			Long pid_state = ServletRequestUtils.getLongParameter(arg0, "pid_state");
			Long pid_store_category = ServletRequestUtils.getLongParameter(arg0, "pid_store_category");
			String platitude = ServletRequestUtils.getStringParameter(arg0, "platitude");
			String plongitude = ServletRequestUtils.getStringParameter(arg0, "plongitude");
			String pname = ServletRequestUtils.getStringParameter(arg0, "pname");
			String porderby = ServletRequestUtils.getStringParameter(arg0, "porderby");
			String pphone = ServletRequestUtils.getStringParameter(arg0, "pphone");
			String ppostal = ServletRequestUtils.getStringParameter(arg0, "ppostal");
			String pshelf = ServletRequestUtils.getStringParameter(arg0, "pshelf");
			String pids = ServletRequestUtils.getStringParameter(arg0, "pids");
			String pidr = ServletRequestUtils.getStringParameter(arg0, "pidr");
			
			HttpSession sess = arg0.getSession();
			sess.setAttribute("SESSIONSTOCAT", "SESSIONSTOCAT");
			sess.setAttribute("active", pactive);
			sess.setAttribute("address1", paddress1);
			sess.setAttribute("address2", paddress2);
			sess.setAttribute("code", pcode);
			sess.setAttribute("email", pemail);
			sess.setAttribute("id_city", pid_city);
			sess.setAttribute("id_state", pid_state);
			sess.setAttribute("id_store_category", pid_store_category);
			sess.setAttribute("latitude", platitude);
			sess.setAttribute("longitude", plongitude);
			sess.setAttribute("name", pname);
			sess.setAttribute("orderby", porderby);
			sess.setAttribute("phone", pphone);
			sess.setAttribute("postal", ppostal);
			sess.setAttribute("shelf", pshelf);
			sess.setAttribute("ids", pids);
			sess.setAttribute("idr", pidr);
		}
		
		return model;
	}

	protected ModelAndView onSubmit(HttpServletRequest request,HttpServletResponse response, Object command, BindException errors) throws ServletException, UserNotFoundException, AccessNotFoundException {
		logger.debug("---- ONSUBMIT ----");
		// Se obtiene el contexto del usuario
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserAcegi useracegi = (UserAcegi) auth.getPrincipal();

		Long id_sup = userService.getAccessByIdUser(useracegi.getId_user()).get(0).getId_supplier();
		
		// Se controlan los parametros active y orderby
		String active = ServletRequestUtils.getStringParameter(request, "active", NO_ACTIVE);
		Integer orderby = ServletRequestUtils.getIntParameter(request, "orderby", ORDERBY);

		// Se setea el dto para agregar un nuevo objeto de dominio StoreCategory
		AddStoreCategoryDTO dto = (AddStoreCategoryDTO) command;
		String name = dto.getName();
		name = dto.getName();
		name = name.replaceAll("\"", "");
		name = name.replaceAll("'", "");
		dto.setName(name);
		dto.setActive(active);
		dto.setCreated(new Date());
		dto.setLogin( useracegi.getUserlogin() );
		dto.setOrderby(orderby);
		dto.setId_supplier(id_sup);
		// Se persiste el objeto
		Long id = retailService.addStoreCategory(dto);
		logger.debug("STOREUPD ==> StoreCategory, id=" + id + " creado... OK");
		
		//actualizamos id_category_store
		HttpSession sess = request.getSession();
		String SESSION = (String)sess.getAttribute("SESSIONSTOCAT");
		logger.debug("STOREUPD ==> SESSION=" + SESSION);
		if ( SESSION!=null && SESSION.equals("SESSIONSTOCAT") )
			sess.setAttribute("id_store_category", id);

		/*-------------------------------------------------------*/
		// Se genera una notificaci�n
		AddNotificationDTO dtn = new AddNotificationDTO();
		dtn.setCreated(new Date());
		dtn.setIcon("fa fa-tags");
		dtn.setId_user(useracegi.getId_user());
		dtn.setMessage("Nueva categor&iacute;a <span class='label label-primary'>" + dto.getName() + "</span>");
		dtn.setPriority("1");
		dtn.setId_supplier(id_sup);
		dtn.setLink("cfgstorecatlist.htm");
		// Se persiste la notificacion
		Long idn = userNotificationService.createNotification(dtn, "003");
		logger.debug("Notification, id="+idn);
		/*-------------------------------------------------------*/

		String cad="";
		String accion = request.getParameter("paccion");
		logger.debug("STOREUPD ==> accion = "+accion);
		
		if ( accion != null && accion.equals("add") )
			cad="idr="+id_retail+"&accion="+accion;
		else if ( accion != null && accion.equals("upd") )
			cad="ids="+id_store+"&accion="+accion;
		
		logger.debug("STOREUPD ==> SUCCESSVIEW = "+getSuccessView()+cad);
		
		return new ModelAndView(getSuccessView()+cad);
	}

}
