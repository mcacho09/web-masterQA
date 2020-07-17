package com.retailsbs.logistikapp.retail.dao.ibatis;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.retailsbs.logistikapp.retail.dao.FinderCityDAO;
import com.retailsbs.logistikapp.retail.dto.CityDTO;
import com.retailsbs.logistikapp.retail.dto.GetCitySearchCriteria;

public class FinderCityDAOImpl extends SqlMapClientDaoSupport implements FinderCityDAO {

	public FinderCityDAOImpl(){
		super();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<CityDTO> getCityByCriteria(GetCitySearchCriteria dto) {
		List<CityDTO> list = (List<CityDTO>) getSqlMapClientTemplate().queryForList("finder_city.getCity", dto);
		return list;
	}

	@Override
	public Integer getCityTotalByCriteria(GetCitySearchCriteria dto) {
		return (Integer) getSqlMapClientTemplate().queryForObject("finder_city.getCityTotalByCriteria", dto);
	}

}
