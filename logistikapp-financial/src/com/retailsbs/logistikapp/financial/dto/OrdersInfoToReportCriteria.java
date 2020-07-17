package com.retailsbs.logistikapp.financial.dto;

import java.util.Date;
import java.util.List;

public class OrdersInfoToReportCriteria {
	
	private List<Long> ids;
	private Long id_supplier;
	private Date invoice;
	private Date invoice_fin;
	private Long id_user;
	
	public List<Long> getIds() {
		return ids;
	}
	public void setIds(List<Long> ids) {
		this.ids = ids;
	}
	public Long getId_supplier() {
		return id_supplier;
	}
	public void setId_supplier(Long id_supplier) {
		this.id_supplier = id_supplier;
	}
	public Date getInvoice() {
		return invoice;
	}
	public void setInvoice(Date invoice) {
		this.invoice = invoice;
	}
	public Date getInvoice_fin() {
		return invoice_fin;
	}
	public void setInvoice_fin(Date invoice_fin) {
		this.invoice_fin = invoice_fin;
	}
	public Long getId_user() {
		return id_user;
	}
	public void setId_user(Long id_user) {
		this.id_user = id_user;
	}

}
