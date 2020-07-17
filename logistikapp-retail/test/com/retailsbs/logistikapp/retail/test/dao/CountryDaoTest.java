package com.retailsbs.logistikapp.retail.test.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.retailsbs.logistikapp.retail.dao.CountryDAO;
import com.retailsbs.logistikapp.retail.domain.Country;
import com.retailsbs.logistikapp.retail.test.base.BaseTestJunit;

public class CountryDaoTest extends BaseTestJunit {

	@Autowired
	private CountryDAO dao;

	public void test_insert() {
		Country record = new Country();
		record.setActive("S");
		record.setCode("code");
		record.setCreated(new Date());
		record.setLogin("admin");
		record.setModified(new Date());
		record.setName("juan");
		record.setOrderby(1);
		record.setPrefix("pre");
		
		Long id = dao.insert(record);
		
		assertNotNull(id);
		
	}
	
	public void test_select() {
		Country data = dao.selectByPrimaryKey(1L);
		assertNotNull(data);
	}
	
	public void test_upd() {
		Country record = new Country();
		record.setId_country(1L);
		record.setCode("chan");
		
		int i = dao.updateByPrimaryKeySelective(record);
		assertTrue(i>0);
	}
	
	@Test
	public void test_delete() {
		int i = dao.deleteByPrimaryKey(2L);
		assertTrue(i>0);
	}
}
