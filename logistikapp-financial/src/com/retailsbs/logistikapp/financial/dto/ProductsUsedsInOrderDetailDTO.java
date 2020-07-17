package com.retailsbs.logistikapp.financial.dto;

public class ProductsUsedsInOrderDetailDTO {

	private Long id_supplier;
	private Long[] ids;
	
	public Long getId_supplier() {
		return id_supplier;
	}
	public void setId_supplier(Long id_supplier) {
		this.id_supplier = id_supplier;
	}
	public Long[] getIds() {
		return ids;
	}
	public void setIds(Long[] ids) {
		this.ids = ids;
	}
	
}
