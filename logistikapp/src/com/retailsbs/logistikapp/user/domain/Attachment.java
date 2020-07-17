package com.retailsbs.logistikapp.user.domain;

import java.util.Date;

public class Attachment {
    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column lgk_attachment.id_attachment
     *
     * @abatorgenerated Mon Aug 03 13:39:53 CDT 2015
     */
    private Long id_attachment;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column lgk_attachment.id_message
     *
     * @abatorgenerated Mon Aug 03 13:39:53 CDT 2015
     */
    private Long id_message;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column lgk_attachment.file
     *
     * @abatorgenerated Mon Aug 03 13:39:53 CDT 2015
     */
    private String file;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column lgk_attachment.created
     *
     * @abatorgenerated Mon Aug 03 13:39:53 CDT 2015
     */
    private Date created;

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column lgk_attachment.id_attachment
     *
     * @return the value of lgk_attachment.id_attachment
     *
     * @abatorgenerated Mon Aug 03 13:39:53 CDT 2015
     */
    public Long getId_attachment() {
        return id_attachment;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column lgk_attachment.id_attachment
     *
     * @param id_attachment the value for lgk_attachment.id_attachment
     *
     * @abatorgenerated Mon Aug 03 13:39:53 CDT 2015
     */
    public void setId_attachment(Long id_attachment) {
        this.id_attachment = id_attachment;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column lgk_attachment.id_message
     *
     * @return the value of lgk_attachment.id_message
     *
     * @abatorgenerated Mon Aug 03 13:39:53 CDT 2015
     */
    public Long getId_message() {
        return id_message;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column lgk_attachment.id_message
     *
     * @param id_message the value for lgk_attachment.id_message
     *
     * @abatorgenerated Mon Aug 03 13:39:53 CDT 2015
     */
    public void setId_message(Long id_message) {
        this.id_message = id_message;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column lgk_attachment.file
     *
     * @return the value of lgk_attachment.file
     *
     * @abatorgenerated Mon Aug 03 13:39:53 CDT 2015
     */
    public String getFile() {
        return file;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column lgk_attachment.file
     *
     * @param file the value for lgk_attachment.file
     *
     * @abatorgenerated Mon Aug 03 13:39:53 CDT 2015
     */
    public void setFile(String file) {
        this.file = file;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column lgk_attachment.created
     *
     * @return the value of lgk_attachment.created
     *
     * @abatorgenerated Mon Aug 03 13:39:53 CDT 2015
     */
    public Date getCreated() {
        return created;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column lgk_attachment.created
     *
     * @param created the value for lgk_attachment.created
     *
     * @abatorgenerated Mon Aug 03 13:39:53 CDT 2015
     */
    public void setCreated(Date created) {
        this.created = created;
    }
}