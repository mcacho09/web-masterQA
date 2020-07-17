package com.retailsbs.logistikapp.financial.dto;

import java.util.List;

public class PerTotalVisitedDTO {
	
	private Long total_stores;
	private Long total_visited;
	private Long total_no_visited;
	private Double per_total_visited;
	private Double per_total_not_visited;
	private List<ExtCategoryAndNoStoresDTO> list;
	public Long getTotal_stores() {
		return total_stores;
	}
	public void setTotal_stores(Long total_stores) {
		this.total_stores = total_stores;
	}
	public Long getTotal_visited() {
		return total_visited;
	}
	public void setTotal_visited(Long total_visited) {
		this.total_visited = total_visited;
	}
	public Long getTotal_no_visited() {
		return total_no_visited;
	}
	public void setTotal_no_visited(Long total_no_visited) {
		this.total_no_visited = total_no_visited;
	}
	public Double getPer_total_visited() {
		return per_total_visited;
	}
	public void setPer_total_visited(Double per_total_visited) {
		this.per_total_visited = per_total_visited;
	}
	public Double getPer_total_not_visited() {
		return per_total_not_visited;
	}
	public void setPer_total_not_visited(Double per_total_not_visited) {
		this.per_total_not_visited = per_total_not_visited;
	}
	public List<ExtCategoryAndNoStoresDTO> getList() {
		return list;
	}
	public void setList(List<ExtCategoryAndNoStoresDTO> list) {
		this.list = list;
	}
	@Override
	public String toString() {
		return "PerTotalVisitedDTO [total_stores=" + total_stores
				+ ", total_visited=" + total_visited + ", total_no_visited="
				+ total_no_visited + ", per_total_visited=" + per_total_visited
				+ ", per_total_not_visited=" + per_total_not_visited
				+ ", list=" + list + "]";
	}

}
