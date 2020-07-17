package com.retailsbs.logistikapp.financial.dto;

public class ProductByCategoryStoreToUpdateCriteria {
	
	private Long id_supplier;
	private Long id_store_category;
	
	public Long getId_supplier() {
		return id_supplier;
	}
	public void setId_supplier(Long id_supplier) {
		this.id_supplier = id_supplier;
	}
	public Long getId_store_category() {
		return id_store_category;
	}
	public void setId_store_category(Long id_store_category) {
		this.id_store_category = id_store_category;
	}
	
}
