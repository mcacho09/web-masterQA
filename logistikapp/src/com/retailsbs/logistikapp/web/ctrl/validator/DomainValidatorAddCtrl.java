package com.retailsbs.logistikapp.web.ctrl.validator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.retailsbs.logistikapp.domain.dto.AddDomainDTO;
import com.retailsbs.logistikapp.domain.dto.DomainAvaibleSearchCriteria;
import com.retailsbs.logistikapp.domain.exception.DomainDuplicadoException;
import com.retailsbs.logistikapp.domain.service.DomainService;

public class DomainValidatorAddCtrl implements Validator {
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	private DomainService domainService;
	private String ACTIVE;
	
	public void setDomainService(DomainService domainService) {
		this.domainService = domainService;
	}
	public void setACTIVE(String aCTIVE) {
		ACTIVE = aCTIVE;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean supports(Class domain) {
		return domain.equals(AddDomainDTO.class);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		AddDomainDTO dto = (AddDomainDTO) arg0;

		if( dto == null ) {
			arg1.reject("error.nullpointer", "Null data received");
		}
		else {
			
			String name = dto.getName();
			String code = dto.getCode();
			logger.debug("name="+name);
			logger.debug("code="+code);
			
			// Se controla que se ingrese un valor
			if( name == "" ) arg1.rejectValue("name", "error.code","Ingresa nombre");
			if( code == "" ) arg1.rejectValue("code", "error.code","Ingresa codigo");
			
			// Se valida que code no este duplicado
			DomainAvaibleSearchCriteria dto_dom = new DomainAvaibleSearchCriteria();
			dto_dom.setActive(ACTIVE);
			dto_dom.setCode(code);
			
			try {
				domainService.getDomainAvaibleByCriteria(dto_dom);
			} catch (DomainDuplicadoException e) {
				arg1.rejectValue("code", "error.code",e.getMessage());
			}
			
		}
		
	}

}
