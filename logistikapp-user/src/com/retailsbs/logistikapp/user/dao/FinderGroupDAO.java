package com.retailsbs.logistikapp.user.dao;


/**
 * Class interface para obtener grupos
 * @author JuanCarlosRamosCampos
 * @since 24-11-2015
 */
public interface FinderGroupDAO {

	/**
	 * Elimina group que tengan relacion con el id_user
	 * @param id_user
	 * @return numero de group eliminados
	 * @author JuanCarlosRamosCampos
	 * @since 24-11-2015
	 */
	int delGroupByIdUser(Long id_user);
	
}
