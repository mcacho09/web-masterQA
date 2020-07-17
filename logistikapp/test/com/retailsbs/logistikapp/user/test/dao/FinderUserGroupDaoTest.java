package com.retailsbs.logistikapp.user.test.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.retailsbs.logistikapp.user.dao.FinderUserGroupDAO;
import com.retailsbs.logistikapp.user.dto.UserGroupDTO;
import com.retailsbs.logistikapp.user.test.base.BaseTestJunit;

public class FinderUserGroupDaoTest extends BaseTestJunit {

	@Autowired
	private FinderUserGroupDAO dao;
	
	public void test_getUserByCriteria() {
		List<UserGroupDTO> list = dao.getUserByIdGroup(2L);
		assertNotNull(list);
	}
	
	@Test
	public void test_delUserGrpByIdGrp() {
		int row = dao.delUserGrpByIdGrp(42L);
		assertTrue(row>0);
	}
	
	
}
