package com.retailsbs.logistikapp.retail.test.dao;

import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.retailsbs.logistikapp.retail.dao.CityDAO;
import com.retailsbs.logistikapp.retail.domain.City;
import com.retailsbs.logistikapp.retail.test.base.BaseTestJunit;

public class CityDaoTest extends BaseTestJunit {

	@Autowired
	private CityDAO dao;

	public void test_insert() {
		City record = new City();
		record.setActive("S");
		record.setCode("C");
		record.setCreated(new Date());
		record.setId_state(1L);
		record.setLogin("admin");
		record.setModified(new Date());
		record.setName("Ags");
		record.setOrderby(10);
		
		Long id = dao.insert(record);
		assertNotNull(id);
	}
	
	public void test_updateByPrimaryKeySelective() {
		City record = new City();
		record.setId_city(1L);
		record.setCode("U");
		
		int i = dao.updateByPrimaryKeySelective(record);
		assertNotNull(i);
	}
	
	@Test
	public void test_selectByPrimaryKey() {
		City city = dao.selectByPrimaryKey(1L);
		assertNotNull(city);
	}
}
