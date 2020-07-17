package com.retailsbs.logistikapp.user.dao;

public interface FinderAttachmentDAO {
	/**
	 * Borra todos los registros attachment relacionados a un id_message
	 * @param id_message
	 * @return int numero de registros eliminados
	 * @author Nataly
	 * @since 30-07-2015
	 */
	int delAttachmentByIdMessage(Long id_message);
}
