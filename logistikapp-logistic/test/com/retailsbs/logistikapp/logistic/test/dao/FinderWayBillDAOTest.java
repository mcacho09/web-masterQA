package com.retailsbs.logistikapp.logistic.test.dao;


import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.retailsbs.logistikapp.logistic.dao.FinderWayBillDAO;
import com.retailsbs.logistikapp.logistic.dto.WayBillDTO;
import com.retailsbs.logistikapp.logistic.dto.WayBillSearchCriteria;
import com.retailsbs.logistikapp.logistic.test.base.BaseTestJunit;

public class FinderWayBillDAOTest extends BaseTestJunit {

	@Autowired
	private FinderWayBillDAO finder;
	
	public void test_getTravelByCriteria() {
		Long id_travel = 5L;
		List<WayBillDTO> lista = finder.getWayBillByIdTravel(id_travel );
		logger.debug("Se seleccionaron  --> "+lista.size()+" registros OK");
	}
	
	public void test_getWayBillByCriteria() {
		WayBillSearchCriteria dto = new WayBillSearchCriteria();
		dto.setId_travel(73L);
		dto.setId_store(10L);
		
		List<WayBillDTO> list = finder.getWayBillByCriteria(dto);
		logger.debug("list="+list);
	}
	
	@Test
	public void test_delWaybillByIdStore() {
		int row = finder.delWaybillByIdStore(195L);
		assertTrue(row > 0);
	}
}
