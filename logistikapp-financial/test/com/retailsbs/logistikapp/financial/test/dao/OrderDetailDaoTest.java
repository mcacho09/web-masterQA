package com.retailsbs.logistikapp.financial.test.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.retailsbs.logistikapp.financial.dao.OrderDetailDAO;
import com.retailsbs.logistikapp.financial.domain.OrderDetail;
import com.retailsbs.logistikapp.financial.dto.OrderDetailExample;
import com.retailsbs.logistikapp.financial.test.base.BaseTestJunit;

public class OrderDetailDaoTest extends BaseTestJunit {

	@Autowired
	private OrderDetailDAO dao;

	public void test_insert(){
		OrderDetail record = new OrderDetail();
		record.setId_order(100L);
		record.setId_product(100L);
		record.setPrice_sug(1520.00);
		record.setQuantity(120);
		
		Long id = dao.insert(record);
		assertNotNull(id);
	}
	
	public void test_upd() {
		OrderDetail record = new OrderDetail();
		record.setId_order_detail(100L);
		record.setQuantity(20);
		
		int row = dao.updateByPrimaryKeySelective(record);
		assertTrue(row > 0);
	}
	
	public void test_selectByPrimaryKey() {
		OrderDetail orderDetail = dao.selectByPrimaryKey(100L);
		assertNotNull(orderDetail);
	}
	
	@Test
	public void test_all() {
		OrderDetailExample example = new OrderDetailExample();
		List<OrderDetail> list = dao.selectByExample(example);
		assertNotNull(list);
	}
	
}
