package com.retailsbs.logistikapp.user.test.dao;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.retailsbs.logistikapp.user.dao.FinderGroupDAO;
import com.retailsbs.logistikapp.user.test.base.BaseTestJunit;

public class FinderGroupDaoTest extends BaseTestJunit {

	@Autowired
	private FinderGroupDAO finder;
	
	@Test
	public void test_delGroupByIdUser() {
		int row = finder.delGroupByIdUser(100L);
		assertTrue(row>0);
	}

	
}
