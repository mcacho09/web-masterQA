package com.retailsbs.logistikapp.web.dto;

import java.util.Date;

/**
 * Objeto para mapear SQL
 * @author JorgeSilva
 * @since 16/10/2016
 */
public class TrxProductDTO {

	private Long id_order;
	private Date invoice;
	private Integer numtrx;
	private Long id_store;
	private String storeName;
	private String storeCode;
	private Long id_user;
	private String userName;
	private String typetrx;
	private Long id_product;
	private Double price;
	private Double cost;
	private Integer quantity;
	
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
	public Integer getNumtrx() {
		return numtrx;
	}
	public void setNumtrx(Integer numtrx) {
		this.numtrx = numtrx;
	}
	public Long getId_store() {
		return id_store;
	}
	public void setId_store(Long id_store) {
		this.id_store = id_store;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getStoreCode() {
		return storeCode;
	}
	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}
	public Long getId_user() {
		return id_user;
	}
	public void setId_user(Long id_user) {
		this.id_user = id_user;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getTypetrx() {
		return typetrx;
	}
	public void setTypetrx(String typetrx) {
		this.typetrx = typetrx;
	}
	public Long getId_product() {
		return id_product;
	}
	public void setId_product(Long id_product) {
		this.id_product = id_product;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getCost() {
		return cost;
	}
	public void setCost(Double cost) {
		this.cost = cost;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
}
