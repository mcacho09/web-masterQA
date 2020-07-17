package com.retailsbs.logistikapp.user.dto;

import java.util.Date;

public class UserMessageGrpDTO {

	private Long id_group;
	private String name;
	private Integer qty;
	private Date since;
	private String message;
	private Long usr_create_grp;
	
	public Long getUsr_create_grp() {
		return usr_create_grp;
	}
	public void setUsr_create_grp(Long usr_create_grp) {
		this.usr_create_grp = usr_create_grp;
	}
	public Long getId_group() {
		return id_group;
	}
	public void setId_group(Long id_group) {
		this.id_group = id_group;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getQty() {
		return qty;
	}
	public void setQty(Integer qty) {
		this.qty = qty;
	}
	public Date getSince() {
		return since;
	}
	public void setSince(Date since) {
		this.since = since;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
