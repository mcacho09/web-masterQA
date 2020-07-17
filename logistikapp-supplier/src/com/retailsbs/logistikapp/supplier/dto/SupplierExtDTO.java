package com.retailsbs.logistikapp.supplier.dto;

import com.retailsbs.logistikapp.supplier.domain.Supplier;

public class SupplierExtDTO extends Supplier {
	
	private Long id_plan;
	private String plan_name;
	
	public Long getId_plan() {
		return id_plan;
	}
	public void setId_plan(Long id_plan) {
		this.id_plan = id_plan;
	}
	public String getPlan_name() {
		return plan_name;
	}
	public void setPlan_name(String plan_name) {
		this.plan_name = plan_name;
	}

}
