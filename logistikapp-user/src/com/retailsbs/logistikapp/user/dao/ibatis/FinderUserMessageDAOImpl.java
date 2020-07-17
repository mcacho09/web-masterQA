package com.retailsbs.logistikapp.user.dao.ibatis;


import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.retailsbs.logistikapp.user.dao.FinderUserMessageDAO;
import com.retailsbs.logistikapp.user.domain.User;
import com.retailsbs.logistikapp.user.domain.UserMessage;
import com.retailsbs.logistikapp.user.dto.RelUserUsertoDTO;
import com.retailsbs.logistikapp.user.dto.UserMessageDTO;
import com.retailsbs.logistikapp.user.dto.UserMessageGrpDTO;
import com.retailsbs.logistikapp.user.dto.UserSearchCriteria;
import com.retailsbs.logistikapp.user.dto.UserUsertoDTO;

public class FinderUserMessageDAOImpl extends SqlMapClientDaoSupport implements FinderUserMessageDAO {

	public FinderUserMessageDAOImpl(){
		super();
	}

	
	@SuppressWarnings("unchecked")
	public List<UserUsertoDTO> getRelUserUserto(RelUserUsertoDTO dto) {
		List<UserUsertoDTO> list = (List<UserUsertoDTO>) getSqlMapClientTemplate().queryForList("finder_user_message.getRelUserUserto",dto);
		return list;
	}

	
	@SuppressWarnings("unchecked")
	public List<UserMessageDTO> getUserMessageByIdUser(Long id_user) {
		return (List<UserMessageDTO>) getSqlMapClientTemplate().queryForList("finder_user_message.getUserMessageByIdUser",id_user);
	}
	
	@SuppressWarnings("unchecked")	
	public List<User> getAvailableUserByCriteria(UserSearchCriteria dto) {
		return (List<User>) getSqlMapClientTemplate().queryForList("finder_user_message.getAvailableUserByCriteria",dto);
		
	}

	
	
	@SuppressWarnings("unchecked")
	public List<UserMessageDTO> getUserLastMessageByIdUser(Long id_user) {
		return (List<UserMessageDTO>) getSqlMapClientTemplate().queryForList("finder_user_message.getUserLastMessageByIdUser",id_user);
	}

	@SuppressWarnings("unchecked")
	public List<UserMessageGrpDTO> getUserLastMsgGroByIdUser(Long id_user) {
		return (List<UserMessageGrpDTO>) getSqlMapClientTemplate().queryForList("finder_user_message.getUserLastMsgGroByIdUser",id_user);
	}

	@SuppressWarnings("unchecked")
	public List<UserMessage> getUserMessageByIdUserId_UserTo(Long id_user) {
		return (List<UserMessage>) getSqlMapClientTemplate().queryForList("finder_user_message.getUserMessageByIdUserId_UserTo",id_user);
	}

	
	public int delUserMessageByIdUserIdUserTo(Long id_user) {
		return getSqlMapClientTemplate().delete("finder_user_message.delUserMessageByIdUserIdUserTo", id_user);
	}

}
