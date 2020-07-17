package com.retailsbs.logistikapp.retail.test.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.retailsbs.logistikapp.retail.dao.LocalityDAO;
import com.retailsbs.logistikapp.retail.domain.Locality;
import com.retailsbs.logistikapp.retail.test.base.BaseTestJunit;

public class LocalityDaoTest extends BaseTestJunit {

	@Autowired
	private LocalityDAO dao;

	public void test_insert() {
		Locality record = new Locality();
		record.setActive("S");
		record.setCode("C");
		record.setCreated(new Date());
		record.setId_city(1L);
		record.setLogin("admin");
		record.setModified(new Date());
		record.setName("Ags");
		record.setOrderby(10);
		
		Long id = dao.insert(record);
		assertNotNull(id);
	}
	
	public void test_updateByPrimaryKeySelective() {
		Locality record = new Locality();
		record.setId_locality(1L);
		record.setCode("U");
		
		int row = dao.updateByPrimaryKeySelective(record);
		assertTrue(row>0);
	}
	
	@Test
	public void test_selectByPrimaryKey() {
		Locality loc = dao.selectByPrimaryKey(1L);
		assertNotNull(loc);
	}
}
