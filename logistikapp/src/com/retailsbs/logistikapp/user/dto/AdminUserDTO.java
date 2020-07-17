package com.retailsbs.logistikapp.user.dto;

import com.retailsbs.logistikapp.user.domain.User;

public class AdminUserDTO extends User {

	private Long id_access;
	private Long id_supplier;
	private String supplier;

	public Long getId_access() {
		return id_access;
	}

	public void setId_access(Long id_access) {
		this.id_access = id_access;
	}

	public Long getId_supplier() {
		return id_supplier;
	}

	public void setId_supplier(Long id_supplier) {
		this.id_supplier = id_supplier;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

}
