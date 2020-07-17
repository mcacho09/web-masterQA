package com.retailsbs.logistikapp.logistic.dto;

public class ProgressMetricsOperativesDTO {
	
	private String category;
	private Long total;
	private Long visited;
	private Long not_visited;
	private Double per_visited;
	private Double per_not_visited;
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public Long getVisited() {
		return visited;
	}
	public void setVisited(Long visited) {
		this.visited = visited;
	}
	public Long getNot_visited() {
		return not_visited;
	}
	public void setNot_visited(Long not_visited) {
		this.not_visited = not_visited;
	}
	public Double getPer_visited() {
		return per_visited;
	}
	public void setPer_visited(Double per_visited) {
		this.per_visited = per_visited;
	}
	public Double getPer_not_visited() {
		return per_not_visited;
	}
	public void setPer_not_visited(Double per_not_visited) {
		this.per_not_visited = per_not_visited;
	}

}
