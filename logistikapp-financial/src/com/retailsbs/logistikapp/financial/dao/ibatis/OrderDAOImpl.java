package com.retailsbs.logistikapp.financial.dao.ibatis;

import com.retailsbs.logistikapp.financial.domain.Order;
import com.retailsbs.logistikapp.financial.dto.OrderExample;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.retailsbs.logistikapp.financial.dao.OrderDAO;

public class OrderDAOImpl extends SqlMapClientDaoSupport implements OrderDAO {

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_order
     *
     * @abatorgenerated Thu Aug 18 11:32:18 CDT 2016
     */
    public OrderDAOImpl() {
        super();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_order
     *
     * @abatorgenerated Thu Aug 18 11:32:18 CDT 2016
     */
    public Long insert(Order record) {
        Object newKey = getSqlMapClientTemplate().insert("lgk_order.abatorgenerated_insert", record);
        return (Long) newKey;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_order
     *
     * @abatorgenerated Thu Aug 18 11:32:18 CDT 2016
     */
    public int updateByPrimaryKey(Order record) {
        int rows = getSqlMapClientTemplate().update("lgk_order.abatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_order
     *
     * @abatorgenerated Thu Aug 18 11:32:18 CDT 2016
     */
    public int updateByPrimaryKeySelective(Order record) {
        int rows = getSqlMapClientTemplate().update("lgk_order.abatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_order
     *
     * @abatorgenerated Thu Aug 18 11:32:18 CDT 2016
     */
    @SuppressWarnings("unchecked")
    public List<Order> selectByExample(OrderExample example) {
        List<Order> list = (List<Order>) getSqlMapClientTemplate().queryForList("lgk_order.abatorgenerated_selectByExample", example);
        return list;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_order
     *
     * @abatorgenerated Thu Aug 18 11:32:18 CDT 2016
     */
    public Order selectByPrimaryKey(Long id_order) {
        Order key = new Order();
        key.setId_order(id_order);
        Order record = (Order) getSqlMapClientTemplate().queryForObject("lgk_order.abatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_order
     *
     * @abatorgenerated Thu Aug 18 11:32:18 CDT 2016
     */
    public int deleteByPrimaryKey(Long id_order) {
        Order key = new Order();
        key.setId_order(id_order);
        int rows = getSqlMapClientTemplate().delete("lgk_order.abatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_order
     *
     * @abatorgenerated Thu Aug 18 11:32:18 CDT 2016
     */
    public int countByExample(OrderExample example) {
        Integer count = (Integer)  getSqlMapClientTemplate().queryForObject("lgk_order.abatorgenerated_countByExample", example);
        return count;
    }
}