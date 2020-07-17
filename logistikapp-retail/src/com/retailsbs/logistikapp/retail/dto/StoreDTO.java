package com.retailsbs.logistikapp.retail.dto;

import com.retailsbs.logistikapp.retail.domain.Store;

public class StoreDTO extends Store {

	private String category;
	private String state;
	private String city;
	private String avaible;
	private String color;
	private String route;
	private Long qty_actives;

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getAvaible() {
		return avaible;
	}

	public void setAvaible(String avaible) {
		this.avaible = avaible;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Long getQty_actives() {
		return qty_actives;
	}

	public void setQty_actives(Long qty_actives) {
		this.qty_actives = qty_actives;
	}

}
