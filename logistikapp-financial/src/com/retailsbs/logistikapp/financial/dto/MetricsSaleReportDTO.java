package com.retailsbs.logistikapp.financial.dto;

public class MetricsSaleReportDTO {
	
	private String code;
	private String store;
	private String store_category;
	private Long noproducts;
	private Double sale;
	private Double decrease;
	private Double utility;
	private Long notrx;
	private Double ticketavg;
	private Long notravels;
	private Long checkins;
	private String driver;
	private String route;
	
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
	public String getStore_category() {
		return store_category;
	}
	public void setStore_category(String store_category) {
		this.store_category = store_category;
	}
	public Long getNoproducts() {
		return noproducts;
	}
	public void setNoproducts(Long noproducts) {
		this.noproducts = noproducts;
	}
	public Double getSale() {
		return sale;
	}
	public void setSale(Double sale) {
		this.sale = sale;
	}
	public Double getDecrease() {
		return decrease;
	}
	public void setDecrease(Double decrease) {
		this.decrease = decrease;
	}
	public Double getUtility() {
		return utility;
	}
	public void setUtility(Double utility) {
		this.utility = utility;
	}
	public Long getNotrx() {
		return notrx;
	}
	public void setNotrx(Long notrx) {
		this.notrx = notrx;
	}
	public Double getTicketavg() {
		return ticketavg;
	}
	public void setTicketavg(Double ticketavg) {
		this.ticketavg = ticketavg;
	}
	public Long getNotravels() {
		return notravels;
	}
	public void setNotravels(Long notravels) {
		this.notravels = notravels;
	}
	public Long getCheckins() {
		return checkins;
	}
	public void setCheckins(Long checkins) {
		this.checkins = checkins;
	}
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public String getRoute() {
		return route;
	}
	public void setRoute(String route) {
		this.route = route;
	}

}
