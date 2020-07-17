package com.retailsbs.logistikapp.user.test.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.retailsbs.logistikapp.user.dao.NotificationDAO;
import com.retailsbs.logistikapp.user.domain.Notification;
import com.retailsbs.logistikapp.user.dto.NotificationExample;
import com.retailsbs.logistikapp.user.test.base.BaseTestJunit;

public class NotificationDaoTest extends BaseTestJunit {

	@Autowired
	private NotificationDAO dao;

	public void test_insert(){
		Notification record = new Notification();
		record.setCreated(new Date());
		record.setId_user(2L);
		record.setMessage("mensaje de prueba");
		record.setPriority("A");
		record.setId_supplier(1L);
		
		Long id = dao.insert(record);
		assertNotNull(id);
	}
	
	public void test_updateByPrimaryKeySelective() {
		Notification record = new Notification();
		record.setId_notification(23L);
		record.setMessage("mensaje upd");
		
		int row = dao.updateByPrimaryKeySelective(record);
		assertTrue(row>0);
	}
	
	public void test_selectByPrimaryKey() {
		Notification not = dao.selectByPrimaryKey(23L);
		assertNotNull(not);
	}
	
	@Test
	public void test_selectByExample() {
		NotificationExample example = new NotificationExample();

		List<Notification> list = dao.selectByExample(example);
		assertNotNull(list);
	}
	
}
