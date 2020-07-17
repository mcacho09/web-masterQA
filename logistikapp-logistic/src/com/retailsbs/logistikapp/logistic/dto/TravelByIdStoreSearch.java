package com.retailsbs.logistikapp.logistic.dto;

import java.util.Date;

public class TravelByIdStoreSearch {

	private Long id_store;
	private Date schedule;

	public Long getId_store() {
		return id_store;
	}
	public void setId_store(Long id_store) {
		this.id_store = id_store;
	}
	public Date getSchedule() {
		return schedule;
	}
	public void setSchedule(Date schedule) {
		this.schedule = schedule;
	}
}
