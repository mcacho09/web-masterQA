package com.retailsbs.logistikapp.retail.dao;

import java.util.List;

import com.retailsbs.logistikapp.retail.dto.CityDTO;
import com.retailsbs.logistikapp.retail.dto.GetCitySearchCriteria;

/**
 * interface de DAO finder city
 * @author Juan Carlos Ramos Campos
 * @since 25-11-2014
 */
public interface FinderCityDAO {

	/*
	 * City
	 */
	
	/**
	 * Obtiene lista de city's por criterio.
	 * @param dto dto con datos de busqueda
	 * @return lista de objetos de dominio {CityDTO}
	 * @author Juan Carlos Ramos Campos
	 * @since 25-11-2014
	 */
	List<CityDTO> getCityByCriteria(GetCitySearchCriteria dto);
	/**
	 * Obtiene numero de registros de getCityByCriteria
	 * @param dto con datos de busqueda
	 * @return numero de registros de busqueda
	 * @author Juan Carlos Ramos Campos
	 * @since 27-11-2014
	 */
	Integer getCityTotalByCriteria(GetCitySearchCriteria dto);
	
}
