package com.retailsbs.logistikapp.retail.dto;

public class AvailableStoreByIdStoreCategoryIdRoute {
	
	private Long idStorecategory;
	private Long idRoute;
	private Long idSupplier;

	public Long getIdStorecategory() {
		return idStorecategory;
	}
	public void setIdStorecategory(Long idStorecategory) {
		this.idStorecategory = idStorecategory;
	}
	public Long getIdRoute() {
		return idRoute;
	}
	public void setIdRoute(Long idRoute) {
		this.idRoute = idRoute;
	}
	public Long getIdSupplier() {
		return idSupplier;
	}
	public void setIdSupplier(Long idSupplier) {
		this.idSupplier = idSupplier;
	}

}
