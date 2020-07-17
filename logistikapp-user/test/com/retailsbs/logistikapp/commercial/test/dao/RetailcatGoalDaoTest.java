package com.retailsbs.logistikapp.commercial.test.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.retailsbs.logistikapp.commercial.dao.RetailcatGoalDAO;
import com.retailsbs.logistikapp.commercial.domain.RetailcatGoal;
import com.retailsbs.logistikapp.commercial.dto.RetailcatGoalExample;
import com.retailsbs.logistikapp.commercial.test.base.BaseTestJunit;

public class RetailcatGoalDaoTest extends BaseTestJunit{

	@Autowired
	private RetailcatGoalDAO dao;
	
	@Test
	public void test_insert() {
		RetailcatGoal record = new RetailcatGoal();
		record.setAmount(1.1);
		record.setCreated(new Date());
		record.setId_retail_category(1L);
		record.setLogin("login_2");
		record.setModified(new Date());
		record.setMonth(3);
		record.setYear(2015);
		
		Long id = dao.insert(record);
		assertNotNull(id);
	}
	
	public void test_upd() {
		RetailcatGoal record = new RetailcatGoal();
		record.setId_retailcat_goal(100L);
		record.setYear(2000);
		
		int row = dao.updateByPrimaryKeySelective(record);
		assertTrue(row > 0);
	}
	
	public void test_selectByPrimaryKey() {
		RetailcatGoal obj = dao.selectByPrimaryKey(100L);
		assertNotNull(obj);
	}
	
	public void test_selectByExample() {
		RetailcatGoalExample example = new RetailcatGoalExample();
		List<RetailcatGoal> list = dao.selectByExample(example);
		assertNotNull(list);
	}
	
}
