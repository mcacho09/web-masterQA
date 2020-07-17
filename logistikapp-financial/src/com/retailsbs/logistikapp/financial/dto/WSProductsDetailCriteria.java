package com.retailsbs.logistikapp.financial.dto;

public class WSProductsDetailCriteria {

	private Long id_supplier;
	private Long id_order;
	private String typetrx;
	
	public Long getId_supplier() {
		return id_supplier;
	}
	public void setId_supplier(Long id_supplier) {
		this.id_supplier = id_supplier;
	}
	public Long getId_order() {
		return id_order;
	}
	public void setId_order(Long id_order) {
		this.id_order = id_order;
	}
	public String getTypetrx() {
		return typetrx;
	}
	public void setTypetrx(String typetrx) {
		this.typetrx = typetrx;
	}
	
}
