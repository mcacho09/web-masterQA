package com.retailsbs.logistikapp.retail.dto;

public class RetailAvaibleSearchCriteria {
	
	private Long id_retail;
	private String code;
	private String active;
	private Long id_supplier;

	public Long getId_retail() {
		return id_retail;
	}
	public void setId_retail(Long id_retail) {
		this.id_retail = id_retail;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public Long getId_supplier() {
		return id_supplier;
	}
	public void setId_supplier(Long id_supplier) {
		this.id_supplier = id_supplier;
	}

}
