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
import com.retailsbs.logistikapp.logistic.dto.UpdTravelDTO;
import com.retailsbs.logistikapp.logistic.exception.TravelNotFoundException;
import com.retailsbs.logistikapp.logistic.service.LogisticService;
import com.retailsbs.logistikapp.retail.service.RetailService;
import com.retailsbs.logistikapp.supplier.domain.Supplier;
import com.retailsbs.logistikapp.supplier.exception.SupplierNotFoundException;
import com.retailsbs.logistikapp.supplier.service.SupplierService;
import com.retailsbs.logistikapp.user.domain.Access;
import com.retailsbs.logistikapp.user.domain.Quantities;
import com.retailsbs.logistikapp.user.domain.UserAcegi;
import com.retailsbs.logistikapp.user.dto.AddNotificationDTO;
import com.retailsbs.logistikapp.user.dto.NotificationTodaySearchCriteria;
import com.retailsbs.logistikapp.user.dto.UserDTO;
import com.retailsbs.logistikapp.user.dto.UserSearchCriteria;
import com.retailsbs.logistikapp.user.exception.AccessNotFoundException;
import com.retailsbs.logistikapp.user.exception.UserNotFoundException;
import com.retailsbs.logistikapp.user.notification.service.UserNotificationService;
import com.retailsbs.logistikapp.user.service.UserService;

/**
 * Controlador de vista travelupd
 * 
 * @author Developer/Team
 * @since 20-08-2015
 */
public class TravelViewUpdCtrl extends SimpleFormController {

	protected final Log logger = LogFactory.getLog(getClass());

	private UserService userService;
	private LogisticService logisticService;
	private RetailService retailService;
	private SupplierService supplierService;
	private UserNotificationService userNotificationService;
	private String DRIVER_PROFILE;
	private String flowToCreateView;
	private String flowToScheduleView;
	private Long TRAVEL_TIME;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setLogisticService(LogisticService logisticService) {
		this.logisticService = logisticService;
	}

	public void setSupplierService(SupplierService supplierService) {
		this.supplierService = supplierService;
	}

	public void setUserNotificationService(
			UserNotificationService userNotificationService) {
		this.userNotificationService = userNotificationService;
	}

	public void setDRIVER_PROFILE(String dRIVER_PROFILE) {
		DRIVER_PROFILE = dRIVER_PROFILE;
	}

	public void setRetailService(RetailService retailService) {
		this.retailService = retailService;
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

	public TravelViewUpdCtrl() {
		setSessionForm(true);
		setBindOnNewForm(true);
	}

	protected Map<String, Object> referenceData(HttpServletRequest arg0,
			Object arg1, Errors arg2) throws Exception {
		logger.debug("---- REFERENCEDATA ----");
		/*-------------------------------------------------------*/
		logger.debug("----- USER CONTEXT -----");
		// Se obtiene el contexto del usuario
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		UserAcegi useracegi = (UserAcegi) auth.getPrincipal();
		Long id_supplier = userService
				.getAccessByIdUser(useracegi.getId_user()).get(0)
				.getId_supplier();
		/*-------------------------------------------------------*/
		// Se obtiene el id de supplier
		Supplier supplier = supplierService.getSupplierById(id_supplier);
		Long traveltime = supplier.getTravel_time();
		if (supplier.getTravel_time() == null) {
			traveltime = TRAVEL_TIME;
		}
		// //////////////////////////////////////////////////////////////////////
		// Se obtienen los datos de acceso para el usuario
		List<Access> accesslist = userService.getAccessByIdUser(useracegi
				.getId_user());
		// Se obtiene el primer acceso por defecto
		Access useraccess = accesslist.get(0);
		// //////////////////////////////////////////////////////////////////////
		NotificationTodaySearchCriteria dtoqty = new NotificationTodaySearchCriteria();
		dtoqty.setProfile(useracegi.getProfile());
		dtoqty.setFecha(new Date());
		dtoqty.setId_supplier(useraccess.getId_supplier());
		dtoqty.setId_user(useracegi.getId_user());
		logger.debug("----- HEADER/NOTIFICATION -----");
		Quantities nttdto = userService.getQtyAlertAndMessagesByIdUser(dtoqty);
		// HeaderNotificationDTO nttdto =
		// userService.getHeaderNotificationByIdUser(useracegi.getId_user());
		logger.debug("alert=" + nttdto.getAlert_qty() + " | message="
				+ nttdto.getMessage_qty() + " | todo=" + nttdto.getTodo_qty());
		/*-------------------------------------------------------*/
		// Se obtiene la accion
		String accion = ServletRequestUtils.getStringParameter(arg0, "accion",
				null);
		logger.debug("accion=" + accion);
		/*-------------------------------------------------------*/
		String tname = ""; // nombre del vaje
		String vendor = ""; // nombre del vendedor
		Long iduser = 0L; // id del vendedor
		String date = ""; // fecha de programacion

		if (arg0.getParameter("tname") != null
				&& !arg0.getParameter("tname").equals(""))
			tname = ServletRequestUtils.getStringParameter(arg0, "tname");

		if (arg0.getParameter("vendor") != null
				&& !arg0.getParameter("vendor").equals(""))
			vendor = ServletRequestUtils.getStringParameter(arg0, "vendor");

		if (arg0.getParameter("iduser") != null
				&& !arg0.getParameter("iduser").equals(""))
			iduser = ServletRequestUtils.getLongParameter(arg0, "iduser");

		if (arg0.getParameter("date") != null
				&& !arg0.getParameter("date").equals(""))
			date = ServletRequestUtils.getStringParameter(arg0, "date");

		/*-------------------------------------------------------*/

		// Se obtiene travel
		Long id_travel = ServletRequestUtils.getRequiredLongParameter(arg0,
				"idt");

		/*-------------------------------------------------------*/

		// Se obtienen todas las rutas del proveedor
		List<Route> all_rutas = logisticService
				.getAllRouteByIdSupplier(id_supplier);
		logger.debug("TRAVELUPD ==> all_rutas=" + all_rutas.size());

		/*-------------------------------------------------------*/
		// Se obtienen los choferes asociados al proveedor
		UserSearchCriteria dto = new UserSearchCriteria();
		dto.setId_supplier(id_supplier);
		dto.setProfile(DRIVER_PROFILE);
		List<UserDTO> drivers = userService.getUserByCriteria(dto);
		logger.debug("TRAVELUPD ==> drivers=" + drivers.size());
		/*-------------------------------------------------------*/
		
		if(supplier.getTravel_time() == null){
			traveltime = TRAVEL_TIME;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.MONTH, 6);
		Date now = new Date();
		Date last_time = calendar.getTime();
		Long time = last_time.getTime() - now.getTime();
		Long days = time/1000/60/60/24;
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("useracegi", useracegi);
		model.put("nttdto", nttdto);
		model.put("choferes", drivers);
		model.put("accion", accion);
		model.put("rutas", all_rutas);
		model.put("id_travel", id_travel);
		model.put("traveltime", traveltime);
		model.put("tname", tname);
		model.put("vendor", vendor);
		model.put("iduser", iduser);
		model.put("date", date);
		model.put("traveltime", traveltime);
		model.put("maxdays", days);

		model.put("useraccess", useraccess);
		return model;
	}

	protected Object formBackingObject(HttpServletRequest request)
			throws ServletException, TravelNotFoundException {
		logger.debug("---- FORMBACKINGOBJECT ----");
		// Se obtiene objeto de dominio del usuario
		Long id_travel = ServletRequestUtils.getLongParameter(request, "idt");
		Travel record = logisticService.getTravelById(id_travel);
		/*-------------------------------------------------------*/
		// Se setea command con los datos del objeto de dominio
		UpdTravelDTO command = new UpdTravelDTO();
		command.setName(record.getName());

		if (record.getSchedule() != null)
			command.setSchedule(record.getSchedule().toString());
		if (record.getId_route() != null) {
			command.setId_route(record.getId_route().toString());
		}
		command.setId_user(record.getId_user());

		return command;
	}

	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws ServletException, SupplierNotFoundException,
			UserNotFoundException, AccessNotFoundException, ParseException,
			TravelNotFoundException {
		logger.debug("---- ONSUBMIT ----");
		// Se obtiene el contexto del usuario
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		UserAcegi useracegi = (UserAcegi) auth.getPrincipal();

		// Se setea dto para agregar un nuevo objeto de dominio Travel
		Long id_travel = ServletRequestUtils.getRequiredLongParameter(request,
				"idt");
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");// 2015/07/16
																		// 12:40
		UpdTravelDTO dto = (UpdTravelDTO) command;

		Travel travel_dto = new Travel();
		travel_dto.setId_travel(id_travel);
		travel_dto.setId_user(dto.getId_user());
		/*if (!dto.getId_route().contains(",")) {
			travel_dto.setId_route(Long.parseLong(dto.getId_route()));
		}*/
		travel_dto.setName(dto.getName());
		travel_dto.setStatus(dto.getStatus());
		travel_dto.setLog_modified(new Date());
		travel_dto.setLog_modified_login(useracegi.getUserlogin());
		travel_dto.setKm(dto.getKm());
		travel_dto.setTime(dto.getTime());
		Date schedule = new Date();
		if (dto.getSchedule() != null && !dto.getSchedule().equals("")) {
			schedule = formatter.parse(dto.getSchedule());
			travel_dto.setSchedule(schedule);
		}

		// Se persiste el objeto
		logisticService.updTravelById(travel_dto);

		String ids = ServletRequestUtils.getRequiredStringParameter(request,
				"ids");
		String store_ids[] = ids.split(",");
		int order = 1;
		logisticService.delWayBillByIdTravel(id_travel);
		for (String id : store_ids) {

			WayBill waybill = new WayBill();
			Long id_store = Long.parseLong(id.replace("n", ""));
			waybill.setId_store(id_store);
			waybill.setId_travel(id_travel);
			waybill.setLog_created(new Date());
			waybill.setLog_created_login(useracegi.getLogin());
			waybill.setOrderby(order);
			waybill.setStatus("N");
			logisticService.addWayBill(waybill);
			order++;
		}
		
		Calendar calendar = Calendar.getInstance();
		
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
		
		for (long i = 0; i < dto.getRepetitions(); i++){
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
			
			order = 1;
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
		// se obtiene id_supplier
		Long id_sup = userService.getAccessByIdUser(useracegi.getId_user())
				.get(0).getId_supplier();
		logger.debug("id_sup = " + id_sup);
		// Se genera una notificaci�n
		AddNotificationDTO dtn = new AddNotificationDTO();
		dtn.setCreated(new Date());
		dtn.setIcon("fa fa-building");
		dtn.setId_user(useracegi.getId_user());
		dtn.setMessage("Viaje <span class='label label-primary'>"
				+ dto.getName() + "</span> modificado");
		dtn.setPriority("1");
		dtn.setId_supplier(id_sup);
		dtn.setLink("alertlist.htm");
		// Se persiste la notificacion
		Long idn = userNotificationService.createNotification(dtn, "003");
		logger.debug("Notification, id=" + idn);

		if (travel_dto.getId_route() != null) {
			List<Integer> users = new LinkedList<Integer>();
			users.add(travel_dto.getId_user().intValue());
			userNotificationService.createNotificationWithIdNotification(idn,
					users);
		}
		String vista = "";

		if (dto.getStatus().equals("PRO"))
			vista = flowToScheduleView + "?idt=" + id_travel;
		else if (dto.getStatus().equals("CRE"))
			vista = flowToCreateView;

		logger.debug("vista=" + vista);

		return new ModelAndView(vista);
	}

}
