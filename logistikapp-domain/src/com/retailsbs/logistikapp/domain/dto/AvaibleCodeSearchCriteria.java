package com.retailsbs.logistikapp.domain.dto;

public class AvaibleCodeSearchCriteria {
	
	private String active;
	private String code;
	private Long id_domain_content;
	private Long id_domain;
	
	public Long getId_domain() {
		return id_domain;
	}
	public void setId_domain(Long id_domain) {
		this.id_domain = id_domain;
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
	public Long getId_domain_content() {
		return id_domain_content;
	}
	public void setId_domain_content(Long id_domain_content) {
		this.id_domain_content = id_domain_content;
	}

}
