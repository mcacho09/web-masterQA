package com.retailsbs.logistikapp.logistic.dto;

public class AviableCodeRouteSearchCriteria {

	private String name;
	private String code;
	private Long id_route;
	private String status;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Long getId_route() {
		return id_route;
	}
	public void setId_route(Long id_route) {
		this.id_route = id_route;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
