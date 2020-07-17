package com.retailsbs.logistikapp.logistic.test.dao;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.retailsbs.logistikapp.logistic.dao.WayBillDAO;
import com.retailsbs.logistikapp.logistic.domain.WayBill;
import com.retailsbs.logistikapp.logistic.dto.WayBillExample;
import com.retailsbs.logistikapp.logistic.test.base.BaseTestJunit;

public class WayBillDAOTest extends BaseTestJunit {

	@Autowired
	private WayBillDAO dao;
	
	public void test_insert() {
		
		
		WayBill record = new WayBill();
		record.setComment("Este es un travel de prueba");
		record.setId_store(1L);
		record.setId_travel(1L);
		record.setStatus("C");
		record.setLog_created(new Date());
		record.setLog_created_login("nath");
		Long id = dao.insert(record);
		
		logger.debug("Se inserto --> "+id+" Route OK");
	}
	
	public void test_updateByPrimaryKeySelective() {
		
		WayBill record = new WayBill();
		record.setComment("WayBill prueba 1 cambiada");
		record.setId_waybill(1L);
		int id = dao.updateByPrimaryKeySelective(record);
		logger.debug("Se actualizaron  --> "+id+" registros OK");
	}
	
	public void test_selectByPrimaryKey() {
		WayBill id = dao.selectByPrimaryKey(1L);
		logger.debug("Se seleccionaron  --> "+id+" registros OK");
	}
	@Test
	public void test_selectByExample() {
		WayBillExample example = new WayBillExample ();
		example.createCriteria().andId_storeEqualTo(1L);
		List<WayBill> id = dao.selectByExample(example);
		logger.debug("Se seleccionaron  --> "+id.size()+" registros OK");
	}
}
