package com.retailsbs.logistikapp.financial.dto;

public class ProductSearchCriteria {

	private Long id_supplier;
	private Long id_category_product;
	private Long id_brand;
	private String active;
	
	public Long getId_brand() {
		return id_brand;
	}
	public void setId_brand(Long id_brand) {
		this.id_brand = id_brand;
	}
	public Long getId_supplier() {
		return id_supplier;
	}
	public void setId_supplier(Long id_supplier) {
		this.id_supplier = id_supplier;
	}
	public Long getId_category_product() {
		return id_category_product;
	}
	public void setId_category_product(Long id_category_product) {
		this.id_category_product = id_category_product;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	
}
