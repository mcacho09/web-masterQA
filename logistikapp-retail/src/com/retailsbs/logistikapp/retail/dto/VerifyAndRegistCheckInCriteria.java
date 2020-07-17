package com.retailsbs.logistikapp.retail.dto;

import java.util.Date;

public class VerifyAndRegistCheckInCriteria {
	
	private Long id_store;
	private Date date;
	
	public Long getId_store() {
		return id_store;
	}
	public void setId_store(Long id_store) {
		this.id_store = id_store;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

}
