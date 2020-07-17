package com.retailsbs.logistikapp.retail.dto;

public class GetCitySearchCriteria {

	private Integer lim_inf;
	private Integer lim_sup;
	private String active;
	private Long id_state;

	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public Long getId_state() {
		return id_state;
	}
	public void setId_state(Long id_state) {
		this.id_state = id_state;
	}
	public Integer getLim_inf() {
		return lim_inf;
	}
	public void setLim_inf(Integer lim_inf) {
		this.lim_inf = lim_inf;
	}
	public Integer getLim_sup() {
		return lim_sup;
	}
	public void setLim_sup(Integer lim_sup) {
		this.lim_sup = lim_sup;
	}
	
}
