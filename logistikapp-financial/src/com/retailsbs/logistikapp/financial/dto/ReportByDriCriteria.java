package com.retailsbs.logistikapp.financial.dto;

import java.util.Date;

public class ReportByDriCriteria {

	private Long id_supplier;
	private Date fini;
	private Date ffin;
	
	public Long getId_supplier() {
		return id_supplier;
	}
	public void setId_supplier(Long id_supplier) {
		this.id_supplier = id_supplier;
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
