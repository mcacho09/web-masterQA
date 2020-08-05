package com.retailsbs.logistikapp.financial.dto;

public class TrxDTO {

	private Long id_product;
	private int quantity;	
	private Double price_sale;
	private Double price_sug;
	private String product_type;
	
	public Long getId_product() {
		return id_product;
	}
	public void setId_product(Long id_product) {
		this.id_product = id_product;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Double getPrice_sale() {
		return price_sale;
	}
	public void setPrice_sale(Double price_sale) {
		this.price_sale = price_sale;
	}
	public Double getPrice_sug() {
		return price_sug;
	}
	public void setPrice_sug(Double price_sug) {
		this.price_sug = price_sug;
	}
	public String getProduct_type() {
		return product_type;
	}
	public void setProduct_type(String product_type) {
		this.product_type = product_type;
	}
}
