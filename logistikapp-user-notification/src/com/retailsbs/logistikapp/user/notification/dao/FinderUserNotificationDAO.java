package com.retailsbs.logistikapp.user.notification.dao;

import java.util.List;

import com.retailsbs.logistikapp.user.notification.domain.ListNotificationDTO;
import com.retailsbs.logistikapp.user.notification.domain.UsersDTO;


public interface FinderUserNotificationDAO {

	 /**
     * Actualizar campo Active
     * @author David Rosales
     * @param id_user
     */
    void updateByIdUser(Long id_user);

    /**
     * Listado Notificaciones total
     * @author David Rosales
     * @param id_user
     */
    List<ListNotificationDTO> selectAllByIdUser(Long id_user);
    
    /**
     * Listado Notificaciones Top 5
     * @author David Rosales
     * @param id_user
     */    
    List<ListNotificationDTO> selectTopByIdUser(Long id_user);
    
    /**
     * Insertado Lista Usuarios
     * @author David Rosales
     * @param dto
     */    
    Long insertNewNotification(UsersDTO dto);
    
    /**
     * Traer Usuarios pertenecientes a un Supplier sin contar peril SOP
     * @author David Rosales
     * @param id_user
     */    
    List<Integer> getUsersByIdSupplier(UsersDTO dto);
    
    /**
     * Traer Usuarios pertenecientes a un Supplier     
     * @param id_user
     */    
    List<Integer> getUsersAndUserByIdSupplier(UsersDTO dto);

    /**
     * Traer Usuarios pertenecientes a un Supplier sin contar peril SOP ni DRI
     * @author David Rosales
     * @param id_user
     */    
	List<Integer> getUsersByIdSupplierNoDRI(UsersDTO tmp);

	List<Integer> getUsersByIdSupplierJustSUP(UsersDTO tmp);
	
	/**
     * Listado Notificaciones para el webservice
     * @author DMarin
     * @param id_user
     */
    List<ListNotificationDTO> selectNotificacionsWSByIdUser(Long id_user);
    
}
