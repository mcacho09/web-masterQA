package com.retailsbs.logistikapp.financial.dto;

public class AlmacenInfoDTO {

	private Long id_almacen;
	private String code;
	private String username;
	private String retail;
	private Long qty_pieces;
	private Long qty_package;
	private Long total_pieces;
	
	public Long getId_almacen() {
		return id_almacen;
	}
	public void setId_almacen(Long id_almacen) {
		this.id_almacen = id_almacen;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRetail() {
		return retail;
	}
	public void setRetail(String retail) {
		this.retail = retail;
	}
	public Long getQty_pieces() {
		return qty_pieces;
	}
	public void setQty_pieces(Long qty_pieces) {
		this.qty_pieces = qty_pieces;
	}
	public Long getQty_package() {
		return qty_package;
	}
	public void setQty_package(Long qty_package) {
		this.qty_package = qty_package;
	}
	public Long getTotal_pieces() {
		return total_pieces;
	}
	public void setTotal_pieces(Long total_pieces) {
		this.total_pieces = total_pieces;
	}
	
}
