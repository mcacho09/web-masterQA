package com.retailsbs.logistikapp.manager.logistic.test.service;
import static org.junit.Assert.assertNotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.retailsbs.logistikapp.logistic.exception.RouteNotFoundException;
import com.retailsbs.logistikapp.manager.logistic.ManagerLogisticService;
import com.retailsbs.logistikapp.manager.logistic.dto.RouteStoreDTO;
import com.retailsbs.logistikapp.manager.logistic.dto.StoreByNameAddressLogistic;
import com.retailsbs.logistikapp.manager.logistic.dto.StoreInfoTravelDTO;
import com.retailsbs.logistikapp.manager.logistic.dto.StoreTravelDTO;
import com.retailsbs.logistikapp.manager.logistic.test.base.BaseTestJunit;
import com.retailsbs.logistikapp.retail.dto.StoreInfoSearchCriteria;



public class LogisticServiceTest extends BaseTestJunit {

	@Autowired
	private ManagerLogisticService service;
	
	public void test_getRouteStoreByIdSuplier() {
		
		List<RouteStoreDTO> list = service.getRouteStoreByIdSuplier(1L);
		
		assertNotNull(list);
	}
	public void test_getRouteStoreByIdRoute() throws RouteNotFoundException {
		
		RouteStoreDTO dto = service.getRouteStoreByIdRoute(16L);
		
		assertNotNull(dto);
	}
	
	public void test_getStoreByNameAddressAndTravels() throws ParseException {
		SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");
		String today = "10-10-2015";
		
		StoreByNameAddressLogistic dto = new StoreByNameAddressLogistic();
		dto.setDireccion("mar");
		dto.setId_supplier(1L);
//		dto.setNombre("panade");
		dto.setSchedule(sd.parse(today));
		
		List<StoreTravelDTO> list = service.getStoreByNameAddressAndTravels(dto);
		assertNotNull(list);
	}
	
	@Test
	public void test_getStoreInfoTravel() {
		StoreInfoSearchCriteria dto = new StoreInfoSearchCriteria();
		dto.setActive("S");
		dto.setId_supplier(134L);
		dto.setStore_name("comercio");
		
		List<StoreInfoTravelDTO> list = service.getStoreInfoTravel(dto);
		assertNotNull(list);
	}
}
