package com.retailsbs.logistikapp.financial.dto;

import java.util.List;

import com.retailsbs.logistikapp.financial.domain.Almacen;

public class AlmacenAndProductsDTO {

	private Almacen almacen;
	private List<ProductAlmacenDTO> products;
	
	public Almacen getAlmacen() {
		return almacen;
	}
	public void setAlmacen(Almacen almacen) {
		this.almacen = almacen;
	}
	public List<ProductAlmacenDTO> getProducts() {
		return products;
	}
	public void setProducts(List<ProductAlmacenDTO> products) {
		this.products = products;
	}
	
}
