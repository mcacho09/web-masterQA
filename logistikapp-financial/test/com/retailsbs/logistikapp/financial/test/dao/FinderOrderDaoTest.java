package com.retailsbs.logistikapp.financial.test.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.retailsbs.logistikapp.financial.dao.FinderOrderDAO;
import com.retailsbs.logistikapp.financial.dto.ReportExtDTO;
import com.retailsbs.logistikapp.financial.dto.ReportExtSearchCriteria;
import com.retailsbs.logistikapp.financial.test.base.BaseTestJunit;

public class FinderOrderDaoTest extends BaseTestJunit {

	@Autowired
	private FinderOrderDAO dao;

	public void test_delOrderByIdSupplier() {
		int row = dao.delOrderByIdSupplier(1L);
		assertTrue(row>0);
	}
	
	@Test
	public void getReportExtByDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
		Date fini = null;
		Date ffin = null;
		
		try {
			fini = sdf.parse("01/08/2016 00:00:00");
			ffin = sdf.parse("31/08/2016 23:59:59");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		ReportExtSearchCriteria dto = new ReportExtSearchCriteria();
		dto.setFini(fini);
		dto.setFfin(ffin);
		dto.setId_supplier(166L);
		List<ReportExtDTO> list = dao.getReportExtByDate(dto);
		assertTrue("Hay datos?", list.size() > 0);
		assertNotNull("Lista null?", list);
	}
	
}
