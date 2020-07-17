package com.retailsbs.logistikapp.web.ctrl.home;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContextHolder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.retailsbs.logistikapp.financial.dto.GetMetricsSaleDTO;
import com.retailsbs.logistikapp.financial.dto.MetricsSaleDTO;
import com.retailsbs.logistikapp.financial.dto.TopClientsCriteria;
import com.retailsbs.logistikapp.financial.dto.TopClientsDTO;
import com.retailsbs.logistikapp.financial.service.FinancialService;
import com.retailsbs.logistikapp.logistic.dto.CountRouteSupplierCriteria;
import com.retailsbs.logistikapp.logistic.dto.MetricsCounterCriteria;
import com.retailsbs.logistikapp.logistic.dto.MetricsCounterDTO;
import com.retailsbs.logistikapp.logistic.dto.ProgressMetricsOperativesCriteria;
import com.retailsbs.logistikapp.logistic.dto.ProgressMetricsOperativesDTO;
import com.retailsbs.logistikapp.logistic.service.LogisticService;
import com.retailsbs.logistikapp.retail.dto.StoreByCategoryDTO;
import com.retailsbs.logistikapp.retail.dto.StoreByRetailDTO;
import com.retailsbs.logistikapp.retail.service.RetailService;
import com.retailsbs.logistikapp.supplier.domain.Plan;
import com.retailsbs.logistikapp.supplier.domain.Supplier;
import com.retailsbs.logistikapp.supplier.service.SupplierService;
import com.retailsbs.logistikapp.user.domain.Access;
import com.retailsbs.logistikapp.user.domain.Quantities;
import com.retailsbs.logistikapp.user.domain.UserAcegi;
import com.retailsbs.logistikapp.user.dto.MetricsAdm;
import com.retailsbs.logistikapp.user.dto.NotificationTodaySearchCriteria;
import com.retailsbs.logistikapp.user.service.UserService;

/**
 * Class controller para la vista de home (dashboard)
 * 
 * @author jorge
 * @since 24/01/2016
 */
public class HomeViewCtrl implements Controller {

	protected final Log logger = LogFactory.getLog(getClass());

	private String viewsup; // Vista para usuario supplier, retail, store
	private String viewadm; // Vista para usuario admin
	private String viewdem; // Vista para usuario demo
	private String viewdri; // Vista para usuario driver
	private UserService userService;
	private RetailService retailService;
	private LogisticService logisticService;
	private SupplierService supplierService;
	private String PERSONAL;
	private String GLOBAL;
	private FinancialService financialService;

	public void setViewsup(String viewsup) {
		this.viewsup = viewsup;
	}

	public void setViewadm(String viewadm) {
		this.viewadm = viewadm;
	}

	public void setViewdem(String viewdem) {
		this.viewdem = viewdem;
	}

	public void setViewdri(String viewdri) {
		this.viewdri = viewdri;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setRetailService(RetailService retailService) {
		this.retailService = retailService;
	}

	public void setLogisticService(LogisticService logisticService) {
		this.logisticService = logisticService;
	}

	public void setSupplierService(SupplierService supplierService) {
		this.supplierService = supplierService;
	}

	public void setPERSONAL(String pERSONAL) {
		PERSONAL = pERSONAL;
	}

	public void setGLOBAL(String gLOBAL) {
		GLOBAL = gLOBAL;
	}

	public void setFinancialService(FinancialService financialService) {
		this.financialService = financialService;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		logger.debug("---- USER-CONTEXT ----");

		// -------------------------------------------------------
		// Se obtiene el contexto del usuario
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		UserAcegi useracegi = (UserAcegi) auth.getPrincipal();
		logger.debug("HOME ==> id_user=" + useracegi.getId_user());
		logger.debug("HOME ==> profile=" + useracegi.getProfile());
		Long id_user = useracegi.getId_user();
		Long id_supplier = null;
		Plan userplan = null;
		Access useraccess = null;
		Supplier supplier_obj = null;

		// Se obtienen datos asociados al supplier para todos los usuarios
		// menos para los usuarios con perfil administrador
		logger.debug("---->" + useracegi.getProfile());
		if (!useracegi.getProfile().equals("ADM")
				|| !useracegi.getProfile().equals("SOP")) {

			// Se obtienen los datos de acceso para el usuario
			List<Access> accesslist = userService.getAccessByIdUser(id_user);
			// Se obtiene el primer acceso por defecto
			useraccess = accesslist.get(0);
			// Se obtiene el id del supplier
			id_supplier = useraccess.getId_supplier();
			supplier_obj = supplierService.getSupplierById(id_supplier);
			logger.debug("HOME ==> id_supplier=" + id_supplier);
			// Se obtiene informacion del plan de usuarios
			userplan = supplierService.getPlanById(supplier_obj.getId_plan());
			logger.debug("HOME ==> userplan=" + userplan.getPlan_name());
		}

		// -------------------------------------------------------
		// Dependiendo del perfil de usuario se define la vista que se utilizará
		String vista = null;
		if (useracegi.getProfile().equals("ADM")
				|| useracegi.getProfile().equals("SOP"))
			vista = viewadm;
		else if (useracegi.getProfile().equals("DEM"))
			vista = viewdem;
		/*
		 * else if (useracegi.getProfile().contains("DRI")) vista = viewdri;
		 */
		else
			vista = viewsup;
		logger.debug("HOME ==> view=" + vista);
		// -------------------------------------------------------
		// Se define el objeto para enviar datos a la vista
		Map<String, Object> model = new HashMap<String, Object>();

		model.put("useracegi", useracegi);
		model.put("userplan", userplan);
		model.put("useraccess", useraccess);
		model.put("supplier_obj", supplier_obj);
		model.put("PERSONAL", PERSONAL);
		model.put("GLOBAL", GLOBAL);

		if (!useracegi.getProfile().equals("ADM")) {
			// Se obtienen las notificaciones para el usuario
			NotificationTodaySearchCriteria dtoqty = new NotificationTodaySearchCriteria();
			dtoqty.setProfile(useracegi.getProfile());
			dtoqty.setFecha(new Date());
			dtoqty.setId_supplier(useraccess.getId_supplier());
			dtoqty.setId_user(useracegi.getId_user());

			Quantities nttdto = userService
					.getQtyAlertAndMessagesByIdUser(dtoqty);
			// HeaderNotificationDTO nttdto =
			// userService.getHeaderNotificationByIdUser(useracegi.getId_user());
			logger.debug("HEADER ==> alert=" + nttdto.getAlert_qty()
					+ " | message=" + nttdto.getMessage_qty() + " | todo="
					+ nttdto.getTodo_qty());

			// Se envian objetos a la vista
			model.put("nttdto", nttdto);
		}

		// -------------------------------------------------------
		// Se obtienen datos para los usuarios que no sean administradores
		// Se controla que no sea un usuario con perfil SUP
		if (!useracegi.getProfile().equals("ADM")) {

			// Se define la fecha para la sección de metricas operativas
			Date schedule = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if (arg0.getParameter("schedule") != null
					&& !arg0.getParameter("schedule").equals("")) {
				schedule = sdf.parse(arg0.getParameter("schedule"));
			} else {
				// Por defecto la fecha es la actual
				schedule = new Date();
			}
			logger.debug("HEADER ==> schedule=" + sdf.format(schedule));

			// Se obtiene el total de comercios por proveedor
			int storeQty = retailService.getTotalStoreByIdSupplier(id_supplier);
			logger.debug("HEADER ==> storeQty=" + storeQty);

			// Se define criterio de busqueda para obtener el total de rutas
			List<String> statusRoute = new ArrayList<String>();
			statusRoute.add("ACT");
			CountRouteSupplierCriteria dtort = new CountRouteSupplierCriteria();
			dtort.setId_supplier(id_supplier);
			dtort.setStatus(statusRoute);
			// Se obtiene total de rutas por proveedor
			int routeQty = logisticService
					.getCountRouteSupplierByCriteria(dtort);
			logger.debug("HEADER ==> routeQty=" + routeQty);

			// Se obtiene el total de usuarios asociados al proveedor
			int userQty = userService.getCountUserBySupplier(id_supplier);
			logger.debug("HEADER ==> userQty=" + userQty);

			// Se obtiene una lista de retail y la cantidad de stores para cada
			// uno
			List<StoreByRetailDTO> retailList = retailService
					.getStoreByRetail(id_supplier);
			logger.debug("HEADER ==> retailList=" + retailList.size());

			// Se obtiene una lista de categorystore y la cantidad de stores
			// para cada uno
			List<StoreByCategoryDTO> categoryList = retailService
					.getStoreByCategory(id_supplier);
			logger.debug("HEADER ==> categoryList=" + categoryList.size());

			// Se define criterio de busqueda para obtener el total de viajes
			// a partir de la fecha seleccionada en la seccion de metricas
			// operativas
			List<String> statusTravel = new ArrayList<String>();
			statusTravel.add("PRO");
			// statusTravel.add("REV");
			statusTravel.add("TRA");
			// statusTravel.add("CAN");
			statusTravel.add("FIN");

			MetricsCounterCriteria dtomc = new MetricsCounterCriteria();
			dtomc.setSchedule(schedule);
			dtomc.setScheduleFin(schedule);
			dtomc.setStatusRoute(statusRoute);
			dtomc.setStatusTravel(statusTravel);
			dtomc.setStatusOne("S");
			dtomc.setStatusTwo("N");
			dtomc.setId_supplier(id_supplier);

			String perfil = useracegi.getProfile();

			// Se cambia para adaptación con planes
			// if (useracegi.getProfile().equals("DRI"))
			if (useracegi.getProfile().contains("DRI"))
				dtomc.setId_user(id_user);

			MetricsCounterDTO metricsCounterDTO = logisticService
					.getMetricsCounter(dtomc);
			int travelQty = 0;
			int travelStoreQty = 0;
			int travelStoreQtyCheck = 0;
			int travelStoreQtyNoCheck = 0;

			// Se obtiene la cantidad de viajes asociados al tipo de perfil
			travelQty = metricsCounterDTO.getQty();
			logger.debug("CONTROL ==> travelQty = " + travelQty);

			// Se obtiene la cantidad de stores asociados a los viajes
			// de la fecha selecciona
			travelStoreQty = metricsCounterDTO.getTotal();
			logger.debug("CONTROL ==> travelStoreQty = " + travelStoreQty);

			// Se obtiene la cantidad de stores visitados asociados a los viajes
			// de la fecha selecciona
			travelStoreQtyCheck = metricsCounterDTO.getVisited();
			logger.debug("CONTROL ==> travelStoreQtyCheck = "
					+ travelStoreQtyCheck);

			// Se obtiene la cantidad de stores no-visitados asociados a los
			// viajes
			// de la fecha selecciona
			travelStoreQtyNoCheck = metricsCounterDTO.getNo_visited();
			logger.debug("CONTROL ==> travelStoreQtyNoCheck = "
					+ travelStoreQtyNoCheck);

			// Se obtiene el listado de eventos del calendario del usuario
			/*EventSearchCriteria dto = new EventSearchCriteria();
			dto.setId_supplier(id_supplier);
			dto.setId_user(id_user);
			List<CalendarDTO> events = userService.getEventByCriteria(dto);
			logger.debug("HEADER ==> events=" + events.size());*/

			// Se obtiene top 10 de clientes
			TopClientsCriteria criteriaTC = new TopClientsCriteria();
			criteriaTC.setId_supplier(id_supplier);
			criteriaTC.setFini(new Date());
			criteriaTC.setFfin(new Date());

			if (useracegi.getProfile().contains("DRI")) {
				criteriaTC.setId_user(useracegi.getId_user());
			}

			List<TopClientsDTO> list_top_clients = financialService
					.getTopClients(criteriaTC);

			// Se obtienen las metricas para ventas
			GetMetricsSaleDTO criteria = new GetMetricsSaleDTO();
			criteria.setId_supplier(id_supplier);
			criteria.setInitDate(new Date());
			criteria.setFinalDate(new Date());
			MetricsSaleDTO metricsSale = financialService
					.getMetricsSale(criteria);

			/******
			 * Se obtiene el progreso de categorias visitadas respecto a la
			 * fecha indicada
			 *****/
			ProgressMetricsOperativesCriteria criteriaPMO = new ProgressMetricsOperativesCriteria();
			criteriaPMO.setId_supplier(id_supplier);
			criteriaPMO.setFini(new Date());
			criteriaPMO.setFfin(new Date());
			if (useracegi.getProfile().contains("DRI")) {
				criteriaPMO.setId_user(id_user);
			}

			List<ProgressMetricsOperativesDTO> listPMO = logisticService
					.getProgressMetricsOperatives(criteriaPMO);

			model.put("storeQty", storeQty);
			model.put("routeQty", routeQty);
			model.put("userQty", userQty);

			model.put("schedule", sdf.format(schedule));
			model.put("retailList", retailList);
			model.put("categoryList", categoryList);

			model.put("travelQty", travelQty);
			model.put("travelStoreQty", travelStoreQty);
			model.put("travelStoreQtyCheck", travelStoreQtyCheck);
			model.put("travelStoreQtyNoCheck", travelStoreQtyNoCheck);

			//model.put("events", events);

			model.put("id_supplier", id_supplier);
			model.put("perfil", perfil);
			model.put("id_user", id_user);
			model.put("metricsSale", metricsSale);
			model.put("list_top_clients", list_top_clients);

			model.put("listPMO", listPMO);
		}
		
		if (useracegi.getProfile().contains("ADM") || useracegi.getProfile().contains("SOP")) {
			MetricsAdm metricsAdm = userService.getMetricsAdm();
			model.put("metricsAdm", metricsAdm);
			MetricsSaleDTO metricsSaleAdm = financialService.getMetricsSale(new GetMetricsSaleDTO());
			model.put("metricsSaleAdm", metricsSaleAdm);
		}

		return new ModelAndView(vista, model);
	}

}
