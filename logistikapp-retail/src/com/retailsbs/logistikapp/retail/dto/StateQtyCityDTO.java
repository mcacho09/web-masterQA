package com.retailsbs.logistikapp.retail.dto;

import com.retailsbs.logistikapp.retail.domain.State;

public class StateQtyCityDTO extends State {

	private Integer cities;

	public Integer getCities() {
		return cities;
	}

	public void setCities(Integer cities) {
		this.cities = cities;
	}
	
}
