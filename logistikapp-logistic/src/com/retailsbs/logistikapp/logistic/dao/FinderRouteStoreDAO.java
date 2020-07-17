package com.retailsbs.logistikapp.logistic.dao;


public interface FinderRouteStoreDAO {

	/**
	 * Elimina RouteStore por medio de id_route
	 * @param id_route
	 * @return numero de registros eliminados
	 * @author jucaraca
	 * @since 09/10/2015
	 */
	int delRouteStoreByIdRoute(Long id_route);
	/**
	 * Elimina RouteStore por medio de id_store
	 * @param id_store
	 * @return numero de registros eliminados
	 * @author JuanCarlosRamosCampos
	 * @since 20-11-2015
	 */
	int delRouteStoreByIdStore(Long id_store);
	
}
