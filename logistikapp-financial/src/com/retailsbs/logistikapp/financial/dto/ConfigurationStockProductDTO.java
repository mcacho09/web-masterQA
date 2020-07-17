package com.retailsbs.logistikapp.financial.dto;

import com.retailsbs.logistikapp.financial.domain.Product;

public class ConfigurationStockProductDTO extends Product {
	
	private Long qty;
	private Long min;
	private Long max;
	private Long qty_stock_general;
	
	public Long getQty() {
		return qty;
	}
	public void setQty(Long qty) {
		this.qty = qty;
	}
	public Long getMin() {
		return min;
	}
	public void setMin(Long min) {
		this.min = min;
	}
	public Long getMax() {
		return max;
	}
	public void setMax(Long max) {
		this.max = max;
	}
	public Long getQty_stock_general() {
		return qty_stock_general;
	}
	public void setQty_stock_general(Long qty_stock_general) {
		this.qty_stock_general = qty_stock_general;
	}

}
