package com.retailsbs.logistikapp.logistic.dto;

public class TrvStatusStrSearchCriteria {
	
	private Long id_supplier;
	private Long id_store;
	private int lim_sup;
	private int lim_inf;
	
	public Long getId_supplier() {
		return id_supplier;
	}
	public void setId_supplier(Long id_supplier) {
		this.id_supplier = id_supplier;
	}
	public Long getId_store() {
		return id_store;
	}
	public void setId_store(Long id_store) {
		this.id_store = id_store;
	}
	public int getLim_sup() {
		return lim_sup;
	}
	public void setLim_sup(int lim_sup) {
		this.lim_sup = lim_sup;
	}
	public int getLim_inf() {
		return lim_inf;
	}
	public void setLim_inf(int lim_inf) {
		this.lim_inf = lim_inf;
	}

}
