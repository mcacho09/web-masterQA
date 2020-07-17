package com.retailsbs.logistikapp.supplier.test.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.retailsbs.logistikapp.supplier.domain.Plan;
import com.retailsbs.logistikapp.supplier.domain.Supplier;
import com.retailsbs.logistikapp.supplier.dto.AddPlanDTO;
import com.retailsbs.logistikapp.supplier.dto.SupplierExtDTO;
import com.retailsbs.logistikapp.supplier.dto.UpdPlanDTO;
import com.retailsbs.logistikapp.supplier.exception.PlanNotFoundException;
import com.retailsbs.logistikapp.supplier.exception.SupplierNotFoundException;
import com.retailsbs.logistikapp.supplier.service.SupplierService;
import com.retailsbs.logistikapp.supplier.test.base.BaseTestJunit;

public class SupplierServiceTest extends BaseTestJunit {

	@Autowired
	private SupplierService service;
	
	/*
	 * Supplier
	 */
	public void getAllSupplier() {
		List<Supplier> list = service.getAllSupplier();
		assertNotNull(list);
	}
	
	public void test_delSupplierById() throws SupplierNotFoundException {
		int row = service.delSupplierById(4L);
		assertTrue(row>0);
	}
	
	public void test_selectPlanById() throws PlanNotFoundException{
		Plan plan = new Plan();
		plan = service.getPlanById(4L);
		System.out.println(plan.getId_plan());
		assertNotNull(plan);
	}
	
	public void test_deletePlanById(){
		int rows = service.delPlanByIdPlan(4L);
		assertTrue(rows>0);
	}
	
	public void test_updateSelectivePlan(){
		UpdPlanDTO dto = new UpdPlanDTO();
		dto.setId_plan(4L);
		dto.setPlan_name("Un plan fabuloso");
		int rows = service.updPlan(dto);
		assertTrue(rows>0);
	}
	
	public void test_insertPlan(){
		AddPlanDTO dto = new AddPlanDTO();
		dto.setAmount(99L);
		dto.setCallcenter("a");
		dto.setCustomers(12L);
		dto.setGroups("a");
		dto.setHistorical("a");
		dto.setId_plan(4L);
		dto.setMessages("a");
		dto.setNotifications("a");
		dto.setPlan_name("plan buenisimo");
		dto.setRoutes("a");
		dto.setScheduling("a");
		dto.setSearchbox("a");
		dto.setTasks("a");
		dto.setTravels("a");
		dto.setUsers(200L);
		
		Long id = service.addPlan(dto);
		assertNotNull(id);
	}
	
	public void test_plan() {
		List<Plan> list = service.getAllPlans();
		assertNotNull(list);
	}
	
	public void test_totalsupplier(){
		int total = service.getTotalSupplier(6l);
		System.out.println("Total: " + total);
	}
	
	@Test
	public void test_getSupplierExt() {
		List<SupplierExtDTO> list = service.getSupplierExt();
		assertNotNull(list);
		assertTrue(list.size() > 0);
	}

}
