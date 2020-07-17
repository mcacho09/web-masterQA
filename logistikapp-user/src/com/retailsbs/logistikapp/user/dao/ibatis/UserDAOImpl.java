package com.retailsbs.logistikapp.user.dao.ibatis;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.retailsbs.logistikapp.user.dao.UserDAO;
import com.retailsbs.logistikapp.user.domain.User;
import com.retailsbs.logistikapp.user.dto.UserExample;

public class UserDAOImpl extends SqlMapClientDaoSupport implements UserDAO {

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_user
     *
     * @abatorgenerated Thu Mar 26 18:34:57 CST 2015
     */
    public UserDAOImpl() {
        super();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_user
     *
     * @abatorgenerated Thu Mar 26 18:34:57 CST 2015
     */
    public Long insert(User record) {
        Object newKey = getSqlMapClientTemplate().insert("lgk_user.abatorgenerated_insert", record);
        return (Long) newKey;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_user
     *
     * @abatorgenerated Thu Mar 26 18:34:57 CST 2015
     */
    public int updateByPrimaryKey(User record) {
        int rows = getSqlMapClientTemplate().update("lgk_user.abatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_user
     *
     * @abatorgenerated Thu Mar 26 18:34:57 CST 2015
     */
    public int updateByPrimaryKeySelective(User record) {
        int rows = getSqlMapClientTemplate().update("lgk_user.abatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_user
     *
     * @abatorgenerated Thu Mar 26 18:34:57 CST 2015
     */
    @SuppressWarnings("unchecked")
    public List<User> selectByExample(UserExample example) {
        List<User> list = (List<User>) getSqlMapClientTemplate().queryForList("lgk_user.abatorgenerated_selectByExample", example);
        return list;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_user
     *
     * @abatorgenerated Thu Mar 26 18:34:57 CST 2015
     */
    public User selectByPrimaryKey(Long id_user) {
        User key = new User();
        key.setId_user(id_user);
        User record = (User) getSqlMapClientTemplate().queryForObject("lgk_user.abatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_user
     *
     * @abatorgenerated Thu Mar 26 18:34:57 CST 2015
     */
    public int deleteByPrimaryKey(Long id_user) {
        User key = new User();
        key.setId_user(id_user);
        int rows = getSqlMapClientTemplate().delete("lgk_user.abatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_user
     *
     * @abatorgenerated Thu Mar 26 18:34:57 CST 2015
     */
    public int countByExample(UserExample example) {
        Integer count = (Integer)  getSqlMapClientTemplate().queryForObject("lgk_user.abatorgenerated_countByExample", example);
        return count;
    }
}