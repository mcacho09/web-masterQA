package com.retailsbs.logistikapp.retail.test.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.retailsbs.logistikapp.retail.dao.FinderRetailCategoryDAO;
import com.retailsbs.logistikapp.retail.domain.RetailCategory;
import com.retailsbs.logistikapp.retail.dto.RetailCategoryActiveSearchCriteria;
import com.retailsbs.logistikapp.retail.test.base.BaseTestJunit;

public class FinderRetailCategoryDAOTest  extends BaseTestJunit {
	@Autowired
	private FinderRetailCategoryDAO dao;
	
	public FinderRetailCategoryDAO getDao() {
		return dao;
	}
	public void setDao(FinderRetailCategoryDAO dao) {
		this.dao = dao;
	}
	public void test_getRetailByCriteria() {
		RetailCategoryActiveSearchCriteria dto = new RetailCategoryActiveSearchCriteria();
		dto.setActive("S");
		dto.setId_supplier(2L);
		dto.setId_retail_category(60L);
		
		List<RetailCategory> list = dao.getRetailsCategoryActiveByCriteria(dto);
		assertNotNull(list);
	}
	
	@Test
	public void test_delRetailCategoryByIdSupplier() {
		int row = dao.delRetailCategoryByIdSupplier(2l);
		assertTrue(row>0);
	}
}
