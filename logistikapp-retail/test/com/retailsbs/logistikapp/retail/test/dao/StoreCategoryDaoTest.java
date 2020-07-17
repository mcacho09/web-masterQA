package com.retailsbs.logistikapp.retail.test.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.retailsbs.logistikapp.retail.dao.StoreCategoryDAO;
import com.retailsbs.logistikapp.retail.domain.StoreCategory;
import com.retailsbs.logistikapp.retail.dto.StoreCategoryExample;
import com.retailsbs.logistikapp.retail.test.base.BaseTestJunit;

public class StoreCategoryDaoTest extends BaseTestJunit {

	@Autowired
	private StoreCategoryDAO dao;

	public void test_insert(){
		StoreCategory record = new StoreCategory();
		record.setActive("S");
		record.setCode("1234567890");
		record.setCreated(new Date());
		record.setId_supplier(1L);
		record.setModified(new Date());
		record.setName("nombre categoria store dos");
		record.setOrderby(2);
		
		Long id = dao.insert(record);
		assertNotNull(id);
	}
	
	public void test_updateByPrimaryKeySelective() {
		StoreCategory record = new StoreCategory();
		record.setId_store_category(4L);
		record.setName("nuevo nombre");
		
		int row = dao.updateByPrimaryKeySelective(record);
		assertTrue(row>0);
	}
	
	public void test_selectByPrimaryKey() {
		StoreCategory sc = dao.selectByPrimaryKey(4L);
		assertNotNull(sc);
	}
	
	@Test
	public void test_selectByExample() {
		StoreCategoryExample example = new StoreCategoryExample();
		
		List<StoreCategory> list = dao.selectByExample(example);
		assertNotNull(list);
	}
	
}
