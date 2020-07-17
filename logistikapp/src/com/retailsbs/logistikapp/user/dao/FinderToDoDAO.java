package com.retailsbs.logistikapp.user.dao;

import java.util.List;

import com.retailsbs.logistikapp.user.domain.ToDo;
import com.retailsbs.logistikapp.user.dto.ToDoActiveSearchCriteria;
import com.retailsbs.logistikapp.user.dto.ToDoDTO;

/**
 * interface de DAO finder ToDo
 * @author Juan Carlos Ramos Campos
 * @since 09-02-2015
 */
public interface FinderToDoDAO {
	/**
	 * obtiene las tareas activas
	 * @param dto con datos de busqueda
	 * @return lista de objetos de dominio {ToDoDTO}
	 * @author juan carlos
	 * @since 09-02-2015
	 */
	List<ToDoDTO> getToDoActive(ToDoActiveSearchCriteria dto);
	
	/**
	 * obtiene numero de tareas activas
	 * @return numero de tareas activas
	 * @author juan carlos
	 * @since 09-02-2015
	 */
	Integer getToDoActiveTotal(ToDoActiveSearchCriteria dto);
	
	/**
	 * Obtiene un listado de todas las tareas activas para un usuario
	 * @param id_user Id usuario
	 * @return Lista de objetos de dominio {Todo}
	 * @author JORGE
	 * @since 13-02-2015
	 */
	List<ToDo> getTodoActiveByIdUser(Long id_user);
	/**
	 * Elimina tareas relacionadas al id_user 
	 * @param id_user
	 * @return numero de tareas borradas
	 * @author JuanCarlosRamosCampos
	 * @since 24-11-2015
	 */
	int delTodoByIdUser(Long id_user);
	
}
