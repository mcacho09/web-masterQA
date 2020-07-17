package com.retailsbs.logistikapp.logistic.service;

import java.util.Date;
import java.util.List;

import com.retailsbs.logistikapp.logistic.domain.Route;
import com.retailsbs.logistikapp.logistic.domain.RouteStore;
import com.retailsbs.logistikapp.logistic.domain.Travel;
import com.retailsbs.logistikapp.logistic.domain.UserPosition;
import com.retailsbs.logistikapp.logistic.domain.WayBill;
import com.retailsbs.logistikapp.logistic.dto.AviableCodeRouteSearchCriteria;
import com.retailsbs.logistikapp.logistic.dto.CountRouteSupplierCriteria;
import com.retailsbs.logistikapp.logistic.dto.CustomersNotVisitedCriteria;
import com.retailsbs.logistikapp.logistic.dto.CustomersNotVisitedDTO;
import com.retailsbs.logistikapp.logistic.dto.InfoReportPromotion;
import com.retailsbs.logistikapp.logistic.dto.MetricsCounterCriteria;
import com.retailsbs.logistikapp.logistic.dto.MetricsCounterDTO;
import com.retailsbs.logistikapp.logistic.dto.MultipleUsersDTO;
import com.retailsbs.logistikapp.logistic.dto.ParameterReportPromotion;
import com.retailsbs.logistikapp.logistic.dto.ProgressMetricsOperativesCriteria;
import com.retailsbs.logistikapp.logistic.dto.ProgressMetricsOperativesDTO;
import com.retailsbs.logistikapp.logistic.dto.ReasignWaybillFromOldTravelDTO;
import com.retailsbs.logistikapp.logistic.dto.RoutesByListRetails;
import com.retailsbs.logistikapp.logistic.dto.StoresNotVisitedByDateAndSupplierCriteria;
import com.retailsbs.logistikapp.logistic.dto.StoresNotVisitedByDateAndSupplierDTO;
import com.retailsbs.logistikapp.logistic.dto.TravelAndStatusStr;
import com.retailsbs.logistikapp.logistic.dto.TravelByIdStoreDTO;
import com.retailsbs.logistikapp.logistic.dto.TravelByIdStoreSearch;
import com.retailsbs.logistikapp.logistic.dto.TravelDTO;
import com.retailsbs.logistikapp.logistic.dto.TravelSearchCriteria;
import com.retailsbs.logistikapp.logistic.dto.TravelsToVisitedCriteria;
import com.retailsbs.logistikapp.logistic.dto.TrvStatusStrSearchCriteria;
import com.retailsbs.logistikapp.logistic.dto.WayBillDTO;
import com.retailsbs.logistikapp.logistic.dto.WayBillSearchCriteria;
import com.retailsbs.logistikapp.logistic.exception.CodeRouteDuplicateException;
import com.retailsbs.logistikapp.logistic.exception.RouteNotFoundException;
import com.retailsbs.logistikapp.logistic.exception.RouteStoreNotFoundException;
import com.retailsbs.logistikapp.logistic.exception.TravelNotFoundException;
import com.retailsbs.logistikapp.logistic.exception.UserScheduleNotAvailableException;
import com.retailsbs.logistikapp.logistic.exception.WayBillNotFoundException;
import com.retailsbs.logistikapp.logistic.dto.AvailableUserScheduleSearchCriteria;

/**
 * Clase interfaz servicio de usuarios
 * 
 * @author jorge
 * @since 17-11-2014
 * @modified 09-02-2015 - JORGE - Se extiende un metodo para obtener
 *           notificaciones
 * @modified 14-03-2015 - JORGE - Se reestructura metodos para las
 *           notificaciones
 * @modified 26-06-2015 - JORGE - Se agrega metodos para las pantallas de
 *           administracion
 * @modified 24/02/2016 - JS - Se agregan metodos para obtener el total de rutas
 *           y viajes
 */
public interface LogisticService {

	/*
	 * Route
	 */

	/**
	 * Agrega objeto de dominio route
	 * 
	 * @param route
	 * @return id_route
	 * @author Nataly
	 * @since 17-08-2015
	 */
	Long addRoute(Route route);

	/**
	 * Se actualiza objeto de dominio Route
	 * 
	 * @param record
	 *            objeto de dominio Route
	 * @return numero de registros modificados
	 * @throws RouteNotFoundException
	 * @author Nataly
	 * @since 17-08-2015
	 */
	int updRouteById(Route record) throws RouteNotFoundException;

	/**
	 * Se elimina registro de tabla route
	 * 
	 * @param id_route
	 * @return numero de registros eliminados
	 * @throws RouteNotFoundException
	 * @author Nataly
	 * @since 17-08-2015
	 */
	int delRouteById(Long id_route) throws RouteNotFoundException;

	/**
	 * Obtiene objeto de dominio route por id
	 * 
	 * @param id_route
	 * @return Objeto de dominio route
	 * @throws RouteNotFoundException
	 * @author Nataly
	 * @since 17-08-2015
	 */
	Route getRouteById(Long id_route) throws RouteNotFoundException;

	/**
	 * Obtiene todos los registros de tabla route
	 * 
	 * @return lista de objetos de dominio Route
	 * @author Nataly
	 * @since 17-08-2015
	 */
	List<Route> getAllRoute();

	/**
	 * Obtiene lista de objetos route por id_supplier
	 * 
	 * @param id_supplier
	 * @return Lista de objetos de dominio Route
	 */
	List<Route> getAllRouteByIdSupplier(Long id_supplier);

	/*
	 * Finder/Route
	 */

	/**
	 * Elimina routa por medio de id_supplier
	 * 
	 * @param id_supplier
	 * @return numero de route eliminadas
	 * @author JuanCarlosRamosCampos
	 * @since 23-11-2015
	 */
	int delRouteByIdSupplier(Long id_supplier);

	/*
	 * RouteStore
	 */

	/**
	 * Agrega objeto de dominio route-store
	 * 
	 * @param route_store
	 * @return id_route_store
	 * @author Nataly
	 * @since 17-08-2015
	 */
	Long addRouteStore(RouteStore route_store);

	/**
	 * Se actualiza objeto de dominio RouteStore
	 * 
	 * @param record
	 *            objeto de dominio RouteStore
	 * @return numero de registros modificados
	 * @throws RouteStoreNotFoundException
	 * @author Nataly
	 * @since 17-08-2015
	 */
	int updRouteStoreById(RouteStore record) throws RouteStoreNotFoundException;

	/**
	 * Se elimina registro de tabla routestore
	 * 
	 * @param id_route_store
	 * @return numero de registros eliminados
	 * @throws RouteStoreNotFoundException
	 * @author Nataly
	 * @since 17-08-2015
	 */
	int delRouteStoreById(Long id_route_store)
			throws RouteStoreNotFoundException;

	/**
	 * Obtiene objeto de dominio routestore por id
	 * 
	 * @param id_route_store
	 * @return Objeto de dominio route_store
	 * @throws RouteStoreNotFoundException
	 * @author Nataly
	 * @since 17-08-2015
	 */
	RouteStore getRouteStoreById(Long id_route_store)
			throws RouteStoreNotFoundException;

	/**
	 * Obtiene todos los registros de tabla route
	 * 
	 * @return lista de objetos de dominio RouteStore
	 * @author Nataly
	 * @since 17-08-2015
	 */
	List<RouteStore> getAllRouteStore();

	/**
	 * Obtiene el numero de cambios realizados al actualizar las tiendas de una
	 * ruta
	 * 
	 * @param List
	 *            <Long> oldList(lista de los idStore que estan actual mente en
	 *            la ruta), List<Long> newList(lista de los idStore que quedaran
	 *            en la ruta), id_route (id de la ruta a modificar),
	 *            log_created_login nobre del usuario que mando a llamar la
	 *            funcion
	 * @return numero de cambios realizados
	 * @author Sergio Valenzuela
	 * @since 02-09-2015
	 */
	int updateRouteStoreByList(List<Long> oldList, List<Long> newList,
			Long id_route, String log_created_login);

	/*
	 * WayBill
	 */

	/**
	 * Agrega objeto de dominio waybill
	 * 
	 * @param waybill
	 * @return id_waybill
	 * @author Nataly
	 * @since 17-08-2015
	 */
	Long addWayBill(WayBill wayBill);

	/**
	 * Se actualiza objeto de dominio waybill
	 * 
	 * @param record
	 *            objeto de dominio waybill
	 * @return numero de registros modificados
	 * @throws WayBillNotFoundException
	 * @author Nataly
	 * @since 17-08-2015
	 */
	int updWayBillById(WayBill record) throws WayBillNotFoundException;

	/**
	 * Se elimina registro de tabla waybill
	 * 
	 * @param id_waybill
	 * @return numero de registros eliminados
	 * @throws WayBillNotFoundException
	 * @author Nataly
	 * @since 17-08-2015
	 */
	int delWayBillById(Long id_wayBill) throws WayBillNotFoundException;
	
	/**
	 * Elimina registros de waybill por id_travel
	 * @param id_travel
	 * @return numero de registros afectados
	 * @author Nataly
	 * @since 26-08-2015
	 * 
	 */
	int delWayBillByIdTravel(Long id_travel);
	
	/*
	 * Se obtiene listado de clientes sin visitar en un rango de fechas
	 * @param CustomersNotVisitedCriteria
	 * @retrurn List<CustomersNotVisitedDTO>
	 * @author DMarin
	 * @since 16-05-2017
	 */
	List<CustomersNotVisitedDTO> getCustomersNotVisited(CustomersNotVisitedCriteria criteria);

	/**
	 * Obtiene objeto de Waybill travel por id
	 * 
	 * @param id_wayBill
	 * @return Objeto de dominio waybill
	 * @throws WayBillNotFoundException
	 * @author Nataly
	 * @since 17-08-2015
	 */
	WayBill getWayBillById(Long id_wayBill) throws WayBillNotFoundException;

	/**
	 * Obtiene todos los registros de tabla waybill
	 * 
	 * @return lista de objetos de dominio waybill
	 * @author Nataly
	 * @since 17-08-2015
	 */
	List<WayBill> getAllWayBill();

	/**
	 * Obtiene objetos de dominio waybill por id_travel
	 * 
	 * @return Lista de objetos de dominio waybill
	 * @author Nataly
	 * @since
	 */
	List<WayBill> getAllWayBillByIdTravel(Long id_travel);
	
	/*
	 * Listado waybill visitados
	 * @return List<Waybill>
	 * @author DMarin
	 * @since 26-05-2017
	 */
	List<WayBill> getWayBillVisited();

	/*
	 * Travel
	 */

	/**
	 * Agrega objeto de dominio travel
	 * 
	 * @param travel
	 * @return id_travel
	 * @author Nataly
	 * @since 17-08-2015
	 */
	Long addTravel(Travel travel);

	/**
	 * Se actualiza objeto de dominio Travel
	 * 
	 * @param record
	 *            objeto de dominio Travel
	 * @return numero de registros modificados
	 * @throws TravelNotFoundException
	 * @author Nataly
	 * @since 17-08-2015
	 */
	int updTravelById(Travel record) throws TravelNotFoundException;

	/**
	 * Se elimina registro de tabla travel
	 * 
	 * @param id_travel
	 * @return numero de registros eliminados
	 * @throws TravelNotFoundException
	 * @author Nataly
	 * @since 17-08-2015
	 */
	int delTravelById(Long id_travel) throws TravelNotFoundException;

	/**
	 * Obtiene objeto de dominio travel por id
	 * 
	 * @param id_travel
	 * @return Objeto de dominio travel
	 * @throws TravelNotFoundException
	 * @author Nataly
	 * @since 17-08-2015
	 */
	Travel getTravelById(Long id_travel) throws TravelNotFoundException;

	/**
	 * Obtiene todos los registros de tabla travel
	 * 
	 * @return lista de objetos de dominio Travel
	 * @author Nataly
	 * @since 17-08-2015
	 */
	List<Travel> getAllTravel();

	/**
	 * obtiene travel por medio de id_route
	 * 
	 * @param id_route
	 * @return lista de objetos de dominio {travel}
	 * @author JuanCarlosRamosCampos
	 * @since 20-11-2015
	 */
	List<Travel> getTravelByIdRoute(Long id_route);

	/**
	 * obtiene viajes relacionados a id_user
	 * 
	 * @param id_user
	 * @return lista de objetos de dominio {Travel}
	 * @author JuanCarlosRamosCampos
	 * @since 25-11-2015
	 */
	List<Travel> getTravelByIdUser(Long id_user);

	/*
	 * Finder/Travel
	 */

	/**
	 * Obtiene listado de objetos Travel por id_supplier
	 * 
	 * @param id_supplier
	 * @return lista de objetos de dominio travel
	 * @author Nataly
	 * @since 18-08-2015
	 */
	List<TravelDTO> getTravelByCriteria(TravelSearchCriteria dto);

	/**
	 * Obtiene los viajes por medio de id store de cierta fecha en adelante
	 * 
	 * @param dto
	 *            con datos de busqued
	 * @return lista de objetos de dominio {TravelByIdStoreDTO}
	 * @author jucaraca
	 * @since 22-10-2015
	 */
	List<TravelByIdStoreDTO> getTravelByIdStore(TravelByIdStoreSearch dto);

	/**
	 * Obtiene una lista maxima de los tres ultimos viajes de un store y obtiene
	 * el checkin en cada uno de los viajes
	 * 
	 * @param dto
	 *            con datos de busqueda
	 * @return lista maxima de 3 objetos de dominio {TravelAndStatusStr}
	 * @author JuanCarlosRamosCampos
	 * @since 18-11-2015
	 */
	List<TravelAndStatusStr> getTravelAndStatusStrByIdStore(
			TrvStatusStrSearchCriteria dto);

	/**
	 * elimina viajes por medio de id_route
	 * 
	 * @param id_route
	 * @return numero de viajes eliminados
	 * @author JuanCarlosRamosCampos
	 * @since 23-11-2015
	 */
	int delTravelByIdRoute(Long id_route);

	/**
	 * elimina travels relacionados al id_user
	 * 
	 * @param id_user
	 * @return numero de travel eliminados
	 * @author JuanCarlosRamosCampos
	 * @since 25-11-2015
	 */
	int delTravelByIdUser(Long id_user);

	/**
	 * 
	 * @param dto
	 *            con id_user y schedule
	 * @throws UserScheduleNotAvailableException
	 * @author Nataly
	 * @since 19-08-2015
	 */
	void getAvailableUserSchedule(AvailableUserScheduleSearchCriteria dto)
			throws UserScheduleNotAvailableException;

	/**
	 * Obtiene datos de waybill y stores por id_travel
	 * 
	 * @param id_travel
	 * @return Lista de objetos WaybillDTO
	 * @author Nataly
	 * @since 20-08-2015
	 */
	List<WayBillDTO> getWayBillByIdTravel(Long id_travel);
	
	
	/*
	 * Obtiene el ultimo viaje de un usuario para saber si se encuentra en TRANSITO o FINALIZADO.
	 * Esto es usado si regresa null o el viaje es FIN no se guarda la posici�n en 'lgk_user_position' si es TRA si es guardado
	 * @return Travel
	 * @author DMarin
	 * @since 30/01/2017
	 */
	Travel getLastTravelTRAorFIN(long id_user);
	
	/*
	 * Obtiene el listado de categorias de clientes con el porcentage de visitados y no visitados
	 * @return List<ProgressMetricsOperativesDTO>
	 * @author Dmarin
	 * @since 24/05/2017
	 */
	List<ProgressMetricsOperativesDTO> getProgressMetricsOperatives(ProgressMetricsOperativesCriteria criteria);
	
	/*
	 * Obtiene un listado de viajes apartir de una fecha asignada
	 * @param java.util.Date
	 * @return List<Travel>
	 * @author DMarin
	 * @since 09-06-2017
	 */
	List<Travel> getTravelsToVisited(TravelsToVisitedCriteria criteria);

	/*
	 * Finder Route Store
	 */

	/**
	 * Elimina RouteStore por medio de id_route
	 * 
	 * @param id_route
	 * @return numero de registros eliminados
	 * @author jucaraca
	 * @since 09/10/2015
	 */
	int delRouteStoreByIdRoute(Long id_route);

	/**
	 * Verifica que el codigo de ruta no este siendo usado por alguna Ruta
	 * 
	 * @param dto
	 *            con datos de busqueda
	 * @throws CodeRouteDuplicateException
	 * @author Sergio Eduardo Valenzuela Guerrero
	 * @since 14-09-2015
	 */
	void getAvaibleCodeRouteByCriteria(AviableCodeRouteSearchCriteria dto)
			throws CodeRouteDuplicateException;

	/**
	 * Obtiene waybill por criterio de busqueda
	 * 
	 * @param dto
	 *            con datos de busqueda
	 * @return lista de objetos de domino {WaybillDTO}
	 * @author JuanCarlosRamosCampos
	 * @since 7-11-2015
	 */
	List<WayBillDTO> getWayBillByCriteria(WayBillSearchCriteria dto);

	/**
	 * Elimina waybill por id_store
	 * 
	 * @param id_store
	 * @return numero de registros borrados
	 * @author JuanCarlosRamosCampos
	 * @since 20-11-2015
	 */
	int delWaybillByIdStore(Long id_store);

	/**
	 * Elimina RouteStore por medio de id_store
	 * 
	 * @param id_store
	 * @return numero de registros eliminados
	 * @author JuanCarlosRamosCampos
	 * @since 20-11-2015
	 */
	int delRouteStoreByIdStore(Long id_store);

	/**
	 * Obtiene el total de rutas por proveedor a partir de un criterio de
	 * busqueda
	 * 
	 * @param dto
	 *            Dto con criterio de busqueda
	 * @return Cantidad de rutas definidas para un proveedor
	 * @author Jorge
	 * @since 24/02/2016
	 */
	int getCountRouteSupplierByCriteria(CountRouteSupplierCriteria dto);

	/**
	 * Obtiene la cantidad total de viajes asociados a un proveedor, total de
	 * waybill, waybill visitados y waybill no visitados
	 * 
	 * @param dto
	 *            DTO con criterio de busqueda
	 * @return Lista de parametros
	 * @author David
	 * @since 02/03/2016
	 */
	MetricsCounterDTO getMetricsCounter(MetricsCounterCriteria dto);

	List<InfoReportPromotion> getTravelWithPromotion(
			ParameterReportPromotion data);

	List<Route> getRouteByListRetail(RoutesByListRetails id_routes);

	List<Long> getIdsRouteByIdTravel(Long id_travel);

	List<Route> getRoutesByIdTravel(Long id_travel);
	
	/**
	 * Agrega objeto de dominio waybill mutliples "Agregar Multiples clientes a un viaje"
	 * 
	 * @param MultipleUsersDTO
	 * @author David Rosales Muñoz
	 * @since 19-08-2016
	 */
	int addWayBill(MultipleUsersDTO dto);
	
	/*
	 * UserPosition
	 */
	
	/*
	 * Obtiene listado de posiciones del usuario por el id del usuario
	 * @param long
	 * @return List<UserPosition>
	 * @since 
	 */
	List<UserPosition> getUserPositionByIdUser(Long id_user, Date date);

	/*
	 * Obtiene listado de posiciones del usuario por el id del usuario y fecha
	 * @param long
	 * @return List<UserPosition>
	 * @since 
	 */
	List<UserPosition> getUserPositionByIdUserAndDate(Long id_user, Date date);

	/*
	 * Obtiene listado de posiciones del usuario por el id del viaje
	 * @param long
	 * @return List<UserPosition>
	 * @since 15-01-2016
	 * @author DMarin
	 */
	List<UserPosition> getUserPositionByIdTravel(Long id_travel);

	/*
	 * Obtiene listado de posiciones del usuario por el id del viaje y fecha
	 * @param long
	 * @return List<UserPosition>
	 * @since 15-01-2016
	 * @author DMarin
	 */
	List<UserPosition> getUserPositionByIdTravelAndDate(Long id_travel, Date date);
	
	/*
	 * Inserta un userposition
	 * @param UserPosition
	 * @return long
	 * @since 15-01-2016
	 * @author DMarin
	 */
	Long addUserPosition(UserPosition userPosition);
	
	/*
	 * Se obtiene listado de proveedores que tienen clientes sin visitar en una fecha expecifica
	 * @param Date
	 * @return List<Long>
	 * @author DMarin
	 * @since 08-06-2017
	 */
	List<Long> getSuppliersWithStoresNotVisited(Date date);
	
	/*
	 * Obtiene el listado de clientes sin visitar de un proveedor en una fecha especifica
	 * @param StoresNotVisitedByDateAndSupplierCriteria
	 * @return List<StoresNotVisitedByDateAndSupplierDTO>
	 * @author DMarin
	 * @since 08-05-2017
	 */
	List<StoresNotVisitedByDateAndSupplierDTO> getStoresNotVisitedByDateAndSupplier(StoresNotVisitedByDateAndSupplierCriteria criteria);
	
	/*
	 * Agrega viajes provenientes de otro viaje
	 * @param ReasignWaybillFromOldTravelDTO
	 * @return int
	 * @author DMarin
	 * @since 12-05-2017
	 */
	
	int reasignWaybillFromOldTravel(ReasignWaybillFromOldTravelDTO dto);
	
}
