package com.retailsbs.logistikapp.retail.service;

import java.util.List;

import com.retailsbs.logistikapp.retail.domain.Actives;
import com.retailsbs.logistikapp.retail.domain.ActivesVisited;
import com.retailsbs.logistikapp.retail.domain.City;
import com.retailsbs.logistikapp.retail.domain.Country;
import com.retailsbs.logistikapp.retail.domain.Locality;
import com.retailsbs.logistikapp.retail.domain.Retail;
import com.retailsbs.logistikapp.retail.domain.RetailCategory;
import com.retailsbs.logistikapp.retail.domain.RetailDept;
import com.retailsbs.logistikapp.retail.domain.State;
import com.retailsbs.logistikapp.retail.domain.Store;
import com.retailsbs.logistikapp.retail.domain.StoreCategory;
import com.retailsbs.logistikapp.retail.dto.ActivesAndVisitsCriteria;
import com.retailsbs.logistikapp.retail.dto.ActivesAndVisitsDTO;
import com.retailsbs.logistikapp.retail.dto.ActivesVisitedCriteria;
import com.retailsbs.logistikapp.retail.dto.ActivesVisitedExt;
import com.retailsbs.logistikapp.retail.dto.AddCityDTO;
import com.retailsbs.logistikapp.retail.dto.AddCountryDTO;
import com.retailsbs.logistikapp.retail.dto.AddLocalityDTO;
import com.retailsbs.logistikapp.retail.dto.AddRetailCategoryDTO;
import com.retailsbs.logistikapp.retail.dto.AddRetailDTO;
import com.retailsbs.logistikapp.retail.dto.AddRetailDeptDTO;
import com.retailsbs.logistikapp.retail.dto.AddStateDTO;
import com.retailsbs.logistikapp.retail.dto.AddStoreCategoryDTO;
import com.retailsbs.logistikapp.retail.dto.AddStoreDTO;
import com.retailsbs.logistikapp.retail.dto.AvailableStoreByIdRetail;
import com.retailsbs.logistikapp.retail.dto.AvailableStoreByIdRetailIdRoute;
import com.retailsbs.logistikapp.retail.dto.AvailableStoreByIdStoreCategory;
import com.retailsbs.logistikapp.retail.dto.AvailableStoreByIdStoreCategoryIdRoute;
import com.retailsbs.logistikapp.retail.dto.AvailableStoreByIdSupplierIdRoute;
import com.retailsbs.logistikapp.retail.dto.ChangeRetailCriteria;
import com.retailsbs.logistikapp.retail.dto.CityCodeAvaibleSearchCriteria;
import com.retailsbs.logistikapp.retail.dto.CityDTO;
import com.retailsbs.logistikapp.retail.dto.CountryAvaibleSearchCriteria;
import com.retailsbs.logistikapp.retail.dto.CountryDTO;
import com.retailsbs.logistikapp.retail.dto.CountrySearchCriteria;
import com.retailsbs.logistikapp.retail.dto.GetCitySearchCriteria;
import com.retailsbs.logistikapp.retail.dto.GetLocalitySearchCriteria;
import com.retailsbs.logistikapp.retail.dto.GetStateSearchCriteria;
import com.retailsbs.logistikapp.retail.dto.LocalityCodeSearchCriteria;
import com.retailsbs.logistikapp.retail.dto.LocalityDTO;
import com.retailsbs.logistikapp.retail.dto.QtyStrCheckByRetai;
import com.retailsbs.logistikapp.retail.dto.QtyStrCheckByRetailDTO;
import com.retailsbs.logistikapp.retail.dto.RetailAvaibleSearchCriteria;
import com.retailsbs.logistikapp.retail.dto.RetailByIdsSearchCriteria;
import com.retailsbs.logistikapp.retail.dto.RetailCategoryActiveSearchCriteria;
import com.retailsbs.logistikapp.retail.dto.RetailDTO;
import com.retailsbs.logistikapp.retail.dto.RetailSearchCriteria;
import com.retailsbs.logistikapp.retail.dto.StateAvaibleSearchCriteria;
import com.retailsbs.logistikapp.retail.dto.StateDTO;
import com.retailsbs.logistikapp.retail.dto.StateQtyCityDTO;
import com.retailsbs.logistikapp.retail.dto.StoreActiveByIdsRetailSearchCriteria;
import com.retailsbs.logistikapp.retail.dto.StoreAvailableInRouteCriteria;
import com.retailsbs.logistikapp.retail.dto.StoreAvailableInTravelCriteria;
import com.retailsbs.logistikapp.retail.dto.StoreByCategoryDTO;
import com.retailsbs.logistikapp.retail.dto.AvailableStoreSearchCriteria;
import com.retailsbs.logistikapp.retail.dto.AvailableStoreDTO;
import com.retailsbs.logistikapp.retail.dto.StoreByIdsSearchCriteria;
import com.retailsbs.logistikapp.retail.dto.StoreByNameAddress;
import com.retailsbs.logistikapp.retail.dto.StoreByRetailDTO;
import com.retailsbs.logistikapp.retail.dto.StoreByRouteDTO;
import com.retailsbs.logistikapp.retail.dto.StoreCallCenterDTO;
import com.retailsbs.logistikapp.retail.dto.StoreCategoryActiveSearchCriteria;
import com.retailsbs.logistikapp.retail.dto.StoreCategorySearchCriteria;
import com.retailsbs.logistikapp.retail.dto.StoreCodeAvaibleSearchCriteria;
import com.retailsbs.logistikapp.retail.dto.StoreDTO;
import com.retailsbs.logistikapp.retail.dto.StoreInfoDTO;
import com.retailsbs.logistikapp.retail.dto.StoreInfoSearchCriteria;
import com.retailsbs.logistikapp.retail.dto.StoreNotInTravelSearchCriteria;
import com.retailsbs.logistikapp.retail.dto.StoreRouteDTO;
import com.retailsbs.logistikapp.retail.dto.StoreSearchCriteria;
import com.retailsbs.logistikapp.retail.dto.TotalStoreCreateCriteria;
import com.retailsbs.logistikapp.retail.dto.UpdCityDTO;
import com.retailsbs.logistikapp.retail.dto.UpdCountryDTO;
import com.retailsbs.logistikapp.retail.dto.UpdLocalityDTO;
import com.retailsbs.logistikapp.retail.dto.UpdRetailCategoryDTO;
import com.retailsbs.logistikapp.retail.dto.UpdRetailDTO;
import com.retailsbs.logistikapp.retail.dto.UpdRetailDeptDTO;
import com.retailsbs.logistikapp.retail.dto.UpdStateDTO;
import com.retailsbs.logistikapp.retail.dto.UpdStoreCategoryDTO;
import com.retailsbs.logistikapp.retail.dto.UpdStoreDTO;
import com.retailsbs.logistikapp.retail.dto.VerifyAndRegistCheckInCriteria;
import com.retailsbs.logistikapp.retail.exception.CityCodeDuplicateException;
import com.retailsbs.logistikapp.retail.exception.CityNotFoundException;
import com.retailsbs.logistikapp.retail.exception.CountryCodeDuplicateException;
import com.retailsbs.logistikapp.retail.exception.CountryNotFoundException;
import com.retailsbs.logistikapp.retail.exception.LocalityCodeDuplicadeException;
import com.retailsbs.logistikapp.retail.exception.LocalityNotFoundException;
import com.retailsbs.logistikapp.retail.exception.RetailCategoryNotFoundException;
import com.retailsbs.logistikapp.retail.exception.RetailDeptNotFoundException;
import com.retailsbs.logistikapp.retail.exception.RetailDuplicadoException;
import com.retailsbs.logistikapp.retail.exception.RetailNotFoundException;
import com.retailsbs.logistikapp.retail.exception.StateDuplicateException;
import com.retailsbs.logistikapp.retail.exception.StateNotFoundException;
import com.retailsbs.logistikapp.retail.exception.StoreCategoryNotFoundException;
import com.retailsbs.logistikapp.retail.exception.StoreCodeDuplicadeException;
import com.retailsbs.logistikapp.retail.exception.StoreNotFoundException;

/**
 * Interface de ServicioRetail
 * @author Juan Carlos Ramos Campos
 * @since 20-11-2014
 * @modified 13-01-2015 - JORGE - Se modifica finder/retail
 * @modified 16-01-2015 - JORGE - Se agrega metodo getAllStateQtyCityByIdCountry
 * @modified 09-07-2015 - JUAN CARLOS - Se agrega metodo getTotalStoreByIdSupplier
 */
public interface RetailService {
	
	/*
	 * Country
	 */
	
	/**
	 * Agrega un nuevo country
	 * @param dto dto con datos de country
	 * @return id de registro creado
	 * @author Juan Carlos Ramos Campos
	 * @since 20-11-2014
	 */
	Long addCountry(AddCountryDTO dto);
	/**
	 * Actualiza los datos de country
	 * @param dto dto con nuevos datos de country
	 * @return numero de registros modificados
	 * @author Juan Carlos Ramos Campos
	 * @throws CountryNotFoundException 
	 * @since 20-11-2014
	 */
	int updCountry(UpdCountryDTO dto) throws CountryNotFoundException;
	/**
	 * Obtiene un country por id
	 * @param id_country
	 * @return objeto de dominio {Country}
	 * @author Juan Carlos Ramos Campos
	 * @throws CountryNotFoundException 
	 * @since 20-11-2014
	 */
	Country getCountryById(Long id_country) throws CountryNotFoundException;
	/**
	 * Obtiene todos los Country
	 * @return Lista de Objetos de dominio {Country}
	 * @throws CountryNotFoundException 
	 * @author Juan Carlos Ramos Campos
	 * @since 20-11-2014
	 */
	List<Country> getAllCountry();
	
	/*
	 * State
	 */
	
	/**
	 * Agrega un nuevo State
	 * @param dto dto con datos de state
	 * @return Id de registro creado
	 * @author Juan Carlos Ramos Campos
	 * @since 21-11-2014
	 */
	Long addState(AddStateDTO dto);
	/**
	 * Actualiza los datos de State
	 * @param dto dto con nuevos datos de state
	 * @return numero de registros modificados
	 * @throws StateNotFoundException
	 * @author Juan Carlos Ramos Campos
	 * @since 21-11-2014
	 */
	int updState(UpdStateDTO dto) throws StateNotFoundException;
	/**
	 * Obtiene State por id
	 * @param id_state id state
	 * @return objeto de dominio {State} 
	 * @throws StateNotFoundException
	 * @author Juan Carlos Ramos Campos
	 * @since 21-11-2014
	 */
	State getStateById(Long id_state) throws StateNotFoundException;
	/**
	 * Obtiene lista de todos los State
	 * @return Lista de Objetod de dominio {State}
	 * @author Juan Carlos Ramos Campos
	 * @since 21-11-2014
	 */
	List<State> getAllState();
	/**
	 * Obtiene una lista de estados para un pais en particular
	 * @param id_country Id pais
	 * @return Lista de objetos de dominio {State}
	 * @author jorge
	 * @since 09-01-2015
	 */
	List<State> getAllStatesByIdCountry(Long id_country);

	/*
	 * City
	 */
	
	/**
	 * Agrega un nuevo city
	 * @param dto con datos de city
	 * @return in de registro que se agrego
	 */
	Long addCity(AddCityDTO dto);
	/**
	 * Actualiza datos de city
	 * @param dto con nuevos datos de city
	 * @return numero de registros modificados
	 * @throws CityNotFoundException 
	 */
	int updCity(UpdCityDTO dto) throws CityNotFoundException;
	/**
	 * Obtiene un city por medio de id
	 * @param id_city id city
	 * @return objeto de domino {City}
	 * @throws CityNotFoundException 
	 */
	City getCityById(Long id_city) throws CityNotFoundException;
	/**
	 * Obtiene lista de todos los city
	 * @return lista de objetos de dominio {City}
	 */
	List<City> getAllCity();
	/**
	 * Verifica que el codigo de city no este en uso
	 * @param dto con datos de busqueda
	 * @throws CityCodeDuplicateException
	 * @author Juan Carlos Ramos Campos
	 * @since 11-12-2014
	 */
	void getCityCodeAvaibleByCriteria(CityCodeAvaibleSearchCriteria dto) throws CityCodeDuplicateException;
	/**
	 * Obtiene una lista todas las ciudades por estado
	 * @param id_state Id estado
	 * @return Lista de objetos de dominio {City}
	 * @author jorge
	 * @since 09-01-2015
	 */
	List<City> getAllCityByIdState(Long id_state);
	
	/*
	 * Locality
	 */

	/**
	 * Agrega un nuevo Locality
	 * @param dto con datos de locality
	 * @return id del registro agregado
	 */
	Long addLocality(AddLocalityDTO dto);
	/**
	 * Actualiza datos de locality
	 * @param dto con nuevos datos de locality
	 * @return numero de registros modificados
	 * @throws LocalityNotFoundException
	 */
	int updLocality(UpdLocalityDTO dto) throws LocalityNotFoundException;
	/**
	 * Obtiene locality por id
	 * @param id_locality id locality
	 * @return un objeto de dominio {Locality}
	 * @throws LocalityNotFoundException
	 */
	Locality getLocalityById(Long id_locality) throws LocalityNotFoundException;
	/**
	 * Obtiene lista de todos los locality
	 * @return lista de objetos de dominio {Locality}
	 */
	List<Locality> getAllLocality();
	/**
	 * verifica que el codigo que se agrega no este activo o no exista.
	 * @param dto con datos de busqueda
	 * @throws LocalityCodeDuplicadeException
	 * @author Juan Carlos Ramos Campos
	 * @since 11-12-2014
	 */
	void getLocalityCodeAvaibleByCriteria(LocalityCodeSearchCriteria dto) throws LocalityCodeDuplicadeException;
	/**
	 * Obtiene una lista de todos las localidades por ciudad
	 * @param id_city Id ciudad
	 * @return Lista de objetos de dominio {Locality}
	 * @author jorge
	 * @since 09-01-2015
	 */
	List<Locality> getAllLocalityByIdCity(Long id_city);

	/*
	 * Retail
	 */

	/**
	 * Agrega un nuevo Retail
	 * @param dto con datos de retail
	 * @return id de registro agregado
	 */
	Long addRetail(AddRetailDTO dto);
	/**
	 * Actualiza datos de Retail
	 * @param dto con nuevos datos de Retail
	 * @return numero de registros actualizados
	 * @throws RetailNotFoundException
	 */
	int updRetail(UpdRetailDTO dto) throws RetailNotFoundException;
	/**
	 * Obtiene un retail por id
	 * @param id_retail id retail
	 * @return objeto de dominio {Retail}
	 * @throws RetailNotFoundException
	 */
	Retail getRetailById(Long id_retail) throws RetailNotFoundException;
	/**
	 * Obtiene lista de todos los retail que hay
	 * @return lista de objetos de dominio {Retail}s
	 */
	List<Retail> getAllRetail();
	/**
	 * Elimina retail por id_retai
	 * @param id_retail
	 * @return numero de retails eliminados
	 * @author JuanCarlosRamosCampos
	 * @throws RetailNotFoundException 
	 * @since 21-11-2015
	 */
	int delRetailById(Long id_retail) throws RetailNotFoundException;
	/**
	 * Obtiene retail por id_supplier
	 * @param id_supplier
	 * @return lista de objetos de dominio {Retail}
	 * @author JuanCarlosRamosCampos
	 * @since 23-11-2015
	 */
	List<Retail> getRetailByIdSupplier(Long id_supplier);

	/*
	 * RetailDept
	 */

	/**
	 * Agrega un nuevo Retail_dept
	 * @param dto dto con datos de retaildept
	 * @return id de registro agregado
	 */
	Long addRetailDept(AddRetailDeptDTO dto);
	/**
	 * Actualiza datos de retailDept
	 * @param dto con nuevos datos de retailDept
	 * @return numero de registros actualizados
	 * @throws RetailDeptNotFoundException
	 */
	int updRetailDept(UpdRetailDeptDTO dto) throws RetailDeptNotFoundException;
	/**
	 * Ontiene un retailDept por id
	 * @param id_retail_dept
	 * @return un objeto de dominio {RetailDept}
	 * @throws RetailDeptNotFoundException
	 */
	RetailDept getRetailDeptById(Long id_retail_dept) throws RetailDeptNotFoundException;
	/**
	 * Obtiene lista completa de RetailDept
	 * @return lista de objetos de dominio {RetailDept}
	 */
	List<RetailDept> getAllRetailDept();
	/**
	 * Obtiene los departamentos de retail por id retail
	 * @param id_retail id retail
	 * @return lista de objetos de dominio {RetailDept}
	 * @author juan carlos
	 * @since 27-03-2015
	 */
	List<RetailDept> getRetailDeptsByIdRetail(Long id_retail);
	/**
	 * Elimina registro de retail dept por medio del id retail dept 
	 * @param id_retaildept id retail dept
	 * @return numero de registros eliminados
	 * @author juan carlos
	 * @throws RetailDeptNotFoundException 
	 * @since 27-03-2015
	 */
	int delRetailDeptById(Long id_retaildept) throws RetailDeptNotFoundException;
	
	/*
	 * finder retail dept
	 */
	
	/**
	 * Elimina retailDept por medio de id_retail
	 * @param id_retail
	 * @return numero de retailDept eliminados
	 * @author JuanCarlosRamosCampos
	 * @since 21-11-2015
	 */
	int delRetailDeptByIdRetail(Long id_retail);
	
	/*
	 * Store
	 */

	/**
	 * Agrega un nuevo Store
	 * @param dto con datos de store
	 * @return id de registro agregado
	 */
	Long addStore(AddStoreDTO dto);
	/**
	 * Actualiza datos de Store
	 * @param dto con nuevos datos de Store
	 * @return numero de registros actualizados
	 * @throws StoreNotFoundException
	 */
	int updStore(UpdStoreDTO dto) throws StoreNotFoundException;
	/**
	 * Obtiene un Store por medio de id
	 * @param id_store id store
	 * @return un objeto de dominio {Store}
	 * @throws StoreNotFoundException
	 */
	Store getStoreById(Long id_store) throws StoreNotFoundException;
	/**
	 * Obtiene lista completa de todos los store
	 * @return lista de objetos de dominio {Store}
	 */
	List<Store> getAllStore();
	/**
	 * Verifica que el codigo de store no este siendo usado por alguna tienda activa
	 * @param dto con datos de busqueda
	 * @throws StoreCodeDuplicadeException
	 * @author Juan Carlos Ramos Campos
	 * @since 11-12-2014
	 */
	void getStoreCodeAvaibleByCriteria(StoreCodeAvaibleSearchCriteria dto) throws StoreCodeDuplicadeException;
	/**
	 * obtiene lista de todos los store con id_store_category especifico
	 * @param id_store_category
	 * @return lista de objeto de dominio {store}
	 * @author Juan Carlos Ramos Campos
	 * @since 03-02-2015
	 */
	List<Store> getAllStoreByIdStoreCategory(Long id_store_category);

	/**
	 * obtiene lista de todos los store con id_supplier especifico
	 * @param id_supplier
	 * @return lista de objeto de dominio {store}
	 * @author Sergio Valenzuela
	 * @since 18-08-2015
	 */
	List<Store> getStoreByIdSupplier(Long id_supplier);
	
	/**
	 * obtiene lista de todos los store con id_supplier especifico que no esten ya en uso
	 * @param id_supplier
	 * @return lista de objeto de dominio {store}
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
	 * elimina store por id_store
	 * @param id_store
	 * @return numero de store eliminados
	 * @author JuanCarlosRamosCampos
	 * @throws StoreNotFoundException 
	 * @since 20-11-2015
	 */
	int delStoreById(Long id_store) throws StoreNotFoundException;
	
	/*
	 * Finder/Retail
	 */
	
	/**
	 * Obtiene una lista de retailers a partir de un criterio de busqueda
	 * @param dto Dto con criterio de busqueda
	 * @return Lista de objetos de {RetailDTO}
	 * @author Juan Carlos Ramos Campos
	 * @since 25-11-2014
	 * @modified 13-01-2015
	 */
	List<RetailDTO> getRetailByCriteria(RetailSearchCriteria dto);
	/**
	 * Obtiene un objeto con informacion extendida de un comercio
	 * @param id_retail Id retail
	 * @return Objeto {RetailDTO} con informacion extendida del retail
	 * @throws RetailNotFoundException
	 * @author juan carlos
	 * @since 26-03-2015
	 */
	RetailDTO getRetailExtById(Long id_retail) throws RetailNotFoundException;
	
	/**
	 * Elimina retail por id_supplier
	 * @param id_supplier
	 * @return numero de retails eliminados
	 * @author JuanCarlosRamosCampos
	 * @since 23-11-2015
	 */
	int delRetailByIdSupplier(Long id_supplier);
	
	/*
	 * Cambia de retail los clientes pertenecientes
	 * @param ChangeStoresRetailCriteria
	 * @return Long
	 * @author DMarin
	 * @since 30-05-2017
	 */
	int changeStoresRetail(ChangeRetailCriteria criteria);
	
	/*
	 * Finder/Store
	 */
	
	/**
	 * Obtiene lista de store por criteria
	 * @param dto con datos de busqueda
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
	 * Obtiene una lista de los los store (locales) de un retail (cliente) que no esten siendo usadas por alguna ruta
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
	 * Obtiene una lista de los los store (locales) de un store category que no esten siendo utilizadas por alguna ruta
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
	 * @throws StoreNotFoundException
	 * @author jorge
	 * @since 19-03-2015
	 */
	StoreDTO getStoreExtById(Long id_store) throws StoreNotFoundException;
	
	/**
	 * Obtiene una lista de ids de los los stores (locales) activos de un retail (comercio)
	 * @param id_retail Id retail (comercio)
	 * @return Lista ids de stores
	 * @author Jorge
	 * @since 31-03-2015
	 */
	List<Long> getIdStoresActiveByIdRetail(Long id_retail);
	
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
	 * @param dto con id_route e id_travel 
	 * @return lista de StoresDTO que estan en algun viaje 
	 * @author Nataly
	 * @since 07-09-2015
	 */
	List<StoreDTO> getStoreInTravelByRoute(StoreAvailableInRouteCriteria dto);
	/**
	 * @param dto con id_route, id_travel 
	 * @return lista de StoresDTO que estan en un viaje distinto  
	 * @author Nataly
	 * @since 10-09-2015
	 */
	List<StoreDTO> getStoreInOtherTravelByCriteria(StoreSearchCriteria dto);
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
	 * @since 26-08-2015
	 */
	List<StoreByRouteDTO> getStoreNotInTravel(StoreNotInTravelSearchCriteria dto);

	/**
	 * obtiene el total de tiendas por criterio
	 * @return numero de tiendas obtenidas
	 * @param dto con criterio de busqueda
	 * @author jucaraca
	 * @since 12-19-2015
	 */
	Integer getTotalStoreByCriteria(TotalStoreCreateCriteria dto);
	
	/**
	 * elimina tiendas por id_store_category
	 * @param id_store_category
	 * @return numero de tiendas eliminadas
	 * @author JuanCarlosRamosCampos
	 * @since 23-11-2015
	 */
	int delStoreByIdStoreCategory(Long id_store_category);

	/**
	 * Obtiene tiendas displonibles que no esten en algun otra ruta por criterio de busqueda
	 * @param dto con datos de busqueda
	 * @return lista de objetos de dominio {AvailableStoreDTO}
	 * @author JuanCarlosRamosCampos
	 * @since 08-03-2016
	 */
	List<AvailableStoreDTO> getAvailableStoreByCriteria(AvailableStoreSearchCriteria dto);
	
	/*
	 * Obtiene listado de store mediante un arreglo de ids de rutas
	 * @param Long[]
	 * @return List<Store>
	 * @authos DMarin
	 * @since 02-02-2017
	 */
	List<StoreRouteDTO> getStoresByIdsRoute(StoreByIdsSearchCriteria ids);

	/*
	 * Finder State
	 */
	
	/**
	 * Obtiene lista de State por criterio de busqueda
	 * @param dto con datos de busqueda
	 * @return lista de objetos de dominio {StateDTO}
	 * @author Juan Carlos Ramos Campos
	 * @since 25-11-2014
	 */
	List<StateDTO> getStateByCriteria(GetStateSearchCriteria dto);
	
	/**
	 * Obtiene una lista de todos los estados y la cantidad de ciudades asociadas para un pais
	 * @param id_country Id pais
	 * @return Lista de objetos {StateQtyCityDTO}
	 * @author jorge
	 * @since 16-01-2015
	 */
	List<StateQtyCityDTO> getAllStateQtyCityByIdCountry(Long id_country);

	/*
	 * Finder City
	 */
	
	/**
	 * Obtiene lista de city's con criterio de bussqueda
	 * @param dto con datos de busqueda
	 * @return lista de objetos de dominio {CityDTO}
	 * @author Juan Carlos Ramos Campos
	 * @since 25-11-2014
	 */
	List<CityDTO> getCityByCriteria(GetCitySearchCriteria dto);
	/**
	 * Obtiene numero de registros totales de busqueda 
	 * @param dto con datos de busqueda
	 * @return numero de registros encontrados
	 * @author Juan Carlos Ramos Campos
	 * @since 27-11-2014
	 */
	Integer getCityTotalByCriteria(GetCitySearchCriteria dto);
	
	/*
	 * Retail Category
	 */
	
	/**
	 * Agrega un RetailCategory
	 * @param dto con datos de RetailCategory
	 * @return id de registro creado
	 * @author Juan Carlos Ramos Campos
	 * @since 28-11-2014
	 */
	Long addRetailCategory(AddRetailCategoryDTO dto);
	/**
	 * Actualiza datos de RetailCategory
	 * @param dto con nuevos datos de retailCategory
	 * @return numero de registros actualizados
	 * @author Juan Carlos Ramos Campos
	 * @throws RetailCategoryNotFoundException 
	 * @since 28-11-2014
	 */
	int updRetailCategory(UpdRetailCategoryDTO dto) throws RetailCategoryNotFoundException;
	/**
	 * Obtiene datos de un retail category por medio de un id
	 * @param id_retail_category
	 * @return objeto de dominio {RetailCategory}
	 * @author Juan Carlos Ramos Campos
	 * @throws RetailCategoryNotFoundException 
	 * @since 28-11-2014
	 */
	RetailCategory getRetailCategoryById(Long id_retail_category) throws RetailCategoryNotFoundException;
	/**
	 * Obtiene lista de datos de todos los RetailCatregory que hay
	 * @return lista de objetos de dominio {RetailCategory}
	 * @author Juan Carlos Ramos Campos
	 * @since 28-11-2014
	 */
	List<RetailCategory> getAllRetailCategory();
	/**
	 * Obtiene una lista de todas las categorias de retail de un proveedor
	 * @param id_supplier Id supplier
	 * @return Lista de objetos de dominio {RetailCategory}
	 * @author jorge
	 * @since 15-01-2015
	 */
	List<RetailCategory> getAllRetailCategoryByIdSupplier(Long id_supplier);
	
	/**
	 * verifica que state no este ya creado o activo
	 * @param dto con datos de busqueda
	 * @throws StateDuplicateException
	 * @author Juan Carlos Ramos Campos
	 * @since 09-12-2014
	 */
	void getStateAvaibleByCriteria(StateAvaibleSearchCriteria dto) throws StateDuplicateException;
	/**
	 * verifica si ya esta definido el retail 
	 * @param dto con datos de busqueda
	 * @throws RetailDuplicadoException
	 * @author Juan Carlos Ramos Campos
	 * @since 09-12-2014
	 */
	void getRetailAvaibleByCriteria(RetailAvaibleSearchCriteria dto) throws RetailDuplicadoException;
	
	/*
	 * Finder Locality
	 */
	
	/**
	 * Obtiene lista de locality's por criterio.
	 * @param dto dto con datos de busqueda
	 * @return lista de objetos de dominio {LocalityDTO}
	 * @author Juan Carlos Ramos Campos
	 * @since 10-12-2014
	 */
	List<LocalityDTO> getLocalityByCriteria(GetLocalitySearchCriteria dto);
	/**
	 * Obtiene numero de registros de getLocalityByCriteria
	 * @param dto con datos de busqueda
	 * @return numero de registros de busqueda
	 * @author Juan Carlos Ramos Campos
	 * @since 10-12-2014
	 */
	Integer getLocalityTotalByCriteria(GetLocalitySearchCriteria dto);
	
	/*
	 * Finder Country
	 */
	
	/**
	 * Obtiene lista de country's por criterio.
	 * @param dto dto con datos de busqueda
	 * @return lista de objetos de dominio {CountryDTO}
	 * @author Juan Carlos Ramos Campos
	 * @since 10-12-2014
	 */
	List<CountryDTO> getCountryByCriteria(CountrySearchCriteria dto);
	/**
	 * Obtiene numero de registros de getCountryByCriteria
	 * @param dto con datos de busqueda
	 * @return numero de registros de busqueda
	 * @author Juan Carlos Ramos Campos
	 * @since 10-12-2014
	 */
	Integer getCountryTotalByCriteria(CountrySearchCriteria dto);
	/**
	 * Verifica si el codigo de country no este dado de alta
	 * @param dto
	 * @author Juan Carlos Ramos Campos
	 * @throws CountryCodeDuplicateException 
	 * @since 10-12-2014
	 */
	void getCountryAvaibleByCriteria(CountryAvaibleSearchCriteria dto) throws CountryCodeDuplicateException;

	/*
	 * Store Category 
	 */
	
	/**
	 * Agrega un StoreCategory
	 * @param dto con datos de storeCategory
	 * @return id de StoreCategory agregado
	 * @author juan carlos
	 * @since 30-01-2015
	 */
	Long addStoreCategory(AddStoreCategoryDTO dto);
	/**
	 * Modifica datos de StoreCategory
	 * @param dto con datos de storeCategory
	 * @return numero de registros modificados
	 * @throws StoreCategoryNotFoundException
	 * @author juan carlos
	 * @since 30-01-2015
	 */
	int updStoreCategory(UpdStoreCategoryDTO dto) throws StoreCategoryNotFoundException;
	/**
	 * Obtiene storeCategiry por id
	 * @param id_store_category
	 * @return objeto de dominio {StoreCategory}
	 * @throws StoreCategoryNotFoundException
	 * @author juan carlos
	 * @since 30-01-2015
	 */
	StoreCategory getStoreCategoryById(Long id_store_category) throws StoreCategoryNotFoundException;
	/**
	 * Obtiene todos los StoreCategory
	 * @return lista de objetos de dominio {StoreCategory}
	 * @author juan carlos
	 * @since 30-01-2015
	 */
	List<StoreCategory> getAllStoreCategory();
	/**
	 * Obtiene todos los StoreCategory activos o todos los inactivos
	 * @return lista de objetos de dominio {StoreCategory}
	 * @author juan carlos
	 * @since 24-03-2015
	 */
	List<StoreCategory> getAllStoreCategory(String estado);
	/**
	 * Obtiene una lista de todas las categorias de tienda de un proveedor
	 * @param id_supplier Id supplier
	 * @return Lista de objetos de dominio {StoreCategory}
	 * @author juan carlos
	 * @since 03-02-2015
	 */
	List<StoreCategory> getAllStoreCategoryByIdSupplier(Long id_supplier);
	/**
	 * 
	 * @param dto con id_supplier y active
	 * @return lista de objetos de dominio StoreCategory  
	 * @author Nataly
	 * @since 03-07-2015
	 */
	List<StoreCategory> getAllStoreCategoryBySupplier(StoreCategorySearchCriteria dto);

	/*
	 * Finder Store Category
	 */
	
	/**
	 * 
	 * @param dto con id_supplier, active y id_store_category
	 * @return lista de objetos de dominio StoreCategory
	 * @author Sergio Valenzuela
	 * @since 03-07-2015
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
	
	/*
	 * Finder Retail_Category
	 */
	/**
	 * Obtiene una lista de los retail_category (categoria de comercio) activos de un supplier (provedor) y si seteas id_retail_category manda tambien la categoria que usa el retail aunque este inactiva
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
	
	/**
	 * Elimina storeCategory por medio de id_supplier
	 * @param id_supplier
	 * @return numero de StoreCategory eliminados
	 * @author AxelMonroy
	 * @since 26-02-2016
	 */
	int delStoreCategoryById(Long id_store_category);

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
	 * @param dto ids retail
	 * @return Lista de id store 
	 * @author Nataly
	 * @since 12-08-2015
	 */
	List<Long> getIdStoresActiveByIdsRetail(StoreActiveByIdsRetailSearchCriteria dto);
	/**
	 * 
	 * @param ids_retail
	 * @return lista de objetos retail
	 * @author Nataly
	 * @since 12-08-2015
	 */
	List<Retail> getRetailByIds(RetailByIdsSearchCriteria dto);
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
	 * Obtiene lista de los retail y las tiendas totates que tiene en viajes y las tiendas de los  viajes que tienen estado checkin
	 * @param dto con datos de busqueda
	 * @return lista de objetos de dominio {QtyStrCheckByRetailDTO}
	 * @author JuanCarlosRamosCampos
	 * @since 17-11-2015
	 */
	List<QtyStrCheckByRetailDTO> getRetailCountStrCheck(QtyStrCheckByRetai dto);

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
	 * Obtiene la cantidad de clientes para verificado de planes
	 * @param dto Dto con criterio de busqueda
	 * @return Cantidad de clientes int
	 * @author David Rosales Muñoz
	 * @since 04-05-2016
	 */
	Integer getCliQtyByCriteria(RetailSearchCriteria dto);
	
	/*
	 * Obtiene los store que no se han agregado al viaje
	 */
	List<StoreByRouteDTO> getStoreAvailableInTravel(StoreAvailableInTravelCriteria criteria);
	
	/*
	 * Obtiene el total de clientes por plaza
	 * @param Long
	 * @return Long
	 * @author DMarin
	 * @since 30-05-2017
	 */
	Long getTotalStore(Long id_retail);
	
	/**
	 * Actives
	 */
	int addActive(Actives actives);
	
	int updateActive(Actives actives);
	
	int deleteActive(Long id_active);
	
	List<Actives> getActives(Long id_supplier, Long id_store);
	
	Actives getActiveById(Long id_active);
	
	int transferActive(Long id_active, Long id_store);
	
	/**
	 * Actives Visited
	 */
	int addActiveVisited(ActivesVisited activesVisited);
	
	int updateActive(ActivesVisited activesVisited);
	
	int deleteActiveVisited(Long id_active_visited);
	
	List<ActivesVisited> getActivesVisited(Long id_active);
	
	ActivesVisited getActiveVisitedById(Long id_active_visited);
	
	/**
	 * Finder Actives/Visited
	 */
	int deleteActivesVisitedByIdActive(Long id_active);
	
	List<ActivesVisitedExt> getVisitedList(ActivesVisitedCriteria criteria);
	
	List<ActivesAndVisitsDTO> getActivesAndVisits(ActivesAndVisitsCriteria criteria);
		
	String verifyAndRegistCheckIn(VerifyAndRegistCheckInCriteria criteria);
	
}
