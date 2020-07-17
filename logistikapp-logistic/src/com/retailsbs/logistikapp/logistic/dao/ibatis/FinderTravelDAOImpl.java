package com.retailsbs.logistikapp.logistic.dao.ibatis;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.retailsbs.logistikapp.logistic.dao.FinderTravelDAO;
import com.retailsbs.logistikapp.logistic.domain.Travel;
import com.retailsbs.logistikapp.logistic.dto.InfoReportPromotion;
import com.retailsbs.logistikapp.logistic.dto.MetricsCounterCriteria;
import com.retailsbs.logistikapp.logistic.dto.MetricsCounterDTO;
import com.retailsbs.logistikapp.logistic.dto.ParameterReportPromotion;
import com.retailsbs.logistikapp.logistic.dto.ProgressMetricsOperativesCriteria;
import com.retailsbs.logistikapp.logistic.dto.ProgressMetricsOperativesDTO;
import com.retailsbs.logistikapp.logistic.dto.TravelAndStatusStr;
import com.retailsbs.logistikapp.logistic.dto.TravelByIdStoreDTO;
import com.retailsbs.logistikapp.logistic.dto.TravelByIdStoreSearch;
import com.retailsbs.logistikapp.logistic.dto.TravelDTO;
import com.retailsbs.logistikapp.logistic.dto.TravelSearchCriteria;
import com.retailsbs.logistikapp.logistic.dto.TravelsToVisitedCriteria;
import com.retailsbs.logistikapp.logistic.dto.TrvStatusStrSearchCriteria;

public class FinderTravelDAOImpl extends SqlMapClientDaoSupport implements FinderTravelDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<TravelDTO> getTravelByCriteria(TravelSearchCriteria dto) {
			return (List<TravelDTO>) getSqlMapClientTemplate().queryForList("finder_travel.getTravelbyCriteria", dto);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TravelByIdStoreDTO> getTravelByIdStore(TravelByIdStoreSearch dto) {
		return (List<TravelByIdStoreDTO>) getSqlMapClientTemplate().queryForList("finder_travel.getTravelByIdStore", dto);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TravelAndStatusStr> getTravelAndStatusStrByIdStore(TrvStatusStrSearchCriteria dto) {
		return (List<TravelAndStatusStr>) getSqlMapClientTemplate().queryForList("finder_travel.getTravelAndStatusStrByIdStore", dto);
	}

	@Override
	public int delTravelByIdRoute(Long id_route) {
		return getSqlMapClientTemplate().delete("finder_travel.delTravelByIdRoute", id_route);
	}

	@Override
	public int delTravelByIdUser(Long id_user) {
		return getSqlMapClientTemplate().delete("finder_travel.delTravelByIdUser", id_user);
	}

	@Override
	public MetricsCounterDTO getMetricsCounter(MetricsCounterCriteria dto) {
		return (MetricsCounterDTO) getSqlMapClientTemplate().queryForObject("finder_travel.getMetricsCounter",dto);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<InfoReportPromotion> getTravelWithPromotion(
			ParameterReportPromotion data) {
		// TODO Auto-generated method stub
		return (List<InfoReportPromotion> ) getSqlMapClientTemplate().queryForList("finder_travel.getTravelWithPromotion", data);
	}

	@Override
	public Travel getLastTravelTRAorFIN(long id_user) {
		return (Travel) getSqlMapClientTemplate().queryForObject("finder_travel.getLastTravelTRAorFIN", id_user);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProgressMetricsOperativesDTO> getProgressMetricsOperatives(ProgressMetricsOperativesCriteria criteria) {
		return (List<ProgressMetricsOperativesDTO>) getSqlMapClientTemplate().queryForList("finder_travel.getProgressMetricsOperatives", criteria);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Travel> getTravelsToVisited(TravelsToVisitedCriteria criteria) {
		return (List<Travel>) getSqlMapClientTemplate().queryForList("finder_travel.getTravelsToVisited", criteria);
	}
	
}
