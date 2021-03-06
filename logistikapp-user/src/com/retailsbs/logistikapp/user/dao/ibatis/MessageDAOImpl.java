package com.retailsbs.logistikapp.user.dao.ibatis;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.retailsbs.logistikapp.user.dao.MessageDAO;
import com.retailsbs.logistikapp.user.domain.Message;
import com.retailsbs.logistikapp.user.dto.MessageExample;

public class MessageDAOImpl extends SqlMapClientDaoSupport implements MessageDAO {

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_message
     *
     * @abatorgenerated Mon Aug 03 14:45:54 CDT 2015
     */
    public MessageDAOImpl() {
        super();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_message
     *
     * @abatorgenerated Mon Aug 03 14:45:54 CDT 2015
     */
    public Long insert(Message record) {
        Object newKey = getSqlMapClientTemplate().insert("lgk_message.abatorgenerated_insert", record);
        return (Long) newKey;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_message
     *
     * @abatorgenerated Mon Aug 03 14:45:54 CDT 2015
     */
    public int updateByPrimaryKey(Message record) {
        int rows = getSqlMapClientTemplate().update("lgk_message.abatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_message
     *
     * @abatorgenerated Mon Aug 03 14:45:54 CDT 2015
     */
    public int updateByPrimaryKeySelective(Message record) {
        int rows = getSqlMapClientTemplate().update("lgk_message.abatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_message
     *
     * @abatorgenerated Mon Aug 03 14:45:54 CDT 2015
     */
    @SuppressWarnings("unchecked")
    public List<Message> selectByExample(MessageExample example) {
        List<Message> list = (List<Message>) getSqlMapClientTemplate().queryForList("lgk_message.abatorgenerated_selectByExample", example);
        return list;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_message
     *
     * @abatorgenerated Mon Aug 03 14:45:54 CDT 2015
     */
    public Message selectByPrimaryKey(Long id_message) {
        Message key = new Message();
        key.setId_message(id_message);
        Message record = (Message) getSqlMapClientTemplate().queryForObject("lgk_message.abatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_message
     *
     * @abatorgenerated Mon Aug 03 14:45:54 CDT 2015
     */
    public int deleteByPrimaryKey(Long id_message) {
        Message key = new Message();
        key.setId_message(id_message);
        int rows = getSqlMapClientTemplate().delete("lgk_message.abatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_message
     *
     * @abatorgenerated Mon Aug 03 14:45:54 CDT 2015
     */
    public int countByExample(MessageExample example) {
        Integer count = (Integer)  getSqlMapClientTemplate().queryForObject("lgk_message.abatorgenerated_countByExample", example);
        return count;
    }
}