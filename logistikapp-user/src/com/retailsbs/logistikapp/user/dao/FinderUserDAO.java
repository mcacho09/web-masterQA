package com.retailsbs.logistikapp.user.dao;

import java.util.List;

import com.retailsbs.logistikapp.user.dto.AdminUserDTO;
import com.retailsbs.logistikapp.user.dto.AdminUserSearchCriteria;
import com.retailsbs.logistikapp.user.dto.DRICriteria;
import com.retailsbs.logistikapp.user.dto.MetricsAdm;
import com.retailsbs.logistikapp.user.dto.UserDTO;
import com.retailsbs.logistikapp.user.dto.UserSearchCriteria;
import com.retailsbs.logistikapp.user.dto.NotificationTodaySearchCriteria;
import com.retailsbs.logistikapp.user.domain.Quantities;
import com.retailsbs.logistikapp.user.domain.User;

/**
 * @author danie
 *
 */
public interface FinderUserDAO {

	/**
	 * Obtiene lista de usuarios por criterio de busqueda
	 * @param dto Dto con datos de criterio
	 * @return lista de objetos de dominio UserDTO
	 * @author jucaraca
	 * @since 23-06-2015
	 */
	List<UserDTO> getUserByCriteria(UserSearchCriteria dto);

	/*
	 * Administration
	 */
	
	/**
	 * Obtiene una lista de usuarios para la pantalla de administración
	 * @param dto Criterio de busqueda
	 * @return Lista de objetos {AdminUserDTO}
	 * @author Jorge
	 * @since 26-06-2015
	 */
	List<AdminUserDTO> getAdminUserListByCriteria(AdminUserSearchCriteria dto);
	
	/**
	 * Obtiene las cantidades de las notificaciones
	 * @param dto Criterio de busqueda
	 * @return Lista de objetos {QuantitiesDTO}
	 * @author David
	 * @since 01-04-2016
	 */
	Quantities getQtyAlertAndMessagesByIdUser(NotificationTodaySearchCriteria dto);
	
	
	/**
	 * Obtiene un listado de conductores disponibles en la fecha indicada
	 * @param criteria
	 * @return List<UserDTO>
	 * @since 10/02/2017
	 * @author DMarin
	 */
	List<UserDTO> getDisponibleDrivers(DRICriteria criteria);
	
	/*
	 * Obtiene las metricas para el administrador
	 * @return MetricsAdm
	 * @since 29-05-2017
	 * @author DMarin
	 */
	MetricsAdm getMetricsAdm();
	
	/*
	 * Obtiene el listado de ids por proveedor que no sea Vendedor o Soporte
	 * @param Long
	 * @return List<Long>
	 * @author DMarin
	 * @since 08-06-2017
	 */
	List<Integer> getSupIdsBySuppliers(Long id_supplier);
	
	List<User> getUserWithOutStockByIdSupplier(Long id_supplier);
	
}
