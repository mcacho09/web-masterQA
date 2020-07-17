package com.retailsbs.logistikapp.financial.dto;

import java.util.Date;

public class GetMetricsSaleDTO {
	
	private Long id_supplier;
	private Long id_user;
	private Date initDate;
	private Date finalDate;
	private Long[] ids;
	private Long[] idus;
	
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
	public Date getInitDate() {
		return initDate;
	}
	public void setInitDate(Date initDate) {
		this.initDate = initDate;
	}
	public Date getFinalDate() {
		return finalDate;
	}
	public void setFinalDate(Date finalDate) {
		this.finalDate = finalDate;
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
