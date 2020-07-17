package com.retailsbs.logistikapp.user.dao;

import java.util.List;

import com.retailsbs.logistikapp.user.domain.Notification;
import com.retailsbs.logistikapp.user.dto.NotificationExample;

public interface NotificationDAO {
    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_notification
     *
     * @abatorgenerated Mon Jul 20 11:53:34 CDT 2015
     */
    Long insert(Notification record);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_notification
     *
     * @abatorgenerated Mon Jul 20 11:53:34 CDT 2015
     */
    int updateByPrimaryKey(Notification record);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_notification
     *
     * @abatorgenerated Mon Jul 20 11:53:34 CDT 2015
     */
    int updateByPrimaryKeySelective(Notification record);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_notification
     *
     * @abatorgenerated Mon Jul 20 11:53:34 CDT 2015
     */
    List<Notification> selectByExample(NotificationExample example);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_notification
     *
     * @abatorgenerated Mon Jul 20 11:53:34 CDT 2015
     */
    Notification selectByPrimaryKey(Long id_notification);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_notification
     *
     * @abatorgenerated Mon Jul 20 11:53:34 CDT 2015
     */
    int deleteByPrimaryKey(Long id_notification);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_notification
     *
     * @abatorgenerated Mon Jul 20 11:53:34 CDT 2015
     */
    int countByExample(NotificationExample example);
}