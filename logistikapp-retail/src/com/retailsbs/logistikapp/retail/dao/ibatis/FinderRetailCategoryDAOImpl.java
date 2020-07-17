package com.retailsbs.logistikapp.retail.dao.ibatis;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.retailsbs.logistikapp.retail.dao.FinderRetailCategoryDAO;
import com.retailsbs.logistikapp.retail.domain.RetailCategory;
import com.retailsbs.logistikapp.retail.dto.RetailCategoryActiveSearchCriteria;

public class FinderRetailCategoryDAOImpl extends SqlMapClientDaoSupport implements FinderRetailCategoryDAO{

	@SuppressWarnings("unchecked")
	public List<RetailCategory> getRetailsCategoryActiveByCriteria(RetailCategoryActiveSearchCriteria dto) {
		List<RetailCategory> list = (List<RetailCategory>) getSqlMapClientTemplate().queryForList("finder_retailcategory.getRetailsCategoryActiveByCriteria", dto);
		return list;
	}

	@Override
	public int delRetailCategoryByIdSupplier(Long id_supplier) {
		return getSqlMapClientTemplate().delete("finder_retailcategory.delRetailCategoryByIdSupplier",id_supplier);
	}
	

}
