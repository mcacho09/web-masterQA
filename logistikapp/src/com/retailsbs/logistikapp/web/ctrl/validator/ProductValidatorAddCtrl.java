package com.retailsbs.logistikapp.web.ctrl.validator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.retailsbs.logistikapp.financial.dto.AddProductDTO;
import com.retailsbs.logistikapp.financial.service.FinancialService;

public class ProductValidatorAddCtrl implements Validator {

	protected final Log logger = LogFactory.getLog(getClass());
	
	private FinancialService financialService;
	private String ACTIVE;

	public void setACTIVE(String aCTIVE) {
		ACTIVE = aCTIVE;
	}
	public void setFinancialService(FinancialService financialService) {
		this.financialService = financialService;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean supports(Class product) {
		return product.equals(AddProductDTO.class);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		AddProductDTO dto = (AddProductDTO) arg0;

		if (dto == null) {
			arg1.reject("error.nullpointer", "Null data received");
		} else {

			String name_long = dto.getName_long();
			String name_short = dto.getName_short();
			String code = dto.getCode();
			Long id_category_product = dto.getId_category_product();
			Long id_brand = dto.getId_brand();
			Double price_cost = dto.getPrice_cost();
			Double price_sale = dto.getPrice_sale();
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

			//Se verifica que el codigo que se va a ingresar no exista 
            /*ProductCodeAvailableSearchCriteria dto_pro = new ProductCodeAvailableSearchCriteria();
            dto_pro.setActive(ACTIVE);
            dto_pro.setCode(dto.getCode());
			
			try{
				financialService.getProductCodeAvaibleByCriteria(dto_pro);
			}catch(Exception e){
				arg1.rejectValue("code", "error.code", e.getMessage());
			}
			*/
		}

	}

}
