package com.retailsbs.logistikapp.supplier.test.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.retailsbs.logistikapp.supplier.dao.SupplierDAO;
import com.retailsbs.logistikapp.supplier.domain.Supplier;
import com.retailsbs.logistikapp.supplier.test.base.BaseTestJunit;

public class SupplierDaoTest extends BaseTestJunit {

	@Autowired
	private SupplierDAO dao;

	@Test
	public void crud() {
		Supplier record = new Supplier();
		record.setActive("N");
		record.setCode("P1");
		record.setCreated(new Date());
		record.setLogin("admin");
		record.setModified(new Date());
		record.setName("PRUEBA");
		record.setOrderby(10);
		record.setShelf("N");
		// create
		Long id = dao.insert(record);
		assertNotNull(id);
		
		// read
		record = dao.selectByPrimaryKey(id);
		assertNotNull(record);
		
		// update
		record.setModified(new Date());
		int rows = dao.updateByPrimaryKey(record);
		assertTrue(rows>0);
		
		// update selective
		record = new Supplier();
		record.setId_supplier(id);
		record.setModified(new Date());
		rows = dao.updateByPrimaryKeySelective(record);
		assertTrue(rows>0);
		
		// delete
	}
	
}
