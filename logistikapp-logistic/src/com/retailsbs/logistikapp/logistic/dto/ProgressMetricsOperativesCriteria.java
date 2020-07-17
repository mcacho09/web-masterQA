package com.retailsbs.logistikapp.logistic.dto;

import java.util.Date;

public class ProgressMetricsOperativesCriteria {
	
	private Long id_supplier;
	private Long id_user;
	private Date fini;
	private Date ffin;
	
	public Long getId_supplier() {
		return id_supplier;
	}
	public void setId_supplier(Long id_supplier) {
		this.id_supplier = id_supplier;
	}
	public Long getId_user() {
		return id_user;
	}
	public void setId_user(Long id_user) {
		this.id_user = id_user;
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
