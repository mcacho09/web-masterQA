package com.retailsbs.logistikapp.logistic.test.dao;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.retailsbs.logistikapp.logistic.dao.FinderRouteDAO;
import com.retailsbs.logistikapp.logistic.dto.CountRouteSupplierCriteria;
import com.retailsbs.logistikapp.logistic.test.base.BaseTestJunit;

public class FinderRouteDAOTest extends BaseTestJunit {

	@Autowired
	private FinderRouteDAO finder;
	
	public void test_delRouteByIdSupplier() {
		int row = finder.delRouteByIdSupplier(2L);
		assertTrue(row>0);
	}

	@Test
	public void getCountTravelSupplier() {
		
		// Estado de rutas
		List<String> status = new ArrayList<String>();
		status.add("ACT");
		
		
		CountRouteSupplierCriteria dto = new CountRouteSupplierCriteria();
		dto.setId_supplier(110L);
		dto.setStatus(status);
		
		int rows = finder.getCountRouteSupplierByCriteria(dto);
		assertTrue("cantidad de viajes", rows>0);
	}
	
}
