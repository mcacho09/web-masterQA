package com.retailsbs.logistikapp.financial.dto;

import com.retailsbs.logistikapp.financial.domain.Order;

public class ReportDTO extends Order{
	
	private String name; 
	private String username;
	private String typetrx;
	private int trx_num;
	private Long qty_vta;
	private Double qty_vta_sug;
	private Double qty_vta_dev_sug;
	private Long qty_chg;
	private Long qty_dev;
	private Long id_product;
	
	//Parametro para filtrar por cliente 
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	//Parametro para saber cual es el vendedor
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	//Se obtiene el tipo de la transacci�n
	public String getTypetrx() {
		return typetrx;
	}
	public void setTypetrx(String typetrx) {
		this.typetrx = typetrx;
	}
	//Se obtiene el numero de ticket
	public int getTrx_num() {
		return trx_num;
	}
	//Se obtiene la candiad de productos vendidos 
	public Long getQty_vta() {
		return qty_vta;
	}
	public void setQty_vta(Long qty_vta) {
		this.qty_vta = qty_vta;
	}
	//Se obtiene el monto total de las ventas 
	public Double getQty_vta_sug() {
		return qty_vta_sug;
	}
	public void setQty_vta_sug(Double qty_vta_sug) {
		this.qty_vta_sug = qty_vta_sug;
	}
	public Double getQty_vta_dev_sug() {
		return qty_vta_dev_sug;
	}
	public void setQty_vta_dev_sug(Double qty_vta_dev_sug) {
		this.qty_vta_dev_sug = qty_vta_dev_sug;
	}
	public void setTrx_num(int trx_num) {
		this.trx_num = trx_num;
	}
	//Se obtiene la cantidad de productos de cambios
	public Long getQty_chg() {
		return qty_chg;
	}
	public void setQty_chg(Long qty_chg) {
		this.qty_chg = qty_chg;
	}
	//Se obtiene la cantidad de productos de devoluciones
	public Long getQty_dev() {
		return qty_dev;
	}
	public void setQty_dev(Long qty_dev) {
		this.qty_dev = qty_dev;
	}
	//Se obtiene id del producto
	public Long getId_product() {
		return id_product;
	}
	public void setId_product(Long id_product) {
		this.id_product = id_product;
	}
	
}
