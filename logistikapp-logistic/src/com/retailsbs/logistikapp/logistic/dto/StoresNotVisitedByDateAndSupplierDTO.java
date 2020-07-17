package com.retailsbs.logistikapp.logistic.dto;

public class StoresNotVisitedByDateAndSupplierDTO {
	
	private Long id_waybill;
	private Long id_store;
	private Long id_travel;
	private String travel;
	private String driver;
	private String store;
	private Double latitude;
	private Double longitude;
	private String code;
	
	public Long getId_waybill() {
		return id_waybill;
	}
	public void setId_waybill(Long id_waybill) {
		this.id_waybill = id_waybill;
	}
	public Long getId_store() {
		return id_store;
	}
	public void setId_store(Long id_store) {
		this.id_store = id_store;
	}
	public Long getId_travel() {
		return id_travel;
	}
	public void setId_travel(Long id_travel) {
		this.id_travel = id_travel;
	}
	public String getTravel() {
		return travel;
	}
	public void setTravel(String travel) {
		this.travel = travel;
	}
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public String getStore() {
		return store;
	}
	public void setStore(String store) {
		this.store = store;
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

}