package com.retailsbs.logistikapp.financial.dao;

import java.util.List;

import com.retailsbs.logistikapp.financial.dto.ProductDTO;
import com.retailsbs.logistikapp.financial.dto.ProductSearchCriteria;
import com.retailsbs.logistikapp.financial.dto.ProductToSubAlmacenDTO;

public interface FinderProductDAO {

	/**
	 * Obtiene un listado de product a partir de un criterio de busqueda
	 * @param dto Dto con criterio de busqueda
	 * @return Lista de objetos
	 * @author jorge
	 * @since 12-12-2014
	 */
	List<ProductDTO> selectProductByCriteria(ProductSearchCriteria dto);
	/**
	 * elimina product por id_category_product
	 * @param id_category_product
	 * @return numero de product que se eliminaron
	 * @author JuanCarlosRamosCampos
	 * @since 23-11-2015
	 */
	int delProductByIdCategoryProduct(Long id_category_product);
	
	/*
	 * Obtiene el listado de nombre de productos de un supplier ordenados de manera ascendente
	 * @param id_supplier
	 * @return List<String>
	 * @author Daniel Marin
	 * @since 10-05-2017
	 */
	List<String> getProductsNameByIdSupplier(Long id_supplier);
	
	/*
	 * Obtiene el listado de precios de los productos para el reporte trx/productos
	 * @param id_order
	 * @return List<Double>
	 * @author Daniel Marin
	 * @since 10-05-2017
	 */
	List<Double> getPriceProductsByIdOrder(Long id_order);
	
	List<ProductToSubAlmacenDTO> getProductsToCreateSubStock(ProductSearchCriteria criteria);
	
}
