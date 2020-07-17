package com.retailsbs.logistikapp.logistic.dao.ibatis;

import java.util.Date;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.retailsbs.logistikapp.logistic.dao.FinderWayBillDAO;
import com.retailsbs.logistikapp.logistic.dto.CustomersNotVisitedCriteria;
import com.retailsbs.logistikapp.logistic.dto.CustomersNotVisitedDTO;
import com.retailsbs.logistikapp.logistic.dto.StoresNotVisitedByDateAndSupplierCriteria;
import com.retailsbs.logistikapp.logistic.dto.StoresNotVisitedByDateAndSupplierDTO;
import com.retailsbs.logistikapp.logistic.dto.WayBillDTO;
import com.retailsbs.logistikapp.logistic.dto.WayBillSearchCriteria;

public class FinderWayBillDAOImpl  extends SqlMapClientDaoSupport implements FinderWayBillDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<WayBillDTO> getWayBillByIdTravel(Long id_travel) {
		return (List<WayBillDTO>) getSqlMapClientTemplate().queryForList("finder_waybill.getWayBillByIdTravel", id_travel);
	}
	@Override
	public int delWayBillByIdTravel(Long id_travel) {
		return getSqlMapClientTemplate().delete("finder_waybill.deleteByIdTravel", id_travel);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<WayBillDTO> getWayBillByCriteria(WayBillSearchCriteria dto) {
		return (List<WayBillDTO>) getSqlMapClientTemplate().queryForList("finder_waybill.getWayBillByCriteria", dto);
	}
	@Override
	public int delWaybillByIdStore(Long id_store) {
		return getSqlMapClientTemplate().delete("finder_waybill.deleteByIdStore", id_store);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CustomersNotVisitedDTO> getCustomersNotVisited(CustomersNotVisitedCriteria criteria) {
		return (List<CustomersNotVisitedDTO>) getSqlMapClientTemplate().queryForList("finder_waybill.getCustomersNotVisited", criteria);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Long> getSuppliersWithStoresNotVisited(Date date) {
		return (List<Long>) getSqlMapClientTemplate().queryForList("finder_waybill.getSuppliersWithStoresNotVisited", date);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<StoresNotVisitedByDateAndSupplierDTO> getStoresNotVisitedByDateAndSupplier(StoresNotVisitedByDateAndSupplierCriteria criteria) {
		return (List<StoresNotVisitedByDateAndSupplierDTO>) getSqlMapClientTemplate().queryForList("finder_waybill.getStoresNotVisitedByDateAndSupplier", criteria);
	}

}
