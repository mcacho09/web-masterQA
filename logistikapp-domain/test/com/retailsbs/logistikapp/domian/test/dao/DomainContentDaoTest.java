package com.retailsbs.logistikapp.domian.test.dao;

import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.retailsbs.logistikapp.domain.dao.DomainContentDAO;
import com.retailsbs.logistikapp.domain.domain.DomainContent;
import com.retailsbs.logistikapp.domain.test.base.BaseTestJunit;

public class DomainContentDaoTest extends BaseTestJunit {

	@Autowired
	private DomainContentDAO dao;

	public void test_insert() {
		DomainContent record = new DomainContent();
		record.setActive("A");
		record.setCode("C");
		record.setCreated(new Date());
		record.setId_domain(1L);
		record.setLogin("admin");
		record.setModified(new Date());
		record.setName("dao");
		record.setOrderby(10);
		record.setParam("pa");
		record.setValue("value");
		
		Long id = dao.insert(record);
		assertNotNull(id);
	}
	
	public void test_updateByPrimaryKeySelective() {
		DomainContent record = new DomainContent();
		record.setId_domain_content(1L);
		record.setName("dao upd");
		record.setModified(new Date());
		
		int i = dao.updateByPrimaryKeySelective(record );
		assertNotNull(i);
	}
	
	@Test
	public void test_selectByPrimaryKey() {
		DomainContent dom = dao.selectByPrimaryKey(1L);
		assertNotNull(dom);
	}
}
