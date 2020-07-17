package com.retailsbs.logistikapp.logistic.test.dao;


import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.retailsbs.logistikapp.logistic.dao.RouteStoreDAO;
import com.retailsbs.logistikapp.logistic.domain.RouteStore;
import com.retailsbs.logistikapp.logistic.dto.RouteStoreExample;
import com.retailsbs.logistikapp.logistic.test.base.BaseTestJunit;

public class RouteStoreDAOTest extends BaseTestJunit {

	@Autowired
	private RouteStoreDAO dao;
	
	public void test_insert() {
		RouteStore record = new RouteStore();
		record.setId_route(1L);	
		record.setId_store(1L);
		record.setLog_created(new Date());
		record.setLog_created_login("nath");
		Long id = dao.insert(record);
		
		logger.debug("Se inserto --> "+id+" RouteStore OK");
	}
	
	public void test_updateByPrimaryKeySelective() {
		RouteStore record = new RouteStore();
		record.setId_store(2L);
		record.setId_route_store(1L);
		int id = dao.updateByPrimaryKeySelective(record);
		logger.debug("Se actualizaron  --> "+id+" registros OK");
	}
	public void test_selectByPrimaryKey() {
		RouteStore id = dao.selectByPrimaryKey(1L);
		logger.debug("Se seleccionaron  --> "+id+" registros OK");
	}
	@Test
	public void test_selectByExample() {
		RouteStoreExample example = new RouteStoreExample ();
		example.createCriteria().andId_storeEqualTo(2L);
		List<RouteStore> id = dao.selectByExample(example);
		logger.debug("Se seleccionaron  --> "+id.size()+" registros OK");
	}
}
