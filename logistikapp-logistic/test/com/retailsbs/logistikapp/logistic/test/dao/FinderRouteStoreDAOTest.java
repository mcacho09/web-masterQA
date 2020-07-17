package com.retailsbs.logistikapp.logistic.test.dao;


import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.retailsbs.logistikapp.logistic.dao.FinderRouteStoreDAO;
import com.retailsbs.logistikapp.logistic.test.base.BaseTestJunit;

public class FinderRouteStoreDAOTest extends BaseTestJunit {

	@Autowired
	private FinderRouteStoreDAO finder;
	
	@Test
	public void test_delRouteStoreByIdRoute() {
		int row = finder.delRouteStoreByIdRoute(21L);
		assertTrue(row > 0);
	}
	
}
