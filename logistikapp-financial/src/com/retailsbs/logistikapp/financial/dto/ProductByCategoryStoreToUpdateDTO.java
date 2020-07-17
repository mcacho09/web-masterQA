package com.retailsbs.logistikapp.financial.dto;

public class ProductByCategoryStoreToUpdateDTO {
	
	private Long id_store_category_product;
	private Long id_product;
	private Long id_store_category;
	private String code;
	private String name_short;
	private String name_long;
	private Double price_sale;
	private Double price_cost;
	private Double price_sale_category;
	private Integer tax;
	
	public Long getId_store_category_product() {
		return id_store_category_product;
	}
	public void setId_store_category_product(Long id_store_category_product) {
		this.id_store_category_product = id_store_category_product;
	}
	public Long getId_product() {
		return id_product;
	}
	public void setId_product(Long id_product) {
		this.id_product = id_product;
	}
	public Long getId_store_category() {
		return id_store_category;
	}
	public void setId_store_category(Long id_store_category) {
		this.id_store_category = id_store_category;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName_short() {
		return name_short;
	}
	public void setName_short(String name_short) {
		this.name_short = name_short;
	}
	public String getName_long() {
		return name_long;
	}
	public void setName_long(String name_long) {
		this.name_long = name_long;
	}
	public Double getPrice_sale() {
		return price_sale;
	}
	public void setPrice_sale(Double price_sale) {
		this.price_sale = price_sale;
	}
	public Double getPrice_cost() {
		return price_cost;
	}
	public void setPrice_cost(Double price_cost) {
		this.price_cost = price_cost;
	}
	public Double getPrice_sale_category() {
		return price_sale_category;
	}
	public void setPrice_sale_category(Double price_sale_category) {
		this.price_sale_category = price_sale_category;
	}
	public Integer getTax() {
		return tax;
	}
	public void setTax(Integer tax) {
		this.tax = tax;
	}
}
