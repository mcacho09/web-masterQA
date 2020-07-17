package com.retailsbs.logistikapp.financial.dto;

import java.util.List;

public class AlmacenInfoAndProductsDTO {
	
	private AlmacenInfoDTO almacen;
	private List<ProductAlmacenDTO> products;
	
	public AlmacenInfoDTO getAlmacen() {
		return almacen;
	}
	public void setAlmacen(AlmacenInfoDTO almacen) {
		this.almacen = almacen;
	}
	public List<ProductAlmacenDTO> getProducts() {
		return products;
	}
	public void setProducts(List<ProductAlmacenDTO> products) {
		this.products = products;
	}
	
}
