package com.retailsbs.logistikapp.retail.dao.ibatis;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.retailsbs.logistikapp.retail.dao.FinderRetailDeptDAO;

public class FinderRetailDeptDAOImpl extends SqlMapClientDaoSupport implements FinderRetailDeptDAO {

	public FinderRetailDeptDAOImpl(){
		super();
	}
	
	@Override
	public int delRetailDepByIdRetail(Long id_retail) {
		return getSqlMapClientTemplate().delete("finder_retail.deleteByIdRetail", id_retail);
	}

}
