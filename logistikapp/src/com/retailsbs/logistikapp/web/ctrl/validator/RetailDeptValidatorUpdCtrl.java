package com.retailsbs.logistikapp.web.ctrl.validator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.retailsbs.logistikapp.retail.dto.UpdRetailDeptDTO;

public class RetailDeptValidatorUpdCtrl implements Validator {
	
	protected final Log logger = LogFactory.getLog(getClass());

	@SuppressWarnings("rawtypes")
	@Override
	public boolean supports(Class state) {
		return state.equals(UpdRetailDeptDTO.class);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		UpdRetailDeptDTO dto = (UpdRetailDeptDTO) arg0;

		if (dto == null) {
			arg1.reject("error.nullpointer", "Null data received");
		} else {
			
			String name = dto.getName();
			String contact = dto.getContact();
			String phone = dto.getContact();
			String email = dto.getEmail();
			logger.debug("name=" + name);
			logger.debug("contact=" + contact);
			logger.debug("phone=" + phone);
			logger.debug("email=" + email);

			// Se controla que se ingrese un valor
			if ( name == "" ) arg1.rejectValue("name", "error.name", "Ingresa un nombre para el comercio");
			if ( contact == "" ) arg1.rejectValue("contact", "error.contact", "Ingresa un nombre de contacto para el departamento");
			if ( phone == "" ) arg1.rejectValue("phone", "error.phone", "Ingresa un tel&eacute;fono para el departamento");
			if ( email == "" ) arg1.rejectValue("email", "error.email", "Ingresa un correo electr&oacute;nico");
		}
	}
}
