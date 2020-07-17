package com.retailsbs.logistikapp.retail.dto;

import java.util.Date;

public class ActivesVisitedCriteria {
	
	private Long id_active;
	private Long id_store;
	private Date dateInit;
	private Date dateEnd;
	
	public Long getId_active() {
		return id_active;
	}
	public void setId_active(Long id_active) {
		this.id_active = id_active;
	}
	public Long getId_store() {
		return id_store;
	}
	public void setId_store(Long id_store) {
		this.id_store = id_store;
	}
	public Date getDateInit() {
		return dateInit;
	}
	public void setDateInit(Date dateInit) {
		this.dateInit = dateInit;
	}
	public Date getDateEnd() {
		return dateEnd;
	}
	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

}
