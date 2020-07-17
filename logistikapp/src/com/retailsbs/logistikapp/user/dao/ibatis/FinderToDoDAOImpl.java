package com.retailsbs.logistikapp.user.dao.ibatis;


import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.retailsbs.logistikapp.user.dao.FinderToDoDAO;
import com.retailsbs.logistikapp.user.domain.ToDo;
import com.retailsbs.logistikapp.user.dto.ToDoActiveSearchCriteria;
import com.retailsbs.logistikapp.user.dto.ToDoDTO;

public class FinderToDoDAOImpl extends SqlMapClientDaoSupport implements FinderToDoDAO {

	public FinderToDoDAOImpl(){
		super();
	}

	
	@SuppressWarnings("unchecked")
	public List<ToDoDTO> getToDoActive(ToDoActiveSearchCriteria dto) {
		return (List<ToDoDTO>) getSqlMapClientTemplate().queryForList("finder_todo.getToDoActive", dto);
	}

	
	public Integer getToDoActiveTotal(ToDoActiveSearchCriteria dto) {
		return (Integer) getSqlMapClientTemplate().queryForObject("finder_todo.getToDoActiveTotal", dto);
	}

	
	@SuppressWarnings("unchecked")
	public List<ToDo> getTodoActiveByIdUser(Long id_user) {
		return (List<ToDo>) getSqlMapClientTemplate().queryForList("finder_todo.getTodoActiveByIdUser", id_user);
	}

	
	public int delTodoByIdUser(Long id_user) {
		return getSqlMapClientTemplate().delete("finder_todo.delTodoByIdUser", id_user);
	}

}
