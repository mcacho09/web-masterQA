package com.retailsbs.logistikapp.financial.dto;

public class ProductToSubAlmacenDTO extends ProductAlmacenDTO {
	
	private Long qty_stock_general;

	public Long getQty_stock_general() {
		return qty_stock_general;
	}

	public void setQty_stock_general(Long qty_stock_general) {
		this.qty_stock_general = qty_stock_general;
	}
	
}
