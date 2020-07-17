package com.retailsbs.logistikapp.retail.dto;

public class CountryAvaibleSearchCriteria {
	
	private String active;
	private String code;
	private Long id_country;

	public Long getId_country() {
		return id_country;
	}
	public void setId_country(Long id_country) {
		this.id_country = id_country;
	}
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

}
