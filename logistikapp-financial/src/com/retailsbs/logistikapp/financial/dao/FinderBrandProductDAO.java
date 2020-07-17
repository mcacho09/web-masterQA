package com.retailsbs.logistikapp.financial.dao;

import java.util.List;

import com.retailsbs.logistikapp.financial.dto.BrandProductDTO;
import com.retailsbs.logistikapp.financial.dto.BrandProductSearchCriteria;

public interface FinderBrandProductDAO {

	/**
	 * Obtiene un listado de category producto a partir de un criterio de busqueda
	 * @param dto Dto con criterio de busqueda
	 * @return Lista de objetos
	
	 */
	List<BrandProductDTO> selectBrandProductByCriteria(BrandProductSearchCriteria dto);

	
}
