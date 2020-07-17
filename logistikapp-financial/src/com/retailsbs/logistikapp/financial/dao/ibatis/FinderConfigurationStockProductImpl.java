package com.retailsbs.logistikapp.financial.dao.ibatis;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.retailsbs.logistikapp.financial.dao.FinderConfigurationStockProduct;
import com.retailsbs.logistikapp.financial.dto.ConfigurationStockProductByIdCondigurationStockCriteria;
import com.retailsbs.logistikapp.financial.dto.ConfigurationStockProductDTO;

public class FinderConfigurationStockProductImpl extends SqlMapClientDaoSupport implements FinderConfigurationStockProduct {

	@Override
	public Long getConfigurationStockProductNextValue() {
		return (Long) getSqlMapClientTemplate().queryForObject("finder_configuration_stock_product.configurationStockProductValue");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ConfigurationStockProductDTO> getConfigurationStockProductByIdCondigurationStock(ConfigurationStockProductByIdCondigurationStockCriteria criteria) {
		return (List<ConfigurationStockProductDTO>) getSqlMapClientTemplate().queryForList("finder_configuration_stock_product.getConfigurationStockProductByIdCondigurationStock", criteria);
	}

	@Override
	public int deleteConfigurationStockProductByIdConfigurationStock(Long id_configuration_stock) {
		return getSqlMapClientTemplate().delete("finder_configuration_stock_product.deleteConfigurationStockProductByIdConfigurationStock", id_configuration_stock);
	}

}
