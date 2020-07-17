package com.retailsbs.logistikapp.financial.dao;

import java.util.List;

import com.retailsbs.logistikapp.financial.dto.CategoryProductDTO;
import com.retailsbs.logistikapp.financial.dto.CategoryProductSearchCriteria;
import com.retailsbs.logistikapp.financial.dto.ProductDTO;
import com.retailsbs.logistikapp.financial.dto.ProductSearchCriteria;

public interface FinderCategoryProductDAO {

	/**
	 * Obtiene un listado de category producto a partir de un criterio de busqueda
	 * @param dto Dto con criterio de busqueda
	 * @return Lista de objetos
	 * @author jorge
	 * @since 12-12-2014
	 */
	List<CategoryProductDTO> selectCategoryProductByCriteria(CategoryProductSearchCriteria dto);
	/**
	 * obtiene product por criterio de busqueda
	 * @param dto con datos de busqueda
	 * @return lista de objetos de dominio {ProductDTO}
	 */
	List<ProductDTO> selectProductByCriteria(ProductSearchCriteria dto);
	/**
	 * Elimina categoryProduct por id_supplier
	 * @param id_supplier
	 * @return numero de category product eliminados
	 * @author JuanCarlosRamosCampos
	 * @since 23-11-2015
	 */
	int delCategoryProductByIdSupplier(Long id_supplier);
	
}
