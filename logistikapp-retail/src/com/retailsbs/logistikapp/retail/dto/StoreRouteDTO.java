package com.retailsbs.logistikapp.retail.dto;

import com.retailsbs.logistikapp.retail.domain.Store;

public class StoreRouteDTO extends Store {
	
	private String color;
	private String route;
	
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getRoute() {
		return route;
	}
	public void setRoute(String route) {
		this.route = route;
	}

}
