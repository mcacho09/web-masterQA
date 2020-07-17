package com.retailsbs.logistikapp.supplier.service;

import java.util.List;

import com.retailsbs.logistikapp.supplier.domain.Plan;
import com.retailsbs.logistikapp.supplier.domain.Supplier;
import com.retailsbs.logistikapp.supplier.dto.AddSupplierDTO;
import com.retailsbs.logistikapp.supplier.dto.AddPlanDTO;
import com.retailsbs.logistikapp.supplier.dto.SupplierExtDTO;
import com.retailsbs.logistikapp.supplier.dto.UpdPlanDTO;
import com.retailsbs.logistikapp.supplier.dto.AvaibleSupplierCodeSearchCriteria;
import com.retailsbs.logistikapp.supplier.dto.UpdSupplierDTO;
import com.retailsbs.logistikapp.supplier.exception.PlanNotFoundException;
import com.retailsbs.logistikapp.supplier.exception.SupplierCodeDuplicateException;
import com.retailsbs.logistikapp.supplier.exception.SupplierNotFoundException;

/**
 * Clase service supplier
 * @author jorge
 * @since 05-12-2014
 */
public interface SupplierService {

	/*
	 * Supplier
	 */
	
	/**
	 * Agrega un nuevo supplier 
	 * @param dto con datos de supplier
	 * @return id de registro agregado
	 * @author Juan Carlos Ramos Campos
	 * @since 28-11-2014
	 */
	Long addSupplier(AddSupplierDTO dto);
	/**
	 * Actualiza datos de supplier
	 * @param dto con nuevos datos de supplier
	 * @return numero de registros actualizados
	 * @author Juan Carlos Ramos Campos
	 * @since 28-11-2014
	 * @throws SupplierNotFoundException 
	 */
	int updSupplier(UpdSupplierDTO dto) throws SupplierNotFoundException;
	/**
	 * Obtiene datos de supplier por medio del id
	 * @param id_supplier is suppler
	 * @return objeto de dominio {supplier}
	 * @author Juan Carlos Ramos Campos
	 * @throws SupplierNotFoundException 
	 * @since 28-11-2014
	 */
	Supplier getSupplierById(Long id_supplier) throws SupplierNotFoundException;
	/**
	 * obtienen todos los supplier que hay
	 * @return lista de objetos de dominio {Supplier}
	 * @author Juan Carlos Ramos Campos
	 * @since 28-11-2014
	 */
	List<Supplier> getAllSupplier();
	/**
	 * verifica que el codigo de  supplier no este siendo usado por algun registro activo de supplier
	 * @param dto con datos de busqueda
	 * @throws SupplierCodeDuplicateException
	 * @author Juan Carlos Ramos Campos
	 * @since 17-12-2014
	 */
	void getAvaibleSupplierCodeSearchCriteria(AvaibleSupplierCodeSearchCriteria dto) throws SupplierCodeDuplicateException;
	/**
	 * eliminar supplier por id_supplier
	 * @param id_supplier
	 * @return numero de supplier eliminados
	 * @author JuanCarlosRamosCampos
	 * @throws SupplierNotFoundException 
	 * @since 24-11-2015
	 */
	int delSupplierById(Long id_supplier) throws SupplierNotFoundException;

	/*
	 * Obtiene el total de supplier por id_plan
	 * @param Long
	 * @return int
	 * @author DMarin
	 * @since 31-05-2017
	 */
	int getTotalSupplier(Long id_plan);
	
	/**
	 * obtiene plan por id
	 * @param id_plan
	 * @return Objeto de dominio {plan}
	 * @author Axel Monroy
	 * @since 03-07-2016
	 */
	Plan getPlanById(Long id_plan) throws PlanNotFoundException;
	
	/**
	 * Agrega un nuevo plan 
	 * @param dto con datos del plan
	 * @return id de registro agregado
	 * @author Axel Monroy
	 * @since 03-07-2016
	 */
	Long addPlan(AddPlanDTO dto);
	
	/**
	 * Actualiza los datos de un plan
	 * @param dto con datos del plan
	 * @return numero de planes que se modificaron
	 * @author Axel Monroy
	 * @since 03-07-2016
	 */
	int updPlan(UpdPlanDTO dto);
	
	/**
	 * elimina plan por id_plan
	 * @param id_plan
	 * @return numero de planes que se eliminaron
	 * @author Axel Monroy
	 * @since 03-07-2016
	 */
	int delPlanByIdPlan(Long id_plan);
	
	/*
	 * Verifica si un suppliere ya se encuentra registrado
	 * @param String
	 * @return boolean
	 * @author DMarin
	 * @since 06/01/2016
	 */
	boolean existSupplier(String name);
	
	/*
	 * Obtiene el listado de todos los planes que existen
	 * @return List<Plan>
	 * @author DMarin
	 * @since 26-05-2017
	 */
	List<Plan> getAllPlans();
	
	/*
	 * Cambiar de plan a un supplier
	 * @return int
	 * @param Supplier
	 * @author DMarin
	 * @since 31-05-2017
	 */
	int updSupplierSelective(Supplier domain);
	
	/*
	 * Agrega un mes al plan de un supplier
	 * @return int
	 * @param Long
	 * author DMarin
	 * @since 31-05-2017
	 */
	int updSupplierPlan(Long id_supplier);
	
	/*
	 * Obtiene el listado de proveedores con el plan al que pertenecen
	 * @return List<SupplierExtDTO>
	 * @author DMarin
	 * @since 01-06-2017
	 */
	List<SupplierExtDTO> getSupplierExt();
	
	/*
	 * Cambia el plan de un proveedor
	 * @param Long id_supplier, Long id_plan
	 * @return int
	 * @author DMarin
	 * @since 1-06-2017
	 */
	int changeSupplierPlan(Long id_supplier, Long id_plan);
	
}
