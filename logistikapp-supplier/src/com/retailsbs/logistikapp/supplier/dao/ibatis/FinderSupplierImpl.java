package com.retailsbs.logistikapp.supplier.dao.ibatis;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.retailsbs.logistikapp.supplier.dao.FinderSupplierDAO;
import com.retailsbs.logistikapp.supplier.dto.SupplierExtDTO;

public class FinderSupplierImpl extends SqlMapClientDaoSupport implements FinderSupplierDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<SupplierExtDTO> getSupplierExt() {
		return (List<SupplierExtDTO>) getSqlMapClientTemplate().queryForList("finder_supplier.getSupplierExt");
	}

}
