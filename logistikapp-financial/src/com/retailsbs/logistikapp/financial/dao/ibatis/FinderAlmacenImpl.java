package com.retailsbs.logistikapp.financial.dao.ibatis;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.retailsbs.logistikapp.financial.dao.FinderAlmacen;
import com.retailsbs.logistikapp.financial.dto.AlmacenInfoDTO;
import com.retailsbs.logistikapp.financial.dto.RecoverProductFromSaleDTO;

public class FinderAlmacenImpl extends SqlMapClientDaoSupport implements FinderAlmacen  {

	@Override
	public Long getAlmacenNextValue() {
		return (Long) getSqlMapClientTemplate().queryForObject("finder_almacen.almacenNextValue");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AlmacenInfoDTO> getSubAlmacenInfo(Long id_supplier) {
		return (List<AlmacenInfoDTO>) getSqlMapClientTemplate().queryForList("finder_almacen.getSubAlmacenInfo", id_supplier);
	}

	@Override
	public AlmacenInfoDTO getSubAlmacenInfoByIdAlmacen(Long id_almacen) {
		return (AlmacenInfoDTO) getSqlMapClientTemplate().queryForObject("finder_almacen.getSubAlmacenInfoByIdAlmacen", id_almacen);
	}

	@Override
	public int recoverProductFromSale(RecoverProductFromSaleDTO params) {
		return getSqlMapClientTemplate().update("finder_almacen.recoverProductFromSale", params);
	}

}
