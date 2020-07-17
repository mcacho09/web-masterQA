package com.retailsbs.logistikapp.logistic.dto;

import java.util.Date;

public class TravelAndStatusStr {
	
	private Long id_travel;
	private Long id_store;
	private String estado_travel;
	private String travel_name;
	private Date schedule;
	private Date checkin;
	private String status;
	private Long id_route;
	private String route_name;
	private Long id_user;
	private String user_name;
	private String outrange;
	private String comment;
	private String note;
	private Long id_waybill;
	private String image1;
	private String image2;
	private String image3;
	
	public Long getId_waybill() {
		return id_waybill;
	}
	public void setId_waybill(Long id_waybill) {
		this.id_waybill = id_waybill;
	}
	public String getImage1() {
		return image1;
	}
	public void setImage1(String image1) {
		this.image1 = image1;
	}
	public String getImage2() {
		return image2;
	}
	public void setImage2(String image2) {
		this.image2 = image2;
	}
	public String getImage3() {
		return image3;
	}
	public void setImage3(String image3) {
		this.image3 = image3;
	}
	public Long getId_travel() {
		return id_travel;
	}
	public void setId_travel(Long id_travel) {
		this.id_travel = id_travel;
	}
	public Long getId_store() {
		return id_store;
	}
	public void setId_store(Long id_store) {
		this.id_store = id_store;
	}
	public String getEstado_travel() {
		return estado_travel;
	}
	public void setEstado_travel(String estado_travel) {
		this.estado_travel = estado_travel;
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
	public Date getCheckin() {
		return checkin;
	}
	public void setCheckin(Date checkin) {
		this.checkin = checkin;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public Long getId_user() {
		return id_user;
	}
	public void setId_user(Long id_user) {
		this.id_user = id_user;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getOutrange() {
		return outrange;
	}
	public void setOutrange(String outrange) {
		this.outrange = outrange;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}

}
