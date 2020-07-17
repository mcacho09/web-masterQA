package com.retailsbs.logistikapp.user.dto;

import java.util.Date;

public class CalendarDTO {

	private Long id_calendar;
	private String cal_title;
    private Date cal_start;
    private Date cal_end;
    private String cal_level;
    private Integer cal_alert;
    
	public Long getId_calendar() {
		return id_calendar;
	}
	public void setId_calendar(Long id_calendar) {
		this.id_calendar = id_calendar;
	}
	public String getCal_title() {
		return cal_title;
	}
	public void setCal_title(String cal_title) {
		this.cal_title = cal_title;
	}
	public Date getCal_start() {
		return cal_start;
	}
	public void setCal_start(Date cal_start) {
		this.cal_start = cal_start;
	}
	public Date getCal_end() {
		return cal_end;
	}
	public void setCal_end(Date cal_end) {
		this.cal_end = cal_end;
	}
	public String getCal_level() {
		return cal_level;
	}
	public void setCal_level(String cal_level) {
		this.cal_level = cal_level;
	}
	public Integer getCal_alert() {
		return cal_alert;
	}
	public void setCal_alert(Integer cal_alert) {
		this.cal_alert = cal_alert;
	}
}
