package com.retailsbs.logistikapp.financial.test.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.retailsbs.logistikapp.financial.dao.ProductDAO;
import com.retailsbs.logistikapp.financial.domain.Product;
import com.retailsbs.logistikapp.financial.test.base.BaseTestJunit;

public class ProductDaoTest extends BaseTestJunit {

	@Autowired
	private ProductDAO dao;

	@Test
	public void crud() {
		Product record = new Product();
		record.setActive("S");
		record.setCode("CAT1");
		record.setCreated(new Date());
		record.setId_category_product(2L);
		record.setLogin("admin");
		record.setModified(new Date());
		record.setName_long("Descripcion larga");
		record.setName_short("Descripcion corta");
		record.setOrderby(10);
		record.setPrice_cost(999.9);
		record.setPrice_sale(1099.5);
		
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
		record = new Product();
		record.setId_product(id);
		record.setModified(new Date());
		rows = dao.updateByPrimaryKeySelective(record);
		assertTrue(rows>0);
		
		// delete
	}
	
}
