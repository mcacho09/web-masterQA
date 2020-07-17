package com.retailsbs.logistikapp.logistic.dto;

import java.util.List;

public class ReasignWaybillFromOldTravelDTO {
	
	private Long id_travel;
	private String travelName;
	private List<Long> ids;
	
	public Long getId_travel() {
		return id_travel;
	}
	public void setId_travel(Long id_travel) {
		this.id_travel = id_travel;
	}
	public String getTravelName() {
		return travelName;
	}
	public void setTravelName(String travelName) {
		this.travelName = travelName;
	}
	public List<Long> getIds() {
		return ids;
	}
	public void setIds(List<Long> ids) {
		this.ids = ids;
	}

}
