package com.retailsbs.logistikapp.retail.dao;

import java.util.List;

import com.retailsbs.logistikapp.retail.domain.StoreCategory;
import com.retailsbs.logistikapp.retail.dto.StoreCategoryActiveSearchCriteria;


public interface FinderStoreCategoryDAO {

	/**
	 * Obtiene una lista de los store_category (categoria de locales) activos de un retail (comercio)
	 * @param id_retail Id retail (comercio), active (indicador de locales activos) y id_store_category (categoria seleccionada) 
	 * @return Lista ids de store_categoty
	 * @author Sergio
	 * @since 07-07-2015
	 */
	List<StoreCategory> getStoresCategoryActiveByCriteria(StoreCategoryActiveSearchCriteria dto);
	/**
	 * elimina store category por id_supplier
	 * @param id_supplier
	 * @return numero de store category eliminados
	 * @author JuanCarlosRamosCampos
	 * @since 23-11-2015
	 */
	int delStoreCategoryByIdSupplier(Long id_supplier);
	
}
