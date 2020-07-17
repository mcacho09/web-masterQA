package com.retailsbs.logistikapp.manager.logistic;

import java.util.List;

import com.retailsbs.logistikapp.logistic.exception.RouteNotFoundException;
import com.retailsbs.logistikapp.manager.logistic.dto.RouteStoreDTO;
import com.retailsbs.logistikapp.manager.logistic.dto.StoreByNameAddressLogistic;
import com.retailsbs.logistikapp.manager.logistic.dto.StoreInfoTravelDTO;
import com.retailsbs.logistikapp.manager.logistic.dto.StoreTravelDTO;
import com.retailsbs.logistikapp.retail.dto.StoreInfoSearchCriteria;
import com.retailsbs.logistikapp.retail.exception.RetailNotFoundException;
import com.retailsbs.logistikapp.retail.exception.StoreNotFoundException;
/**
 * Interface de Servicio ManagerLogistic
 * @author Sergio Valenzuela
 * @since 27-08-2015
 */
public interface ManagerLogisticService {
	
	/*
	 * RouteStore
	 */
	/**
	 * Obtiene todas las rutas y sus tiendas por id supplier
	 * @return Lista de Objetos de dominio {RouteStoreDTO}
	 * @author Sergio Valenzuela
	 * @since 27-08-2015
	 */
	List<RouteStoreDTO> getRouteStoreByIdSuplier(Long id_supplier);
	
	/**
	 * Obtiene la ruta y sus tiendas por id route
	 * @return Objetos de dominio {RouteStoreDTO}
	 * @author Sergio Valenzuela
	 * @throws RouteNotFoundException 
	 * @since 28-08-2015
	 */
	RouteStoreDTO getRouteStoreByIdRoute(Long id_route) throws RouteNotFoundException;

	/*
	 * StoreTravel
	 */

	/**
	 * obtiene tiendas por nombre o direccion y la lista de viajes de cada tienda
	 * @param dto con datos de busqeuda
	 * @return lista de objetos de dominio {StoreTravelDTO}
	 * @author jucaraca
	 * @since 22-10-2015
	 */
	List<StoreTravelDTO> getStoreByNameAddressAndTravels(StoreByNameAddressLogistic dto);
	
	List<StoreInfoTravelDTO> getStoreInfoTravel(StoreInfoSearchCriteria dto);
	
	/*
	 * StoreTravel
	 */

	/**
	 * Elimina los retails con los stores asociados
	 * @param ID's de los retails a eliminar
	 * @return Cantidad de registros eliminados
	 * @author Axel Monroy
	 * @since 01-02-2016
	 */	
	int delRetailAndStores(String[] idsArray) throws RetailNotFoundException, StoreNotFoundException;
}
