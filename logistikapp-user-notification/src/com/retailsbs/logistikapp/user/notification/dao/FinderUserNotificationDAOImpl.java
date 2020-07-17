package com.retailsbs.logistikapp.user.notification.dao;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.retailsbs.logistikapp.user.notification.domain.ListNotificationDTO;
import com.retailsbs.logistikapp.user.notification.domain.UsersDTO;

public class FinderUserNotificationDAOImpl extends SqlMapClientDaoSupport implements FinderUserNotificationDAO {


	@Override
	public void updateByIdUser(Long id_user) {
		getSqlMapClientTemplate().update("finder_user_notification.updateActiveByIdUser",id_user);
	}

	
	@Override
	public List<ListNotificationDTO> selectAllByIdUser(Long id_user) {
		@SuppressWarnings("unchecked")
		List<ListNotificationDTO> lista = (List<ListNotificationDTO>) getSqlMapClientTemplate().queryForList("finder_user_notification.selectAllByIdUser",id_user);
		
		return lista; 
		
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ListNotificationDTO> selectTopByIdUser(Long id_user) {
		List<ListNotificationDTO> lista = (List<ListNotificationDTO>) getSqlMapClientTemplate().queryForList("finder_user_notification.selectTopByIdUser",id_user);
		return lista;
	}

	@Override
	public Long insertNewNotification(UsersDTO dto) {
		getSqlMapClientTemplate().insert("finder_user_notification.insertNewNotification", dto);
		return 1L;
	}

	@Override
	public List<Integer> getUsersByIdSupplier(UsersDTO dto) {
		@SuppressWarnings("unchecked")
		List<Integer>  lista = (List<Integer>) getSqlMapClientTemplate().queryForList("finder_user_notification.getUsersByIdSupplier",dto);
		return lista;
	}
	
	@Override
	public List<Integer> getUsersAndUserByIdSupplier(UsersDTO dto) {
		@SuppressWarnings("unchecked")
		List<Integer>  lista = (List<Integer>) getSqlMapClientTemplate().queryForList("finder_user_notification.getUsersAndUserByIdSupplier",dto);
		return lista;
	}


	@Override
	public List<Integer> getUsersByIdSupplierNoDRI(UsersDTO tmp) {
		@SuppressWarnings("unchecked")
		List<Integer>  lista = (List<Integer>) getSqlMapClientTemplate().queryForList("finder_user_notification.getUsersByIdSupplierNoDRI",tmp);
		return lista;
	}


	@Override
	public List<Integer> getUsersByIdSupplierJustSUP(UsersDTO tmp) {
		@SuppressWarnings("unchecked")
		List<Integer>  lista = (List<Integer>) getSqlMapClientTemplate().queryForList("finder_user_notification.getUsersByIdSupplierJustSUP",tmp);
		return lista;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<ListNotificationDTO> selectNotificacionsWSByIdUser(Long id_user) {
		// TODO Auto-generated method stub
		return (List<ListNotificationDTO>) getSqlMapClientTemplate().queryForList("finder_user_notification.selectNotificacionsWSByIdUser", id_user);
	}
	
}
