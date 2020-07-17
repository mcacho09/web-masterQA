package com.retailsbs.logistikapp.user.test.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.retailsbs.logistikapp.user.dao.AccessDAO;
import com.retailsbs.logistikapp.user.domain.Access;
import com.retailsbs.logistikapp.user.test.base.BaseTestJunit;

public class AccessDaoTest extends BaseTestJunit {

	@Autowired
	private AccessDAO dao;

	public void test_insert() {
		Access acces = new Access();
		acces.setActive("S");
		acces.setCreated(new Date());
		acces.setId_retail(1L);
		acces.setId_store(1L);
		acces.setId_user(1L);
		acces.setLogin("SETUP");
		
		Long id = dao.insert(acces);
		assertNotNull(id);
	}
	
	public void test_updateByPrimaryKeySelective() {
		Access record = new Access();
		record.setId_access(1L);
		record.setActive("U");
		
		int i = dao.updateByPrimaryKeySelective(record);
		assertTrue(i>0);
	}
	
	@Test
	public void test_selectByPrimaryKey() {
		Access acces = dao.selectByPrimaryKey(1L);
		assertNotNull(acces);
	}
	
}
