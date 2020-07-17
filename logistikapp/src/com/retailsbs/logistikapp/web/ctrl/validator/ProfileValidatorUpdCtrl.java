package com.retailsbs.logistikapp.web.ctrl.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.retailsbs.logistikapp.user.dto.UpdUserDTO;

public class ProfileValidatorUpdCtrl implements Validator {
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
			String job = dto.getJob();
			String phone1 = dto.getPhone1();
			String phone2 = dto.getPhone2();
			
			logger.debug("username="+username);
			logger.debug("email="+email);
			logger.debug("job="+job);
			logger.debug("phone1="+phone1);
			
			// Se controla que se ingrese un valor
			if (username == "") arg1.rejectValue("username", "error.code", "Debes ingresar el nombre");
			if (email == "") arg1.rejectValue("email", "error.email", "Debes ingresar un corr&eacute;o electr&oacute;nico");
			if (job == "") arg1.rejectValue("job", "error.job", "Debes ingresar un puesto");
			if (phone1 == "") arg1.rejectValue("phone1", "error.phone1", "Debes ingresar el tel&eacute;fono 1");
			
			// Se controla que el email sea valido
			String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
					+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

			Pattern pattern = Pattern.compile(PATTERN_EMAIL);
			Matcher matcher = pattern.matcher(email);
			if(!matcher.matches())
				{
					arg1.rejectValue("email", "error.code", "El correo electronico no es valido");
				}
			
			Pattern phone = Pattern.compile("\\d*");
			Matcher match_phone = phone.matcher(dto.getPhone1());
			if(!match_phone.matches() || dto.getPhone1().length() > 10)
				arg1.rejectValue("phone1", "error.code", "El tel&eacute;fono debe contener solamente n&uacute;meros y no ser mayor a 10 d&iacute;gitos");

			Matcher match_phone2 = phone.matcher(dto.getPhone2());			
			if(!match_phone2.matches() || dto.getPhone2().length() > 10)
				arg1.rejectValue("phone2", "error.code", "El tel&eacute;fono debe contener solamente n&uacute;meros y no ser mayor a 10 d&iacute;gitos");

			
		}
	}
}
