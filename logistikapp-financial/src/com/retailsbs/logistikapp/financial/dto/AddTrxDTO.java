package com.retailsbs.logistikapp.financial.dto;

import java.util.List;

import com.retailsbs.logistikapp.financial.domain.Order;

public class AddTrxDTO extends Order {
	
	private List<TrxDTO> products_VTA; 
	private List<TrxDTO> products_CHG;
	private List<TrxDTO> products_DEV;
	
	public List<TrxDTO> getProducts_VTA() {
		return products_VTA;
	}
	public void setProducts_VTA(List<TrxDTO> products_VTA) {
		this.products_VTA = products_VTA;
	}
	public List<TrxDTO> getProducts_CHG() {
		return products_CHG;
	}
	public void setProducts_CHG(List<TrxDTO> products_CHG) {
		this.products_CHG = products_CHG;
	}
	public List<TrxDTO> getProducts_DEV() {
		return products_DEV;
	}
	public void setProducts_DEV(List<TrxDTO> products_DEV) {
		this.products_DEV = products_DEV;
	}
	
	
	
}
