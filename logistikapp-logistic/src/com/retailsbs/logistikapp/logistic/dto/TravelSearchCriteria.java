package com.retailsbs.logistikapp.logistic.dto;

import java.util.Date;
import java.util.List;

public class TravelSearchCriteria {

	private Long id_supplier;
	private List<String> status;
	private Long id_user;
	private Long id_route;
	private Date fini;
	private Date ffin;

	public Long getId_supplier() {
		return id_supplier;
	}

	public void setId_supplier(Long id_supplier) {
		this.id_supplier = id_supplier;
	}

	public List<String> getStatus() {
		return status;
	}

	public void setStatus(List<String> status) {
		this.status = status;
	}

	public Long getId_user() {
		return id_user;
	}

	public void setId_user(Long id_user) {
		this.id_user = id_user;
	}

	public Long getId_route() {
		return id_route;
	}

	public void setId_route(Long id_route) {
		this.id_route = id_route;
	}

	public Date getFini() {
		return fini;
	}

	public void setFini(Date fini) {
		this.fini = fini;
	}

	public Date getFfin() {
		return ffin;
	}

	public void setFfin(Date ffin) {
		this.ffin = ffin;
	}

}
