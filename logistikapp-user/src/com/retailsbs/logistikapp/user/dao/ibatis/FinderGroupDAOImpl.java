package com.retailsbs.logistikapp.user.dao.ibatis;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.retailsbs.logistikapp.user.dao.FinderGroupDAO;

public class FinderGroupDAOImpl extends SqlMapClientDaoSupport implements
		FinderGroupDAO {

	public FinderGroupDAOImpl() {
		super();
	}

	
	public int delGroupByIdUser(Long id_user) {
		return getSqlMapClientTemplate().delete("finder_group.delGroupByIdUser", id_user);
	}


}
