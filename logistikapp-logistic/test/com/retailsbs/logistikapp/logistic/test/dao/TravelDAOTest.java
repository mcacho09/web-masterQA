package com.retailsbs.logistikapp.logistic.test.dao;


import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.retailsbs.logistikapp.logistic.dao.TravelDAO;
import com.retailsbs.logistikapp.logistic.domain.Travel;
import com.retailsbs.logistikapp.logistic.dto.TravelExample;
import com.retailsbs.logistikapp.logistic.test.base.BaseTestJunit;

public class TravelDAOTest extends BaseTestJunit {

	@Autowired
	private TravelDAO dao;
	
	public void test_insert() {
		
		Travel record = new Travel();
		record.setComment("Este es un travel de prueba");
		record.setName("Travel prueba 1");
		record.setId_route(1L);
		record.setId_user(5L);
		record.setStatus("CRT");
		record.setLog_created(new Date());
		record.setLog_created_login("nath");
		Long id = dao.insert(record);
		
		logger.debug("Se inserto --> "+id+" Route OK");
	}

	public void test_updateByPrimaryKeySelective() {
		
		Travel record = new Travel();
		record .setName("Travel prueba 1 cambiada");
		record.setId_travel(1L);
		int id = dao.updateByPrimaryKeySelective(record);
		logger.debug("Se actualizaron  --> "+id+" registros OK");
	}
	public void test_selectByPrimaryKey() {
		Travel id = dao.selectByPrimaryKey(1L);
		logger.debug("Se seleccionaron  --> "+id+" registros OK");
	}
	@Test
	public void test_selectByExample() {
		TravelExample example = new TravelExample ();
		example.createCriteria().andId_routeEqualTo(1L);
		List<Travel> id = dao.selectByExample(example);
		logger.debug("Se seleccionaron  --> "+id.size()+" registros OK");
	}
}
