package com.retailsbs.logistikapp.financial.dto;

public class InfoProductsToTicketDTO {
	
	private Long quantity;
	private String name_short;
	private Double price_sale;
	private Double sale;
	
	public Long getQuantity() {
		return quantity;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	public String getName_short() {
		return name_short;
	}
	public void setName_short(String name_short) {
		this.name_short = name_short;
	}
	public Double getPrice_sale() {
		return price_sale;
	}
	public void setPrice_sale(Double price_sale) {
		this.price_sale = price_sale;
	}
	public Double getSale() {
		return sale;
	}
	public void setSale(Double sale) {
		this.sale = sale;
	}
	@Override
	public String toString() {
		return "InfoProductsToTicketDTO [quantity=" + quantity
				+ ", name_short=" + name_short + ", price_sale=" + price_sale
				+ ", sale=" + sale + "]";
	}
	

}
