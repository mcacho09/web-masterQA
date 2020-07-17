package com.retailsbs.logistikapp.financial.test.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.retailsbs.logistikapp.financial.dao.CostDAO;
import com.retailsbs.logistikapp.financial.domain.Cost;
import com.retailsbs.logistikapp.financial.dto.CostExample;
import com.retailsbs.logistikapp.financial.test.base.BaseTestJunit;

public class CostDaoTest extends BaseTestJunit {

	@Autowired
	private CostDAO dao;

	public void test_insert(){
		Cost record = new Cost();
		record.setAccounting(new Date());
		record.setActive("S");
		record.setAmount(12.2);
		record.setComment("com");
		record.setCreated(new Date());
		record.setId_retail(100L);
		record.setId_supplier(1L);
		record.setLogin("login");
		record.setModified(new Date());
		record.setName("name 2");
		
		Long id = dao.insert(record);
		assertNotNull(id);
	}
	
	public void test_upd() {
		Cost record = new Cost();
		record.setId_cost(101L);
		record.setName("name upd");
		
		int row = dao.updateByPrimaryKeySelective(record);
		assertTrue(row > 0);
	}
	
	public void test_selectByPrimaryKey() {
		Cost cost = dao.selectByPrimaryKey(101L);
		assertNotNull(cost);
	}
	
	@Test
	public void test_all() {
		CostExample example = new CostExample();
		
		List<Cost> list = dao.selectByExample(example);
		assertNotNull(list);
	}
	
}
