package com.retailsbs.logistikapp.financial.dao;

import java.util.List;

import com.retailsbs.logistikapp.financial.dto.ConfigurationStockProductByIdCondigurationStockCriteria;
import com.retailsbs.logistikapp.financial.dto.ConfigurationStockProductDTO;

public interface FinderConfigurationStockProduct {

	Long getConfigurationStockProductNextValue();
	
	List<ConfigurationStockProductDTO> getConfigurationStockProductByIdCondigurationStock(ConfigurationStockProductByIdCondigurationStockCriteria criteria);
	
	int deleteConfigurationStockProductByIdConfigurationStock(Long id_configuration_stock);
	
}
