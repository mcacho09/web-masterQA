package com.retailsbs.logistikapp.retail.test.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.retailsbs.logistikapp.retail.dao.FinderStoreDAO;
import com.retailsbs.logistikapp.retail.domain.Store;
import com.retailsbs.logistikapp.retail.dto.AvailableStoreByIdRetail;
import com.retailsbs.logistikapp.retail.dto.AvailableStoreByIdRetailIdRoute;
import com.retailsbs.logistikapp.retail.dto.AvailableStoreByIdStoreCategory;
import com.retailsbs.logistikapp.retail.dto.AvailableStoreByIdStoreCategoryIdRoute;
import com.retailsbs.logistikapp.retail.dto.AvailableStoreByIdSupplierIdRoute;
import com.retailsbs.logistikapp.retail.dto.AvailableStoreDTO;
import com.retailsbs.logistikapp.retail.dto.AvailableStoreSearchCriteria;
import com.retailsbs.logistikapp.retail.dto.StoreActiveByIdsRetailSearchCriteria;
import com.retailsbs.logistikapp.retail.dto.StoreAvailableInRouteCriteria;
import com.retailsbs.logistikapp.retail.dto.StoreByCategoryDTO;
import com.retailsbs.logistikapp.retail.dto.StoreByIdsSearchCriteria;
import com.retailsbs.logistikapp.retail.dto.StoreByNameAddress;
import com.retailsbs.logistikapp.retail.dto.StoreByRetailDTO;
import com.retailsbs.logistikapp.retail.dto.StoreByRouteDTO;
import com.retailsbs.logistikapp.retail.dto.StoreCallCenterDTO;
import com.retailsbs.logistikapp.retail.dto.StoreDTO;
import com.retailsbs.logistikapp.retail.dto.StoreInfoDTO;
import com.retailsbs.logistikapp.retail.dto.StoreInfoSearchCriteria;
import com.retailsbs.logistikapp.retail.dto.StoreSearchCriteria;
import com.retailsbs.logistikapp.retail.dto.TotalStoreCreateCriteria;
import com.retailsbs.logistikapp.retail.test.base.BaseTestJunit;

public class FinderStoreDaoTest extends BaseTestJunit {

	@Autowired
	private FinderStoreDAO dao;

	public void test_getRetailByCriteria() {
		StoreSearchCriteria dto = new StoreSearchCriteria();
		dto.setId_retail(100L);
		
		List<StoreDTO> list = dao.getStoreByCriteria(dto);
		assertNotNull(list);
	}
	
	public void test_getStoreByIdRetail() {
		List<Store> list = dao.getStoreByIdRetail(100L);
		assertNotNull(list);
	}
	
	public void test_getStoreByIdRetailCategory() {
		List<Store> list = dao.getStoreByIdRetailCategory(1L);
		assertNotNull(list);
	}
	
	public void test_getStoreByIds() {
		
		long[] ids = new long[2];
		ids[0] = 100L;
		ids[1] = 101L;
		
		StoreByIdsSearchCriteria dto = new StoreByIdsSearchCriteria();
		dto.setIds(ids);
		
		List<Store> list = dao.getStoreByIds(dto );
		assertNotNull(list);
	}
	
	public void test_getStoreExtById(){
		StoreDTO store = dao.getStoreExtById(677L);
		assertNotNull(store);
	}
	
	public void test_getIdStoresActiveByIdRetail() {
		List<Long> list = dao.getIdStoresActiveByIdRetail(102L);
		assertNotNull(list);
	}
	
	public void test_getTotalStoreByIdSupplier() {
		int a = dao.getTotalStoreByIdSupplier(2L);
		assertTrue(a>0);
	}
	
	
	public void test_getStoreByRetail() {
		List<StoreByRetailDTO> list = dao.getStoreByRetail(101L);
				assertNotNull(list);
	}
	public void test_getStoreByRoute() {
		List<StoreByRouteDTO> list = dao.getStoreByRoute(1L);
		assertNotNull(list);
	}
	
	public void test_getIdStoreActiveByIdsRetail() {
		
		List<Long> ids = new ArrayList<Long>();
		ids.add(1L);
		ids.add(2L);
		ids.add(3L);	
		StoreActiveByIdsRetailSearchCriteria dto = new StoreActiveByIdsRetailSearchCriteria(); 
		dto.setIds(ids);
		List<Long> list = dao.getIdStoresActiveByIdsRetail(dto);
		assertNotNull(list);
	}
	public void test_getStoreByIdSupplier() {
		List<Store> list = dao.getStoreByIdSupplier(1L);
		assertNotNull(list);
	}
	public void test_getAviableStoreByIdSupplier() {
		List<Store> list = dao.getAvailableStoreByIdSupplier(1L);
		assertNotNull(list);
	}
	public void test_getAviableStoreByIdRetail() {
		AvailableStoreByIdRetail dto = new AvailableStoreByIdRetail();
		dto.setIdRetail(104L);
		dto.setIdSupplier(1L);
		
		List<Store> list = dao.getAvailableStoreByIdRetail(dto);
		assertNotNull(list);
	}
	public void test_getAvailableStoreByIdStoreCategory() {
		AvailableStoreByIdStoreCategory dto = new AvailableStoreByIdStoreCategory();
		dto.setIdStorecategory(1L);
		dto.setIdSuppler(1L);
		
		List<Store> list = dao.getAvailableStoreByIdStoreCategory(dto);
		assertNotNull(list);
	}
	
	public void	test_getTotalStoreByCriteria() throws ParseException {
		
		TotalStoreCreateCriteria dto = new TotalStoreCreateCriteria();
		
		
		int tot_str = dao.getTotalStoreByCriteria(dto);
		assertTrue(tot_str>0);
	}
	
	public void test_getStoreInfoByCriteria() {
		StoreInfoSearchCriteria dto = new StoreInfoSearchCriteria();
		dto.setActive("S");
		dto.setStore_name("COMERCIO");
		dto.setId_supplier(134L);
		
		List<StoreInfoDTO> list = dao.getStoreInfoByCriteria(dto);
		assertNotNull(list);
	}
	
	public void test_getStoreByNameAddress() throws ParseException {
		SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");
		Date schedule = sd.parse("13-10-2015");
		
		StoreByNameAddress dto = new StoreByNameAddress();
		
		dto.setId_supplier(1L);
		dto.setSchedule(schedule);
		
		List<StoreCallCenterDTO> list = dao.getStoreByNameAddress(dto);
		assertNotNull(list);
	}
	
	public void test_delStoreByIdStoreCategory() {
		int row = dao.delStoreByIdStoreCategory(2L);
		assertTrue(row>0);
	}

	public void getStoreByCategory() {
		List<StoreByCategoryDTO> list = dao.getStoreByCategory(110L);
		assertNotNull(list);
	}
	
	public void getStoreByRetail() {
		List<StoreByRetailDTO> list = dao.getStoreByRetail(110L);
		assertNotNull(list);
	}
	
	public void test_getAvailableStoreByIdSupplierAndIdRoute() {
		AvailableStoreByIdSupplierIdRoute dto = new AvailableStoreByIdSupplierIdRoute();
		dto.setId_supplier(1L);
		dto.setId_route(33L);
		
		List<Store> list = dao.getAvailableStoreByIdSupplierAndIdRoute(dto);
		assertNotNull(list);
	}
	
	public void	test_getAvailableStoreByIdRetailAndIdRoute(){
		AvailableStoreByIdRetailIdRoute dto = new AvailableStoreByIdRetailIdRoute();
		dto.setId_retail(104L);
		dto.setId_route(33L);
		dto.setId_supplier(1L);
		
		List<Store> list = dao.getAvailableStoreByIdRetailAndIdRoute(dto);
		assertNotNull(list);
	}
	
	public void test_getAviableStoreByIdStoreCategoryAndIdRoute() {
		
		AvailableStoreByIdStoreCategoryIdRoute dto = new AvailableStoreByIdStoreCategoryIdRoute();
		dto.setIdRoute(33L);
		dto.setIdStorecategory(1L);
		dto.setIdSupplier(1L);
		
		List<Store> list = dao.getAvailableStoreByIdStoreCategoryAndIdRoute(dto);
		assertNotNull(list);
	}
	
	public void test_getAvailableStoreByCriteria(){
		AvailableStoreSearchCriteria dto = new AvailableStoreSearchCriteria();
		dto.setIdSupplier(1L);
		dto.setIdRetail(101L);
		dto.setIdStorecategory(1L);
		
		List<AvailableStoreDTO> list = dao.getAvailableStoreByCriteria(dto);
		assertNotNull(list);
	}

	@Test
	public void getStoreInTravelByRoute() {
		List<Long> ids = new ArrayList<Long>();
		ids.add(121L);
		
		StoreAvailableInRouteCriteria dto = new StoreAvailableInRouteCriteria();
		dto.setId_route(ids);
		List<StoreDTO> list = dao.getStoreInTravelByRoute(dto );
		assertTrue("stores que estan en algun travel",list.size()==0);
	}
	
	@Test
	public void getStoreAvailableByRoute() {
		List<Long> ids = new ArrayList<Long>();
		ids.add(121L);
		
		StoreAvailableInRouteCriteria dto = new StoreAvailableInRouteCriteria();
		dto.setId_route(ids);
		List<StoreDTO> list = dao.getStoreAvailableByRoute(dto);
		assertTrue("stores disponibles",list.size()>0);
	}
	
}
