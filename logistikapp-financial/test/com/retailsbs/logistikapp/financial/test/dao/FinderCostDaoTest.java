package com.retailsbs.logistikapp.financial.test.dao;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.retailsbs.logistikapp.financial.dao.FinderCostDAO;
import com.retailsbs.logistikapp.financial.test.base.BaseTestJunit;

public class FinderCostDaoTest extends BaseTestJunit {

	@Autowired
	private FinderCostDAO finder;

	@Test
	public void test_delCostByIdSupplier() {
		int row = finder.delCostByIdSupplier(1L);
		assertTrue(row>0);
	}
	
}
