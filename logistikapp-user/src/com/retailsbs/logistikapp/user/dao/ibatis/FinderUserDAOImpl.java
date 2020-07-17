package com.retailsbs.logistikapp.user.dao.ibatis;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.retailsbs.logistikapp.user.dao.FinderUserDAO;
import com.retailsbs.logistikapp.user.domain.Quantities;
import com.retailsbs.logistikapp.user.domain.User;
import com.retailsbs.logistikapp.user.dto.AdminUserDTO;
import com.retailsbs.logistikapp.user.dto.AdminUserSearchCriteria;
import com.retailsbs.logistikapp.user.dto.DRICriteria;
import com.retailsbs.logistikapp.user.dto.MetricsAdm;
import com.retailsbs.logistikapp.user.dto.NotificationTodaySearchCriteria;
import com.retailsbs.logistikapp.user.dto.UserDTO;
import com.retailsbs.logistikapp.user.dto.UserSearchCriteria;

public class FinderUserDAOImpl extends SqlMapClientDaoSupport implements FinderUserDAO {

    public FinderUserDAOImpl() {
        super();
    }
	
	@SuppressWarnings("unchecked")
	public List<UserDTO> getUserByCriteria(UserSearchCriteria dto) {
		return (List<UserDTO>) getSqlMapClientTemplate().queryForList("finder_user.getUserByCriteria",dto);
	}

	@SuppressWarnings("unchecked")
	
	public List<AdminUserDTO> getAdminUserListByCriteria(AdminUserSearchCriteria dto) {
		return (List<AdminUserDTO>) getSqlMapClientTemplate().queryForList("finder_user.getAdminUserList", dto);
	}

	
	public Quantities getQtyAlertAndMessagesByIdUser(NotificationTodaySearchCriteria dto) {
		return (Quantities) getSqlMapClientTemplate().queryForObject("finder_user.getQtyAlertAndMessagesByIdUser", dto);
	}

	@SuppressWarnings("unchecked")
	
	public List<UserDTO> getDisponibleDrivers(DRICriteria criteria) {
		return (List<UserDTO>) getSqlMapClientTemplate().queryForList("finder_user.getDisponibleDrivers", criteria);
	}

	
	public MetricsAdm getMetricsAdm() {
		return (MetricsAdm) getSqlMapClientTemplate().queryForObject("finder_user.getMetricsAdm");
	}

	@SuppressWarnings("unchecked")
	
	public List<Integer> getSupIdsBySuppliers(Long id_supplier) {
		return (List<Integer>) getSqlMapClientTemplate().queryForList("finder_user.getSupIdsBySuppliers", id_supplier);
	}

	@SuppressWarnings("unchecked")
	
	public List<User> getUserWithOutStockByIdSupplier(Long id_supplier) {
		return (List<User>) getSqlMapClientTemplate().queryForList("finder_user.getUserWithOutStockByIdSupplier", id_supplier);
	}

}
