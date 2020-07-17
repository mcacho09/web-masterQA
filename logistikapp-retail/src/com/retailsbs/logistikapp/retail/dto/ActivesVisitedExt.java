package com.retailsbs.logistikapp.retail.dto;

import com.retailsbs.logistikapp.retail.domain.ActivesVisited;

public class ActivesVisitedExt extends ActivesVisited {
	
	public String store;
	public Double store_lat;
	public Double store_lng;

	public String getStore() {
		return store;
	}

	public void setStore(String store) {
		this.store = store;
	}

	public Double getStore_lat() {
		return store_lat;
	}

	public void setStore_lat(Double store_lat) {
		this.store_lat = store_lat;
	}

	public Double getStore_lng() {
		return store_lng;
	}

	public void setStore_lng(Double store_lng) {
		this.store_lng = store_lng;
	}

}
