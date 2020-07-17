package com.retailsbs.logistikapp.user.notification.test.service;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.retailsbs.logistikapp.user.dto.AddNotificationDTO;
import com.retailsbs.logistikapp.user.notification.domain.ListNotificationDTO;
import com.retailsbs.logistikapp.user.notification.service.UserNotificationService;
import com.retailsbs.logistikapp.user.notification.test.base.BaseTestJunit;



public class service extends BaseTestJunit {

	@Autowired
	private UserNotificationService usernotificationservice;
	
	

	public void test() {
		AddNotificationDTO dtn = new AddNotificationDTO();
		dtn.setCreated(new Date());
		dtn.setIcon("fa fa-building");
		dtn.setId_user(218L); 
		dtn.setMessage("Nuevo mensaje grupal de <span class='label label-info'>"+"Grp Lgk"+"</span> creado");
		dtn.setPriority("1");
		dtn.setId_supplier(166L);
		// Se persiste la notificacion
		
		List<Integer> list = new LinkedList<Integer>();
		list.add(218);
		list.add(223);
		list.add(225);
		list.add(222);
		list.add(230);
		
		Long idn = usernotificationservice.createNotificationWithList(dtn, list);

		assertNotNull(idn);
	}

	public void teste(){
		List<ListNotificationDTO> se = usernotificationservice.searchNotification(218L, "c");
		
		for(ListNotificationDTO i : se){
			System.out.println(i.getLink());
		}

	}
	
	@Test
	public void search(){
		AddNotificationDTO dto = new AddNotificationDTO();
		dto.setCreated(new Date());
		dto.setIcon("fa fa-building");
		dto.setId_supplier(177l);
		dto.setId_user(255l);
		dto.setMessage("Pruebas y más pruebas");
		dto.setPriority("1");
		dto.setLink("alertlist.htm");
		long id_not = usernotificationservice.createNotification(dto, "001");
		System.out.println("Resp ---> " + id_not);
	}

}
