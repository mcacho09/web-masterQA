package com.retailsbs.logistikapp.retail.dto;

public class StoreByRetailDTO {
	
	private Long id_retail;
	private String retail_name;
	private Integer tot_store;

	public Long getId_retail() {
		return id_retail;
	}
	public void setId_retail(Long id_retail) {
		this.id_retail = id_retail;
	}
	public String getRetail_name() {
		return retail_name;
	}
	public void setRetail_name(String retail_name) {
		this.retail_name = retail_name;
	}
	public Integer getTot_store() {
		return tot_store;
	}
	public void setTot_store(Integer tot_store) {
		this.tot_store = tot_store;
	}
	
}
