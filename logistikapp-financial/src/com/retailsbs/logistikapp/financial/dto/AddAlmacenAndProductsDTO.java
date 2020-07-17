package com.retailsbs.logistikapp.financial.dto;

import java.util.List;

import com.retailsbs.logistikapp.financial.domain.Almacen;
import com.retailsbs.logistikapp.financial.domain.ProductAlmacen;

public class AddAlmacenAndProductsDTO {

	private Almacen almacen;
	private List<ProductAlmacen> products;
	
	public Almacen getAlmacen() {
		return almacen;
	}
	public void setAlmacen(Almacen almacen) {
		this.almacen = almacen;
	}
	public List<ProductAlmacen> getProducts() {
		return products;
	}
	public void setProducts(List<ProductAlmacen> products) {
		this.products = products;
	}
	
}
