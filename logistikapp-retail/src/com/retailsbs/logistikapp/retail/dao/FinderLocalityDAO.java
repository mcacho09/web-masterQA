package com.retailsbs.logistikapp.retail.dao;

import java.util.List;

import com.retailsbs.logistikapp.retail.dto.GetLocalitySearchCriteria;
import com.retailsbs.logistikapp.retail.dto.LocalityDTO;

/**
 * interface de DAO finder city
 * @author Juan Carlos Ramos Campos
 * @since 25-11-2014
 */
public interface FinderLocalityDAO {

	/*
	 * Locality
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
	
}
