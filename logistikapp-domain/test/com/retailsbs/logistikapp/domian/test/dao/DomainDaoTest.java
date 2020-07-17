package com.retailsbs.logistikapp.domian.test.dao;

import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.retailsbs.logistikapp.domain.dao.DomainDAO;
import com.retailsbs.logistikapp.domain.domain.Domain;
import com.retailsbs.logistikapp.domain.test.base.BaseTestJunit;

public class DomainDaoTest extends BaseTestJunit {

	@Autowired
	private DomainDAO dao;

	public void test_insert() {
		Domain record = new Domain();
		record.setActive("A");
		record.setCode("C");
		record.setCreated(new Date());
		record.setLogin("Adimin");
		record.setModified(new Date());
		record.setName("name");
		record.setOrderby(10);
		
		Long id = dao.insert(record);
		assertNotNull(id);
	}
	
	public void test_updateByPrimaryKeySelective() {
		Domain record = new Domain();
		record.setId_domain(1L);
		record.setName("update doa");
		
		int i = dao.updateByPrimaryKeySelective(record);
		assertNotNull(i);
	}
	
	@Test
	public void test_selectByPrimaryKey() {
		Domain domain = dao.selectByPrimaryKey(1L);
		assertNotNull(domain);
	}
}
