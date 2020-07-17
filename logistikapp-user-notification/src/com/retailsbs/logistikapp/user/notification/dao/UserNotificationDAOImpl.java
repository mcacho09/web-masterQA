package com.retailsbs.logistikapp.user.notification.dao;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.retailsbs.logistikapp.user.notification.domain.UserNotification;
import com.retailsbs.logistikapp.user.notification.domain.UserNotificationExample;

public class UserNotificationDAOImpl extends SqlMapClientDaoSupport implements UserNotificationDAO {

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_user_notification
     *
     * @abatorgenerated Fri May 20 09:11:21 CDT 2016
     */
    public UserNotificationDAOImpl() {
        super();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_user_notification
     *
     * @abatorgenerated Fri May 20 09:11:21 CDT 2016
     */
    public Long insert(UserNotification record) {
        Object newKey = getSqlMapClientTemplate().insert("lgk_user_notification.abatorgenerated_insert", record);
        return (Long) newKey;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_user_notification
     *
     * @abatorgenerated Fri May 20 09:11:21 CDT 2016
     */
    public int updateByPrimaryKey(UserNotification record) {
        int rows = getSqlMapClientTemplate().update("lgk_user_notification.abatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_user_notification
     *
     * @abatorgenerated Fri May 20 09:11:21 CDT 2016
     */
    public int updateByPrimaryKeySelective(UserNotification record) {
        int rows = getSqlMapClientTemplate().update("lgk_user_notification.abatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_user_notification
     *
     * @abatorgenerated Fri May 20 09:11:21 CDT 2016
     */
    @SuppressWarnings("unchecked")
    public List<UserNotification> selectByExample(UserNotificationExample example) {
        List<UserNotification> list = (List<UserNotification>) getSqlMapClientTemplate().queryForList("lgk_user_notification.abatorgenerated_selectByExample", example);
        return list;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_user_notification
     *
     * @abatorgenerated Fri May 20 09:11:21 CDT 2016
     */
    public UserNotification selectByPrimaryKey(Long id) {
        UserNotification key = new UserNotification();
        key.setId(id);
        UserNotification record = (UserNotification) getSqlMapClientTemplate().queryForObject("lgk_user_notification.abatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_user_notification
     *
     * @abatorgenerated Fri May 20 09:11:21 CDT 2016
     */
    public int deleteByExample(UserNotificationExample example) {
        int rows = getSqlMapClientTemplate().delete("lgk_user_notification.abatorgenerated_deleteByExample", example);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_user_notification
     *
     * @abatorgenerated Fri May 20 09:11:21 CDT 2016
     */
    public int deleteByPrimaryKey(Long id) {
        UserNotification key = new UserNotification();
        key.setId(id);
        int rows = getSqlMapClientTemplate().delete("lgk_user_notification.abatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_user_notification
     *
     * @abatorgenerated Fri May 20 09:11:21 CDT 2016
     */
    public int countByExample(UserNotificationExample example) {
        Integer count = (Integer)  getSqlMapClientTemplate().queryForObject("lgk_user_notification.abatorgenerated_countByExample", example);
        return count;
    }

}