package com.retailsbs.logistikapp.web.ctrl.customer;

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

import com.retailsbs.logistikapp.retail.domain.City;
import com.retailsbs.logistikapp.retail.domain.State;
import com.retailsbs.logistikapp.retail.domain.StoreCategory;
import com.retailsbs.logistikapp.retail.dto.AddStoreDTO;
import com.retailsbs.logistikapp.retail.dto.RetailDTO;
import com.retailsbs.logistikapp.retail.dto.RetailSearchCriteria;
import com.retailsbs.logistikapp.retail.dto.StoreCategoryActiveSearchCriteria;
import com.retailsbs.logistikapp.retail.service.RetailService;
import com.retailsbs.logistikapp.supplier.domain.Plan;
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
 * Controlador de vista para un nuevo cliente (store)
 * @author Jorge
 * @since 19-11-2015
 */
public class CustomerViewAddCtrl extends SimpleFormController {

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
	private Integer PAIS;
	
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
	public void setPAIS(Integer pAIS) {
		PAIS = pAIS;
	}
	public void setSHELF(String sHELF) {
		SHELF = sHELF;
	}
	public void setNO_SHELF(String nO_SHELF) {
		NO_SHELF = nO_SHELF;
	}	
	public CustomerViewAddCtrl() {
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
		Long id_user = useracegi.getId_user();
		logger.debug( "CUSTOMER NEW ==> id_user=" + id_user );
		Long id_supplier = userService.getAccessByIdUser(useracegi.getId_user()).get(0).getId_supplier();
		logger.debug( "CUSTOMER NEW ==> id_supplier=" + id_supplier );
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
		// Se obtiene un listado de retail
		RetailSearchCriteria dtre = new RetailSearchCriteria();
		dtre.setId_supplier(id_supplier);
		dtre.setActive("S");
		List<RetailDTO> retailers = retailService.getRetailByCriteria(dtre);
		logger.debug( "CUSTOMER NEW ==> retailers=" + retailers.size() );
		/*-------------------------------------------------------*/
		// Por defecto, se obtiene una lista de estados para el pais del retail
		List<State> states = retailService.getAllStatesByIdCountry( (long)PAIS );
		logger.debug( "CUSTOMER NEW ==> states=" + states.size() );
		/*-------------------------------------------------------*/
		// Por defecto, se obtiene una lista de ciudades a partir del primer estado
		Long idstt = (states.get(0)).getId_state();
		logger.debug( "CUSTOMER NEW ==> idstt=" + idstt );
		List<City> cities = retailService.getAllCityByIdState(idstt);
		logger.debug( "CUSTOMER NEW ==> cities=" + cities.size() );
		/*-------------------------------------------------------*/
		// Se obtiene un listado de categorias de cliente (store category)
		StoreCategoryActiveSearchCriteria dto = new StoreCategoryActiveSearchCriteria();
		dto.setActive( ACTIVE );
		dto.setId_supplier( id_supplier );
		List<StoreCategory> categories = retailService.getStoresCategoryActiveByCriteria(dto);
		//se obtiene lista de store_category
		logger.debug( "CUSTOMER NEW ==> categories=" + categories.size() );
		/*-------------------------------------------------------*/
		Supplier supplier = supplierService.getSupplierById(id_supplier);
		/*-------------------------------------------------------*/
		//Se obtiene la informacion del plan
      	Long id_plan = supplier.getId_plan();
      	Plan plan = supplierService.getPlanById(id_plan);
      	// Se define criterio de busqueda para obtener listado de retailers
     	RetailSearchCriteria dtto = new RetailSearchCriteria();
     	dtto.setId_supplier(supplier.getId_supplier());
      	// Se obtiene lista de retailers
     	List<RetailDTO> list = retailService.getRetailByCriteria(dtto);
     	logger.debug("list="+list);
		/*-------------------Calculo limite clientes-------------*/
		int total = 0;
		for (RetailDTO re : list) {
			total += re.getStores();
		}
		boolean advertencia = (total >= plan.getCustomers() ? true : false);
		
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
		String retail = ServletRequestUtils.getStringParameter(arg0, "ret", "");
		String cat = ServletRequestUtils.getStringParameter(arg0, "cat", "");

		/*-------------------------------------------------------*/
		Map<String, Object> model = new HashMap<String, Object>();
		model.put( "useracegi", useracegi );
		model.put("useraccess", useraccess);
		model.put( "nttdto", nttdto );
		model.put( "ACTIVE", ACTIVE );
		model.put( "ORDERBY", ORDERBY );
		model.put( "PAIS", PAIS );
				
		model.put("id_supplier", id_supplier);
		model.put("supplier", supplier);
		model.put("retailers", retailers);
		model.put("states", states);
		model.put("cities", cities);
		model.put("categories", categories);
		model.put("advertencia", advertencia);
		
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
		model.put("retail", retail);
		
		return model;
	}

	protected Object formBackingObject(HttpServletRequest request) throws ServletException {
		logger.debug("---- FORMBACKINGOBJECT ----");
		AddStoreDTO command = new AddStoreDTO();
		return command;
	}

	protected ModelAndView onSubmit(HttpServletRequest request,HttpServletResponse response, Object command, BindException errors) throws ServletException, UserNotFoundException, AccessNotFoundException {
		logger.debug("---- ONSUBMIT ----");
		// Se obtiene el contexto del usuario
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserAcegi useracegi = (UserAcegi) auth.getPrincipal();

		// Se controlan los parametros active, orderby y id_supplier
		String active = ServletRequestUtils.getStringParameter( request, "active", NO_ACTIVE );
		Integer orderby = ServletRequestUtils.getIntParameter( request, "orderby", ORDERBY );
		Long id_supplier = ServletRequestUtils.getLongParameter(request, "id_supplier");
		// Se setea dto para agregar un nuevo objeto de dominio Store
		AddStoreDTO dto = (AddStoreDTO) command;
		String name = dto.getName();
		name = dto.getName().replaceAll("\"", "");
		name = dto.getName().replaceAll("'", "");
		dto.setName(name);
		dto.setActive( active );
		dto.setCreated( new Date() );
		dto.setLogin( useracegi.getUserlogin() );
		dto.setOrderby( orderby );
		// Se persiste el objeto
		Long id = retailService.addStore( dto );
		logger.info( "Store, id=" + id + " creado... OK" );
		
		/*-------------------------------------------------------*/
		// Se genera una notificación
		AddNotificationDTO dtn = new AddNotificationDTO();
		dtn.setCreated( new Date() );
		dtn.setIcon( "fa fa-building" );
		dtn.setId_user( useracegi.getId_user() );
		dtn.setMessage( "Nuevo cliente <span class='label label-primary'>"+dto.getName()+"</span> creado" );
		dtn.setPriority( "1" );
		dtn.setId_supplier( id_supplier );
		dtn.setLink("store.htm?id=" + dto.getId_retail());
		// Se persiste la notificacion
		Long idn = userNotificationService.createNotification(dtn, "002");
		logger.debug( "Notification, id="+idn );
		/*-------------------------------------------------------*/

		return new ModelAndView( getSuccessView() );
	}

}
