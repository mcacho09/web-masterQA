package com.retailsbs.logistikapp.financial.dto;

import com.retailsbs.logistikapp.financial.domain.Product;

public class ProductDTO extends Product {

	private String category;
	private String brand;

	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
}
