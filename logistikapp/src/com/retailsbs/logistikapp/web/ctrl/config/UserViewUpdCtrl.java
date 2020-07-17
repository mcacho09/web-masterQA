package com.retailsbs.logistikapp.web.ctrl.config;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContextHolder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.retailsbs.logistikapp.user.domain.Access;
import com.retailsbs.logistikapp.user.domain.Quantities;
import com.retailsbs.logistikapp.user.domain.User;
import com.retailsbs.logistikapp.user.domain.UserAcegi;
import com.retailsbs.logistikapp.user.dto.AddNotificationDTO;
import com.retailsbs.logistikapp.user.dto.NotificationTodaySearchCriteria;
import com.retailsbs.logistikapp.user.dto.UpdUserDTO;
import com.retailsbs.logistikapp.user.exception.AccessNotFoundException;
import com.retailsbs.logistikapp.user.exception.UserNotFoundException;
import com.retailsbs.logistikapp.user.notification.service.UserNotificationService;
import com.retailsbs.logistikapp.user.service.UserService;

/**
 * Controller para la vista para agregar usuarios
 * @author JORGE
 * @since 04-12-2014
 * @modified 26-12-2014 - JORGE - estandarizacion estructura de controller
 * @modified 20-02-2015 - JORGE - Estandarizacion estructura de controller para valores por defecto y contador de alertas
 * @modified 31-03-2015 - Jorge - Se traspasan datos de job, phone1, phone2
 * @modified 22-06-2015 - Juan Carlos - Al agregar usuario, en el momento de agregar la notofocacion se le agrega tambien el id_supplier ya que este dato es notnull
 */
public class UserViewUpdCtrl extends SimpleFormController {

	protected final Log logger = LogFactory.getLog(getClass());

	private UserService userService;
	private UserNotificationService userNotificationService;
	private String ACTIVE;
	private String NO_ACTIVE;
	private Integer ORDERBY;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public void setUserNotificationService(
			UserNotificationService userNotificationService) {
		this.userNotificationService = userNotificationService;
	}
	public void setACTIVE(String aCTIVE) {
		ACTIVE = aCTIVE;
	}
	public void setNO_ACTIVE(String nO_ACTIVE) {
		NO_ACTIVE = nO_ACTIVE;
	}
	public void setORDERBY(Integer oRDERBY) {
		ORDERBY = oRDERBY;
	}

	public UserViewUpdCtrl() {
		setSessionForm(true);
		setBindOnNewForm(true);
	}

	protected Map<String, Object> referenceData(HttpServletRequest arg0, Object arg1, Errors arg2) throws Exception {
		logger.debug("---- REFERENCEDATA ----");
		/*-------------------------------------------------------*/
		logger.debug("----- USER CONTEXT -----");
		// Se obtiene el contexto del usuario
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserAcegi useracegi = (UserAcegi) auth.getPrincipal();
		/*-------------------------------------------------------*/
		// Se obtienen los datos de acceso para el usuario
		List<Access> accesslist = userService.getAccessByIdUser(useracegi.getId_user());
		// Se obtiene el primer acceso por defecto
		Access useraccess = accesslist.get(0);
		logger.debug("----- HEADER/NOTIFICATION -----");
        NotificationTodaySearchCriteria dtoqty = new NotificationTodaySearchCriteria();
        dtoqty.setProfile(useracegi.getProfile());
        dtoqty.setFecha(new Date());
        dtoqty.setId_supplier(useraccess.getId_supplier());
        dtoqty.setId_user(useracegi.getId_user());
        
        Quantities nttdto = userService.getQtyAlertAndMessagesByIdUser(dtoqty);
        //HeaderNotificationDTO nttdto = userService.getHeaderNotificationByIdUser(useracegi.getId_user());

		logger.debug("alert="+nttdto.getAlert_qty()+" | message="+nttdto.getMessage_qty()+" | todo="+nttdto.getTodo_qty());
		/*-------------------------------------------------------*/
		// Se obtiene la accion
		String accion = ServletRequestUtils.getStringParameter(arg0, "accion", null);
		/*-------------------------------------------------------*/
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("useracegi", useracegi);
		model.put("useraccess",useraccess);
		model.put("nttdto", nttdto);
		model.put("accion", accion);
		
		model.put("ACTIVE", ACTIVE);
		return model;
	}

	protected Object formBackingObject(HttpServletRequest request) throws ServletException, UserNotFoundException {
		logger.debug("---- FORMBACKINGOBJECT ----");
		// Se obtiene objeto de dominio del usuario
		Long id_user = ServletRequestUtils.getLongParameter(request, "id");
		User record = userService.getUserById(id_user); 
		/*-------------------------------------------------------*/
		// Se setea command con los datos del objeto de dominio
		UpdUserDTO command = new UpdUserDTO();
		command.setActive(record.getActive());
		command.setCreated(record.getCreated());
		command.setEmail(record.getEmail());
		command.setId_user(record.getId_user());
		command.setLogin(record.getLogin());
		command.setModified(record.getModified());
		command.setOrderby(record.getOrderby());
		command.setPasswd(record.getPasswd());
		command.setProfile(record.getProfile());
		command.setSuperuser(record.getSuperuser());
		command.setUserlogin(record.getUserlogin());
		command.setUsername(record.getUsername());
		command.setJob(record.getJob());
		command.setPhone1(record.getPhone1());
		command.setPhone2(record.getPhone2());
		command.setUbi_time(record.getUbi_time());
		return command;
	}

	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws ServletException, UserNotFoundException, AccessNotFoundException {
		logger.info("---- ONSUBMIT ----");
		// Se obtiene el contexto del usuario
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserAcegi useracegi = (UserAcegi) auth.getPrincipal();
		
		// Se controlan los parametros active, orderby y superuser
		String active = ServletRequestUtils.getStringParameter(request, "active", NO_ACTIVE);
		Integer orderby = ServletRequestUtils.getIntParameter(request, "orderby", ORDERBY);
		String superuser = ServletRequestUtils.getStringParameter(request, "superuser", NO_ACTIVE);

		UpdUserDTO dto = (UpdUserDTO) command;
		String userlogin = dto.getUserlogin();
		
		// Se setea y se persiste el objeto para actualizar el objeto de dominio User
		dto.setActive(active);
		dto.setLogin(useracegi.getUserlogin());
		dto.setModified(new Date());
		dto.setOrderby(orderby);
		dto.setSuperuser(superuser);
		dto.setUserlogin(userlogin.toLowerCase());
		// Se persiste el objeto para actualizar los datos del usuario
		int rows = userService.updUser(dto);
		logger.debug("User, id=" + dto.getId_user() + " actualizado " + rows + " ...ok!");

		/*-------------------------------------------------------*/
		// Se obtiene id supplier de usuario logueado
		Long id_sup = userService.getAccessByIdUser(useracegi.getId_user()).get(0).getId_supplier();
		logger.debug("id_supplier = "+id_sup);
		// Se genera una notificacion
		AddNotificationDTO dtn = new AddNotificationDTO();
		dtn.setCreated(new Date());
		dtn.setIcon("fa fa-user");
		dtn.setId_user(useracegi.getId_user());
		dtn.setMessage("Usuario <span class='label label-success'>"+dto.getUsername()+"</span> actualizado");
		dtn.setPriority("1");
		dtn.setId_supplier(id_sup);
		dtn.setLink("cfguserlist.htm");
		// Se persiste la notificacion
		Long idn = userNotificationService.createNotification(dtn, "005");
		logger.debug("Notification, id="+idn);
		/*-------------------------------------------------------*/
		
		return new ModelAndView(getSuccessView());
	}

}
