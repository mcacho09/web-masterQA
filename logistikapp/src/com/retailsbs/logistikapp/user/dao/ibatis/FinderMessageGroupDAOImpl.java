package com.retailsbs.logistikapp.user.dao.ibatis;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.retailsbs.logistikapp.user.dao.FinderMessageGroupDAO;
import com.retailsbs.logistikapp.user.domain.MessageGroup;

public class FinderMessageGroupDAOImpl extends SqlMapClientDaoSupport implements FinderMessageGroupDAO {

	public FinderMessageGroupDAOImpl(){
		super();
	}
	
	
	public int delMessageGrpByIdGrp(Long id_group) {
        MessageGroup key = new MessageGroup();
        key.setId_group(id_group);
        int rows = getSqlMapClientTemplate().delete("finder_message_group.delMessageGrpByIdGrp", key);
        return rows;
	}

	
	@SuppressWarnings("unchecked")
	public List<MessageGroup> getMessageGroupByIdGroup(Long id_group) {
		return (List<MessageGroup>) getSqlMapClientTemplate().queryForList("finder_message_group.getMessageByIdUserMessage", id_group);	
	}

	
	public int delMessageGroupByIdMessage(Long id_message) {
		return getSqlMapClientTemplate().delete("finder_message_group.delMessageGroupByIdMessage", id_message);
	}

}
