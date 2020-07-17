package com.retailsbs.logistikapp.financial.dto;

public class ExtCategoryAndNoStoresDTO extends CategoryAndNoStoresDTO {
	
	private Long total_visited;
	private Long total_not_visited;
	private Double per_total_visited;
	private Double per_total_not_visited;
	
	public Long getTotal_visited() {
		return total_visited;
	}
	public void setTotal_visited(Long total_visited) {
		this.total_visited = total_visited;
	}
	public Long getTotal_not_visited() {
		return total_not_visited;
	}
	public void setTotal_not_visited(Long total_not_visited) {
		this.total_not_visited = total_not_visited;
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
	
	@Override
	public String toString() {
		return "ExtCategoryAndNoStoresDTO [total_visited=" + total_visited
				+ ", total_not_visited=" + total_not_visited
				+ ", per_total_visited=" + per_total_visited
				+ ", per_total_not_visited=" + per_total_not_visited + "]";
	}

}
