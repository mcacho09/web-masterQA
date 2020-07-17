package com.retailsbs.logistikapp.retail.test.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.retailsbs.logistikapp.retail.dao.FinderStoreCategoryDAO;
import com.retailsbs.logistikapp.retail.domain.StoreCategory;
import com.retailsbs.logistikapp.retail.dto.StoreCategoryActiveSearchCriteria;
import com.retailsbs.logistikapp.retail.test.base.BaseTestJunit;

public class FinderStoreCategoryDAOTest extends BaseTestJunit {
	@Autowired
	private FinderStoreCategoryDAO dao;
	public FinderStoreCategoryDAO getDao() {
		return dao;
	}
	public void setDao(FinderStoreCategoryDAO dao) {
		this.dao = dao;
	}
	public void test_getStoresCategoryActiveByCriteria() {
		StoreCategoryActiveSearchCriteria dto = new StoreCategoryActiveSearchCriteria();
		dto.setActive("S");
		dto.setId_supplier(110L);
		dto.setId_store_category(47L);
		
		List<StoreCategory> list = dao.getStoresCategoryActiveByCriteria(dto);
		assertNotNull(list);
	}
	
	@Test
	public void test_delStoreCategoryByIdSupplier() {
		int row = dao.delStoreCategoryByIdSupplier(2L);
		assertTrue(row>0);
	}

}
