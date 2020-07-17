package com.retailsbs.logistikapp.retail.dto;

public class GetStateSearchCriteria {
	
	private Integer lim_inf;
	private Integer lim_sup;
	private Long id_country;
	private String active;

	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public Long getId_country() {
		return id_country;
	}
	public void setId_country(Long id_country) {
		this.id_country = id_country;
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
