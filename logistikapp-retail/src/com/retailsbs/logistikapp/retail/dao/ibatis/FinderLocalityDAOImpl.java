package com.retailsbs.logistikapp.retail.dao.ibatis;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.retailsbs.logistikapp.retail.dao.FinderLocalityDAO;
import com.retailsbs.logistikapp.retail.dto.GetLocalitySearchCriteria;
import com.retailsbs.logistikapp.retail.dto.LocalityDTO;

public class FinderLocalityDAOImpl extends SqlMapClientDaoSupport implements FinderLocalityDAO {

	public FinderLocalityDAOImpl(){
		super();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<LocalityDTO> getLocalityByCriteria(GetLocalitySearchCriteria dto) {
		List<LocalityDTO> list = (List<LocalityDTO>) getSqlMapClientTemplate().queryForList("finder_locality.getLocality", dto);
		return list;
	}

	@Override
	public Integer getLocalityTotalByCriteria(GetLocalitySearchCriteria dto) {
		return (Integer) getSqlMapClientTemplate().queryForObject("finder_locality.getLocalityTotalByCriteria", dto);
	}

}
