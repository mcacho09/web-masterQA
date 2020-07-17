package com.retailsbs.logistikapp.logistic.dto;

import java.util.Date;

public class TravelDTO {

	private Long id_travel;
	private String travel;
	private Long id_user;
	private String username;
	private String status;
	private Date created;
	private Date schedule;
	private Date started;
	private Date finished;
	private Long id_route;
	private String route;
	private int qtystore;
	private int qtycheckin;
	private int qtynotcheckin;
	private int qtycomment;
	private Double km;
	private Double time;
	private int qtyimg1;
	private int qtyimg2;
	private int qtyimg3;

	
    public Double getKm() {
		return km;
	}

	public void setKm(Double km) {
		this.km = km;
	}

	public Double getTime() {
		return time;
	}

	public void setTime(Double time) {
		this.time = time;
	}

	public Long getId_travel() {
		return id_travel;
	}

	public void setId_travel(Long id_travel) {
		this.id_travel = id_travel;
	}

	public String getTravel() {
		return travel;
	}

	public void setTravel(String travel) {
		this.travel = travel;
	}

	public Long getId_user() {
		return id_user;
	}

	public void setId_user(Long id_user) {
		this.id_user = id_user;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getSchedule() {
		return schedule;
	}

	public void setSchedule(Date schedule) {
		this.schedule = schedule;
	}

	public Date getStarted() {
		return started;
	}

	public void setStarted(Date started) {
		this.started = started;
	}

	public Date getFinished() {
		return finished;
	}

	public void setFinished(Date finished) {
		this.finished = finished;
	}

	public Long getId_route() {
		return id_route;
	}

	public void setId_route(Long id_route) {
		this.id_route = id_route;
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

	public int getQtystore() {
		return qtystore;
	}

	public void setQtystore(int qtystore) {
		this.qtystore = qtystore;
	}

	public int getQtycheckin() {
		return qtycheckin;
	}

	public void setQtycheckin(int qtycheckin) {
		this.qtycheckin = qtycheckin;
	}

	public int getQtynotcheckin() {
		return qtynotcheckin;
	}

	public void setQtynotcheckin(int qtynotcheckin) {
		this.qtynotcheckin = qtynotcheckin;
	}

	public int getQtycomment() {
		return qtycomment;
	}

	public void setQtycomment(int qtycomment) {
		this.qtycomment = qtycomment;
	}

	public int getQtyimg1() {
		return qtyimg1;
	}

	public void setQtyimg1(int qtyimg1) {
		this.qtyimg1 = qtyimg1;
	}

	public int getQtyimg2() {
		return qtyimg2;
	}

	public void setQtyimg2(int qtyimg2) {
		this.qtyimg2 = qtyimg2;
	}

	public int getQtyimg3() {
		return qtyimg3;
	}

	public void setQtyimg3(int qtyimg3) {
		this.qtyimg3 = qtyimg3;
	}
	

}
