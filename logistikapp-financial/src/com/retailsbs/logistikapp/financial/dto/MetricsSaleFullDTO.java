package com.retailsbs.logistikapp.financial.dto;

import java.util.Date;

public class MetricsSaleFullDTO {
	
	private Double sale;
	private Double decrease;
	private Double utility;
	private Double salecost;
	private Date delivery;
	
	public Double getSale() {
		return sale;
	}
	public void setSale(Double sale) {
		this.sale = sale;
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
	public Double getSalecost() {
		return salecost;
	}
	public void setSalecost(Double salecost) {
		this.salecost = salecost;
	}
	public Date getDelivery() {
		return delivery;
	}
	public void setDelivery(Date delivery) {
		this.delivery = delivery;
	}

}
