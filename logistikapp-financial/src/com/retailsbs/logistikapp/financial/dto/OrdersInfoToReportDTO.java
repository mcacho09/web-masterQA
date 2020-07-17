package com.retailsbs.logistikapp.financial.dto;

import java.util.Date;
import java.util.List;

public class OrdersInfoToReportDTO {
	
	private Long id_order;
	private Date invoice;
	private String code;
	private String store;
	private String username;
	private Long no_trx;
	private Double total;
	private String status;
	private List<Double> prices;
	
	public Long getId_order() {
		return id_order;
	}
	public void setId_order(Long id_order) {
		this.id_order = id_order;
	}
	public Date getInvoice() {
		return invoice;
	}
	public void setInvoice(Date invoice) {
		this.invoice = invoice;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getStore() {
		return store;
	}
	public void setStore(String store) {
		this.store = store;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Long getNo_trx() {
		return no_trx;
	}
	public void setNo_trx(Long no_trx) {
		this.no_trx = no_trx;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<Double> getPrices() {
		return prices;
	}
	public void setPrices(List<Double> prices) {
		this.prices = prices;
	}

}
