package com.retailsbs.logistikapp.logistic.dto;

import java.util.Date;

public class TravelByIdStoreDTO {
	
	private Long id_travel;
	private String travel_name;
	private Date schedule;
	private Long id_user;
	private String chofer;
	private Long id_route;
	private String route_name;
	private String status;

	public Long getId_travel() {
		return id_travel;
	}
	public void setId_travel(Long id_travel) {
		this.id_travel = id_travel;
	}
	public String getTravel_name() {
		return travel_name;
	}
	public void setTravel_name(String travel_name) {
		this.travel_name = travel_name;
	}
	public Date getSchedule() {
		return schedule;
	}
	public void setSchedule(Date schedule) {
		this.schedule = schedule;
	}
	public Long getId_user() {
		return id_user;
	}
	public void setId_user(Long id_user) {
		this.id_user = id_user;
	}
	public String getChofer() {
		return chofer;
	}
	public void setChofer(String chofer) {
		this.chofer = chofer;
	}
	public Long getId_route() {
		return id_route;
	}
	public void setId_route(Long id_route) {
		this.id_route = id_route;
	}
	public String getRoute_name() {
		return route_name;
	}
	public void setRoute_name(String route_name) {
		this.route_name = route_name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
