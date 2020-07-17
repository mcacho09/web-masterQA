package com.retailsbs.logistikapp.user.dao;

import java.util.List;

import com.retailsbs.logistikapp.user.dto.AccessTinyDTO;
import com.retailsbs.logistikapp.user.dto.AccessTinyExtDTO;

/**
 * Class interface para obtener los accesos del usuario
 * @author JORGE
 * @since 20-02-2015
 */
public interface FinderAccessDAO {

	/**
	 * Obtiene una lista de todos los supplier que el usuario tiene acceso
	 * @param id_user Id usuario
	 * @return Lista de objetos {AccessTinyDTO}
	 * @author JORGE
	 * @since 20-02-2015
	 */
	public List<AccessTinyDTO> getAccessSupplierByIdUser(Long id_user);
	/**
	 * Obtiene una lista extendida de todos los supplier y la relaci�n con el usuario
	 * @param id_user Id usuario
	 * @return Lista de objetos {AccessTinyExtDTO}
	 * @author JORGE
	 * @since 24-03-2015
	 */
	public List<AccessTinyExtDTO> getAccessSupplierExtByIdUser(Long id_user);
	/**
	 * elimina registros de access por medio de id_store
	 * @param id_store
	 * @return numero de registros eliminados
	 * @author JuanCarlosRamosCampos
	 * @since 20-11-2015
	 */
	int delAccessByIdStore(Long id_store);
	/**
	 * elimina registros de access por medio de id_retail
	 * @param id_retail
	 * @return numero de registros eliminados
	 * @author JuanCarlosRamosCampos
	 * @since 23-11-2015
	 */
	int delAccessByIdRetail(Long id_retail);
	/**
	 * Elimina acces por id_supplier
	 * @param id_supplier
	 * @return numero de access eliminados
	 * @author JuanCarlosRamosCampos
	 * @since 23-11-2015
	 */
	int delAccessByIdSupplier(Long id_supplier);
	/**
	 * elimina Access por id_user
	 * @param id_user
	 * @return numero de access eliminados
	 * @author JuanCarlosRamosCampos
	 * @since 24-11-2015
	 */
	int delAccessByIdUser(Long id_user);
	
}
