package com.retailsbs.logistikapp.financial.dao;


/**
 * Class interface para obtener cost
 * @author JuanCarlosRamosCampos
 * @since 23-11-2015
 */

public interface FinderCostDAO {

	/**
	 * Elimina cost por id_supplier
	 * @param id_supplier
	 * @return numero de cost esliminados
	 * @author JuanCarlosRamosCampos
	 * @since 23-11-2015
	 */
	int delCostByIdSupplier(Long id_supplier);
	
}
