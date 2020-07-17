package com.retailsbs.logistikapp.retail.dao;

import java.util.List;

import com.retailsbs.logistikapp.retail.dto.CountryDTO;
import com.retailsbs.logistikapp.retail.dto.CountrySearchCriteria;

/**
 * interface de DAO finder city
 * @author Juan Carlos Ramos Campos
 * @since 25-11-2014
 */
public interface FinderCountryDAO {

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
	
}
