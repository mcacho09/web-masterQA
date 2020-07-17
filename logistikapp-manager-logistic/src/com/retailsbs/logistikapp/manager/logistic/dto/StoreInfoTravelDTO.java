package com.retailsbs.logistikapp.manager.logistic.dto;

import java.util.List;

import com.retailsbs.logistikapp.logistic.dto.TravelAndStatusStr;
import com.retailsbs.logistikapp.retail.dto.StoreInfoDTO;

public class StoreInfoTravelDTO extends StoreInfoDTO {
	
	private List<TravelAndStatusStr> list_travel;

	public List<TravelAndStatusStr> getList_travel() {
		return list_travel;
	}

	public void setList_travel(List<TravelAndStatusStr> list_travel) {
		this.list_travel = list_travel;
	}
	
}
