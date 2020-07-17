package com.retailsbs.logistikapp.web.ctrl.validator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.retailsbs.logistikapp.user.dto.UpdUserDTO;

public class PasswdProfileValidatorUpdCtrl implements Validator {
	protected final Log logger = LogFactory.getLog(getClass());
	
	
	@SuppressWarnings("rawtypes")
	@Override
	public boolean supports(Class user) {
		return user.equals(UpdUserDTO.class);
	}

	@Override
	public void validate(Object arg0, Errors arg1){
		UpdUserDTO dto = (UpdUserDTO) arg0;

			String passwd = dto.getPasswd();
			String lastPasswd = encryptPass(dto.getLastPasswd()).toString();
			String newPasswd = encryptPass(dto.getNewPasswd());
			String verifiedPasswd = encryptPass(dto.getVerifiedPasswd());
			
			logger.debug("passwd="+passwd);
			logger.debug("lasPasswd="+lastPasswd);
			logger.debug("newPasswd="+newPasswd);
			logger.debug("verifiedPasswd="+verifiedPasswd);
			
			// Se controla que se ingrese un valor
			if (lastPasswd=="") 
				arg1.rejectValue("lastPasswd", "error.lastPasswd", "Debes ingresar la contrase&#241;a actual");
			else if (!lastPasswd.equals(passwd)){
				arg1.rejectValue("lastPasswd", "error.lastPasswd", "La contrase&#241;a actual no es correcta");
			}
			
			if (newPasswd == "") 
				arg1.rejectValue("newPasswd", "error.code", "Debes ingresar una contrase&#241;a nueva");
			
			if (verifiedPasswd == "") 
				arg1.rejectValue("verifiedPasswd", "error.verifiedPasswd", "Debes ingresar de nuevo la  nueva contrase&#241;a");
			else if (!verifiedPasswd.equals(newPasswd)) 
				arg1.rejectValue("verifiedPasswd", "error.code", "La contrase&#241;a no coincide");

			
		}
	
	public String encryptPass(String passwd){
		//se encripta la contrasenia
		String password = passwd;
		/*
		if(password!=null && !password.equals("")){
			MessageDigest md;
			try {
				md = MessageDigest.getInstance("MD5");
				md.update(password.getBytes()); 
				byte byteData[] = md.digest(); 
				StringBuffer sb = new StringBuffer(); 
				for (int i = 0; i < byteData.length; i++) 
					sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1)); 
				password = sb.toString();
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
		}else
			return "";
		*/
		return password;
	}
}
