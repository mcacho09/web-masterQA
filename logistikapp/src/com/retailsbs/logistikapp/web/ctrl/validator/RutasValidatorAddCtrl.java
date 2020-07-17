package com.retailsbs.logistikapp.web.ctrl.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.retailsbs.logistikapp.logistic.dto.AddRouteDTO;
import com.retailsbs.logistikapp.logistic.dto.AviableCodeRouteSearchCriteria;
import com.retailsbs.logistikapp.logistic.exception.CodeRouteDuplicateException;
import com.retailsbs.logistikapp.logistic.service.LogisticService;

public class RutasValidatorAddCtrl implements Validator {

	protected final Log logger = LogFactory.getLog(getClass());

	private LogisticService logisticService;

	public void setLogisticService(LogisticService logisticService) {
		this.logisticService = logisticService;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean supports(Class ruta) {
		return ruta.equals(AddRouteDTO.class);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		AddRouteDTO dto = (AddRouteDTO) arg0;

		if (dto == null) {
			arg1.reject("error.nullpointer", "Null data received");
		} else {

			String name = dto.getName();
			String code = dto.getCode();

			logger.debug("name=" + name);
			logger.debug("code=" + code);
			
			// Se controla que se ingrese un valor
			if (name == "") arg1.rejectValue("name", "error.name", "Debes ingresar el nombre de la ruta");
			if (code == "") arg1.rejectValue("code", "error.code", "Debes ingresar codigo");
			
			//valida que nombre sea escrito alfanumerico
			Pattern pat = Pattern.compile("^[A-Z0-9 a-z Ã±Ã‘Ã¡Ã©Ã­Ã³ÃºÃ�Ã‰Ã�Ã“Ãš ñáéíóú ÑÁÉÍÓÚ]*$");
			Matcher mat = pat.matcher(name);
			if(!mat.matches())
				arg1.rejectValue("name", "error.code", "El nombre debe ser escrito solo con n&uacute;meros y/o letras");

			// Se valida que el codigo no este duplicado
			try {
				AviableCodeRouteSearchCriteria dto_route = new AviableCodeRouteSearchCriteria();
				dto_route.setCode(code);
				logisticService.getAvaibleCodeRouteByCriteria(dto_route);
			} catch (CodeRouteDuplicateException e) {
				arg1.rejectValue("code", "error.code", e.getMessage());
			}

		}

	}

}
