package com.retailsbs.logistikapp.user.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.acegisecurity.userdetails.UserDetails;
import org.acegisecurity.userdetails.UserDetailsService;
import org.acegisecurity.userdetails.UsernameNotFoundException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.util.FileCopyUtils;

import com.retailsbs.logistikapp.email.EmailDataDTO;
import com.retailsbs.logistikapp.email.SendEmail;
import com.retailsbs.logistikapp.user.dao.AccessDAO;
import com.retailsbs.logistikapp.user.dao.AttachmentDAO;
import com.retailsbs.logistikapp.user.dao.CalendarDAO;
import com.retailsbs.logistikapp.user.dao.FinderAccessDAO;
import com.retailsbs.logistikapp.user.dao.FinderAttachmentDAO;
import com.retailsbs.logistikapp.user.dao.FinderCalendarDAO;
import com.retailsbs.logistikapp.user.dao.FinderGroupDAO;
import com.retailsbs.logistikapp.user.dao.FinderMessageDAO;
import com.retailsbs.logistikapp.user.dao.FinderMessageGroupDAO;
import com.retailsbs.logistikapp.user.dao.FinderNotificationDAO;
import com.retailsbs.logistikapp.user.dao.FinderToDoDAO;
import com.retailsbs.logistikapp.user.dao.FinderUserDAO;
import com.retailsbs.logistikapp.user.dao.FinderUserGroupDAO;
import com.retailsbs.logistikapp.user.dao.FinderUserMessageDAO;
import com.retailsbs.logistikapp.user.dao.GroupDAO;
import com.retailsbs.logistikapp.user.dao.MessageDAO;
import com.retailsbs.logistikapp.user.dao.MessageGroupDAO;
import com.retailsbs.logistikapp.user.dao.NotificationDAO;
import com.retailsbs.logistikapp.user.dao.ToDoDAO;
import com.retailsbs.logistikapp.user.dao.UserDAO;
import com.retailsbs.logistikapp.user.dao.UserGroupDAO;
import com.retailsbs.logistikapp.user.dao.UserMessageDAO;
import com.retailsbs.logistikapp.user.domain.Access;
import com.retailsbs.logistikapp.user.domain.Attachment;
import com.retailsbs.logistikapp.user.domain.Calendar;
import com.retailsbs.logistikapp.user.domain.Group;
import com.retailsbs.logistikapp.user.domain.Message;
import com.retailsbs.logistikapp.user.domain.MessageGroup;
import com.retailsbs.logistikapp.user.domain.Notification;
import com.retailsbs.logistikapp.user.domain.Quantities;
import com.retailsbs.logistikapp.user.domain.ToDo;
import com.retailsbs.logistikapp.user.domain.User;
import com.retailsbs.logistikapp.user.domain.UserAcegi;
import com.retailsbs.logistikapp.user.domain.UserGroup;
import com.retailsbs.logistikapp.user.domain.UserMessage;
import com.retailsbs.logistikapp.user.dto.AccessExample;
import com.retailsbs.logistikapp.user.dto.AccessTinyDTO;
import com.retailsbs.logistikapp.user.dto.AccessTinyExtDTO;
import com.retailsbs.logistikapp.user.dto.AddAccessDTO;
import com.retailsbs.logistikapp.user.dto.AddCalendarDTO;
import com.retailsbs.logistikapp.user.dto.AddGroupDTO;
import com.retailsbs.logistikapp.user.dto.AddMessageDTO;
import com.retailsbs.logistikapp.user.dto.AddMessageGroupDTO;
import com.retailsbs.logistikapp.user.dto.AddNotificationDTO;
import com.retailsbs.logistikapp.user.dto.AddToDoDTO;
import com.retailsbs.logistikapp.user.dto.AddUserDTO;
import com.retailsbs.logistikapp.user.dto.AddUserGroupDTO;
import com.retailsbs.logistikapp.user.dto.AddUserMessageDTO;
import com.retailsbs.logistikapp.user.dto.AdminUserDTO;
import com.retailsbs.logistikapp.user.dto.AdminUserSearchCriteria;
import com.retailsbs.logistikapp.user.dto.AttachmentDTO;
import com.retailsbs.logistikapp.user.dto.AttachmentExample;
import com.retailsbs.logistikapp.user.dto.AvaibleLoginUserSearchCriteria;
import com.retailsbs.logistikapp.user.dto.CalendarDTO;
import com.retailsbs.logistikapp.user.dto.CalendarExample;
import com.retailsbs.logistikapp.user.dto.DRICriteria;
import com.retailsbs.logistikapp.user.dto.DoProcessAccessDTO;
import com.retailsbs.logistikapp.user.dto.EventSearchCriteria;
import com.retailsbs.logistikapp.user.dto.GroupExample;
import com.retailsbs.logistikapp.user.dto.HeaderNotificationDTO;
import com.retailsbs.logistikapp.user.dto.MessageDTO;
import com.retailsbs.logistikapp.user.dto.MessageExample;
import com.retailsbs.logistikapp.user.dto.MessageGroupDTO;
import com.retailsbs.logistikapp.user.dto.MessageGroupExample;
import com.retailsbs.logistikapp.user.dto.MessageNoReadDTO;
import com.retailsbs.logistikapp.user.dto.MetricsAdm;
import com.retailsbs.logistikapp.user.dto.NewMessageTotalDTO;
import com.retailsbs.logistikapp.user.dto.NotificationDTO;
import com.retailsbs.logistikapp.user.dto.NotificationExample;
import com.retailsbs.logistikapp.user.dto.NotificationSearchCriteria;
import com.retailsbs.logistikapp.user.dto.NotificationTodaySearchCriteria;
import com.retailsbs.logistikapp.user.dto.RelUserUsertoDTO;
import com.retailsbs.logistikapp.user.dto.SendMessageDTO;
import com.retailsbs.logistikapp.user.dto.ToDoActiveSearchCriteria;
import com.retailsbs.logistikapp.user.dto.ToDoDTO;
import com.retailsbs.logistikapp.user.dto.ToDoExample;
import com.retailsbs.logistikapp.user.dto.UpdAccessDTO;
import com.retailsbs.logistikapp.user.dto.UpdCalendarDTO;
import com.retailsbs.logistikapp.user.dto.UpdGroupDTO;
import com.retailsbs.logistikapp.user.dto.UpdMessageDTO;
import com.retailsbs.logistikapp.user.dto.UpdMessageGroupDTO;
import com.retailsbs.logistikapp.user.dto.UpdNotificationDTO;
import com.retailsbs.logistikapp.user.dto.UpdToDoDTO;
import com.retailsbs.logistikapp.user.dto.UpdUserDTO;
import com.retailsbs.logistikapp.user.dto.UpdUserGroupDTO;
import com.retailsbs.logistikapp.user.dto.UpdUserMessageDTO;
import com.retailsbs.logistikapp.user.dto.UserDTO;
import com.retailsbs.logistikapp.user.dto.UserExample;
import com.retailsbs.logistikapp.user.dto.UserGroupDTO;
import com.retailsbs.logistikapp.user.dto.UserGroupExample;
import com.retailsbs.logistikapp.user.dto.UserMessageDTO;
import com.retailsbs.logistikapp.user.dto.UserMessageExample;
import com.retailsbs.logistikapp.user.dto.UserMessageGrpDTO;
import com.retailsbs.logistikapp.user.dto.UserSearchCriteria;
import com.retailsbs.logistikapp.user.dto.UserUsertoDTO;
import com.retailsbs.logistikapp.user.dto.lastNewMessageDTO;
import com.retailsbs.logistikapp.user.dto.lastNewMessageSearchCriteria;
import com.retailsbs.logistikapp.user.exception.AccessErrorException;
import com.retailsbs.logistikapp.user.exception.AccessNotFoundException;
import com.retailsbs.logistikapp.user.exception.AttachmentNotFoundException;
import com.retailsbs.logistikapp.user.exception.CalendarNotFoundException;
import com.retailsbs.logistikapp.user.exception.EmailUserDuplicateException;
import com.retailsbs.logistikapp.user.exception.GroupNotFoundException;
import com.retailsbs.logistikapp.user.exception.LoginUserDuplicateException;
import com.retailsbs.logistikapp.user.exception.MessageGroupNotFoundException;
import com.retailsbs.logistikapp.user.exception.MessageNotFoundException;
import com.retailsbs.logistikapp.user.exception.NotificationNotFoundException;
import com.retailsbs.logistikapp.user.exception.ToDoNotFoundException;
import com.retailsbs.logistikapp.user.exception.UserGroupNotFoundException;
import com.retailsbs.logistikapp.user.exception.UserMessageNotFoundException;
import com.retailsbs.logistikapp.user.exception.UserNotFoundException;
import com.retailsbs.logistikapp.user.service.UserService;

/**
 * Implementacion servicio de usuario
 * 
 * @author JORGE
 * @since 17-11-2014
 * @modified 26-01-2015 - JORGE - Se modifican finder/user y finder/user/message
 * @modified 27-01-2015 - JORGE - Se agrega metodo sendMessage()
 * @modified 05-02-2015 - JORGE - Se agrega metodo getAccessByIdUser() para
 *           obtener todos los accesos del usuario
 * @modified 10-02-1015 - JORGE - Se agrega metodo getHeaderNotificationByIdUser
 * @modified 16-02-1015 - JORGE - Se agrega campo profile al dao_notification,
 *           getAllUserAvailableMessage
 * @modified 19-02-2015 - JORGE - Se actualizan daos de User y Access
 * @modified 20-02-2015 - JORGE - Se crean metodos para los accesos del usuario
 * @modified 24-03-2015 - JORGE - Se crean metodos para los accesos del usuario
 * @modified 25-03-2015 - JORGE - Se lanza exception en
 *           getAccessSupplierByIdUser cuando no hay datos
 * @modified 25-03-2015 - JORGE - Se crea metodos de finder usuario
 * @modified 10-07-2015 - Sergio - Se crea metodos de finder calendar
 * @modified 07-08-2015 - Sergio - Se agrega metodo getMessageByIdGroup() para
 *           obtener todos los mensajes de un grupo
 */
public class UserServiceImpl implements UserService, UserDetailsService {

	protected final Log logger = LogFactory.getLog(getClass());

	private UserDAO dao_user;
	private AccessDAO dao_access;
	private ToDoDAO dao_todo;
	private UserMessageDAO dao_user_message;
	private MessageDAO dao_message;
	private MessageGroupDAO dao_message_group;
	private UserGroupDAO dao_user_group;
	private GroupDAO dao_group;
	private CalendarDAO dao_calendar;
	private FinderMessageDAO finder_message;
	private FinderUserMessageDAO finder_user_message;
	private NotificationDAO dao_notification;
	private FinderNotificationDAO finder_notification;
	private FinderToDoDAO finder_todo;
	private FinderAccessDAO finder_access;
	private FinderUserDAO finder_user;
	private FinderCalendarDAO finder_calendar;
	private AttachmentDAO dao_attachment;
	private FinderAttachmentDAO finder_attachment;
	private String PATH_ROOT;
	private FinderUserGroupDAO finder_user_group;
	private FinderMessageGroupDAO finder_message_group;
	private FinderGroupDAO finder_group;

	public void setFinder_group(FinderGroupDAO finder_group) {
		this.finder_group = finder_group;
	}

	public void setFinder_message_group(
			FinderMessageGroupDAO finder_message_group) {
		this.finder_message_group = finder_message_group;
	}

	public void setFinder_user_group(FinderUserGroupDAO finder_user_group) {
		this.finder_user_group = finder_user_group;
	}

	public void setPATH_ROOT(String pATH_ROOT) {
		PATH_ROOT = pATH_ROOT;
	}

	public void setFinder_attachment(FinderAttachmentDAO finder_attachment) {
		this.finder_attachment = finder_attachment;
	}

	public void setDao_attachment(AttachmentDAO dao_attachment) {
		this.dao_attachment = dao_attachment;
	}

	public void setFinder_calendar(FinderCalendarDAO finder_calendar) {
		this.finder_calendar = finder_calendar;
	}

	public void setFinder_todo(FinderToDoDAO finder_todo) {
		this.finder_todo = finder_todo;
	}

	public void setDao_user(UserDAO dao_user) {
		this.dao_user = dao_user;
	}

	public void setDao_access(AccessDAO dao_access) {
		this.dao_access = dao_access;
	}

	public void setDao_todo(ToDoDAO dao_todo) {
		this.dao_todo = dao_todo;
	}

	public void setDao_user_message(UserMessageDAO dao_user_message) {
		this.dao_user_message = dao_user_message;
	}

	public void setDao_message(MessageDAO dao_message) {
		this.dao_message = dao_message;
	}

	public void setFinder_message(FinderMessageDAO finder_message) {
		this.finder_message = finder_message;
	}

	public void setFinder_user_message(FinderUserMessageDAO finder_user_message) {
		this.finder_user_message = finder_user_message;
	}

	public void setDao_notification(NotificationDAO dao_notification) {
		this.dao_notification = dao_notification;
	}

	public void setFinder_notification(FinderNotificationDAO finder_notification) {
		this.finder_notification = finder_notification;
	}

	public void setFinder_access(FinderAccessDAO finder_access) {
		this.finder_access = finder_access;
	}

	public void setFinder_user(FinderUserDAO finder_user) {
		this.finder_user = finder_user;
	}

	public void setDao_calendar(CalendarDAO dao_calendar) {
		this.dao_calendar = dao_calendar;
	}

	public void setDao_message_group(MessageGroupDAO dao_message_group) {
		this.dao_message_group = dao_message_group;
	}

	public void setDao_user_group(UserGroupDAO dao_user_group) {
		this.dao_user_group = dao_user_group;
	}

	public void setDao_group(GroupDAO dao_group) {
		this.dao_group = dao_group;
	}

	/*
	 * Acegi security
	 */
	public UserDetails loadUserByUsername(String login)
			throws UsernameNotFoundException, DataAccessException {
		// Se define criterio de busqueda
		UserExample example = new UserExample();
		example.createCriteria().andUserloginEqualTo(login);

		// Se obtiene lista de objetos
		List<User> lista = dao_user.selectByExample(example);

		// Se controla que exista el usuario
		if (lista.size() == 0)
			throw new UsernameNotFoundException("Usuario, userlogin=" + login
					+ " no existe");

		UserAcegi usuario = new UserAcegi(lista.get(0));

		// Se retorna usuarioacegi
		return usuario;
	}

	/*
	 * User
	 */
	public Long addUser(AddUserDTO dto) {
		// Se crea el objeto de dominio
		// a partir de los datos del dto

		User record = new User();
		record.setActive(dto.getActive());
		record.setCreated(dto.getCreated());
		record.setEmail(dto.getEmail());
		record.setLogin(dto.getLogin());
		record.setModified(dto.getModified());
		record.setOrderby(dto.getOrderby());
		record.setUsername(dto.getUsername());
		record.setPasswd(dto.getPasswd());
		record.setProfile(dto.getProfile());
		record.setUserlogin(dto.getUserlogin());
		record.setImage(dto.getImage());
		record.setSuperuser(dto.getSuperuser());
		record.setJob(dto.getJob());
		record.setPhone1(dto.getPhone1());
		record.setPhone2(dto.getPhone2());

		// Se persiste el objeto
		Long id = dao_user.insert(record);
		
		//Se busca a soporte
		UserSearchCriteria cri = new UserSearchCriteria();
		cri.setProfile("SOP");
		long id_sop = this.getUserByCriteria(cri).get(0).getId_user();
		
		SendMessageDTO dtoMsg = new SendMessageDTO();
		dtoMsg.setMessage("Te damos la bienvenida a LogistikApp, puedes enviarme mensajes en cualquier momento del día y en breve contestare para ayudarte, Saludos SOP");
		dtoMsg.setId_user_to(id);
		dtoMsg.setId_user(id_sop);
		
		try {
			this.sendMesssage(dtoMsg);
		} catch (IOException ex) {
			this.logger.error(null, ex);
		}

		// Se retorna id
		return id;
	}

	
	public Long addUserEmail(AddUserDTO dto) {
		// Se crea el objeto de dominio
		// a partir de los datos del dto
		Long id = addUser(dto);

		// Seteo de información para enviado de Email
		EmailDataDTO edto = new EmailDataDTO();
		edto.setFrom("soporte@logistikapp.com");
		edto.setName_from("Soporte LogistikApp");
		edto.setTo(dto.getEmail());
		edto.setBcc("soporte@logistikapp.com");
		edto.setSubject("Bienvenido a LogistikApp");
		edto.setUsername(dto.getUsername());
		edto.setUserlogin(dto.getUserlogin());
		edto.setPasswd(dto.getPasswd());
		edto.setEmail(dto.getEmail());
		edto.setProfile(dto.getProfile());

		SendEmail.SendEmailInterno(edto);
		// Se retorna id
		return id;
	}

	
	public User getUserById(Long id_user) throws UserNotFoundException {
		// Se controla que exista el objeto de dominio
		User record = dao_user.selectByPrimaryKey(id_user);
		if (record == null)
			throw new UserNotFoundException("User, id=" + id_user
					+ " no existe");

		// Se retorna objeto de dominio
		return record;
	}

	
	public int updUser(UpdUserDTO dto) throws UserNotFoundException {
		// Se controla que exista el objeto de dominio
		User record = dao_user.selectByPrimaryKey(dto.getId_user());
		if (record == null)
			throw new UserNotFoundException("User, id=" + dto.getId_user()
					+ " no existe");

		// Se setea objeto de dominio
		// a partir de los datos del dto
		record.setActive(dto.getActive());
		record.setCreated(dto.getCreated());
		record.setEmail(dto.getEmail());
		record.setLogin(dto.getLogin());
		record.setModified(dto.getModified());
		record.setOrderby(dto.getOrderby());
		record.setPasswd(dto.getPasswd());
		record.setProfile(dto.getProfile());
		record.setUserlogin(dto.getUserlogin());
		record.setUsername(dto.getUsername());
		record.setImage(dto.getImage());
		record.setSuperuser(dto.getSuperuser());
		record.setJob(dto.getJob());
		record.setPhone1(dto.getPhone1());
		record.setPhone2(dto.getPhone2());

		// Se persiste el objeto
		int rows = dao_user.updateByPrimaryKeySelective(record);

		// Se retorna la cantidad de registros actualizados
		return rows;
	}

	
	public List<User> getAllUser() {
		UserExample example = new UserExample();
		example.setOrderByClause("active desc, orderby, username");
		return dao_user.selectByExample(example);
	}

	@SuppressWarnings("null")
	
	public int delUserById(Long id_user) throws UserNotFoundException {
		User user = dao_user.selectByPrimaryKey(id_user);
		if (user == null)
			throw new UserNotFoundException("El usuario con id "
					+ user.getId_user() + " no existe");

		return dao_user.deleteByPrimaryKey(id_user);
	}

	
	public void getAvaibleLoginUserByCriteria(AvaibleLoginUserSearchCriteria dto)
			throws LoginUserDuplicateException {
		UserExample example = new UserExample();
		if (dto.getId_user() == null) {
			example.createCriteria().andActiveEqualTo(dto.getActive())
					.andUserloginEqualTo(dto.getUserlogin());
		} else {
			example.createCriteria().andActiveEqualTo(dto.getActive())
					.andUserloginEqualTo(dto.getUserlogin())
					.andId_userNotEqualTo(dto.getId_user());
		}
		List<User> list = dao_user.selectByExample(example);
		if (list.size() > 0)
			throw new LoginUserDuplicateException("El usuario ya existe");
	}

	
	public List<User> getAllUserAvailableMessage(Long id_user) {
		UserExample example = new UserExample();
		example.createCriteria().andId_userNotEqualTo(id_user);
		example.setOrderByClause("orderby, username");
		return dao_user.selectByExample(example);
	}

	/*
	 * Access
	 */

	public Long addAcces(AddAccessDTO dto) {
		// Se crea el objeto de dominio
		// a partir de los datos del dto
		Access record = new Access();
		record.setActive(dto.getActive());
		record.setCreated(dto.getCreated());
		record.setId_empresa(dto.getId_empresa());
		record.setId_retail(dto.getId_retail());
		record.setId_store(dto.getId_store());
		record.setId_supplier(dto.getId_supplier());
		record.setId_user(dto.getId_user());
		record.setLogin(dto.getLogin());
		record.setModified(dto.getModified());

		// Se persiste el objeto
		Long id = dao_access.insert(record);
		return id;
	}

	public int updAccess(UpdAccessDTO dto) throws AccessNotFoundException {
		// Se controla que exista el objeto de dominio
		Access record = dao_access.selectByPrimaryKey(dto.getId_access());
		if (record == null)
			throw new AccessNotFoundException("Access, id="
					+ dto.getId_access() + " no existe");

		// Se setea el objeto de dominio
		// a partir de los datos del dto
		record.setActive(dto.getActive());
		record.setCreated(dto.getCreated());
		record.setId_empresa(dto.getId_empresa());
		record.setId_retail(dto.getId_retail());
		record.setId_store(dto.getId_store());
		record.setId_supplier(dto.getId_supplier());
		record.setId_user(dto.getId_user());
		record.setLogin(dto.getLogin());
		record.setModified(dto.getModified());

		// Se persiste el objeto
		int row = dao_access.updateByPrimaryKeySelective(record);

		// Se retorna la cantidad de registros actualizados
		return row;
	}

	
	public Access getAccessById(Long id_access) throws AccessNotFoundException {
		// Se controla que exista el objeto de dominio
		Access record = dao_access.selectByPrimaryKey(id_access);
		if (record == null)
			throw new AccessNotFoundException("Access id_access=" + id_access
					+ " no existe");

		return record;
	}

	
	public List<Access> getAccessByIdUser(Long id_user)
			throws UserNotFoundException, AccessNotFoundException {
		// Se controla que el usuario exista
		User record = dao_user.selectByPrimaryKey(id_user);
		if (record == null)
			throw new UserNotFoundException("Usuario id_user=" + id_user
					+ " no existe");

		// Se define criterio de busqueda
		AccessExample example = new AccessExample();
		example.createCriteria().andId_userEqualTo(id_user);
		example.setOrderByClause("id_supplier, id_retail, id_store");

		// Se obtiene todos los accesos para el usuario
		List<Access> list = dao_access.selectByExample(example);

		// Se controla que el usuario tenga definido algun acceso
		if (list.size() == 0)
			throw new AccessNotFoundException("Usuario id_user=" + id_user
					+ " no tiene definido ningun acceso definido");

		// Se retorna listado de objetos
		return list;
	}

	
	public void doProcessAccess(DoProcessAccessDTO dto)
			throws AccessErrorException {
		// Se controla si existe el objeto de dominio Access
		Access access = dao_access.selectByPrimaryKey(dto.getId_access());

		// Si no existe se crea
		if (access == null) {
			AddAccessDTO dtoi = new AddAccessDTO();
			dtoi.setId_empresa(dto.getId_empresa());
			dtoi.setId_user(dto.getId_user());
			dtoi.setLogin(dto.getLogin());
			dtoi.setActive(dto.getActive());
			dtoi.setCreated(dto.getFecha());
			if (dto.getId_supplier() != null)
				dtoi.setId_supplier(dto.getId_supplier());
			if (dto.getId_retail() != null)
				dtoi.setId_retail(dto.getId_retail());
			if (dto.getId_store() != null)
				dtoi.setId_store(dto.getId_store());
			// Se persiste el objeto
			addAcces(dtoi);
		} else {
			// Si existe s�lo se actualiza
			UpdAccessDTO dtou = new UpdAccessDTO();
			dtou.setId_access(access.getId_access());
			dtou.setActive(dto.getActive());
			dtou.setLogin(dto.getLogin());
			dtou.setModified(dto.getFecha());
			// Se persiste el objeto
			try {
				updAccess(dtou);
			} catch (AccessNotFoundException e) {
				throw new AccessErrorException(
						"Error, no se pudo actualizar Access, id="
								+ dto.getId_access());
			}
		}

	}

	/*
	 * Finder/Access
	 */

	
	public List<AccessTinyDTO> getAccessSupplierByIdUser(Long id_user)
			throws AccessNotFoundException {
		// Se obtiene lista de accesos
		List<AccessTinyDTO> list = finder_access
				.getAccessSupplierByIdUser(id_user);

		// Se controla que hayan datos
		// sino se lanza exception
		if (list.size() == 0)
			throw new AccessNotFoundException("Usuario con id =" + id_user
					+ " acceso suspendido");

		// Se retorna lista
		return list;
	}

	
	public List<AccessTinyExtDTO> getAccessSupplierExtByIdUser(Long id_user) {
		return finder_access.getAccessSupplierExtByIdUser(id_user);
	}

	
	public int delAccessByIdRetail(Long id_retail) {
		return finder_access.delAccessByIdRetail(id_retail);
	}

	
	public int delAccessByIdSupplier(Long id_supplier) {
		return finder_access.delAccessByIdSupplier(id_supplier);
	}

	
	public int delAccessByIdUser(Long id_user) {
		return finder_access.delAccessByIdUser(id_user);
	}

	/*
	 * Todo
	 */

	
	public Long addToDo(AddToDoDTO dto) {
		// Se crea el objeto de dominio
		// a partir de los datos del dto
		ToDo record = new ToDo();
		record.setActive(dto.getActive());
		record.setCreated(dto.getCreated());
		record.setId_user(dto.getId_user());
		record.setModified(dto.getModified());
		record.setPriority(dto.getPriority());
		record.setTodo(dto.getTodo());

		// Se persiste el objeto
		Long id = dao_todo.insert(record);
		return id;
	}

	
	public int updToDo(UpdToDoDTO dto) throws ToDoNotFoundException {
		// Se controla que exista el objeto de dominio
		ToDo record = dao_todo.selectByPrimaryKey(dto.getId_todo());
		if (record == null)
			throw new ToDoNotFoundException("Todo id_todo=" + dto.getId_todo()
					+ " no existe");

		// Se setea el objeto de dominio
		// a partir de los datos del dto
		record.setActive(dto.getActive());
		record.setCreated(dto.getCreated());
		record.setId_user(dto.getId_user());
		record.setModified(dto.getModified());
		record.setPriority(dto.getPriority());
		record.setTodo(dto.getTodo());

		// Se persiste el objeto
		int row = dao_todo.updateByPrimaryKeySelective(record);
		return row;
	}

	
	public ToDo getToDoById(Long id_todo) throws ToDoNotFoundException {
		// Se controla que exista el objeto de dominio
		ToDo record = dao_todo.selectByPrimaryKey(id_todo);
		if (record == null)
			throw new ToDoNotFoundException("Todo id_todo=" + id_todo
					+ " no existe");

		// Se retorna objeto
		return record;
	}

	
	public List<ToDo> getToDoByIdUser(Long id_user) {
		// Se define criterio de busqueda
		ToDoExample example = new ToDoExample();
		example.createCriteria().andId_userEqualTo(id_user);
		example.setOrderByClause("priority, active desc, todo");

		// Se obtiene listado de objetos de dominio {ToDo}
		List<ToDo> list = dao_todo.selectByExample(example);
		return list;
	}

	
	public List<ToDo> getToDoByIdUserAndPriority(Long id_user, String priority) {
		// Se define criterio de busqueda
		ToDoExample example = new ToDoExample();
		example.createCriteria().andId_userEqualTo(id_user)
				.andPriorityEqualTo(priority);
		example.setOrderByClause("priority, active desc, todo");

		// Se obtiene listado de objetos de dominio {ToDo}
		List<ToDo> list = dao_todo.selectByExample(example);
		return list;
	}

	
	public int delToDoById(Long id_todo) throws ToDoNotFoundException {
		return dao_todo.deleteByPrimaryKey(id_todo);
	}

	/*
	 * UserMessage
	 */

	
	public UserMessage getUserMessageById(Long id_user_message)
			throws UserMessageNotFoundException {
		// Se controla que el objeto de dominio exista
		UserMessage record = dao_user_message
				.selectByPrimaryKey(id_user_message);
		if (record == null)
			throw new UserMessageNotFoundException("UserMessage id="
					+ id_user_message + " no existe");

		// Se retorna objeto de dominio
		return record;
	}

	
	public Long addUserMessage(AddUserMessageDTO dto) {
		// Se crea el objeto de dominio
		// a partir de los datos del dto
		UserMessage record = new UserMessage();
		record.setCreated(dto.getCreated());
		record.setId_user(dto.getId_user());
		record.setId_user_to(dto.getId_user_to());

		// Se persiste el objeto
		Long id = dao_user_message.insert(record);

		// Se retorna id
		return id;
	}

	
	public int updUserMessage(UpdUserMessageDTO dto)
			throws UserMessageNotFoundException {
		// Se controla que el objeto de dominio exista
		UserMessage record = dao_user_message.selectByPrimaryKey(dto
				.getId_user_message());
		if (record == null)
			throw new UserMessageNotFoundException("El usuarioMessage con id "
					+ dto.getId_user_message() + " no existe.");

		// Se setea el objeto de dominio
		// con los datos del dto
		record.setCreated(dto.getCreated());
		record.setId_user(dto.getId_user());
		record.setId_user_to(dto.getId_user_to());

		// Se persiste el objeto
		int rows = dao_user_message.updateByPrimaryKeySelective(record);

		// Se retorna la cantidad de
		// registros actualizados
		return rows;
	}

	
	public int delUserMessageById(Long id_user_message)
			throws UserMessageNotFoundException {
		// Se controla que el objeto de dominio exista
		UserMessage record = dao_user_message
				.selectByPrimaryKey(id_user_message);
		if (record == null)
			throw new UserMessageNotFoundException("UserMessage, id="
					+ id_user_message + " no existe");

		// Se persiste el objeto
		int rows = dao_user_message.deleteByPrimaryKey(id_user_message);

		// Se retorna la cantidad de registros eliminados
		return rows;
	}

	/*
	 * Message
	 */

	public Long addMessage(AddMessageDTO dto) {
		// Se crea el objeto de dominio
		// a partir de los datos del dto
		Message record = new Message();
		record.setCreated(dto.getCreated());
		record.setId_user_message(dto.getId_user_message());
		record.setMessage(dto.getMessage());
		record.setRead(dto.getRead());
		record.setSend(dto.getSend());
		record.setId_user(dto.getId_user());

		// Se persiste el objeto
		Long id = dao_message.insert(record);

		// Se retorna id objeto dominio
		return id;
	}

	
	public Message getMessageById(Long id_message) {
		return dao_message.selectByPrimaryKey(id_message);
	}

	
	public int updMessage(UpdMessageDTO dto) throws MessageNotFoundException {
		// Se obtiene y se controla que exista objeto de dominio
		Message record = dao_message.selectByPrimaryKey(dto.getId_message());
		if (record == null)
			throw new MessageNotFoundException("Message, id_message="
					+ dto.getId_message() + " no existe");

		// Se setea objeto de dominio
		// con los datos del dto
		record.setCreated(dto.getCreated());
		record.setId_user_message(dto.getId_user_message());
		record.setMessage(dto.getMessage());
		record.setRead(dto.getRead());
		record.setSend(dto.getSend());
		record.setId_user(dto.getId_user());

		// Se persiste el objeto
		int rows = dao_message.updateByPrimaryKeySelective(record);

		// Se retorna el total de
		// registros actualizados
		return rows;
	}

	
	public int delMessageById(Long id_message) throws MessageNotFoundException {
		// Se obtiene y se controla que exista objeto de dominio
		Message record = dao_message.selectByPrimaryKey(id_message);
		if (record == null)
			throw new MessageNotFoundException("Message, id=" + id_message
					+ " no existe");

		// Se persiste el objeto
		List<Attachment> atts = getAllAttachmentByIdMessage(id_message);
		for (Attachment att : atts) {
			att.getFile();
			File file = new File(PATH_ROOT + att.getFile());
			if (file.delete())
				logger.info("archivo " + att.getFile() + " borrado");
		}
		finder_attachment.delAttachmentByIdMessage(record.getId_message());
		int rows = dao_message.deleteByPrimaryKey(id_message);

		// Se retorna el total de registros eliminados
		return rows;
	}

	
	public Long sendMesssage(SendMessageDTO dto) throws IOException {
		Long id_message_from = null;
		Long id_message_to = null;
		Long id_attachment = null;
		AddMessageDTO message = null;

		// Se prepara el registro de la conversacion que
		// envia "from" recibe "to"
		Long fromto_id;
		UserMessageExample fromto_ex = new UserMessageExample();
		fromto_ex.createCriteria().andId_userEqualTo(dto.getId_user())
				.andId_user_toEqualTo(dto.getId_user_to());
		List<UserMessage> fromto_list = dao_user_message
				.selectByExample(fromto_ex);
		if (fromto_list.size() == 0) {
			UserMessage fromto_record = new UserMessage();
			fromto_record.setCreated(new Date());
			fromto_record.setId_user(dto.getId_user());
			fromto_record.setId_user_to(dto.getId_user_to());
			fromto_id = dao_user_message.insert(fromto_record);
		} else {
			fromto_id = (fromto_list.get(0)).getId_user_message();
		}

		logger.debug("fromto_id=" + fromto_id);
		logger.debug("(message) FROM->TO, id=" + fromto_id);
		// Se prepara el registro para el mensaje que
		// envia "from" recibe "to"
		message = new AddMessageDTO();
		message.setCreated(new Date());
		message.setId_user_message(fromto_id);
		message.setMessage(dto.getMessage());
		message.setRead("S");
		message.setSend("S");
		// Se persiste el objeto
		id_message_from = addMessage(message);

		logger.debug("Message, id=" + id_message_from
				+ " enviado from -> to... ok!");

		// Se prepara el registro de la conversacion que
		// recibe "to" envia "from"
		Long tofrom_id;
		UserMessageExample tofrom_ex = new UserMessageExample();
		tofrom_ex.createCriteria().andId_userEqualTo(dto.getId_user_to())
				.andId_user_toEqualTo(dto.getId_user());
		List<UserMessage> tofrom_list = dao_user_message
				.selectByExample(tofrom_ex);
		if (tofrom_list.size() == 0) {
			UserMessage tofrom_record = new UserMessage();
			tofrom_record.setCreated(new Date());
			tofrom_record.setId_user(dto.getId_user_to());
			tofrom_record.setId_user_to(dto.getId_user());
			tofrom_id = dao_user_message.insert(tofrom_record);
		} else {
			tofrom_id = (tofrom_list.get(0)).getId_user_message();
		}
		logger.debug("(message) TO->FROM, id=" + tofrom_id);
		// Se prepara el registro para el mensaje que
		// recibe "to" envia "from"
		message = new AddMessageDTO();
		message.setCreated(new Date());
		message.setId_user_message(tofrom_id);
		message.setMessage(dto.getMessage());
		message.setRead("N");
		message.setSend("N");
		// Se persiste el objeto
		id_message_to = addMessage(message);
		logger.debug("Message, id=" + id_message_to
				+ " recibido from -> to... ok!");
		if (dto.getAttachment_files() != null
				&& dto.getAttachment_files().size() > 0) {
			for (AttachmentDTO att : dto.getAttachment_files()) {
				String originalFilename = att.getFile();
				att.setId_message(id_message_from);
				// ----- se crea el archivo adjunto para usuario from--- //
				String filename = originalFilename.replaceFirst("idmessage==",
						id_message_from + "==");
				att.setFile(filename);
				filename = PATH_ROOT + filename;
				File out = new File(filename);
				File folder = new File(att.getFolder());
				boolean creararchivo = folder.mkdirs();
				logger.debug("SE CREO LA RUTA: --->" + creararchivo);
				FileCopyUtils.copy(att.getIn(), out);

				// ----- se persiste el registro ----/
				id_attachment = dao_attachment.insert(att);
				logger.debug("Attachment, id=" + id_attachment
						+ " adjunto from -> to..." + id_message_from + " ok!");

				// ------------------------------------------------------------------//

				att.setId_message(id_message_to);
				// ----- se crea el archivo adjunto para usuario to --- //
				filename = originalFilename.replaceFirst("idmessage==",
						id_message_to + "==");
				att.setFile(filename);
				filename = PATH_ROOT + filename;
				out = new File(filename);
				folder = new File(att.getFolder());
				creararchivo = folder.mkdirs();
				logger.debug("SE CREO LA RUTA: --->" + creararchivo);
				FileCopyUtils.copy(att.getIn(), out);

				id_attachment = dao_attachment.insert(att);

				logger.debug("Attachment, id=" + id_attachment
						+ " adjunto from -> to..." + id_message_to + " ok!");
			}
		}

		return fromto_id;
	}

	
	public List<Message> getMessageByIdUser(Long id_user) {
		MessageExample example = new MessageExample();
		example.createCriteria().andId_userEqualTo(id_user);

		return dao_message.selectByExample(example);
	}

	/*
	 * Finder/message
	 */

	
	public int deleteMessageByIdUserMessage(Long id_user_message)
			throws MessageNotFoundException {
		MessageExample example = new MessageExample();
		example.createCriteria().andId_user_messageEqualTo(id_user_message);
		int row = 0;
		List<Message> list = dao_message.selectByExample(example);
		if (list.size() < 1)
			throw new MessageNotFoundException("mensaje con id_user_message="
					+ id_user_message + " no existe");
		else {
			for (Message msg : list) {
				row += delMessageById(msg.getId_message());
			}
		}
		return row;
	}

	
	public List<MessageDTO> getMessageByIdUserMessage(Long id_user_message) {
		return finder_message.getMessageByIdUserMessage(id_user_message);
	}

	
	public Integer newMessageTotal(NewMessageTotalDTO dto) {
		return finder_message.newMessageTotal(dto);
	}

	
	public List<lastNewMessageDTO> lastNewMessage(
			lastNewMessageSearchCriteria dto) {
		return finder_message.lastNewMessage(dto);
	}

	
	public int updateReadByIdUserMessage(Long id_user_message) {
		return finder_message.updateReadByIdUserMessage(id_user_message);
	}

	
	public int delMessageByIdUser(Long id_user) {
		return finder_message.delMessageByIdUser(id_user);
	}

	/*
	 * Finder/user/message
	 */

	
	public List<UserUsertoDTO> getRelUserUserto(RelUserUsertoDTO dto) {
		return finder_user_message.getRelUserUserto(dto);
	}

	
	public List<UserMessageDTO> getUserMessageByIdUser(Long id_user) {
		return finder_user_message.getUserMessageByIdUser(id_user);
	}

	
	public List<UserMessageDTO> getUserLastMessageByIdUser(Long id_user) {
		return finder_user_message.getUserLastMessageByIdUser(id_user);
	}

	
	public List<UserMessage> getUserMessageByIdUserId_UserTo(Long id_user) {
		return finder_user_message.getUserMessageByIdUserId_UserTo(id_user);
	}

	
	public int delUserMessageByIdUserIdUserTo(Long id_user) {
		return finder_user_message.delUserMessageByIdUserIdUserTo(id_user);
	}

	/*
	 * Notification
	 */
	public Long addNotification(AddNotificationDTO dto) {
		// Se crea un objeto de dominio
		// con los datos del dto
		Notification record = new Notification();
		record.setCreated(dto.getCreated());
		record.setId_user(dto.getId_user());
		record.setId_supplier(dto.getId_supplier());
		record.setMessage(dto.getMessage());
		record.setPriority(dto.getPriority());
		record.setIcon(dto.getIcon());
		record.setProfile(dto.getProfile());
		record.setLevel(dto.getLevel());
		record.setLink(dto.getLink());
		// Se persiste el objeto
		Long id = dao_notification.insert(record);
		return id;
	}

	
	public int updNotification(UpdNotificationDTO dto)
			throws NotificationNotFoundException {
		// Se controla que el objeto de dominio exista
		Notification record = dao_notification.selectByPrimaryKey(dto
				.getId_notification());
		if (record == null)
			throw new NotificationNotFoundException("Notification id="
					+ dto.getId_notification() + " no existe");

		// Se setea el objeto de dominio
		// con los datos del dto
		record.setCreated(dto.getCreated());
		record.setId_user(dto.getId_user());
		record.setId_supplier(dto.getId_supplier());
		record.setMessage(dto.getMessage());
		record.setPriority(dto.getPriority());
		record.setIcon(dto.getIcon());
		record.setProfile(dto.getProfile());
		record.setLevel(dto.getLevel());
		record.setLink(dto.getLink());
		// Se persiste el objeto
		int row = dao_notification.updateByPrimaryKeySelective(record);
		return row;
	}

	
	public Notification getNotificationById(Long id_notification)
			throws NotificationNotFoundException {
		// Se controla que exista el objeto de dominio
		Notification record = dao_notification
				.selectByPrimaryKey(id_notification);
		if (record == null)
			throw new NotificationNotFoundException("Notification id="
					+ id_notification + " no existe");

		// Se retorna objeto de dominio
		return record;
	}

	
	public List<Notification> getAllNotification() {
		NotificationExample example = new NotificationExample();
		return dao_notification.selectByExample(example);
	}

	/*
	 * Finder/notification
	 */

	
	public List<NotificationDTO> getNotificationByCriteria(
			NotificationSearchCriteria dto) {
		return finder_notification.getNotificationByCriteria(dto);
	}

	
	public List<Notification> getNotificationTodayByCriteria(
			NotificationTodaySearchCriteria dto) {
		return finder_notification.getNotificationTodayByCriteria(dto);
	}

	
	public int delNotificationByIdSupplier(Long id_supplier) {
		return finder_notification.delNotificationByIdSupplier(id_supplier);
	}

	
	public int delNotificationByIdUser(Long id_user) {
		return finder_notification.delNotificationByIdUser(id_user);
	}

	/*
	 * Finder/todo
	 */

	
	public List<ToDoDTO> getToDoActive(ToDoActiveSearchCriteria dto) {
		return finder_todo.getToDoActive(dto);
	}

	
	public Integer getToDoActiveTotal(ToDoActiveSearchCriteria dto) {
		return finder_todo.getToDoActiveTotal(dto);
	}

	
	public int delTodoByIdUser(Long id_user) {
		return finder_todo.delTodoByIdUser(id_user);
	}

	/*
	 * Finder/header/notification
	 */

	
	public HeaderNotificationDTO getHeaderNotificationByIdUser(Long id_user)
			throws UserNotFoundException {

		// Se obtiene objeto de dominio del usuario
		User user = getUserById(id_user);
		AccessExample example = new AccessExample();
		example.createCriteria().andId_userEqualTo(id_user);
		List<Access> access = dao_access.selectByExample(example);
		Long id_supplier = 0L;
		if (access.size() > 0)
			id_supplier = access.get(0).getId_supplier();
		if (id_supplier == null)
			id_supplier = 0L;
		// Notificaciones
		NotificationTodaySearchCriteria dto = new NotificationTodaySearchCriteria();
		dto.setFecha(new Date());
		dto.setProfile(user.getProfile());
		if (!id_supplier.equals(0))
			dto.setId_supplier(id_supplier);
		dto.setId_user(id_user);
		List<Notification> alert = finder_notification.getNotificationTodayByCriteria(dto);
		Integer alert_qty = alert.size();
		// Mensajes
		List<MessageNoReadDTO> message = finder_message.getListMessageNoReadByIdUser(id_user);
		Integer message_qty = finder_message.getQtyMessageNoReadByIdUser(id_user);

		// Tareas
		List<ToDo> todo = finder_todo.getTodoActiveByIdUser(id_user);
		Integer todo_qty = todo.size();

		// Se setea el dto con todos los datos de notificaciones, mensajes y
		// tareas
		HeaderNotificationDTO header = new HeaderNotificationDTO();
		// Alertas
		header.setAlert_qty(alert_qty);
		header.setAlert(alert);
		// Mensajes
		header.setMessage(message);
		header.setMessage_qty(message_qty);
		// Tareas
		header.setTodo_qty(todo_qty);
		header.setTodo(todo);

		// Se retorna dto
		return header;
	}

	
	public List<User> getAvailableUserByCriteria(UserSearchCriteria dto) {
		return finder_user_message.getAvailableUserByCriteria(dto);
	}

	/*
	 * Finder/user
	 */

	
	public List<UserDTO> getUserByCriteria(UserSearchCriteria dto) {
		return finder_user.getUserByCriteria(dto);
	}
	
	
	public List<Integer> getSupIdsBySuppliers(Long id_supplier) {
		return finder_user.getSupIdsBySuppliers(id_supplier);
	}

	/*
	 * Administration
	 */

	
	public List<AdminUserDTO> getAdminUserListByCriteria(AdminUserSearchCriteria dto) {
		return finder_user.getAdminUserListByCriteria(dto);
	}

	/*
	 * Calendar
	 */

	public Long addCalendar(AddCalendarDTO dto) {
		return dao_calendar.insert(dto);
	}

	
	public int updCalendar(UpdCalendarDTO dto) throws CalendarNotFoundException {
		Calendar record = dao_calendar.selectByPrimaryKey(dto.getId_calendar());
		if (record == null)
			throw new CalendarNotFoundException("Calendar id = "
					+ dto.getId_calendar() + " no existe.");
		return dao_calendar.updateByPrimaryKeySelective(dto);
	}

	
	public Calendar getCalendarById(Long id_calendar)
			throws CalendarNotFoundException {
		Calendar record = dao_calendar.selectByPrimaryKey(id_calendar);
		if (record == null)
			throw new CalendarNotFoundException("Calendar id = " + id_calendar
					+ " no existe.");
		return dao_calendar.selectByPrimaryKey(id_calendar);
	}

	
	public List<Calendar> getAllCalendar() {
		CalendarExample example = new CalendarExample();
		List<Calendar> list = dao_calendar.selectByExample(example);
		return list;
	}

	
	public int delCalendarById(Long id_calendar)
			throws CalendarNotFoundException {
		Calendar record = dao_calendar.selectByPrimaryKey(id_calendar);
		if (record == null)
			throw new CalendarNotFoundException("calendar id " + id_calendar
					+ " no existe.");
		return dao_calendar.deleteByPrimaryKey(id_calendar);
	}

	
	public List<Calendar> getCalendarNoSend() {

		CalendarExample example = new CalendarExample();
		example.createCriteria().andCal_sendEqualTo("N");
		List<Calendar> list = dao_calendar.selectByExample(example);

		return list;
	}

	/*
	 * FINDER CALENDAR
	 */
	
	public List<CalendarDTO> getEventByCriteria(EventSearchCriteria dto) {
		List<CalendarDTO> list = finder_calendar.getEventByCriteria(dto);
		return list;
	}

	
	public int delCalendarByIdSupplier(Long id_supplier) {
		return finder_calendar.delCalendarByIdSupplier(id_supplier);
	}

	
	public User getUserByEmail(String email) {
		UserExample example = new UserExample();
		example.createCriteria().andEmailEqualTo(email);
		List<User> users = dao_user.selectByExample(example);
		User usr = null;
		if (users.size() != 0)
			usr = users.get(0);

		return usr;
	}

	
	public void getDuplicateUserByEmail(String email)
			throws EmailUserDuplicateException {
		UserExample example = new UserExample();
		example.createCriteria().andEmailEqualTo(email);
		List<User> users = dao_user.selectByExample(example);
		if (users.size() != 0)
			throw new EmailUserDuplicateException(
					"Correo Electr&oacute;nico utilizado por otro usuario");
	}

	
	public int delCalendarByIdUser(Long id_user) {
		return finder_calendar.delCalendarByIdUser(id_user);
	}

	/*
	 * Attachment
	 */
	
	public Attachment getAttachmentById(Long id_attachment)
			throws AttachmentNotFoundException {
		Attachment attachment = dao_attachment
				.selectByPrimaryKey(id_attachment);
		if (attachment == null)
			throw new AttachmentNotFoundException("attachment, id="
					+ id_attachment + " no existe");
		return attachment;
	}

	
	public List<Attachment> getAllAttachment() {
		AttachmentExample example = new AttachmentExample();
		List<Attachment> attachments = dao_attachment.selectByExample(example);
		return attachments;
	}

	
	public int delAttachmentById(Long id_attachment)
			throws AttachmentNotFoundException {
		int row = dao_attachment.deleteByPrimaryKey(id_attachment);
		if (row == 0)
			throw new AttachmentNotFoundException("attachment, id="
					+ id_attachment + " no existe");
		return row;
	}

	
	public int updAttachmentById(Attachment record)
			throws AttachmentNotFoundException {
		int row = dao_attachment.updateByPrimaryKeySelective(record);
		if (row == 0)
			throw new AttachmentNotFoundException("attachment, id="
					+ record.getId_attachment() + " no existe");
		return row;
	}

	
	public List<Attachment> getAllAttachmentByIdMessage(Long id_message) {
		AttachmentExample example = new AttachmentExample();
		example.createCriteria().andId_messageEqualTo(id_message);
		List<Attachment> attachment = dao_attachment.selectByExample(example);
		return attachment;
	}

	
	public Long addAttachment(Attachment attachment) {
		Long id_attachment = dao_attachment.insert(attachment);
		return id_attachment;
	}

	
	public int delAttachmentByIdMessage(Long id_message) {
		int rows = finder_attachment.delAttachmentByIdMessage(id_message);
		return rows;
	}

	/*
	 * MessageGroup
	 */
	public Long addMessageGroup(AddMessageGroupDTO dto) {
		return dao_message_group.insert(dto);
	}

	
	public MessageGroup getMessageGroupById(Long id_msgGroup)
			throws MessageGroupNotFoundException {
		MessageGroup record = dao_message_group.selectByPrimaryKey(id_msgGroup);
		if (record == null)
			throw new MessageGroupNotFoundException("MessageGroup id = "
					+ id_msgGroup + " no existe.");
		return dao_message_group.selectByPrimaryKey(id_msgGroup);

	}

	
	public int delMessageGroupById(Long id_msgGroup)
			throws MessageGroupNotFoundException {
		MessageGroup record = dao_message_group.selectByPrimaryKey(id_msgGroup);
		if (record == null)
			throw new MessageGroupNotFoundException("MessageGroup id = "
					+ id_msgGroup + " no existe.");
		return dao_message_group.deleteByPrimaryKey(id_msgGroup);
	}

	
	public int updMessageGroup(UpdMessageGroupDTO dto)
			throws MessageGroupNotFoundException {
		MessageGroup record = dao_message_group.selectByPrimaryKey(dto
				.getId_message_group());
		if (record == null)
			throw new MessageGroupNotFoundException("MessageGroup id = "
					+ dto.getId_message_group() + " no existe.");
		return dao_message_group.updateByPrimaryKeySelective(dto);
	}

	
	public List<MessageGroup> getAllMessageGroup() {
		MessageGroupExample example = new MessageGroupExample();
		List<MessageGroup> list = dao_message_group.selectByExample(example);
		return list;
	}

	
	public int delMessageGroupByIdMessage(Long id_message) {
		return finder_message_group.delMessageGroupByIdMessage(id_message);
	}

	/*
	 * Group
	 */
	public Long addGroup(AddGroupDTO dto) {
		return dao_group.insert(dto);
	}

	
	public Group getGroupById(Long id_group) throws GroupNotFoundException {
		Group record = dao_group.selectByPrimaryKey(id_group);
		if (record == null)
			throw new GroupNotFoundException("Group id = " + id_group
					+ " no existe.");
		return dao_group.selectByPrimaryKey(id_group);
	}

	
	public int delGroupById(Long id_group) throws GroupNotFoundException {
		Group record = dao_group.selectByPrimaryKey(id_group);
		if (record == null)
			throw new GroupNotFoundException("Group id = " + id_group
					+ " no existe.");
		return dao_group.deleteByPrimaryKey(id_group);
	}

	
	public int updGroup(UpdGroupDTO dto) throws GroupNotFoundException {
		Group record = dao_group.selectByPrimaryKey(dto.getId_group());
		if (record == null)
			throw new GroupNotFoundException("Group id = " + dto.getId_group()
					+ " no existe.");
		return dao_group.updateByPrimaryKeySelective(dto);

	}

	
	public List<Group> getAllGroup() {
		GroupExample example = new GroupExample();
		List<Group> list = dao_group.selectByExample(example);
		return list;
	}

	
	public List<Group> getGroupByIdUser(Long id_user) {
		GroupExample example = new GroupExample();
		example.createCriteria().andId_userEqualTo(id_user);
		return dao_group.selectByExample(example);
	}

	/**
	 * finder group
	 */

	
	public int delGroupByIdUser(Long id_user) {
		return finder_group.delGroupByIdUser(id_user);
	}

	/*
	 * UserGroup
	 */
	
	public Long addUserGroup(AddUserGroupDTO dto) {
		return dao_user_group.insert(dto);
	}

	
	public UserGroup getUserGroupById(Long id_userGroup)
			throws UserGroupNotFoundException {
		UserGroup record = dao_user_group.selectByPrimaryKey(id_userGroup);
		if (record == null)
			throw new UserGroupNotFoundException("UserGroup id = "
					+ id_userGroup + " no existe.");
		return dao_user_group.selectByPrimaryKey(id_userGroup);

	}

	
	public int delUserGroupById(Long id_userGroup)
			throws UserGroupNotFoundException {
		UserGroup record = dao_user_group.selectByPrimaryKey(id_userGroup);
		if (record == null)
			throw new UserGroupNotFoundException("UserGroup id = "
					+ id_userGroup + " no existe.");
		return dao_user_group.deleteByPrimaryKey(id_userGroup);
	}

	
	public int updUserGroup(UpdUserGroupDTO dto)
			throws UserGroupNotFoundException {
		UserGroup record = dao_user_group.selectByPrimaryKey(dto
				.getId_user_group());
		if (record == null)
			throw new UserGroupNotFoundException("UserGroup id = "
					+ dto.getId_user_group() + " no existe.");
		return dao_user_group.updateByPrimaryKeySelective(dto);

	}

	
	public List<UserGroup> getAllUserGroup() {
		UserGroupExample example = new UserGroupExample();
		List<UserGroup> list = dao_user_group.selectByExample(example);
		return list;
	}

	
	public List<MessageGroupDTO> getMessageByIdGroup(Long id_group) {

		List<MessageGroupDTO> list = finder_message
				.getMessageByIdGroup(id_group);
		return list;
	}

	public List<UserMessageGrpDTO> getUserLastMsgGroByIdUser(Long id_user) {
		return finder_user_message.getUserLastMsgGroByIdUser(id_user);
	}

	/*
	 * Finder/userGroup
	 */

	
	public List<UserGroupDTO> getUserByIdGroup(Long id_group)
			throws UserGroupNotFoundException {
		return (List<UserGroupDTO>) finder_user_group
				.getUserByIdGroup(id_group);
	}

	
	public int delMessageGrpByIdGrp(Long id_group)
			throws MessageGroupNotFoundException {
		List<MessageGroup> list = finder_message_group
				.getMessageGroupByIdGroup(id_group);
		if (list == null)
			throw new MessageGroupNotFoundException("el id " + id_group
					+ " no existe.");
		return finder_message_group.delMessageGrpByIdGrp(id_group);
	}

	
	public List<MessageGroup> getMessageGroupByIdGroup(Long id_group) {
		return finder_message_group.getMessageGroupByIdGroup(id_group);
	}

	
	public int delUserGrpByIdGrp(Long id_group) {
		return finder_user_group.delUserGrpByIdGrp(id_group);
	}

	
	public int delUserGrpByIdUser(Long id_user) {
		return finder_user_group.delUserGrpByIdUser(id_user);
	}

	
	public int delAccessByIdStore(Long id_store) {
		return finder_access.delAccessByIdStore(id_store);
	}

	
	public int getCountUserBySupplier(Long id_supplier) {
		AccessExample example = new AccessExample();
		example.createCriteria().andId_supplierEqualTo(id_supplier);
		return dao_access.countByExample(example);
	}

	
	public Quantities getQtyAlertAndMessagesByIdUser(NotificationTodaySearchCriteria dto) {
		return finder_user.getQtyAlertAndMessagesByIdUser(dto);
	}

	
	public List<UserDTO> getDisponibleDrivers(DRICriteria criteria) {
		return finder_user.getDisponibleDrivers(criteria);
	}

	
	public MetricsAdm getMetricsAdm() {
		return finder_user.getMetricsAdm();
	}

	
	public List<User> getUserWithOutStockByIdSupplier(Long id_supplier) {
		return finder_user.getUserWithOutStockByIdSupplier(id_supplier);
	}

}