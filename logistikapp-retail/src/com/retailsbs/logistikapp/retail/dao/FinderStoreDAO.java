package com.retailsbs.logistikapp.retail.dao;

import java.util.List;

import com.retailsbs.logistikapp.retail.domain.Store;
import com.retailsbs.logistikapp.retail.dto.AvailableStoreByIdRetail;
import com.retailsbs.logistikapp.retail.dto.AvailableStoreByIdRetailIdRoute;
import com.retailsbs.logistikapp.retail.dto.AvailableStoreByIdStoreCategory;
import com.retailsbs.logistikapp.retail.dto.AvailableStoreByIdStoreCategoryIdRoute;
import com.retailsbs.logistikapp.retail.dto.AvailableStoreByIdSupplierIdRoute;
import com.retailsbs.logistikapp.retail.dto.AvailableStoreDTO;
import com.retailsbs.logistikapp.retail.dto.AvailableStoreSearchCriteria;
import com.retailsbs.logistikapp.retail.dto.StoreActiveByIdsRetailSearchCriteria;
import com.retailsbs.logistikapp.retail.dto.StoreAvailableInRouteCriteria;
import com.retailsbs.logistikapp.retail.dto.StoreAvailableInTravelCriteria;
import com.retailsbs.logistikapp.retail.dto.StoreByCategoryDTO;
import com.retailsbs.logistikapp.retail.dto.StoreByIdsSearchCriteria;
import com.retailsbs.logistikapp.retail.dto.StoreByNameAddress;
import com.retailsbs.logistikapp.retail.dto.StoreByRetailDTO;
import com.retailsbs.logistikapp.retail.dto.StoreByRouteDTO;
import com.retailsbs.logistikapp.retail.dto.StoreCallCenterDTO;
import com.retailsbs.logistikapp.retail.dto.StoreDTO;
import com.retailsbs.logistikapp.retail.dto.StoreInfoDTO;
import com.retailsbs.logistikapp.retail.dto.StoreInfoSearchCriteria;
import com.retailsbs.logistikapp.retail.dto.StoreNotInTravelSearchCriteria;
import com.retailsbs.logistikapp.retail.dto.StoreRouteDTO;
import com.retailsbs.logistikapp.retail.dto.StoreSearchCriteria;
import com.retailsbs.logistikapp.retail.dto.TotalStoreCreateCriteria;

/**
 * interface de DAO finder atore
 * @author jucaraca
 */
public interface FinderStoreDAO {

	/*
	 * Store
	 */
	
	/**
	 * Obtiene lista de store's por criteria.
	 * @param dto dto con datos de busqueda
	 * @return lista de objetos de dominio {StoreDTO}
	 * @author Juan Carlos Ramos Campos
	 * @since 25-11-2014
	 */
	List<StoreDTO> getStoreByCriteria(StoreSearchCriteria dto);

	/**
	 * Obtiene una lista de store a partir de un listado de ids
	 * @param dto Listado de ids de store
	 * @return Lista de objetos de dominio {Store}
	 * @author jorge
	 * @since 05-01-2015
	 */
	List<Store> getStoreByIds(StoreByIdsSearchCriteria dto);
	
	/**
	 * Obtiene una lista de los los store (locales) de un retail (cliente)
	 * @param id_retail Id retail (cliente)
	 * @return Lista de objetos de dominio {Store}
	 * @author jorge
	 * @since 05-01-2015
	 */
	List<Store> getStoreByIdRetail(Long id_retail);	
	
	/**
	 * Obtiene una lista de los los store (locales) de un retail (cliente) que no esten siendo utilizados en una ruta
	 * @param dto con datos de busqueda
	 * @return Lista de objetos de dominio {Store}
	 * @author Sergio Valenzuela
	 * @since 31-08-2015
	 */
	List<Store> getAvailableStoreByIdRetail(AvailableStoreByIdRetail dto);
	
	/**
	 * Obtiene una lista de los los store (locales) de un retail (cliente) que no esten siendo utilizados en una ruta
	 * a excepcion de los store de la ruta la cual se pasa el id
	 * @param dto con datos de busqueda
	 * @return Lista de objetos de dominio {Store}
	 * @author JuanCarlosRamosCampos
	 * @since 01-03-2016
	 */
	List<Store> getAvailableStoreByIdRetailAndIdRoute(AvailableStoreByIdRetailIdRoute dto);
	
	/**
	 * Obtiene una lista de los los store (locales) de un storeCategory (categoria de local) que no esten siendo utilizados en una ruta
	 * @param dto con datos de busqueda 
	 * @return Lista de objetos de dominio {Store}
	 * @author Sergio Valenzuela
	 * @since 31-08-2015
	 */
	List<Store> getAvailableStoreByIdStoreCategory(AvailableStoreByIdStoreCategory dto);
	
	/**
	 * Obtiene una lista de los los store (locales) de un storeCategory (categoria de local) que no esten siendo utilizados 
	 * en una ruta y agrega los store que tiene la ruta la cual se le pasa el id
	 * @param dto 
	 * @return Lista de objetos de dominio {Store}
	 * @author JuanCarlosRamosCampos
	 * @since 01-03-2016
	 */
	List<Store> getAvailableStoreByIdStoreCategoryAndIdRoute(AvailableStoreByIdStoreCategoryIdRoute dto);
	
	/**
	 * Obtiene una lista de los los store (locales) de un retail category
	 * @param id_retail_category Id retail category (categoria)
	 * @return Lista de objetos de dominio {Store}
	 * @author jorge
	 * @since 28-01-2015
	 */
	List<Store> getStoreByIdRetailCategory(Long id_retail_category);
	
	/**
	 * Obtiene un objeto con informacion extendida de un local
	 * @param id_store Id store
	 * @return Objeto {StoreDTO} con informacion extendida del local
	 * @author jorge
	 * @since 19-03-2015
	 */
	StoreDTO getStoreExtById(Long id_store);
	
	/**
	 * Obtiene una lista de ids de los los stores (locales) activos de un retail (comercio)
	 * @param id_retail Id retail (comercio)
	 * @return Lista ids de stores
	 * @author Jorge
	 * @since 31-03-2015
	 */
	List<Long> getIdStoresActiveByIdRetail(Long id_retail);

	/**
	 * Obtiene el numero total de tiendas definidas en un proveedor
	 * @param id_supplier id proveedor
	 * @return numero total de tiendas definidas en el proveedor
	 * @author Juan Carlos
	 * @since 09-07-2015
	 */
	Integer getTotalStoreByIdSupplier(Long id_supplier);

	/**
	 * 
	 * @param dto (Ids retail)
	 * @return Lista de stores
	 * @author Nataly
	 * @since 12-08-2015
	 */
	List<Long> getIdStoresActiveByIdsRetail(StoreActiveByIdsRetailSearchCriteria dto);

	/**
	 * Obtiene todas las tiendas definidas en un proveedor
	 * @param id_supplier id proveedor
	 * @return lista de tiendas definidas en el proveedor
	 * @author Sergio Valenzuela
	 * @since 18-08-2015
	 */
	List<Store> getStoreByIdSupplier(Long id_supplier);
	
	/**
	 * Obtiene todas las tiendas definidas en un proveedor que no esten asignadas a una ruta
	 * @param id_supplier id proveedor
	 * @return lista de tiendas definidas en el proveedor
	 * @author Sergio Valenzuela
	 * @since 31-08-2015
	 */
	List<Store> getAvailableStoreByIdSupplier(Long id_supplier);
	
	/**
	 * Obtiene todas las tiendas definidas en un proveedor que no esten asignadas a una ruta y 
	 * las las tiendas de la ruta del id_route que se pasa
	 * @param dto
	 * @return lista de tiendas definidas en el proveedor
	 * @author JuanCarlosRamosCampos
	 * @since 01-03-2016
	 */
	List<Store> getAvailableStoreByIdSupplierAndIdRoute(AvailableStoreByIdSupplierIdRoute dto);
	
	/**
	 * Obtiene lista de stores por ruta
	 * @param id_route
	 * @return Lista de stores por id_route
	 * @author Nataly
	 * @since 19-08-2015
	 */
	List<StoreByRouteDTO> getStoreByRoute(Long id_route);

	/**
	 * 
	 * @param dto con id_route y id_travel
	 * @return lista de stores que no se usan en ese travel
	 * @author Nataly
	 * @since 
	 */
	List<StoreByRouteDTO> getStoreNotInTravel(StoreNotInTravelSearchCriteria dto);
	/**
	 * 
	 * @param id_route 
	 * @return lista de StoresDTO que no estan en ningun viaje 
	 * @author Nataly
	 * @since 07-09-2015
	 */
	List<StoreDTO> getStoreAvailableByRoute(StoreAvailableInRouteCriteria dto);
	/**
	 * 
	 * @param id_route 
	 * @return lista de StoresDTO que estan en algun viaje 
	 * @author Nataly
	 * @since 07-09-2015
	 */
	List<StoreDTO> getStoreInTravelByRoute(StoreAvailableInRouteCriteria dto);
	/**
	 * 
	 * @param dto con id_route, id_travel 
	 * @return lista de StoresDTO que estan en un viaje distinto  
	 * @author Nataly
	 * @since 10-09-2015
	 */
	List<StoreDTO> getStoreInOtherTravelByCriteria(StoreSearchCriteria dto);
	/**
	 * obtiene el total de tiendas por criterio
	 * @param dto con criterio de busqueda
	 * @return numero de tiendas obtenidas
	 * @author jucaraca
	 * @since 12-19-2015
	 */
	Integer getTotalStoreByCriteria(TotalStoreCreateCriteria dto);
	/**
	 * Obtiene datos de store y en cuan ruta, en cual viaje esta.
	 * @param dto con datos de busqueda de store
	 * @return lista de objetos de domianio {StoreInfoDTO}
	 * @author jucaraca
	 * @since 15/10/2015
	 */
	List<StoreInfoDTO> getStoreInfoByCriteria(StoreInfoSearchCriteria dto);
	/**
	 * Obtiene tiendas por nombre de tienda o direccion
	 * @param dto con criterio de busqueda
	 * @return lista de objetos de dominio {StoreInfoDTO}
	 * @author jucaraca
	 * @since 22/10/2015
	 */
	List<StoreCallCenterDTO> getStoreByNameAddress(StoreByNameAddress dto);
	/**
	 * elimina tiendas por id_store_category
	 * @param id_store_category
	 * @return numero de tiendas eliminadas
	 * @author JuanCarlosRamosCampos
	 * @since 23-11-2015
	 */
	int delStoreByIdStoreCategory(Long id_store_category);
	
	/**
	 * Para un proveedor obtiene un listado de comercios y la cantidad total de locales
	 * @param id_supplier Id supplier
	 * @return Lista de objetos con el retail y la cantidad de locales asociados
	 * @author JR
	 * @since 09/07/2015
	 * @modified 25/02/2016 - JS - Mejoras
	 */
	List<StoreByRetailDTO> getStoreByRetail(Long id_supplier);
	
	/**
	 * Para un proveedor obtiene un listado de categorias de locales y la cantidad total de locales
	 * @param id_supplier Id supplier
	 * @return Lista de objetos con la categoria de local y la cantidad de locales asociados
	 * @author JS
	 * @since 25/02/2016
	 */
	List<StoreByCategoryDTO> getStoreByCategory(Long id_supplier);
	/**
	 * Obtiene tiendas displonibles que no esten en algun otra ruta por criterio de busqueda
	 * @param dto con datos de busqueda
	 * @return lista de objetos de dominio {AvailableStoreDTO}
	 * @author JuanCarlosRamosCampos
	 * @since 08-03-2016
	 */
	List<AvailableStoreDTO> getAvailableStoreByCriteria(AvailableStoreSearchCriteria dto);
	
	/*
	 * Obtiene los store que no se han agregado al viaje
	 */
	List<StoreByRouteDTO> getStoreAvailableInTravel(StoreAvailableInTravelCriteria criteria);
	
	/*
	 * Obtiene listado de store mediante un arreglo de ids de rutas
	 * @param Long[]
	 * @return List<Store>
	 * @authos DMarin
	 * @since 02-02-2017
	 */
	List<StoreRouteDTO> getStoresByIdsRoute(StoreByIdsSearchCriteria dto);
	
	/*
	 * Obtiene el total de clientes por plaza
	 * @param Long
	 * @return Long
	 * @author DMarin
	 * @since 30-05-2017
	 */
	Long getTotalStore(Long id_retail);
	
}
