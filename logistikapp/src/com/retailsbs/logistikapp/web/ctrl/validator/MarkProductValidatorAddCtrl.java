package com.retailsbs.logistikapp.web.ctrl.validator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.retailsbs.logistikapp.financial.dto.AddBrandProductDTO;


public class MarkProductValidatorAddCtrl implements Validator {

	protected final Log logger = LogFactory.getLog(getClass());

	@SuppressWarnings("rawtypes")
	@Override
	public boolean supports(Class categoryProduct) {
		return categoryProduct.equals(AddBrandProductDTO.class);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		AddBrandProductDTO dto = (AddBrandProductDTO) arg0;

		if (dto == null) {
			arg1.reject("error.nullpointer", "Null data received");
		} else {

			String name = dto.getName();
			logger.debug("name=" + name);

			// Se controla que se ingrese un valor
			if (name == "") arg1.rejectValue("name", "error.code", "Debes ingresar un nombre");
		}

	}

}
