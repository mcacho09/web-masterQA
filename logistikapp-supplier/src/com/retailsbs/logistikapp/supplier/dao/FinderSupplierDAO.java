package com.retailsbs.logistikapp.supplier.dao;

import java.util.List;

import com.retailsbs.logistikapp.supplier.dto.SupplierExtDTO;

public interface FinderSupplierDAO {

	/*
	 * Obtiene el listado de proveedores con el plan al que pertenecen
	 * @return List<SupplierExtDTO>
	 * @author DMarin
	 * @since 01-06-2017
	 */
	List<SupplierExtDTO> getSupplierExt();
}
