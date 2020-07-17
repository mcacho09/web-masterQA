package com.retailsbs.logistikapp.commercial.test.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.retailsbs.logistikapp.commercial.domain.RetailcatGoal;
import com.retailsbs.logistikapp.commercial.dto.AddRetailcatGoal;
import com.retailsbs.logistikapp.commercial.dto.UpdRetailcatGoal;
import com.retailsbs.logistikapp.commercial.exception.RetailcatGoalNotFoundException;
import com.retailsbs.logistikapp.commercial.service.CommercialService;
import com.retailsbs.logistikapp.commercial.test.base.BaseTestJunit;

public class CommercialServiceTest extends BaseTestJunit {

	@Autowired
	private CommercialService service;
	
	@Test
	public void test_addRetailcatGoal() {
		AddRetailcatGoal dto = new AddRetailcatGoal();
		dto.setAmount(2.2);
		dto.setCreated(new Date());
		dto.setId_retail_category(1L);
		dto.setLogin("login_ser");
		dto.setModified(new Date());
		dto.setMonth(11);
		dto.setYear(2001);
		
		Long id = service.addRetailcatGoal(dto);
		assertNotNull(id);
	}
	
	public void test_updRetailcatGoal() throws RetailcatGoalNotFoundException {
		UpdRetailcatGoal dto = new UpdRetailcatGoal();
		dto.setId_retailcat_goal(1L);
		dto.setLogin("logSer_upd");
		
		int row = service.updRetailcatGoal(dto);
		assertTrue(row > 0);
	}
	
	public void test_getRetailcatGoalById() throws RetailcatGoalNotFoundException {
		RetailcatGoal obj = service.getRetailcatGoalById(1L);
		assertNotNull(obj);
	}
	
	public void test_getAllRetailcatGoal() {
		List<RetailcatGoal> list = service.getAllRetailcatGoal();
		assertNotNull(list);
	}
	
}
