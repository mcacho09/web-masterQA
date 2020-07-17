package com.retailsbs.logistikapp.retail.dto;

import java.util.List;

public class StoreSearchCriteria {
	
	private Long id_retail;
	private Long id_store;
	private String active;
	private Long id_route;
	private Long id_travel;
	public List<Long> idsrt;
	
	public List<Long> getIdsrt() {
		return idsrt;
	}
	public void setIdsrt(List<Long> idsrt) {
		this.idsrt = idsrt;
	}
	public Long getId_store() {
		return id_store;
	}
	public void setId_store(Long id_store) {
		this.id_store = id_store;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
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
	public Long getId_travel() {
		return id_travel;
	}
	public void setId_travel(Long id_travel) {
		this.id_travel = id_travel;
	}

}
