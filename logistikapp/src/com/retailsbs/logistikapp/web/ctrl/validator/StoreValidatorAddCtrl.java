package com.retailsbs.logistikapp.web.ctrl.validator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.retailsbs.logistikapp.retail.dto.AddStoreDTO;
import com.retailsbs.logistikapp.retail.dto.StoreCodeAvaibleSearchCriteria;
import com.retailsbs.logistikapp.retail.exception.StoreCodeDuplicadeException;
import com.retailsbs.logistikapp.retail.service.RetailService;

public class StoreValidatorAddCtrl implements Validator {

	protected final Log logger = LogFactory.getLog(getClass());

	private RetailService retailService;
	private String ACTIVE;

	public void setRetailService(RetailService retailService) {
		this.retailService = retailService;
	}
	public void setACTIVE(String aCTIVE) {
		ACTIVE = aCTIVE;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean supports(Class store) {
		return store.equals(AddStoreDTO.class);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		AddStoreDTO dto = (AddStoreDTO) arg0;

		if (dto == null) {
			arg1.reject("error.nullpointer", "Null data received");
		} else {

			String name = dto.getName();
			String code = dto.getCode();
			Long id_state = dto.getId_state();
			Long id_city = dto.getId_city();
			Long id_retail = dto.getId_retail();
			logger.debug("name=" + name);
			logger.debug("code=" + code);
			logger.debug("id_state=" + id_state);
			logger.debug("id_city=" + id_city);
			logger.debug("id_retail=" + id_retail);

			// Se controla que se ingrese un valor
			if ( name == "" ) arg1.rejectValue("name", "error.code", "Debes ingresar un nombre de local");
			if ( code == "" ) arg1.rejectValue("code", "error.code", "Debes ingresar un c&oacute;digo");
			if ( id_state == null ) arg1.rejectValue("id_state", "error.code", "Debes seleccionar un estado");
			if ( id_city == null ) arg1.rejectValue("id_city", "error.code", "Debes seleccionar una municipio");
			
			

			// Se valida que el codigo de store no este duplicado
			StoreCodeAvaibleSearchCriteria dto_str = new StoreCodeAvaibleSearchCriteria();
			
			dto_str.setCode(code);
			dto_str.setActive(ACTIVE);
			dto_str.setId_retail(id_retail);
			try {
				retailService.getStoreCodeAvaibleByCriteria(dto_str);
			} catch (StoreCodeDuplicadeException e) {
				arg1.rejectValue("code", "error.code", e.getMessage());
			}

		}

	}

}
