package com.retailsbs.logistikapp.retail.test.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.retailsbs.logistikapp.retail.dao.StoreDAO;
import com.retailsbs.logistikapp.retail.domain.Store;
import com.retailsbs.logistikapp.retail.test.base.BaseTestJunit;

public class StoreDaoTest extends BaseTestJunit {

	@Autowired
	private StoreDAO dao;
	@Test
	public void test_insert() {
		Store record = new Store();
		record.setId_store_category(1L);
		record.setActive("S");
		record.setAddress1("address1");
		record.setAddress2("address2");
		record.setCode("C");
		record.setCreated(new Date());
		record.setCode("dmarinr21");
		record.setId_city(100L);
		record.setId_country(1L);
		record.setId_retail(100L);
		record.setId_state(1L);
		record.setLatitude(12.23);
		record.setLogin("admin");
		record.setModified(new Date());
		record.setName("DM");
		record.setOrderby(10);
		record.setPostal(47200);
		record.setEmail("correo@correo.com");
		record.setPhone("1234567890");
		
		Long id = dao.insert(record);
		assertNotNull(id);
	}
	
	public void test_updateByPrimaryKeySelective() {
		Store record = new Store();
		record.setId_store(1L);
		record.setName("updated");
		
		int row = dao.updateByPrimaryKeySelective(record);
		assertTrue(row>0);
	}
	
	public void test_selectByPrimaryKey() {
		Store store = dao.selectByPrimaryKey(1L);
		assertNotNull(store);
	}
}
