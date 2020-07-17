package com.retailsbs.logistikapp.domian.test.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.retailsbs.logistikapp.domain.dao.FinderDomainContentDAO;
import com.retailsbs.logistikapp.domain.dto.DomainContentDTO;
import com.retailsbs.logistikapp.domain.dto.DomainContentSearchCriteria;
import com.retailsbs.logistikapp.domain.test.base.BaseTestJunit;

public class FinderDomainContentDaoTest extends BaseTestJunit {

	@Autowired
	private FinderDomainContentDAO dao;

	public void test_getDomainContentByCriteria() {
		DomainContentSearchCriteria dto = new DomainContentSearchCriteria();
		dto.setActive("S");
		dto.setId_domain(100L);
		
		List<DomainContentDTO> list = dao.getDomainContentByCriteria(dto);
		assertNotNull(list);
	}
	
	@Test
	public void test_getDomainContentTotalByCriteria() {
		DomainContentSearchCriteria dto = new DomainContentSearchCriteria();
		dto.setActive("S");
		dto.setId_domain(100L);
		
		int i = dao.getDomainContentTotalByCriteria(dto);
		assertTrue(i>0);
	}
}
