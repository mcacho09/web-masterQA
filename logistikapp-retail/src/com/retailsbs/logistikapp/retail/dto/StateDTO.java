package com.retailsbs.logistikapp.retail.dto;

import com.retailsbs.logistikapp.retail.domain.State;

public class StateDTO extends State{
	
	private String country_name;

	public String getCountry_name() {
		return country_name;
	}

	public void setCountry_name(String country_name) {
		this.country_name = country_name;
	}

}
