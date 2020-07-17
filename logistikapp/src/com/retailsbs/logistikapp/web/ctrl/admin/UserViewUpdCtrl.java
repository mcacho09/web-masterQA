package com.retailsbs.logistikapp.web.ctrl.admin;

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

import com.retailsbs.logistikapp.supplier.domain.Supplier;
import com.retailsbs.logistikapp.supplier.exception.SupplierNotFoundException;
import com.retailsbs.logistikapp.supplier.service.SupplierService;
import com.retailsbs.logistikapp.user.domain.Access;
import com.retailsbs.logistikapp.user.domain.User;
import com.retailsbs.logistikapp.user.domain.UserAcegi;
import com.retailsbs.logistikapp.user.dto.AddNotificationDTO;
import com.retailsbs.logistikapp.user.dto.UpdAccessDTO;
import com.retailsbs.logistikapp.user.dto.UpdUserDTO;
import com.retailsbs.logistikapp.user.exception.AccessNotFoundException;
import com.retailsbs.logistikapp.user.exception.UserNotFoundException;
import com.retailsbs.logistikapp.user.notification.service.UserNotificationService;
import com.retailsbs.logistikapp.user.service.UserService;

/**
 * Controller para la vista para agregar usuarios
 * @author Jorge
 * @since 04-12-2014
 * @modified 26-12-2014 - Jorge - estandarizacion estructura de controller
 * @modified 20-02-2015 - Jorge - Estandarizacion estructura de controller para valores por defecto y contador de alertas
 * @modified 31-03-2015 - Jorge - Se traspasan datos de job, phone1, phone2
 * @modified 22-06-2015 - Juan Carlos - Al agregar usuario, en el momento de agregar la notificacion se le agrega tambien el id_supplier ya que este dato es notnull
 * @modified 9-7-2015 - Jorge - Se adapta la funcionalidad para un usuario con perfil administrador
 */
public class UserViewUpdCtrl extends SimpleFormController {

	protected final Log logger = LogFactory.getLog(getClass());

	private UserService userService;
	private SupplierService supplierService;
	private UserNotificationService userNotificationService;
	private String ACTIVE;
	private String NO_ACTIVE;
	private Integer ORDERBY;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public void setSupplierService(SupplierService supplierService) {
		this.supplierService = supplierService;
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
		// Se obtiene la accion
		String accion = ServletRequestUtils.getStringParameter(arg0, "accion", null);
		/*-------------------------------------------------------*/
		// Se lee parametro para obtener id del usuario
		Long id = ServletRequestUtils.getLongParameter(arg0, "id");
		logger.debug("USER ==> id="+id);
		// Se obtienen los datos de acceso para el usuario
		List<Access> useraccess = userService.getAccessByIdUser(id);
		logger.debug("USER ==> useraccess="+useraccess.size());
		Access access = (Access)useraccess.get(0);
		logger.debug("USER ==> access ==> id_access="+access.getId_access()+" | id_supplier="+access.getId_supplier());
		/*-------------------------------------------------------*/
		// Se obtiene la lista de suppliers
		List<Supplier> suppliers = supplierService.getAllSupplier();
		logger.debug("USER ==> suppliers="+suppliers.size());
		/*-------------------------------------------------------*/
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("useracegi", useracegi);
		model.put("accion", accion);
		model.put("suppliers", suppliers);
		model.put("access", access);
		
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

	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws ServletException, SupplierNotFoundException, UserNotFoundException, AccessNotFoundException {
		logger.info("---- ONSUBMIT ----");
		// Se obtiene el contexto del usuario
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserAcegi useracegi = (UserAcegi) auth.getPrincipal();
		
		// Se controlan los parametros active, orderby, superuser y idsupplier
		String active = ServletRequestUtils.getStringParameter(request, "active", NO_ACTIVE);
		Integer orderby = ServletRequestUtils.getIntParameter(request, "orderby", ORDERBY);
		String superuser = ServletRequestUtils.getStringParameter(request, "superuser", NO_ACTIVE);
		Long id_supplier = ServletRequestUtils.getLongParameter(request, "id_supplier");

		// Se obtienen los datos del supplier
		Supplier supplier = null;
		try {
			supplier = supplierService.getSupplierById(id_supplier);
		} catch (SupplierNotFoundException e) {
			logger.error("Supplier, id="+id_supplier+" no existe");
			throw new SupplierNotFoundException("Error al obtener los datos supplier id = " + id_supplier);
		}
		
		// Se setea el dto desde el objeto command
		UpdUserDTO dto = (UpdUserDTO) command;
		// Se setea el dto y se persiste el objeto para actualizar objeto de dominio User
		dto.setActive( active );
		dto.setLogin( useracegi.getUserlogin() );
		dto.setModified( new Date() );
		dto.setOrderby( orderby );
		dto.setSuperuser( superuser );
		int rows = 0;
		try {
			// Se persiste el objeto para actualizar los datos del usuario
			rows = userService.updUser(dto);
		} catch (UserNotFoundException e) {
			logger.error( "Error al actualizar los datos del usuario, id="+dto.getId_user() + " login=" + dto.getUserlogin() );
			throw new UserNotFoundException( "Error al actualizar los datos del usuario, id="+dto.getId_user() + " login=" + dto.getUserlogin() );
		}
		logger.debug( "User, id=" + dto.getId_user() + " actualizado " + rows + " ...ok!" );

		// Se obtiene la definicion de access para el usuario para actualizar
		// supplier, retail o store cuando hayan sido modificados
		List<Access> access = null;
		try {
			access = userService.getAccessByIdUser( dto.getId_user() );
		} catch ( UserNotFoundException e ) {
			logger.error( "Error al obtener accesos del usuario, id="+dto.getId_user() + " login=" + dto.getUserlogin() );
			throw new UserNotFoundException( "Error al obtener accesos del usuario, id="+dto.getId_user() + " login=" + dto.getUserlogin() );
		} catch ( AccessNotFoundException e ) {
			logger.error( "Error al obtener accesos del usuario, id="+dto.getId_user() + " login=" + dto.getUserlogin() );
			throw new AccessNotFoundException( "Error al obtener accesos del usuario, id="+dto.getId_user() + " login=" + dto.getUserlogin() );
		}
		logger.debug( "USER => access = " + access.size() );
		
		AddNotificationDTO dtn;
		Long idn;
		
		// Se controla el perfil de usuario
		if ( dto.getProfile().equals("SUP") ) {
			Access accessuser = (Access)access.get(0);
			Long access_id_supplier = accessuser.getId_supplier();
			// Si el supplier seleccionado es diferente al que ya tiene asignado se debe actualizar
			if ( (long)access_id_supplier != (long)id_supplier ) {
				UpdAccessDTO dta = new UpdAccessDTO();
				dta.setId_access( accessuser.getId_access() );
				dta.setId_supplier( id_supplier );
				try {
					rows = userService.updAccess(dta);
				} catch ( AccessNotFoundException e ) {
					logger.error( "Error al asignar nuevo acceso de supplier, id= " + supplier.getId_supplier() + " name=" + supplier.getName()
							+ " al usuario, id="+dto.getId_user() + " login=" + dto.getUserlogin() );
					throw new AccessNotFoundException( "Error al asignar nuevo acceso de supplier, id= " + supplier.getId_supplier() + " name=" + supplier.getName()
							+ " al usuario, id="+dto.getId_user() + " login=" + dto.getUserlogin() );
				}
				logger.debug( "ACCESS, user="+dto.getUserlogin() + " ==> supplier=" + supplier.getName() + " actualizado.. ok!");
				/*-------------------------------------------------------*/
				// Se genera una notificacion de cambio de supplier para el usuario
				dtn = new AddNotificationDTO();
				dtn.setCreated( new Date() );
				dtn.setIcon( "fa fa-user" );
				dtn.setId_user( useracegi.getId_user() );
				dtn.setId_supplier( id_supplier );
				dtn.setMessage( "Usuario <b>"+dto.getUsername()+"</b> actualizado y " +
										"asociado al proveedor <span class='label label-success'>" + supplier.getName() + "</span>");
				dtn.setPriority( "1" );
				// Se persiste la notificacion
				idn = userService.addNotification( dtn );
				logger.debug("Notification, id="+idn);
				/*-------------------------------------------------------*/
				return new ModelAndView( getSuccessView() );
			}
		}
		
		/*-------------------------------------------------------*/
		// Se genera una notificacion de actualización genérica del usuario
		dtn = new AddNotificationDTO();
		dtn.setCreated( new Date() );
		dtn.setIcon( "fa fa-user" );
		dtn.setId_user( useracegi.getId_user() );
		dtn.setId_supplier( id_supplier );
		dtn.setMessage( "Usuario <span class='label label-success'>"+dto.getUsername()+"</span> actualizado");
		dtn.setPriority( "1" );
		// Se persiste la notificacion
		idn = userNotificationService.createNotification(dtn, "");
		logger.debug("Notification, id="+idn);
		/*-------------------------------------------------------*/
		
		return new ModelAndView( getSuccessView() );
	}

}
