package com.retailsbs.logistikapp.web.ctrl.validator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.retailsbs.logistikapp.domain.dto.AvaibleCodeSearchCriteria;
import com.retailsbs.logistikapp.domain.dto.UpdDomainContentDTO;
import com.retailsbs.logistikapp.domain.exception.DomainContentCodeDuplicateException;
import com.retailsbs.logistikapp.domain.service.DomainService;

public class DomainContentValidatorUpdCtrl implements Validator {
	protected final Log logger = LogFactory.getLog(getClass());
	
	private DomainService domainService;
	private String ACTIVE;
	
	public void setACTIVE(String aCTIVE) {
		ACTIVE = aCTIVE;
	}

	public void setDomainService(DomainService domainService) {
		this.domainService = domainService;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean supports(Class domain) {
		return domain.equals(UpdDomainContentDTO.class);
	}

	@Override
	public void validate(Object arg0, Errors arg1){
		UpdDomainContentDTO dto = (UpdDomainContentDTO) arg0;

		if( dto == null ) {
			arg1.reject("error.nullpointer", "Null data received");
		}
		else {
			String code = dto.getCode();
			String name = dto.getName();
			String param = dto.getParam();
			String value = dto.getValue();
			
			logger.debug("code="+code);
			logger.debug("name="+name);
			logger.debug("param="+param);
			logger.debug("value="+value);
			
			// Se controla que se ingrese un valor
			if( name == "" ) arg1.rejectValue("name", "error.code","Ingresa nombre");
			if( code == "" ) arg1.rejectValue("code", "error.code","Ingresa un codigo");
			if( param == "" ) arg1.rejectValue("param", "error.code","Ingresa parametro");
			if( value == "" ) arg1.rejectValue("value", "error.code","Ingresa un valor");
			
			// Se valida que code no este duplicado
			AvaibleCodeSearchCriteria dto_dc = new AvaibleCodeSearchCriteria();
			dto_dc.setActive(ACTIVE);
			dto_dc.setCode(code);
			dto_dc.setId_domain(dto.getId_domain());
			dto_dc.setId_domain_content(dto.getId_domain_content());
			
			try {
				domainService.getAvaibleCodeByCriteria(dto_dc);
			} catch (DomainContentCodeDuplicateException e) {
				arg1.rejectValue("code", "error.code",e.getMessage());
			}
		}
	}
}
