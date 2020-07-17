package com.retailsbs.logistikapp.retail.dto;

public class StoreCategoryActiveSearchCriteria {
	private String active;
	private Long id_supplier;
	private Long id_store_category;
	
	public Long getId_store_category() {
		return id_store_category;
	}
	public void setId_store_category(Long id_store_category) {
		this.id_store_category = id_store_category;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public Long getId_supplier() {
		return id_supplier;
	}
	public void setId_supplier(Long id_supplier) {
		this.id_supplier = id_supplier;
	}
	
}
