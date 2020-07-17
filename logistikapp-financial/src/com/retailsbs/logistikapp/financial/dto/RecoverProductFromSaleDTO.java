package com.retailsbs.logistikapp.financial.dto;

public class RecoverProductFromSaleDTO {

	private Long id_almacen;
	private Long id_order;
	private String operator;

	public Long getId_almacen() {
		return id_almacen;
	}
	public void setId_almacen(Long id_almacen) {
		this.id_almacen = id_almacen;
	}
	public Long getId_order() {
		return id_order;
	}
	public void setId_order(Long id_order) {
		this.id_order = id_order;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	
}
