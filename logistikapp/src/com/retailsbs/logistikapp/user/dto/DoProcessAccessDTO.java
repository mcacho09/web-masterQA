package com.retailsbs.logistikapp.user.dto;

import java.util.Date;

import com.retailsbs.logistikapp.user.domain.Access;

public class DoProcessAccessDTO extends Access {

	private Date fecha;
	
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
}
