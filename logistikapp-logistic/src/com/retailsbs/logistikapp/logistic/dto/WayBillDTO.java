package com.retailsbs.logistikapp.logistic.dto;

import com.retailsbs.logistikapp.logistic.domain.WayBill;

public class WayBillDTO extends WayBill {
	private String name;
	private Double latitude;
	private Double longitude;
	private String code;
	private String address1;
	private String address2;
	private Integer postal;
	private Long id_retail;
	private String travel_name;
	private String shelf;
	private Double lat_real;
	private Double lon_real;
	private Long map_range;
	private String outrange;
	private String city;
	private String state;
	private String color;
	private String route;
	private String email;

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

	public String getTravel_name() {
		return travel_name;
	}

	public void setTravel_name(String travel_name) {
		this.travel_name = travel_name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getPostal() {
		return postal;
	}

	public void setPostal(Integer postal) {
		this.postal = postal;
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

	public Long getId_retail() {
		return id_retail;
	}

	public void setId_retail(Long id_retail) {
		this.id_retail = id_retail;
	}

	public String getShelf() {
		return shelf;
	}

	public void setShelf(String shelf) {
		this.shelf = shelf;
	}

	public Double getLat_real() {
		return lat_real;
	}

	public void setLat_real(Double lat_real) {
		this.lat_real = lat_real;
	}

	public Double getLon_real() {
		return lon_real;
	}

	public void setLon_real(Double lon_real) {
		this.lon_real = lon_real;
	}

	public Long getMap_range() {
		return map_range;
	}

	public void setMap_range(Long map_range) {
		this.map_range = map_range;
	}

	public String getOutrange() {
		return outrange;
	}

	public void setOutrange(String outrange) {
		this.outrange = outrange;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
