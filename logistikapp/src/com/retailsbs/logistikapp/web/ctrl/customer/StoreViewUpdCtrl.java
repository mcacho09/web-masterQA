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

import com.retailsbs.logistikapp.retail.domain.Retail;
import com.retailsbs.logistikapp.retail.domain.Store;
import com.retailsbs.logistikapp.retail.domain.StoreCategory;
import com.retailsbs.logistikapp.retail.dto.CityDTO;
import com.retailsbs.logistikapp.retail.dto.GetCitySearchCriteria;
import com.retailsbs.logistikapp.retail.dto.GetStateSearchCriteria;
import com.retailsbs.logistikapp.retail.dto.StateDTO;
import com.retailsbs.logistikapp.retail.dto.StoreCategoryActiveSearchCriteria;
import com.retailsbs.logistikapp.retail.dto.UpdStoreDTO;
import com.retailsbs.logistikapp.retail.exception.StoreNotFoundException;
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
 * Controlador de vista para modificar clientes (stores)
 * @author Juan
 * @since 25-11-2014
 * @modified 08-01-2015 - Jorge - Estandarizacion estructura de controller
 * @modified 16-02-2015 - Jorge - Integración para contador y alertas en barra navegación
 * @modified 18-11-2015 - Jorge - Actualización controller
 */
public class StoreViewUpdCtrl extends SimpleFormController {
	
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
	
	public StoreViewUpdCtrl() {
		setSessionForm(true);
		setBindOnNewForm(true);
	}
	
	protected Object formBackingObject(HttpServletRequest request) throws ServletException, StoreNotFoundException{
		logger.debug("---- FORMBACKINGOBJECT ----");

		/*-------------------------------------------------------*/
		HttpSession sess = request.getSession();
		String SESSION = (String)sess.getAttribute("SESSIONSTOCAT");
		logger.debug("STOREUPD ==> SESSION=" + SESSION);
		/*-------------------------------------------------------*/

		// Se setea el objeto command
		// con los datos del objeto de dominio
		UpdStoreDTO command = new UpdStoreDTO();
		
		// Se controla si existe alguna sesion activa luego de agregar una categoria de cliente
		if ( SESSION != null && SESSION.equals("SESSIONSTOCAT") ) {
			command.setActive( String.valueOf(sess.getAttribute("active")) );
			command.setAddress1( String.valueOf(sess.getAttribute("address1")) );
			command.setAddress2( String.valueOf(sess.getAttribute("address2")) );
			command.setCode( String.valueOf(sess.getAttribute("code")) );
			command.setEmail( String.valueOf(sess.getAttribute("email")) );
			command.setId_city( Long.parseLong( String.valueOf(sess.getAttribute("id_city")) ) );
			command.setId_state( Long.parseLong( String.valueOf(sess.getAttribute("id_state")) ) );
			command.setId_store( Long.parseLong( String.valueOf(sess.getAttribute("ids")) ) );
			command.setId_store_category( Long.parseLong( String.valueOf(sess.getAttribute("id_store_category")) ) );
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
		} else {
			/*-------------------------------------------------------*/
			// Se obtiene objeto de dominio del store
			logger.debug("STOREUPD ==> ids=" + request.getParameter("ids"));
			Long id_store = ServletRequestUtils.getLongParameter(request, "ids");
			logger.debug("STOREUPD ==> id_store" + id_store);
			
			//Se obtiene el filtrado utilizado para la busqueda
			logger.debug("REQUEST ==> searchByName= "+ request.getParameter("searchByName"));
			String searchByName = null;
			if ( request.getParameter("searchByName") != null && !request.getParameter("searchByName").equals("") )
				searchByName = ServletRequestUtils.getStringParameter(request,"searchByName");
			
			// Se obtiene el objeto de dominio de store
			Store store = retailService.getStoreById(id_store);
			logger.debug("STOREUPD ==> store=" + store.getName()+",id="+store.getId_store());

			command.setActive(store.getActive());
			command.setAddress1(store.getAddress1());
			command.setAddress2(store.getAddress2());
			command.setCode(store.getCode());
			command.setCreated(store.getCreated());
			command.setId_city(store.getId_city());
			command.setId_country(store.getId_country());
			command.setId_locality(store.getId_locality());
			command.setId_retail(store.getId_retail());
			command.setId_store_category(store.getId_store_category());
			command.setId_state(store.getId_state());
			command.setId_store(store.getId_store());
			command.setLatitude(store.getLatitude());
			command.setLogin(store.getLogin());
			command.setLongitude(store.getLongitude());
			command.setModified(store.getModified());
			command.setName(store.getName());
			command.setOrderby(store.getOrderby());
			command.setPostal(store.getPostal());
			command.setEmail(store.getEmail());
			command.setPhone(store.getPhone());
			command.setShelf(store.getShelf());
		}
		
		return command;
	}

	protected Map<String, Object> referenceData(HttpServletRequest arg0, Object arg1, Errors arg2) throws Exception {
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
		logger.debug("STOREUPD ==> supplier = " + supplier.getName());
		/*-------------------------------------------------------*/
		// Se obtiene la accion
		String accion = ServletRequestUtils.getStringParameter( arg0, "accion", null );
		logger.debug("STOREUPD ==> accion="+accion);
		/*-------------------------------------------------------*/
		// Se obtiene objeto de dominio del store para obtener datos generales
		Long id_store = ServletRequestUtils.getLongParameter(arg0, "ids");
		// Se obtiene valor de parametraje searchByName
		String namesearch = ServletRequestUtils.getStringParameter(arg0, "searchByName");

		// Se obtiene el objeto de dominio de store
		Store store = retailService.getStoreById(id_store);
		logger.debug("STOREUPD ==> store=" + store.getName()+",id="+store.getId_store());
		/*-------------------------------------------------------*/
		// Se obtiene objeto de dominio retail
		Retail retail = retailService.getRetailById( store.getId_retail() );
		logger.debug("STOREUPD ==> retail=" + retail.getName()+",id="+retail.getId_retail());
		/*-------------------------------------------------------*/
		// Se obtiene lista de estados
		GetStateSearchCriteria dto_state = new GetStateSearchCriteria();
		dto_state.setId_country( retail.getId_country() );
		List<StateDTO> states = retailService.getStateByCriteria(dto_state);
		logger.debug("STOREUPD ==> states=" + states.size());
		/*-------------------------------------------------------*/
		// Se obtiene lista de municipios
		GetCitySearchCriteria dto_city = new GetCitySearchCriteria();
		dto_city.setId_state( store.getId_state() );
		List<CityDTO> cities = retailService.getCityByCriteria(dto_city);
		logger.debug("STOREUPD ==> cities=" + cities.size());
		/*-------------------------------------------------------*/
		//se obtiene lista de categorias de locales
		StoreCategoryActiveSearchCriteria dto_stocat = new StoreCategoryActiveSearchCriteria();
		dto_stocat.setActive("S");
		dto_stocat.setId_supplier( supplier.getId_supplier() );
		dto_stocat.setId_store_category( store.getId_store_category() );
		List<StoreCategory> categories = retailService.getStoresCategoryActiveByCriteria(dto_stocat);
		logger.debug("STOREUPD ==> categories=" + categories.size());
		
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
		model.put("namesearch",namesearch);

		model.put("accion", accion);
		model.put("retail", retail);
		model.put("supplier", supplier);
		model.put("cities", cities);
		model.put("states", states);
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
	
	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws ServletException, StoreNotFoundException, UserNotFoundException, AccessNotFoundException{
		logger.debug("---- ONSUBMIT ----");
		// Se obtiene el contexto del usuario
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserAcegi useracegi = (UserAcegi) auth.getPrincipal();

		Long id_supplier = ServletRequestUtils.getLongParameter(request, "id_supplier");
		
		// Se controlan los parametros active y orderby
		String active = ServletRequestUtils.getStringParameter(request, "active", NO_ACTIVE);
		Integer orderby = ServletRequestUtils.getIntParameter(request, "orderby", ORDERBY);
		String shelf = ServletRequestUtils.getStringParameter(request, "shelf",NO_SHELF);
		
		// Se setea dto para modificar el objeto de dominio Retail
		UpdStoreDTO dto = (UpdStoreDTO) command;
		String name = dto.getName();
		name = name.replaceAll("\"", "");
		name = name.replaceAll("'", "");
		dto.setName(name);
		dto.setActive(active);
		dto.setShelf(shelf);
		dto.setLogin( useracegi.getUserlogin() );
		dto.setModified(new Date());
		dto.setOrderby(orderby);
		// Se persiste el objeto
		int rows = retailService.updStore(dto);
		logger.debug("STOREUPD ==>Store id=" + dto.getId_store() + " actualizado " + rows + "...ok!");
		
		/*-------------------------------------------------------*/
		// Se genera una notificacion
		AddNotificationDTO dtn = new AddNotificationDTO();
		dtn.setCreated(new Date());
		dtn.setIcon("fa fa-building");
		dtn.setId_user(useracegi.getId_user());
		dtn.setMessage("Cliente <span class='label label-success'>"+dto.getName()+"</span> actualizado");
		dtn.setPriority("1");
		dtn.setId_supplier(id_supplier);
		dtn.setLink("store.htm?id=" + dto.getId_retail());
		// Se persiste la notificacion
		Long idn = userNotificationService.createNotification(dtn, "003");
		logger.debug("Notification, id="+idn);
		/*-------------------------------------------------------*/
		String busqueda = request.getParameter("searchByName");
		String ruta = (busqueda == null ? "": ("?searchByName="+busqueda));
		return new ModelAndView(busqueda != null ? ("redirect:storesearch.htm" + ruta) : getSuccessView()+"?id="+dto.getId_retail());
	}

}
