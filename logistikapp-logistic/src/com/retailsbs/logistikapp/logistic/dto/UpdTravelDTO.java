package com.retailsbs.logistikapp.logistic.dto;

public class UpdTravelDTO  {
    private String id_route;
	private Long id_user;
    private String name;
    private String schedule;
    private String status;
    private Double km;
	private Double time;
	private Long repetitions;
    private Long repetitionsday;
	
    public Double getKm() {
		return km;
	}

	public void setKm(Double km) {
		this.km = km;
	}

	public Double getTime() {
		return time;
	}

	public void setTime(Double time) {
		this.time = time;
	}
    public String getId_route() {
		return id_route;
	}
	public void setId_route(String id_route) {
		this.id_route = id_route;
	}
	public Long getId_user() {
		return id_user;
	}
	public void setId_user(Long id_user) {
		this.id_user = id_user;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSchedule() {
		return schedule;
	}
	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public Long getRepetitions() {
		return repetitions;
	}

	public void setRepetitions(Long repetitions) {
		this.repetitions = repetitions;
	}

	public Long getRepetitionsday() {
		return repetitionsday;
	}

	public void setRepetitionsday(Long repetitionsday) {
		this.repetitionsday = repetitionsday;
	}
	
}
