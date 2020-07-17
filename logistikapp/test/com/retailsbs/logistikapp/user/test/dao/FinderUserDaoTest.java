package com.retailsbs.logistikapp.user.test.dao;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.retailsbs.logistikapp.user.dao.FinderUserDAO;
import com.retailsbs.logistikapp.user.dto.AdminUserDTO;
import com.retailsbs.logistikapp.user.dto.AdminUserSearchCriteria;
import com.retailsbs.logistikapp.user.dto.UserDTO;
import com.retailsbs.logistikapp.user.dto.UserSearchCriteria;
import com.retailsbs.logistikapp.user.test.base.BaseTestJunit;

public class FinderUserDaoTest extends BaseTestJunit {

	@Autowired
	private FinderUserDAO dao;
	
	public void test_getUserByCriteria() {
		UserSearchCriteria dto = new UserSearchCriteria();
		dto.setId_supplier(2L);
		
		List<UserDTO> list = dao.getUserByCriteria(dto);
		assertNotNull(list);
	}
	
	@Test
	public void test_getAdminUserList() {
		AdminUserSearchCriteria dto = new AdminUserSearchCriteria();
		List<AdminUserDTO> list = dao.getAdminUserListByCriteria(dto);
		assertNotNull(list);
	}
	
}
