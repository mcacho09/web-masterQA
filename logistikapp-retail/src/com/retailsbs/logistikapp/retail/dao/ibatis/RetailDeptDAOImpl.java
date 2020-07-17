package com.retailsbs.logistikapp.retail.dao.ibatis;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.retailsbs.logistikapp.retail.dao.RetailDeptDAO;
import com.retailsbs.logistikapp.retail.domain.RetailDept;
import com.retailsbs.logistikapp.retail.dto.RetailDeptExample;

public class RetailDeptDAOImpl extends SqlMapClientDaoSupport implements RetailDeptDAO {

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_retail_dept
     *
     * @abatorgenerated Thu Nov 20 16:35:37 CST 2014
     */
    public RetailDeptDAOImpl() {
        super();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_retail_dept
     *
     * @abatorgenerated Thu Nov 20 16:35:37 CST 2014
     */
    public Long insert(RetailDept record) {
        Object newKey = getSqlMapClientTemplate().insert("lgk_retail_dept.abatorgenerated_insert", record);
        return (Long) newKey;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_retail_dept
     *
     * @abatorgenerated Thu Nov 20 16:35:37 CST 2014
     */
    public int updateByPrimaryKey(RetailDept record) {
        int rows = getSqlMapClientTemplate().update("lgk_retail_dept.abatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_retail_dept
     *
     * @abatorgenerated Thu Nov 20 16:35:37 CST 2014
     */
    public int updateByPrimaryKeySelective(RetailDept record) {
        int rows = getSqlMapClientTemplate().update("lgk_retail_dept.abatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_retail_dept
     *
     * @abatorgenerated Thu Nov 20 16:35:37 CST 2014
     */
    @SuppressWarnings("unchecked")
    public List<RetailDept> selectByExample(RetailDeptExample example) {
        List<RetailDept> list = (List<RetailDept>) getSqlMapClientTemplate().queryForList("lgk_retail_dept.abatorgenerated_selectByExample", example);
        return list;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_retail_dept
     *
     * @abatorgenerated Thu Nov 20 16:35:37 CST 2014
     */
    public RetailDept selectByPrimaryKey(Long id_retail_dept) {
        RetailDept key = new RetailDept();
        key.setId_retail_dept(id_retail_dept);
        RetailDept record = (RetailDept) getSqlMapClientTemplate().queryForObject("lgk_retail_dept.abatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_retail_dept
     *
     * @abatorgenerated Thu Nov 20 16:35:37 CST 2014
     */
    public int deleteByPrimaryKey(Long id_retail_dept) {
        RetailDept key = new RetailDept();
        key.setId_retail_dept(id_retail_dept);
        int rows = getSqlMapClientTemplate().delete("lgk_retail_dept.abatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_retail_dept
     *
     * @abatorgenerated Thu Nov 20 16:35:37 CST 2014
     */
    public int countByExample(RetailDeptExample example) {
        Integer count = (Integer)  getSqlMapClientTemplate().queryForObject("lgk_retail_dept.abatorgenerated_countByExample", example);
        return count;
    }
}