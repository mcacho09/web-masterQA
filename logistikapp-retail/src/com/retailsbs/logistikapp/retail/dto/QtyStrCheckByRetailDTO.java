package com.retailsbs.logistikapp.retail.dto;

public class QtyStrCheckByRetailDTO {

	private String name;
	private int qty_tot;
	private int qty_chec;
	private Long id_retail;
	private Double porcent;

	public Double getPorcent() {
		return porcent;
	}
	public void setPorcent(Double porcent) {
		this.porcent = porcent;
	}
	public Long getId_retail() {
		return id_retail;
	}
	public void setId_retail(Long id_retail) {
		this.id_retail = id_retail;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getQty_tot() {
		return qty_tot;
	}
	public void setQty_tot(int qty_tot) {
		this.qty_tot = qty_tot;
	}
	public int getQty_chec() {
		return qty_chec;
	}
	public void setQty_chec(int qty_chec) {
		this.qty_chec = qty_chec;
	}
	
}
