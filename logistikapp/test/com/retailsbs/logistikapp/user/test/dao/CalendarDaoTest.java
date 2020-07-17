package com.retailsbs.logistikapp.user.test.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.retailsbs.logistikapp.user.dao.CalendarDAO;
import com.retailsbs.logistikapp.user.domain.Calendar;
import com.retailsbs.logistikapp.user.dto.CalendarExample;
import com.retailsbs.logistikapp.user.test.base.BaseTestJunit;

public class CalendarDaoTest extends BaseTestJunit {

	@Autowired
	private CalendarDAO dao;

	public void test_insert() {
		Calendar dto = new Calendar();
		dto.setId_supplier(2L);
		dto.setId_user(5L);
		dto.setCal_alert(1);
		dto.setCal_end(new Date());
		dto.setCal_level("P");
		dto.setCal_location("ags");
		dto.setCal_start(new Date());
		dto.setCal_title("cal_title");
		dto.setCreated(new Date());
		dto.setLogin("admin");
		dto.setModified(new Date());
		
		Long id = dao.insert(dto);
		assertNotNull(id);
	}
	
	public void test_updateByPrimaryKeySelective() {
		Calendar dto = new Calendar();
		dto.setId_calendar(1L);
		dto.setCal_title("upd");
		
		int i = dao.updateByPrimaryKeySelective(dto);
		assertTrue(i>0);
	}
	
	public void test_selectByPrimaryKey() {
		Calendar cal = dao.selectByPrimaryKey(1L);
		assertNotNull(cal);
	}
	
	public void test_selectByExample() {
		CalendarExample example = new CalendarExample();
		
		List<Calendar> list = dao.selectByExample(example);
		assertNotNull(list);
	}
	
	@Test
	public void test_deleteByPrimaryKey() {
		int row = dao.deleteByPrimaryKey(1L);
		assertTrue(row>0);
	}
	
}
