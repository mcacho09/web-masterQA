package com.retailsbs.logistikapp.retail.dto;

public class StoreCallCenterDTO {

	private Long id_store;
	private String store_name;
	private Double latitude;
	private Double longitude;
	private String address1;
	private String address2;
	private Integer postal;
	private Long id_route;
	private String route_name;
	private String active;

	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public Long getId_store() {
		return id_store;
	}
	public void setId_store(Long id_store) {
		this.id_store = id_store;
	}
	public String getStore_name() {
		return store_name;
	}
	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public Integer getPostal() {
		return postal;
	}
	public void setPostal(Integer postal) {
		this.postal = postal;
	}
	public Long getId_route() {
		return id_route;
	}
	public void setId_route(Long id_route) {
		this.id_route = id_route;
	}
	public String getRoute_name() {
		return route_name;
	}
	public void setRoute_name(String route_name) {
		this.route_name = route_name;
	}
}
