package com.retailsbs.logistikapp.user.test.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.retailsbs.logistikapp.user.dao.FinderMessageDAO;
import com.retailsbs.logistikapp.user.dto.MessageDTO;
import com.retailsbs.logistikapp.user.dto.MessageGroupDTO;
import com.retailsbs.logistikapp.user.dto.MessageNoReadDTO;
import com.retailsbs.logistikapp.user.dto.NewMessageTotalDTO;
import com.retailsbs.logistikapp.user.dto.lastNewMessageDTO;
import com.retailsbs.logistikapp.user.dto.lastNewMessageSearchCriteria;
import com.retailsbs.logistikapp.user.test.base.BaseTestJunit;

public class FinderMessageDaoTest extends BaseTestJunit {

	@Autowired
	private FinderMessageDAO dao;

	public void test_getMessageByIdUserMessage() {
		List<MessageDTO> list = dao.getMessageByIdUserMessage(1L);
		assertNotNull(list);
	}
	
	public void test_deleteMessageByIdUserMessage() {
		int i = dao.deleteMessageByIdUserMessage(6L);
		assertTrue(i > 0);
	}
	
	public void test_newMessageTotal() {
		NewMessageTotalDTO dto = new NewMessageTotalDTO();
		dto.setId_user(100L);
		dto.setRead("S");
		
		int a = dao.newMessageTotal(dto);
		assertNotNull(a>0);
	}
	
	public void test_lastNewMessage() {
		lastNewMessageSearchCriteria dto = new lastNewMessageSearchCriteria();
		dto.setId_user(100L);
		dto.setRead("N");
		dto.setLimit(3);
		
		List<lastNewMessageDTO> list = dao.lastNewMessage(dto);
		assertNotNull(list);
	}

	public void test_getQtyMessageNoReadByIdUser() {
		int i = dao.getQtyMessageNoReadByIdUser(1L);
		assertNotNull(i>0);
	}
	
	@Test
	public void getListMessageNoReadByIdUser() {
		List<MessageNoReadDTO> list = dao.getListMessageNoReadByIdUser(176L);
		assertNotNull(list);
	}
	
	public void test_updateReadByIdUserMessage(){
		
		int n = dao.updateReadByIdUserMessage(4L);
		logger.debug("Se actualizaron --> "+n+" registros");
		
	}
	public void test_getMessageByIdGroup(){
		List<MessageGroupDTO> list = dao.getMessageByIdGroup(4L);
		assertNotNull(list);	
	}
}
