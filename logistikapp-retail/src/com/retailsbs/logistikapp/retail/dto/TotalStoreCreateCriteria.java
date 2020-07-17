package com.retailsbs.logistikapp.retail.dto;

import java.util.Date;

public class TotalStoreCreateCriteria {
	
	private Date schedule;
	private Date fini;
	private Date ffin;
	private Boolean check = false;
	private Boolean noCheck = false;
	private Long id_supplier;

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
	public Long getId_supplier() {
		return id_supplier;
	}
	public void setId_supplier(Long id_supplier) {
		this.id_supplier = id_supplier;
	}
	public Date getSchedule() {
		return schedule;
	}
	public void setSchedule(Date schedule) {
		this.schedule = schedule;
	}
	public Boolean getCheck() {
		return check;
	}
	public void setCheck(Boolean check) {
		this.check = check;
	}
	public Boolean getNoCheck() {
		return noCheck;
	}
	public void setNoCheck(Boolean noCheck) {
		this.noCheck = noCheck;
	}
}
