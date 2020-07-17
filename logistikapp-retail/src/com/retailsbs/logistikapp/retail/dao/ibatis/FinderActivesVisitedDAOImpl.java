package com.retailsbs.logistikapp.retail.dao.ibatis;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.retailsbs.logistikapp.retail.dao.FinderActivesVisitedDAO;
import com.retailsbs.logistikapp.retail.dto.ActivesAndVisitsCriteria;
import com.retailsbs.logistikapp.retail.dto.ActivesAndVisitsDTO;
import com.retailsbs.logistikapp.retail.dto.ActivesVisitedCriteria;
import com.retailsbs.logistikapp.retail.dto.ActivesVisitedExt;
import com.retailsbs.logistikapp.retail.dto.VerifyAndRegistCheckInCriteria;

public class FinderActivesVisitedDAOImpl extends SqlMapClientDaoSupport implements FinderActivesVisitedDAO {

	@Override
	public int deleteActivesVisitedByIdActive(Long id_active) {
		return getSqlMapClientTemplate().delete("finder_actives_visited.deleteActivesVisitedByIdActive", id_active);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ActivesVisitedExt> getVisitedList(ActivesVisitedCriteria criteria) {
		return (List<ActivesVisitedExt>) getSqlMapClientTemplate().queryForList("finder_actives_visited.getVisitedList", criteria);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ActivesAndVisitsDTO> getActivesAndVisits(ActivesAndVisitsCriteria criteria) {
		return (List<ActivesAndVisitsDTO>) getSqlMapClientTemplate().queryForList("finder_actives_visited.getActivesAndVisits", criteria);
	}

	@Override
	public String verifyAndRegistCheckIn(VerifyAndRegistCheckInCriteria criteria) {
		return (String) getSqlMapClientTemplate().queryForObject("finder_actives_visited.verifyAndRegistCheckIn", criteria);
	}
	
}
