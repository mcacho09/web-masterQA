package com.retailsbs.logistikapp.web.ctrl.validator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.retailsbs.logistikapp.retail.dto.AddCityDTO;
import com.retailsbs.logistikapp.retail.dto.CityCodeAvaibleSearchCriteria;
import com.retailsbs.logistikapp.retail.exception.CityCodeDuplicateException;
import com.retailsbs.logistikapp.retail.service.RetailService;

/**
 * Controlador de clase de validacion al agregar ciudad
 * 
 * @author JC
 * @since 22-12-2014
 */
public class CityValidatorAddCtrl implements Validator {

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
		return city.equals(AddCityDTO.class);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		AddCityDTO dto = (AddCityDTO) arg0;

		if (dto == null) {
			arg1.reject("error.nullpointer", "Null data received");
		} else {

			String name = dto.getName();
			String code = dto.getCode();
			Integer orderby = dto.getOrderby();
			logger.debug("name=" + name);
			logger.debug("code=" + code);
			logger.debug("orderby=" + orderby);

			// Se controla que se ingrese un valor
			if (name == "")
				arg1.rejectValue("name", "error.code", "Ingresa estado");
			if (code == "")
				arg1.rejectValue("code", "error.code", "Ingresa un codigo");
			if (orderby == null)
				arg1.rejectValue("orderby", "error.code", "Ingresa orden de listado");

			// Se valida que code no este duplicado

			CityCodeAvaibleSearchCriteria dto_cty = new CityCodeAvaibleSearchCriteria();
			dto_cty.setActive(ACTIVE);
			dto_cty.setCode(code);

			try {
				retailService.getCityCodeAvaibleByCriteria(dto_cty);
			} catch (CityCodeDuplicateException e) {
				arg1.rejectValue("code", "error.code", e.getMessage());
			}

		}

	}

}
