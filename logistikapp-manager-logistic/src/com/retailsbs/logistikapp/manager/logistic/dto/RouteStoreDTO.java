package com.retailsbs.logistikapp.manager.logistic.dto;

import java.util.List;

import com.retailsbs.logistikapp.logistic.domain.Route;
import com.retailsbs.logistikapp.retail.dto.StoreByRouteDTO;

public class RouteStoreDTO extends Route{
	
	private List<StoreByRouteDTO> Store;

	
	public List<StoreByRouteDTO> getStore() {
		return Store;
	}

	public void setStore(List<StoreByRouteDTO> store) {
		Store = store;
	}

}
