package com.retailsbs.logistikapp.retail.dao;

import java.util.List;

import com.retailsbs.logistikapp.retail.domain.RetailCategory;
import com.retailsbs.logistikapp.retail.dto.RetailCategoryActiveSearchCriteria;

public interface FinderRetailCategoryDAO {
	/**
	 * Obtiene una lista de los retail_category (categoria de comercio) activos de un supplier (provedor)
	 * @param id_retail Id retail (comercio), active (indicador de locales activos) y id_store_category (categoria seleccionada) 
	 * @return Lista ids de store_categoty
	 * @author Sergio
	 * @since 08-07-2015
	 */
	List<RetailCategory> getRetailsCategoryActiveByCriteria(RetailCategoryActiveSearchCriteria dto);
	/**
	 * Elimina retailCategory por medio de id_supplier
	 * @param id_supplier
	 * @return numero de retail category eliminados
	 * @author JuanCarlosRamosCampos
	 * @since 23-11-2015
	 */
	int delRetailCategoryByIdSupplier(Long id_supplier);

}
