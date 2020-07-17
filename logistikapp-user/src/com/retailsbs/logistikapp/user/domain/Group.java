package com.retailsbs.logistikapp.user.domain;

import java.util.Date;

public class Group {
    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column lgk_group.id_group
     *
     * @abatorgenerated Mon Aug 03 13:51:36 CDT 2015
     */
    private Long id_group;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column lgk_group.id_user
     *
     * @abatorgenerated Mon Aug 03 13:51:36 CDT 2015
     */
    private Long id_user;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column lgk_group.name
     *
     * @abatorgenerated Mon Aug 03 13:51:36 CDT 2015
     */
    private String name;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column lgk_group.created
     *
     * @abatorgenerated Mon Aug 03 13:51:36 CDT 2015
     */
    private Date created;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column lgk_group.modified
     *
     * @abatorgenerated Mon Aug 03 13:51:36 CDT 2015
     */
    private Date modified;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column lgk_group.login
     *
     * @abatorgenerated Mon Aug 03 13:51:36 CDT 2015
     */
    private String login;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column lgk_group.active
     *
     * @abatorgenerated Mon Aug 03 13:51:36 CDT 2015
     */
    private String active;

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column lgk_group.id_group
     *
     * @return the value of lgk_group.id_group
     *
     * @abatorgenerated Mon Aug 03 13:51:36 CDT 2015
     */
    public Long getId_group() {
        return id_group;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column lgk_group.id_group
     *
     * @param id_group the value for lgk_group.id_group
     *
     * @abatorgenerated Mon Aug 03 13:51:36 CDT 2015
     */
    public void setId_group(Long id_group) {
        this.id_group = id_group;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column lgk_group.id_user
     *
     * @return the value of lgk_group.id_user
     *
     * @abatorgenerated Mon Aug 03 13:51:36 CDT 2015
     */
    public Long getId_user() {
        return id_user;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column lgk_group.id_user
     *
     * @param id_user the value for lgk_group.id_user
     *
     * @abatorgenerated Mon Aug 03 13:51:36 CDT 2015
     */
    public void setId_user(Long id_user) {
        this.id_user = id_user;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column lgk_group.name
     *
     * @return the value of lgk_group.name
     *
     * @abatorgenerated Mon Aug 03 13:51:36 CDT 2015
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column lgk_group.name
     *
     * @param name the value for lgk_group.name
     *
     * @abatorgenerated Mon Aug 03 13:51:36 CDT 2015
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column lgk_group.created
     *
     * @return the value of lgk_group.created
     *
     * @abatorgenerated Mon Aug 03 13:51:36 CDT 2015
     */
    public Date getCreated() {
        return created;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column lgk_group.created
     *
     * @param created the value for lgk_group.created
     *
     * @abatorgenerated Mon Aug 03 13:51:36 CDT 2015
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column lgk_group.modified
     *
     * @return the value of lgk_group.modified
     *
     * @abatorgenerated Mon Aug 03 13:51:36 CDT 2015
     */
    public Date getModified() {
        return modified;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column lgk_group.modified
     *
     * @param modified the value for lgk_group.modified
     *
     * @abatorgenerated Mon Aug 03 13:51:36 CDT 2015
     */
    public void setModified(Date modified) {
        this.modified = modified;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column lgk_group.login
     *
     * @return the value of lgk_group.login
     *
     * @abatorgenerated Mon Aug 03 13:51:36 CDT 2015
     */
    public String getLogin() {
        return login;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column lgk_group.login
     *
     * @param login the value for lgk_group.login
     *
     * @abatorgenerated Mon Aug 03 13:51:36 CDT 2015
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column lgk_group.active
     *
     * @return the value of lgk_group.active
     *
     * @abatorgenerated Mon Aug 03 13:51:36 CDT 2015
     */
    public String getActive() {
        return active;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column lgk_group.active
     *
     * @param active the value for lgk_group.active
     *
     * @abatorgenerated Mon Aug 03 13:51:36 CDT 2015
     */
    public void setActive(String active) {
        this.active = active;
    }
}