package com.retailsbs.logistikapp.logistic.dto;

import java.util.Date;

public class TravelsToVisitedCriteria {
	
	private Long id_supplier;
	private Long id_user;
	private Date date;
	
	public Long getId_supplier() {
		return id_supplier;
	}
	public void setId_supplier(Long id_supplier) {
		this.id_supplier = id_supplier;
	}
	public Long getId_user() {
		return id_user;
	}
	public void setId_user(Long id_user) {
		this.id_user = id_user;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

}
