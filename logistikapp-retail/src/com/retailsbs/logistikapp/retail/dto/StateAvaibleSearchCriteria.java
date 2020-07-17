package com.retailsbs.logistikapp.retail.dto;

public class StateAvaibleSearchCriteria {

	private String code;
	private Long id_state;
	private String active;

	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Long getId_state() {
		return id_state;
	}
	public void setId_state(Long id_state) {
		this.id_state = id_state;
	}
	
}
