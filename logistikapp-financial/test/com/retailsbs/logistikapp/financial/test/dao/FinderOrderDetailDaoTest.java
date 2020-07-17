package com.retailsbs.logistikapp.financial.test.dao;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.retailsbs.logistikapp.financial.dao.FinderOrderDetailDAO;
import com.retailsbs.logistikapp.financial.test.base.BaseTestJunit;

public class FinderOrderDetailDaoTest extends BaseTestJunit {

	@Autowired
	private FinderOrderDetailDAO finder_order_detail;

	public void test_delOrderDetailByIdOrder() {
		int row = finder_order_detail.delOrderDetailByIdOrder(3L);
		assertTrue(row>0);
	}
	
	@Test
	public void test_delOrderDetailByIdProduct() {
		int row = finder_order_detail.delOrderDetailByIdProduct(1L);
		assertTrue(row>0);
	}
	
}
