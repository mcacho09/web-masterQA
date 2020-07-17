package com.retailsbs.logistikapp.web.ctrl.validator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.retailsbs.logistikapp.retail.dto.CityCodeAvaibleSearchCriteria;
import com.retailsbs.logistikapp.retail.dto.UpdCityDTO;
import com.retailsbs.logistikapp.retail.exception.CityCodeDuplicateException;
import com.retailsbs.logistikapp.retail.service.RetailService;

public class CityValidatorUpdCtrl implements Validator {
	protected final Log logger = LogFactory.getLog(getClass());

	private RetailService retailService;
	private String ACTIVE;

	public void setACTIVE(String aCTIVE) {
		ACTIVE = aCTIVE;
	}

	public void setRetailService(RetailService retailService) {
		this.retailService = retailService;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean supports(Class city) {
		return city.equals(UpdCityDTO.class);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		UpdCityDTO dto = (UpdCityDTO) arg0;

		if (dto == null) {
			arg1.reject("error.nullpointer", "Null data received");
		} else {

			String name = dto.getName();
			String code = dto.getCode();
			Integer orderby = dto.getOrderby();
			Long id_state = dto.getId_state();
			logger.debug("name=" + name);
			logger.debug("code=" + code);
			logger.debug("orderby=" + orderby);
			logger.debug("id_state=" + id_state);

			// Se controla que se ingrese un valor
			if (name == "")
				arg1.rejectValue("name", "error.code", "Ingresa estado");
			if (code == "")
				arg1.rejectValue("code", "error.code", "Ingresa un codigo");
			if (orderby == null)
				arg1.rejectValue("orderby", "error.code", "Ingresa orden de listado");
			if (id_state == null)
				arg1.rejectValue("id_state", "error.code", "Selecciona un estado");

			// Se valida que code no este duplicado

			CityCodeAvaibleSearchCriteria dto_cty = new CityCodeAvaibleSearchCriteria();
			dto_cty.setActive(ACTIVE);
			dto_cty.setCode(code);
			dto_cty.setId_city(dto.getId_city());

			try {
				retailService.getCityCodeAvaibleByCriteria(dto_cty);
			} catch (CityCodeDuplicateException e) {
				arg1.rejectValue("code", "error.code", e.getMessage());
			}
		}
	}
}
