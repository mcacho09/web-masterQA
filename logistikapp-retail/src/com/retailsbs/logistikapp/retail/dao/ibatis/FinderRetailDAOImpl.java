package com.retailsbs.logistikapp.retail.dao.ibatis;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.retailsbs.logistikapp.retail.dao.FinderRetailDAO;
import com.retailsbs.logistikapp.retail.domain.Retail;
import com.retailsbs.logistikapp.retail.dto.ChangeRetailCriteria;
import com.retailsbs.logistikapp.retail.dto.QtyStrCheckByRetai;
import com.retailsbs.logistikapp.retail.dto.QtyStrCheckByRetailDTO;
import com.retailsbs.logistikapp.retail.dto.RetailByIdsSearchCriteria;
import com.retailsbs.logistikapp.retail.dto.RetailDTO;
import com.retailsbs.logistikapp.retail.dto.RetailSearchCriteria;

public class FinderRetailDAOImpl extends SqlMapClientDaoSupport implements FinderRetailDAO {
	
	@Override
	@SuppressWarnings("unchecked")
	public List<RetailDTO> getRetailByCriteria(RetailSearchCriteria dto) {
		return (List<RetailDTO>) getSqlMapClientTemplate().queryForList("finder_retail.getRetail", dto); 
	}

	@Override
	public RetailDTO getRetailExtById(Long id_retail) {
		return (RetailDTO)getSqlMapClientTemplate().queryForObject("finder_retail.getRetailExtById", id_retail);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Retail> getRetailByIds(RetailByIdsSearchCriteria dto) {
		return (List<Retail>)getSqlMapClientTemplate().queryForList("finder_retail.getRetailByIds", dto);		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<QtyStrCheckByRetailDTO> getRetailCountStrCheck(QtyStrCheckByRetai dto) {
		return (List<QtyStrCheckByRetailDTO>)getSqlMapClientTemplate().queryForList("finder_retail.getRetailCountStrCheck", dto);		
	}

	@Override
	public int delRetailByIdSupplier(Long id_supplier) {
		return getSqlMapClientTemplate().delete("finder_retail.delRetailByIdSupplier", id_supplier);
	}

	@Override
	public Integer getCliQtyByCriteria(RetailSearchCriteria dto) {
		return (Integer) getSqlMapClientTemplate().queryForObject("finder_retail.getRetailQty",dto);
	}

	@Override
	public int changeStoresRetail(ChangeRetailCriteria criteria) {
		return getSqlMapClientTemplate().update("finder_retail.changeStoresRetail", criteria);
	}
	
}
