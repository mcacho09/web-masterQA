package com.retailsbs.logistikapp.retail.test.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.retailsbs.logistikapp.retail.dao.FinderCityDAO;
import com.retailsbs.logistikapp.retail.dto.CityDTO;
import com.retailsbs.logistikapp.retail.dto.GetCitySearchCriteria;
import com.retailsbs.logistikapp.retail.test.base.BaseTestJunit;

public class FinderCityDaoTest extends BaseTestJunit {

	@Autowired
	private FinderCityDAO dao;

	public void test_getCityByCriteria() {
		GetCitySearchCriteria dto = new GetCitySearchCriteria();
		dto.setLim_inf(1);
		dto.setLim_sup(2);
		
		List<CityDTO> list = dao.getCityByCriteria(dto);
		assertNotNull(list);
	}
	
	@Test
	public void test_getCityTotalByCriteria() {
		GetCitySearchCriteria dto = new GetCitySearchCriteria();
		Integer num = dao.getCityTotalByCriteria(dto);
		assertTrue(num>0);
	}
}
