package com.retailsbs.logistikapp.web.ctrl.validator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.retailsbs.logistikapp.financial.dto.UpdProductDTO;


public class ProductValidatorUpdCtrl implements Validator {
	protected final Log logger = LogFactory.getLog(getClass());

	@SuppressWarnings("rawtypes")
	@Override
	public boolean supports(Class product) {
		return product.equals(UpdProductDTO.class);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		UpdProductDTO dto = (UpdProductDTO) arg0;

		if (dto == null) {
			arg1.reject("error.nullpointer", "Null data received");
		} else {

			Long id_product = dto.getId_product();
			String name_long = dto.getName_long();
			String name_short = dto.getName_short();
			String code = dto.getCode();
			Long id_category_product = dto.getId_category_product();
			Long id_brand = dto.getId_brand();
			Double price_cost = dto.getPrice_cost();
			Double price_sale = dto.getPrice_sale();
			logger.debug("id_product=" + id_product);
			logger.debug("name_long=" + name_long);
			logger.debug("name_short=" + name_short);
			logger.debug("code=" + code);
			logger.debug("id_category_product=" + id_category_product);
			logger.debug("id_brand=" + id_brand);
			logger.debug("price_cost=" + price_cost);
			logger.debug("price_sale=" + price_sale);

			// Se controla que se ingrese un valor
			if (name_long == "") arg1.rejectValue("name_long", "error.code", "Debes ingresar una descripci&oacute;n");
			if (name_short == "") arg1.rejectValue("name_short", "error.code", "Debes ingresar un nombre");
			if (code == "") arg1.rejectValue("code", "error.code", "Debes ingresar un c&oacute;digo");
			if (id_category_product == null) arg1.rejectValue("id_category_product", "error.code", "Debes seleccionar una categor&iacute;a");
			if (id_brand == null) arg1.rejectValue("id_brand", "error.code", "Debes seleccionar una marca");
			if (price_cost == null) arg1.rejectValue("price_cost", "error.code", "Debes ingresar un precio costo v&aacute;lido");
			if (price_sale == null) arg1.rejectValue("price_sale", "error.code", "Debes ingresar un precio venta v&aacute;lido");

			// TODO controlar que el c�digo no est� definido para otro producto
			
		}
	}
}
