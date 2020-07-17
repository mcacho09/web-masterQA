package com.retailsbs.logistikapp.user.test.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.acegisecurity.userdetails.UserDetails;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

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
import com.retailsbs.logistikapp.user.domain.UserGroup;
import com.retailsbs.logistikapp.user.domain.UserMessage;
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
import com.retailsbs.logistikapp.user.dto.CalendarDTO;
import com.retailsbs.logistikapp.user.dto.DRICriteria;
import com.retailsbs.logistikapp.user.dto.DoProcessAccessDTO;
import com.retailsbs.logistikapp.user.dto.EventSearchCriteria;
import com.retailsbs.logistikapp.user.dto.HeaderNotificationDTO;
import com.retailsbs.logistikapp.user.dto.MessageDTO;
import com.retailsbs.logistikapp.user.dto.MessageGroupDTO;
import com.retailsbs.logistikapp.user.dto.MetricsAdm;
import com.retailsbs.logistikapp.user.dto.NewMessageTotalDTO;
import com.retailsbs.logistikapp.user.dto.NotificationDTO;
import com.retailsbs.logistikapp.user.dto.NotificationSearchCriteria;
import com.retailsbs.logistikapp.user.dto.NotificationTodaySearchCriteria;
import com.retailsbs.logistikapp.user.dto.RelUserUsertoDTO;
import com.retailsbs.logistikapp.user.dto.SendMessageDTO;
import com.retailsbs.logistikapp.user.dto.ToDoActiveSearchCriteria;
import com.retailsbs.logistikapp.user.dto.ToDoDTO;
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
import com.retailsbs.logistikapp.user.dto.UserGroupDTO;
import com.retailsbs.logistikapp.user.dto.UserMessageDTO;
import com.retailsbs.logistikapp.user.dto.UserMessageGrpDTO;
import com.retailsbs.logistikapp.user.dto.UserSearchCriteria;
import com.retailsbs.logistikapp.user.dto.UserUsertoDTO;
import com.retailsbs.logistikapp.user.dto.lastNewMessageDTO;
import com.retailsbs.logistikapp.user.dto.lastNewMessageSearchCriteria;
import com.retailsbs.logistikapp.user.exception.AccessErrorException;
import com.retailsbs.logistikapp.user.exception.AccessNotFoundException;
import com.retailsbs.logistikapp.user.exception.AttachmentNotFoundException;
import com.retailsbs.logistikapp.user.exception.CalendarNotFoundException;
import com.retailsbs.logistikapp.user.exception.GroupNotFoundException;
import com.retailsbs.logistikapp.user.exception.MessageGroupNotFoundException;
import com.retailsbs.logistikapp.user.exception.MessageNotFoundException;
import com.retailsbs.logistikapp.user.exception.NotificationNotFoundException;
import com.retailsbs.logistikapp.user.exception.ToDoNotFoundException;
import com.retailsbs.logistikapp.user.exception.UserGroupNotFoundException;
import com.retailsbs.logistikapp.user.exception.UserMessageNotFoundException;
import com.retailsbs.logistikapp.user.exception.UserNotFoundException;
import com.retailsbs.logistikapp.user.service.UserService;
import com.retailsbs.logistikapp.user.test.base.BaseTestJunit;

public class UserServiceTest extends BaseTestJunit {

	@Autowired
	private UserService service;
	
	/*
	 * UsuarioAcegy
	 */
	public void test_loadUserByUsername() {
		UserDetails ud = service.loadUserByUsername("admin");
		assertNotNull(ud);
		
		logger.debug("username="+ud.getUsername());
		logger.debug("password="+ud.getPassword());
	}
	
	/*
	 * User
	 */
	public void test_addUser() {
		AddUserDTO dto = new AddUserDTO();
		dto.setLogin("admin");
		dto.setUserlogin("prueba");
		dto.setUsername("prueba username");
		dto.setPasswd("prueba");
		dto.setProfile("ADM");
		dto.setEmail("jorge@logistikapp.com");
		dto.setCreated(new Date());
		dto.setActive("S");
		dto.setOrderby(10);
		dto.setImage("//image.jpeg");
		dto.setJob("job");
		dto.setPhone1("014499765432");
		dto.setPhone2("4499234567");
		dto.setSuperuser("S");

		Long id = service.addUser(dto);
		assertNotNull(id);
	}
	
	
	public void test_addUserEmail() {
		AddUserDTO dto = new AddUserDTO();
		dto.setLogin("laslas");
		dto.setUserlogin("laslas");
		dto.setUsername("laslas laslas");
		dto.setPasswd("laslas");
		dto.setProfile("DRI");
		dto.setEmail("darmz21@gmail.com");
		dto.setCreated(new Date());
		dto.setActive("S");
		dto.setOrderby(10);
		dto.setImage("img/users/default.png");
		dto.setJob("job");
		dto.setPhone1("4491962728");
		dto.setSuperuser("N");

		Long id = service.addUserEmail(dto);
		assertNotNull(id);
	}
	public void test_updUser() throws UserNotFoundException {
		UpdUserDTO dto = new UpdUserDTO();
		dto.setId_user(101L);
		dto.setImage("nueva imagen");
		dto.setJob("new job");
		dto.setPhone1("new");
		dto.setPhone2("new");
		
		int i = service.updUser(dto);
		assertNotNull(i);
	}
	
	public void test_getUserById() throws UserNotFoundException {
		User usr = service.getUserById(10011L);
		
		assertNotNull(usr);
	}
	
	public void test_getAllUser() {
		List<User> user = service.getAllUser();
		assertNotNull(user);
	}
	
	public void test_delUserById() throws UserNotFoundException {
		int row = service.delUserById(100L);
		assertTrue(row>0);
	}
	
	/*
	 * Access
	 */
	
	public void test_addAcces() {
		AddAccessDTO dto = new AddAccessDTO();
		dto.setActive("S");
		dto.setId_retail(102L);
		dto.setId_store(102l);
		dto.setId_user(1L);
		dto.setLogin("admin");
		
		Long id = service.addAcces(dto);
		assertNotNull(id);
	}
	
	public void test_updAccess() throws AccessNotFoundException {
		UpdAccessDTO dto = new UpdAccessDTO();
		dto.setId_access(2L);
		dto.setLogin("user");
		
		int i = service.updAccess(dto);
		assertNotNull(i);
	}
	
	public void test_getAccessByIdUser() throws UserNotFoundException, AccessNotFoundException {
		List<Access> list = service.getAccessByIdUser(1L);
		assertNotNull(list);
	}
	
	public void test_doProcessAccess() throws AccessErrorException {
		DoProcessAccessDTO dto = new DoProcessAccessDTO();
		dto.setFecha( new Date() );
		dto.setActive("S");
		dto.setLogin("TEST");
		dto.setId_user( 100L );
		dto.setId_empresa( 1L );
		dto.setId_supplier( 2L );
		service.doProcessAccess(dto);
	}
	
	
	/*
	 * ToDo
	 */
	
	public void test_addToDo() {
		AddToDoDTO dto = new AddToDoDTO();
		dto.setActive("S");
		dto.setCreated(new Date());
		dto.setId_user(200L);
		dto.setModified(new Date());
		dto.setPriority("A");
		dto.setTodo("mensaje de servicio 4");
		
		Long id = service.addToDo(dto);
		assertNotNull(id);
	}
	
	public void test_updToDo() throws ToDoNotFoundException {
		UpdToDoDTO dto = new UpdToDoDTO();
		dto.setId_todo(10001L);
		dto.setTodo("upd service todo");
		
		int i = service.updToDo(dto);
		assertTrue(i>0);
	}
	
	public void test_getToDoById() throws ToDoNotFoundException {
		ToDo todo = service.getToDoById(10002L);
		assertNotNull(todo);
	}

	public void test_getToDoByIdUser() {
		List<ToDo> list = service.getToDoByIdUser(1L);
		assertNotNull(list);
	}

	public void test_getToDoByIdUserAndPriority() {
		List<ToDo> list = service.getToDoByIdUserAndPriority(1L, "1");
		assertNotNull(list);
	}
	
	public void test_delToDoById() throws ToDoNotFoundException {
		int i = service.delToDoById(10008L);
		assertTrue(i>0);
	}
	
	/*
	 * UserMessage
	 */
	
	public void test_addUserMessage() {
		AddUserMessageDTO dto = new AddUserMessageDTO();
		dto.setCreated(new Date());
		dto.setId_user(2L);
		dto.setId_user_to(1L);
		
		Long id = service.addUserMessage(dto);
		assertNotNull(id);
	}
	
	public void test_updUserMessage() throws UserMessageNotFoundException {
		UpdUserMessageDTO dto = new UpdUserMessageDTO();
		dto.setId_user_message(3L);
		dto.setId_user(10008L);
		
		int row = service.updUserMessage(dto);
		assertTrue(row>0);
		
	}
	
	public void test_getUserMessageById() throws UserMessageNotFoundException {
		UserMessage as = service.getUserMessageById(3L);
		assertNotNull(as);
	}
	
	public void test_getUserLastMessageByIdUser() throws UserMessageNotFoundException {
		List<UserMessageDTO> as = service.getUserLastMessageByIdUser(225L);
		System.out.println(as.get(0).getRead());
	}
	
	
	public void test_delUserMessage() throws UserMessageNotFoundException {
		int i = service.delUserMessageById(3L);
		assertTrue(i>0);
	}
	
	/*
	 * Message
	 */
	
	public void test_addMessage() {
		AddMessageDTO dto = new AddMessageDTO();
		dto.setCreated(new Date());
		dto.setId_user_message(1L);
		dto.setSend("S");
		dto.setMessage("mensaje 1 de 1 a 2");
		dto.setRead("S");
		
		Long id = service.addMessage(dto);
		assertNotNull(id);
	}
	
	public void test_updMessage() throws MessageNotFoundException {
		UpdMessageDTO dto = new UpdMessageDTO();
		dto.setCreated(new Date());
		dto.setId_message(3L);
		dto.setMessage("otro mensaje");
		
		int i = service.updMessage(dto);
		assertTrue(i>0);
	}
	
	public void test_getMessageById() {
		Message mes = service.getMessageById(3L);
		assertNotNull(mes);
	}
	
	public void test_delMessageById() throws MessageNotFoundException {
		int a = service.delMessageById(162L);
		assertTrue(a>0);
	}
	
	public void test_getRelUserUserto() {
		RelUserUsertoDTO dto = new RelUserUsertoDTO();
		dto.setId_user(100L);
		dto.setId_user_to(2L);
		
		List<UserUsertoDTO> list = service.getRelUserUserto(dto);
		assertNotNull(list);
	}
	
	public void test_deleteMessageByIdUserMessage() throws MessageNotFoundException {
		int row = service.deleteMessageByIdUserMessage(30L);
		assertTrue(row > 0);
	}
	
	public void sendMessage() throws IOException {
		SendMessageDTO dto = new SendMessageDTO();
		dto.setId_user(2L);
		dto.setId_user_to(1L);
		List<AttachmentDTO>lista = new ArrayList<AttachmentDTO>();
		AttachmentDTO a = new AttachmentDTO();
		a.setCreated(new Date());
		a.setFile("idmessage;file.png");
		a.setFolder("uploads/1/messages");
		a.setId_message(38L);	
		byte[] in = new byte[100];
		a.setIn(in );
		lista.add(a);
		dto.setAttachment_files(lista);
		dto.setMessage("Mensaje de prueba 2");
		service.sendMesssage(dto);
		logger.debug("Mensaje enviado");		
	}

	public void updateReadMessageByIdUserTest() {
		int n = service.updateReadByIdUserMessage(1L);
		logger.debug("Servicio update read by id user --> "+ n + " registros cambiados ");		
	}
	
	public void test_getMessageByIdUser() {
		List<Message> list = service.getMessageByIdUser(4L);
		assertNotNull(list);
	}
	
	
	/*
	 * Finder/user/message
	 */
	public void test_getMessageByIdUserMessage() {
		List<UserMessageDTO> list = service.getUserMessageByIdUser(225L);
		assertNotNull(list);
	}
	
	public void test_getUserMessageByIdUserId_UserTo() {
		List<UserMessage> list = service.getUserMessageByIdUserId_UserTo(7L);
		assertNotNull(list);
	}
	
	public void test_delUserMessageByIdUserIdUserTo() {
		int row = service.delUserMessageByIdUserIdUserTo(100L);
		assertTrue(row>0);
	}
	
	/*
	 * Finder/message
	 */
	public void getMessageByIdUserMessage() {
		List<MessageDTO> list = service.getMessageByIdUserMessage(205L);
		for (MessageDTO i : list) {
			System.out.println(i.getUser_from_id() + " -- " + i.getMessage() + " " + i.getUser_to_id());
		}
	}	
	public void test_getMessageByIdGroup() {
		List<MessageGroupDTO> list = service.getMessageByIdGroup(4L);
		assertNotNull(list);
	}
	
	public void test_delMessageByIdUser() {
		int row = service.delMessageByIdUser(100L);
		assertTrue(row>0);
	}
	
	/*
	 * Notification
	 */
	public void test_addNotification() {
		AddNotificationDTO dto = new AddNotificationDTO();
		dto.setCreated(new Date());
		dto.setId_user(1L);
		dto.setId_supplier(1L);
		dto.setMessage("Prueba para links");
		dto.setPriority("1");
		dto.setIcon("icon");
		dto.setLink("home.htm");
		
		Long id = service.addNotification(dto);
		assertNotNull(id);
	}
	
	
	public void test_updNotification() throws NotificationNotFoundException {
		UpdNotificationDTO dto = new UpdNotificationDTO();
		dto.setId_notification(21L);
		dto.setMessage("nuevo mensaje service upd");
		
		int row = service.updNotification(dto);
		assertTrue(row>0);
	}
	
	public void test_getNotificationById() throws NotificationNotFoundException {
		Notification n = service.getNotificationById(5669L);
		System.out.println("---> " + n.getLink());
	}
	
	public void test_getAllNotification() {
		List<Notification> list = service.getAllNotification();
		assertNotNull(list);
	}
	
	public void test_newMessageTotal() {
		NewMessageTotalDTO dto = new NewMessageTotalDTO();
		dto.setId_user(100L);
		dto.setRead("S");
		
		int a = service.newMessageTotal(dto);
		assertNotNull(a);
	}
	
	public void test_lastNewMessage() {
		lastNewMessageSearchCriteria dto = new lastNewMessageSearchCriteria();
		dto.setId_user(1L);
		dto.setLimit(3);
		dto.setRead("N");
		dto.setCaracteres(8);
		
		List<lastNewMessageDTO> list = service.lastNewMessage(dto);
		assertNotNull(list);
	}
	
	/*
	 * Finder Notification
	 */
	
	public void test_getNotificationByCriteria() throws UserNotFoundException {
		HeaderNotificationDTO header = service.getHeaderNotificationByIdUser(6L);
		logger.debug("Notificaciones --> " + header.getAlert().size());
		assertNotNull(header);
	}
	
	public void test_getNotificationTodayByCriteria() throws UserNotFoundException {
		
		NotificationSearchCriteria dto = new NotificationSearchCriteria();
		dto.setLimit(10);
		dto.setId_supplier(1L);
		dto.setId_user(2L);
		dto.setProfile("ADM");
		List<NotificationDTO> header = service.getNotificationByCriteria(dto);
		logger.debug("Notificaciones --> ");
		assertNotNull(header);
	}
	
	public void test_delNotificationByIdSupplier() {
		int row = service.delNotificationByIdSupplier(2L);
		assertTrue(row>0);
	}
	
	public void test_delNotificationByIdUser() {
		int row = service.delNotificationByIdUser(100L);
		assertTrue(row>0);
	}
	
	/*
	 * finder todo
	 */
	
	public void test_getToDoActive() {
		ToDoActiveSearchCriteria dto = new ToDoActiveSearchCriteria();
		dto.setActive("S");
		dto.setLimit(3);
		dto.setLimit_char(8);
		
		List<ToDoDTO> list = service.getToDoActive(dto);
		assertNotNull(list);
	}
	
	public void test_getToDoActiveTotal() {
		ToDoActiveSearchCriteria dto = new ToDoActiveSearchCriteria();
		dto.setActive("S");
		
		int total = service.getToDoActiveTotal(dto);
		assertTrue(total>0);
	}
	
	public void test_delTodoByIdUser() {
		int row = service.delTodoByIdUser(100L);
		assertTrue(row>0);
	}

	/*
	 * Finder/Access
	 */
	public void test_getAccessSupplierExtByIdUser() {
		List<AccessTinyExtDTO> list = service.getAccessSupplierExtByIdUser(1L);
		assertNotNull(list);
	}
	
	public void test_delAccessByIdSupplier() {
		int row = service.delAccessByIdSupplier(2L);
		assertTrue(row>0);
	}
	
	public void test_delAccessByIdUser() {
		int row = service.delAccessByIdUser(100L);
		assertTrue(row>0);
	}
	
	/*
	 * Finder User
	 */
	public void test_getUserByCriteria() {
		UserSearchCriteria dto = new UserSearchCriteria();
		dto.setId_supplier(1L);
		dto.setProfile("DRI");
		List<UserDTO> list = service.getUserByCriteria(dto);
		assertNotNull(list);
	}
	
	public void test_getUserByEmail() {
		String email ="chekovlg@gmail.com";
		User usr = service.getUserByEmail(email );
		assertNotNull(usr);
	}
	
	public void test_getDisponibleDrivers() {
		DRICriteria criteria = new DRICriteria();
		criteria.setDate(new Date());
		criteria.setId_supplier(166l);
		
		List<UserDTO> list = service.getDisponibleDrivers(criteria);
		for (UserDTO u : list) {
			System.out.println("---> " + u.getUsername());
		}
		
		assertTrue(list != null && list.size() == 3);
	}
	
	@Test
	public void test_getSupIdsBySuppliers() {
		List<Integer> ids = service.getSupIdsBySuppliers(166l);
		assertNotNull(ids);
		assertNotNull(ids.size() > 0);
	}
	
	/*
	 * Administration
	 */
	public void test_getAdminUserList() {
		AdminUserSearchCriteria dto = new AdminUserSearchCriteria();
		dto.setId_supplier(166l);
		List<AdminUserDTO> list = service.getAdminUserListByCriteria(dto);
		assertNotNull(list);
		assertTrue(list.size() > 0);
	}
	
	/*
	 * Calendar
	 */
	public void test_addCalendar() {
		AddCalendarDTO dto = new AddCalendarDTO();
		dto.setCal_alert(1);
		dto.setCal_end(new Date());
		dto.setCal_level("P");
		dto.setCal_location("cal_location");
		dto.setCal_start(new Date());
		dto.setCal_title("cal_title");
		dto.setCreated(new Date());
		dto.setId_supplier(1L);
		dto.setId_user(3L);
		dto.setLogin("login");
		dto.setModified(new Date());
		dto.setCal_send("N");
		
		Long id = service.addCalendar(dto);
		assertNotNull(id);
	}
	
	public void test_updCalendar() throws CalendarNotFoundException {
		UpdCalendarDTO dto = new UpdCalendarDTO();
		dto.setId_calendar(14L);
		dto.setCal_title("otro upd dos");
		
		int i = service.updCalendar(dto);
		assertTrue(i>0);
	}
	
	public void test_getAllCalendar() {
		List<Calendar> list = service.getAllCalendar();
		assertNotNull(list);
	}

	public void test_delCalendarById() throws CalendarNotFoundException {
		int i = service.delCalendarById(2L);
		assertTrue(i>0);
	}

	/*
	 * finder calendar
	 */
	public void test_delCalendarByIdSupplier() {
		int row = service.delCalendarByIdSupplier(2L);
		assertTrue(row>0);
	}
	
	public void test_getEventByCriteria() {
		EventSearchCriteria dto = new EventSearchCriteria();
		dto.setId_supplier(106L);
		dto.setId_user(10L);
		List<CalendarDTO> list = service.getEventByCriteria(dto);
		assertNotNull(list);
	}

	public void test_getCalendarNoSend() {
		List<Calendar> list = service.getCalendarNoSend();
		assertNotNull(list);
	}
	
	public void test_delCalendarByIdUser() {
		int row = service.delCalendarByIdUser(100L);
		assertTrue(row>0);
	}
	
	/*
	 * MessageGroup 
	 */
	public void test_AddMessageGroup() {
		AddMessageGroupDTO dto = new AddMessageGroupDTO();
		dto.setId_message(297L);
		dto.setId_group(1L);
		
		Long id = service.addMessageGroup(dto);
		assertNotNull(id);
	}
	public void test_getMessageGroupById() throws MessageGroupNotFoundException{
		MessageGroup mesGp = service.getMessageGroupById(1L);
		assertNotNull(mesGp);
	}
	public void test_delMessageGroupById() throws MessageGroupNotFoundException{
		int i = service.delMessageGroupById(2L);
		assertTrue(i>0);
	}
	public void test_updMessageGroup() throws MessageGroupNotFoundException{
		UpdMessageGroupDTO dto = new UpdMessageGroupDTO();
		dto.setId_message_group(1L);
		dto.setId_message(297L);
		dto.setId_group(1L);
		
		int i = service.updMessageGroup(dto);
		assertTrue(i>0);
	}
	public void test_getAllMessageGroup(){
		List<MessageGroup> list = service.getAllMessageGroup();
		assertNotNull(list);
	}
	
	
	/*
	 * Group 
	 */
	public void test_AddGroup() {
		AddGroupDTO dto = new AddGroupDTO();
		dto.setName("Grupo Test 3");
		dto.setId_user(6L);
		
		Long id = service.addGroup(dto);
		assertNotNull(id);
	}
	public void test_getGroupById() throws GroupNotFoundException{
		Group group = service.getGroupById(1L);
		assertNotNull(group);
	}
	public void test_delGroupById() throws GroupNotFoundException{
		int i = service.delGroupById(3L);
		assertTrue(i>0);
	}
	public void test_updGroup() throws GroupNotFoundException{
		UpdGroupDTO dto = new UpdGroupDTO();
		dto.setId_group(2L);
		dto.setName("Grupo Test 2 Upd");
		dto.setId_user(5L);
		
		int i = service.updGroup(dto);
		assertTrue(i>0);
	}
	public void test_getAllGroup(){
		List<Group> list = service.getAllGroup();
		assertNotNull(list);
	}

	public void test_getGroupByIdUser() {
		List<Group> list = service.getGroupByIdUser(4L);
		assertNotNull(list);
	}
	
	/**
	 * finder group
	 */
	
	public void test_delGroupByIdUser() {
		int row = service.delGroupByIdUser(100L);
		assertTrue(row>0);
	}

	/*
	 * UserGroup 
	 */
	public void test_AddUserGroup() {
		AddUserGroupDTO dto = new AddUserGroupDTO();
		dto.setId_user(5L);
		dto.setId_group(1L);
		
		Long id = service.addUserGroup(dto);
		assertNotNull(id);
	}
	public void test_getUserGroupById() throws UserGroupNotFoundException{
		UserGroup usrGp = service.getUserGroupById(1L);
		assertNotNull(usrGp);
	}
	public void test_delUserGroupById() throws UserGroupNotFoundException{
		int i = service.delUserGroupById(2L);
		assertTrue(i>0);
	}
	public void test_updUserGroup() throws UserGroupNotFoundException{
		UpdUserGroupDTO dto = new UpdUserGroupDTO();
		dto.setId_user_group(1L);
		dto.setId_user(5L);
		dto.setId_group(2L);
		
		int i = service.updUserGroup(dto);
		assertTrue(i>0);
	}
	public void test_getAllUserGroup(){
		List<UserGroup> list = service.getAllUserGroup();
		assertNotNull(list);
	}
	
	public void test_getUserLastMsgGroByIdUser() {
		List<UserMessageGrpDTO> list = service.getUserLastMsgGroByIdUser(4L);
		assertNotNull(list);
	}
	/*
	 * Finder UserGroup
	 */
	
	public void test_getUserByIdGroup() throws UserGroupNotFoundException {
		
		List<UserGroupDTO> list = service.getUserByIdGroup(3L);
		assertNotNull(list);
	}

	public void test_delUserGrpByIdGrp() {
		int row = service.delUserGrpByIdGrp(41L);
		assertTrue(row>0);
	}
	
	public void test_delUserGrpByIdUser() {
		int row = service.delUserGrpByIdUser(4L);
		assertTrue(row>0);
	}

	/*
	 * Attachment
	 */
	
	public void test_addAttachment() {
		Attachment record = new Attachment();
		record.setCreated(new Date());
		record.setFile("prueba.png");
		record.setId_message(38L);
		Long id = service.addAttachment(record);
		logger.debug("Se inserto --> "+id+" Attachment OK");
	}

	public void test_updateByPrimaryKeySelective() throws AttachmentNotFoundException {
		
		Attachment record = new Attachment();
		record.setFile("prueba2.png");
		record.setId_message(38L);
		record.setId_attachment(1L);
		int id = service.updAttachmentById(record);
		logger.debug("Se actualizaron  --> "+id+" registros OK");
	}

	public void test_selectByPrimaryKey() throws AttachmentNotFoundException {
		Attachment id = service.getAttachmentById(1L);
		logger.debug("Se seleccionaron  --> "+id+" registros OK");
	}
	public void test_selectByExample() {
		AttachmentExample example = new AttachmentExample();
		example.createCriteria().andCreatedLessThan(new Date());
		List<Attachment> id = service.getAllAttachmentByIdMessage(1L);
		logger.debug("Se seleccionaron  --> "+id.size()+" registros OK");
		List<Attachment> all = service.getAllAttachment();		
		logger.debug("Se seleccionaron  todos --> "+all.size()+" registros OK");
	}
	
	public void delAttachmentByIdMessage() {
		service.setPATH_ROOT("/var/lib/tomcat6/webapps/prueba/");
		int rows = service.delAttachmentByIdMessage(185L);
		logger.debug("Se eliminaron --> "+rows+" registros OK");
	}
	
	/*
	 * FINDER MESSAGE GROUP
	 */
	
	public void test_getMessageGroupByIdGroup() {
		List<MessageGroup> list = service.getMessageGroupByIdGroup(41L);
		assertNotNull(list);
	}
	
	public void test_delMessageGrpByIdGrp() throws MessageGroupNotFoundException {
		int row = service.delMessageGrpByIdGrp(41L);
		assertTrue(row>0);
	}
	
	public void test_delAccessByIdStore() {
		service.delAccessByIdStore(1L);
	}
	
	public void test_delMessageGroupByIdMessage() {
		int row = service.delMessageGroupByIdMessage(626L);
		assertTrue(row>0);
	}

	
	public void getCountUserBySupplier() {
		int rows = service.getCountUserBySupplier(110L);
		assertTrue(rows>0);
	}
	
	public void getQtyAlertAndMessagesByIdUser(){
		NotificationTodaySearchCriteria dto = new NotificationTodaySearchCriteria();
		dto.setId_supplier(166L);
		dto.setId_user(255L);
		dto.setProfile("SOP");
		dto.setFecha(new Date());
		
		Quantities result = service.getQtyAlertAndMessagesByIdUser(dto);
		
		System.out.println(result.toString());
		
		assertNotNull(service.getQtyAlertAndMessagesByIdUser(dto));
	}
	
	public void test_getMetricsAdm(){
		MetricsAdm metrics = service.getMetricsAdm();
		assertNotNull(metrics);
		System.out.println(metrics.toString());
	}
	
}
