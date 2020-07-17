package com.retailsbs.logistikapp.retail.test.dao;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.retailsbs.logistikapp.retail.dao.FinderStateDAO;
import com.retailsbs.logistikapp.retail.dto.GetStateSearchCriteria;
import com.retailsbs.logistikapp.retail.dto.StateDTO;
import com.retailsbs.logistikapp.retail.dto.StateQtyCityDTO;
import com.retailsbs.logistikapp.retail.test.base.BaseTestJunit;

public class FinderStateDaoTest extends BaseTestJunit {

	@Autowired
	private FinderStateDAO dao;

	public void test_getStateByCriteria() {
		GetStateSearchCriteria dto = new GetStateSearchCriteria();
		dto.setLim_inf(1);
		dto.setLim_sup(6);
		
		List<StateDTO> list = dao.getStateByCriteria(dto);
		assertNotNull(list);
	}

	@Test
	public void test_getAllStateQtyCityByIdCoun() {
		List<StateQtyCityDTO> list = dao.getAllStateQtyCityByIdCountry(1L);
		assertNotNull(list);
	}
	
}
