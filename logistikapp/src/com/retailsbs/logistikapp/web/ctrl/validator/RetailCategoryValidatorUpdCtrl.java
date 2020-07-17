package com.retailsbs.logistikapp.web.ctrl.validator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.retailsbs.logistikapp.retail.dto.UpdRetailCategoryDTO;

public class RetailCategoryValidatorUpdCtrl implements Validator {
	
	protected final Log logger = LogFactory.getLog(getClass());

	@SuppressWarnings("rawtypes")
	@Override
	public boolean supports(Class retailCategory) {
		return retailCategory.equals(UpdRetailCategoryDTO.class);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		UpdRetailCategoryDTO dto = (UpdRetailCategoryDTO) arg0;

		if (dto == null) {
			arg1.reject("error.nullpointer", "Null data received");
		} else {

			Long id_retail_category = dto.getId_retail_category();
			String name = dto.getName();
			String code = dto.getCode();
			logger.debug("id_retail_category=" + id_retail_category);
			logger.debug("name=" + name);
			logger.debug("code=" + code);

			// Se controla que se ingrese un valor
			if (name == "") arg1.rejectValue("name", "error.code", "Debes ingresar un nombre");
			if (code == "") arg1.rejectValue("code", "error.code", "Debes ingresar un c&oacute;digo");

			// TODO controlar que el c—digo no est‡ definido para otra categoria de comercio
			
		}
	}
}
