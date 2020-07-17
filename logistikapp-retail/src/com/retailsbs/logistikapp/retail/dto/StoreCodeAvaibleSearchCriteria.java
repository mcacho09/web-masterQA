package com.retailsbs.logistikapp.retail.dto;

public class StoreCodeAvaibleSearchCriteria {
	
	private String active;
	private String code;
	private Long id_store;
	private Long id_retail;

	public Long getId_retail() {
		return id_retail;
	}
	public void setId_retail(Long id_retail) {
		this.id_retail = id_retail;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Long getId_store() {
		return id_store;
	}
	public void setId_store(Long id_store) {
		this.id_store = id_store;
	}

}
