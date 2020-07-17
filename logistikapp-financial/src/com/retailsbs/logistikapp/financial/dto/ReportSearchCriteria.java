package com.retailsbs.logistikapp.financial.dto;

import java.util.Date;
import java.util.List;


public class ReportSearchCriteria {

	private Date invoice;
	private Date invoice_fin;
	private Long id_store;
	private Long id_supplier;
	private List<String> status;
	private Long id_order;
	private String login;
	private Long id_user;
	private List<Long> ids;
	private List<Long> idus;
	
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
	public Long getId_store() {
		return id_store;
	}
	public void setId_store(Long id_store) {
		this.id_store = id_store;
	}
	public Long getId_supplier() {
		return id_supplier;
	}
	public void setId_supplier(Long id_supplier) {
		this.id_supplier = id_supplier;
	}
	public List<String> getStatus() {
		return status;
	}
	public void setStatus(List<String> status) {
		this.status = status;
	}
	public Long getId_order() {
		return id_order;
	}
	public void setId_order(Long id_order) {
		this.id_order = id_order;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public Long getId_user() {
		return id_user;
	}
	public void setId_user(Long id_user) {
		this.id_user = id_user;
	}
	public List<Long> getIds() {
		return ids;
	}
	public void setIds(List<Long> ids) {
		this.ids = ids;
	}
	public List<Long> getIdus() {
		return idus;
	}
	public void setIdus(List<Long> idus) {
		this.idus = idus;
	}
	
}
