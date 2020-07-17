package com.retailsbs.logistikapp.user.test.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.retailsbs.logistikapp.user.dao.ToDoDAO;
import com.retailsbs.logistikapp.user.domain.ToDo;
import com.retailsbs.logistikapp.user.test.base.BaseTestJunit;

public class ToDoDaoTest extends BaseTestJunit {

	@Autowired
	private ToDoDAO dao;

	public void	test_insert(){
		ToDo record = new ToDo();
		record.setActive("S");
		record.setCreated(new Date());
		record.setId_user(200L);
		record.setModified(new Date());
		record.setPriority("A");
		record.setTodo("mensaje de tarea");
		
		Long id = dao.insert(record);
		assertNotNull(id);
	}
	
	public void test_updateByPrimaryKeySelective() {
		ToDo record = new ToDo();
		record.setId_todo(10000L);
		record.setTodo("mensaje de tarea update");
		
		int i = dao.updateByPrimaryKeySelective(record);
		assertTrue(i>0);
	}
	
	public void test_selectByPrimaryKey() {
		ToDo todo = dao.selectByPrimaryKey(10000l);
		assertNotNull(todo);
	}
	
	@Test
	public void test_deleteByPrimaryKey() {
		int i = dao.deleteByPrimaryKey(10000L);
		assertTrue(i>0);
	}
	
}
