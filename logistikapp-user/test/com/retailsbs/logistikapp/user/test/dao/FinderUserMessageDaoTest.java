package com.retailsbs.logistikapp.user.test.dao;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.retailsbs.logistikapp.user.dao.FinderUserMessageDAO;
import com.retailsbs.logistikapp.user.domain.User;
import com.retailsbs.logistikapp.user.domain.UserMessage;
import com.retailsbs.logistikapp.user.dto.RelUserUsertoDTO;
import com.retailsbs.logistikapp.user.dto.UserMessageDTO;
import com.retailsbs.logistikapp.user.dto.UserMessageGrpDTO;
import com.retailsbs.logistikapp.user.dto.UserSearchCriteria;
import com.retailsbs.logistikapp.user.dto.UserUsertoDTO;
import com.retailsbs.logistikapp.user.test.base.BaseTestJunit;

public class FinderUserMessageDaoTest extends BaseTestJunit {

	@Autowired
	private FinderUserMessageDAO dao;

	public void test_getMessageByCriteria() {
		RelUserUsertoDTO dto = new RelUserUsertoDTO();
		dto.setId_user(2L);
		dto.setId_user_to(1L);
		
		List<UserUsertoDTO> list = dao.getRelUserUserto(dto);
		assertNotNull(list);
	}
	
	
	public void test_getUserMessageByIdUser() {
		List<UserMessageDTO> list = dao.getUserMessageByIdUser(1L);
		assertNotNull(list);
	}
	
	public void test_getAvailableUserByCriteria(){
		UserSearchCriteria dto = new UserSearchCriteria();
		dto.setId_user(1L);
		dto.setId_supplier(1L);		
		List<User> list = dao.getAvailableUserByCriteria(dto);
		logger.debug("--- Probando getAvailableUserByCriteria--->  " + list.size());	
	}
	public void test_getUserLastMessageById(){
		Long id_user = 6L;				
		List<UserMessageDTO> list = dao.getUserLastMessageByIdUser(id_user);
		logger.debug("--- Probando getAvailableUserByCriteria--->  " + list.size());	
	}
	
	public void test_getUserLastMsgGroByIdUser() {
		List<UserMessageGrpDTO> list = dao.getUserLastMsgGroByIdUser(6L);
		assertNotNull(list);
	}
	
	@Test
	public void test_getUserMessageByIdUserId_UserTo() {
		List<UserMessage> list = dao.getUserMessageByIdUserId_UserTo(4L);
		assertNotNull(list);
	}
	
}
