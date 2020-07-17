package com.retailsbs.logistikapp.retail.dao.ibatis;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.retailsbs.logistikapp.retail.dao.FinderCountryDAO;
import com.retailsbs.logistikapp.retail.dto.CountryDTO;
import com.retailsbs.logistikapp.retail.dto.CountrySearchCriteria;

public class FinderCountryDAOImpl extends SqlMapClientDaoSupport implements FinderCountryDAO {

	public FinderCountryDAOImpl(){
		super();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<CountryDTO> getCountryByCriteria(CountrySearchCriteria dto) {
		List<CountryDTO> list = (List<CountryDTO>) getSqlMapClientTemplate().queryForList("finder_country.getCountry", dto);
		return list;
	}

	@Override
	public Integer getCountryTotalByCriteria(CountrySearchCriteria dto) {
		return (Integer) getSqlMapClientTemplate().queryForObject("finder_country.getCountryTotalByCriteria", dto);
	}

}
