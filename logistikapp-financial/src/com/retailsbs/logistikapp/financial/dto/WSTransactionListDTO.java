package com.retailsbs.logistikapp.financial.dto;

import java.util.Date;

public class WSTransactionListDTO {

	private Long id_supplier;
	private Date invoice;
	private Long id_user;
	private Long id_order;
	private Long limit;
	private Long id_store;
	
	public Long getId_user() {
		return id_user;
	}
	public void setId_user(Long id_user) {
		this.id_user = id_user;
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
	public Long getId_order() {
		return id_order;
	}
	public void setId_order(Long id_order) {
		this.id_order = id_order;
	}
	public Long getLimit() {
		return limit;
	}
	public void setLimit(Long limit) {
		this.limit = limit;
	}
	public Long getId_store() {
		return id_store;
	}
	public void setId_store(Long id_store) {
		this.id_store = id_store;
	}
	
}
