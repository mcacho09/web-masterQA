package com.retailsbs.logistikapp.retail.test.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.retailsbs.logistikapp.retail.dao.FinderRetailDAO;
import com.retailsbs.logistikapp.retail.dto.QtyStrCheckByRetai;
import com.retailsbs.logistikapp.retail.dto.QtyStrCheckByRetailDTO;
import com.retailsbs.logistikapp.retail.dto.RetailDTO;
import com.retailsbs.logistikapp.retail.dto.RetailSearchCriteria;
import com.retailsbs.logistikapp.retail.test.base.BaseTestJunit;

public class FinderRetailDaoTest extends BaseTestJunit {

	@Autowired
	private FinderRetailDAO dao;

	public void test_getRetailByCriteria() {
		RetailSearchCriteria dto = new RetailSearchCriteria();
		dto.setId_supplier(1L);
		dto.setActive("S");

		List<RetailDTO> list = dao.getRetailByCriteria(dto);
		assertNotNull(list);
	}
	
	public void test_getRetailExtById() {
		RetailDTO ret = dao.getRetailExtById(100L);
		assertNotNull(ret);
	}
	
	public void test_getRetailCountStrCheck() throws ParseException {
		SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date fini = sd.parse("16/11/2015 00:00:00");
		Date ffin = sd.parse("22/11/2015 23:59:59");
		QtyStrCheckByRetai dto = new QtyStrCheckByRetai();
		dto.setFini(fini);
		dto.setFfin(ffin);
		dto.setId_supplier(1L);
		
		List<QtyStrCheckByRetailDTO> list = dao.getRetailCountStrCheck(dto);
		assertNotNull(list);
	}
	
	@Test
	public void test_delRetailByIdSupplier() {
		int row = dao.delRetailByIdSupplier(2L);
		assertTrue(row>0);
	}

}
