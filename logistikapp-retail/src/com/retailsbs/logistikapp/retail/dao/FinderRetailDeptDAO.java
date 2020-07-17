package com.retailsbs.logistikapp.retail.dao;


/**
 * interface de finder retailDept
 * @author jucaraca
 */
public interface FinderRetailDeptDAO {

	/**
	 * Elimina retailDept por medio de id_retail
	 * @param id_retail
	 * @return numero de retailDept eliminados
	 * @author JuanCarlosRamosCampos
	 * @since 21-11-2015
	 */
	int delRetailDepByIdRetail(Long id_retail);
	
}
