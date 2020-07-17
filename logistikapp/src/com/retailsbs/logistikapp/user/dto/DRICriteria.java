package com.retailsbs.logistikapp.user.dto;

import java.util.Date;

public class DRICriteria {
	
	private Long id_supplier;
	private Date date;
	
	public Long getId_supplier() {
		return id_supplier;
	}
	public void setId_supplier(Long id_supplier) {
		this.id_supplier = id_supplier;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
}
