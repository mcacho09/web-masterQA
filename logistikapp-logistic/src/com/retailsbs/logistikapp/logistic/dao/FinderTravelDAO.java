package com.retailsbs.logistikapp.logistic.dao;

import java.util.List;

import com.retailsbs.logistikapp.logistic.domain.Travel;
import com.retailsbs.logistikapp.logistic.dto.InfoReportPromotion;
import com.retailsbs.logistikapp.logistic.dto.MetricsCounterCriteria;
import com.retailsbs.logistikapp.logistic.dto.MetricsCounterDTO;
import com.retailsbs.logistikapp.logistic.dto.ParameterReportPromotion;
import com.retailsbs.logistikapp.logistic.dto.ProgressMetricsOperativesCriteria;
import com.retailsbs.logistikapp.logistic.dto.ProgressMetricsOperativesDTO;
import com.retailsbs.logistikapp.logistic.dto.TravelAndStatusStr;
import com.retailsbs.logistikapp.logistic.dto.TravelByIdStoreDTO;
import com.retailsbs.logistikapp.logistic.dto.TravelByIdStoreSearch;
import com.retailsbs.logistikapp.logistic.dto.TravelDTO;
import com.retailsbs.logistikapp.logistic.dto.TravelSearchCriteria;
import com.retailsbs.logistikapp.logistic.dto.TravelsToVisitedCriteria;
import com.retailsbs.logistikapp.logistic.dto.TrvStatusStrSearchCriteria;

public interface FinderTravelDAO {
	
	/**
	 * Obtiene lista de objetos TravelDTO por id_supplier
	 * @param dto con id_supplier
	 * @return Lista de objetos TravelDTO
	 * @author Nataly
	 * @since 18-08-2015
	 */
	List<TravelDTO> getTravelByCriteria(TravelSearchCriteria dto);

	/**
	 * Obtiene los viajes por medio de id store de cierta fecha en adelante
	 * @param dto con datos de busqued
	 * @return lista de objetos de dominio {TravelByIdStoreDTO}
	 * @author jucaraca
	 * @since 22-10-2015
	 */
	List<TravelByIdStoreDTO> getTravelByIdStore(TravelByIdStoreSearch dto);

	/**
	 * Obtiene una lista maxima de los tres ultimos viajes de un store y obtiene
	 * el checkin en cada uno de los viajes
	 * @param dto con datos de busqueda
	 * @return lista maxima de 3 objetos de dominio {TravelAndStatusStr}
	 * @author JuanCarlosRamosCampos
	 * @since 18-11-2015
	 */
	List<TravelAndStatusStr> getTravelAndStatusStrByIdStore(TrvStatusStrSearchCriteria dto);

	/**
	 * elimina viajes por medio de id_route
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
	 * Obtiene la cantidad total de viajes asociados a un proveedor, total de waybill, waybill visitados y waybill no visitados
	 * @param dto DTO con criterio de busqueda
	 * @return Lista de parametros
	 * @author David
	 * @since 02/03/2016
	 */
	MetricsCounterDTO getMetricsCounter(MetricsCounterCriteria dto);
	
	List<InfoReportPromotion> getTravelWithPromotion(ParameterReportPromotion data);
	
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
	 * @author DMarin
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
	
}
