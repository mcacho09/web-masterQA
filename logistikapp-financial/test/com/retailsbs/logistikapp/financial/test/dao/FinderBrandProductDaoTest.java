package com.retailsbs.logistikapp.financial.test.dao;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.retailsbs.logistikapp.financial.dao.FinderBrandProductDAO;
import com.retailsbs.logistikapp.financial.dto.BrandProductDTO;
import com.retailsbs.logistikapp.financial.dto.BrandProductSearchCriteria;
import com.retailsbs.logistikapp.financial.test.base.BaseTestJunit;

public class FinderBrandProductDaoTest extends BaseTestJunit {

	@Autowired
	private FinderBrandProductDAO dao;


	@Test
	public void test_selectBrandProductByCriteria() {

		BrandProductSearchCriteria dto = new BrandProductSearchCriteria();
		dto.setId_supplier(166L);
		
		List<BrandProductDTO> list = dao.selectBrandProductByCriteria(dto);
		assertNotNull(list);
		
		for (BrandProductDTO brandProductDTO : list) {
			System.out.println(brandProductDTO.getName());
		}
	}	
}
