package com.retailsbs.logistikapp.user.dao;

import java.util.List;

import com.retailsbs.logistikapp.user.domain.User;
import com.retailsbs.logistikapp.user.dto.UserExample;

public interface UserDAO {
    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_user
     *
     * @abatorgenerated Thu Mar 26 18:34:57 CST 2015
     */
    Long insert(User record);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_user
     *
     * @abatorgenerated Thu Mar 26 18:34:57 CST 2015
     */
    int updateByPrimaryKey(User record);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_user
     *
     * @abatorgenerated Thu Mar 26 18:34:57 CST 2015
     */
    int updateByPrimaryKeySelective(User record);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_user
     *
     * @abatorgenerated Thu Mar 26 18:34:57 CST 2015
     */
    List<User> selectByExample(UserExample example);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_user
     *
     * @abatorgenerated Thu Mar 26 18:34:57 CST 2015
     */
    User selectByPrimaryKey(Long id_user);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_user
     *
     * @abatorgenerated Thu Mar 26 18:34:57 CST 2015
     */
    int deleteByPrimaryKey(Long id_user);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_user
     *
     * @abatorgenerated Thu Mar 26 18:34:57 CST 2015
     */
    int countByExample(UserExample example);
}