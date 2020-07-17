package com.retailsbs.logistikapp.user.service;

import java.io.IOException;
import java.util.List;

import org.acegisecurity.userdetails.UserDetails;
import org.acegisecurity.userdetails.UsernameNotFoundException;
import org.springframework.dao.DataAccessException;

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
import com.retailsbs.logistikapp.user.dto.AvaibleLoginUserSearchCriteria;
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

/**
 * Clase interfaz servicio de usuarios
 * @author jorge
 * @since 17-11-2014
 * @modified 09-02-2015 - JORGE - Se extiende un metodo para obtener notificaciones
 * @modified 14-03-2015 - JORGE - Se reestructura metodos para las notificaciones
 * @modified 26-06-2015 - JORGE - Se agrega metodos para las pantallas de administracion
 */
public interface UserService {

	/*
	 * Acegy Security
	 */
	UserDetails loadUserByUsername(String login) throws UsernameNotFoundException, DataAccessException;
	
	/*
	 * User
	 */
	/**
	 * Agrega un nuevo usuario
	 * @param dto Dto con datos del usuario
	 * @return Id usuario agregado
	 * @author jorge
	 * @since 17-11-2014
	 */
    Long addUser(AddUserDTO dto);
    
    /**
	 * Agrega un nuevo usuario con funcionalidad de email
	 * @param dto Dto con datos del usuario
	 * @return Id usuario agregado
	 * @author david
	 * @since 17-03-2016
	 */
    Long addUserEmail(AddUserDTO dto);
    
    /**
     * Obtiene un usuario por id
     * @param id_user Id usuario
     * @return Objeto de dominio {User}z
     * @throws UserNotFoundException
	 * @author jorge
	 * @since 17-11-2014
     */
    User getUserById(Long id_user) throws UserNotFoundException;
    
    /**
     * Actualiza los datos de un usuario
     * @param dto Dto con datos del usuario
     * @return Cantidad de registros modificados
     * @throws UserNotFoundException
	 * @author jorge
	 * @since 17-11-2014
     */
    int updUser(UpdUserDTO dto) throws UserNotFoundException;
    
    /**
     * Obtiene lista de todos los usuarios. activos y no activos
     * @return lista de objetos de dominio {User}
     * @author Juan Carlos Ramos Campos
     * @since 24-11-2014
     */
    List<User> getAllUser();
    
    /**
     * Elimina usuario por id_user
     * @param id_user
     * @return numero de usuarios eliminados
     * @author JuanCarlosRamosCampos
     * @throws UserNotFoundException 
     * @since 25-11-2015
     */
    int delUserById(Long id_user) throws UserNotFoundException;
    
    /**
     * Verifica que el login no este siendo usado por algun usuario activo
     * @param dto con datos de busqueda
     * @throws LoginUserDuplicateException
     * @author Juan Carlos Ramos Campos
     * @since 17-12-2014
     */
    void getAvaibleLoginUserByCriteria(AvaibleLoginUserSearchCriteria dto) throws LoginUserDuplicateException;
    
    /**
     * Obtiene una lista de todos los usuarios de los cuales se poria tener una conversacion
     * @return Lista de objetos de dominio {User}
     * @author JORGE
     * @since 16-02-2017
     */
    List<User> getAllUserAvailableMessage(Long id_user);
    
    /*
     * Access
     */
    
    /**
     * Agrega un nuevo Access
     * @param dto con datos de Acces
     * @return id de registro creado
     * @author Juan Carlos Ramos Campos
     * @since 21-11-2014
     */
    Long addAcces(AddAccessDTO dto);
    
    /**
     * Actualiza datos de Acces
     * @param dto con datos nuevos de Access
     * @return numero de registros modificados
     * @throws AccessNotFoundException
     * @author Juan Carlos Ramos Campos
     * @since 21-11-2014
     */
    int updAccess(UpdAccessDTO dto) throws AccessNotFoundException;
    
    /**
     * Obtiene un Access por medio de id
     * @param id_access id access
     * @return un objeto de dominio {Access}
     * @throws AccessNotFoundException
     * @author Juan Carlos Ramos Campos
     * @since 21-11-2014
     */
    Access getAccessById(Long id_access) throws AccessNotFoundException;
    
    /**
     * Obtiene una lista de todos los accesos para un usuario
     * @param id_user Id user
     * @return Lista de objetos de dominio {Access}
     * @throws UserNotFoundException
     * @throws AccessNotFoundException
     * @author jorge
     * @since 05-02-2015
     */
    List<Access> getAccessByIdUser(Long id_user) throws UserNotFoundException, AccessNotFoundException;
    
    /**
     * Procesa la actualizacion de acceso para un usuario
     * @param dto Dto con datos de acceso
     * @throws AccessErrorException
     * @author jorge
     * @since 24-03-2015
     */
    void doProcessAccess(DoProcessAccessDTO dto)  throws AccessErrorException;
    
    /*
     * Finder/Access
     */
	
	/**
	 * Obtiene una lista de todos los supplier que el usuario tiene acceso
	 * @param id_user Id usuario
	 * @throws AccessNotFoundException Exception cuando el usuario no tienen definido ningun acceso
	 * @return Lista de objetos {AccessTinyDTO}
	 * @author JORGE
	 * @since 20-02-2015
	 */
	public List<AccessTinyDTO> getAccessSupplierByIdUser(Long id_user) throws AccessNotFoundException;
	
	/**
	 * Obtiene una lista extendida de todos los supplier y la relacion con el usuario
	 * @param id_user Id usuario
	 * @return Lista de objetos {AccessTinyExtDTO}
	 * @author JORGE
	 * @since 24-03-2015
	 */
	public List<AccessTinyExtDTO> getAccessSupplierExtByIdUser(Long id_user);
    
	/**
	 * elimina registros de access por medio de id_retail
	 * @param id_retail
	 * @return numero de registros eliminados
	 * @author JuanCarlosRamosCampos
	 * @since 23-11-2015
	 */
	int delAccessByIdRetail(Long id_retail);
	
	/**
	 * Elimina acces por id_supplier
	 * @param id_supplier
	 * @return numero de access eliminados
	 * @author JuanCarlosRamosCampos
	 * @since 23-11-2015
	 */
	int delAccessByIdSupplier(Long id_supplier);
	
    /*
     * ToDo
     */
    
    /**
     * Agrega tareas nuevas (ToDo)
     * @param dto con datos de las nuevas tareas(ToDo)
     * @return id de registro agregado
     * @author Juan Carlos Ramos Campos
     * @since 05-12-2014
     */
    
    Long addToDo(AddToDoDTO dto);
    /**
     * Actualizacion de datos de tareas (ToDo)
     * @param dto con nuevos datos de ToDo
     * @return numeor de registros actualizados
     * @throws ToDoNotFoundException
     * @author Juan Carlos Ramos Campos
     * @since 05-12-2014
     */
    int updToDo(UpdToDoDTO dto) throws ToDoNotFoundException;
    
    /**
     * obtienen una tarea por medio de id
     * @param id_todo id todo
     * @return objeto de dominio {ToDo}
     * @author Juan Carlos Ramos Campos
     * @throws ToDoNotFoundException 
     * @since 05-12-2014
     */
    ToDo getToDoById(Long id_todo) throws ToDoNotFoundException;
    
    /**
     * Obtiene tareas(ToDo) solo del usuario
     * @param id_user
     * @return
     * @author Juan Carlos Ramos Campos
     * @since 05-12-2014
     */
    List<ToDo> getToDoByIdUser(Long id_user);
    
    /**
     * Obtiene tareas(ToDo) usuario y prioridad
     * @param id_user priority
     * @return tareas con prioridad seleccionada por un usuario
     * @author Sergio Eduardo Valenzuela Guerrero
     * @since 19-06-2015
     */
    List<ToDo> getToDoByIdUserAndPriority(Long id_user, String priority);
    
    /**
     * borra registo por medio de id_todo
     * @param id_ odo id todo
     * @return numero de regsitros borrados
     * @throws ToDoNotFoundException
     * @author Juan Carlos Ramos Campos
     * @since 08-12-2014
     */
    int delToDoById(Long id_todo) throws ToDoNotFoundException;
    
    /*
     * User Message
     */
    
    /**
     * Obtiene un objeto de dominio {UserMessage} a partir de un id
     * @param id_user_message Id
     * @return objeto de dominio {UserMessage}
     * @throws UserMessageNotFoundException
     * @author Juan Carlos Ramos Campos
     * @since 07-01-2015
     * @modified 19-02-2015 - JORGE - se completa definicion del metodo
     */
    UserMessage getUserMessageById(Long id_user_message) throws UserMessageNotFoundException;
    
    /**
     * Agrega un nuevo UserMessage
     * @param dto con datos de userMessage
     * @return id de userMessage creado
     * @author Juan Carlos Ramos Campos
     * @since 07-01-2015
     */
    
    Long addUserMessage(AddUserMessageDTO dto);
    /**
     * Modificacion de UserMessage
     * @param dto con datos de UserMessage
     * @return numero de registros modificados
     * @throws UserMessageNotFoundException
     * @author Juan Carlos Ramos Campos
     * @since 07-01-2015
     */
    
    int updUserMessage(UpdUserMessageDTO dto) throws UserMessageNotFoundException;
    /**
     * Elimina un UserMessage por id
     * @param id_user_message Id
     * @return Cantidad de registros eliminados
     * @throws UserMessageNotFoundException
     * @author Juan Carlos Ramos Campos
     * @since 07-01-2015
     */
    int delUserMessageById(Long id_user_message) throws UserMessageNotFoundException;

    /*
     * Message
     */
    
    /**
     * obtiene objeto de dominio {Message}
     * @param id_message is message
     * @return objeto de dominio {message}
     * @throws MessageNotFoundException
     * @author Juan Carlos Ramos Campos
     * @since 07-01-2015
     */
    
    Message getMessageById(Long id_message);
    /**
     * se agrega un Message
     * @param dto con datos de message
     * @return id de registro agregado
     * @author Juan Carlos Ramos Campos
     * @since 07-01-2015
     */
    
    Long addMessage(AddMessageDTO dto);
    /**
     * modifica datos de Message
     * @param dto con datos de message
     * @return numero de registros modificados
     * @throws MessageNotFoundException
     * @author Juan Carlos Ramos Campos
     * @since 07-01-2015
     */
    
    int updMessage(UpdMessageDTO dto) throws MessageNotFoundException;
    /**
     * Eliminar un Message por id
     * @param id_message Id
     * @return Cantidad de registros eliminados
     * @throws MessageNotFoundException
     * @author Juan Carlos Ramos Campos
     * @since 07-01-2015
     */
    int delMessageById(Long id_message) throws MessageNotFoundException;
    
    /**
     * Registra el envio y recepcion de un mensaje
     * @param dto Dto con datos del mensaje y conversacion
     * @author JORGE
     * @throws IOException 
     * @since 27-01-2015
     */
    Long sendMesssage(SendMessageDTO dto) throws IOException;
    /**
     * Obtiene message por id_user
     * @param id_user
     * @return lista de objetos de dominio {message}
     * @author JuanCarlosRamosCampos
     * @since 24-11-2015
     */
    List<Message> getMessageByIdUser(Long id_user);
    
    /*
     * Finder/message
     */
    
	/**
	 * A partir de una relacion entre usuarios se obtiene una lista de todos los mensajes
	 * @param id_user_message Id relacion entre usuarios
	 * @return Lista de objetos {MessageDTO}
	 * @author Juan Carlos Ramos Campos
	 * @since 08-01-2015
	 * @modified 26-01-2015 - JORGE - Se optimiza SQL
	 */
	List<MessageDTO> getMessageByIdUserMessage(Long id_user_message);
	
	/**
	 * borra mensajes por id_user_message
	 * @param id_user_message id user message
	 * @return numero de registros borrados
	 * @throws MessageNotFoundException
	 * @author Juan Carlos Ramos Campos
	 * @since 15-01-2015
	 */
	int deleteMessageByIdUserMessage(Long id_user_message) throws MessageNotFoundException;

	/**
	 * obtiene el numero todal de mensajes nuevos para el usuario
	 * @param dto con datos de busqueda
	 * @return numero total de mensajes nuevos
	 * @author Juan Carlos Ramos Campos
	 * @since 05-02-2015
	 */
	Integer newMessageTotal(NewMessageTotalDTO dto);

	/**
	 * obtiene los ultimos mensajes que se tienen para el usuario logueado
	 * @param dto con datos de busqueda
	 * @return ultimos mensajes nuevos que se tienen
	 * @author Juan Carlos Ramos Campos
	 * @since 06-02-2015
	 */
	List<lastNewMessageDTO> lastNewMessage(lastNewMessageSearchCriteria dto);
	
	/**
	 * Obtiene los mensajes que se tienen en un grupo especifico para el usuario logueado
	 * @param id_group id grupo
	 * @return Lista de objetos {MessageGroupDTO}
	 * @author Sergio Valenzuela
	 * @since 07-08-2015
	 */
	List<MessageGroupDTO> getMessageByIdGroup(Long id_group);

	/**
	 * elimina message que esten relacionados con el id_user que s ele manda
	 * @param id_user
	 * @return numero de registros borrados
	 * @author JuanCarlosRamosCampos
	 * @since 24-11-2015
	 */
	int delMessageByIdUser(Long id_user);

	/*
	 * Finder/user/message
	 */
	
	/**
	 * obtiene registros que tengan la relacion id_user y id_user_to especificos
	 * @param dto con id_user y id_user_to que se quiere obtener
	 * @return lista de objeto de dominio {UserUsertoDTO}
	 * @author Juan Carlos Ramos Campos
	 * @since 14-01-2015
	 */
	List<UserUsertoDTO> getRelUserUserto(RelUserUsertoDTO dto);
	
	/**
	 * A partir de un usuario se obtiene una lista de todas las relaciones con otros usuarios y la cantidad de mensajes no leidos
	 * @param id_user Id usuario
	 * @return Lista de objetos {UserMessageDTO}
	 * @author JORGE
	 * @since 26-01-2015
	 */
	List<UserMessageDTO> getUserMessageByIdUser(Long id_user);
	
	/**
	 * A partir de un usuario se obtiene una lista de todas las relaciones con otros usuarios con los que tiene conversacion y el 
	 * ultimo mensaje de la conversacion.
	 * @param id_user
	 * @return lista de objetos {UserMessageDTO}
	 * @author Nataly
	 * @since 24-07-2015
	 */
	List<UserMessageDTO> getUserLastMessageByIdUser(Long id_user);
	/**
	 * Obtiene todos los UserMessage que id_user o id_user_to es = id_user
	 * @param id_user
	 * @return lista de objetos de dominio {UserMessage}
	 * @author JuanCarlosRamosCampos
	 * @since 24-11-2015
	 */
	List<UserMessage> getUserMessageByIdUserId_UserTo(Long id_user);
	/**
	 * Elimina user message si id_user esta en id_user o id_user_to
	 * @param id_user
	 * @return numero de registros borrados
	 * @author JuanCarlosRamosCampos
	 * @since 24-11-2015
	 */
	int delUserMessageByIdUserIdUserTo(Long id_user);

	/*
	 * Notification
	 */

	/**
	 * Agrega notification
	 * @param dto con datos de notification
	 * @return id de notification agregada
	 * @author juan carlos
	 * @since 30-01-2015
	 */
	Long addNotification(AddNotificationDTO dto);
	
	/**
	 * Modifica datos de notification
	 * @param dto con datos de notification
	 * @return numero de registros modificados
	 * @throws NotificationNotFoundException
	 * @author juan carlos
	 * @since 30-01-2015
	 */
	int updNotification(UpdNotificationDTO dto) throws NotificationNotFoundException;
	
	/**
	 * obtiene notification por id_notification
	 * @param id_notification id notificacion
	 * @return objeto de dominio {Notification}
	 * @throws NotificationNotFoundException
	 * @author juan carlos
	 * @since 30-01-2015
	 */
	Notification getNotificationById(Long id_notification) throws NotificationNotFoundException;
	
	/**
	 * Obtiene todos los notification
	 * @return lista de objetos de dominio {Notification}
	 * @author juan carlos
	 * @since 30-01-2015
	 */
	List<Notification> getAllNotification();

	
	
	/*
	 * Finder/notification
	 */
	
	/**
	 * Obtiene el listado de notificaciones a partir de un criterio de busqueda
	 * @param dto Dto con parametros de busqueda
	 * @return Lista de objetos {NotificationDTO}
	 * @author jorge
	 * @since 14-03-2015
	 */
	List<NotificationDTO> getNotificationByCriteria(NotificationSearchCriteria dto);
	
	/**
	 * Obtiene una lista de objetos de notificaciones s�lo del d�a actual
	 * @param dto Dto con par�metros de b�squeda
	 * @return Lista de objetos de dominio {Notification}
	 * @author jorge
	 * @since 14-03-2015
	 */
	List<Notification> getNotificationTodayByCriteria(NotificationTodaySearchCriteria dto);
	
	/**
	 * Obtiene las cantidades de las notificaciones
	 * @param dto Criterio de busqueda
	 * @return Lista de objetos {QuantitiesDTO}
	 * @author David
	 * @since 01-04-2016
	 */
	Quantities getQtyAlertAndMessagesByIdUser(NotificationTodaySearchCriteria dto);
	
	/**
	 * Elimina notificaciones por id_supplier
	 * @param id_supplier
	 * @return numero de notificaciones eliminadas
	 * @author JuanCarlosRamosCampos
	 * @since 23-11-2015
	 */
	int delNotificationByIdSupplier(Long id_supplier);

	/**
	 * Elimina notificaciones por id_user
	 * @param id_user
	 * @return numero de notificaciones eliminadas
	 * @author JuanCarlosRamosCampos
	 * @since 24-11-2015
	 */
	int delNotificationByIdUser(Long id_user);

	/*
	 * Finder/header/notification
	 */

	/**
	 * Obtiene un objeto con los datos de todas las notificaciones que se mostraran
	 * en el header de la aplicacion
	 * @param id_user Id usuario
	 * @return Objeto {HeaderNotificationDTO}
	 * @throws UserNotFoundException
	 */
	HeaderNotificationDTO getHeaderNotificationByIdUser(Long id_user) throws UserNotFoundException;
	
	/*
	 * Finder/todo
	 */
	
	/**
	 * obtiene las tareas activas
	 * @param dto con datos de busqueda
	 * @return lista de objetos de dominio {ToDoDTO}
	 * @author juan carlos
	 * @since 09-02-2015
	 */
	List<ToDoDTO> getToDoActive(ToDoActiveSearchCriteria dto);
	/**
	 * obtiene numero de tareas activas
	 * @return numero de tareas activas
	 * @author juan carlos
	 * @since 09-02-2015
	 */
	Integer getToDoActiveTotal(ToDoActiveSearchCriteria dto);
	/**
	 * obtiene lista de usuarios con el mismo id_supplier
	 * @param dto con id_user y id_supplier
	 * @return lista de objetos de dominio {User}
	 * @author Nataly
	 * @since 09-06-2015
	 */
	List<User> getAvailableUserByCriteria(UserSearchCriteria dto);
	/**
	 * Elimina tareas relacionadas al id_user 
	 * @param id_user
	 * @return numero de tareas borradas
	 * @author JuanCarlosRamosCampos
	 * @since 24-11-2015
	 */
	int delTodoByIdUser(Long id_user);
	
	/*
	 * Finder/user
	 */
	
	/**
	 * Obtiene lista de usuario por criterio de busqueda
	 * @param dto con criterio de busqueda
	 * @return lista de objetos de dominio UserDTO
	 * @author jucaraca
	 * @since 23-06-2015
	 */
	List<UserDTO> getUserByCriteria(UserSearchCriteria dto);
	
	/**
	 * Obtiene un listado de conductores disponibles en la fecha indicada
	 * @param criteria
	 * @return List<UserDTO>
	 * @since 10/02/2017
	 * @author DMarin
	 */
	List<UserDTO> getDisponibleDrivers(DRICriteria criteria);
	
	/*
	 * Administration/user
	 */
	
	/**
	 * Obtiene una lista de usuarios para la pantalla de administración
	 * @param dto Criterio de busqueda
	 * @return Lista de objetos {AdminUserDTO}
	 * @author Jorge
	 * @since 26-06-2015
	 */
	List<AdminUserDTO> getAdminUserListByCriteria(AdminUserSearchCriteria dto);
	
	
	/*
	 * calendar
	 */
	
	/**
	 * agregar un objeto de dominio calendar
	 * @param dto con datos de calendar
	 * @return id de registro creado
	 * @author Juan Carlos
	 * @since 06-07-2015
	 */
	Long addCalendar(AddCalendarDTO dto);
	/**
	 * Modificar datos de calendar
	 * @param dto con datos de calendar
	 * @return numero de registro modificados
	 * @author Juan Carlos
	 * @throws CalendarNotFoundException 
	 * @since 06-07-2015
	 */
	int updCalendar(UpdCalendarDTO dto) throws CalendarNotFoundException;
	/**
	 * Obtener objeto de dominio calendar
	 * @param id_calendar id calendar
	 * @return objeto de dominio Calendar
	 * @author Juan Carlos
	 * @throws CalendarNotFoundException 
	 * @since 06-07-2015
	 */
	Calendar getCalendarById(Long id_calendar) throws CalendarNotFoundException;
	/**
	 * obtiene lista de objeto de todos los calendar
	 * @return lista de objeto de dominio calendar
	 * @author Juan Carlos
	 * @since 06-07-2015
	 */
	List<Calendar> getAllCalendar();
	/**
	 * se elimina objeto de dominio calendar por medio del id_calendar
	 * @param id_calendar
	 * @return numero de registros borrados
	 * @author Juan Carlos
	 * @throws CalendarNotFoundException 
	 * @since 08-07-2015
	 */
	int delCalendarById(Long id_calendar) throws CalendarNotFoundException;
	
	/**
	 * obtiene los eventos personales y globales de un usuario y por el id_supplier
	 * @param dto con id_user y id_supplier
	 * @return Lista de objetos {CalendarDTO}
	 * @author Sergio Valenzuela
	 * @since 10-07-2015
	 */
	List<CalendarDTO> getEventByCriteria(EventSearchCriteria dto);
	/**
	 * Obtiene una lista de los calendar que no se han enviado notificacion
	 * @return lista de objeto de dominio {CalendarNoSendDTO} 
	 * @author Juan Carlos Ramos
	 * @since 21-07-2015
	 */
	List<Calendar> getCalendarNoSend();
	/**
	 * Establece como leidos los mensajes de una conversacion
	 * @param id_user_message
	 * @return numero de registros actualizados
	 * @author Nataly
	 * @since 27-07-2015
	 */
	int updateReadByIdUserMessage(Long id_user_message);
	
	/**
	 * borrar calendar por medio de id_supplier
	 * @param id_supplier
	 * @return numero de registros eliminados
	 * @author JuanCarlosRamosCampos
	 * @since 23-11-2015
	 */
	int delCalendarByIdSupplier(Long id_supplier);
	
	/**
	 * borrar calendar por medio de id_user
	 * @param id_user
	 * @return numero de registros eliminados
	 * @author JuanCarlosRamosCampos
	 * @since 24-11-2015
	 */
	int delCalendarByIdUser(Long id_user);
	/*
	 * Message Group
	 */
	
	/**
	 * Agrega un nuevo mensaje grupal
	 * @param dto Dto con datos del MessageGroup
	 * @return Id MessageGroup agregado
	 * @author Sergio
	 * @since 03-08-2015
	 */
    Long addMessageGroup(AddMessageGroupDTO dto);
    
    /**
     * Obtiene un MessageGroup por id
     * @param id_msgGroup Id MessageGroup
     * @return Objeto de dominio {MessageGroup}
     * @throws MessageGroupNotFoundException
	 * @author Sergio
	 * @since 03-08-2015
     */
    MessageGroup getMessageGroupById(Long id_msgGroup) throws MessageGroupNotFoundException;
    
    /**
     * Elimina un MessageGroup por id
     * @param id_msgGroup Id MessageGroup
     * @return Cantidad de registros eliminados
     * @throws MessageGroupNotFoundException
	 * @author Sergio
	 * @since 03-08-2015
     */
    int delMessageGroupById(Long id_msgGroup) throws MessageGroupNotFoundException;
    
    /**
     * Actualiza los datos de un MessageGroup
     * @param dto Dto con datos de MessageGroup
     * @return Cantidad de registros modificados
     * @throws MessageGroupNotFoundException
	 * @author Sergio
	 * @since 03-08-2015
     */
    int updMessageGroup(UpdMessageGroupDTO dto) throws MessageGroupNotFoundException;
    
    /**
     * Obtiene lista de todos los MessageGroup
     * @return lista de objetos de dominio {MessageGroup}
	 * @author Sergio
	 * @since 03-08-2015
     */
    List<MessageGroup> getAllMessageGroup();
    
    
    
    /*
	 * Group
	 */
	
	/**
	 * Agrega un nuevo grupo
	 * @param dto Dto con datos del Grupo
	 * @return Id Group agregado
	 * @author Sergio
	 * @since 03-08-2015
	 */
    Long addGroup(AddGroupDTO dto);
    
    /**
     * Obtiene un Grupo por id
     * @param id_group Id Group
     * @return Objeto de dominio {Group}
     * @throws GroupNotFoundException
	 * @author Sergio
	 * @since 03-08-2015
     */
    Group getGroupById(Long id_group) throws GroupNotFoundException;
    
    /**
     * Elimina un Grupo por id
     * @param id_group Id Group
     * @return Cantidad de registros eliminados
     * @throws GroupNotFoundException
	 * @author Sergio
	 * @since 03-08-2015
     */
    int delGroupById(Long id_group) throws GroupNotFoundException;
    
    /**
     * Actualiza los datos de un Grupo
     * @param dto Dto con datos del Grupo
     * @return Cantidad de registros modificados
     * @throws GroupNotFoundException
	 * @author Sergio
	 * @since 03-08-2015
     */
    int updGroup(UpdGroupDTO dto) throws GroupNotFoundException;
    
    /**
     * Obtiene lista de todos los Grupos
     * @return lista de objetos de dominio {Group}
	 * @author Sergio
	 * @since 03-08-2015
     */
    List<Group> getAllGroup();
    /**
     * obtiene lista de group por id_user
     * @param id_user
     * @return lista de objetos de dominio {Group}
     * @author JuanCarlosRamosCampos
     * @since 24-11-2015
     */
    List<Group> getGroupByIdUser(Long id_user);
    
    /**
     * finder group
     */
    
	/**
	 * Elimina group que tengan relacion con el id_user
	 * @param id_user
	 * @return numero de group eliminados
	 * @author JuanCarlosRamosCampos
	 * @since 24-11-2015
	 */
	int delGroupByIdUser(Long id_user);
    
    /*
	 * UserGroup
	 */
	
	/**
	 * Agrega una nueva relacion user-group
	 * @param dto Dto con datos de la relacion UserGroup
	 * @return Id UserGroup agregado
	 * @author Sergio
	 * @since 03-08-2015
	 */
    Long addUserGroup(AddUserGroupDTO dto);
    
    /**
     * Obtiene un UserGroup por id
     * @param id_userGroup Id UserGroup
     * @return Objeto de dominio {UserGroup}
     * @throws UserGroupNotFoundException
	 * @author Sergio
	 * @since 03-08-2015
     */
    UserGroup getUserGroupById(Long id_userGroup) throws UserGroupNotFoundException;
    
    /**
     * Elimina un UserGroup por id
     * @param id_userGroup Id UserGroup
     * @return Objeto de dominio {UserGroup}
     * @return Cantidad de registros eliminados
	 * @author Sergio
	 * @since 03-08-2015
     */
    int delUserGroupById(Long id_userGroup) throws UserGroupNotFoundException;
    
    /**
     * Actualiza los datos de un UserGroup
     * @param dto Dto con datos de UserGroup
     * @return Cantidad de registros modificados
     * @throws UserGroupNotFoundException
	 * @author Sergio
	 * @since 03-08-2015
     */
    int updUserGroup(UpdUserGroupDTO dto) throws UserGroupNotFoundException;
    
    /**
     * Obtiene lista de todos los UserGroup
     * @return lista de objetos de dominio {UserGroup}
	 * @author Sergio
	 * @since 03-08-2015
     */
    List<UserGroup> getAllUserGroup();
	/**
	 * Obtiene a partir de un usuario una lista de las conversaciones grupales en la que participa el usuario
	 * @param id_user id user
	 * @return Lista de objetos de dominio {UserMessageGrpDTO}
	 * @authorJuan Carlos
	 * @since 10-08-2015
	 */
	List<UserMessageGrpDTO> getUserLastMsgGroByIdUser(Long id_user);
	
	
	/*
	 * Finder/userGroup
	 */
	
	/**
	 * Obtiene lista de usuario por id de un grupo
	 * @param id_group id del grupo
	 * @return lista de objetos de dominio UserGroupDTO
	 * @author Sergio Valenzuela
	 * @since 12-08-2015
	 */
	List<UserGroupDTO> getUserByIdGroup(Long id_group) throws UserGroupNotFoundException;
	/**
	 * Se borran los registros que tengan el mismo id_group
	 * @param id_group
	 * @return numero de registros borrados
	 * @author JuanCarlosRamosCampos
	 * @since 11-11-2015
	 */
	int delUserGrpByIdGrp(Long id_group);
	/**
	 * Se borran los registros que tengan el mismo id_user
	 * @param id_user
	 * @return numero de registros borrados
	 * @author JuanCarlosRamosCampos
	 * @since 24-11-2015
	 */
	int delUserGrpByIdUser(Long id_user);
	
	/*
	 * Attachment 
	 */
	
	/**
	 * Obtiene el attachment 
	 * @param id_attachment
	 * @return Attachment seleccionado por medio del id
	 * @author Nataly 
	 * @since 29-07-2015
	 */
	Attachment getAttachmentById(Long id_attachment) throws AttachmentNotFoundException;
	/**
	 * Obtiene lista de objeto de todos los attachments 
	 * @return lista de objetos de dominio
	 * @author Nataly
	 * @since 29-07-2015
	 */
	List<Attachment> getAllAttachment();	
	/**
	 * Agrega un objeto de dominio Attachment
	 * @param attachment Objeto de dominio Attachment
	 * @return id_attachment
	 * @author Nataly
	 * @since 29-07-2015
	 */
	Long addAttachment(Attachment attachment);
	/** 
	 * Se elimina el objeto de dominio Attachment por medio del id attachment
	 * @param id
	 * @return int numero de registros eliminador
	 * @author Nataly
	 * @throws AttachmentNotFoundException 
	 * @since 29-07-2015
	 */
	int delAttachmentById(Long id_attachment) throws AttachmentNotFoundException;
	/**
	 * Se actualiza objeto de dominio Attachment por medio del id
	 * @param record Objeto de dominio Attachment con datos actualizados 
	 * @return numero de registros actualizados por el id
	 * @throws AttachmentNotFoundException 
	 * @author Nataly
	 * @since 29-07-2015
	 */
	int updAttachmentById(Attachment record) throws AttachmentNotFoundException;
	/**
	 * Se obtiene todos los attachments por id_message
	 * @param id_message
	 * @return Lista de objetos de dominio Attachment
	 * @author Nataly
	 * @since 29-07-2015
	 */
	List<Attachment> getAllAttachmentByIdMessage(Long id_message);	
	/**
	 * Borra todos los registros attachment relacionados a un id_message
	 * @param id_message
	 * @return int numero de registros eliminados
	 * @author Nataly
	 * @since 30-07-2015
	 */
	int delAttachmentByIdMessage(Long id);
	/**
	 * Envia el path de los archivos 
	 * @param PATH_ROOT
	 */
	void setPATH_ROOT(String PATH_ROOT);
	/**
	 * Obtiene usuario por email
	 * @param email
	 * @return Objeto de dominio User
	 * @author Sergio Valenzuela
	 */
	User getUserByEmail(String email);
	
	/**
	 * Verifica existencia de email duplicado
	 * @param email
	 * @author David Rosales
	 * @throws EmailUserDuplicateException
	 */
	void getDuplicateUserByEmail(String email) throws EmailUserDuplicateException;
	
	/*
	 * FINDER MESSAGE GROUP 
	 */
	
	/**
	 * borra messageGroup por id_group
	 * @param id_group
	 * @return numero de registros borrados
	 * @author Juan Carlos Ramos Campos
	 * @throws MessageGroupNotFoundException 
	 * @since 11-11-2015
	 */
	int delMessageGrpByIdGrp(Long id_group) throws MessageGroupNotFoundException;
	/**
	 * Obtener messageGroup por medio de id_group
	 * @param id_group
	 * @return lista de objetos de dominio {messageGroup}
	 * @author JuanCarlosRamosCampos
	 * @since 11-11-2015
	 */
	List<MessageGroup> getMessageGroupByIdGroup(Long id_group);
	/**
	 * Elimina message group relacionados con id_message
	 * @param id_message
	 * @return numero de messageGroup eliminados
	 * @author JuanCarlosRamosCampos
	 * @since 24-11-2015
	 */
	int delMessageGroupByIdMessage(Long id_message);
	
	/**
	 * elimina registros de access por medio de id_store
	 * @param id_store
	 * @return numero de registros eliminados
	 * @author JuanCarlosRamosCampos
	 * @since 20-11-2015
	 */
	int delAccessByIdStore(Long id_store);
	/**
	 * elimina Access por id_user
	 * @param id_user
	 * @return numero de access eliminados
	 * @author JuanCarlosRamosCampos
	 * @since 24-11-2015
	 */
	int delAccessByIdUser(Long id_user);
	
	/**
	 * Obtiene la cantidad de usuarios asociados a un proveedor
	 * @param id_supplier Id supplier
	 * @return cantidad de usuarios
	 * @author Jorge
	 * @since 24/02/2016
	 */
	int getCountUserBySupplier(Long id_supplier);
	
	/*
	 * Obtiene las metricas para el administrador
	 * @return MetricsAdm
	 * @since 29-05-2017
	 * @author DMarin
	 */
	MetricsAdm getMetricsAdm();
	
	/*
	 * Obtiene el listado de ids por proveedor que no sea Vendedor o Soporte
	 * @param Long
	 * @return List<Long>
	 * @author DMarin
	 * @since 08-06-2017
	 */
	List<Integer> getSupIdsBySuppliers(Long id_supplier);
	
	List<User> getUserWithOutStockByIdSupplier(Long id_supplier);
	
}
