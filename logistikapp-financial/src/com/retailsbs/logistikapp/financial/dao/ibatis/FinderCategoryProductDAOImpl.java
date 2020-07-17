package com.retailsbs.logistikapp.financial.dao.ibatis;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.retailsbs.logistikapp.financial.dao.FinderCategoryProductDAO;
import com.retailsbs.logistikapp.financial.dto.CategoryProductDTO;
import com.retailsbs.logistikapp.financial.dto.CategoryProductSearchCriteria;
import com.retailsbs.logistikapp.financial.dto.ProductDTO;
import com.retailsbs.logistikapp.financial.dto.ProductSearchCriteria;


public class FinderCategoryProductDAOImpl extends SqlMapClientDaoSupport implements FinderCategoryProductDAO {

	@Override
	@SuppressWarnings("unchecked")
	public List<CategoryProductDTO> selectCategoryProductByCriteria(CategoryProductSearchCriteria dto) {
		return (List<CategoryProductDTO>)getSqlMapClientTemplate().queryForList("finder_category_product.selectCategoryProduct", dto);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<ProductDTO> selectProductByCriteria(ProductSearchCriteria dto) {
		return (List<ProductDTO>)getSqlMapClientTemplate().queryForList("finder_product.selectProduct", dto);
	}

	@Override
	public int delCategoryProductByIdSupplier(Long id_supplier) {
		return getSqlMapClientTemplate().delete("finder_category_product.delCategoryProductByIdSupplier", id_supplier);
	}

}
