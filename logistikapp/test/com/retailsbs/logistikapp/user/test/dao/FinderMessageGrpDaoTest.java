package com.retailsbs.logistikapp.user.test.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.retailsbs.logistikapp.user.dao.FinderMessageGroupDAO;
import com.retailsbs.logistikapp.user.domain.MessageGroup;
import com.retailsbs.logistikapp.user.test.base.BaseTestJunit;

public class FinderMessageGrpDaoTest extends BaseTestJunit {

	@Autowired
	private FinderMessageGroupDAO dao;

	public void test_deleteMessageByIdUserMessage() {
		int i = dao.delMessageGrpByIdGrp(42L);
		assertTrue(i > 0);
	}
	
	@Test
	public void test_getMessageGroupByIdGroup() {
		List<MessageGroup> list = dao.getMessageGroupByIdGroup(41L);
		assertNotNull(list);
	}
}
