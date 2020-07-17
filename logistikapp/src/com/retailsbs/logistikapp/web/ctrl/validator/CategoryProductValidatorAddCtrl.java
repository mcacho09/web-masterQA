package com.retailsbs.logistikapp.web.ctrl.validator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.retailsbs.logistikapp.financial.dto.AddCategoryProductDTO;



public class CategoryProductValidatorAddCtrl implements Validator {

	protected final Log logger = LogFactory.getLog(getClass());

	@SuppressWarnings("rawtypes")
	@Override
	public boolean supports(Class categoryProduct) {
		return categoryProduct.equals(AddCategoryProductDTO.class);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		AddCategoryProductDTO dto = (AddCategoryProductDTO) arg0;

		if (dto == null) {
			arg1.reject("error.nullpointer", "Null data received");
		} else {

			String name = dto.getName();
			String code = dto.getCode();
			logger.debug("name=" + name);
			logger.debug("code=" + code);

			// Se controla que se ingrese un valor
			if (name == "") arg1.rejectValue("name", "error.code", "Debes ingresar un nombre");
			if (code == "") arg1.rejectValue("code", "error.code", "Debes ingresar un c&oacute;digo");

			// TODO controlar que el c�digo no est� definido para otra categoria de producto

		}

	}

}
