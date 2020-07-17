package com.retailsbs.logistikapp.user.dao;

import java.util.List;
import com.retailsbs.logistikapp.user.domain.MessageGroup;


/**
 * interface de DAO finder messageGroup
 * @author Juan Carlos Ramos Campos
 * @since 11-11-2015
 */
public interface FinderMessageGroupDAO {
	/**
	 * borra messageGroup por id_group
	 * @param id_group
	 * @return numero de registros borrados
	 * @author Juan Carlos Ramos Campos
	 * @since 11-11-2015
	 */
	int delMessageGrpByIdGrp(Long id_group);
	/**
	 * Obtener messageGroup por medio de id_group
	 * @param id_group
	 * @return lista de objetos de dominio {messageGroup}
	 * @author JuanCarlosRamosCampos
	 * @since 11-11-2015
	 */
	List<MessageGroup> getMessageGroupByIdGroup(Long id_group);
	/**
	 * Elimina message group relacionados con id_message
	 * @param id_message
	 * @return numero de messageGroup eliminados
	 * @author JuanCarlosRamosCampos
	 * @since 24-11-2015
	 */
	int delMessageGroupByIdMessage(Long id_message);
}
