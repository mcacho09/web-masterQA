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
import com.retailsbs.logistikapp.user.domain.UserAcegi;
import com.retailsbs.logistikapp.user.dto.AddAccessDTO;
import com.retailsbs.logistikapp.user.dto.AddNotificationDTO;
import com.retailsbs.logistikapp.user.dto.AddUserDTO;
import com.retailsbs.logistikapp.user.notification.service.UserNotificationService;
import com.retailsbs.logistikapp.user.service.UserService;

/**
 * Controller para la vista de agregar usuarios
 * @author Jorge
 * @since 04-12-2014
 * @modified 26-12-2014 - Jorge - Estandarizacion estructura de controller
 * @modified 20-02-2015 - Jorge - Estandarizacion estructura de controller para valores por defecto y contador de alertas
 * @modified 22-06-2015 - Juan Carlos - Al agregar usuario, en el momento de agregar la notofocacion se le agrega tambien el id_supplier ya que este dato es notnull
 * @modified 7-7-2015 - Jorge - Se adapta la funcionalidad para un usuario con perfil administrador
 */
public class UserViewAddCtrl extends SimpleFormController {

	protected final Log logger = LogFactory.getLog(getClass());

	private UserService userService;
	private SupplierService supplierService;
	private UserNotificationService userNotificationService;
	public void setUserNotificationService(
			UserNotificationService userNotificationService) {
		this.userNotificationService = userNotificationService;
	}

	private String ACTIVE;
	private String NO_ACTIVE;
	private Integer ORDERBY;
	private Integer EMPRESA;
	private String IMAGE;
	
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
	public void setEMPRESA(Integer eMPRESA) {
		EMPRESA = eMPRESA;
	}
	public void setIMAGE(String iMAGE) {
		IMAGE = iMAGE;
	}
	
	public UserViewAddCtrl() {
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
		// Se obtiene la lista de suppliers
		List<Supplier> suppliers = supplierService.getAllSupplier();
		logger.debug("suppliers="+suppliers.size());
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("useracegi", useracegi);
		model.put("accion", accion);
		model.put("suppliers", suppliers);
		
		model.put("ACTIVE", ACTIVE);
		return model;
	}

	protected Object formBackingObject(HttpServletRequest request) throws ServletException {
		logger.debug("---- FORMBACKINGOBJECT ----");
		AddUserDTO command = new AddUserDTO();
		return command;
	}

	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws ServletException {
		logger.debug("---- ONSUBMIT ----");
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
		}
		
		/*-------------------------------------------------------*/
		// Se encripta la password
		/*String password = dto.getPasswd(); 
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes()); 
			byte byteData[] = md.digest(); 
			StringBuffer sb = new StringBuffer(); 
			for (int i = 0; i < byteData.length; i++) 
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1)); 
			password = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			logger.error("Error al encriptar password");
		}*/
		/*-------------------------------------------------------*/
		
		// Se setea el dto desde el objeto command
		AddUserDTO dto = (AddUserDTO) command;
		
		// Se setea el dto y se persiste el objeto para crear objeto de dominio User
		dto.setActive( active );
		dto.setCreated( new Date() );
		dto.setImage( IMAGE );
		dto.setLogin( useracegi.getUserlogin() );
		dto.setOrderby( orderby );
		dto.setSuperuser( superuser );
		// Se persiste el objeto para crear un nuevo usuario
		Long idu = userService.addUser( dto );
		logger.debug("User, id="+idu+" creado.. ok!");
		
		// Se setea el dto y se persiste un nuevo
		// objeto de dominio Access
		AddAccessDTO dta = new AddAccessDTO();
		dta.setId_user( idu );
		dta.setId_empresa( (long)EMPRESA );
		dta.setId_supplier( id_supplier );
		dta.setCreated( new Date() );
		dta.setLogin( useracegi.getLogin() );
		dta.setActive( ACTIVE );
		Long ida = userService.addAcces( dta );
		logger.debug("Access, id=" + ida + " creado.. ok!");

		/*-------------------------------------------------------*/
		// Se genera una notificacion
		AddNotificationDTO dtn = new AddNotificationDTO();
		dtn.setCreated( new Date() );
		dtn.setIcon( "fa fa-user" );
		dtn.setId_user( useracegi.getId_user() );
		dtn.setId_supplier( id_supplier );
		dtn.setMessage( "Usuario <span class='label label-primary'>"+dto.getUsername()+"</span> creado " +
									"y asociado al proveedor <span class='label label-primary'>"+supplier.getName()+"</span>");
		dtn.setPriority( "1" );
		dtn.setLink("alertlist.htm");
		// Se persiste la notificacion
		Long idn = userNotificationService.createNotification(dtn, "");
		logger.debug("Notification, id="+idn);
		/*-------------------------------------------------------*/
		
		return new ModelAndView(getSuccessView());
	}

}
