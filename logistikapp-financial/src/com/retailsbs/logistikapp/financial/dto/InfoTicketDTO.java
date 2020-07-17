package com.retailsbs.logistikapp.financial.dto;

public class InfoTicketDTO {
	
	private String supplier;
	private String store;
	private String retail;
	private String date;
	private String hour;
	private Long trx_num;
	private String status;
	private String seller;
	private String store_email;
	private String seller_email;
	private String retail_email;
	
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	public String getStore() {
		return store;
	}
	public void setStore(String store) {
		this.store = store;
	}
	public String getRetail() {
		return retail;
	}
	public void setRetail(String retail) {
		this.retail = retail;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getHour() {
		return hour;
	}
	public void setHour(String hour) {
		this.hour = hour;
	}
	public Long getTrx_num() {
		return trx_num;
	}
	public void setTrx_num(Long trx_num) {
		this.trx_num = trx_num;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSeller() {
		return seller;
	}
	public void setSeller(String seller) {
		this.seller = seller;
	}
	public String getStore_email() {
		return store_email;
	}
	public void setStore_email(String store_email) {
		this.store_email = store_email;
	}
	public String getSeller_email() {
		return seller_email;
	}
	public void setSeller_email(String seller_email) {
		this.seller_email = seller_email;
	}
	public String getRetail_email() {
		return retail_email;
	}
	public void setRetail_email(String retail_email) {
		this.retail_email = retail_email;
	}
	@Override
	public String toString() {
		return "InfoTicketDTO [supplier=" + supplier + ", store=" + store
				+ ", retail=" + retail + ", date=" + date + ", hour=" + hour
				+ ", trx_num=" + trx_num + ", status=" + status + ", seller="
				+ seller + ", store_email=" + store_email + ", seller_email="
				+ seller_email + ", retail_email=" + retail_email + "]";
	}

}
