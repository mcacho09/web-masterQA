package com.retails.logistikapp.manager.user.test.service;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.retails.logistikapp.manager.user.test.base.BaseTestJunit;
import com.retailsbs.logistikapp.manager.user.ManagerUserService;
import com.retailsbs.logistikapp.manager.user.dto.AutoRegistroDTO;

public class ManagerUserServiceTest extends BaseTestJunit {

	@Autowired
	private ManagerUserService manager;

	@Test
	public void AddUser() {

		AutoRegistroDTO user = new AutoRegistroDTO();

		user.setUsername("Separación de Métodos");
		user.setUserlogin("DavidRosales");
		user.setPasswd("sepemail");
		user.setEmail("darmz21@hotmail.com");
		user.setName("Farma      ");
		user.setPhone1("4491962728");
		user.setId_plan(2L);
	

		manager.autoregistro(user);

	}

}
