package com.retailsbs.logistikapp.financial.dao;

import java.util.List;

import com.retailsbs.logistikapp.financial.dto.ProductsUsedsInOrderDetailDTO;

/**
 * Class interface para obtener order detail
 * @author JuanCarlosRamosCampos
 * @since 23-11-2015
 */

public interface FinderOrderDetailDAO {

	/**
	 * elimina orderDetail por id_order
	 * @param id_order
	 * @return numero de order Detail eliminados
	 * @author JuanCarlosRamosCampos
	 * @since 23-11-2015
	 */
	int delOrderDetailByIdOrder(Long id_order);
	
	/**
	 * elimina orderDetail por id_product
	 * @param id_product
	 * @return numero de order Detail eliminados
	 * @author JuanCarlosRamosCampos
	 * @since 23-11-2015
	 */
	int delOrderDetailByIdProduct(Long id_product);
	
	/*
	 * Busca si los ids mandados se encuentran usados en alguna venta y regresara los ids que si son usados
	 * @param ProductsUsedsInOrderDetailDTO
	 * @return List<Long>
	 * @author DMarin
	 * @since 17-02-2017
	 */
	
	List<Integer> getProductsUsedsInOrderDetail(ProductsUsedsInOrderDetailDTO dto);
	
}
