package com.retailsbs.logistikapp.domain.dto;

import com.retailsbs.logistikapp.domain.domain.DomainContent;

public class DomainContentDTO extends DomainContent{

	private String domain_code;

	public String getDomain_code() {
		return domain_code;
	}

	public void setDomain_code(String domain_code) {
		this.domain_code = domain_code;
	}
	
}
