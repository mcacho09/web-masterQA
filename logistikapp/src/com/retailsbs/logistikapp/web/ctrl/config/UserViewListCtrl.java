package com.retailsbs.logistikapp.web.ctrl.config;

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
import org.springframework.web.servlet.mvc.Controller;

import com.retailsbs.logistikapp.supplier.domain.Plan;
import com.retailsbs.logistikapp.supplier.domain.Supplier;
import com.retailsbs.logistikapp.supplier.service.SupplierService;
import com.retailsbs.logistikapp.user.domain.Access;
import com.retailsbs.logistikapp.user.domain.Quantities;
import com.retailsbs.logistikapp.user.domain.UserAcegi;
import com.retailsbs.logistikapp.user.dto.NotificationTodaySearchCriteria;
import com.retailsbs.logistikapp.user.dto.UserDTO;
import com.retailsbs.logistikapp.user.dto.UserSearchCriteria;
import com.retailsbs.logistikapp.user.service.UserService;

/**
 * Controller para la vista general de configuracion de usuarios
 * @author JORGE
 * @since 20/11/2014
 * @modified 20/02/2015 - JORGE - Reestructuracion completa de la clase
 * @modified 23/06/2015 - Juan Carlos - se obtiene la lista solo de los usuarios del mismo proveedor 
 * @modified 09/05/2016 - Jorge - Se envía a la vista información del usuario perfil soporte
 */
public class UserViewListCtrl implements Controller {
	
	protected final Log logger = LogFactory.getLog(getClass());

	private UserService userService;
	private SupplierService supplierService;
	private String view;
	private String ACTIVE;
	private String NO_ACTIVE;
	private String PERFIL_ADM; 
	private String PERFIL_SUP;
	private String PERFIL_RET;
	private String PERFIL_DRI;
	private String PERFIL_STO;
	private String PERFIL_DEM;
	private String PERFIL_LGK;
	private String PERFIL_CCT;
	private String PERFIL_SOP;
	private String GLO_ADM;
	private String GLO_SUP;
	private String GLO_RET;
	private String GLO_DRI;
	private String GLO_STO;
	private String GLO_DEM;
	private String GLO_LGK;
	private String GLO_CCT;
	private String GLO_SOP;

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public SupplierService getSupplierService() {
		return supplierService;
	}

	public void setSupplierService(SupplierService supplierService) {
		this.supplierService = supplierService;
	}

	public String getView() {
		return view;
	}

	public void setView(String view) {
		this.view = view;
	}

	public String getACTIVE() {
		return ACTIVE;
	}

	public void setACTIVE(String aCTIVE) {
		ACTIVE = aCTIVE;
	}

	public String getNO_ACTIVE() {
		return NO_ACTIVE;
	}

	public void setNO_ACTIVE(String nO_ACTIVE) {
		NO_ACTIVE = nO_ACTIVE;
	}

	public String getPERFIL_ADM() {
		return PERFIL_ADM;
	}

	public void setPERFIL_ADM(String pERFIL_ADM) {
		PERFIL_ADM = pERFIL_ADM;
	}

	public String getPERFIL_SUP() {
		return PERFIL_SUP;
	}

	public void setPERFIL_SUP(String pERFIL_SUP) {
		PERFIL_SUP = pERFIL_SUP;
	}

	public String getPERFIL_RET() {
		return PERFIL_RET;
	}

	public void setPERFIL_RET(String pERFIL_RET) {
		PERFIL_RET = pERFIL_RET;
	}

	public String getPERFIL_DRI() {
		return PERFIL_DRI;
	}

	public void setPERFIL_DRI(String pERFIL_DRI) {
		PERFIL_DRI = pERFIL_DRI;
	}

	public String getPERFIL_STO() {
		return PERFIL_STO;
	}

	public void setPERFIL_STO(String pERFIL_STO) {
		PERFIL_STO = pERFIL_STO;
	}

	public String getPERFIL_DEM() {
		return PERFIL_DEM;
	}

	public void setPERFIL_DEM(String pERFIL_DEM) {
		PERFIL_DEM = pERFIL_DEM;
	}

	public String getPERFIL_LGK() {
		return PERFIL_LGK;
	}

	public void setPERFIL_LGK(String pERFIL_LGK) {
		PERFIL_LGK = pERFIL_LGK;
	}

	public String getPERFIL_CCT() {
		return PERFIL_CCT;
	}

	public void setPERFIL_CCT(String pERFIL_CCT) {
		PERFIL_CCT = pERFIL_CCT;
	}

	public String getPERFIL_SOP() {
		return PERFIL_SOP;
	}

	public void setPERFIL_SOP(String pERFIL_SOP) {
		PERFIL_SOP = pERFIL_SOP;
	}

	public String getGLO_ADM() {
		return GLO_ADM;
	}

	public void setGLO_ADM(String gLO_ADM) {
		GLO_ADM = gLO_ADM;
	}

	public String getGLO_SUP() {
		return GLO_SUP;
	}

	public void setGLO_SUP(String gLO_SUP) {
		GLO_SUP = gLO_SUP;
	}

	public String getGLO_RET() {
		return GLO_RET;
	}

	public void setGLO_RET(String gLO_RET) {
		GLO_RET = gLO_RET;
	}

	public String getGLO_DRI() {
		return GLO_DRI;
	}

	public void setGLO_DRI(String gLO_DRI) {
		GLO_DRI = gLO_DRI;
	}

	public String getGLO_STO() {
		return GLO_STO;
	}

	public void setGLO_STO(String gLO_STO) {
		GLO_STO = gLO_STO;
	}

	public String getGLO_DEM() {
		return GLO_DEM;
	}

	public void setGLO_DEM(String gLO_DEM) {
		GLO_DEM = gLO_DEM;
	}

	public String getGLO_LGK() {
		return GLO_LGK;
	}

	public void setGLO_LGK(String gLO_LGK) {
		GLO_LGK = gLO_LGK;
	}

	public String getGLO_CCT() {
		return GLO_CCT;
	}

	public void setGLO_CCT(String gLO_CCT) {
		GLO_CCT = gLO_CCT;
	}

	public String getGLO_SOP() {
		return GLO_SOP;
	}

	public void setGLO_SOP(String gLO_SOP) {
		GLO_SOP = gLO_SOP;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		logger.debug("---- HANDLE REQUEST ----");
		/*-------------------------------------------------------*/
		logger.debug("----- USER ACCESS -----");
		// Se obtiene el contexto del usuario
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserAcegi useracegi = (UserAcegi) auth.getPrincipal();
		
		long my_id_user = useracegi.getId_user();
		/*-------------------------------------------------------*/
		// Se obtienen los datos de acceso para el usuario
		List<Access> accesslist = userService.getAccessByIdUser(useracegi.getId_user());
		// Se obtiene el primer acceso por defecto
		Access useraccess = accesslist.get(0);
		Long idsupplier = useraccess.getId_supplier();
		Supplier supplier = supplierService.getSupplierById(idsupplier);
		logger.debug("Supplier, name="+supplier.getName());
		/*-------------------------------------------------------*/
		logger.debug("----- HEADER/NOTIFICATION -----");
        NotificationTodaySearchCriteria dtoqty = new NotificationTodaySearchCriteria();
        dtoqty.setProfile(useracegi.getProfile());
        dtoqty.setFecha(new Date());
        dtoqty.setId_supplier(useraccess.getId_supplier());
        dtoqty.setId_user(useracegi.getId_user());

        Quantities nttdto = userService.getQtyAlertAndMessagesByIdUser(dtoqty);
		/*-------------------------------------------------------*/
        //Se obtiene la informacion del plan
      	Long id_plan = supplier.getId_plan();
      	Plan plan = supplierService.getPlanById(id_plan);
      	/*-------------------------------------------------------*/
		String profile = null;
		if(arg0.getParameter("sel_profile")!=null && !arg0.getParameter("sel_profile").equals(""))
			profile = ServletRequestUtils.getStringParameter(arg0, "sel_profile");
		logger.debug("profile = "+profile);
		/*-------------------------------------------------------*/
		// Se define el criterio de busqueda para obtener
		// una lista de usuarios asociados al mismo supplier 
		UserSearchCriteria dto = new UserSearchCriteria();
		dto.setId_supplier(idsupplier);
		dto.setProfile(profile);
		
		// Se obtiene la lista de usuarios
		List<UserDTO> list = userService.getUserByCriteria(dto);
		logger.debug("list= "+list.size());
		/*-------------------------------------------------------*/
		
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("useracegi", useracegi);
		model.put("useraccess", useraccess);
		model.put("nttdto", nttdto);
		model.put("plan", plan);
		
		model.put("list", list);

		model.put("ACTIVE", ACTIVE);
		model.put("NO_ACTIVE", NO_ACTIVE);

		model.put("PERFIL_ADM", PERFIL_ADM);
		model.put("PERFIL_DEM", PERFIL_DEM);
		model.put("PERFIL_DRI", PERFIL_DRI);
		model.put("PERFIL_RET", PERFIL_RET);
		model.put("PERFIL_STO", PERFIL_STO);
		model.put("PERFIL_SUP", PERFIL_SUP);
		model.put("PERFIL_LGK", PERFIL_LGK);
		model.put("PERFIL_CCT", PERFIL_CCT);
		model.put("PERFIL_SOP", PERFIL_SOP);
		model.put("GLO_CCT", GLO_CCT);
		model.put("GLO_ADM", GLO_ADM);
		model.put("GLO_DEM", GLO_DEM);
		model.put("GLO_DRI", GLO_DRI);
		model.put("GLO_RET", GLO_RET);
		model.put("GLO_STO", GLO_STO);
		model.put("GLO_SUP", GLO_SUP);
		model.put("GLO_LGK", GLO_LGK);
		model.put("GLO_SOP", GLO_SOP);
		model.put("profile", profile);
		model.put("my_id_user", my_id_user);
		
		return new ModelAndView(view, model);
	}
	
}
