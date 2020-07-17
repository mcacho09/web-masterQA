package com.retailsbs.logistikapp.web.ctrl.user;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContextHolder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
import com.retailsbs.logistikapp.user.notification.service.UserNotificationService;
import com.retailsbs.logistikapp.user.service.UserService;

/**
 * Controller para la vista para user-profile
 * @author Juan Carlos Ramos Campos
 * @since 28-01-2015
 */
public class ProfileViewCtrl extends SimpleFormController {

	protected final Log logger = LogFactory.getLog(getClass());

	private UserService userService;
	private UserNotificationService userNotificationService;
	private String view;
	private String ACTIVE;
	private String IMAGE_DEFAULT_USER;
	private String PERFIL_ADM; 
	private String PERFIL_SUP;
	private String PERFIL_RET;
	private String PERFIL_DRI;
	private String PERFIL_STO;
	private String PERFIL_DEM;
	private String PERFIL_LGK;
	private String PERFIL_CCT;
	private String GLO_ADM;
	private String GLO_SUP;
	private String GLO_RET;
	private String GLO_DRI;
	private String GLO_STO;
	private String GLO_DEM;
	private String GLO_LGK;
	private String GLO_CCT;
	
	
	public void setPERFIL_ADM(String pERFIL_ADM) {
		PERFIL_ADM = pERFIL_ADM;
	}
	public void setPERFIL_SUP(String pERFIL_SUP) {
		PERFIL_SUP = pERFIL_SUP;
	}
	public void setPERFIL_RET(String pERFIL_RET) {
		PERFIL_RET = pERFIL_RET;
	}
	public void setPERFIL_DRI(String pERFIL_DRI) {
		PERFIL_DRI = pERFIL_DRI;
	}
	public void setPERFIL_STO(String pERFIL_STO) {
		PERFIL_STO = pERFIL_STO;
	}
	public void setPERFIL_DEM(String pERFIL_DEM) {
		PERFIL_DEM = pERFIL_DEM;
	}
	public void setPERFIL_LGK(String pERFIL_LGK) {
		PERFIL_LGK = pERFIL_LGK;
	}
	public void setPERFIL_CCT(String pERFIL_CCT) {
		PERFIL_CCT = pERFIL_CCT;
	}
	public void setGLO_ADM(String gLO_ADM) {
		GLO_ADM = gLO_ADM;
	}
	public void setGLO_SUP(String gLO_SUP) {
		GLO_SUP = gLO_SUP;
	}
	public void setGLO_RET(String gLO_RET) {
		GLO_RET = gLO_RET;
	}
	public void setGLO_DRI(String gLO_DRI) {
		GLO_DRI = gLO_DRI;
	}
	public void setGLO_STO(String gLO_STO) {
		GLO_STO = gLO_STO;
	}
	public void setGLO_DEM(String gLO_DEM) {
		GLO_DEM = gLO_DEM;
	}
	public void setGLO_LGK(String gLO_LGK) {
		GLO_LGK = gLO_LGK;
	}
	public void setGLO_CCT(String gLO_CCT) {
		GLO_CCT = gLO_CCT;
	}
	public String getIMAGE_DEFAULT_USER() {
		return IMAGE_DEFAULT_USER;
	}
	public void setIMAGE_DEFAULT_USER(String iMAGE_DEFAULT_USER) {
		IMAGE_DEFAULT_USER = iMAGE_DEFAULT_USER;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public void setUserNotificationService(
			UserNotificationService userNotificationService) {
		this.userNotificationService = userNotificationService;
	}
	public void setView(String view) {
		this.view = view;
	}
	public void setACTIVE(String aCTIVE) {
		ACTIVE = aCTIVE;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		logger.debug("---- HANDLE REQUEST ----");
		/*-------------------------------------------------------*/
		// Se obtiene el contexto del usuario
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserAcegi useracegi = (UserAcegi) auth.getPrincipal();
		
		/*-------------------------------------------------------*/
		// Se controla que exista objeto de dominio
		Long id_user = ServletRequestUtils.getLongParameter(arg0, "id",  useracegi.getId_user());
		
		boolean isMe = false;
		if (id_user.equals(useracegi.getId_user()))
			isMe = true;
		else
			isMe = false;
		
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
		/*-------------------------------------------------------*/
		Long id_usuario = null;
		if(arg0.getParameter("id") != null && !arg0.getParameter("id").equals(""))
			id_usuario = ServletRequestUtils.getLongParameter(arg0, "id");
		else 
			id_usuario = id_user;
		
		//cambiar a imagen default cuando se llama la pagina desde borrar la imagen
		if(arg0.getParameter("imagen") != null && !arg0.getParameter("imagen").equals(""))
		{
			if (arg0.getParameter("imagen").equals("0")){				
				UpdUserDTO updUser = new UpdUserDTO ();
				updUser.setId_user(id_usuario);				
				updUser.setImage(IMAGE_DEFAULT_USER);
				updUser.setModified(new Date());
				updUser.setLogin(useracegi.getUserlogin());
				
				// se crea una notificacion
				if (!useracegi.getProfile().equals("ADM")){
					AddNotificationDTO dtn = new AddNotificationDTO();
					Long id_supplier = userService.getAccessByIdUser(useracegi.getId_user()).get(0).getId_supplier();
					dtn.setCreated(new Date());
					dtn.setIcon("fa fa-user");
					dtn.setId_user(useracegi.getId_user());
					dtn.setId_supplier(id_supplier);					
					dtn.setMessage("Imagen del usuario <span class='label label-success'>"+ useracegi.getFullname() +"</span > actualizada");
					dtn.setPriority("1");					
					dtn.setLink("profile.htm?id=" + id_user);
					// Se persiste la notificacion
					Long idn = userNotificationService.createNotification(dtn, "005");
					logger.debug("Notification, id="+idn);
				}			
				
				userService.updUser(updUser);
			}			
		}	
		boolean defaultImage;
		
		if (userService.getUserById(useracegi.getId_user()).getImage().equals(IMAGE_DEFAULT_USER))
			defaultImage = true;
		else
			defaultImage = false;
		
		
		User user = userService.getUserById(id_user);
		logger.debug("id_usuario = "+id_usuario);
		String redireccion = "profile.htm?id="+id_user;		
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("user", user);
		model.put("useracegi", useracegi);
		model.put("nttdto", nttdto);		
		model.put("ACTIVE", ACTIVE);
		model.put("redireccion", redireccion);
		model.put("isme", isMe);
		model.put("defaultimage",defaultImage);
		model.put("PERFIL_ADM", PERFIL_ADM);
		model.put("PERFIL_DEM", PERFIL_DEM);
		model.put("PERFIL_DRI", PERFIL_DRI);
		model.put("PERFIL_RET", PERFIL_RET);
		model.put("PERFIL_STO", PERFIL_STO);
		model.put("PERFIL_SUP", PERFIL_SUP);
		model.put("PERFIL_LGK", PERFIL_LGK);
		model.put("PERFIL_CCT", PERFIL_CCT);
		model.put("GLO_CCT", GLO_CCT);
		model.put("GLO_ADM", GLO_ADM);
		model.put("GLO_DEM", GLO_DEM);
		model.put("GLO_DRI", GLO_DRI);
		model.put("GLO_RET", GLO_RET);
		model.put("GLO_STO", GLO_STO);
		model.put("GLO_SUP", GLO_SUP);
		model.put("GLO_LGK", GLO_LGK);
		model.put("useraccess",useraccess);

		return new ModelAndView(view, model);
	}
}
