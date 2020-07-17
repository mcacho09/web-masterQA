package com.retailsbs.logistikapp.user.dto;


public class lastNewMessageSearchCriteria{
	
	private Integer limit;
	private Integer caracteres;
	private String read;
	private Long id_user;
	private String active;
	
	public Integer getCaracteres() {
		return caracteres;
	}
	public void setCaracteres(Integer caracteres) {
		this.caracteres = caracteres;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public String getRead() {
		return read;
	}
	public void setRead(String read) {
		this.read = read;
	}
	public Long getId_user() {
		return id_user;
	}
	public void setId_user(Long id_user) {
		this.id_user = id_user;
	}

}
