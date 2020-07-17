package com.retailsbs.logistikapp.financial.dto;

public class CategoryAndNoStoresDTO {
	
	private Long id_store_category;
	private String category;
	private Long total_stores;
	
	public Long getId_store_category() {
		return id_store_category;
	}
	public void setId_store_category(Long id_store_category) {
		this.id_store_category = id_store_category;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Long getTotal_stores() {
		return total_stores;
	}
	public void setTotal_stores(Long total_stores) {
		this.total_stores = total_stores;
	}

}
