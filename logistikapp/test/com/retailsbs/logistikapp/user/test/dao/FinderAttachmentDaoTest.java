package com.retailsbs.logistikapp.user.test.dao;



import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.retailsbs.logistikapp.user.dao.FinderAttachmentDAO;
import com.retailsbs.logistikapp.user.test.base.BaseTestJunit;

public class FinderAttachmentDaoTest extends BaseTestJunit {

	@Autowired
	private FinderAttachmentDAO dao;

	@Test
	public void test_delAttachmentByIdMessage() {
		Long id_message = 38L;		
		int rows = dao.delAttachmentByIdMessage(id_message);
		logger.debug("Se eliminaron --> "+ rows+" registros OK");
	}
	
}
