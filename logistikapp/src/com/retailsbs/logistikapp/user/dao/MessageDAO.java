package com.retailsbs.logistikapp.user.dao;

import java.util.List;

import com.retailsbs.logistikapp.user.domain.Message;
import com.retailsbs.logistikapp.user.dto.MessageExample;

public interface MessageDAO {
    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_message
     *
     * @abatorgenerated Mon Aug 03 14:45:54 CDT 2015
     */
    Long insert(Message record);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_message
     *
     * @abatorgenerated Mon Aug 03 14:45:54 CDT 2015
     */
    int updateByPrimaryKey(Message record);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_message
     *
     * @abatorgenerated Mon Aug 03 14:45:54 CDT 2015
     */
    int updateByPrimaryKeySelective(Message record);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_message
     *
     * @abatorgenerated Mon Aug 03 14:45:54 CDT 2015
     */
    List<Message> selectByExample(MessageExample example);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_message
     *
     * @abatorgenerated Mon Aug 03 14:45:54 CDT 2015
     */
    Message selectByPrimaryKey(Long id_message);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_message
     *
     * @abatorgenerated Mon Aug 03 14:45:54 CDT 2015
     */
    int deleteByPrimaryKey(Long id_message);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_message
     *
     * @abatorgenerated Mon Aug 03 14:45:54 CDT 2015
     */
    int countByExample(MessageExample example);
}