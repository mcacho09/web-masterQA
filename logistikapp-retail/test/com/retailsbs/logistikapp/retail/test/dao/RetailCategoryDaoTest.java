package com.retailsbs.logistikapp.retail.test.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.retailsbs.logistikapp.retail.dao.RetailCategoryDAO;
import com.retailsbs.logistikapp.retail.domain.RetailCategory;
import com.retailsbs.logistikapp.retail.test.base.BaseTestJunit;

public class RetailCategoryDaoTest extends BaseTestJunit {

	@Autowired
	private RetailCategoryDAO dao;

	public void test_insert() {
		RetailCategory record = new RetailCategory();
		record.setActive("S");
		record.setCode("cod");
		record.setCreated(new Date());
		record.setLogin("admin");
		record.setModified(new Date());
		record.setName("retail category uno");
		record.setOrderby(10);
		
		Long id = dao.insert(record);
		assertNotNull(id);
	}
	
	public void test_updateByPrimaryKeySelective() {
		RetailCategory record = new RetailCategory();
		record.setId_retail_category(10000L);
		record.setName("retail category 1 upd");
		
		int num = dao.updateByPrimaryKeySelective(record);
		assertTrue(num>0);
	}
	
	@Test
	public void test_selectByPrimaryKey() {
		RetailCategory rc = dao.selectByPrimaryKey(1L);
		assertNotNull(rc);
	}
}
