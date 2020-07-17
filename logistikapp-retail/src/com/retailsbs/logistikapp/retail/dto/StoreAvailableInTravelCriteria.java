package com.retailsbs.logistikapp.retail.dto;

public class StoreAvailableInTravelCriteria {
	
	private Long id_travel;
	private Long id_supplier;
	private Long id_route;
	private String store_name;
	
	
	public Long getId_travel() {
		return id_travel;
	}
	public void setId_travel(Long id_travel) {
		this.id_travel = id_travel;
	}
	public Long getId_supplier() {
		return id_supplier;
	}
	public void setId_supplier(Long id_supplier) {
		this.id_supplier = id_supplier;
	}
	public Long getId_route() {
		return id_route;
	}
	public void setId_route(Long id_route) {
		this.id_route = id_route;
	}
	public String getStore_name() {
		return store_name;
	}
	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}
	
	
}
