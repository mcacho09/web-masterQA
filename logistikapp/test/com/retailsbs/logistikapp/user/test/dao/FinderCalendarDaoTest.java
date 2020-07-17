package com.retailsbs.logistikapp.user.test.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.retailsbs.logistikapp.user.dao.FinderCalendarDAO;
import com.retailsbs.logistikapp.user.dto.CalendarDTO;
import com.retailsbs.logistikapp.user.dto.EventSearchCriteria;
import com.retailsbs.logistikapp.user.test.base.BaseTestJunit;

public class FinderCalendarDaoTest extends BaseTestJunit {

	@Autowired
	private FinderCalendarDAO dao;

	public void test_getEventByCriteria() {
		EventSearchCriteria dto = new EventSearchCriteria();
		dto.setId_supplier(200L);
		dto.setId_user(16L);
		
		List<CalendarDTO> list = dao.getEventByCriteria(dto);
		assertNotNull(list);
	}
	
	@Test
	public void test_delCalendarByIdSupplier() {
		int row = dao.delCalendarByIdSupplier(2L);
		assertTrue(row>0);
	}
	
}
