package com.retailsbs.logistikapp.financial.dao;

import java.util.List;

import com.retailsbs.logistikapp.financial.dto.ProductAlmacenDTO;
import com.retailsbs.logistikapp.financial.dto.ProductByIdAlmacenCriteria;

public interface FinderProductAlmacen {

	Long getProductAlmacenNextValue();
	
	List<ProductAlmacenDTO> getProductsByIdAlmacen(ProductByIdAlmacenCriteria criteria);
	
	int deleteProductAlmacenByIdAlmacen(Long id_almacen);
	
	int removeProductsNotSale(Long id_almacen);
	
	List<ProductAlmacenDTO> getProductsFromAlmacen(ProductByIdAlmacenCriteria criteria);

	List<ProductAlmacenDTO> getSaledProductsByIdAlmacen(ProductByIdAlmacenCriteria criteria);
	
}
