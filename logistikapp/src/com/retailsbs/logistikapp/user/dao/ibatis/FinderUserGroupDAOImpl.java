package com.retailsbs.logistikapp.user.dao.ibatis;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.retailsbs.logistikapp.user.dao.FinderUserGroupDAO;
import com.retailsbs.logistikapp.user.domain.UserGroup;
import com.retailsbs.logistikapp.user.dto.UserGroupDTO;

public class FinderUserGroupDAOImpl extends SqlMapClientDaoSupport implements FinderUserGroupDAO {

	@SuppressWarnings("unchecked")
	
	public List<UserGroupDTO> getUserByIdGroup(Long id_group) {
		return (List<UserGroupDTO>) getSqlMapClientTemplate().queryForList("finder_user_group.getUserByIdGroup", id_group);
		
	}

	
	public int delUserGrpByIdGrp(Long id_group) {
		UserGroup key = new UserGroup();
        key.setId_group(id_group);
        int rows = getSqlMapClientTemplate().delete("finder_user_group.delUserGrpByIdGrp", key);
        return rows;
	}

	
	public int delUserGrpByIdUser(Long id_user) {
		return getSqlMapClientTemplate().delete("finder_user_group.delUserGrpByIdUser", id_user);
	}

}
