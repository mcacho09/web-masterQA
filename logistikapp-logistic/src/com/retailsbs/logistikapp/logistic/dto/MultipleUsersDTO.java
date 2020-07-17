package com.retailsbs.logistikapp.logistic.dto;

import java.util.List;

import com.retailsbs.logistikapp.logistic.domain.WayBill;

public class MultipleUsersDTO {
	
	private List<WayBill> clientsToAdd;

	public List<WayBill> getClientsToAdd() {
		return clientsToAdd;
	}

	public void setClientsToAdd(List<WayBill> clientsToAdd) {
		this.clientsToAdd = clientsToAdd;
	}

	
	
	
}
