package com.retailsbs.logistikapp.user.dao.ibatis;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.retailsbs.logistikapp.user.dao.FinderAttachmentDAO;

public class FinderAttachmentDAOImpl  extends SqlMapClientDaoSupport implements FinderAttachmentDAO{

	
	public int delAttachmentByIdMessage(Long id_message) {
        int rows = getSqlMapClientTemplate().delete("finder_attachment.delAttachmentByIdMessage", id_message);
        return rows;
	}

}
