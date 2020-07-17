package com.retailsbs.logistikapp.user.dao;

import java.util.List;

import com.retailsbs.logistikapp.user.domain.Notification;
import com.retailsbs.logistikapp.user.dto.NotificationDTO;
import com.retailsbs.logistikapp.user.dto.NotificationSearchCriteria;
import com.retailsbs.logistikapp.user.dto.NotificationTodaySearchCriteria;

/**
 * Interface que define los metodos para las notificaciones
 * @author Juan Carlos Ramos Campos
 * @since 09-02-2015
 * @modified 16-02-2015 - JORGE - Se agrega un metodo para obtener notificaciones enviada a todos y un perfil de usuario en particular
 * @modified 14-03-2015 - JORGE - Se reestructura dao
 */
public interface FinderNotificationDAO {
	
	/**
	 * Obtiene el listado de notificaciones a partir de un criterio de busqueda
	 * @param dto Dto con par�metros de b�squeda
	 * @return Lista de objetos {NotificationDTO}
	 * @author jorge  
	 * @since 14-03-2015
	 * @modified 22-06-2015 - Nataly - se agrega el parametro de busqueda id_supplier
	 */
	List<NotificationDTO> getNotificationByCriteria(NotificationSearchCriteria dto);
	
	/**
	 * Obtiene una lista de objetos de notificaciones s�lo del d�a actual
	 * @param dto Dto con par�metros de b�squeda
	 * @return Lista de objetos de dominio {Notification}
	 * @author jorge
	 * @since 14-03-2015
	 */
	List<Notification> getNotificationTodayByCriteria(NotificationTodaySearchCriteria dto);
	/**
	 * Elimina notificaciones por id_supplier
	 * @param id_supplier
	 * @return numero de notificaciones eliminadas
	 * @author JuanCarlosRamosCampos
	 * @since 23-11-2015
	 */
	int delNotificationByIdSupplier(Long id_supplier);
	/**
	 * Elimina notificaciones por id_user
	 * @param id_user
	 * @return numero de notificaciones eliminadas
	 * @author JuanCarlosRamosCampos
	 * @since 24-11-2015
	 */
	int delNotificationByIdUser(Long id_user);
}
