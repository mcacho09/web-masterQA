package com.retailsbs.logistikapp.logistic.dao.ibatis;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.retailsbs.logistikapp.logistic.dao.FinderRouteStoreDAO;

public class FinderRouteStoreDAOImpl extends SqlMapClientDaoSupport implements FinderRouteStoreDAO {

	@Override
	public int delRouteStoreByIdRoute(Long id_route) {
		return getSqlMapClientTemplate().delete("finder_route_store.deleteByIdRoute", id_route);
	}

	@Override
	public int delRouteStoreByIdStore(Long id_store) {
		return getSqlMapClientTemplate().delete("finder_route_store.deleteByIdStore", id_store);	}

}
