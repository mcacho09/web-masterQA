package com.retailsbs.logistikapp.user.notification.service.impl;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.retailsbs.logistikapp.user.dto.AddNotificationDTO;
import com.retailsbs.logistikapp.user.dto.UserDTO;
import com.retailsbs.logistikapp.user.dto.UserSearchCriteria;
import com.retailsbs.logistikapp.user.notification.dao.FinderUserNotificationDAO;
import com.retailsbs.logistikapp.user.notification.dao.UserNotificationDAO;
import com.retailsbs.logistikapp.user.notification.domain.ListNotificationDTO;
import com.retailsbs.logistikapp.user.notification.domain.UserNotification;
import com.retailsbs.logistikapp.user.notification.domain.UsersDTO;
import com.retailsbs.logistikapp.user.notification.service.UserNotificationService;
import com.retailsbs.logistikapp.user.service.UserService;

public class UserNotificationServiceImpl implements UserNotificationService {
	protected final Log logger = LogFactory.getLog(getClass());

	private UserService userservice;
	FinderUserNotificationDAO dao_finder_user_notification;
	UserNotificationDAO dao_user_notification;

	public UserService getUserservice() {
		return userservice;
	}

	public void setUserservice(UserService userservice) {
		this.userservice = userservice;
	}

	public FinderUserNotificationDAO getDao_finder_user_notification() {
		return dao_finder_user_notification;
	}

	public void setDao_finder_user_notification(
			FinderUserNotificationDAO dao_finder_user_notification) {
		this.dao_finder_user_notification = dao_finder_user_notification;
	}
	

	public UserNotificationDAO getDao_user_notification() {
		return dao_user_notification;
	}

	public void setDao_user_notification(UserNotificationDAO dao_user_notification) {
		this.dao_user_notification = dao_user_notification;
	}

	public long createNotification(AddNotificationDTO datos, String code) {

		int opcion = (code.equals("001") ? 1 : code.equals("002") ? 2 : code.equals("003") ? 3 : code.equals("004") ? 4 
											 : code.equals("005") ? 5 : code.equals("006") ? 6 : 0);
		//Creamos la notificación normal, con el DTO recibido
		Long id_notification = userservice.addNotification(datos);
		logger.debug("Notification, id=" + id_notification + " creado OK!");
		
	
		UsersDTO tmp= new UsersDTO();
		tmp.setId_supplier(datos.getId_supplier());
		tmp.setId_user(datos.getId_user());
		UsersDTO dto = new UsersDTO();
		dto.setId_notification(id_notification);
		dto.setId_user(datos.getId_user());

		switch(opcion){
			case 1: { 
				List<Integer> usuarios = dao_finder_user_notification.getUsersByIdSupplier(tmp);
				dto.setLista(usuarios);
				if (usuarios != null && usuarios.size() > 0){
					dao_finder_user_notification.insertNewNotification(dto);
				}
			}break;
			case 2: { 
				List<Integer> usuarios = dao_finder_user_notification.getUsersByIdSupplierNoDRI(tmp);
				dto.setLista(usuarios);
				if (usuarios != null && usuarios.size() > 0){
					dao_finder_user_notification.insertNewNotification(dto);
				}
			}break;
			case 3: { 
				List<Integer> usuarios = dao_finder_user_notification.getUsersByIdSupplierJustSUP(tmp);
				dto.setLista(usuarios);
				if (usuarios != null && usuarios.size() > 0){
					dao_finder_user_notification.insertNewNotification(dto);				
				}
			}break;
			case 4: { 
				UserNotification dtto = new UserNotification();
				dtto.setId_notification(dto.getId_notification());
				dtto.setId_user(datos.getId_user());
				List<Integer> usuarios = new LinkedList<Integer>();
				usuarios.add( datos.getId_user().intValue() );
				dto.setLista(usuarios);
				if (usuarios != null && usuarios.size() > 0){
					dao_finder_user_notification.insertNewNotification(dto);				
				}
			}break;
			case 5: { 
				List<Integer> usuarios = dao_finder_user_notification.getUsersByIdSupplierJustSUP(tmp);
				dto.setLista(usuarios);
				if (usuarios != null && usuarios.size() > 0){
					dao_finder_user_notification.insertNewNotification(dto);
				}
				Long id_usuario = 0L;
				String perfil ="";
									
				UserSearchCriteria cri = new UserSearchCriteria();
				int pos_uno = (datos.getMessage().indexOf("'>"))+2;
				int pos_dos = datos.getMessage().indexOf("</");
				String mensaje = datos.getMessage().substring(pos_uno, pos_dos);
				cri.setUsername(mensaje);
				List<UserDTO> lista = userservice.getUserByCriteria(cri);
				
				for (UserDTO userDTO : lista) {
					id_usuario = userDTO.getId_user();
					perfil = userDTO.getProfile();
				}
				usuarios.removeAll(usuarios);
				if(perfil.contains("DRI")){
					usuarios.add(id_usuario.intValue());
				}
				usuarios.add(datos.getId_user().intValue());
				dto.setLista(usuarios);
				if (lista != null && usuarios.size() > 0){
					dao_finder_user_notification.insertNewNotification(dto);
				}
				
			}break;
			case 6: { 
				List<Integer> usuarios = dao_finder_user_notification.getUsersAndUserByIdSupplier(tmp);
				dto.setLista(usuarios);
				if (usuarios != null && usuarios.size() > 0){
					dao_finder_user_notification.insertNewNotification(dto);
				}
			}break;			
		
		}
		
		
		
		return id_notification;
	}

	@Override
	public List<ListNotificationDTO> searchNotification(Long id_user, String code) {
		List<ListNotificationDTO> dto = null;
		
		if (code.equalsIgnoreCase("L")){
			dto = dao_finder_user_notification.selectAllByIdUser(id_user);	 
		} 
		if (code.equalsIgnoreCase("C")){
			dto = dao_finder_user_notification.selectTopByIdUser(id_user);
		}
		
		//Para obtener el listado de notificaciones para el webservice
		if (code.equals("WS")) {
			dto = dao_finder_user_notification.selectNotificacionsWSByIdUser(id_user);
		}

		return dto;
	}

	@Override
	public void updateNotification(Long id_user) {
		dao_finder_user_notification.updateByIdUser(id_user);
	}

	@Override
	public long createNotificationWithList(AddNotificationDTO datos,
			List<Integer> usuarios) {
		
		long id_notification = userservice.addNotification(datos);
		UsersDTO dto = new UsersDTO();
		dto.setId_notification(id_notification);
		dto.setLista(usuarios);
		dao_finder_user_notification.insertNewNotification(dto);
		
		return id_notification;
	}

	@Override
	public long createNotificationWithIdNotification(Long id_notification,
			List<Integer> usuarios) {
		// TODO Auto-generated method stub
		UsersDTO dto = new UsersDTO();
		dto.setId_notification(id_notification);
		dto.setLista(usuarios);
		long res = dao_finder_user_notification.insertNewNotification(dto);
		return res;
	}

}
