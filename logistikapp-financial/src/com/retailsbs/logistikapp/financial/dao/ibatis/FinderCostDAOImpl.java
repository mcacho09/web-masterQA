package com.retailsbs.logistikapp.financial.dao.ibatis;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.retailsbs.logistikapp.financial.dao.FinderCostDAO;

public class FinderCostDAOImpl extends SqlMapClientDaoSupport implements FinderCostDAO {

	public FinderCostDAOImpl() {
		super();
	}
	
	@Override
	public int delCostByIdSupplier(Long id_supplier) {
		return getSqlMapClientTemplate().delete("finder_cost.delCostByIdSupplier", id_supplier);
	}

}
