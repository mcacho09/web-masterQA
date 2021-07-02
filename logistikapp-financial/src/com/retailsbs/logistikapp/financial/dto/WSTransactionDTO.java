package com.retailsbs.logistikapp.financial.dto;

import java.util.Date;

public class WSTransactionDTO {
	
	private int id_order;
	private Date invoice;
	private String store;
	private Long trxnumber;
	private String driver;
	private Double sale;
	private Double sale_dev;
	private int devolutions;
	private int changes;
	private String status;
	private Double payment_part;
	
	public int getId_order() {
		return id_order;
	}
	public void setId_order(int id_order) {
		this.id_order = id_order;
	}
	public Date getInvoice() {
		return invoice;
	}
	public void setInvoice(Date invoice) {
		this.invoice = invoice;
	}
	public String getStore() {
		return store;
	}
	public void setStore(String store) {
		this.store = store;
	}
	public Long getTrxnumber() {
		return trxnumber;
	}
	public void setTrxnumber(Long trxnumber) {
		this.trxnumber = trxnumber;
	}
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public Double getSale() {
		return sale;
	}
	public void setSale(Double sale) {
		this.sale = sale;
	}
	public Double getSale_dev() {
		return sale_dev;
	}
	public void setSale_dev(Double sale_dev) {
		this.sale_dev = sale_dev;
	}
	public int getDevolutions() {
		return devolutions;
	}
	public void setDevolutions(int devolutions) {
		this.devolutions = devolutions;
	}
	public int getChanges() {
		return changes;
	}
	public void setChanges(int changes) {
		this.changes = changes;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Double getPayment_part() {
		return payment_part;
	}
	public void setPayment_part(Double payment_part) {
		this.payment_part = payment_part;
	}
}
