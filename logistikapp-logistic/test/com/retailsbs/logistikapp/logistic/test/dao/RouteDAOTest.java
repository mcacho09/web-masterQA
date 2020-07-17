package com.retailsbs.logistikapp.logistic.test.dao;


import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.retailsbs.logistikapp.logistic.dao.RouteDAO;
import com.retailsbs.logistikapp.logistic.domain.Route;
import com.retailsbs.logistikapp.logistic.dto.RouteExample;
import com.retailsbs.logistikapp.logistic.test.base.BaseTestJunit;

public class RouteDAOTest extends BaseTestJunit {

	@Autowired
	private RouteDAO dao;
	
	public void test_insert() {
		Route record = new Route();
		record.setName("Ruta prueba 1");
		record.setCode("abcd");
		record.setColor("#000000");
		record.setId_supplier(1L);
		record.setStatus("CRT");
		record.setLog_created(new Date());
		record.setLog_created_login("nath");
		Long id = dao.insert(record);
		
		logger.debug("Se inserto --> "+id+" Route OK");
	}
	
	public void test_updateByPrimaryKeySelective() {
		Route record = new Route ();
		record.setName("Ruta prueba 1 cambiada");
		record.setId_route(1L);
		
		int id = dao.updateByPrimaryKeySelective(record);
		logger.debug("Se actualizaron  --> "+id+" registros OK");
	}
	
	public void test_selectByPrimaryKey() {
		Route id = dao.selectByPrimaryKey(1L);
		logger.debug("Se seleccionaron  --> "+id+" registros OK");
	}
	@Test
	public void test_selectByExample() {
		RouteExample example = new RouteExample ();
		example.createCriteria().andId_supplierEqualTo(1L);
		List<Route> id = dao.selectByExample(example);
		logger.debug("Se seleccionaron  --> "+id.size()+" registros OK");
	}
}
