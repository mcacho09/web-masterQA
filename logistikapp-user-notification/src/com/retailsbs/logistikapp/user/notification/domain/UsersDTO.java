package com.retailsbs.logistikapp.user.notification.domain;

import java.util.List;

public class UsersDTO {

	private List<Integer> lista;
	private long id_notification;
	private long id_user;
	private long id_supplier;
	
	
	public List<Integer> getLista() {
		return lista;
	}
	public void setLista(List<Integer> lista) {
		this.lista = lista;
	}
	public long getId_notification() {
		return id_notification;
	}
	public void setId_notification(long id_notification) {
		this.id_notification = id_notification;
	}
	public long getId_user() {
		return id_user;
	}
	public void setId_user(long id_user) {
		this.id_user = id_user;
	}
	public long getId_supplier() {
		return id_supplier;
	}
	public void setId_supplier(long id_supplier) {
		this.id_supplier = id_supplier;
	}
	
	
	
	
}
