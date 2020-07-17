package com.retailsbs.logistikapp.retail.dto;

public class RetailCategoryActiveSearchCriteria {
	private String active;
	private Long id_supplier;
	private Long id_retail_category;
	
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
	public Long getId_retail_category() {
		return id_retail_category;
	}
	public void setId_retail_category(Long id_retail_category) {
		this.id_retail_category = id_retail_category;
	}
}
