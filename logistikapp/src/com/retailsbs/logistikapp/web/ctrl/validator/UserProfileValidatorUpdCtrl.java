package com.retailsbs.logistikapp.web.ctrl.validator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.retailsbs.logistikapp.user.dto.UpdUserDTO;

public class UserProfileValidatorUpdCtrl implements Validator {
	protected final Log logger = LogFactory.getLog(getClass());
	
	@SuppressWarnings("rawtypes")
	@Override
	public boolean supports(Class user) {
		return user.equals(UpdUserDTO.class);
	}

	@Override
	public void validate(Object arg0, Errors arg1){
		UpdUserDTO dto = (UpdUserDTO) arg0;

		if( dto == null ) {
			arg1.reject("error.nullpointer", "Null data received");
		}
		else {
			String username = dto.getUsername();
			String email = dto.getEmail();
			String passwd = dto.getPasswd();
			
			logger.debug("username="+username);
			logger.debug("email="+email);
			logger.debug("passwd="+passwd);
			
			// Se controla que se ingrese un valor
			if( username == "" ) arg1.rejectValue("username", "error.code","Ingresa nombre de usuario");
			if( email == "" ) arg1.rejectValue("email", "error.code","Ingresa un email");
			if( passwd == "" ) arg1.rejectValue("passwd", "error.code","Ingresa un password");
			
		}
	}
}
