package com.retailsbs.logistikapp.financial.dto;

import java.util.Date;

public class NoStoreVisitedByIdStoreCategoryCriteria {
	
	private Long id_store_category;
	private Date fini;
	private Date ffin;
	
	public Long getId_store_category() {
		return id_store_category;
	}
	public void setId_store_category(Long id_store_category) {
		this.id_store_category = id_store_category;
	}
	public Date getFini() {
		return fini;
	}
	public void setFini(Date fini) {
		this.fini = fini;
	}
	public Date getFfin() {
		return ffin;
	}
	public void setFfin(Date ffin) {
		this.ffin = ffin;
	}

}
