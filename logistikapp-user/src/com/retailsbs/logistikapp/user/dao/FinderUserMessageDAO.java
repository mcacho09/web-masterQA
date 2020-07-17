package com.retailsbs.logistikapp.user.dao;

import java.util.List;

import com.retailsbs.logistikapp.user.domain.User;
import com.retailsbs.logistikapp.user.domain.UserMessage;
import com.retailsbs.logistikapp.user.dto.RelUserUsertoDTO;
import com.retailsbs.logistikapp.user.dto.UserMessageDTO;
import com.retailsbs.logistikapp.user.dto.UserMessageGrpDTO;
import com.retailsbs.logistikapp.user.dto.UserSearchCriteria;
import com.retailsbs.logistikapp.user.dto.UserUsertoDTO;

/**
 * interface de DAO finder user message
 * @author Juan Carlos Ramos Campos
 * @since 14-01-2015
 * @modified 26-01-2015 - JORGE - Se agrega metodo para obtener todas las relaciones y cantidad de mensajes entre usuarios
 */
public interface FinderUserMessageDAO {

	/**
	 * obtiene registros que tengan la relacion id_user y id_user_to especificos
	 * @param dto con id_user y id_user_to que se quiere obtener
	 * @return lista de objeto de dominio {UserUsertoDTO}
	 * @author Juan Carlos Ramos Campos
	 * @since 14-01-2015
	 */
	List<UserUsertoDTO> getRelUserUserto(RelUserUsertoDTO dto);
	
	/**
	 * A partir de un usuario se obtiene una lista de todas las relaciones con otros usuarios y la cantidad de mensajes no leidos
	 * @param id_user Id usuario
	 * @return Lista de objetos {UserMessageDTO}
	 * @author JORGE
	 * @since 26-01-2015
	 */
	List<UserMessageDTO> getUserMessageByIdUser(Long id_user);
	
	/**
	 * A partir de un usuario y su id_supplier se obtiene todos los usuarios con el mismo id_supplier 
	 * @param dto con id_user, id_supplier  
	 * @return lista de objeto de dominio User
	 * @author Nataly
	 * @since 22-06-2015
	 */
	List<User> getAvailableUserByCriteria(UserSearchCriteria dto);
	/**
	 * A partir de un usuario se obtiene una lista de todas las relaciones con otros usuarios con los que tiene conversaci�n y el 
	 * ultimo mensaje de la conversacion.
	 * @param id_user
	 * @return lista de objetos {UserMessageDTO}
	 * @author Nataly
	 * @since 24-07-2015
	 */
	List<UserMessageDTO> getUserLastMessageByIdUser(Long id_user);
	/**
	 * Obtiene a partir de un usuario una lista de las conversaciones grupales en la que participa el usuario
	 * @param id_user id user
	 * @return Lista de objetos de dominio {UserMessageGrpDTO}
	 * @authorJuan Carlos
	 * @since 10-08-2015
	 */
	List<UserMessageGrpDTO> getUserLastMsgGroByIdUser(Long id_user);
	/**
	 * Obtiene todos los UserMessage que id_user o id_user_to es = id_user
	 * @param id_user
	 * @return lista de objetos de dominio {UserMessage}
	 * @author JuanCarlosRamosCampos
	 * @since 24-11-2015
	 */
	List<UserMessage> getUserMessageByIdUserId_UserTo(Long id_user);
	/**
	 * Elimina user message si id_user esta en id_user o id_user_to
	 * @param id_user
	 * @return numero de registros borrados
	 * @author JuanCarlosRamosCampos
	 * @since 24-11-2015
	 */
	int delUserMessageByIdUserIdUserTo(Long id_user);
	
}
