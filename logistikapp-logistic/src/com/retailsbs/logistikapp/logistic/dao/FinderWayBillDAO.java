package com.retailsbs.logistikapp.logistic.dao;

import java.util.Date;
import java.util.List;

import com.retailsbs.logistikapp.logistic.dto.CustomersNotVisitedCriteria;
import com.retailsbs.logistikapp.logistic.dto.CustomersNotVisitedDTO;
import com.retailsbs.logistikapp.logistic.dto.StoresNotVisitedByDateAndSupplierCriteria;
import com.retailsbs.logistikapp.logistic.dto.StoresNotVisitedByDateAndSupplierDTO;
import com.retailsbs.logistikapp.logistic.dto.WayBillDTO;
import com.retailsbs.logistikapp.logistic.dto.WayBillSearchCriteria;

public interface FinderWayBillDAO {
	/**
	 * Obtiene datos de waybill y stores por id_travel
	 * @param id_travel
	 * @return Lista de objetos WaybillDTO
	 * @author Nataly
	 * @since 20-08-2015
	 */
	List<WayBillDTO> getWayBillByIdTravel(Long id_travel);
	/**
	 * Elimina registros de waybill por id_travel
	 * @param id_travel
	 * @return numero de registros afectados
	 * @author Nataly
	 * @since 26-08-2015
	 * 
	 */
	int delWayBillByIdTravel(Long id_travel);
	/**
	 * Obtiene waybill por criterio de busqueda
	 * @param dto con datos de busqueda
	 * @return lista de objetos de domino {WaybillDTO}
	 * @author JuanCarlosRamosCampos
	 * @since 7-11-2015
	 */
	List<WayBillDTO> getWayBillByCriteria(WayBillSearchCriteria dto);
	/**
	 * Elimina waybill por id_store
	 * @param id_store
	 * @return numero de registros borrados
	 * @author JuanCarlosRamosCampos
	 * @since 20-11-2015
	 */
	int delWaybillByIdStore(Long id_store);
	
	/*
	 * Se obtiene listado de clientes sin visitar en un rango de fechas
	 * @param CustomersNotVisitedCriteria
	 * @return List<CustomersNotVisitedDTO>
	 * @author DMarin
	 * @since 16-05-2017
	 */
	List<CustomersNotVisitedDTO> getCustomersNotVisited(CustomersNotVisitedCriteria criteria);
	
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
}
