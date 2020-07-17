package com.retailsbs.logistikapp.logistic.test.dao;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.retailsbs.logistikapp.logistic.dao.FinderTravelDAO;
import com.retailsbs.logistikapp.logistic.dto.TravelAndStatusStr;
import com.retailsbs.logistikapp.logistic.dto.TravelByIdStoreDTO;
import com.retailsbs.logistikapp.logistic.dto.TravelByIdStoreSearch;
import com.retailsbs.logistikapp.logistic.dto.TravelDTO;
import com.retailsbs.logistikapp.logistic.dto.TravelSearchCriteria;
import com.retailsbs.logistikapp.logistic.dto.TrvStatusStrSearchCriteria;
import com.retailsbs.logistikapp.logistic.test.base.BaseTestJunit;

public class FinderTravelDAOTest extends BaseTestJunit {

	@Autowired
	private FinderTravelDAO dao;
	
	public void getTravelByCriteria() {
		Date fini = null;
		Date ffin = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		try {
			fini = sdf.parse("01/02/2016 00:00:00");
			ffin = sdf.parse("31/03/2016 23:59:59");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		List<String> status = new ArrayList<String>();
		status.add("CRE");
		status.add("PRO");
		status.add("REV");
		status.add("TRA");
		
		TravelSearchCriteria dto = new TravelSearchCriteria();
		dto.setId_supplier(110L);
		dto.setFini(fini);
		dto.setFfin(ffin);
		dto.setStatus(status);
//		dto.setId_user(175L);
//		dto.setId_route(1L);
		
		List<TravelDTO> list= dao.getTravelByCriteria(dto);
		assertNotNull(list);
	}
	
	public void test_getTravelByIdStore() throws ParseException {
		SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");
		String schedule = "20-10-2015";
		
		TravelByIdStoreSearch dto = new TravelByIdStoreSearch();
		dto.setId_store(8L);
		dto.setSchedule(sd.parse(schedule));
		
		List<TravelByIdStoreDTO> list = dao.getTravelByIdStore(dto);
		assertNotNull(list);
	}
	
	@Test
	public void test_getTravelAndStatusStrByIdStore() {
		TrvStatusStrSearchCriteria dto = new TrvStatusStrSearchCriteria();
		dto.setId_supplier(134L);
		dto.setId_store(14183L);
		dto.setLim_inf(1);
		dto.setLim_sup(3);
		
		List<TravelAndStatusStr> list = dao.getTravelAndStatusStrByIdStore(dto);
		assertNotNull(list);
	}
	
	public void test_delTravelByIdRoute() {
		int row = dao.delTravelByIdRoute(1L);
		assertTrue(row>0);
	}
	
}
