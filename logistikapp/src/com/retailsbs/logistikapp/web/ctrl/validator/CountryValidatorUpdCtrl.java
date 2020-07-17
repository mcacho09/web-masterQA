package com.retailsbs.logistikapp.web.ctrl.validator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.retailsbs.logistikapp.retail.dto.CountryAvaibleSearchCriteria;
import com.retailsbs.logistikapp.retail.dto.UpdCountryDTO;
import com.retailsbs.logistikapp.retail.exception.CountryCodeDuplicateException;
import com.retailsbs.logistikapp.retail.service.RetailService;

public class CountryValidatorUpdCtrl implements Validator {
	
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
	public boolean supports(Class country) {
		return country.equals(UpdCountryDTO.class);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		UpdCountryDTO dto = (UpdCountryDTO) arg0;

		if (dto == null) {
			arg1.reject("error.nullpointer", "Null data received");
		} else {

			Long id_country = dto.getId_country();
			String name = dto.getName();
			String code = dto.getCode();
			logger.debug("id_country=" + id_country);
			logger.debug("name=" + name);
			logger.debug("code=" + code);

			// Se controla que se ingrese un valor
			if (name == "") arg1.rejectValue("name", "error.code", "Ingresa el nombre del pais");
			if (code == "") arg1.rejectValue("code", "error.code", "Ingresa un c—digo para el pais");

			// Se valida que code no este duplicado
			CountryAvaibleSearchCriteria dto_con = new CountryAvaibleSearchCriteria();
			dto_con.setId_country(id_country);
			dto_con.setCode(code);
			dto_con.setActive(ACTIVE);
			try {
				retailService.getCountryAvaibleByCriteria(dto_con);
			} catch (CountryCodeDuplicateException e) {
				arg1.rejectValue("code", "error.code", e.getMessage());
			}
		}
	}
}
