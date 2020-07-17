package com.retailsbs.logistikapp.financial.dao;

import java.util.List;

import com.retailsbs.logistikapp.financial.dto.ProductByCategoryStoreToUpdateCriteria;
import com.retailsbs.logistikapp.financial.dto.ProductByCategoryStoreToUpdateDTO;

public interface FinderStoreCategoryProductDAO {

	/**
	 * Obtiene los productos con los precios relacionados a la categoría
	 * @param ProductByCategoryStoreToUpdateCriteria
	 * @return List<ProductByCategoryStoreToUpdate>
	 * @since 21/03/2017
	 * @author DMarin
	 */
	List<ProductByCategoryStoreToUpdateDTO> getProductByCategoryStoreToUpdate(ProductByCategoryStoreToUpdateCriteria criteria);
	
}
