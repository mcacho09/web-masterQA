package com.retailsbs.logistikapp.logistic.dto;

import java.util.Date;

public class ParameterReportPromotion {

	private Long id_retail;
	private Date fini;
	private Date ffin;
	public Long getId_retail() {
		return id_retail;
	}
	public void setId_retail(Long id_retail) {
		this.id_retail = id_retail;
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
	
	@Override
	public String toString() {
		return "ParameterReportPromotion [id_retail=" + id_retail + ", fini="
				+ fini + ", ffin=" + ffin + "]";
	}
	
}
