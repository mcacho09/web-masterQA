package com.retailsbs.logistikapp.financial.dto;

import java.util.List;

import com.retailsbs.logistikapp.financial.domain.ConfigurationStock;
import com.retailsbs.logistikapp.financial.domain.ConfigurationStockProduct;

public class AddConfigurationStockProductDTO {

	private ConfigurationStock configuratin;
	private List<ConfigurationStockProduct> products;
	
	public ConfigurationStock getConfiguratin() {
		return configuratin;
	}
	public void setConfiguratin(ConfigurationStock configuratin) {
		this.configuratin = configuratin;
	}
	public List<ConfigurationStockProduct> getProducts() {
		return products;
	}
	public void setProducts(List<ConfigurationStockProduct> products) {
		this.products = products;
	}
	
}
