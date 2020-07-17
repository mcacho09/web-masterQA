package com.retailsbs.logistikapp.financial.dao.ibatis;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.retailsbs.logistikapp.financial.dao.FinderStoreCategoryProductDAO;
import com.retailsbs.logistikapp.financial.dto.ProductByCategoryStoreToUpdateCriteria;
import com.retailsbs.logistikapp.financial.dto.ProductByCategoryStoreToUpdateDTO;

public class FinderStoreCategoryProductDAOImpl extends SqlMapClientDaoSupport implements FinderStoreCategoryProductDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductByCategoryStoreToUpdateDTO> getProductByCategoryStoreToUpdate(
			ProductByCategoryStoreToUpdateCriteria criteria) {
		return (List<ProductByCategoryStoreToUpdateDTO>) getSqlMapClientTemplate().queryForList("finder_store_category_product.getProductByCategoryStoreToUpdate", criteria);
	}

}
