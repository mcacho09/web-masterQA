package com.retailsbs.logistikapp.user.test.dao;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.retailsbs.logistikapp.user.dao.AttachmentDAO;
import com.retailsbs.logistikapp.user.domain.Attachment;
import com.retailsbs.logistikapp.user.dto.AttachmentExample;
import com.retailsbs.logistikapp.user.test.base.BaseTestJunit;

public class AttachmentDaoTest extends BaseTestJunit {

	@Autowired
	private AttachmentDAO dao;
	
	public void test_insert() {
		Attachment record = new Attachment();
		record.setCreated(new Date());
		record.setFile("prueba.png");
		record.setId_message(38L);
		Long id = dao.insert(record );
		logger.debug("Se inserto --> "+id+" Attachment OK");
	}
	
	public void test_updateByPrimaryKeySelective() {
		Attachment record = new Attachment();
		record.setFile("prueba2.png");
		record.setId_message(38L);
		record.setId_attachment(1L);
		int id = dao.updateByPrimaryKey(record);
		logger.debug("Se actualizaron  --> "+id+" registros OK");
	}
	public void test_selectByPrimaryKey() {
		Attachment id = dao.selectByPrimaryKey(1L);
		logger.debug("Se seleccionaron  --> "+id+" registros OK");
	}
	@Test
	public void test_selectByExample() {
		AttachmentExample example = new AttachmentExample();
		example.createCriteria().andCreatedLessThan(new Date());
		List<Attachment> id = dao.selectByExample(example );
		logger.debug("Se seleccionaron  --> "+id.size()+" registros OK");
	}
}
