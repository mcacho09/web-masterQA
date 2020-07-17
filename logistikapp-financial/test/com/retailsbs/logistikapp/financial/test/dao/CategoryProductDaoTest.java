package com.retailsbs.logistikapp.financial.test.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.retailsbs.logistikapp.financial.dao.CategoryProductDAO;
import com.retailsbs.logistikapp.financial.domain.CategoryProduct;
import com.retailsbs.logistikapp.financial.test.base.BaseTestJunit;

public class CategoryProductDaoTest extends BaseTestJunit {

	@Autowired
	private CategoryProductDAO dao;

	@Test
	public void crud() {
		CategoryProduct record = new CategoryProduct();
		record.setActive("S");
		record.setCode("CAT1");
		record.setCreated(new Date());
		record.setId_supplier(1L);
		record.setLogin("admin");
		record.setModified(new Date());
		record.setName("Categor�a CAT1");
		record.setOrderby(10);
		
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
		record = new CategoryProduct();
		record.setId_category_product(id);
		record.setModified(new Date());
		rows = dao.updateByPrimaryKeySelective(record);
		assertTrue(rows>0);
		
		// delete
	}
	
}
