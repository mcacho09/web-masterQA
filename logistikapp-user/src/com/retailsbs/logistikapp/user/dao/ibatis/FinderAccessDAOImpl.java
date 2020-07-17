package com.retailsbs.logistikapp.user.dao.ibatis;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.retailsbs.logistikapp.user.dao.FinderAccessDAO;
import com.retailsbs.logistikapp.user.dto.AccessTinyDTO;
import com.retailsbs.logistikapp.user.dto.AccessTinyExtDTO;

public class FinderAccessDAOImpl extends SqlMapClientDaoSupport implements
		FinderAccessDAO {

	public FinderAccessDAOImpl() {
		super();
	}

	
	@SuppressWarnings("unchecked")
	public List<AccessTinyDTO> getAccessSupplierByIdUser(Long id_user) {
		return (List<AccessTinyDTO>) getSqlMapClientTemplate().queryForList("finder_access.getAccessSupplierByIdUser", id_user);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<AccessTinyExtDTO> getAccessSupplierExtByIdUser(Long id_user) {
		return (List<AccessTinyExtDTO>) getSqlMapClientTemplate().queryForList("finder_access.getAccessSupplierExtByIdUser", id_user);
	}

	
	public int delAccessByIdStore(Long id_store) {
		return getSqlMapClientTemplate().delete("finder_access.deleteAccessByIdStore", id_store);	
	}

	
	public int delAccessByIdRetail(Long id_retail) {
		return getSqlMapClientTemplate().delete("finder_access.deleteAccessByIdRetail", id_retail);	
	}

	
	public int delAccessByIdSupplier(Long id_supplier) {
		return getSqlMapClientTemplate().delete("finder_access.delAccessByIdSupplier", id_supplier);
	}

	
	public int delAccessByIdUser(Long id_user) {
		return getSqlMapClientTemplate().delete("finder_access.delAccessByIdUser", id_user);
	}

}
