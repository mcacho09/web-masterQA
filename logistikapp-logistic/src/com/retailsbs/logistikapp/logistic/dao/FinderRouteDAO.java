package com.retailsbs.logistikapp.logistic.dao;

import java.util.List;

import com.retailsbs.logistikapp.logistic.domain.Route;
import com.retailsbs.logistikapp.logistic.dto.CountRouteSupplierCriteria;
import com.retailsbs.logistikapp.logistic.dto.RoutesByListRetails;

public interface FinderRouteDAO {

	/**
	 * Elimina routa por medio de id_supplier
	 * @param id_supplier
	 * @return numero de route eliminadas
	 * @author JuanCarlosRamosCampos
	 * @since 23-11-2015
	 */
	int delRouteByIdSupplier(Long id_supplier);

	/**
	 * Obtiene el total de rutas por proveedor a partir de un criterio de busqueda
	 * @param dto Dto con criterio de busqueda
	 * @return Cantidad de rutas definidas para un proveedor
	 * @author Jorge
	 * @since 24/02/2016
	 */
	int getCountRouteSupplierByCriteria(CountRouteSupplierCriteria dto);
	
	List<Route> getRouteByListRetail(RoutesByListRetails dto);
	     
	List<Long> getIdsRouteByIdTravel(Long id_travel);
	List<Route> getRoutesByIdTravel(Long id_travel);

	
}
