package com.retailsbs.logistikapp.financial.test.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.retailsbs.logistikapp.financial.dao.OrderDAO;
import com.retailsbs.logistikapp.financial.domain.Order;
import com.retailsbs.logistikapp.financial.dto.OrderExample;
import com.retailsbs.logistikapp.financial.test.base.BaseTestJunit;

public class OrderDaoTest extends BaseTestJunit {

	@Autowired
	private OrderDAO dao;

	public void test_insert(){
		Order record = new Order();
		record.setDelivery(new Date());
		record.setId_supplier(1L);
		record.setInvoice(new Date());
		record.setModified(new Date());
		record.setStatus("st");
		
		Long id = dao.insert(record);
		assertNotNull(id);
	}
	
	public void test_updateByPrimaryKeySelective() {
		Order record = new Order();
		record.setId_order(100L);
		
		int row = dao.updateByPrimaryKeySelective(record);
		assertTrue(row > 0);
	}
	
	public void test_selectByPrimaryKey() {
		Order order = dao.selectByPrimaryKey(100L);
		assertNotNull(order);
	}
	
	@Test
	public void test_selectAll() {
		OrderExample example = new OrderExample();
		List<Order> order = dao.selectByExample(example);
		assertNotNull(order);
	}
	
}
