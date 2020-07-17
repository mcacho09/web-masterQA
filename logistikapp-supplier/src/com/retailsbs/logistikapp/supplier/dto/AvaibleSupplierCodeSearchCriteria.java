package com.retailsbs.logistikapp.supplier.dto;

public class AvaibleSupplierCodeSearchCriteria {
	
	private String active;
	private String code;
	private Long id_supplier;

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
	public Long getId_supplier() {
		return id_supplier;
	}
	public void setId_supplier(Long id_supplier) {
		this.id_supplier = id_supplier;
	}

}
