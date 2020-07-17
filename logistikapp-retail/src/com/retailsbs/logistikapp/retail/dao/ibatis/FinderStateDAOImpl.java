package com.retailsbs.logistikapp.retail.dao.ibatis;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.retailsbs.logistikapp.retail.dao.FinderStateDAO;
import com.retailsbs.logistikapp.retail.dto.GetStateSearchCriteria;
import com.retailsbs.logistikapp.retail.dto.StateDTO;
import com.retailsbs.logistikapp.retail.dto.StateQtyCityDTO;

public class FinderStateDAOImpl extends SqlMapClientDaoSupport implements FinderStateDAO {

	public FinderStateDAOImpl(){
		super();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<StateDTO> getStateByCriteria(GetStateSearchCriteria dto) {
		return (List<StateDTO>) getSqlMapClientTemplate().queryForList("finder_state.getState", dto);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<StateQtyCityDTO> getAllStateQtyCityByIdCountry(Long id_country) {
		return (List<StateQtyCityDTO>) getSqlMapClientTemplate().queryForList("finder_state.getAllStateQtyCity", id_country);
	}

}
