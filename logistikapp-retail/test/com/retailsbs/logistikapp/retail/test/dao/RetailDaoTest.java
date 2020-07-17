package com.retailsbs.logistikapp.retail.test.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.retailsbs.logistikapp.retail.dao.RetailDAO;
import com.retailsbs.logistikapp.retail.domain.Retail;
import com.retailsbs.logistikapp.retail.test.base.BaseTestJunit;

public class RetailDaoTest extends BaseTestJunit {

	@Autowired
	private RetailDAO dao;
	
	public void test_insert() {
		Retail record = new Retail();
		record.setActive("S");
		record.setAddress1("address1");
		record.setAddress2("address2");
		record.setCode("C");
		record.setCreated(new Date());
		record.setId_city(112L);
		record.setId_country(1L);
		record.setId_state(1L);
		record.setLogin("admin");
		record.setModified(new Date());
		record.setName("oxxo");
		record.setOrderby(10);
		record.setPostal(20230);
		record.setId_supplier(101L);
		record.setId_retail_category(1L);
		record.setPhone("1234567890");
		record.setEmail("correo@correo.com");
		
		Long id = dao.insert(record);
		assertNotNull(id);
	}
	
	
	public void test_updateByPrimaryKeySelective() {
		Retail record = new Retail();
		record.setId_retail(10003L);
		record.setName("new ratail in 2 service");
		
		int row = dao.updateByPrimaryKeySelective(record);
		assertTrue(row>0);
	}
	@Test
	public void test_selectByPrimaryKey() {
		Retail retail = dao.selectByPrimaryKey(208L);
		assertNotNull(retail);
	}
}
