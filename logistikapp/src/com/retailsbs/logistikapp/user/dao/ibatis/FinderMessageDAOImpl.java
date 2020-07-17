package com.retailsbs.logistikapp.user.dao.ibatis;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.retailsbs.logistikapp.user.dao.FinderMessageDAO;
import com.retailsbs.logistikapp.user.dto.MessageGroupDTO;
import com.retailsbs.logistikapp.user.domain.Message;
import com.retailsbs.logistikapp.user.dto.MessageDTO;
import com.retailsbs.logistikapp.user.dto.MessageNoReadDTO;
import com.retailsbs.logistikapp.user.dto.NewMessageTotalDTO;
import com.retailsbs.logistikapp.user.dto.lastNewMessageDTO;
import com.retailsbs.logistikapp.user.dto.lastNewMessageSearchCriteria;

public class FinderMessageDAOImpl extends SqlMapClientDaoSupport implements FinderMessageDAO {

	public FinderMessageDAOImpl(){
		super();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<MessageDTO> getMessageByIdUserMessage(Long id_user_message) {
		return (List<MessageDTO>) getSqlMapClientTemplate().queryForList("finder_message.getMessageByIdUserMessage", id_user_message);
	}

	
	public int deleteMessageByIdUserMessage(Long id_user_message){
        Message key = new Message();
        key.setId_user_message(id_user_message);
        int rows = getSqlMapClientTemplate().delete("finder_message.delMessageByIdUserMsg", key);
        return rows;
	}

	
	public Integer newMessageTotal(NewMessageTotalDTO dto) {
		return (Integer) getSqlMapClientTemplate().queryForObject("finder_message.newMessageTotal", dto);
	}

	
	@SuppressWarnings("unchecked")
	public List<lastNewMessageDTO> lastNewMessage(lastNewMessageSearchCriteria dto) {
		return (List<lastNewMessageDTO>) getSqlMapClientTemplate().queryForList("finder_message.getLastNewMessage", dto);
	}

	
	public Integer getQtyMessageNoReadByIdUser(Long id_user) {
		return (Integer) getSqlMapClientTemplate().queryForObject("finder_message.getQtyMessageNoReadByIdUser", id_user);
	}

	
	@SuppressWarnings("unchecked")
	public List<MessageNoReadDTO> getListMessageNoReadByIdUser(Long id_user) {
		return (List<MessageNoReadDTO>) getSqlMapClientTemplate().queryForList("finder_message.getListMessageNoReadByIdUser", id_user);
	}

	
	public int updateReadByIdUserMessage(Long id_user_message) {
		Message key = new Message();
		key.setId_user_message(id_user_message);
		int rows = getSqlMapClientTemplate().update("finder_message.updateReadByIdUserMessage", key);
		return rows;
	}

	@SuppressWarnings("unchecked")
	
	public List<MessageGroupDTO> getMessageByIdGroup(Long id_group) {
		return (List<MessageGroupDTO>) getSqlMapClientTemplate().queryForList("finder_message.getMessageByIdGroup", id_group);
		
	}

	
	public int delMessageByIdUser(Long id_user) {
		return getSqlMapClientTemplate().delete("finder_message.delMessageByIdUser",id_user);
	}
	
}
