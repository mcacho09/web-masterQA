package com.retailsbs.logistikapp.retail.dao.ibatis;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.retailsbs.logistikapp.retail.dao.CountryDAO;
import com.retailsbs.logistikapp.retail.domain.Country;
import com.retailsbs.logistikapp.retail.dto.CountryExample;

public class CountryDAOImpl extends SqlMapClientDaoSupport implements CountryDAO {

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_country
     *
     * @abatorgenerated Thu Nov 20 16:35:37 CST 2014
     */
    public CountryDAOImpl() {
        super();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_country
     *
     * @abatorgenerated Thu Nov 20 16:35:37 CST 2014
     */
    public Long insert(Country record) {
        Object newKey = getSqlMapClientTemplate().insert("lgk_country.abatorgenerated_insert", record);
        return (Long) newKey;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_country
     *
     * @abatorgenerated Thu Nov 20 16:35:37 CST 2014
     */
    public int updateByPrimaryKey(Country record) {
        int rows = getSqlMapClientTemplate().update("lgk_country.abatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_country
     *
     * @abatorgenerated Thu Nov 20 16:35:37 CST 2014
     */
    public int updateByPrimaryKeySelective(Country record) {
        int rows = getSqlMapClientTemplate().update("lgk_country.abatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_country
     *
     * @abatorgenerated Thu Nov 20 16:35:37 CST 2014
     */
    @SuppressWarnings("unchecked")
    public List<Country> selectByExample(CountryExample example) {
        List<Country> list = (List<Country>) getSqlMapClientTemplate().queryForList("lgk_country.abatorgenerated_selectByExample", example);
        return list;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_country
     *
     * @abatorgenerated Thu Nov 20 16:35:37 CST 2014
     */
    public Country selectByPrimaryKey(Long id_country) {
        Country key = new Country();
        key.setId_country(id_country);
        Country record = (Country) getSqlMapClientTemplate().queryForObject("lgk_country.abatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_country
     *
     * @abatorgenerated Thu Nov 20 16:35:37 CST 2014
     */
    public int deleteByPrimaryKey(Long id_country) {
        Country key = new Country();
        key.setId_country(id_country);
        int rows = getSqlMapClientTemplate().delete("lgk_country.abatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_country
     *
     * @abatorgenerated Thu Nov 20 16:35:37 CST 2014
     */
    public int countByExample(CountryExample example) {
        Integer count = (Integer)  getSqlMapClientTemplate().queryForObject("lgk_country.abatorgenerated_countByExample", example);
        return count;
    }
}