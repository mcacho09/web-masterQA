package com.retailsbs.logistikapp.financial.dto;

public class MetricsSaleDTO {
	
	private Double sale;
	private Integer productsQty;
	private Double decrease;
	private Double utility;
	private Long notrx;
	private Double ticketavg;
	
	public Double getSale() {
		return sale;
	}
	public void setSale(Double sale) {
		this.sale = sale;
	}
	public Integer getProductsQty() {
		return productsQty;
	}
	public void setProductsQty(Integer productsQty) {
		this.productsQty = productsQty;
	}
	public Double getDecrease() {
		return decrease;
	}
	public void setDecrease(Double decrease) {
		this.decrease = decrease;
	}
	public Double getUtility() {
		return utility;
	}
	public void setUtility(Double utility) {
		this.utility = utility;
	}
	public Long getNotrx() {
		return notrx;
	}
	public void setNotrx(Long notrx) {
		this.notrx = notrx;
	}
	public Double getTicketavg() {
		return ticketavg;
	}
	public void setTicketavg(Double ticketavg) {
		this.ticketavg = ticketavg;
	}

}
