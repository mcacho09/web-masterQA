package com.retailsbs.logistikapp.web.ctrl.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.retailsbs.logistikapp.user.dto.AddUserDTO;
import com.retailsbs.logistikapp.user.dto.AvaibleLoginUserSearchCriteria;
import com.retailsbs.logistikapp.user.exception.EmailUserDuplicateException;
import com.retailsbs.logistikapp.user.exception.LoginUserDuplicateException;
import com.retailsbs.logistikapp.user.service.UserService;

public class UserValidatorAddCtrl implements Validator {

	protected final Log logger = LogFactory.getLog(getClass());

	private UserService userService;
	private String ACTIVE;

	public void setACTIVE(String aCTIVE) {
		ACTIVE = aCTIVE;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean supports(Class user) {
		return user.equals(AddUserDTO.class);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		AddUserDTO dto = (AddUserDTO) arg0;

		if (dto == null) {
			arg1.reject("error.nullpointer", "Null data received");
		} else {

			String username = dto.getUsername();
			String userlogin = dto.getUserlogin();
			String passwd = dto.getPasswd();
			String email = dto.getEmail();

			logger.debug("username=" + username);
			logger.debug("userlogin=" + userlogin);
			logger.debug("passwd=" + passwd);
			logger.debug("email=" + email);

			// Se controla que se ingrese un valor
			if (username == "") arg1.rejectValue("username", "error.code", "Debes ingresar el nombre");
			if (userlogin == "") arg1.rejectValue("userlogin", "error.code", "Debes ingresar un login de usuario");
			
			if (passwd == "") {
				arg1.rejectValue("passwd", "error.code", "Debes ingresar una contrase&ntilde;a");
			}else{
				if(passwd.length() < 6 || passwd.length() > 20)
					arg1.rejectValue("passwd", "error.code", "La clave  debe contener m&iacute;nimo 6  o m&aacute;ximo 20 n&uacute;meros y/o letras");
			}
			
			if (email == ""){
				arg1.rejectValue("email", "error.code", "Debes ingresar un correo electr&oacute;nico");
			}else{

				
				// Se controla que el email sea valido
				String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
						+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

				Pattern pattern = Pattern.compile(PATTERN_EMAIL);
				Matcher matcher = pattern.matcher(email);
				if(!matcher.matches())
					{
						arg1.rejectValue("email", "error.code", "El correo electr&oacute;nico no es v&aacute;lido");
					}

				//Se valida que el email no este duplicado
				try{
					userService.getDuplicateUserByEmail(email);
				}catch(EmailUserDuplicateException e){
					arg1.rejectValue("email", "error.code", "Correo electr&oacute;nico utilizado por otro usuario");
				}
			}
			
			Pattern phone = Pattern.compile("\\d*");
			Matcher match_phone = phone.matcher(dto.getPhone1());
			if(!match_phone.matches() || dto.getPhone1().length() > 10)
				arg1.rejectValue("phone1", "error.code", "El tel&eacute;fono debe contener solamente n&uacute;meros y no ser mayor a 10 d&iacute;gitos");

			if(dto.getPhone1().length() < 7)
				arg1.rejectValue("phone1", "error.code", "El tel&eacute;fono es muy corto, ingrese un m&iacute;nimo de 10 d&iacute;gitos");
			
			//valida que login sea escrito alfanumerico
			Pattern pat = Pattern.compile("^[A-Z0-9 a-z]*$");
			Matcher mat = pat.matcher(userlogin);
			
			if(!mat.matches() || userlogin.contains(" "))
				arg1.rejectValue("userlogin", "error.code", "El login de usuario debe ser escrito solo con n&uacute;meros, letras y sin espacios");
			
			


			// Se valida que login no este duplicado
			AvaibleLoginUserSearchCriteria dto_usr = new AvaibleLoginUserSearchCriteria();
			dto_usr.setActive(ACTIVE);
			dto_usr.setUserlogin(userlogin);
			try {
				userService.getAvaibleLoginUserByCriteria(dto_usr);
			} catch (LoginUserDuplicateException e) {
				arg1.rejectValue("userlogin", "error.code", e.getMessage());
			}

		}

	}

}
