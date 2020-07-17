package com.retailsbs.logistikapp.web.ctrl.validator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.retailsbs.logistikapp.retail.dto.AddStateDTO;
import com.retailsbs.logistikapp.retail.dto.StateAvaibleSearchCriteria;
import com.retailsbs.logistikapp.retail.exception.StateDuplicateException;
import com.retailsbs.logistikapp.retail.service.RetailService;

public class StateValidatorAddCtrl implements Validator {

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
	public boolean supports(Class state) {
		return state.equals(AddStateDTO.class);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		AddStateDTO dto = (AddStateDTO) arg0;

		if (dto == null) {
			arg1.reject("error.nullpointer", "Null data received");
		} else {

			Long id_country = dto.getId_country();
			String name = dto.getName();
			String code = dto.getCode();
			String prefix = dto.getPrefix();
			logger.debug("id_country=" + id_country);
			logger.debug("name=" + name);
			logger.debug("code=" + code);
			logger.debug("prefix=" + prefix);

			// Se controla que se ingrese un valor
			if (id_country == null)
				arg1.rejectValue("id_country", "error.code", "Selecciona un pa’s");
			if (name == "")
				arg1.rejectValue("name", "error.code", "Ingresa un nombre");
			if (code == "")
				arg1.rejectValue("code", "error.code", "Ingresa un codigo");
			if (prefix == "")
				arg1.rejectValue("prefix", "error.code", "Ingresa un prefijo");

			// Se valida que codigo no este duplicado
			StateAvaibleSearchCriteria dto_stt = new StateAvaibleSearchCriteria();
			dto_stt.setCode(code);
			dto_stt.setActive(ACTIVE);

			try {
				retailService.getStateAvaibleByCriteria(dto_stt);
			} catch (StateDuplicateException e) {
				arg1.rejectValue("code", "error.code", e.getMessage());
			}

		}

	}

}
