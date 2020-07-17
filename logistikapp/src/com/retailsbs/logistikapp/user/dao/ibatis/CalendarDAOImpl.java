package com.retailsbs.logistikapp.user.dao.ibatis;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.retailsbs.logistikapp.user.dao.CalendarDAO;
import com.retailsbs.logistikapp.user.domain.Calendar;
import com.retailsbs.logistikapp.user.dto.CalendarExample;

public class CalendarDAOImpl extends SqlMapClientDaoSupport implements CalendarDAO {

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_calendar
     *
     * @abatorgenerated Tue Jul 21 11:25:21 CDT 2015
     */
    public CalendarDAOImpl() {
        super();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_calendar
     *
     * @abatorgenerated Tue Jul 21 11:25:21 CDT 2015
     */
    public Long insert(Calendar record) {
        Object newKey = getSqlMapClientTemplate().insert("lgk_calendar.abatorgenerated_insert", record);
        return (Long) newKey;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_calendar
     *
     * @abatorgenerated Tue Jul 21 11:25:21 CDT 2015
     */
    public int updateByPrimaryKey(Calendar record) {
        int rows = getSqlMapClientTemplate().update("lgk_calendar.abatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_calendar
     *
     * @abatorgenerated Tue Jul 21 11:25:21 CDT 2015
     */
    public int updateByPrimaryKeySelective(Calendar record) {
        int rows = getSqlMapClientTemplate().update("lgk_calendar.abatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_calendar
     *
     * @abatorgenerated Tue Jul 21 11:25:21 CDT 2015
     */
    @SuppressWarnings("unchecked")
    public List<Calendar> selectByExample(CalendarExample example) {
        List<Calendar> list = (List<Calendar>) getSqlMapClientTemplate().queryForList("lgk_calendar.abatorgenerated_selectByExample", example);
        return list;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_calendar
     *
     * @abatorgenerated Tue Jul 21 11:25:21 CDT 2015
     */
    public Calendar selectByPrimaryKey(Long id_calendar) {
        Calendar key = new Calendar();
        key.setId_calendar(id_calendar);
        Calendar record = (Calendar) getSqlMapClientTemplate().queryForObject("lgk_calendar.abatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_calendar
     *
     * @abatorgenerated Tue Jul 21 11:25:21 CDT 2015
     */
    public int deleteByPrimaryKey(Long id_calendar) {
        Calendar key = new Calendar();
        key.setId_calendar(id_calendar);
        int rows = getSqlMapClientTemplate().delete("lgk_calendar.abatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table lgk_calendar
     *
     * @abatorgenerated Tue Jul 21 11:25:21 CDT 2015
     */
    public int countByExample(CalendarExample example) {
        Integer count = (Integer)  getSqlMapClientTemplate().queryForObject("lgk_calendar.abatorgenerated_countByExample", example);
        return count;
    }
}