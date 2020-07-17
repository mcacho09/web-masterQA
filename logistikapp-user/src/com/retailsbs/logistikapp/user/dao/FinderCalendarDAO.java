package com.retailsbs.logistikapp.user.dao;

import java.util.List;

import com.retailsbs.logistikapp.user.dto.CalendarDTO;
import com.retailsbs.logistikapp.user.dto.EventSearchCriteria;

/**
 * interface de DAO calendar
 * @author Sergio Valenzuela
 * @since 10-07-2015
 */
public interface FinderCalendarDAO {

	/**
	 * @param id_user, id_supplier
	 * @return Lista de objetos {CalendarDTO}
	 * @author Sergio Valenzuela
	 * @since 10-07-2015
	 */
	List<CalendarDTO> getEventByCriteria(EventSearchCriteria dto);
	/**
	 * borrar calendar por medio de id_supplier
	 * @param id_supplier
	 * @return numero de registros eliminados
	 * @author JuanCarlosRamosCampos
	 * @since 23-11-2015
	 */
	int delCalendarByIdSupplier(Long id_supplier);
	/**
	 * borrar calendar por medio de id_user
	 * @param id_user
	 * @return numero de registros eliminados
	 * @author JuanCarlosRamosCampos
	 * @since 24-11-2015
	 */
	int delCalendarByIdUser(Long id_user);
	
}
