package com.retailsbs.logistikapp.retail.test.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.retailsbs.logistikapp.retail.domain.ActivesVisited;
import com.retailsbs.logistikapp.retail.domain.City;
import com.retailsbs.logistikapp.retail.domain.Country;
import com.retailsbs.logistikapp.retail.domain.Locality;
import com.retailsbs.logistikapp.retail.domain.Retail;
import com.retailsbs.logistikapp.retail.domain.RetailCategory;
import com.retailsbs.logistikapp.retail.domain.RetailDept;
import com.retailsbs.logistikapp.retail.domain.State;
import com.retailsbs.logistikapp.retail.domain.Store;
import com.retailsbs.logistikapp.retail.domain.StoreCategory;
import com.retailsbs.logistikapp.retail.dto.ActivesAndVisitsCriteria;
import com.retailsbs.logistikapp.retail.dto.ActivesAndVisitsDTO;
import com.retailsbs.logistikapp.retail.dto.ActivesVisitedCriteria;
import com.retailsbs.logistikapp.retail.dto.ActivesVisitedExt;
import com.retailsbs.logistikapp.retail.dto.AddCityDTO;
import com.retailsbs.logistikapp.retail.dto.AddCountryDTO;
import com.retailsbs.logistikapp.retail.dto.AddLocalityDTO;
import com.retailsbs.logistikapp.retail.dto.AddRetailCategoryDTO;
import com.retailsbs.logistikapp.retail.dto.AddRetailDTO;
import com.retailsbs.logistikapp.retail.dto.AddRetailDeptDTO;
import com.retailsbs.logistikapp.retail.dto.AddStateDTO;
import com.retailsbs.logistikapp.retail.dto.AddStoreCategoryDTO;
import com.retailsbs.logistikapp.retail.dto.AddStoreDTO;
import com.retailsbs.logistikapp.retail.dto.AvailableStoreByIdRetail;
import com.retailsbs.logistikapp.retail.dto.AvailableStoreByIdRetailIdRoute;
import com.retailsbs.logistikapp.retail.dto.AvailableStoreByIdStoreCategory;
import com.retailsbs.logistikapp.retail.dto.AvailableStoreByIdStoreCategoryIdRoute;
import com.retailsbs.logistikapp.retail.dto.AvailableStoreByIdSupplierIdRoute;
import com.retailsbs.logistikapp.retail.dto.ChangeRetailCriteria;
import com.retailsbs.logistikapp.retail.dto.CityDTO;
import com.retailsbs.logistikapp.retail.dto.CountryDTO;
import com.retailsbs.logistikapp.retail.dto.CountrySearchCriteria;
import com.retailsbs.logistikapp.retail.dto.GetCitySearchCriteria;
import com.retailsbs.logistikapp.retail.dto.GetLocalitySearchCriteria;
import com.retailsbs.logistikapp.retail.dto.GetStateSearchCriteria;
import com.retailsbs.logistikapp.retail.dto.LocalityDTO;
import com.retailsbs.logistikapp.retail.dto.QtyStrCheckByRetai;
import com.retailsbs.logistikapp.retail.dto.QtyStrCheckByRetailDTO;
import com.retailsbs.logistikapp.retail.dto.RetailAvaibleSearchCriteria;
import com.retailsbs.logistikapp.retail.dto.RetailByIdsSearchCriteria;
import com.retailsbs.logistikapp.retail.dto.RetailCategoryActiveSearchCriteria;
import com.retailsbs.logistikapp.retail.dto.RetailDTO;
import com.retailsbs.logistikapp.retail.dto.RetailSearchCriteria;
import com.retailsbs.logistikapp.retail.dto.StateAvaibleSearchCriteria;
import com.retailsbs.logistikapp.retail.dto.StateDTO;
import com.retailsbs.logistikapp.retail.dto.StateQtyCityDTO;
import com.retailsbs.logistikapp.retail.dto.StoreActiveByIdsRetailSearchCriteria;
import com.retailsbs.logistikapp.retail.dto.StoreAvailableInRouteCriteria;
import com.retailsbs.logistikapp.retail.dto.StoreAvailableInTravelCriteria;
import com.retailsbs.logistikapp.retail.dto.StoreByCategoryDTO;
import com.retailsbs.logistikapp.retail.dto.AvailableStoreSearchCriteria;
import com.retailsbs.logistikapp.retail.dto.AvailableStoreDTO;
import com.retailsbs.logistikapp.retail.dto.StoreByIdsSearchCriteria;
import com.retailsbs.logistikapp.retail.dto.StoreByNameAddress;
import com.retailsbs.logistikapp.retail.dto.StoreByRetailDTO;
import com.retailsbs.logistikapp.retail.dto.StoreByRouteDTO;
import com.retailsbs.logistikapp.retail.dto.StoreCallCenterDTO;
import com.retailsbs.logistikapp.retail.dto.StoreCategoryActiveSearchCriteria;
import com.retailsbs.logistikapp.retail.dto.StoreCategorySearchCriteria;
import com.retailsbs.logistikapp.retail.dto.StoreDTO;
import com.retailsbs.logistikapp.retail.dto.StoreInfoDTO;
import com.retailsbs.logistikapp.retail.dto.StoreInfoSearchCriteria;
import com.retailsbs.logistikapp.retail.dto.StoreNotInTravelSearchCriteria;
import com.retailsbs.logistikapp.retail.dto.StoreRouteDTO;
import com.retailsbs.logistikapp.retail.dto.StoreSearchCriteria;
import com.retailsbs.logistikapp.retail.dto.TotalStoreCreateCriteria;
import com.retailsbs.logistikapp.retail.dto.UpdCityDTO;
import com.retailsbs.logistikapp.retail.dto.UpdCountryDTO;
import com.retailsbs.logistikapp.retail.dto.UpdLocalityDTO;
import com.retailsbs.logistikapp.retail.dto.UpdRetailCategoryDTO;
import com.retailsbs.logistikapp.retail.dto.UpdRetailDTO;
import com.retailsbs.logistikapp.retail.dto.UpdRetailDeptDTO;
import com.retailsbs.logistikapp.retail.dto.UpdStateDTO;
import com.retailsbs.logistikapp.retail.dto.UpdStoreCategoryDTO;
import com.retailsbs.logistikapp.retail.dto.UpdStoreDTO;
import com.retailsbs.logistikapp.retail.dto.VerifyAndRegistCheckInCriteria;
import com.retailsbs.logistikapp.retail.exception.CityNotFoundException;
import com.retailsbs.logistikapp.retail.exception.CountryNotFoundException;
import com.retailsbs.logistikapp.retail.exception.LocalityNotFoundException;
import com.retailsbs.logistikapp.retail.exception.RetailCategoryNotFoundException;
import com.retailsbs.logistikapp.retail.exception.RetailDeptNotFoundException;
import com.retailsbs.logistikapp.retail.exception.RetailDuplicadoException;
import com.retailsbs.logistikapp.retail.exception.RetailNotFoundException;
import com.retailsbs.logistikapp.retail.exception.StateDuplicateException;
import com.retailsbs.logistikapp.retail.exception.StateNotFoundException;
import com.retailsbs.logistikapp.retail.exception.StoreCategoryNotFoundException;
import com.retailsbs.logistikapp.retail.exception.StoreNotFoundException;
import com.retailsbs.logistikapp.retail.service.RetailService;
import com.retailsbs.logistikapp.retail.test.base.BaseTestJunit;

public class RetailServiceTest extends BaseTestJunit {

	@Autowired
	private RetailService service;

	/*
	 * Country
	 */
	public void test_addCountry() {
		AddCountryDTO dto = new AddCountryDTO();
		dto.setActive("S");
		dto.setCode("code");
		dto.setLogin("admin");
		dto.setModified(new Date());
		dto.setName("name");
		dto.setOrderby(2);
		dto.setPrefix("pre");

		Long id = service.addCountry(dto);
		assertNotNull(id);
	}

	public void test_updCountry() throws CountryNotFoundException {
		UpdCountryDTO dto = new UpdCountryDTO();
		dto.setId_country(3L);
		dto.setLogin("user");

		int i = service.updCountry(dto);

		assertTrue(i > 0);
	}

	public void test_getCountryById() throws CountryNotFoundException {
		Country ctry = service.getCountryById(1L);
		assertNotNull(ctry);
	}

	public void test_getAllCountry() {
		List<Country> list_ctry = service.getAllCountry();
		assertNotNull(list_ctry);
	}

	/*
	 * State
	 */

	public void test_addState() {
		AddStateDTO dto = new AddStateDTO();
		dto.setActive("S");
		dto.setCode("C");
		dto.setId_country(3L);
		dto.setLogin("admin");
		dto.setModified(new Date());
		dto.setName("state name");
		dto.setOrderby(10);
		dto.setPrefix("st");

		Long id_st = service.addState(dto);
		assertNotNull(id_st);
	}

	public void test_updState() throws StateNotFoundException {
		UpdStateDTO dto = new UpdStateDTO();
		dto.setId_state(3L);
		dto.setName("State segundo");

		int i = service.updState(dto);
		assertTrue(i > 0);
	}

	public void test_getStateById() throws StateNotFoundException {
		State st = service.getStateById(3L);
		assertNotNull(st);
	}

	public void test_getAllState() {
		List<State> list_st = service.getAllState();
		assertNotNull(list_st);
	}

	/*
	 * City
	 */

	public void test_addCity() {
		AddCityDTO dto = new AddCityDTO();
		dto.setActive("S");
		dto.setCode("C");
		dto.setId_state(1L);
		dto.setLogin("admin");
		dto.setModified(new Date());
		dto.setName("nombre");
		dto.setOrderby(10);

		Long id = service.addCity(dto);
		assertNotNull(id);
	}

	public void test_updCity() throws CityNotFoundException {
		UpdCityDTO dto = new UpdCityDTO();
		dto.setId_city(2L);
		dto.setCode("U");

		int i = service.updCity(dto);
		assertTrue(i > 0);
	}

	public void test_getCityById() throws CityNotFoundException {
		City city = service.getCityById(1L);
		assertNotNull(city);
	}

	public void test_getAllCity() {
		List<City> city = service.getAllCity();
		assertNotNull(city);
	}

	public void test_getAllStatesByIdCountry() {
		List<State> states = service.getAllStatesByIdCountry(100L);
		assertNotNull(states);
	}

	public void test_getAllCityByIdState() {
		List<City> cities = service.getAllCityByIdState(1L);
		assertNotNull(cities);
	}

	public void test_getAllLocalityByIdCity() {
		List<Locality> localities = service.getAllLocalityByIdCity(100L);
		assertNotNull(localities);
	}

	/*
	 * Locality
	 */

	public void test_addLocality() {
		AddLocalityDTO dto = new AddLocalityDTO();
		dto.setActive("A");
		dto.setCode("CS");
		dto.setId_city(1L);
		dto.setLogin("admin");
		dto.setModified(new Date());
		dto.setName("Create in service");
		dto.setOrderby(10);

		Long id = service.addLocality(dto);
		assertNotNull(id);
	}

	public void test_updLocality() throws LocalityNotFoundException {
		UpdLocalityDTO dto = new UpdLocalityDTO();
		dto.setId_locality(2L);
		dto.setCode("US");

		int i = service.updLocality(dto);
		assertTrue(i > 0);
	}

	public void test_getLocalityById() throws LocalityNotFoundException {
		Locality loc = service.getLocalityById(2L);
		assertNotNull(loc);
	}

	public void test_getAllLocality() {
		List<Locality> loc = service.getAllLocality();
		assertNotNull(loc);
	}

	/*
	 * Retail
	 */

	public void test_addRetail() {
		AddRetailDTO dto = new AddRetailDTO();
		dto.setActive("S");
		dto.setAddress1("address1");
		dto.setAddress2("address2");
		dto.setCode("SC");
		dto.setId_country(1L);
		dto.setId_state(1L);
		dto.setId_retail_category(100L);
		dto.setId_supplier(1L);
		dto.setLogin("admin");
		dto.setName("service");
		dto.setOrderby(10);
		dto.setPostal(23456);

		Long id = service.addRetail(dto);
		assertNotNull(id);
	}

	public void test_updRetail() throws RetailNotFoundException {
		UpdRetailDTO dto = new UpdRetailDTO();
		dto.setId_retail(10004L);
		dto.setName("new service again");

		int i = service.updRetail(dto);
		assertTrue(i > 0);
	}

	public void test_getRetailById() throws RetailNotFoundException {
		Retail re = service.getRetailById(10004L);
		assertNotNull(re);
	}

	public void test_getAllRetail() {
		List<Retail> re = service.getAllRetail();
		assertNotNull(re);
	}

	public void test_delRetailById() throws RetailNotFoundException {
		int row = service.delRetailById(6L);
		assertTrue(row > 0);
	}

	public void test_getRetailByIdSupplier() {
		List<Retail> list = service.getRetailByIdSupplier(1L);
		assertNotNull(list);
	}

	/*
	 * RetailDept
	 */

	public void test_addRetailDept() {
		AddRetailDeptDTO dto = new AddRetailDeptDTO();
		dto.setActive("S");
		dto.setContact("Juan Carlos");
		dto.setEmail("juancarlos@gmail.com");
		dto.setId_retail(99L);
		dto.setLogin("admin");
		dto.setName("Ventas");
		dto.setOrderby(10);
		dto.setPhone("9123456");
		dto.setCreated(new Date());

		Long id = service.addRetailDept(dto);
		assertNotNull(id);
	}

	public void test_updRetailDept() throws RetailDeptNotFoundException {
		UpdRetailDeptDTO dto = new UpdRetailDeptDTO();
		dto.setId_retail_dept(2L);
		dto.setName("service changed");

		int i = service.updRetailDept(dto);
		assertTrue(i > 0);
	}

	public void test_getRetailDeptById() throws RetailDeptNotFoundException {
		RetailDept rd = service.getRetailDeptById(2L);
		assertNotNull(rd);
	}

	public void test_getAllRetailDept() {
		List<RetailDept> re = service.getAllRetailDept();
		assertNotNull(re);
	}

	public void test_getRetailDeptsByIdRetail() {
		List<RetailDept> list = service.getRetailDeptsByIdRetail(100L);
		assertNotNull(list);
	}

	public void test_delRetailDeptById() throws RetailDeptNotFoundException {
		int row = service.delRetailDeptById(100L);
		assertTrue(row > 0);
	}

	/*
	 * finder retail dept
	 */

	public void test_delRetailDepByIdRetail() {
		int row = service.delRetailDeptByIdRetail(6L);
		assertTrue(row > 0);
	}

	/*
	 * Store
	 */

	public void test_addStore() {
		AddStoreDTO dto = new AddStoreDTO();
		dto.setId_city(1L);
		dto.setId_store_category(2L);
		dto.setId_country(1L);
		dto.setId_retail(102L);
		dto.setId_state(1L);
		dto.setActive("S");
		dto.setAddress1("address1");
		dto.setAddress2("address2");
		dto.setCode("SC");
		dto.setLatitude(12.2);
		dto.setLogin("admin");
		dto.setLongitude(21.3);
		dto.setName("name service 3");
		dto.setOrderby(10);
		dto.setPostal(23124);

		Long id = service.addStore(dto);
		assertNotNull(id);
	}

	public void test_updStore() throws StoreNotFoundException {
		UpdStoreDTO dto = new UpdStoreDTO();
		dto.setId_store(2L);
		dto.setCode("SU");

		int i = service.updStore(dto);
		assertTrue(i > 0);
	}

	public void test_getStoreById() throws StoreNotFoundException {
		Store store = service.getStoreById(2L);
		assertNotNull(store);
	}

	public void test_getAllStore() {
		List<Store> store = service.getAllStore();
		assertNotNull(store);
	}

	/*
	 * (non-Javadoc) Finder/store
	 */

	public void test_getStoreByCriteria() {
		StoreSearchCriteria dto = new StoreSearchCriteria();
		dto.setId_retail(100L);
		dto.setId_store(370L);
		List<StoreDTO> list = service.getStoreByCriteria(dto);
		assertNotNull(list);
	}

	public void test_getStoreByIdRetail() {
		List<Store> list = service.getStoreByIdRetail(6L);
		assertNotNull(list);
	}

	public void test_getAvailableStoreByIdRetail() {
		AvailableStoreByIdRetail dto = new AvailableStoreByIdRetail();
		dto.setIdRetail(104L);
		dto.setIdSupplier(1L);

		List<Store> list = service.getAvailableStoreByIdRetail(dto);
		assertNotNull(list);
	}

	public void test_getAvailableStoreByIdStoreCategory() {
		AvailableStoreByIdStoreCategory dto = new AvailableStoreByIdStoreCategory();
		dto.setIdStorecategory(1L);
		dto.setIdSuppler(1L);

		List<Store> list = service.getAvailableStoreByIdStoreCategory(dto);
		assertNotNull(list);
	}

	public void test_getStoreByIdRetailCategory() {
		List<Store> list = service.getStoreByIdRetailCategory(1L);
		assertNotNull(list);
	}

	public void test_getStoreByIds() {

		long[] ids = new long[2];
		ids[0] = 100L;
		ids[1] = 101L;

		StoreByIdsSearchCriteria dto = new StoreByIdsSearchCriteria();
		dto.setIds(ids);

		List<Store> list = service.getStoreByIds(dto);
		assertNotNull(list);
	}

	public void test_getStoreExtById() throws StoreNotFoundException {
		StoreDTO store = service.getStoreExtById(100L);
		assertNotNull(store);
	}

	public void test_getIdStoresActiveByIdRetail() {
		List<Long> list = service.getIdStoresActiveByIdRetail(102L);
		assertNotNull(list);
	}

	public void test_getStoreByRoute() {
		List<StoreByRouteDTO> store = service.getStoreByRoute(3L);
		assertNotNull(store);
	}

	public void test_getTotalStoreByCriteria() throws ParseException {
		// SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		// SimpleDateFormat sd2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		// Date fini = new Date("10/11/2015 00:00:00");
		// Date ffin = new Date("12/11/2015 23:59:59");
		// String sc = "12/11/2015";

		TotalStoreCreateCriteria dto = new TotalStoreCreateCriteria();

		// dto.setCheck(true);
		dto.setNoCheck(true);
		// dto.setFini(fini);
		// dto.setFfin(ffin);

		Integer tot_str = service.getTotalStoreByCriteria(dto);
		assertTrue(tot_str > 0);
	}

	public void test_getStoreByNameAddress() throws ParseException {
		SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");
		Date schedule = sd.parse("20-10-2015");

		StoreByNameAddress dto = new StoreByNameAddress();
		dto.setNombre("Mi");
		dto.setId_supplier(166L);
		dto.setSchedule(schedule);

		List<StoreCallCenterDTO> list = service.getStoreByNameAddress(dto);
		for (StoreCallCenterDTO i : list) {
			System.out.println("---> " + i.getStore_name());
		}
	}

	public void test_delStoreByIdStoreCategory() {
		int row = service.delStoreByIdStoreCategory(2L);
		assertTrue(row > 0);
	}

	public void test_getStoreByIdRetailIdStorecategory() {
		AvailableStoreSearchCriteria dto = new AvailableStoreSearchCriteria();
		dto.setIdSupplier(166L);
		dto.setIdRetail(230L);

		List<AvailableStoreDTO> list = service.getAvailableStoreByCriteria(dto);
		for (AvailableStoreDTO i : list){
			System.out.println(i.getName());
		}
	}

	/*
	 * Finder state
	 */

	public void test_getStateByCriteria() {
		GetStateSearchCriteria dto = new GetStateSearchCriteria();
		dto.setActive("S");

		List<StateDTO> list = service.getStateByCriteria(dto);
		assertNotNull(list);
	}

	public void test_getAllStateQtyCityByIdCountry() {
		List<StateQtyCityDTO> list = service.getAllStateQtyCityByIdCountry(1L);
		assertNotNull(list);
	}

	/*
	 * Finder city
	 */
	public void test_getCityByCriteria() {
		GetCitySearchCriteria dto = new GetCitySearchCriteria();
		dto.setActive("N");
		dto.setId_state(100L);

		List<CityDTO> list = service.getCityByCriteria(dto);
		assertNotNull(list);
	}

	public void test_getCityTotalByCriteria() {
		GetCitySearchCriteria dto = new GetCitySearchCriteria();
		Integer num = service.getCityTotalByCriteria(dto);
		assertTrue(num > 0);
	}

	/*
	 * finder retail
	 */
	public void test_getRetailByCriteria() {
		RetailSearchCriteria dto = new RetailSearchCriteria();
		dto.setId_supplier(1L);
		List<RetailDTO> list = service.getRetailByCriteria(dto);
		assertNotNull(list);
	}

	public void test_getCliQtyByCriteria() {
		RetailSearchCriteria dto = new RetailSearchCriteria();
		dto.setId_supplier(166L);
		int list = service.getCliQtyByCriteria(dto);
		System.out.println(list);
		assertNotNull(list);
	}

	public void test_getRetailAvaibleByCriteria()
			throws RetailDuplicadoException {
		RetailAvaibleSearchCriteria dto = new RetailAvaibleSearchCriteria();
		dto.setActive("S");
		dto.setCode("12345");
		dto.setId_supplier(100L);

		service.getRetailAvaibleByCriteria(dto);
	}

	public void test_getRetailCountStrCheck() throws ParseException {
		SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date fini = sd.parse("16/11/2015 00:00:00");
		Date ffin = sd.parse("22/11/2015 23:59:59");

		QtyStrCheckByRetai dto = new QtyStrCheckByRetai();
		dto.setFini(fini);
		dto.setFfin(ffin);
		dto.setId_supplier(1L);

		List<QtyStrCheckByRetailDTO> list = service.getRetailCountStrCheck(dto);
		assertNotNull(list);
	}

	public void test_delRetailByIdSupplier() {
		int row = service.delRetailByIdSupplier(2L);
		assertTrue(row > 0);
	}

	/*
	 * retailCategory
	 */
	public void test_addRetailCategory() {
		AddRetailCategoryDTO dto = new AddRetailCategoryDTO();
		dto.setActive("S");
		dto.setCode("CO");
		dto.setLogin("admin");
		dto.setModified(new Date());
		dto.setName("in service");
		dto.setOrderby(11);

		Long id = service.addRetailCategory(dto);
		assertNotNull(id);
	}

	public void test_updRetailCategory() throws RetailCategoryNotFoundException {
		UpdRetailCategoryDTO dto = new UpdRetailCategoryDTO();
		dto.setId_retail_category(10001L);
		dto.setName("in service upd");

		int uno = service.updRetailCategory(dto);
		assertTrue(uno > 0);
	}

	public void test_getRetailCategory() throws RetailCategoryNotFoundException {
		RetailCategory re = service.getRetailCategoryById(10001L);
		assertNotNull(re);
	}

	public void test_getAllRetailCategory() {
		List<RetailCategory> list = service.getAllRetailCategory();
		assertNotNull(list);
	}

	public void test_getAllRetailCategoryByIdSupplier() {
		List<RetailCategory> list = service
				.getAllRetailCategoryByIdSupplier(1L);
		assertNotNull(list);
	}

	public void test_getStateAvaibleByCriteria() throws StateDuplicateException {
		StateAvaibleSearchCriteria dto = new StateAvaibleSearchCriteria();
		dto.setActive("S");
		dto.setCode("sd");

		service.getStateAvaibleByCriteria(dto);
	}

	/**
	 * Finder Locality
	 */

	public void test_getLocalityByCriteria() {
		GetLocalitySearchCriteria dto = new GetLocalitySearchCriteria();
		dto.setActive("S");
		dto.setId_city(112L);

		List<LocalityDTO> list = service.getLocalityByCriteria(dto);
		assertNotNull(list);
	}

	public void test_getLocalityTotalByCriteria() {
		GetLocalitySearchCriteria dto = new GetLocalitySearchCriteria();
		dto.setActive("S");
		dto.setId_city(112L);

		service.getLocalityTotalByCriteria(dto);
	}

	/**
	 * FINDER COUNTRY
	 */

	public void test_getCountryByCriteria() {
		CountrySearchCriteria dto = new CountrySearchCriteria();
		dto.setLim_inf(0);
		dto.setLim_sup(1);

		List<CountryDTO> list = service.getCountryByCriteria(dto);
		assertNotNull(list);
	}

	public void test_getCountryTotalByCriteria() {
		CountrySearchCriteria dto = new CountrySearchCriteria();
		dto.setLim_inf(0);
		dto.setLim_sup(1);
		int i = service.getCountryTotalByCriteria(dto);

		assertTrue(i > 0);
	}

	/**
	 * Store category
	 */

	public void test_addStoreCategory() {
		AddStoreCategoryDTO dto = new AddStoreCategoryDTO();
		dto.setActive("S");
		dto.setCode("A");
		dto.setCreated(new Date());
		dto.setId_supplier(1L);
		dto.setModified(new Date());
		dto.setName("Urbano");
		dto.setOrderby(1);
		dto.setLogin("admin");

		Long id = service.addStoreCategory(dto);
		assertNotNull(id);
	}

	public void test_updStoreCategory() throws StoreCategoryNotFoundException {
		UpdStoreCategoryDTO dto = new UpdStoreCategoryDTO();
		dto.setId_store_category(7L);
		dto.setName("NAME upd");
		dto.setLogin("adminupd");
		int row = service.updStoreCategory(dto);
		assertTrue(row > 0);
	}

	public void test_getStoreCategoryById()
			throws StoreCategoryNotFoundException {
		StoreCategory sc = service.getStoreCategoryById(7L);
		assertNotNull(sc);
	}

	public void test_getAllStoreCategory() {
		List<StoreCategory> list = service.getAllStoreCategory();
		assertNotNull(list);
	}

	public void test_getAllStoreCategoryBySupplier() {
		StoreCategorySearchCriteria dto = new StoreCategorySearchCriteria();
		dto.setActive("S");
		dto.setId_supplier(2L);

		// List<StoreCategory> list = service.getAllStoreCategory();
		List<StoreCategory> list = service.getAllStoreCategoryBySupplier(dto);
		assertNotNull(list);
	}

	public void test_getAllStoreCategoryByIdSupplier() {
		List<StoreCategory> list = service.getAllStoreCategoryByIdSupplier(1L);
		assertNotNull(list);
	}

	public void test_getAllStoreByIdStoreCategory() {
		List<Store> list = service.getAllStoreByIdStoreCategory(2L);
		assertNotNull(list);
	}

	public void test_getAllStoreCategory_estado() {
		List<StoreCategory> list = service.getAllStoreCategory("N");
		assertNotNull(list);
	}

	public void test_getRetailExtById() throws RetailNotFoundException {
		RetailDTO ret = service.getRetailExtById(102L);
		assertNotNull(ret);
	}

	/**
	 * Retail category
	 */

	public void getRetailCategoryActiveByCriteria() {
		RetailCategoryActiveSearchCriteria dto = new RetailCategoryActiveSearchCriteria();
		dto.setActive("S");
		dto.setId_supplier(2L);
		dto.setId_retail_category(60L);
		List<RetailCategory> list = service
				.getRetailsCategoryActiveByCriteria(dto);
		assertNotNull(list);
	}

	public void test_getTotalStoreByIdSupplier() {
		int a = service.getTotalStoreByIdSupplier(2L);
		assertTrue(a > 0);
	}

	public void test_getStoreByIdSupplier() {
		List<Store> list = service.getStoreByIdSupplier(1L);
		assertNotNull(list);
	}

	public void test_getAvailableStoreByIdSupplier() {
		List<Store> list = service.getAvailableStoreByIdSupplier(1L);
		assertNotNull(list);
	}

	public void test_getAviableStoreByIdSupplierAndIdRoute() {
		AvailableStoreByIdSupplierIdRoute dto = new AvailableStoreByIdSupplierIdRoute();
		dto.setId_supplier(166L);
		dto.setId_route(97L);

		List<Store> list = service.getAvailableStoreByIdSupplierAndIdRoute(dto);
		for (Store store : list) {
			System.out.println(store.getShelf());
		}
		assertNotNull(list);
	}

	public void test_getStoreByRetail() {
		List<StoreByRetailDTO> list = service.getStoreByRetail(2L);
		assertNotNull(list);
	}

	public void test_getIdStoreActiveByIdsRetail() {
		StoreActiveByIdsRetailSearchCriteria dto = new StoreActiveByIdsRetailSearchCriteria();
		List<Long> ids = new ArrayList<Long>();
		ids.add(1L);
		ids.add(2L);
		ids.add(3L);
		dto.setIds(ids);
		List<Long> list = service.getIdStoresActiveByIdsRetail(dto);
		assertNotNull(list);
	}

	public void test_getRetailByIds() {
		List<Long> ids = new ArrayList<Long>();
		ids.add(1L);
		ids.add(2L);
		RetailByIdsSearchCriteria dto = new RetailByIdsSearchCriteria();
		dto.setIds(ids);
		List<Retail> list_retails = service.getRetailByIds(dto);
		assertNotNull(list_retails);
	}

	public void test_getStoreNotInTravel() {
		StoreNotInTravelSearchCriteria dto = new StoreNotInTravelSearchCriteria();
		dto.setId_route(1L);
		dto.setId_travel(13L);
		List<StoreByRouteDTO> store = service.getStoreNotInTravel(dto);
		assertNotNull(store);
	}

	public void test_getStoreAvailableByRoute() {
		List<Long> id_route = new LinkedList<Long>();
		id_route.add(97l);
		id_route.add(122l);
		StoreAvailableInRouteCriteria dto = new StoreAvailableInRouteCriteria();
		dto.setId_route(id_route);
		List<StoreDTO> stores = service.getStoreAvailableByRoute(dto);
		for (StoreDTO i : stores) {
			System.out.println("---> " + i.getName() + " - " + i.getColor());
		}

	}

	public void test_getStoreInTravelByRoute() {

		List<Long> id_route = new LinkedList<Long>();
		id_route.add(122l);
		id_route.add(130l);
		StoreAvailableInRouteCriteria dto = new StoreAvailableInRouteCriteria();
		dto.setId_route(id_route);
		List<StoreDTO> stores = service.getStoreInTravelByRoute(dto);
		for (StoreDTO i : stores) {
			System.out.println("---> " + i.getName() + " - " + i.getColor()
					+ " - " + i.getRoute());
		}

	}

	public void test_getStoreInOtherTravelByCriteria() {
		StoreSearchCriteria dto = new StoreSearchCriteria();
		dto.setId_route(5L);
		dto.setId_route(12L);
		List<StoreDTO> stores = service.getStoreInOtherTravelByCriteria(dto);
		assertNotNull(stores);
	}
	
	public void test_getStoreInfoByCriteria() {
		StoreInfoSearchCriteria dto = new StoreInfoSearchCriteria();
		dto.setActive("S");
		dto.setStore_name("");
		dto.setId_supplier(166L);

		List<StoreInfoDTO> list = service.getStoreInfoByCriteria(dto);
		assertNotNull(list);
	}

	public void test_delStoreById() throws StoreNotFoundException {
		int row = service.delStoreById(760L);
		assertTrue(row > 0);
	}

	/*
	 * finder store category
	 */

	public void getStoresCategoryActiveByCriteria() {
		StoreCategoryActiveSearchCriteria dto = new StoreCategoryActiveSearchCriteria();
		dto.setActive("S");
		dto.setId_supplier(1L);
		dto.setId_store_category(1L);
		List<StoreCategory> list = service
				.getStoresCategoryActiveByCriteria(dto);
		assertNotNull(list);
	}

	public void test_delStoreCategoryByIdSupplier() {
		int row = service.delStoreCategoryByIdSupplier(2L);
		assertTrue(row > 0);
	}

	public void test_delRetailCategoryByIdSupplier() {
		int row = service.delRetailCategoryByIdSupplier(2L);
		assertTrue(row > 0);
	}

	public void getStoreByCategory() {
		List<StoreByCategoryDTO> list = service.getStoreByCategory(110L);
		assertNotNull(list);
	}

	public void getStoreByRetail() {
		List<StoreByRetailDTO> list = service.getStoreByRetail(110L);
		assertNotNull(list);
	}

	public void test_getAvailableStoreByIdRetailAndIdRoute() {
		AvailableStoreByIdRetailIdRoute dto = new AvailableStoreByIdRetailIdRoute();
		dto.setId_retail(104L);
		dto.setId_route(33L);
		dto.setId_supplier(1L);

		List<Store> list = service.getAvailableStoreByIdRetailAndIdRoute(dto);
		assertNotNull(list);
	}

	public void test_getAvailableStoreByIdStoreCategoryAndIdRoute() {
		AvailableStoreByIdStoreCategoryIdRoute dto = new AvailableStoreByIdStoreCategoryIdRoute();
		dto.setIdRoute(33L);
		dto.setIdStorecategory(1L);
		dto.setIdSupplier(1L);

		List<Store> list = service
				.getAvailableStoreByIdStoreCategoryAndIdRoute(dto);
		assertNotNull(list);
	}

	public void test_getAvailableStoreByCriteria() {
		StoreAvailableInTravelCriteria criteria = new StoreAvailableInTravelCriteria();
		criteria.setId_supplier(166l);
		criteria.setId_travel(514l);
		List<StoreByRouteDTO> list = service
				.getStoreAvailableInTravel(criteria);
		System.out.println("---> " + list.size());
	}
	
	public void test() {
		for (Store s : service.getStoreByIdRetail(340l)){
			System.out.println("---> " + s.getId_store() + " : " + s.getName());
		}
	}
	
	public void test_getStoresByIdsRoute() {
		
		long[] ids = {117l, 116l};
		StoreByIdsSearchCriteria dto = new StoreByIdsSearchCriteria();
		dto.setIds(ids);
		List<StoreRouteDTO> list = service.getStoresByIdsRoute(dto);
		
		System.out.println("Size: " + list.size());
		
		assertTrue(list != null && list.size() > 0);
	}
	
	public void test_changeStoresRetail() {
		ChangeRetailCriteria criteria = new ChangeRetailCriteria();
		criteria.setId_retail(260l);
		criteria.setId_retail_to(259l);
		int res = service.changeStoresRetail(criteria);
		assertTrue(res > 0);
	}
	
	public void test_getTotalStore(){
		Long res = service.getTotalStore(260l);
		System.out.println("---> " + res);
	}
	
	public void test_getVisitedList() {
		ActivesVisitedCriteria criteria = new ActivesVisitedCriteria();
		criteria.setDateEnd(new Date());
		criteria.setDateInit(new Date());
		criteria.setId_active(4l);
		List<ActivesVisitedExt> list = service.getVisitedList(criteria);
		assertNotNull(list);
	}
	
	public void test_addActiveVisited() {
		ActivesVisited vis = new ActivesVisited();
		vis.setCreated(new Date());
		vis.setId_actives(4l);
		vis.setId_store(18129l);
		vis.setLatitude(21.885145);
		vis.setLongitude(-102.28182);
		int res = service.addActiveVisited(vis);
		assertTrue(res > 0);
	}
	
	public void test_getActivesAndVisits() {
		ActivesAndVisitsCriteria criteria = new ActivesAndVisitsCriteria();
		criteria.setId_store(18129l);
		List<ActivesAndVisitsDTO> list = service.getActivesAndVisits(criteria);
		assertNotNull(list);
		if (list != null)
			assertTrue(list.size() > 0);
	}
	
	@Test
	public void test_verifyActivesVisit() {
		VerifyAndRegistCheckInCriteria criteria = new VerifyAndRegistCheckInCriteria();
		criteria.setDate(new Date());
		criteria.setId_store(18129l);
		String s = service.verifyAndRegistCheckIn(criteria);
		assertNotNull(s);
	}

}
