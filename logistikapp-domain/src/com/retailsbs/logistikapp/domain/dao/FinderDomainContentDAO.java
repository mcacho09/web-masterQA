package com.retailsbs.logistikapp.domain.dao;

import java.util.List;

import com.retailsbs.logistikapp.domain.dto.DomainContentDTO;
import com.retailsbs.logistikapp.domain.dto.DomainContentSearchCriteria;

/**
 * interface de DAO finder city
 * @author Juan Carlos Ramos Campos
 * @since 25-11-2014
 */
public interface FinderDomainContentDAO {

	/*
	 * FINDER DOMAIN CONTENT
	 */
	
	/**
	 * Obtiene lista de contenido de dominio por criterio.
	 * @param dto dto con datos de busqueda
	 * @return lista de objetos de dominio {DomainContentDTO}
	 * @author Juan Carlos Ramos Campos
	 * @since 17-12-2014
	 */
	List<DomainContentDTO> getDomainContentByCriteria(DomainContentSearchCriteria dto);
	/**
	 * Obtiene total de registros de domaincontent que trae la busqueda realizada
	 * @param dto con datos de busqueda
	 * @return total de registros de busqueda
	 * @author Juan Carlos Ramos Campos
	 * @since 17-12-2014
	 */
	Integer getDomainContentTotalByCriteria(DomainContentSearchCriteria dto);
	
}
