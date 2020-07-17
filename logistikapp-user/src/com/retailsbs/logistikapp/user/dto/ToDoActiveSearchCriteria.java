package com.retailsbs.logistikapp.user.dto;

public class ToDoActiveSearchCriteria {
	
	private String active;
	private Integer limit;
	private Integer limit_char;

	public Integer getLimit_char() {
		return limit_char;
	}
	public void setLimit_char(Integer limit_char) {
		this.limit_char = limit_char;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}

}
