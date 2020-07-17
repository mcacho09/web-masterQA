package com.retailsbs.logistikapp.financial.test.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.retailsbs.logistikapp.financial.dao.FinderCategoryProductDAO;
import com.retailsbs.logistikapp.financial.dto.CategoryProductDTO;
import com.retailsbs.logistikapp.financial.dto.CategoryProductSearchCriteria;
import com.retailsbs.logistikapp.financial.test.base.BaseTestJunit;

public class FinderCategoryProductDaoTest extends BaseTestJunit {

	@Autowired
	private FinderCategoryProductDAO dao;

	public void test_selectCategoryProductByCriteria() {

		CategoryProductSearchCriteria dto = new CategoryProductSearchCriteria();
		dto.setId_supplier(1L);
		
		List<CategoryProductDTO> list = dao.selectCategoryProductByCriteria(dto);
		assertNotNull(list);
	}
	
	@Test
	public void test_delCategoryProductByIdSupplier() {
		int row = dao.delCategoryProductByIdSupplier(2L);
		assertTrue(row>0);
	}
	
}
