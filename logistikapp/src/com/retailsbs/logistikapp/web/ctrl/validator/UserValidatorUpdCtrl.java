package com.retailsbs.logistikapp.web.ctrl.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.retailsbs.logistikapp.user.dto.AvaibleLoginUserSearchCriteria;
import com.retailsbs.logistikapp.user.dto.UpdUserDTO;
import com.retailsbs.logistikapp.user.exception.LoginUserDuplicateException;
import com.retailsbs.logistikapp.user.service.UserService;

public class UserValidatorUpdCtrl implements Validator {
	protected final Log logger = LogFactory.getLog(getClass());
	
	private UserService userService;
	private String ACTIVE;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setACTIVE(String aCTIVE) {
		ACTIVE = aCTIVE;
	}

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
			Long id_user = dto.getId_user();
			String username = dto.getUsername();
			String userlogin = dto.getUserlogin();
			String email = dto.getEmail();

			
			logger.debug("id_user="+id_user);
			logger.debug("username="+username);
			logger.debug("userlogin="+userlogin);
			logger.debug("email=" + email);
			
			// Se controla que se ingrese un valor
			if (username == "") arg1.rejectValue("username", "error.code", "Debes ingresar el nombre");
			if (userlogin == "") arg1.rejectValue("userlogin", "error.code", "Debes ingresar un login de usuario");
			if (email == "") arg1.rejectValue("email", "error.code", "Debes ingresar un correo electronico");
			
			//valida que login sea escrito alfanumerico
			Pattern pat = Pattern.compile("^[A-Z0-9 a-z]*$");
			Matcher mat = pat.matcher(userlogin);
			if(!mat.matches())
				arg1.rejectValue("userlogin", "error.code", "El login de usuario debe ser escrito solo con n&uacute;meros y/o letras");

			
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

			
			// Se valida que login no este duplicado
			AvaibleLoginUserSearchCriteria dto_usr = new AvaibleLoginUserSearchCriteria();
			dto_usr.setId_user(id_user);
			dto_usr.setActive(ACTIVE);
			dto_usr.setUserlogin(userlogin);
			try {
				userService.getAvaibleLoginUserByCriteria(dto_usr);
			} catch (LoginUserDuplicateException e) {
				arg1.rejectValue("userlogin", "error.code",e.getMessage());
			}
		}
	}
}
