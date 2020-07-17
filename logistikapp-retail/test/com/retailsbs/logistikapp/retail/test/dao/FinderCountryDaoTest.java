package com.retailsbs.logistikapp.retail.test.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.retailsbs.logistikapp.retail.dao.FinderCountryDAO;
import com.retailsbs.logistikapp.retail.dto.CountryDTO;
import com.retailsbs.logistikapp.retail.dto.CountrySearchCriteria;
import com.retailsbs.logistikapp.retail.test.base.BaseTestJunit;

public class FinderCountryDaoTest extends BaseTestJunit {

	@Autowired
	private FinderCountryDAO dao;

	public void test_getCountryByCriteria() {
		CountrySearchCriteria dto = new CountrySearchCriteria();
		dto.setLim_inf(0);
		dto.setLim_sup(1);
		
		List<CountryDTO> list = dao.getCountryByCriteria(dto);
		assertNotNull(list);
	}
	
	@Test
	public void test_getCityTotalByCriteria() {
		CountrySearchCriteria dto = new CountrySearchCriteria();
		dto.setLim_inf(0);
		dto.setLim_sup(1);
		dto.setActive("S");
		
		Integer i = dao.getCountryTotalByCriteria(dto);
		assertTrue(i>0);
	}
}
