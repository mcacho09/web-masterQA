package com.retailsbs.logistikapp.financial.dto;

public class UpdateOrderAndRecoverToAlmacenDTO {
	
	private UpdOrder updOrder;
	private Long id_user;
	private Long id_supplier;
	
	public UpdOrder getUpdOrder() {
		return updOrder;
	}
	public void setUpdOrder(UpdOrder updOrder) {
		this.updOrder = updOrder;
	}
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

}
