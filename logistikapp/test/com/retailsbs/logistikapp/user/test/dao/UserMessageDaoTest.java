package com.retailsbs.logistikapp.user.test.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.retailsbs.logistikapp.user.dao.UserMessageDAO;
import com.retailsbs.logistikapp.user.domain.UserMessage;
import com.retailsbs.logistikapp.user.test.base.BaseTestJunit;

public class UserMessageDaoTest extends BaseTestJunit {

	@Autowired
	private UserMessageDAO dao;

	public void	test_insert(){
		UserMessage record = new UserMessage();
		record.setCreated(new Date());
		record.setId_user(100L);
		record.setId_user_to(200L);
		
		Long id = dao.insert(record);
		assertNotNull(id);
	}
	
	public void test_updateByPrimaryKeySelective() {
		UserMessage record = new UserMessage();
		record.setId_user_message(1L);
		record.setId_user(100L);
		
		int i = dao.updateByPrimaryKeySelective(record);
		assertTrue(i>0);
	}
	
	public void test_deleteByPrimaryKey() {
		int i = dao.deleteByPrimaryKey(1L);
		assertNotNull(i>0);
	}
	
	@Test
	public void test_selectByPrimaryKey() {
		UserMessage as = dao.selectByPrimaryKey(2L);
		assertNotNull(as);
	}
	
}
