package com.retailsbs.logistikapp.web.ctrl.validator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.retailsbs.logistikapp.financial.dto.UpdBrandProductDTO;

public class MarkProductValidatorUpdCtrl implements Validator {
	
	protected final Log logger = LogFactory.getLog(getClass());

	@SuppressWarnings("rawtypes")
	@Override
	public boolean supports(Class brand) {
		return brand.equals(UpdBrandProductDTO.class);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		UpdBrandProductDTO dto = (UpdBrandProductDTO) arg0;

		if (dto == null) {
			arg1.reject("error.nullpointer", "Null data received");
		} else {

			Long id_brand = dto.getId_brand();
			String name = dto.getName();
			logger.debug("id_brand=" + id_brand);
			logger.debug("name=" + name);

			// Se controla que se ingrese un valor
			if (name == "") arg1.rejectValue("name", "error.code", "Debes ingresar un nombre");
		}
	}
}