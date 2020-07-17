package com.retailsbs.logistikapp.supplier.test.dao;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.springframework.beans.factory.annotation.Autowired;

import com.retailsbs.logistikapp.supplier.dao.PlanDAO;
import com.retailsbs.logistikapp.supplier.domain.Plan;
import com.retailsbs.logistikapp.supplier.test.base.BaseTestJunit;

public class PlanDaoTest extends BaseTestJunit {
	
	@Autowired
	private PlanDAO dao;
	
	//@Test
	public void insert(){
		Plan record = new Plan();
		record.setAmount(99L);
		record.setCallcenter("S");
		record.setCustomers(12L);
		record.setGroups("S");
		record.setHistorical("S");
		record.setId_plan(3L);
		record.setMessages("S");
		record.setNotifications("S");
		record.setPlan_name("otro plan que no es el mismo");
		record.setRoutes("S");
		record.setScheduling("S");
		record.setSearchbox("S");
		record.setTasks("S");
		record.setTravels("S");
		record.setUsers(200L);
		
		Long id = dao.insert(record);
		assertNotNull(id);
	}

	//@Test
	public void select(){
		Plan plan = dao.selectByPrimaryKey(4L);
		assertNotNull(plan.getId_plan());
	}
	//@Test
	public void delete(){
		int rows = dao.deleteByPrimaryKey(5L);
		assertTrue(rows>0);
	}
	//@Test
	public void update(){
		Plan record = new Plan();
		record.setId_plan(1L);
		record.setPlan_name("MiPlan");
		int rows = dao.updateByPrimaryKeySelective(record);
		assertTrue(rows>0);
	}
}
