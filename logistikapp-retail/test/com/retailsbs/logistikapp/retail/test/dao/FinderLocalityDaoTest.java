package com.retailsbs.logistikapp.retail.test.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.retailsbs.logistikapp.retail.dao.FinderLocalityDAO;
import com.retailsbs.logistikapp.retail.dto.GetLocalitySearchCriteria;
import com.retailsbs.logistikapp.retail.dto.LocalityDTO;
import com.retailsbs.logistikapp.retail.test.base.BaseTestJunit;

public class FinderLocalityDaoTest extends BaseTestJunit {

	@Autowired
	private FinderLocalityDAO dao;

	@Test
	public void test_getLocalityByCriteria() {
		GetLocalitySearchCriteria dto = new GetLocalitySearchCriteria();
		dto.setLim_inf(0);
		dto.setLim_sup(1);
		
		List<LocalityDTO> list = dao.getLocalityByCriteria(dto);
		assertNotNull(list);
	}
	
	public void test_getLocalityTotalByCriteria() {
		GetLocalitySearchCriteria dto = new GetLocalitySearchCriteria();
		dto.setActive("S");
		dto.setId_city(112L);
		
		Integer num = dao.getLocalityTotalByCriteria(dto);
		assertTrue(num>0);
	}
}
