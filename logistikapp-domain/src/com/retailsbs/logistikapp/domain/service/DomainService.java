package com.retailsbs.logistikapp.domain.service;

import java.util.List;

import com.retailsbs.logistikapp.domain.domain.Domain;
import com.retailsbs.logistikapp.domain.domain.DomainContent;
import com.retailsbs.logistikapp.domain.dto.AddDomainContentDTO;
import com.retailsbs.logistikapp.domain.dto.AddDomainDTO;
import com.retailsbs.logistikapp.domain.dto.AvaibleCodeSearchCriteria;
import com.retailsbs.logistikapp.domain.dto.DomainAvaibleSearchCriteria;
import com.retailsbs.logistikapp.domain.dto.DomainContentDTO;
import com.retailsbs.logistikapp.domain.dto.DomainContentSearchCriteria;
import com.retailsbs.logistikapp.domain.dto.UpdDomainContentDTO;
import com.retailsbs.logistikapp.domain.dto.UpdDomainDTO;
import com.retailsbs.logistikapp.domain.exception.DomainContentCodeDuplicateException;
import com.retailsbs.logistikapp.domain.exception.DomainContentNotFoundException;
import com.retailsbs.logistikapp.domain.exception.DomainDuplicadoException;
import com.retailsbs.logistikapp.domain.exception.DomainNotFoundException;

/**
 * Clase interfaz serviciko domain
 * @author Juan Carlos Ramos Campos
 * @since 24-11-2014
 */
public interface DomainService {

	/*
	 * Domain
	 */
	/**
	 * Agrega un nuevo domain
	 * @param dto con datos de domain que se va a crear
	 * @return id de registro creado
	 * @author Juan Carlos Ramos Campos
	 * @since 24-11-2014
	 */
	Long addDomain(AddDomainDTO dto);
	/**
	 * Actualiza datos de domain
	 * @param dto con nuevos datos de domain
	 * @return numero de registros modificados
	 * @author Juan Carlos Ramos Campos
	 * @throws DomainNotFoundException 
	 * @since 24-11-2014
	 */
	int updDomain(UpdDomainDTO dto) throws DomainNotFoundException;
	/**
	 * Obtiene un domain por medio de id_domain
	 * @param id_domain id domain
	 * @return objeto de dominio {Domain}
	 * @author Juan Carlos Ramos Campos
	 * @throws DomainNotFoundException 
	 * @since 24-11-2014
	 */
	Domain getDomainById(Long id_domain) throws DomainNotFoundException;
	/**
	 * Obtiene lista de todos los domain que hay
	 * @return lista de objetos de dominio {Domain}
	 * @author Juan Carlos Ramos Campos
	 * @since 24-11-2014
	 */
	List<Domain> getAllDomain();
	/**
	 * Verifica que el dominio que se quiere agregar no exista el mismo codigo
	 * @param dto
	 * @throws DomainDuplicadoException 
	 * @author Juan Carlos Ramos Campos
	 * @since 08-12-2014
	 */
	void getDomainAvaibleByCriteria(DomainAvaibleSearchCriteria dto) throws DomainDuplicadoException;
	
	/*
	 * DomainContent
	 */
	
	/**
	 * Agrega un nuevo DomainContent
	 * @param dto con datos de DomainContent
	 * @return id de registro agregado
	 * @author Juan Carlos Ramos Campos
	 * @since 24-11-2014
	 */
	Long addDomainContent(AddDomainContentDTO dto);
	/**
	 * Actualiza datos de domainContent
	 * @param dto con datos nuevos de domainContent
	 * @return numero de registros actualizados
	 * @author Juan Carlos Ramos Campos
	 * @throws DomainContentNotFoundException 
	 * @since 24-11-2014
	 */
	int updDomainContent(UpdDomainContentDTO dto) throws DomainContentNotFoundException;
	/**
	 * Obtiene un domainContent por medio del id
	 * @param id_domain_content id domain content
	 * @return Objeto de dominio {DomainContent}
	 * @author Juan Carlos Ramos Campos
	 * @throws DomainContentNotFoundException 
	 * @since 24-11-2014
	 */
	DomainContent getDomainContentById(Long id_domain_content) throws DomainContentNotFoundException;
	/**
	 * Obtiene todos los domain content que hay
	 * @return lista de objetos de dominio {domainContent}
	 * @author Juan Carlos Ramos Campos
	 * @since 24-11-2014
	 */
	List<DomainContent> getAllDomainContent();
	/**
	 * verifica que el codigo de dominio content no este siendo usado por algun DomainContent activo 
	 * @param dto con datos de busqueda
	 * @throws DomainContentCodeDuplicateException
	 * @author Juan Carlos Ramos Campos
	 * @since 18-12-2014
	 */
	void getAvaibleCodeByCriteria(AvaibleCodeSearchCriteria dto) throws DomainContentCodeDuplicateException;
	
	/*
	 * FINDER DOMAIN CONTENT
	 */
	
	/**
	 * Obtiene lista de contenido de dominios 
	 * @param dto con datos de busqueda
	 * @return lista de objetos de dominio {domainContent}
	 * @author Juan Carlos Ramos Campos
	 * @since 18-12-2014
	 */
	List<DomainContentDTO> getDomainContentByCriteria(DomainContentSearchCriteria dto);
	/**
	 * obtiene numero total de registros obtenidos con criterio de busqueda domainContentSearchCriteria
	 * @param dto con datos de busqueda
	 * @return numero total de registro de busqueda
	 * @author Juan Carlos Ramos Campos
	 * @since 18-12-2014
	 */
	int getDomainContentTotalByCriteria(DomainContentSearchCriteria dto);
}
