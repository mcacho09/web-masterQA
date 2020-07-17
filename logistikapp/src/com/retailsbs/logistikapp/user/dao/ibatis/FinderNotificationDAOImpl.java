package com.retailsbs.logistikapp.user.dao.ibatis;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.retailsbs.logistikapp.user.dao.FinderNotificationDAO;
import com.retailsbs.logistikapp.user.domain.Notification;
import com.retailsbs.logistikapp.user.dto.NotificationDTO;
import com.retailsbs.logistikapp.user.dto.NotificationSearchCriteria;
import com.retailsbs.logistikapp.user.dto.NotificationTodaySearchCriteria;

public class FinderNotificationDAOImpl extends SqlMapClientDaoSupport implements
		FinderNotificationDAO {

	public FinderNotificationDAOImpl() {
		super();
	}

	@SuppressWarnings("unchecked")
	
	public List<NotificationDTO> getNotificationByCriteria(
			NotificationSearchCriteria dto) {
		return (List<NotificationDTO>) getSqlMapClientTemplate().queryForList("finder_notification.getNotificationByCriteria", dto);
	}

	@SuppressWarnings("unchecked")
	
	public List<Notification> getNotificationTodayByCriteria(
			NotificationTodaySearchCriteria dto) {
		return (List<Notification>) getSqlMapClientTemplate().queryForList("finder_notification.getNotificationTodayByCriteria", dto);
	}

	
	public int delNotificationByIdSupplier(Long id_supplier) {
		return getSqlMapClientTemplate().delete("finder_notification.delNotificationByIdSupplier",id_supplier);
	}

	
	public int delNotificationByIdUser(Long id_user) {
		return getSqlMapClientTemplate().delete("finder_notification.delNotificationByIdUser", id_user);
	}

}
