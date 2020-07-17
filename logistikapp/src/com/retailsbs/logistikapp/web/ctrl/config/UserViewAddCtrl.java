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

import com.retailsbs.logistikapp.supplier.domain.Plan;
import com.retailsbs.logistikapp.supplier.domain.Supplier;
import com.retailsbs.logistikapp.supplier.service.SupplierService;
import com.retailsbs.logistikapp.user.domain.Access;
import com.retailsbs.logistikapp.user.domain.Quantities;
import com.retailsbs.logistikapp.user.domain.UserAcegi;
import com.retailsbs.logistikapp.user.dto.AddAccessDTO;
import com.retailsbs.logistikapp.user.dto.AddNotificationDTO;
import com.retailsbs.logistikapp.user.dto.AddUserDTO;
import com.retailsbs.logistikapp.user.dto.NotificationTodaySearchCriteria;
import com.retailsbs.logistikapp.user.dto.UserDTO;
import com.retailsbs.logistikapp.user.dto.UserSearchCriteria;
import com.retailsbs.logistikapp.user.exception.AccessNotFoundException;
import com.retailsbs.logistikapp.user.exception.UserNotFoundException;
import com.retailsbs.logistikapp.user.notification.service.UserNotificationService;
import com.retailsbs.logistikapp.user.service.UserService;

/**
 * Controller para la vista para agregar usuarios
 * @author JORGE
 * @since 04-12-2014
 * @modified 26-12-2014 - JORGE - Estandarizacion estructura de controller
 * @modified 20-02-2015 - JORGE - Estandarizacion estructura de controller para valores por defecto y contador de alertas
 * @modified 22-06-2015 - Juan Carlos - Al agregar usuario, en el momento de agregar la notofocacion se le agrega tambien el id_supplier ya que este dato es notnull
 */
public class UserViewAddCtrl extends SimpleFormController {

	protected final Log logger = LogFactory.getLog(getClass());

	private UserService userService;
	private SupplierService supplierService;
	private UserNotificationService userNotificationService;
	private String ACTIVE;
	private String NO_ACTIVE;
	private Integer ORDERBY;
	private Integer EMPRESA;
	private String IMAGE;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
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
	public void setSupplierService(SupplierService supplierService) {
		this.supplierService = supplierService;
	}
	public void setUserNotificationService(
			UserNotificationService userNotificationService) {
		this.userNotificationService = userNotificationService;
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
		//Se obtiene la informacion del plan
		Supplier supplier = supplierService.getSupplierById(useraccess.getId_supplier());
      	Long id_plan = supplier.getId_plan();
      	Plan plan = supplierService.getPlanById(id_plan);
		// Se define el criterio de busqueda para obtener
		// una lista de usuarios asociados al mismo supplier 
		UserSearchCriteria dto = new UserSearchCriteria();
		dto.setId_supplier(useraccess.getId_supplier());
		
				
		// Se obtiene la lista de usuarios
		List<UserDTO> list = userService.getUserByCriteria(dto);
		logger.debug("list= "+list.size());
		
		boolean advertencia = (list.size() >= plan.getUsers() ? true : false);
		
		
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("useracegi", useracegi);
		model.put("useraccess",useraccess);
		model.put("nttdto", nttdto);
		model.put("accion", accion);
		model.put("advertencia",advertencia);
		
		model.put("ACTIVE", ACTIVE);
		return model;
	}

	protected Object formBackingObject(HttpServletRequest request) throws ServletException {
		logger.debug("---- FORMBACKINGOBJECT ----");
		AddUserDTO command = new AddUserDTO();
		return command;
	}

	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws ServletException, UserNotFoundException, AccessNotFoundException {
		logger.debug("---- ONSUBMIT ----");
		// Se obtiene el contexto del usuario
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserAcegi useracegi = (UserAcegi) auth.getPrincipal();
		
		// Se controlan los parametros active, orderby y superuser
		String active = ServletRequestUtils.getStringParameter(request, "active", NO_ACTIVE);
		Integer orderby = ServletRequestUtils.getIntParameter(request, "orderby", ORDERBY);
		String superuser = ServletRequestUtils.getStringParameter(request, "superuser", NO_ACTIVE);
		
		AddUserDTO dto = (AddUserDTO) command;
		//se encripta la contrasenia
		String password = dto.getPasswd(); 
		String userlogin = dto.getUserlogin();
		
		/*MessageDigest md;
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
		}
		*/
		// Se setea y se persiste el objeto para crear objeto de dominio User
		dto.setActive(active);
		dto.setCreated(new Date());
		dto.setImage(IMAGE);
		dto.setLogin(useracegi.getUserlogin());
		dto.setOrderby(orderby);
		dto.setSuperuser(superuser);
		dto.setPasswd(password);
		dto.setUserlogin(userlogin.toLowerCase());
		// Se persiste el objeto para crear un nuevo usuario
		Long idu = userService.addUserEmail(dto);
		logger.debug("User, id="+idu+" creado.. ok!");
		// Se obtiene id supplier de usuario logueado
		Long id_sup = userService.getAccessByIdUser(useracegi.getId_user()).get(0).getId_supplier();
		logger.debug("id_supplier = "+id_sup);
		
		// Se setea y se persiste el objeto para crear objeto de dominio Access
		AddAccessDTO dta = new AddAccessDTO();
		dta.setId_user(idu);
		dta.setId_empresa((long)EMPRESA);
		dta.setId_supplier(id_sup);
		dta.setCreated(new Date());
		dta.setLogin(useracegi.getLogin());
		dta.setActive(ACTIVE);
		Long ida = userService.addAcces(dta);
		logger.debug("Access, id=" + ida + " creado.. ok!");
		
		/*-------------------------------------------------------*/
		// Se genera una notificacion
		AddNotificationDTO dtn = new AddNotificationDTO();
		dtn.setCreated(new Date());
		dtn.setIcon("fa fa-user");
		dtn.setId_user(useracegi.getId_user());
		dtn.setId_supplier(id_sup);
		dtn.setMessage("Nuevo usuario <span class='label label-primary'>"+dto.getUsername()+"</span> creado");
		dtn.setPriority("1");
		dtn.setLink("cfguserlist.htm");
		// Se persiste la notificacion
		Long idn = userNotificationService.createNotification(dtn, "003");
		logger.debug("Notification, id="+idn);
		/*-------------------------------------------------------*/
		
		return new ModelAndView(getSuccessView());
	}

}
