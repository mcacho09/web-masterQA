package com.retailsbs.logistikapp.web.ctrl.validator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.retailsbs.logistikapp.retail.dto.LocalityCodeSearchCriteria;
import com.retailsbs.logistikapp.retail.dto.UpdLocalityDTO;
import com.retailsbs.logistikapp.retail.exception.LocalityCodeDuplicadeException;
import com.retailsbs.logistikapp.retail.service.RetailService;

public class LocalityValidatorUpdCtrl implements Validator {
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
	public boolean supports(Class locality) {
		return locality.equals(UpdLocalityDTO.class);
	}

	@Override
	public void validate(Object arg0, Errors arg1){
		UpdLocalityDTO dto = (UpdLocalityDTO) arg0;

		if( dto == null ) {
			arg1.reject("error.nullpointer", "Null data received");
		}
		else {
			
			String name = dto.getName();
			String code = dto.getCode();
			Long id_city = dto.getId_city();
			Integer orderby = dto.getOrderby();
			logger.debug("name="+name);
			logger.debug("code="+code);
			logger.debug("id_city="+id_city);
			logger.debug("orderby="+orderby);
			
			// Se controla que se ingrese un valor
			if( name == "" ) arg1.rejectValue("name", "error.code","Ingresa estado");
			if( code == "" ) arg1.rejectValue("code", "error.code","Ingresa un codigo");
			if( id_city == null ) arg1.rejectValue("id_city", "error.code","Selecciona una ciudad");
			if( orderby == null ) arg1.rejectValue("orderby", "error.code","Ingresa un \"orden\" de acomodo");
			
			// Se valida que code no este duplicado
			
			LocalityCodeSearchCriteria dto_loc = new LocalityCodeSearchCriteria();
			dto_loc.setActive(ACTIVE);
			dto_loc.setCode(code);
			dto_loc.setId_locality(dto.getId_locality());
			
			try {
				retailService.getLocalityCodeAvaibleByCriteria(dto_loc);
			} catch (LocalityCodeDuplicadeException e) {
				arg1.rejectValue("code", "error.code",e.getMessage());
			}
		}
	}
}
