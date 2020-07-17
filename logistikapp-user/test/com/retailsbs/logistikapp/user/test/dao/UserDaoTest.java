package com.retailsbs.logistikapp.user.test.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.retailsbs.logistikapp.user.dao.UserDAO;
import com.retailsbs.logistikapp.user.domain.User;
import com.retailsbs.logistikapp.user.test.base.BaseTestJunit;

public class UserDaoTest extends BaseTestJunit {

	@Autowired
	private UserDAO dao;

	public void	test_insert(){
		User record = new User();
		record.setActive("S");
		record.setCreated(new Date());
		record.setEmail("email");
		record.setImage("image");
		record.setLogin("login");
		record.setModified(new Date());
		record.setOrderby(1);
		record.setPasswd("passwd");
		record.setProfile("pro");
		record.setUserlogin("userlogin");
		record.setUsername("username");
		
		Long id = dao.insert(record);
		assertNotNull(id);
	}
	
	public void test_updateByPrimaryKeySelective() {
		User record = new User();
		record.setId_user(10010L);
		record.setImage("//image");
		
		int i = dao.updateByPrimaryKeySelective(record);
		assertTrue(i>0);
	}
	
	@Test
	public void	test_selectByPrimaryKey(){
		User user = dao.selectByPrimaryKey(10010L);
		assertNotNull(user);
	}
	
	
//	public void crud() {
//		User u = new User();
//		u.setCreated(new Date());
//		u.setModified(new Date());
//		u.setLogin("ADM");
//		u.setOrderby(999);
//		u.setActive("N");
//		u.setUsername("Administrador");
//		u.setUserlogin("admin");
//		u.setPasswd("admin");
//		u.setProfile("ADM");
//		u.setEmail("admin2@logistikapp.com");
//		
//		// insert
//		Long id = dao.insert(u);
//		assertNotNull("ID null",id);
//		
//		// select
//		User u2 = dao.selectByPrimaryKey(id);
//		assertNotNull(u2);
//		
//		// update
//		u2.setModified(new Date());
//		u2.setActive("S");
//		int i = dao.updateByPrimaryKey(u2);
//		assertTrue(i>0);
//		
//		// update seletive
//		User u3 = new User();
//		u3.setId_user(u2.getId_user());
//		u3.setActive("N");
//		u3.setModified(new Date());
//		i = dao.updateByPrimaryKeySelective(u3);
//		assertTrue(i>0);
//		
//	}
	
}
