package com.retailsbs.logistikapp.logistic.domain;


import java.util.Date;

public class Travel {
	/**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column lgk_travel.id_travel
     *
     * @abatorgenerated Thu Mar 24 15:03:47 CST 2016
     */
    private Long id_travel;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column lgk_travel.id_route
     *
     * @abatorgenerated Thu Mar 24 15:03:47 CST 2016
     */
    private Long id_route;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column lgk_travel.id_user
     *
     * @abatorgenerated Thu Mar 24 15:03:47 CST 2016
     */
    private Long id_user;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column lgk_travel.status
     *
     * @abatorgenerated Thu Mar 24 15:03:47 CST 2016
     */
    private String status;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column lgk_travel.name
     *
     * @abatorgenerated Thu Mar 24 15:03:47 CST 2016
     */
    private String name;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column lgk_travel.schedule
     *
     * @abatorgenerated Thu Mar 24 15:03:47 CST 2016
     */
    private Date schedule;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column lgk_travel.comment
     *
     * @abatorgenerated Thu Mar 24 15:03:47 CST 2016
     */
    private String comment;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column lgk_travel.started
     *
     * @abatorgenerated Thu Mar 24 15:03:47 CST 2016
     */
    private Date started;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column lgk_travel.finished
     *
     * @abatorgenerated Thu Mar 24 15:03:47 CST 2016
     */
    private Date finished;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column lgk_travel.log_created
     *
     * @abatorgenerated Thu Mar 24 15:03:47 CST 2016
     */
    private Date log_created;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column lgk_travel.log_created_login
     *
     * @abatorgenerated Thu Mar 24 15:03:47 CST 2016
     */
    private String log_created_login;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column lgk_travel.log_modified
     *
     * @abatorgenerated Thu Mar 24 15:03:47 CST 2016
     */
    private Date log_modified;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column lgk_travel.log_modified_login
     *
     * @abatorgenerated Thu Mar 24 15:03:47 CST 2016
     */
    private String log_modified_login;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column lgk_travel.km
     *
     * @abatorgenerated Thu Mar 24 15:03:47 CST 2016
     */
    private Double km;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column lgk_travel.time
     *
     * @abatorgenerated Thu Mar 24 15:03:47 CST 2016
     */
    private Double time;

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column lgk_travel.id_travel
     *
     * @return the value of lgk_travel.id_travel
     *
     * @abatorgenerated Thu Mar 24 15:03:47 CST 2016
     */
    public Long getId_travel() {
        return id_travel;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column lgk_travel.id_travel
     *
     * @param id_travel the value for lgk_travel.id_travel
     *
     * @abatorgenerated Thu Mar 24 15:03:47 CST 2016
     */
    public void setId_travel(Long id_travel) {
        this.id_travel = id_travel;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column lgk_travel.id_route
     *
     * @return the value of lgk_travel.id_route
     *
     * @abatorgenerated Thu Mar 24 15:03:47 CST 2016
     */
    public Long getId_route() {
        return id_route;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column lgk_travel.id_route
     *
     * @param id_route the value for lgk_travel.id_route
     *
     * @abatorgenerated Thu Mar 24 15:03:47 CST 2016
     */
    public void setId_route(Long id_route) {
        this.id_route = id_route;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column lgk_travel.id_user
     *
     * @return the value of lgk_travel.id_user
     *
     * @abatorgenerated Thu Mar 24 15:03:47 CST 2016
     */
    public Long getId_user() {
        return id_user;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column lgk_travel.id_user
     *
     * @param id_user the value for lgk_travel.id_user
     *
     * @abatorgenerated Thu Mar 24 15:03:47 CST 2016
     */
    public void setId_user(Long id_user) {
        this.id_user = id_user;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column lgk_travel.status
     *
     * @return the value of lgk_travel.status
     *
     * @abatorgenerated Thu Mar 24 15:03:47 CST 2016
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column lgk_travel.status
     *
     * @param status the value for lgk_travel.status
     *
     * @abatorgenerated Thu Mar 24 15:03:47 CST 2016
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column lgk_travel.name
     *
     * @return the value of lgk_travel.name
     *
     * @abatorgenerated Thu Mar 24 15:03:47 CST 2016
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column lgk_travel.name
     *
     * @param name the value for lgk_travel.name
     *
     * @abatorgenerated Thu Mar 24 15:03:47 CST 2016
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column lgk_travel.schedule
     *
     * @return the value of lgk_travel.schedule
     *
     * @abatorgenerated Thu Mar 24 15:03:47 CST 2016
     */
    public Date getSchedule() {
        return schedule;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column lgk_travel.schedule
     *
     * @param schedule the value for lgk_travel.schedule
     *
     * @abatorgenerated Thu Mar 24 15:03:47 CST 2016
     */
    public void setSchedule(Date schedule) {
        this.schedule = schedule;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column lgk_travel.comment
     *
     * @return the value of lgk_travel.comment
     *
     * @abatorgenerated Thu Mar 24 15:03:47 CST 2016
     */
    public String getComment() {
        return comment;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column lgk_travel.comment
     *
     * @param comment the value for lgk_travel.comment
     *
     * @abatorgenerated Thu Mar 24 15:03:47 CST 2016
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column lgk_travel.started
     *
     * @return the value of lgk_travel.started
     *
     * @abatorgenerated Thu Mar 24 15:03:47 CST 2016
     */
    public Date getStarted() {
        return started;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column lgk_travel.started
     *
     * @param started the value for lgk_travel.started
     *
     * @abatorgenerated Thu Mar 24 15:03:47 CST 2016
     */
    public void setStarted(Date started) {
        this.started = started;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column lgk_travel.finished
     *
     * @return the value of lgk_travel.finished
     *
     * @abatorgenerated Thu Mar 24 15:03:47 CST 2016
     */
    public Date getFinished() {
        return finished;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column lgk_travel.finished
     *
     * @param finished the value for lgk_travel.finished
     *
     * @abatorgenerated Thu Mar 24 15:03:47 CST 2016
     */
    public void setFinished(Date finished) {
        this.finished = finished;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column lgk_travel.log_created
     *
     * @return the value of lgk_travel.log_created
     *
     * @abatorgenerated Thu Mar 24 15:03:47 CST 2016
     */
    public Date getLog_created() {
        return log_created;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column lgk_travel.log_created
     *
     * @param log_created the value for lgk_travel.log_created
     *
     * @abatorgenerated Thu Mar 24 15:03:47 CST 2016
     */
    public void setLog_created(Date log_created) {
        this.log_created = log_created;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column lgk_travel.log_created_login
     *
     * @return the value of lgk_travel.log_created_login
     *
     * @abatorgenerated Thu Mar 24 15:03:47 CST 2016
     */
    public String getLog_created_login() {
        return log_created_login;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column lgk_travel.log_created_login
     *
     * @param log_created_login the value for lgk_travel.log_created_login
     *
     * @abatorgenerated Thu Mar 24 15:03:47 CST 2016
     */
    public void setLog_created_login(String log_created_login) {
        this.log_created_login = log_created_login;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column lgk_travel.log_modified
     *
     * @return the value of lgk_travel.log_modified
     *
     * @abatorgenerated Thu Mar 24 15:03:47 CST 2016
     */
    public Date getLog_modified() {
        return log_modified;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column lgk_travel.log_modified
     *
     * @param log_modified the value for lgk_travel.log_modified
     *
     * @abatorgenerated Thu Mar 24 15:03:47 CST 2016
     */
    public void setLog_modified(Date log_modified) {
        this.log_modified = log_modified;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column lgk_travel.log_modified_login
     *
     * @return the value of lgk_travel.log_modified_login
     *
     * @abatorgenerated Thu Mar 24 15:03:47 CST 2016
     */
    public String getLog_modified_login() {
        return log_modified_login;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column lgk_travel.log_modified_login
     *
     * @param log_modified_login the value for lgk_travel.log_modified_login
     *
     * @abatorgenerated Thu Mar 24 15:03:47 CST 2016
     */
    public void setLog_modified_login(String log_modified_login) {
        this.log_modified_login = log_modified_login;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column lgk_travel.km
     *
     * @return the value of lgk_travel.km
     *
     * @abatorgenerated Thu Mar 24 15:03:47 CST 2016
     */
    public Double getKm() {
        return km;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column lgk_travel.km
     *
     * @param km the value for lgk_travel.km
     *
     * @abatorgenerated Thu Mar 24 15:03:47 CST 2016
     */
    public void setKm(Double km) {
        this.km = km;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column lgk_travel.time
     *
     * @return the value of lgk_travel.time
     *
     * @abatorgenerated Thu Mar 24 15:03:47 CST 2016
     */
    public Double getTime() {
        return time;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column lgk_travel.time
     *
     * @param time the value for lgk_travel.time
     *
     * @abatorgenerated Thu Mar 24 15:03:47 CST 2016
     */
    public void setTime(Double time) {
        this.time = time;
    }
}