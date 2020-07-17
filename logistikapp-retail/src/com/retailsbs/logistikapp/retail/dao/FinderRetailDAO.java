package com.retailsbs.logistikapp.retail.dao;

import java.util.List;

import com.retailsbs.logistikapp.retail.domain.Retail;
import com.retailsbs.logistikapp.retail.dto.ChangeRetailCriteria;
import com.retailsbs.logistikapp.retail.dto.QtyStrCheckByRetai;
import com.retailsbs.logistikapp.retail.dto.QtyStrCheckByRetailDTO;
import com.retailsbs.logistikapp.retail.dto.RetailByIdsSearchCriteria;
import com.retailsbs.logistikapp.retail.dto.RetailDTO;
import com.retailsbs.logistikapp.retail.dto.RetailSearchCriteria;

/**
 * interface de DAO finder retail
 * @author jucaraca
 * @modified 01-13-2015 - JORGE - Se optimiza SQL
 */
public interface FinderRetailDAO {

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
	 * Obtiene un objeto con informacion extendida de un retail
	 * @param id_retail Id retail
	 * @return Objeto {RetailDTO} con informacion extendida del comercio
	 * @author juan carlos
	 * @since 26-03-2015
	 */
	RetailDTO getRetailExtById(Long id_retail);
	/**
	 * 
	 * @param ids_retail
	 * @return lista de objetos retail
	 * @author Nataly
	 * @since 12-08-2015
	 */
	List<Retail> getRetailByIds(RetailByIdsSearchCriteria dto);
	/**
	 * Obtiene lista de los retail y las tiendas totates que tiene en viajes y las tiendas de los  viajes que tienen estado checkin
	 * @param dto con datos de busqueda
	 * @return lista de objetos de dominio {QtyStrCheckByRetailDTO}
	 * @author JuanCarlosRamosCampos
	 * @since 17-11-2015
	 */
	List<QtyStrCheckByRetailDTO> getRetailCountStrCheck(QtyStrCheckByRetai dto);
	/**
	 * Elimina retail por id_supplier
	 * @param id_supplier
	 * @return numero de retails eliminados
	 * @author JuanCarlosRamosCampos
	 * @since 23-11-2015
	 */
	int delRetailByIdSupplier(Long id_supplier);
	/**
	 * Obtiene la cantidad de clientes para verificado de planes
	 * @param dto Dto con criterio de busqueda
	 * @return Cantidad de clientes int
	 * @author David Rosales Muñoz
	 * @since 04-05-2016
	 */
	Integer getCliQtyByCriteria(RetailSearchCriteria dto);
	
	/*
	 * Cambia de retail los clientes pertenecientes
	 * @param ChangeStoresRetailCriteria
	 * @return int
	 * @author DMarin
	 * @since 30-05-2017
	 */
	int changeStoresRetail(ChangeRetailCriteria criteria);
	
}
