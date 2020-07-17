package com.retailsbs.logistikapp.web.ctrl.logistic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
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

import com.retailsbs.logistikapp.logistic.domain.Route;
import com.retailsbs.logistikapp.logistic.domain.Travel;
import com.retailsbs.logistikapp.logistic.domain.WayBill;
import com.retailsbs.logistikapp.logistic.dto.AddTravelDTO;
import com.retailsbs.logistikapp.logistic.exception.RouteNotFoundException;
import com.retailsbs.logistikapp.logistic.service.LogisticService;
import com.retailsbs.logistikapp.retail.service.RetailService;
import com.retailsbs.logistikapp.supplier.domain.Supplier;
import com.retailsbs.logistikapp.supplier.service.SupplierService;
import com.retailsbs.logistikapp.user.domain.Access;
import com.retailsbs.logistikapp.user.domain.Quantities;
import com.retailsbs.logistikapp.user.domain.UserAcegi;
import com.retailsbs.logistikapp.user.dto.AddNotificationDTO;
import com.retailsbs.logistikapp.user.dto.DRICriteria;
import com.retailsbs.logistikapp.user.dto.NotificationTodaySearchCriteria;
import com.retailsbs.logistikapp.user.dto.UserDTO;
import com.retailsbs.logistikapp.user.exception.AccessNotFoundException;
import com.retailsbs.logistikapp.user.exception.UserNotFoundException;
import com.retailsbs.logistikapp.user.notification.service.UserNotificationService;
import com.retailsbs.logistikapp.user.service.UserService;

/**
 * Controlador de vista traveladd
 * @author Developer/Team
 * @since 20-08-2015 
 */
public class TravelViewAddCtrl extends SimpleFormController {

	protected final Log logger = LogFactory.getLog(getClass());

	private UserService userService;
	private RetailService retailService;
	private LogisticService logisticService;
	private SupplierService supplierService;
	private UserNotificationService userNotificationService;
	private String ACTIVE;
	private String DRIVER_PROFILE;
	private String flowToCreateView;
	private String flowToScheduleView;
	private Long TRAVEL_TIME;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public void setRetailService(RetailService retailService) {
		this.retailService = retailService;
	}
	public void setLogisticService(LogisticService logisticService) {
		this.logisticService = logisticService;
	}
	public void setSupplierService(SupplierService supplierService){
		this.supplierService = supplierService;
	}
	public void setUserNotificationService(
			UserNotificationService userNotificationService) {
		this.userNotificationService = userNotificationService;
	}
	public void setACTIVE(String aCTIVE) {
		ACTIVE = aCTIVE;
	}
	public void setDRIVER_PROFILE(String dRIVER_PROFILE) {
		DRIVER_PROFILE = dRIVER_PROFILE;
	}
	public void setFlowToCreateView(String flowToCreateView) {
		this.flowToCreateView = flowToCreateView;
	}
	public void setFlowToScheduleView(String flowToScheduleView) {
		this.flowToScheduleView = flowToScheduleView;
	}
	
	public void setTRAVEL_TIME(Long tRAVEL_TIME) {
		TRAVEL_TIME = tRAVEL_TIME;
	}
	public TravelViewAddCtrl() {
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
		Long id_supplier = userService.getAccessByIdUser(useracegi.getId_user()).get(0).getId_supplier();
		/*-------------------------------------------------------*/
		//Se obtiene el id de supplier
		Supplier supplier = supplierService.getSupplierById(id_supplier);
		Long traveltime = supplier.getTravel_time();
		if(supplier.getTravel_time() == null){
			traveltime = TRAVEL_TIME;
		}
		////////////////////////////////////////////////////////////////////////
		//Se obtienen los datos de acceso para el usuario
		List<Access> accesslist = userService.getAccessByIdUser(useracegi.getId_user());
		//Se obtiene el primer acceso por defecto
		Access useraccess = accesslist.get(0);
		////////////////////////////////////////////////////////////////////////
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
		logger.info("accion="+accion);
		/*-------------------------------------------------------*/
		String tname = ""; //nombre del vaje
		String vendor = ""; //nombre del vendedor
		Long iduser = 0L; //id del vendedor
		String date = ""; //fecha de programacion
		
		if( arg0.getParameter("tname") != null && !arg0.getParameter("tname").equals("") )
			tname = ServletRequestUtils.getStringParameter(arg0, "tname");
		
		if( arg0.getParameter("vendor") != null && !arg0.getParameter("vendor").equals("") )
			vendor = ServletRequestUtils.getStringParameter(arg0, "vendor");
		
		if( arg0.getParameter("iduser") != null && !arg0.getParameter("iduser").equals("") )
			iduser = ServletRequestUtils.getLongParameter(arg0, "iduser");
		
		if( arg0.getParameter("date") != null && !arg0.getParameter("date").equals("") )
			date = ServletRequestUtils.getStringParameter(arg0, "date");
		
		/*-------------------------------------------------------*/
		
		List<Long> ids = new LinkedList<Long>();
		
		// Se obtiene parametro ruta
		String ids_route = ServletRequestUtils.getStringParameter(arg0, "idr");
		logger.debug("TRAVELADD ==> ids_route="+ids_route);
		if ( ids_route != null && !ids_route.equals("") ) {
			for (String i : ids_route.split(",")){
				ids.add(Long.parseLong(i));
			}
			logger.debug("TRAVELADD ==> ids="+ids.size());
		}
		/*-------------------------------------------------------*/
		
		// se obtienen las rutas del proveedor			
		List<Route> routes = logisticService.getAllRouteByIdSupplier(id_supplier);
		logger.debug("TRAVELADD ==> routes="+routes.size());
//		if (ids_route == null || ids_route.equals(""))
//			if(routes.size() > 0)
//				ids_route = routes.get(0).getId_route().toString();			
		
		/*-------------------------------------------------------*/
		
		// Se obtienen los choferes disponibles para ese proveedor
		DRICriteria dto = new DRICriteria();
		dto.setId_supplier(id_supplier);
		dto.setDate(new Date());
		List<UserDTO> drivers = userService.getDisponibleDrivers(dto);
		
		/*-------------------------------------------------------*/
		
		/*List<StoreDTO> availableStores = new ArrayList<StoreDTO>();
		List<StoreDTO> notAvailableStores = new ArrayList<StoreDTO>();
		List<StoreDTO> list = new ArrayList<StoreDTO>();
		// Se controla que haya seleccionado alguna ruta para obtener clientes
		if ( ids.size() > 0 ) {
			//Se obtienen tiendas por ruta default 
			StoreAvailableInRouteCriteria st_dto = new StoreAvailableInRouteCriteria();
			st_dto.setId_route(ids);
			// Se obtienen tiendas a partir de las rutas
			availableStores = retailService.getStoreAvailableByRoute(st_dto);
			notAvailableStores = retailService.getStoreInTravelByRoute(st_dto);
			// se unen en una sola lista todas las tiendas avaible and not avaible
			list = new ArrayList<StoreDTO>();
			StoreDTO store;
			for(int i=0; i<availableStores.size();i++){
				store = new StoreDTO();
				store.setAvaible("storeavailable");
				store.setLatitude(availableStores.get(i).getLatitude());
				store.setLongitude(availableStores.get(i).getLongitude());
				store.setName(availableStores.get(i).getName());
				store.setId_store(availableStores.get(i).getId_store());
				store.setShelf(availableStores.get(i).getShelf());
				store.setAddress1(availableStores.get(i).getAddress1());
				store.setAddress2(availableStores.get(i).getAddress2());
				store.setPostal(availableStores.get(i).getPostal());
				store.setColor(availableStores.get(i).getColor());
				store.setRoute(availableStores.get(i).getRoute());
				list.add(store);
			}
			for(int i=0; i<notAvailableStores.size();i++){
				store = new StoreDTO();
				store.setAvaible("storenoavailable");
				store.setLatitude(notAvailableStores.get(i).getLatitude());
				store.setLongitude(notAvailableStores.get(i).getLongitude());
				store.setName(notAvailableStores.get(i).getName());
				store.setId_store(notAvailableStores.get(i).getId_store());
				store.setShelf(notAvailableStores.get(i).getShelf());
				store.setAddress1(notAvailableStores.get(i).getAddress1());
				store.setAddress2(notAvailableStores.get(i).getAddress2());
				store.setPostal(notAvailableStores.get(i).getPostal());
				store.setColor(notAvailableStores.get(i).getColor());
				store.setRoute(notAvailableStores.get(i).getRoute());
				list.add(store);
			}
		}*/

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.MONTH, 6);
		Date last_time = calendar.getTime();
		Date now = new Date();
		Long time = last_time.getTime() - now.getTime();
		Long days = time/1000/60/60/24;
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("useracegi", useracegi);
		model.put("nttdto", nttdto);
		model.put("choferes",drivers);
		//model.put("tienda_disp",availableStores);
		//model.put("tienda_no_disp",notAvailableStores);
		model.put("accion", accion);		
		model.put("ACTIVE", ACTIVE);
		model.put("id_route", ids_route);		
		model.put("rutas", routes);
		//model.put("list", list);
		//model.put("totlist", list.size());
		model.put("tname", tname);
		model.put("vendor", vendor);
		model.put("iduser", iduser);
		model.put("date", date);
		model.put("useraccess",useraccess);
		model.put("traveltime", traveltime);
		model.put("maxdays", days);
		return model;
	}

	protected Object formBackingObject(HttpServletRequest request) throws ServletException, RouteNotFoundException {
		//==========================================================
		
		logger.debug("---- FORMBACKINGOBJECT ----");
		AddTravelDTO command = new AddTravelDTO();
	
		return command;
	}

	protected ModelAndView onSubmit(HttpServletRequest request,HttpServletResponse response, Object command, BindException errors) throws ServletException, UserNotFoundException, AccessNotFoundException, ParseException {
		logger.debug("---- ONSUBMIT ----");
		// Se obtiene el contexto del usuario
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserAcegi useracegi = (UserAcegi) auth.getPrincipal();
		
		// Se setea dto para agregar un nuevo objeto de dominio Travel
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");// 2015/07/16 12:40
		AddTravelDTO dto = (AddTravelDTO) command;
		Travel travel_dto = null;
		Date schedule = null;
		if (dto.getSchedule()!= null && !dto.getSchedule().equals("")){
			schedule = formatter.parse(dto.getSchedule()) ;
		}
		Long id_travel = 0L;
		Calendar calendar = Calendar.getInstance();
		
		for (int i = 0; i < dto.getRepetitions(); i++){
			travel_dto = new Travel();
			travel_dto.setId_user(dto.getId_user());
			if (!dto.getId_route().contains(",")){
				travel_dto.setId_route(Long.parseLong(dto.getId_route()));
			}
			travel_dto.setName(dto.getName());
			travel_dto.setStatus(dto.getStatus());
			travel_dto.setLog_created(new Date());
			travel_dto.setLog_created_login(useracegi.getUserlogin());
			travel_dto.setKm(dto.getKm());
			travel_dto.setTime(dto.getTime());
			if (dto.getSchedule()!= null && !dto.getSchedule().equals("")){
				travel_dto.setSchedule(schedule);
			}
			// Se persiste el objeto
			id_travel = logisticService.addTravel(travel_dto);
			
			String ids = ServletRequestUtils.getRequiredStringParameter(request, "ids");
			String store_ids[] = ids.split(",");
			int order = 1;
			for(String id: store_ids){
				WayBill waybill = new WayBill();
				Long id_store = Long.parseLong(id.replace("n", ""));
				waybill.setId_store(id_store);
				waybill.setId_travel(id_travel);
				waybill.setLog_created(new Date());
				waybill.setLog_created_login(useracegi.getLogin());
				waybill.setOrderby(order);
				waybill.setStatus("N");
				logisticService.addWayBill(waybill);
				order ++;
			}		
	
			// se obtiene id_supplier
			Long id_sup = userService.getAccessByIdUser(useracegi.getId_user()).get(0).getId_supplier();
			logger.debug("id_sup = "+id_sup);
			
			// Se genera una notificacion
			if (dto.getStatus().equals("PRO")){
				AddNotificationDTO dtn = new AddNotificationDTO();
				dtn.setCreated(new Date());
				dtn.setIcon("fa fa-building");
				dtn.setId_user(useracegi.getId_user());
				dtn.setMessage("Nuevo viaje <span class='label label-primary'>"+dto.getName()+"</span> " + (dto.getStatus().equals("PRO")?"programado" :"creado"));
				dtn.setPriority("1");
				dtn.setId_supplier(id_sup);
				dtn.setLink(dto.getStatus().equals("PRO")?"travelwaybill.htm?idt=" + id_travel :"travelupd.htm?idt=" + id_travel + "&accion=upd");
				// Se persiste la notificacion
				Long idn = userNotificationService.createNotification(dtn, "003");
				List<Integer> users = new LinkedList<Integer>();
				users.add(dto.getId_user().intValue());
				userNotificationService.createNotificationWithIdNotification(idn, users);
				logger.debug("Notification, id="+idn);
			}
			calendar.setTime(schedule);
			if (dto.getRepetitionsday() == 1){
				calendar.add(Calendar.DAY_OF_YEAR, 1);
			} else if (dto.getRepetitionsday() == 2){
				calendar.add(Calendar.WEEK_OF_MONTH, 1);
			}else if (dto.getRepetitionsday() == 3){
				calendar.add(Calendar.MONTH, 1);
			}else if (dto.getRepetitionsday() == 4){
				calendar.add(Calendar.WEEK_OF_MONTH, 2);
			}
			
			schedule = calendar.getTime();
		}
		/*-------------------------------------------------------*/
		String vista = "";

		if ( dto.getStatus().equals("PRO")){
			if (dto.getRepetitions() == 1){
				vista = flowToScheduleView + "?idt=" + id_travel;
			}else{
				vista = "redirect:travellist.htm";
			}
		}else if ( dto.getStatus().equals("CRE") ){
			if (dto.getRepetitions() == 1){
				vista = flowToCreateView;
			}else{
				vista = "redirect:travellist.htm";
			}
		}
		
		logger.debug("vista="+vista);
		
		return new ModelAndView(vista);
	}
	
}
