package com.retailsbs.logistikapp.financial.test.dao;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.retailsbs.logistikapp.financial.dao.BrandDAO;
import com.retailsbs.logistikapp.financial.domain.Brand;
import com.retailsbs.logistikapp.financial.test.base.BaseTestJunit;

public class BrandProductDaoTest extends BaseTestJunit {

	@Autowired
	private BrandDAO dao;

	@Test
	public void crud() {
	Brand record = new Brand();
	record.setActive("N");
	record.setId_supplier(166L);
	record.setName("Ciel");
	
	// create
	Long id = dao.insert(record);
	assertNotNull(id);
	}
	
}
