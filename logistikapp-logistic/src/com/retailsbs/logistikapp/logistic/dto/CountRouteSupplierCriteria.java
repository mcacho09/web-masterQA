package com.retailsbs.logistikapp.logistic.dto;

import java.util.List;

public class CountRouteSupplierCriteria {

	private Long id_supplier;
	private List<String> status;

	public Long getId_supplier() {
		return id_supplier;
	}

	public void setId_supplier(Long id_supplier) {
		this.id_supplier = id_supplier;
	}

	public List<String> getStatus() {
		return status;
	}

	public void setStatus(List<String> status) {
		this.status = status;
	}

}
