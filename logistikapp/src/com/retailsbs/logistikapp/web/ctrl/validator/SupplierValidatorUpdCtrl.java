package com.retailsbs.logistikapp.web.ctrl.validator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.retailsbs.logistikapp.supplier.dto.AvaibleSupplierCodeSearchCriteria;
import com.retailsbs.logistikapp.supplier.dto.UpdSupplierDTO;
import com.retailsbs.logistikapp.supplier.exception.SupplierCodeDuplicateException;
import com.retailsbs.logistikapp.supplier.service.SupplierService;

public class SupplierValidatorUpdCtrl implements Validator {
	protected final Log logger = LogFactory.getLog(getClass());

	private SupplierService supplierService;
	private String ACTIVE;

	public void setSupplierService(SupplierService supplierService) {
		this.supplierService = supplierService;
	}

	public void setACTIVE(String aCTIVE) {
		ACTIVE = aCTIVE;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean supports(Class supplier) {
		return supplier.equals(UpdSupplierDTO.class);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		UpdSupplierDTO dto = (UpdSupplierDTO) arg0;

		if (dto == null) {
			arg1.reject("error.nullpointer", "Null data received");
		} else {
			Long id_supplier = dto.getId_supplier();
			String name = dto.getName();
			String code = dto.getCode();
			logger.debug("id_supplier=" + id_supplier);
			logger.debug("name=" + name);
			logger.debug("code=" + code);

			// Se controla que se ingrese un valor
			if (name == "") arg1.rejectValue("name", "error.code", "Ingresa el nombre del proveedor");
			if (code == "") arg1.rejectValue("code", "error.code", "Ingresa un código para el proveedor");

			// Se valida que el código del proveedor no esté duplicado
			AvaibleSupplierCodeSearchCriteria dto_sup = new AvaibleSupplierCodeSearchCriteria();
			dto_sup.setId_supplier(id_supplier);
			dto_sup.setCode(code);
			dto_sup.setActive(ACTIVE);
			try {
				supplierService.getAvaibleSupplierCodeSearchCriteria(dto_sup);
			} catch (SupplierCodeDuplicateException e) {
				arg1.rejectValue("code", "error.code", e.getMessage());
			}
		}
	}
}
