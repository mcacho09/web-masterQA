package com.retailsbs.logistikapp.user.test.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.retailsbs.logistikapp.user.dao.FinderAccessDAO;
import com.retailsbs.logistikapp.user.dto.AccessTinyDTO;
import com.retailsbs.logistikapp.user.dto.AccessTinyExtDTO;
import com.retailsbs.logistikapp.user.test.base.BaseTestJunit;

public class FinderAccessDaoTest extends BaseTestJunit {

	@Autowired
	private FinderAccessDAO dao;

	public void test_getListMessageNoReadByIdUser() {
		List<AccessTinyDTO> list = dao.getAccessSupplierByIdUser(1L);
		assertNotNull(list);
	}
	
	public void test_getAccessSupplierExtByIdUser() {
		List<AccessTinyExtDTO> list = dao.getAccessSupplierExtByIdUser(1L);
		assertNotNull(list);
	}
	
	public void test_delAccessByIdStore() {
		dao.delAccessByIdStore(1L);
	}
	
	public void test_delAccessByIdSupplier() {
		int row = dao.delAccessByIdSupplier(2L);
		assertTrue(row>0);
	}
	
	@Test
	public void test_delAccessByIdUser() {
		int row = dao.delAccessByIdUser(100L);
		assertTrue(row>0);
	}
	
}
