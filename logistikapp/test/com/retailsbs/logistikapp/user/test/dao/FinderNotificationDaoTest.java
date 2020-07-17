package com.retailsbs.logistikapp.user.test.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.retailsbs.logistikapp.user.dao.FinderNotificationDAO;
import com.retailsbs.logistikapp.user.domain.Notification;
import com.retailsbs.logistikapp.user.dto.NotificationDTO;
import com.retailsbs.logistikapp.user.dto.NotificationSearchCriteria;
import com.retailsbs.logistikapp.user.dto.NotificationTodaySearchCriteria;
import com.retailsbs.logistikapp.user.test.base.BaseTestJunit;

public class FinderNotificationDaoTest extends BaseTestJunit {

	@Autowired
	private FinderNotificationDAO dao;

	public void	test_getNotificationTodayByCriteria(){
		NotificationTodaySearchCriteria dto = new NotificationTodaySearchCriteria();
		dto.setFecha(new Date());
		dto.setProfile("ADM");
		dto.setId_user(3L);
		dto.setId_supplier(1L);
		List<Notification> list = dao.getNotificationTodayByCriteria(dto );
		assertNotNull(list);
	}
	
	public void test_getNotificationByCriteria() {
		NotificationSearchCriteria dto = new NotificationSearchCriteria();
		//dto.setLimit(10);
		dto.setId_supplier(1L);
		dto.setId_user(2L);
		dto.setProfile("ADM");
		List<NotificationDTO> list = dao.getNotificationByCriteria(dto );
		logger.debug("<---Probando getNotificationByCriteria--> "+ list.size() );		
		assertNotNull(list);
	}
	
	@Test
	public void test_delNotificationByIdSupplier() {
		int row = dao.delNotificationByIdSupplier(2L);
		assertTrue(row>0);
	}
	
}
