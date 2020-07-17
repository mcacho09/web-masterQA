package com.retailsbs.logistikapp.financial.dao.ibatis;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.retailsbs.logistikapp.financial.dao.FinderConfigurationStock;

public class FinderConfigurationStockImpl extends SqlMapClientDaoSupport implements FinderConfigurationStock {

	@Override
	public Long getConfigurationStockNextValue() {
		return (Long) getSqlMapClientTemplate().queryForObject("finder_configuration_stock.configurationStockValue");
	}

}
