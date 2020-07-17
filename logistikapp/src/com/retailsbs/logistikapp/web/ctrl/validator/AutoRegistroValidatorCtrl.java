package com.retailsbs.logistikapp.web.ctrl.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.retailsbs.logistikapp.manager.user.dto.AutoRegistroDTO;
import com.retailsbs.logistikapp.user.dto.AvaibleLoginUserSearchCriteria;
import com.retailsbs.logistikapp.user.exception.EmailUserDuplicateException;
import com.retailsbs.logistikapp.user.exception.LoginUserDuplicateException;
import com.retailsbs.logistikapp.user.service.UserService;

public class AutoRegistroValidatorCtrl implements Validator{


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
		return user.equals(AutoRegistroDTO.class);
	}


	@Override
	public void validate(Object arg0, Errors arg1) {
		AutoRegistroDTO dto = (AutoRegistroDTO) arg0;
		
		if (dto == null) {
			arg1.reject("error.nullpointer", "Null data received");
		} else {

			String username = dto.getUsername();
			String userlogin = dto.getUserlogin();
			String passwd = dto.getPasswd();
			String email = dto.getEmail();
			String name = dto.getName();
			String phones = dto.getPhone1();

			
			logger.debug("username=" + username);
			logger.debug("userlogin=" + userlogin);
			logger.debug("passwd=" + passwd);
			logger.debug("email=" + email);
			logger.debug("name=" + name);

			// Se controla que se ingrese un valor
			if (username == "") arg1.rejectValue("username", "error.code", "Debes ingresar el nombre completo");
			if (userlogin == "") arg1.rejectValue("userlogin", "error.code", "Debes ingresar un nombre de usuario");
			if (passwd == "") arg1.rejectValue("passwd", "error.code", "Debes ingresar una clave");
			if (email == "") arg1.rejectValue("email", "error.code", "Debes ingresar un correo electr&oacute;nico");
			if (name == "") arg1.rejectValue("name", "error.code", "Debes ingresar un nombre de Empresa");
			if (phones == "") arg1.rejectValue("phone1", "error.code", "Debes ingresar un tel&eacute;fono");
			
			//valida que login sea escrito alfanumerico
			Pattern pat = Pattern.compile("^[A-Z0-9 a-z]*$");
			Matcher mat = pat.matcher(userlogin);
			Matcher pass = pat.matcher(passwd);
			
						
		    Pattern nom_pat = Pattern.compile("[A-Za-z ñáéíóú]{5,60}");
		    Matcher nom = nom_pat.matcher(username);
		    
		    
		    if(!nom.matches())
				arg1.rejectValue("username", "error.code", "El nombre no puede contener n&uacute;meros o caracteres especiales");

			if(!mat.matches() || userlogin.contains(" "))
				arg1.rejectValue("userlogin", "error.code", "El nombre de usuario debe ser escrito sin espacios y solo con n&uacute;meros y/o letras");
			
			if(!pass.matches())
				arg1.rejectValue("passwd", "error.code", "La clave debe ser escrita solo con n&uacute;meros y/o letras");

			if(userlogin.length() > 19)
				arg1.rejectValue("userlogin", "error.code", "El nombre de usuario es demasiado largo, escriba una m&aacute;s corta, m&aacute;ximo 20 n&uacute;meros y/o letras");

			if(passwd.length() < 6 )
				arg1.rejectValue("passwd", "error.code", "La clave es demasiado corta, escriba una m&aacute;s larga, m&iacute;nimo 6 n&uacute;meros y/o letras");

			if(passwd.length() > 20 )
				arg1.rejectValue("passwd", "error.code", "La clave es demasiado larga, escriba una m&aacute;s corta, m&aacute;ximo 20 n&uacute;meros y/o letras");
				
			Pattern phone = Pattern.compile("\\d*");
			Matcher match_phone = phone.matcher(dto.getPhone1());
			if(!match_phone.matches() || dto.getPhone1().length() > 10 || dto.getPhone1().length() < 7) 
				arg1.rejectValue("phone1", "error.code", "Debes ingresar solamente n&uacute;meros y no ser mayor a 10 d&iacute;gitos ni menor a 7 d&iacute;gitos");

			
			
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
				arg1.rejectValue("email", "error.code", e.getMessage());
			}
				

			// Se valida que login no este duplicado
			AvaibleLoginUserSearchCriteria dto_usr = new AvaibleLoginUserSearchCriteria();
			dto_usr.setActive(ACTIVE);
			dto_usr.setUserlogin(userlogin.toLowerCase());
			try {
				userService.getAvaibleLoginUserByCriteria(dto_usr);
			} catch (LoginUserDuplicateException e) {
				arg1.rejectValue("userlogin", "error.code", e.getMessage());
			}

		}

	}
	
	
}
