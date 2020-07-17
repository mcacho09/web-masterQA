package com.retailsbs.logistikapp.financial.dto;

public class ConfigurationStockProductByIdCondigurationStockCriteria {

	private Long id_configuration_stock;
	private Long id_supplier;
	private Long qty_stock_general;
	
	public Long getId_configuration_stock() {
		return id_configuration_stock;
	}
	public void setId_configuration_stock(Long id_configuration_stock) {
		this.id_configuration_stock = id_configuration_stock;
	}
	public Long getId_supplier() {
		return id_supplier;
	}
	public void setId_supplier(Long id_supplier) {
		this.id_supplier = id_supplier;
	}
	public Long getQty_stock_general() {
		return qty_stock_general;
	}
	public void setQty_stock_general(Long qty_stock_general) {
		this.qty_stock_general = qty_stock_general;
	}
	
}
