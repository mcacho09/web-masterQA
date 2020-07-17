package com.retailsbs.logistikapp.retail.dto;

import com.retailsbs.logistikapp.retail.domain.Store;

public class AvailableStoreDTO extends Store{
	
	private Long idRetail;
	private Long idStorecategory;

	public Long getIdRetail() {
		return idRetail;
	}
	public void setIdRetail(Long idRetail) {
		this.idRetail = idRetail;
	}
	public Long getIdStorecategory() {
		return idStorecategory;
	}
	public void setIdStorecategory(Long idStorecategory) {
		this.idStorecategory = idStorecategory;
	}

}
