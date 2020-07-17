package com.retailsbs.logistikapp.retail.dao.ibatis;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.retailsbs.logistikapp.retail.dao.FinderStoreCategoryDAO;
import com.retailsbs.logistikapp.retail.domain.StoreCategory;
import com.retailsbs.logistikapp.retail.dto.StoreCategoryActiveSearchCriteria;

public class FinderStoreCategoryDAOImpl  extends SqlMapClientDaoSupport implements FinderStoreCategoryDAO{

	public List<StoreCategory> getStoresCategoryActiveByCriteria(StoreCategoryActiveSearchCriteria dto) {
		@SuppressWarnings("unchecked")
		List<StoreCategory> list = (List<StoreCategory>) getSqlMapClientTemplate().queryForList("finder_storecategory.getStoresCategoryActiveByCriteria", dto);
		return list;
	}

	@Override
	public int delStoreCategoryByIdSupplier(Long id_supplier) {
		return getSqlMapClientTemplate().delete("finder_storecategory.delStoreCategoryByIdSupplier", id_supplier);
	}

}
