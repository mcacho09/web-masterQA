package com.retailsbs.logistikapp.web.ctrl.user;

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
import com.retailsbs.logistikapp.user.dto.NotificationTodaySearchCriteria;
import com.retailsbs.logistikapp.user.dto.UpdUserDTO;
import com.retailsbs.logistikapp.user.exception.UserNotFoundException;
import com.retailsbs.logistikapp.user.service.UserService;

/**
 * Controller para la vista para agregar usuarios
 * @author JORGE
 * @since 04-12-2014
 * @modified 26-12-2014 - JORGE - estandarizaci—n estructura de controller
 */
public class UserProfileViewUpdCtrl extends SimpleFormController {

	protected final Log logger = LogFactory.getLog(getClass());

	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public UserProfileViewUpdCtrl() {
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
		////////////////////////////////////////////////////////////////////////
		//Se obtienen los datos de acceso para el usuario
		List<Access> accesslist = userService.getAccessByIdUser(useracegi.getId_user());
		//Se obtiene el primer acceso por defecto
		Access useraccess = accesslist.get(0);
		////////////////////////////////////////////////////////////////////////
		NotificationTodaySearchCriteria dtoqty = new NotificationTodaySearchCriteria();
		dtoqty.setProfile(useracegi.getProfile());
		dtoqty.setFecha(new Date());
		dtoqty.setId_supplier(useraccess.getId_supplier());
		dtoqty.setId_user(useracegi.getId_user());
		logger.debug("----- HEADER/NOTIFICATION -----");
		Quantities nttdto = userService.getQtyAlertAndMessagesByIdUser(dtoqty);
		//HeaderNotificationDTO nttdto = userService.getHeaderNotificationByIdUser(useracegi.getId_user());
		logger.debug("alert="+nttdto.getAlert_qty()+" | message="+nttdto.getMessage_qty()+" | todo="+nttdto.getTodo_qty());
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("useracegi", useracegi);
		model.put("useraccess", useraccess);
		model.put("nttdto", nttdto);
		
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
		command.setUserlogin(record.getUserlogin());
		command.setUsername(record.getUsername());
		
		return command;
	}

	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws ServletException, UserNotFoundException {
		logger.debug("---- ONSUBMIT ----");
		
		UpdUserDTO dto = (UpdUserDTO) command;
		dto.setModified(new Date());

		// Se persiste el objeto
		int rows = userService.updUser(dto);
		logger.debug("User id=" + dto.getId_user() + " actualizado " + rows + "...ok!");

		return new ModelAndView(getSuccessView());
	}

}
