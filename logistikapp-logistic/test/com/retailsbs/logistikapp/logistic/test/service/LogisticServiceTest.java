package com.retailsbs.logistikapp.logistic.test.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.retailsbs.logistikapp.logistic.domain.Route;
import com.retailsbs.logistikapp.logistic.domain.RouteStore;
import com.retailsbs.logistikapp.logistic.domain.Travel;
import com.retailsbs.logistikapp.logistic.domain.UserPosition;
import com.retailsbs.logistikapp.logistic.domain.WayBill;
import com.retailsbs.logistikapp.logistic.dto.AvailableUserScheduleSearchCriteria;
import com.retailsbs.logistikapp.logistic.dto.AviableCodeRouteSearchCriteria;
import com.retailsbs.logistikapp.logistic.dto.CountRouteSupplierCriteria;
import com.retailsbs.logistikapp.logistic.dto.CustomersNotVisitedCriteria;
import com.retailsbs.logistikapp.logistic.dto.CustomersNotVisitedDTO;
import com.retailsbs.logistikapp.logistic.dto.InfoReportPromotion;
import com.retailsbs.logistikapp.logistic.dto.MetricsCounterCriteria;
import com.retailsbs.logistikapp.logistic.dto.MetricsCounterDTO;
import com.retailsbs.logistikapp.logistic.dto.MultipleUsersDTO;
import com.retailsbs.logistikapp.logistic.dto.ParameterReportPromotion;
import com.retailsbs.logistikapp.logistic.dto.ProgressMetricsOperativesCriteria;
import com.retailsbs.logistikapp.logistic.dto.ProgressMetricsOperativesDTO;
import com.retailsbs.logistikapp.logistic.dto.RoutesByListRetails;
import com.retailsbs.logistikapp.logistic.dto.StoresNotVisitedByDateAndSupplierCriteria;
import com.retailsbs.logistikapp.logistic.dto.StoresNotVisitedByDateAndSupplierDTO;
import com.retailsbs.logistikapp.logistic.dto.TravelAndStatusStr;
import com.retailsbs.logistikapp.logistic.dto.TravelByIdStoreDTO;
import com.retailsbs.logistikapp.logistic.dto.TravelByIdStoreSearch;
import com.retailsbs.logistikapp.logistic.dto.TravelDTO;
import com.retailsbs.logistikapp.logistic.dto.TravelSearchCriteria;
import com.retailsbs.logistikapp.logistic.dto.TravelsToVisitedCriteria;
import com.retailsbs.logistikapp.logistic.dto.TrvStatusStrSearchCriteria;
import com.retailsbs.logistikapp.logistic.dto.WayBillDTO;
import com.retailsbs.logistikapp.logistic.dto.WayBillSearchCriteria;
import com.retailsbs.logistikapp.logistic.exception.CodeRouteDuplicateException;
import com.retailsbs.logistikapp.logistic.exception.RouteNotFoundException;
import com.retailsbs.logistikapp.logistic.exception.RouteStoreNotFoundException;
import com.retailsbs.logistikapp.logistic.exception.TravelNotFoundException;
import com.retailsbs.logistikapp.logistic.exception.UserScheduleNotAvailableException;
import com.retailsbs.logistikapp.logistic.exception.WayBillNotFoundException;
import com.retailsbs.logistikapp.logistic.service.LogisticService;
import com.retailsbs.logistikapp.logistic.test.base.BaseTestJunit;

public class LogisticServiceTest extends BaseTestJunit {

	@Autowired
	private LogisticService service;

	/*
	 * route
	 */

	public void test_addRoute() {
		Route record = new Route();
		record.setName("Ruta prueba 1");
		record.setCode("abcd");
		record.setColor("#000000");
		record.setId_supplier(1L);
		record.setStatus("CRT");
		record.setLog_created(new Date());
		record.setLog_created_login("nath");
		Long id = service.addRoute(record);
		logger.debug("Se inserto --> " + id + " Route OK");
	}

	public void test_updRouteById() throws RouteNotFoundException {
		Route record = new Route();
		record.setName("Ruta prueba 1 cambiada");
		record.setId_route(1L);

		int id = service.updRouteById(record);
		logger.debug("Se actualizaron  --> " + id + " registros OK");
	}

	public void test_getRouteById() throws RouteNotFoundException {
		Route id = service.getRouteById(1L);
		logger.debug("Se seleccionaron  --> " + id + " registros OK");
	}

	public void test_getAllRouteByIdSupplier() {
		List<Route> id = service.getAllRouteByIdSupplier(1L);
		logger.debug("Se seleccionaron  --> " + id.size() + " registros OK");
	}

	public void test_addRouteStore() {
		RouteStore record = new RouteStore();
		record.setId_route(1L);
		record.setId_store(1L);
		record.setLog_created(new Date());
		record.setLog_created_login("nath");
		Long id = service.addRouteStore(record);

		logger.debug("Se inserto --> " + id + " RouteStore OK");
	}

	/*
	 * finder route
	 */

	public void test_delRouteByIdSupplier() {
		int row = service.delRouteByIdSupplier(2L);
		assertTrue(row > 0);
	}

	/*
	 * route store
	 */

	public void test_updRouteStoreById() throws RouteStoreNotFoundException {
		RouteStore record = new RouteStore();
		record.setId_store(2L);
		record.setId_route_store(1L);
		int id = service.updRouteStoreById(record);
		logger.debug("Se actualizaron  --> " + id + " registros OK");
	}

	public void test_getRouteStoreById() throws RouteStoreNotFoundException {
		RouteStore id = service.getRouteStoreById(1L);
		logger.debug("Se seleccionaron  --> " + id + " registros OK");
	}

	public void test_getAllRouteStore() {
		List<RouteStore> id = service.getAllRouteStore();
		logger.debug("Se seleccionaron  --> " + id.size() + " registros OK");
	}

	/**
	 * travel
	 */

	public void addTravel() {

		Travel record = new Travel();
		record.setComment("Este es un travel de prueba");
		record.setName("Travel prueba 1");
		record.setId_route(1L);
		record.setId_user(5L);
		record.setStatus("CRT");
		record.setLog_created(new Date());
		record.setLog_created_login("nath");
		Long id = service.addTravel(record);

		logger.debug("Se inserto --> " + id + " Route OK");
	}

	public void test_updTravelById() throws TravelNotFoundException {

		Travel record = new Travel();
		record.setName("Travel prueba 1 cambiada");
		record.setId_travel(1L);
		int id = service.updTravelById(record);
		logger.debug("Se actualizaron  --> " + id + " registros OK");
	}

	public void test_getTravelById() throws TravelNotFoundException {
		Travel id = service.getTravelById(1L);
		logger.debug("Se seleccionaron  --> " + id + " registros OK");
	}

	public void test_delTravelById() throws TravelNotFoundException {
		int row = service.delTravelById(83L);
		assertTrue(row > 0);
	}

	public void test_getAllTravel() {

		List<Travel> id = service.getAllTravel();
		logger.debug("Se seleccionaron  --> " + id.size() + " registros OK");
	}

	public void test_getTravelByIdRoute() {
		List<Travel> list = service.getTravelByIdRoute(34L);
		assertNotNull(list);
	}

	public void test_getTravelByIdUser() {
		List<Travel> list = service.getTravelByIdUser(13L);
		assertNotNull(list);
	}

	/**
	 * waybill
	 */
	public void test_addWayBill() {
		WayBill record = new WayBill();
		record.setComment("asdadadsadadas");
		record.setId_store(18180L);
		record.setId_travel(523L);
		record.setStatus("N");
		record.setLog_created(new Date());
		record.setLog_created_login("dmarin");
		record.setNote("Esta es una nota de prueba");
		Long id = service.addWayBill(record);

		logger.debug("Se inserto --> " + id + " Route OK");
	}
	
	public void test_updWayBillById() throws WayBillNotFoundException {

		WayBill record = new WayBill();
		record.setStatus("N");
		record.setId_waybill(184277L);
		record.setLatitude(null);
		record.setLongitude(null);
		record.setCheckin(null);
		record.setStatus("N");
		int id = service.updWayBillById(record);
		assertTrue(id > 0);
	}

	public void test_getWayBillById() throws WayBillNotFoundException {
		WayBill id = service.getWayBillById(1L);
		logger.debug("Se seleccionaron  --> " + id + " registros OK");
	}

	public void test_getAllWayBill() {

		List<WayBill> id = service.getAllWayBill();
		logger.debug("Se seleccionaron  --> " + id.size() + " registros OK");
	}

	public void test_delWayBillById() throws WayBillNotFoundException {

		int r = service.delWayBillById(1L);
		logger.debug("Se eliminaron --> " + r + " registros OK");
	}

	public void test_updateRouteStoreByList() {
		List<Long> oldList = new ArrayList<Long>();
		List<Long> newList = new ArrayList<Long>();

		oldList.add(34L);
		oldList.add(28L);
		oldList.add(30L);
		oldList.add(49L);
		oldList.add(25L);
		oldList.add(50L);
		oldList.add(26L);
		oldList.add(43L);
		oldList.add(39L);
		oldList.add(46L);
		oldList.add(22L);
		oldList.add(41L);
		oldList.add(17L);
		oldList.add(37L);
		oldList.add(110L);

		newList.add(395L);
		newList.add(28L);
		newList.add(30L);
		newList.add(49L);
		newList.add(25L);
		newList.add(714L);
		newList.add(665L);
		newList.add(26L);
		newList.add(43L);
		newList.add(39L);
		newList.add(46L);
		newList.add(22L);
		newList.add(41L);
		newList.add(17L);
		newList.add(37L);
		newList.add(110L);

		Long id_route = (long) 17;
		String log_created_login = "cheko";

		int r = service.updateRouteStoreByList(oldList, newList, id_route,
				log_created_login);

		logger.debug("Se Modificaron --> " + r + " registros OK");
	}

	/*
	 * finder travel
	 */
	public void test_getTravelByCriteria() throws ParseException {
		SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat sd2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

		Date ini = new Date();
		String fini = sd.format(ini) + " 00:00:00";
		// String ffin = sd.format(ini)+" 23:59:59";

		List<String> status = new ArrayList<String>();
		status.add("CRE");
		status.add("PRO");
		status.add("TRA");

		TravelSearchCriteria dto = new TravelSearchCriteria();

		dto.setId_supplier(1L);
		dto.setFini(sd2.parse(fini));
		dto.setStatus(status);

		List<TravelDTO> list = service.getTravelByCriteria(dto);

		assertNotNull(list);
		logger.debug("Se obtuvieron --> " + list.size()
				+ " registros TravelDTO OK");
	}

	public void test_getTravelByIdStore() throws ParseException {
		SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");
		String schedule = "10-10-2015";
		TravelByIdStoreSearch dto = new TravelByIdStoreSearch();
		dto.setId_store(7L);
		dto.setSchedule(sd.parse(schedule));

		List<TravelByIdStoreDTO> list = service.getTravelByIdStore(dto);
		assertNotNull(list);
	}

	public void test_getTravelAndStatusStrByIdStore() {
		TrvStatusStrSearchCriteria dto = new TrvStatusStrSearchCriteria();
		dto.setId_store(18129L);
		dto.setLim_inf(1);
		dto.setLim_sup(3);

		List<TravelAndStatusStr> list = service
				.getTravelAndStatusStrByIdStore(dto);
		for (TravelAndStatusStr i : list) {
			System.out.println("---> " + i.getImage1());
		}
	}

	public void test_delTravelByIdRoute() {
		int row = service.delTravelByIdRoute(1L);
		assertTrue(row > 0);
	}

	public void test_getUserAvailableSchedule() {

		AvailableUserScheduleSearchCriteria dto = new AvailableUserScheduleSearchCriteria();
		dto.setId_user(4L);
		dto.setSchedule(new Date());
		try {
			service.getAvailableUserSchedule(dto);
		} catch (UserScheduleNotAvailableException e) {
			logger.debug(e.getMessage());
		}
	}

	public void test_delTravelByIdUser() {
		int row = service.delTravelByIdUser(4L);
		assertTrue(row > 0);
	}
	
	@Test
	public void test_getTravelsToVisited(){
		TravelsToVisitedCriteria criteria = new TravelsToVisitedCriteria();
		criteria.setDate(new Date());
		criteria.setId_supplier(166l);
		List<Travel> list = service.getTravelsToVisited(criteria);
		assertNotNull("NotNull", list);
		assertTrue("True", list.size() > 0);
	}

	/*
	 * finder waybill
	 */
	public void test_getWayBillByIdTravel() {
		List<WayBillDTO> lista = service.getWayBillByIdTravel(178L);
		for (int i = 0; i < lista.size(); i++) {
			System.out.println(lista.get(i).getOutrange() + " "
					+ lista.get(i).getMap_range());
		}
		assertNotNull(lista);
	}

	public void test_getWayBillByCriteria() {
		WayBillSearchCriteria dto = new WayBillSearchCriteria();
		dto.setId_travel(73L);
		dto.setId_store(10L);

		List<WayBillDTO> list = service.getWayBillByCriteria(dto);
		assertNotNull(list);
	}

	public void test_getAvaibleCodeRouteByCriteria()
			throws CodeRouteDuplicateException {
		AviableCodeRouteSearchCriteria dto = new AviableCodeRouteSearchCriteria();
		dto.setCode("hjk");
		dto.setId_route(22L);

		service.getAvaibleCodeRouteByCriteria(dto);
	}

	public void test_delRouteStoreByIdRoute() {
		int row = service.delRouteStoreByIdRoute(27L);
		assertTrue(row > 0);
	}

	public void test_delWaybillByIdStore() {
		int row = service.delWaybillByIdStore(174L);
		assertTrue(row > 0);
	}

	public void test_delRouteStoreByIdStore() {
		int row = service.delRouteStoreByIdStore(264L);
		assertTrue(row > 0);
	}

	public void getCountRouteSupplierByCriteria() {

		List<String> status = new ArrayList<String>();
		status.add("ACT");

		CountRouteSupplierCriteria dto = new CountRouteSupplierCriteria();
		dto.setId_supplier(110L);
		dto.setStatus(status);
		int rows = service.getCountRouteSupplierByCriteria(dto);
		assertTrue("Total de rutas", rows > 0);
	}

	public void getMetricsCounter() {

		// Estado de rutas
		List<String> statusRoute = new ArrayList<String>();
		statusRoute.add("ACT");

		// Estado de viajes
		List<String> statusTravel = new ArrayList<String>();
		statusTravel.add("PRO");
		statusTravel.add("REV");
		statusTravel.add("TRA");
		statusTravel.add("CAN");
		statusTravel.add("FIN");

		Date schedule = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			schedule = sdf.parse("05/08/2016");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		MetricsCounterCriteria dtos = new MetricsCounterCriteria();
		dtos.setId_supplier((long) 166);
		dtos.setId_user((long) 225);
		dtos.setStatusOne("S");
		dtos.setStatusTwo("N");
		dtos.setSchedule(schedule);
		dtos.setStatusTravel(statusTravel);
		dtos.setStatusRoute(statusRoute);

		MetricsCounterDTO metricsCounterDTO = service.getMetricsCounter(dtos);
		System.out.println("Quantity: " + metricsCounterDTO.getQty()
				+ "\nTotal viajes: " + metricsCounterDTO.getTotal()
				+ "\nVisitados: " + metricsCounterDTO.getVisited()
				+ "\nNo Visitados: " + metricsCounterDTO.getNo_visited());

		assertNotNull(metricsCounterDTO);
	}

	public void getTravelByCriteria() {
		Date fini = null;
		Date ffin = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		try {
			fini = sdf.parse("01/02/2016 00:00:00");
			ffin = sdf.parse("31/03/2016 23:59:59");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		List<String> status = new ArrayList<String>();
		status.add("CRE");
		status.add("PRO");
		status.add("REV");
		status.add("TRA");

		TravelSearchCriteria dto = new TravelSearchCriteria();
		dto.setId_supplier(110L);
		dto.setFini(fini);
		dto.setFfin(ffin);
		dto.setStatus(status);
		// dto.setId_user(175L);
		// dto.setId_route(1L);

		List<TravelDTO> list = service.getTravelByCriteria(dto);
		assertNotNull(list);
	}

	public void updateWaybillComment() throws WayBillNotFoundException {
		WayBill way = new WayBill();
		way.setId_waybill(7316l);
		way.setComment("Esta es una prueba de comentario");
		int i = service.updWayBillById(way);
		assertTrue(i > 0);
	}

	public void test_getTravelWithPromotion() throws ParseException {
		ParameterReportPromotion data = new ParameterReportPromotion();
		data.setId_retail(230l);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date fini = sdf.parse("2016-01-01");
		Date ffin = sdf.parse("2016-07-07");
		System.out.println(sdf.format(fini));
		System.out.println(sdf.format(ffin));
		data.setFini(fini);
		data.setFfin(ffin);
		System.out.println(data);
		List<InfoReportPromotion> list = service.getTravelWithPromotion(data);
		for (InfoReportPromotion i : list) {
			System.out.println(i.getCode());
		}
	}

	public void test_getRouteByListRetail() {

		List<Integer> ids = new LinkedList<Integer>();
		ids.add(122);
		ids.add(130);
		RoutesByListRetails dto = new RoutesByListRetails();
		dto.setId_routes(ids);
		List<Route> list = service.getRouteByListRetail(dto);
		for (Route i : list) {
			System.out.println("--> " + i.getName() + " - " + i.getColor());
		}

	}

	public void test_getIdsRouteByIdTravel() {
		List<Long> ids = service.getIdsRouteByIdTravel(545l);
		System.out.println("--> " + ids);
	}

	public void test_getRoutesByIdTravel() {
		List<Route> list = service.getRoutesByIdTravel(545l);
		for (Route i : list) {
			System.out.println("--> " + i.getName() + " - " + i.getColor());
		}
	}

	public void test_addWayBillMultiple() {

		MultipleUsersDTO dto = new MultipleUsersDTO();
		List<WayBill> info = new ArrayList<WayBill>();
		WayBill datos;

		for (int i = 19; i < 22; i++) {
			datos = new WayBill();
			datos.setId_travel(414L);
			datos.setLog_created(new Date());
			datos.setLog_created_login("logistikapp");
			datos.setStatus("N");
			datos.setOrderby(i);
			datos.setId_store((long) i);

			info.add(datos);
		}

		// Agregamos la lista con la información del waybill
		dto.setClientsToAdd(info);

		int total = service.addWayBill(dto);

		System.out.println("Se agregaron " + total + " Clientes al viaje");

		assertNotNull(total);

	}
	
	/*
	 * UserPosition
	 */
	
	public void test_getUserPositionByIdUser(){
		List<UserPosition> list = service.getUserPositionByIdUser(225l);
		for (UserPosition up : list) {
			System.out.println("---> " + up.getId_user() + " " + up.getLatitude() + " - " + up.getLongitude());
		}
		assertTrue(list.size() > 0);
	}
	
	
	public void test_getUserPositionByIdUserAndDate(){
		List<UserPosition> list = service.getUserPositionByIdUserAndDate(225l, new Date());
		for (UserPosition up : list) {
			System.out.println("---> " + up.getId_user() + " " + up.getLatitude() + " - " + up.getLongitude());
		}
		assertNotNull(list);
	}
	
	public void test_getUPByIdTravel() {
		List<UserPosition> list = service.getUserPositionByIdTravel(3892l);
		for (UserPosition up : list) {
			System.out.println("---> " + up.getId_user() + " " + up.getLatitude() + " - " + up.getLongitude());
		}
		assertNotNull(list);
	}
	
	public void test() {
		Travel travel = service.getLastTravelTRAorFIN(398l);
		
		if (travel != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			if (travel.getStatus().contentEquals("FIN")) System.out.println("VIAJE FINALIZADO: " + travel.getName() + " - " + sdf.format(travel.getFinished()) );
			else System.out.println("VIAJE INICIADO: " + travel.getId_travel() + " - " + travel.getName() + " - " + sdf.format(travel.getStarted()) );
        } else {
        	System.out.println("== SIN VIAJES == ");
        }
		
	}
	
	public void test_getCustomersNotVisited() {
		CustomersNotVisitedCriteria criteria = new CustomersNotVisitedCriteria();
		criteria.setId_supplier(166l);
		
		List<CustomersNotVisitedDTO> list = service.getCustomersNotVisited(criteria);
		assertNotNull(list);
		assertTrue(list.size() == 71);
	}
	
	public void test_getProgressMetricsOperatives() {
		ProgressMetricsOperativesCriteria criteria = new ProgressMetricsOperativesCriteria();
		criteria.setFfin(new Date());
		criteria.setFini(new Date());
		criteria.setId_supplier(166l);
		List<ProgressMetricsOperativesDTO> list = service.getProgressMetricsOperatives(criteria);
		assertNotNull("Se valida que no sea null el listado", list);
		assertTrue("Se valida el total de registros", list.size() == 1);
	}
	
	public void test_getSuppliersWithStoresNotVisited() {
		
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, -1);
		
		List<Long> list = service.getSuppliersWithStoresNotVisited(calendar.getTime());
		assertNotNull(list);
		assertTrue(list.size() > 0);
		
	}
	
	public void test_getStoresNotVisitedByDateAndSupplier() {
		
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, -1);
	
		StoresNotVisitedByDateAndSupplierCriteria criteria = new StoresNotVisitedByDateAndSupplierCriteria();
		criteria.setDate(calendar.getTime());
		criteria.setId_supplier(166l);
		
		List<StoresNotVisitedByDateAndSupplierDTO> list = service.getStoresNotVisitedByDateAndSupplier(criteria);
		assertNotNull(list);
		assertTrue(list.size() > 0);
		
	}

}
