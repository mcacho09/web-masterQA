package com.retailsbs.logistikapp.financial.dto;

public class TopClientsDTO {
	
	private String store;
	private Double sales;
	private Long no_trx;
	private Double ticket_avg;
	private Double utility;
	
	public String getStore() {
		return store;
	}
	public void setStore(String store) {
		this.store = store;
	}
	public Double getSales() {
		return sales;
	}
	public void setSales(Double sales) {
		this.sales = sales;
	}
	public Long getNo_trx() {
		return no_trx;
	}
	public void setNo_trx(Long no_trx) {
		this.no_trx = no_trx;
	}
	public Double getTicket_avg() {
		return ticket_avg;
	}
	public void setTicket_avg(Double ticket_avg) {
		this.ticket_avg = ticket_avg;
	}
	public Double getUtility() {
		return utility;
	}
	public void setUtility(Double utility) {
		this.utility = utility;
	}

}
