package com.retailsbs.logistikapp.web.ctrl.validator;

import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContextHolder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.retailsbs.logistikapp.retail.dto.RetailAvaibleSearchCriteria;
import com.retailsbs.logistikapp.retail.dto.UpdRetailDTO;
import com.retailsbs.logistikapp.retail.exception.RetailDuplicadoException;
import com.retailsbs.logistikapp.retail.service.RetailService;
import com.retailsbs.logistikapp.user.domain.UserAcegi;
import com.retailsbs.logistikapp.user.exception.AccessNotFoundException;
import com.retailsbs.logistikapp.user.exception.UserNotFoundException;
import com.retailsbs.logistikapp.user.service.UserService;

public class RetailValidatorUpdCtrl implements Validator {
	
	protected final Log logger = LogFactory.getLog(getClass());

	private RetailService retailService;
	private UserService userService;
	private String ACTIVE;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public void setRetailService(RetailService retailService) {
		this.retailService = retailService;
	}
	public void setACTIVE(String aCTIVE) {
		ACTIVE = aCTIVE;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean supports(Class state) {
		return state.equals(UpdRetailDTO.class);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		// Se obtiene el contexto del usuario
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserAcegi useracegi = (UserAcegi) auth.getPrincipal();
		UpdRetailDTO dto = (UpdRetailDTO) arg0;

		if (dto == null) {
			arg1.reject("error.nullpointer", "Null data received");
		} else {
			
			Long id_retail = dto.getId_retail();
			String name = dto.getName();
			String code = dto.getCode();
			Long id_retail_category = dto.getId_retail_category();
			logger.debug("id_retail=" + id_retail);
			logger.debug("id_retail_category=" + id_retail_category);
			logger.debug("name=" + name);
			logger.debug("code=" + code);

			// Se controla que se ingrese un valor
			if ( name == "" ) arg1.rejectValue("name", "error.name", "Ingresa un nombre para el comercio");
			if ( code == "" ) arg1.rejectValue("code", "error.code", "Ingresa un c&oacute;digo de comercio");
			if ( id_retail_category == null ) arg1.rejectValue("id_retail_category", "error.code", "Selecciona una categoria");

			/*
			Pattern phone = Pattern.compile("\\d*");
			Matcher match_phone = phone.matcher(dto.getPhone());
			if(!match_phone.matches() || dto.getPhone().length() > 10)
				arg1.rejectValue("phone", "error.code", "El tel&eacute;fono debe contener solamente n&uacute;meros y no ser mayor a 10 d&iacute;gitos");
			 */
			
			try {
				Long id_sup = userService.getAccessByIdUser(useracegi.getId_user()).get(0).getId_supplier();
				logger.debug("id_supplier = "+id_sup);
				// Se valida que code no este duplicado
				RetailAvaibleSearchCriteria dto_ret = new RetailAvaibleSearchCriteria();
				dto_ret.setCode(code);
				dto_ret.setActive(ACTIVE);
				dto_ret.setId_retail(id_retail);
				dto_ret.setId_supplier(id_sup);
				
				retailService.getRetailAvaibleByCriteria(dto_ret);
				
			} catch (RetailDuplicadoException e) {
				arg1.rejectValue("code", "error.code", e.getMessage());
			}catch (UserNotFoundException e1) {
				arg1.rejectValue("code", "error.code", e1.getMessage());
			} catch (AccessNotFoundException e1) {
				arg1.rejectValue("code", "error.code", e1.getMessage());
			}
		}
	}
}
