package com.retailsbs.logistikapp.retail.dto;

public class StoreInfoDTO {

	private Long id_store;
	private String store_code;
	private String store_name;
	private Double latitude;
	private Double longitude;
	private String address1;
	private String address2;
	private Long id_city;
	private String city_name;
	private Long id_retail;
	private String retail_name;
	private Long id_store_category;
	private String store_category_name;
	private String shelf;
	private String email;

	public String getShelf() {
		return shelf;
	}

	public void setShelf(String shelf) {
		this.shelf = shelf;
	}

	public Long getId_store() {
		return id_store;
	}

	public void setId_store(Long id_store) {
		this.id_store = id_store;
	}

	public Long getId_retail() {
		return id_retail;
	}

	public void setId_retail(Long id_retail) {
		this.id_retail = id_retail;
	}

	public Long getId_city() {
		return id_city;
	}

	public void setId_city(Long id_city) {
		this.id_city = id_city;
	}

	public String getStore_code() {
		return store_code;
	}

	public void setStore_code(String store_code) {
		this.store_code = store_code;
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

	public String getCity_name() {
		return city_name;
	}

	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}

	public String getRetail_name() {
		return retail_name;
	}

	public void setRetail_name(String retail_name) {
		this.retail_name = retail_name;
	}

	public Long getId_store_category() {
		return id_store_category;
	}

	public void setId_store_category(Long id_store_category) {
		this.id_store_category = id_store_category;
	}

	public String getStore_category_name() {
		return store_category_name;
	}

	public void setStore_category_name(String store_category_name) {
		this.store_category_name = store_category_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
