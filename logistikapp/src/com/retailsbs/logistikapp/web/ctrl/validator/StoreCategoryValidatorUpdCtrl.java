package com.retailsbs.logistikapp.web.ctrl.validator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.retailsbs.logistikapp.retail.dto.UpdStoreCategoryDTO;

public class StoreCategoryValidatorUpdCtrl implements Validator {
	protected final Log logger = LogFactory.getLog(getClass());

	@SuppressWarnings("rawtypes")
	@Override
	public boolean supports(Class storeCategory) {
		return storeCategory.equals(UpdStoreCategoryDTO.class);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		UpdStoreCategoryDTO dto = (UpdStoreCategoryDTO) arg0;

		if (dto == null) {
			arg1.reject("error.nullpointer", "Null data received");
		} else {

			Long id_store_category = dto.getId_store_category();
			String name = dto.getName();
			String code = dto.getCode();
			logger.debug("id_store_category=" + id_store_category);
			logger.debug("name=" + name);
			logger.debug("code=" + code);

			// Se controla que se ingrese un valor
			if (name == "") arg1.rejectValue("name", "error.code", "Debes ingresar un nombre");
			if (code == "") arg1.rejectValue("code", "error.code", "Debes ingresar un c&oacute;digo");
			
			// TODO controlar que el c—digo no est‡ definido para otra categoria de comercio

		}
	}
}
