package com.retailsbs.logistikapp.web.ctrl.customer;

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

import com.retailsbs.logistikapp.retail.domain.City;
import com.retailsbs.logistikapp.retail.domain.Retail;
import com.retailsbs.logistikapp.retail.domain.State;
import com.retailsbs.logistikapp.retail.domain.StoreCategory;
import com.retailsbs.logistikapp.retail.dto.AddStoreDTO;
import com.retailsbs.logistikapp.retail.dto.StoreCategoryActiveSearchCriteria;
import com.retailsbs.logistikapp.retail.service.RetailService;
import com.retailsbs.logistikapp.supplier.domain.Supplier;
import com.retailsbs.logistikapp.supplier.service.SupplierService;
import com.retailsbs.logistikapp.user.domain.Access;
import com.retailsbs.logistikapp.user.domain.Quantities;
import com.retailsbs.logistikapp.user.domain.UserAcegi;
import com.retailsbs.logistikapp.user.dto.AddNotificationDTO;
import com.retailsbs.logistikapp.user.dto.NotificationTodaySearchCriteria;
import com.retailsbs.logistikapp.user.exception.AccessNotFoundException;
import com.retailsbs.logistikapp.user.exception.UserNotFoundException;
import com.retailsbs.logistikapp.user.notification.service.UserNotificationService;
import com.retailsbs.logistikapp.user.service.UserService;

/**
 * Controlador de vista para agregar locales (store)
 * @author Juan Carlos Ramos Campos
 * @since 25-11-2014
 * @modified 08-01-2015 - JORGE - Estandarizacion estructura de controller
 * @modified 24-06-2015 - SERGIO - Categorias por id_suplier
 */
public class StoreViewAddCtrl extends SimpleFormController {

	protected final Log logger = LogFactory.getLog(getClass());

	private UserService userService;
	private RetailService retailService;
	private SupplierService supplierService;
	private UserNotificationService userNotificationService;
	private String ACTIVE;
	private String NO_ACTIVE;
	private Integer ORDERBY;
	private String SHELF;
	private String NO_SHELF;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public void setRetailService(RetailService retailService) {
		this.retailService = retailService;
	}
	public void setSupplierService(SupplierService supplierService) {
		this.supplierService = supplierService;
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
	public void setSHELF(String sHELF) {
		SHELF = sHELF;
	}
	public void setNO_SHELF(String nO_SHELF) {
		NO_SHELF = nO_SHELF;
	}	
	public StoreViewAddCtrl() {
		setSessionForm(true);
		setBindOnNewForm(true);
	}

	protected Object formBackingObject(HttpServletRequest request) throws ServletException {
		logger.debug("---- FORMBACKINGOBJECT ----");
		
		/*-------------------------------------------------------*/
		HttpSession sess = request.getSession();
		String SESSION = (String)sess.getAttribute("SESSIONSTOCAT");
		logger.debug("STOREADD ==> SESSION=" + SESSION);
		/*-------------------------------------------------------*/
		
		// Se setea el objeto command
		// con los datos de la session
		AddStoreDTO command = new AddStoreDTO();
		
		// Se controla si existe alguna sesion activa luego de agregar una categoria de cliente
		if ( SESSION != null && SESSION.equals("SESSIONSTOCAT") ) {
			command.setActive( String.valueOf(sess.getAttribute("active")) );
			command.setAddress1( String.valueOf(sess.getAttribute("address1")) );
			command.setAddress2( String.valueOf(sess.getAttribute("address2")) );
			command.setCode( String.valueOf(sess.getAttribute("code")) );
			command.setEmail( String.valueOf(sess.getAttribute("email")) );
			command.setId_city( Long.parseLong( String.valueOf(sess.getAttribute("id_city")) ) );
			command.setId_state( Long.parseLong( String.valueOf(sess.getAttribute("id_state")) ) );
			command.setId_store_category( Long.parseLong( String.valueOf(sess.getAttribute("id_store_category")) ) );
			command.setId_retail( Long.parseLong( String.valueOf(sess.getAttribute("idr")) ) );
			try { command.setLatitude( Double.parseDouble( String.valueOf(sess.getAttribute("latitude")) ) ); } catch(Exception e) { command.setLatitude( 0.0 ); }
			try { command.setLongitude( Double.parseDouble( String.valueOf(sess.getAttribute("longitude")) ) ); } catch(Exception e) { command.setLongitude( 0.0 ); }
			command.setName( String.valueOf(sess.getAttribute("name")) );
			try { command.setOrderby( Integer.parseInt( String.valueOf(sess.getAttribute("orderby")) ) ); } catch(Exception e) { command.setOrderby( 0 ); }
			try { command.setPostal( Integer.parseInt( String.valueOf(sess.getAttribute("postal")) ) ); } catch(Exception e) { command.setPostal( 0 ); }
			command.setPhone( String.valueOf(sess.getAttribute("phone")) );
			command.setShelf( String.valueOf(sess.getAttribute("shelf")) );

			sess.removeAttribute("SESSIONSTOCAT");
			sess.removeAttribute("active");
			sess.removeAttribute("address1");
			sess.removeAttribute("address2");
			sess.removeAttribute("code");
			sess.removeAttribute("email");
			sess.removeAttribute("id_city");
			sess.removeAttribute("id_state");
			sess.removeAttribute("id_store_category");
			sess.removeAttribute("latitude");
			sess.removeAttribute("longitude");
			sess.removeAttribute("name");
			sess.removeAttribute("orderby");
			sess.removeAttribute("postal");
			sess.removeAttribute("phone");
			sess.removeAttribute("shelf");
			sess.removeAttribute("ids");
			sess.removeAttribute("idr");
		}
		
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
		// Se obtienen los datos del supplier
		Supplier supplier = supplierService.getSupplierById(useraccess.getId_supplier());
		logger.debug("STOREADD ==> supplier = " + supplier.getName());
		/*-------------------------------------------------------*/
		// Se obtiene la accion
		String accion = ServletRequestUtils.getStringParameter(arg0, "accion", null);
		logger.info("accion="+accion);
		/*-------------------------------------------------------*/
		// Se obtiene objeto de dominio del retail
		Long id_retail = ServletRequestUtils.getLongParameter(arg0, "idr");
		Retail retail = retailService.getRetailById(id_retail);
		logger.debug("STOREADD ==> retail=" + retail.getName()+",id="+retail.getId_retail());
		/*-------------------------------------------------------*/
		// Por defecto, se obtiene una lista de estados para el pais del retail
		List<State> states = retailService.getAllStatesByIdCountry( retail.getId_country() );
		logger.debug("STOREADD ==> states=" + states.size());
		/*-------------------------------------------------------*/
		// Por defecto, se obtiene una lista de ciudades a partir del primer estado
		Long idstt = (states.get(0)).getId_state();
		logger.debug("idstt="+idstt);
		List<City> cities = retailService.getAllCityByIdState(idstt);
		logger.debug("STOREADD ==> cities="+cities.size());
		/*-------------------------------------------------------*/
		StoreCategoryActiveSearchCriteria dto = new StoreCategoryActiveSearchCriteria();
		dto.setActive("S");
		dto.setId_supplier( supplier.getId_supplier() );
		List<StoreCategory> categories = retailService.getStoresCategoryActiveByCriteria(dto);
		//se obtiene lista de store_category
		logger.debug("STOREADD ==> categories ="+categories.size());
		
 		String cn = ServletRequestUtils.getStringParameter(arg0, "cn","");
		String fr = ServletRequestUtils.getStringParameter(arg0, "fr", "");
		String mun = ServletRequestUtils.getStringParameter(arg0, "mun", "");
		String est = ServletRequestUtils.getStringParameter(arg0, "est", "");
		String cp = ServletRequestUtils.getStringParameter(arg0, "cp", "");
		String lat = ServletRequestUtils.getStringParameter(arg0, "lat", "");
		String lng = ServletRequestUtils.getStringParameter(arg0, "lng", "");
		String name = ServletRequestUtils.getStringParameter(arg0, "na", "");
		String code = ServletRequestUtils.getStringParameter(arg0, "co", "");
		String phone = ServletRequestUtils.getStringParameter(arg0, "ph", "");
		String email = ServletRequestUtils.getStringParameter(arg0, "em", "");
		String order = ServletRequestUtils.getStringParameter(arg0, "or", "");
		boolean shelf = ServletRequestUtils.getBooleanParameter(arg0, "she", false);
		String cat = ServletRequestUtils.getStringParameter(arg0, "cat", "");

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("useracegi", useracegi);
		model.put("useraccess",useraccess);
		model.put("nttdto", nttdto);

		model.put("accion", accion);
		model.put("retail", retail);
		model.put("supplier", supplier);
		model.put("states", states);
		model.put("cities", cities);
		model.put("categories", categories);
		
		model.put("ACTIVE", ACTIVE);
		model.put("SHELF", SHELF);
		
		model.put("cn", cn);
		model.put("fr", fr);
		model.put("mun", mun);
		model.put("est", est);
		model.put("cp", cp);
		model.put("lat", lat);
		model.put("lng", lng);
		model.put("name", name);
		model.put("code", code);
		model.put("phone", phone);
		model.put("email", email);
		model.put("order", order);
		model.put("she", shelf);
		model.put("cat", cat);
		
		return model;
	}
	
	protected ModelAndView onSubmit(HttpServletRequest request,HttpServletResponse response, Object command, BindException errors) throws ServletException, UserNotFoundException, AccessNotFoundException {
		logger.debug("---- ONSUBMIT ----");
		// Se obtiene el contexto del usuario
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserAcegi useracegi = (UserAcegi) auth.getPrincipal();

		Long id_supplier = ServletRequestUtils.getLongParameter(request, "id_supplier");
		
		// Se controlan los parametros active y orderby
		String active = ServletRequestUtils.getStringParameter(request, "active", NO_ACTIVE);
		Integer orderby = ServletRequestUtils.getIntParameter(request, "orderby", ORDERBY);
		String shelf = ServletRequestUtils.getStringParameter(request, "shelf", NO_SHELF);

		// Se setea dto para agregar un nuevo objeto de dominio Store
		AddStoreDTO dto = (AddStoreDTO) command;
		String name = dto.getName();
		name = name.replaceAll("\"", "");
		name = name.replaceAll("'", "");
		dto.setName(name);
		dto.setActive(active);
		dto.setShelf(shelf);
		dto.setCreated(new Date());
		dto.setLogin( useracegi.getUserlogin() );
		dto.setOrderby(orderby);
		// Se persiste el objeto
		Long id = retailService.addStore(dto);
		logger.debug("Store, id=" + id + " creado... OK");
		
		/*-------------------------------------------------------*/
		// Se genera una notificacion
		AddNotificationDTO dtn = new AddNotificationDTO();
		dtn.setCreated(new Date());
		dtn.setIcon("fa fa-building");
		dtn.setId_user(useracegi.getId_user());
		dtn.setMessage("Nuevo cliente <span class='label label-primary'>"+dto.getName()+"</span> creado");
		dtn.setPriority("1");
		dtn.setId_supplier(id_supplier);
		dtn.setLink("store.htm?id=" + dto.getId_retail());
		// Se persiste la notificacion
		Long idn = userNotificationService.createNotification(dtn, "002");
		logger.debug("Notification, id="+idn);
		/*-------------------------------------------------------*/

		return new ModelAndView(getSuccessView()+"?id="+dto.getId_retail());
	}

}
