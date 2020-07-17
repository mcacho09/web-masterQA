package com.retailsbs.logistikapp.retail.dto;

import com.retailsbs.logistikapp.retail.domain.Store;

public class StoreByRouteDTO extends Store{

	private String color;
	private String shelf;

	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getShelf() {
		return shelf;
	}
	public void setShelf(String shelf) {
		this.shelf = shelf;
	}
	
	
	
}
