package com.retailsbs.logistikapp.user.dao.ibatis;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.retailsbs.logistikapp.user.dao.FinderCalendarDAO;
import com.retailsbs.logistikapp.user.dto.CalendarDTO;
import com.retailsbs.logistikapp.user.dto.EventSearchCriteria;

public class FinderCalendarDAOImpl extends SqlMapClientDaoSupport implements FinderCalendarDAO{

	@SuppressWarnings("unchecked")
	public List<CalendarDTO> getEventByCriteria(EventSearchCriteria dto) {
		return (List<CalendarDTO>) getSqlMapClientTemplate().queryForList("finder_calendar.getEventByCriteria", dto);
	}

	
	public int delCalendarByIdSupplier(Long id_supplier) {
		return getSqlMapClientTemplate().delete("finder_calendar.delCalendarByIdSupplier", id_supplier);
	}

	
	public int delCalendarByIdUser(Long id_user) {
		return getSqlMapClientTemplate().delete("finder_calendar.delCalendarByIdUser", id_user);
	}

}
