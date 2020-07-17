package com.retailsbs.logistikapp.domain.dto;

public class DomainAvaibleSearchCriteria {

	private String code;
	private String name;
	private String active;
	private Long id_domain;
	
	public String getActive() {
		return active;
	}
	public Long getId_domain() {
		return id_domain;
	}
	public void setId_domain(Long id_domain) {
		this.id_domain = id_domain;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
