package com.retailsbs.logistikapp.financial.test.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.retailsbs.logistikapp.financial.dao.FinderProductDAO;
import com.retailsbs.logistikapp.financial.dto.ProductDTO;
import com.retailsbs.logistikapp.financial.dto.ProductSearchCriteria;
import com.retailsbs.logistikapp.financial.test.base.BaseTestJunit;

public class FinderProductDaoTest extends BaseTestJunit {

	@Autowired
	private FinderProductDAO dao;

	public void test_selectProductByCriteria() {
		ProductSearchCriteria dto = new ProductSearchCriteria();
		dto.setId_supplier(1L);
		
		List<ProductDTO> list = dao.selectProductByCriteria(dto);
		assertNotNull(list);
	}

	@Test
	public void test_delProductByIdCategoryProduct() {
		int row = dao.delProductByIdCategoryProduct(2L);
		assertTrue(row>0);
	}
}
