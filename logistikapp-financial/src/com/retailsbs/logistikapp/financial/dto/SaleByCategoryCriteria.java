package com.retailsbs.logistikapp.financial.dto;

import java.util.Date;

public class SaleByCategoryCriteria {
	
	private Long id_supplier;
	private Date fini;
	private Date ffin;
	private Long id_user;
	private Long[] ids;
	private Long[] idus;
	
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
	public Long getId_user() {
		return id_user;
	}
	public void setId_user(Long id_user) {
		this.id_user = id_user;
	}
	public Long[] getIds() {
		return ids;
	}
	public void setIds(Long[] ids) {
		this.ids = ids;
	}
	public Long[] getIdus() {
		return idus;
	}
	public void setIdus(Long[] idus) {
		this.idus = idus;
	}

}
