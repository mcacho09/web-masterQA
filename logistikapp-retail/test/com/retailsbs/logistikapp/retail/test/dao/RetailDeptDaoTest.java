package com.retailsbs.logistikapp.retail.test.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.retailsbs.logistikapp.retail.dao.RetailDeptDAO;
import com.retailsbs.logistikapp.retail.domain.RetailDept;
import com.retailsbs.logistikapp.retail.test.base.BaseTestJunit;

public class RetailDeptDaoTest extends BaseTestJunit {

	@Autowired
	private RetailDeptDAO dao;

	public void test_insert() {
		RetailDept record = new RetailDept();
		record.setActive("S");
		record.setContact("con");
		record.setCreated(new Date());
		record.setEmail("email@hotmail.com");
		record.setId_retail(1L);
		record.setLogin("admin");
		record.setModified(new Date());
		record.setName("name");
		record.setOrderby(10);
		record.setPhone("9-12-09-32");
		
		Long id = dao.insert(record);
		assertNotNull(id);
	}
	
	public void test_updateByPrimaryKeySelective() {
		RetailDept record = new RetailDept();
		record.setId_retail_dept(1L);
		record.setName("updataed");
		
		int row = dao.updateByPrimaryKeySelective(record);
		assertTrue(row>0);
	}
	
	@Test
	public void test_selectByPrimaryKey() {
		RetailDept rd = dao.selectByPrimaryKey(1L);
		assertNotNull(rd);
	}
}
