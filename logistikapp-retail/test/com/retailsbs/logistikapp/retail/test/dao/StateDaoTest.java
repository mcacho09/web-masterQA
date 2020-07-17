package com.retailsbs.logistikapp.retail.test.dao;

import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.retailsbs.logistikapp.retail.dao.StateDAO;
import com.retailsbs.logistikapp.retail.domain.State;
import com.retailsbs.logistikapp.retail.test.base.BaseTestJunit;

public class StateDaoTest extends BaseTestJunit {

	@Autowired
	private StateDAO dao;

	public void test_insert() {
		State record = new State();
		record.setActive("S");
		record.setCode("co");
		record.setId_country(1L);
		record.setLogin("admin");
		record.setModified(new Date());
		record.setName("name");
		record.setOrderby(10);
		record.setPrefix("p");
		
		Long id = dao.insert(record);
		assertNotNull(id);
	}
	
	public void test_updateByPrimaryKeySelective() {
		State record = new State();
		record.setId_state(1L);
		record.setId_country(1L);
		
		int i = dao.updateByPrimaryKeySelective(record);
		assertNotNull(i);
	}
	
	@Test
	public void test_selectByPrimaryKey() {
		State st = dao.selectByPrimaryKey(1L);
		assertNotNull(st);
	}
}
