package com.retailsbs.logistikapp.logistic.dto;

import java.util.Date;

public class AvailableUserScheduleSearchCriteria {
	private Long id_user;
	private Date schedule;
	public Date getSchedule() {
		return schedule;
	}
	public void setSchedule(Date schedule) {
		this.schedule = schedule;
	}
	public Long getId_user() {
		return id_user;
	}
	public void setId_user(Long id_user) {
		this.id_user = id_user;
	}
}
