package com.retailsbs.logistikapp.manager.logistic.dto;

import java.util.List;

import com.retailsbs.logistikapp.logistic.dto.TravelByIdStoreDTO;
import com.retailsbs.logistikapp.retail.dto.StoreCallCenterDTO;

public class StoreTravelDTO extends StoreCallCenterDTO{
	
	private List<TravelByIdStoreDTO> travelStore;

	public List<TravelByIdStoreDTO> getTravelStore() {
		return travelStore;
	}
	public void setTravelStore(List<TravelByIdStoreDTO> travelStore) {
		this.travelStore = travelStore;
	}
}
