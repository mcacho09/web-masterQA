package com.retailsbs.logistikapp.retail.dto;

import com.retailsbs.logistikapp.retail.domain.City;

public class CityDTO extends City{
	
	private String state_name;

	public String getState_name() {
		return state_name;
	}

	public void setState_name(String state_name) {
		this.state_name = state_name;
	}

}
