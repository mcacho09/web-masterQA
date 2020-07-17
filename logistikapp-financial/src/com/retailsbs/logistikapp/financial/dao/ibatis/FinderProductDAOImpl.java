package com.retailsbs.logistikapp.financial.dao.ibatis;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.retailsbs.logistikapp.financial.dao.FinderProductDAO;
import com.retailsbs.logistikapp.financial.dto.ProductDTO;
import com.retailsbs.logistikapp.financial.dto.ProductSearchCriteria;
import com.retailsbs.logistikapp.financial.dto.ProductToSubAlmacenDTO;

public class FinderProductDAOImpl extends SqlMapClientDaoSupport implements FinderProductDAO {

	@Override
	@SuppressWarnings("unchecked")
	public List<ProductDTO> selectProductByCriteria(ProductSearchCriteria dto) {
		return (List<ProductDTO>)getSqlMapClientTemplate().queryForList("finder_product.selectProduct", dto);
	}

	@Override
	public int delProductByIdCategoryProduct(Long id_category_product) {
		return getSqlMapClientTemplate().delete("finder_product.delProductByIdCategoryProduct",id_category_product);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getProductsNameByIdSupplier(Long id_supplier) {
		return (List<String>) getSqlMapClientTemplate().queryForList("finder_product.getProductsNameByIdSupplier", id_supplier);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Double> getPriceProductsByIdOrder(Long id_order) {
		return (List<Double>) getSqlMapClientTemplate().queryForList("finder_product.getPriceProductsByIdOrder", id_order);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductToSubAlmacenDTO> getProductsToCreateSubStock(ProductSearchCriteria criteria) {
		return (List<ProductToSubAlmacenDTO>) getSqlMapClientTemplate().queryForList("finder_product.getProductsToCreateSubStock", criteria);
	}

}
