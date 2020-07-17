package com.retailsbs.logistikapp.user.test.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.retailsbs.logistikapp.user.dao.FinderToDoDAO;
import com.retailsbs.logistikapp.user.domain.ToDo;
import com.retailsbs.logistikapp.user.dto.ToDoActiveSearchCriteria;
import com.retailsbs.logistikapp.user.dto.ToDoDTO;
import com.retailsbs.logistikapp.user.test.base.BaseTestJunit;

public class FinderToDoDaoTest extends BaseTestJunit {

	@Autowired
	private FinderToDoDAO dao;

	public void test_getToDoActive() {
		ToDoActiveSearchCriteria dto = new ToDoActiveSearchCriteria();
		dto.setActive("S");
		dto.setLimit(2);
		dto.setLimit_char(4);

		List<ToDoDTO> list = dao.getToDoActive(dto);
		assertNotNull(list);
	}

	public void test_getToDoActiveTotal() {
		ToDoActiveSearchCriteria dto = new ToDoActiveSearchCriteria();
		dto.setActive("S");

		Integer tot = dao.getToDoActiveTotal(dto);
		assertTrue(tot > 0);
	}
	
	@Test
	public void test_getTodoActiveByIdUser() {
		List<ToDo> list = dao.getTodoActiveByIdUser(1L);
		assertNotNull(list);
	}
	
}
