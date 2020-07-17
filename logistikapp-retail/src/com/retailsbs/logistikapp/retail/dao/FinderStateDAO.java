package com.retailsbs.logistikapp.retail.dao;

import java.util.List;

import com.retailsbs.logistikapp.retail.dto.GetStateSearchCriteria;
import com.retailsbs.logistikapp.retail.dto.StateDTO;
import com.retailsbs.logistikapp.retail.dto.StateQtyCityDTO;

/**
 * interface de DAO finder state
 * @author jucaraca
 * @since 25-11-2014
 * @modified 16-01-2015 - JORGE - Se agrega metodo getAllStateQtyCityByIdCountry
 */
public interface FinderStateDAO {

	/**
	 * Obtiene lista de state's por criteria.
	 * @param dto dto con datos de busqueda
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
	
}
