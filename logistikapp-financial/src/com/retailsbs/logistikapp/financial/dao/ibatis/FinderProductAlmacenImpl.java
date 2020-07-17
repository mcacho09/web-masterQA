package com.retailsbs.logistikapp.financial.dao.ibatis;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.retailsbs.logistikapp.financial.dao.FinderProductAlmacen;
import com.retailsbs.logistikapp.financial.dto.ProductAlmacenDTO;
import com.retailsbs.logistikapp.financial.dto.ProductByIdAlmacenCriteria;

public class FinderProductAlmacenImpl extends SqlMapClientDaoSupport implements FinderProductAlmacen {

	@Override
	public Long getProductAlmacenNextValue() {
		return (Long) getSqlMapClientTemplate().queryForObject("finder_product_almacen.productAlmacenNextValue");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductAlmacenDTO> getProductsByIdAlmacen(ProductByIdAlmacenCriteria criteria) {
		return (List<ProductAlmacenDTO>) getSqlMapClientTemplate().queryForList("finder_product_almacen.getProductByIdAlmacen", criteria);
	}

	@Override
	public int deleteProductAlmacenByIdAlmacen(Long id_almacen) {
		return getSqlMapClientTemplate().delete("finder_product_almacen.deleteProductAlmacenByIdAlmacen", id_almacen);
	}

	@Override
	public int removeProductsNotSale(Long id_almacen) {
		return getSqlMapClientTemplate().delete("finder_product_almacen.removeProductsNotSale", id_almacen);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductAlmacenDTO> getProductsFromAlmacen(ProductByIdAlmacenCriteria criteria) {
		return (List<ProductAlmacenDTO>) getSqlMapClientTemplate().queryForList("finder_product_almacen.getProductsFromAlmacen", criteria);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductAlmacenDTO> getSaledProductsByIdAlmacen(ProductByIdAlmacenCriteria criteria) {
		return (List<ProductAlmacenDTO>) getSqlMapClientTemplate().queryForList("finder_product_almacen.getSaledProductsByIdAlmacen", criteria);
	}
	
	

}
