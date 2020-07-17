package com.retailsbs.logistikapp.financial.dto;

public class WSProductCriteria {

	private Long id_supplier;
	private String active;
	private Long id_store;
	
	public Long getId_supplier() {
		return id_supplier;
	}
	public void setId_supplier(Long id_supplier) {
		this.id_supplier = id_supplier;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public Long getId_store() {
		return id_store;
	}
	public void setId_store(Long id_store) {
		this.id_store = id_store;
	}
	
}
