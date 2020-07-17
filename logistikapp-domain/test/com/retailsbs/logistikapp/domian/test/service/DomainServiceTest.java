package com.retailsbs.logistikapp.domian.test.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.retailsbs.logistikapp.domain.domain.Domain;
import com.retailsbs.logistikapp.domain.domain.DomainContent;
import com.retailsbs.logistikapp.domain.dto.AddDomainContentDTO;
import com.retailsbs.logistikapp.domain.dto.AddDomainDTO;
import com.retailsbs.logistikapp.domain.dto.DomainContentDTO;
import com.retailsbs.logistikapp.domain.dto.DomainContentSearchCriteria;
import com.retailsbs.logistikapp.domain.dto.UpdDomainContentDTO;
import com.retailsbs.logistikapp.domain.dto.UpdDomainDTO;
import com.retailsbs.logistikapp.domain.exception.DomainContentNotFoundException;
import com.retailsbs.logistikapp.domain.exception.DomainNotFoundException;
import com.retailsbs.logistikapp.domain.service.DomainService;
import com.retailsbs.logistikapp.domain.test.base.BaseTestJunit;

public class DomainServiceTest extends BaseTestJunit {

	@Autowired
	private DomainService service;
	
	/*
	 * Domain
	 */
	
	public void test_addDomain() {
		AddDomainDTO dto = new AddDomainDTO();
		dto.setActive("A");
		dto.setCode("C");
		dto.setLogin("admin");
		dto.setModified(new Date());
		dto.setName("test service");
		dto.setOrderby(11);
		
		Long id = service.addDomain(dto);
		assertNotNull(id);
	}
	
	public void test_updDomain() throws DomainNotFoundException {
		UpdDomainDTO dto = new UpdDomainDTO();
		dto.setId_domain(2L);
		dto.setName("service test upd");
		
		int i = service.updDomain(dto);
		assertTrue(i>0);
	}

	public void test_getDomainById() throws DomainNotFoundException{
		Domain domain = service.getDomainById(2L);
		assertNotNull(domain);
	}
	
	public void test_getAllDomain() {
		List<Domain> domain = service.getAllDomain();
		assertNotNull(domain);
	}
	
	/*
	 * DomainContent
	 */
	
	public void test_addDomainContent() {
		AddDomainContentDTO dto = new AddDomainContentDTO();
		dto.setActive("S");
		dto.setCode("C");
		dto.setId_domain(2L);
		dto.setLogin("admin");
		dto.setName("test service");
		dto.setOrderby(11);
		dto.setParam("P");
		dto.setValue("value");
		
		Long id = service.addDomainContent(dto);
		assertNotNull(id);
	}
	
	public void test_updDomainContent() throws DomainContentNotFoundException {
		UpdDomainContentDTO dto = new UpdDomainContentDTO();
		dto.setId_domain_content(2L);
		dto.setName("Service test upd");
		
		int i = service.updDomainContent(dto);
		assertTrue(i>0);
	}

	public void test_getDomainContentById() throws DomainContentNotFoundException {
		DomainContent dom_con = service.getDomainContentById(2L);
		assertNotNull(dom_con);
	}
	
	public void test_getAllDomainContent() {
		List<DomainContent> dom_con = service.getAllDomainContent();
		assertNotNull(dom_con);
	}
	
	/*
	 * FINDER DOMAIN CONTENT
	 */
	
	public void test_getDomainContentByCriteria() {
		DomainContentSearchCriteria dto = new DomainContentSearchCriteria();
		dto.setActive("S");
		dto.setId_domain(100L);
		
		List<DomainContentDTO> list = service.getDomainContentByCriteria(dto);
		assertNotNull(list);
	}
	
	@Test
	public void test_getDomainContentTotalByCriteria() {
		DomainContentSearchCriteria dto = new DomainContentSearchCriteria();
		dto.setActive("S");
		dto.setId_domain(100L);
		
		int i = service.getDomainContentTotalByCriteria(dto);
		assertTrue(i>0);
	}
	
}
