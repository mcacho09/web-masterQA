package com.retailsbs.logistikapp.logistic.dto;

import java.util.Date;
import java.util.List;

public class MetricsCounterCriteria {

	private Long id_supplier;
	private Long id_user;
	private Date schedule;
	private Date scheduleFin;
	//Expandimos las opciones para los status del waybill
	private String statusOne;
	private String statusTwo;
	private List<String> statusRoute;
	private List<String> statusTravel;

	public Long getId_supplier() {
		return id_supplier;
	}

	public void setId_supplier(Long id_supplier) {
		this.id_supplier = id_supplier;
	}

	public Long getId_user() {
		return id_user;
	}

	public void setId_user(Long id_user) {
		this.id_user = id_user;
	}

	public Date getSchedule() {
		return schedule;
	}

	public void setSchedule(Date schedule) {
		this.schedule = schedule;
	}

	public List<String> getStatusTravel() {
		return statusTravel;
	}

	public void setStatusTravel(List<String> statusTravel) {
		this.statusTravel = statusTravel;
	}

	public String getStatusOne() {
		return statusOne;
	}

	public void setStatusOne(String statusOne) {
		this.statusOne = statusOne;
	}

	public String getStatusTwo() {
		return statusTwo;
	}

	public void setStatusTwo(String statusTwo) {
		this.statusTwo = statusTwo;
	}

	public List<String> getStatusRoute() {
		return statusRoute;
	}

	public void setStatusRoute(List<String> statusRoute) {
		this.statusRoute = statusRoute;
	}

	public Date getScheduleFin() {
		return scheduleFin;
	}

	public void setScheduleFin(Date scheduleFin) {
		this.scheduleFin = scheduleFin;
	}

}
