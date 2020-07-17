package com.retailsbs.logistikapp.retail.dto;

public class AvailableStoreByIdRetailIdRoute {
	
	private Long id_retail;
	private Long id_route;
	private Long id_supplier;

	public Long getId_retail() {
		return id_retail;
	}
	public void setId_retail(Long id_retail) {
		this.id_retail = id_retail;
	}
	public Long getId_route() {
		return id_route;
	}
	public void setId_route(Long id_route) {
		this.id_route = id_route;
	}
	public Long getId_supplier() {
		return id_supplier;
	}
	public void setId_supplier(Long id_supplier) {
		this.id_supplier = id_supplier;
	}

}
