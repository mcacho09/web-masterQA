package com.retailsbs.logistikapp.logistic.dao.ibatis;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.retailsbs.logistikapp.logistic.dao.FinderRouteDAO;
import com.retailsbs.logistikapp.logistic.domain.Route;
import com.retailsbs.logistikapp.logistic.dto.CountRouteSupplierCriteria;
import com.retailsbs.logistikapp.logistic.dto.RoutesByListRetails;

public class FinderRouteDAOImpl extends SqlMapClientDaoSupport implements
		FinderRouteDAO {

	public FinderRouteDAOImpl() {
		super();
	}

	@Override
	public int delRouteByIdSupplier(Long id_supplier) {
		return getSqlMapClientTemplate().delete(
				"finder_route.delRouteByIdSupplier", id_supplier);
	}

	@Override
	public int getCountRouteSupplierByCriteria(CountRouteSupplierCriteria dto) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"finder_route.getCountRouteSupplier", dto);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Route> getRouteByListRetail(RoutesByListRetails dto) {
		return (List<Route>) getSqlMapClientTemplate().queryForList(
				"finder_route.getRouteByListRetail", dto);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Long> getIdsRouteByIdTravel(Long id_travel) {
		// TODO Auto-generated method stub
		return (List<Long>) getSqlMapClientTemplate().queryForList(
				"finder_route.getIdsRouteByIdTravel", id_travel);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Route> getRoutesByIdTravel(Long id_travel) {
		// TODO Auto-generated method stub
		return (List<Route>) getSqlMapClientTemplate().queryForList(
				"finder_route.getRoutesByIdTravel", id_travel);
	}

}
