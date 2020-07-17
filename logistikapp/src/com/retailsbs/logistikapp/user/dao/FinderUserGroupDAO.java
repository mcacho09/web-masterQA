package com.retailsbs.logistikapp.user.dao;

import java.util.List;

import com.retailsbs.logistikapp.user.dto.UserGroupDTO;
/**
 * interface de DAO finder UserGroup
 * @author Sergio Valenzuela
 * @since 12-08-2015
 */
public interface FinderUserGroupDAO {


	/**
	 * A partir del id group se obtiene una lista de todos los usuarios que pertenecen al mismo
	 * @param id_group Id del grupo del que se desean obtener los usuarios
	 * @return Lista de objetos {UserGroupDTO}
	 * @author Juan Carlos Ramos Campos
	 * @since 08-01-2015
	 * @modified 26-01-2015 - JORGE - Se optimiza SQL
	 */
	List<UserGroupDTO> getUserByIdGroup(Long id_user_message);
	/**
	 * Se borran los registros que tengan el mismo id_group
	 * @param id_group
	 * @return numero de registros borrados
	 * @author JuanCarlosRamosCampos
	 * @since 11-11-2015
	 */
	int delUserGrpByIdGrp(Long id_group);
	/**
	 * Se borran los registros que tengan el mismo id_user
	 * @param id_user
	 * @return numero de registros borrados
	 * @author JuanCarlosRamosCampos
	 * @since 24-11-2015
	 */
	int delUserGrpByIdUser(Long id_user);
	
}
