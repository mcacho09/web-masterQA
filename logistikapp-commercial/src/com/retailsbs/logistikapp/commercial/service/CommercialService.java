package com.retailsbs.logistikapp.commercial.service;

import java.util.List;

import com.retailsbs.logistikapp.commercial.domain.RetailcatGoal;
import com.retailsbs.logistikapp.commercial.dto.AddRetailcatGoal;
import com.retailsbs.logistikapp.commercial.dto.UpdRetailcatGoal;
import com.retailsbs.logistikapp.commercial.exception.RetailcatGoalNotFoundException;




/**
 * Clase interfaz servicio de commercial
 * @author juan carlos
 * @since 17-03-2015
 */
public interface CommercialService {

	/*
	 * RetailcatGoal
	 */
	
	/**
	 * Agrega un RetailcatGoal
	 * @param dto dto con datos de RetailcatGoal
	 * @return id de registro agregado
	 * @author juan carlos
	 * @since 17-03-2015
	 */
	Long addRetailcatGoal(AddRetailcatGoal dto);
	/**
	 * actualiza RetailcatGoal
	 * @param dto con datos de RetailcatGoal
	 * @return objeto de dominio {RetailcatGoal}
	 * @throws RetailcatGoalNotFoundException
	 * @author juan carlos
	 * @since 17-03-2015
	 */
	int updRetailcatGoal(UpdRetailcatGoal dto) throws RetailcatGoalNotFoundException;
	/**
	 * obtiene RetailcatGoal por id
	 * @param id_retailcat_goal
	 * @return obtiene datos de RetailcatGoal
	 * @throws RetailcatGoalNotFoundException
	 * @author juan carlos
	 * @since 17-03-2015
	 */
	RetailcatGoal getRetailcatGoalById(Long id_retailcat_goal) throws RetailcatGoalNotFoundException;
	/**
	 * obtiene todos los RetailcatGoal
	 * @return lista de objetos de dominio {RetailcatGoal}
	 * @author juan carlos
	 * @since 17-03-2015
	 */
	List<RetailcatGoal> getAllRetailcatGoal();

}
