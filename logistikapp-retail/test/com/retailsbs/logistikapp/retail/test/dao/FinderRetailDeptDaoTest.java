package com.retailsbs.logistikapp.retail.test.dao;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.retailsbs.logistikapp.retail.dao.FinderRetailDeptDAO;
import com.retailsbs.logistikapp.retail.test.base.BaseTestJunit;

public class FinderRetailDeptDaoTest extends BaseTestJunit {

	@Autowired
	private FinderRetailDeptDAO dao;
	
	@Test
	public void test_delRetailDepByIdRetail() {
		int row = dao.delRetailDepByIdRetail(6L);
		assertTrue(row>0);
	}

}
