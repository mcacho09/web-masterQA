package com.retailsbs.logistikapp.retail.dto;

import com.retailsbs.logistikapp.retail.domain.Retail;

public class RetailDTO extends Retail {

	private Integer stores;
	private String category;
	private String supplier;
	private String country;
	private String state;
	private String city;
	
	public Integer getStores() {
		return stores;
	}
	public void setStores(Integer stores) {
		this.stores = stores;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}

	
	
}
