package com.retailsbs.logistikapp.retail.dao.ibatis;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.retailsbs.logistikapp.retail.dao.FinderStoreDAO;
import com.retailsbs.logistikapp.retail.domain.Store;
import com.retailsbs.logistikapp.retail.dto.AvailableStoreByIdRetail;
import com.retailsbs.logistikapp.retail.dto.AvailableStoreByIdRetailIdRoute;
import com.retailsbs.logistikapp.retail.dto.AvailableStoreByIdStoreCategory;
import com.retailsbs.logistikapp.retail.dto.AvailableStoreByIdStoreCategoryIdRoute;
import com.retailsbs.logistikapp.retail.dto.AvailableStoreByIdSupplierIdRoute;
import com.retailsbs.logistikapp.retail.dto.StoreActiveByIdsRetailSearchCriteria;
import com.retailsbs.logistikapp.retail.dto.StoreAvailableInRouteCriteria;
import com.retailsbs.logistikapp.retail.dto.StoreAvailableInTravelCriteria;
import com.retailsbs.logistikapp.retail.dto.StoreByCategoryDTO;
import com.retailsbs.logistikapp.retail.dto.AvailableStoreSearchCriteria;
import com.retailsbs.logistikapp.retail.dto.AvailableStoreDTO;
import com.retailsbs.logistikapp.retail.dto.StoreByIdsSearchCriteria;
import com.retailsbs.logistikapp.retail.dto.StoreByNameAddress;
import com.retailsbs.logistikapp.retail.dto.StoreByRetailDTO;
import com.retailsbs.logistikapp.retail.dto.StoreByRouteDTO;
import com.retailsbs.logistikapp.retail.dto.StoreCallCenterDTO;
import com.retailsbs.logistikapp.retail.dto.StoreDTO;
import com.retailsbs.logistikapp.retail.dto.StoreInfoDTO;
import com.retailsbs.logistikapp.retail.dto.StoreInfoSearchCriteria;
import com.retailsbs.logistikapp.retail.dto.StoreNotInTravelSearchCriteria;
import com.retailsbs.logistikapp.retail.dto.StoreRouteDTO;
import com.retailsbs.logistikapp.retail.dto.StoreSearchCriteria;
import com.retailsbs.logistikapp.retail.dto.TotalStoreCreateCriteria;

public class FinderStoreDAOImpl extends SqlMapClientDaoSupport implements FinderStoreDAO {

	public FinderStoreDAOImpl(){
		super();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<StoreDTO> getStoreByCriteria(StoreSearchCriteria dto) {
		List<StoreDTO> list = (List<StoreDTO>) getSqlMapClientTemplate().queryForList("finder_store.getStore", dto);
		return list;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Store> getStoreByIds(StoreByIdsSearchCriteria dto) {
		return (List<Store>) getSqlMapClientTemplate().queryForList("finder_store.getStoreByIds", dto);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Store> getStoreByIdRetail(Long id_retail) {
		return (List<Store>) getSqlMapClientTemplate().queryForList("finder_store.getStoreByIdRetail", id_retail);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Store> getAvailableStoreByIdRetail(AvailableStoreByIdRetail dto) {
		return (List<Store>) getSqlMapClientTemplate().queryForList("finder_store.getAvailableStoreByIdRetail", dto);
	}
	@Override
	@SuppressWarnings("unchecked")
	public List<Store> getAvailableStoreByIdRetailAndIdRoute(AvailableStoreByIdRetailIdRoute dto) {
		return (List<Store>) getSqlMapClientTemplate().queryForList("finder_store.getAvailableStoreByIdRetailAndIdRoute", dto);
	}
	@Override
	@SuppressWarnings("unchecked")
	public List<Store> getAvailableStoreByIdStoreCategory(AvailableStoreByIdStoreCategory dto) {
		return (List<Store>) getSqlMapClientTemplate().queryForList("finder_store.getAvailableStoreByIdStoreCategory", dto);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Store> getAvailableStoreByIdStoreCategoryAndIdRoute(AvailableStoreByIdStoreCategoryIdRoute dto) {
		return (List<Store>) getSqlMapClientTemplate().queryForList("finder_store.getAvailableStoreByIdStoreCategoryAndIdRoute", dto);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Long> getIdStoresActiveByIdRetail(Long id_retail) {
		return (List<Long>) getSqlMapClientTemplate().queryForList("finder_store.getIdStoresActiveByIdRetail", id_retail);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Store> getStoreByIdRetailCategory(Long id_retail_category) {
		return (List<Store>) getSqlMapClientTemplate().queryForList("finder_store.getStoreByIdRetailCategory", id_retail_category);
	}

	@Override
	public StoreDTO getStoreExtById(Long id_store) {
		return (StoreDTO)getSqlMapClientTemplate().queryForObject("finder_store.getStoreExtById", id_store);
	}

	@Override
	public Integer getTotalStoreByIdSupplier(Long id_supplier) {
		return (Integer) getSqlMapClientTemplate().queryForObject("finder_store.getTotalStoreByIdSupplier",id_supplier);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Long> getIdStoresActiveByIdsRetail(StoreActiveByIdsRetailSearchCriteria dto) {
		return (List<Long>) getSqlMapClientTemplate().queryForList("finder_store.getIdStoresActiveByIdsRetail", dto);
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Store> getStoreByIdSupplier(Long id_supplier) {
		return (List<Store>) getSqlMapClientTemplate().queryForList("finder_store.getStoreByIdSupplier", id_supplier);
		
	}	@SuppressWarnings("unchecked")
	@Override
	public List<Store> getAvailableStoreByIdSupplier(Long id_supplier) {
		return (List<Store>) getSqlMapClientTemplate().queryForList("finder_store.getAvailableStoreByIdSupplier", id_supplier);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Store> getAvailableStoreByIdSupplierAndIdRoute(AvailableStoreByIdSupplierIdRoute dto) {
		return (List<Store>) getSqlMapClientTemplate().queryForList("finder_store.getAvailableStoreByIdSupplierAndIdRoute", dto);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<StoreByRouteDTO> getStoreByRoute(Long id_route) {
		return (List<StoreByRouteDTO>) getSqlMapClientTemplate().queryForList("finder_store.getStoreByRoute", id_route);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StoreByRouteDTO> getStoreNotInTravel(
			StoreNotInTravelSearchCriteria dto) {
		return (List<StoreByRouteDTO>) getSqlMapClientTemplate().queryForList("finder_store.getStoreNotInTravel", dto);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StoreDTO> getStoreAvailableByRoute(StoreAvailableInRouteCriteria dto) {
		return (List<StoreDTO>) getSqlMapClientTemplate().queryForList("finder_store.getStoreAvailableByRoute", dto);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StoreDTO> getStoreInTravelByRoute(StoreAvailableInRouteCriteria dto) {
		return (List<StoreDTO>) getSqlMapClientTemplate().queryForList("finder_store.getStoreInTravelByRoute", dto);		
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<StoreDTO> getStoreInOtherTravelByCriteria(StoreSearchCriteria dto) {
		return (List<StoreDTO>) getSqlMapClientTemplate().queryForList("finder_store.getStoreInOtherTravelByCriteria", dto);		
	}

	@Override
	public Integer getTotalStoreByCriteria(TotalStoreCreateCriteria dto) {
		return (Integer) getSqlMapClientTemplate().queryForObject("finder_store.getTotalStoreByCriteria", dto);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<StoreInfoDTO> getStoreInfoByCriteria(StoreInfoSearchCriteria dto) {
		return (List<StoreInfoDTO>) getSqlMapClientTemplate().queryForList("finder_store.getStoreInfo", dto);		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StoreCallCenterDTO> getStoreByNameAddress(StoreByNameAddress dto) {
		return (List<StoreCallCenterDTO>) getSqlMapClientTemplate().queryForList("finder_store.getStoreByNameAddress", dto);
	}

	@Override
	public int delStoreByIdStoreCategory(Long id_store_category) {
		return getSqlMapClientTemplate().delete("finder_store.delStoreByIdStoreCategory",id_store_category);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StoreByRetailDTO> getStoreByRetail(Long id_supplier) {
		return (List<StoreByRetailDTO>) getSqlMapClientTemplate().queryForList("finder_store.getStoreByRetail", id_supplier);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StoreByCategoryDTO> getStoreByCategory(Long id_supplier) {
		return (List<StoreByCategoryDTO>) getSqlMapClientTemplate().queryForList("finder_store.getStoreByCategory", id_supplier);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AvailableStoreDTO> getAvailableStoreByCriteria(AvailableStoreSearchCriteria dto) {
		return (List<AvailableStoreDTO>) getSqlMapClientTemplate().queryForList("finder_store.getAvailableStoreByCriteria", dto);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StoreByRouteDTO> getStoreAvailableInTravel(
			StoreAvailableInTravelCriteria criteria) {
		return (List<StoreByRouteDTO>) getSqlMapClientTemplate().queryForList("finder_store.getStoreAvailableInTravel", criteria);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StoreRouteDTO> getStoresByIdsRoute(StoreByIdsSearchCriteria dto){
		return (List<StoreRouteDTO>) getSqlMapClientTemplate().queryForList("finder_store.getStoresByIdsRoute", dto);
	}

	@Override
	public Long getTotalStore(Long id_retail) {
		return (Long) getSqlMapClientTemplate().queryForObject("finder_store.getTotalStore", id_retail);
	}

}
