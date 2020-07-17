package com.retailsbs.logistikapp.user.test.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.retailsbs.logistikapp.user.dao.MessageDAO;
import com.retailsbs.logistikapp.user.domain.Message;
import com.retailsbs.logistikapp.user.test.base.BaseTestJunit;

public class MessageDaoTest extends BaseTestJunit {

	@Autowired
	private MessageDAO dao;

	
	public void test_insert(){
		Message record = new Message();
		record.setSend("S");
		record.setId_user_message(6L);
		record.setCreated(new Date());
		record.setMessage("mensaje a enviar");
		record.setRead("N");
		Long id = dao.insert(record);
		assertNotNull(id);
		
	}
	public void test_updateByPrimaryKeySelective() {
		Message record = new Message();
		record.setId_message(253L);
		record.setMessage("mensaje update");
		
		int i = dao.updateByPrimaryKeySelective(record);
		assertTrue(i>0);
	}

	@Test
	public void test_deleteByPrimaryKey() {
		int i = dao.deleteByPrimaryKey(253L);
		assertTrue(i>0);
	}
	public void test_selectByPrimaryKey() {
		Message me = dao.selectByPrimaryKey(253L);
		assertNotNull(me);
	}
	
}
