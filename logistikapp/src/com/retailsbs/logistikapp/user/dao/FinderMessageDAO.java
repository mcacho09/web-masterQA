package com.retailsbs.logistikapp.user.dao;

import java.util.List;

import com.retailsbs.logistikapp.user.dto.MessageDTO;
import com.retailsbs.logistikapp.user.dto.MessageGroupDTO;
import com.retailsbs.logistikapp.user.dto.MessageNoReadDTO;
import com.retailsbs.logistikapp.user.dto.NewMessageTotalDTO;
import com.retailsbs.logistikapp.user.dto.lastNewMessageDTO;
import com.retailsbs.logistikapp.user.dto.lastNewMessageSearchCriteria;

/**
 * interface de DAO finder message
 * @author Juan Carlos Ramos Campos
 * @since 08-01-2015
 * @modified 27-01-2015 - JORGE - Se actualiza metodo getMessageByIdUserMessage
 * @modified 11-02-2015 - JORGE - Se actualiza metodo getQtyMessageNoReadByIdUser()
 */
public interface FinderMessageDAO {

	/**
	 * A partir de una relacion entre usuarios se obtiene una lista de todos los mensajes
	 * @param id_user_message Id relacion entre usuarios
	 * @return Lista de objetos {MessageDTO}
	 * @author Juan Carlos Ramos Campos
	 * @since 08-01-2015
	 * @modified 26-01-2015 - JORGE - Se optimiza SQL
	 */
	List<MessageDTO> getMessageByIdUserMessage(Long id_user_message);
	
	/**
	 * borra mensajes por id_user_message
	 * @param id_user_message id user message
	 * @return numero de registros borrados
	 * @author Juan Carlos Ramos Campos
	 * @since 15-01-2015
	 */
	int deleteMessageByIdUserMessage(Long id_user_message);
	/**
	 * obtiene el numero todal de mensajes nuevos para el usuario
	 * @param dto con datos de busqueda
	 * @return numero total de mensajes nuevos
	 * @author Juan Carlos Ramos Campos
	 * @since 05-02-2015
	 */
	Integer newMessageTotal(NewMessageTotalDTO dto);
	/**
	 * obtiene los ultimos mensajes que se tienen para el usuario logueado
	 * @param dto con datos de busqueda
	 * @return ultimos mensajes nuevos que se tienen
	 * @author Juan Carlos Ramos Campos
	 * @since 06-02-2015
	 */
	List<lastNewMessageDTO> lastNewMessage(lastNewMessageSearchCriteria dto);

	/*
	 * Metodos requeridos para las notificaciones en el header
	 */
	/**
	 * Obtiene el total de mensajes no leidos para mostrarse en la barra de navegaci�n superior
	 * @param id_user Id usuario
	 * @return Cantidad total de mensajes no leidos
	 * @author JORGE
	 * @since 12-02-2015
	 */
	Integer getQtyMessageNoReadByIdUser(Long id_user);
	/**
	 * Obtiene una lista de mensajes no leidos para mostrarse en la barra de navegaci�n superior
	 * @param id_user Id usuario
	 * @return Lista de objetos {MessageDTO}
	 * @author JORGE
	 * @since 12-02-2015
	 */
	List<MessageNoReadDTO> getListMessageNoReadByIdUser(Long id_user);
	/**
	 * Establece como leidos todos los mensajes de una conversacion
	 * @param id_user_message
	 * @return
	 * @author Nataly
	 * @since 27-07-2015
	 */
	int updateReadByIdUserMessage(Long id_user_message);

	/**
	 * A partir de una relacion entre usuarios, mensajes, messageGroup y Group se obtiene
	 * una lista de todos los mensajes grupales quien los envia y a que grupo
	 * @param id_group Id de Group
	 * @return Lista de objetos {MessageGroupDTO}
	 * @author Sergio Valenzuela
	 * @since 07-08-2015
	 */
	List<MessageGroupDTO> getMessageByIdGroup(Long id_group);
	/**
	 * elimina message que esten relacionados con el id_user que s ele manda
	 * @param id_user
	 * @return numero de registros borrados
	 * @author JuanCarlosRamosCampos
	 * @since 24-11-2015
	 */
	int delMessageByIdUser(Long id_user);
}
