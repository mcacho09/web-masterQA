package com.retailsbs.logistikapp.web.ctrl.admin;

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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.retailsbs.logistikapp.retail.dto.StateQtyCityDTO;
import com.retailsbs.logistikapp.retail.service.RetailService;
import com.retailsbs.logistikapp.user.domain.Access;
import com.retailsbs.logistikapp.user.domain.Quantities;
import com.retailsbs.logistikapp.user.domain.UserAcegi;
import com.retailsbs.logistikapp.user.dto.NotificationTodaySearchCriteria;
import com.retailsbs.logistikapp.user.service.UserService;

/**
 * Controlador de clase para mostrar listado de estados
 * @author Juan Carlos Ramos Campos
 * @since 25-11-2014
 * @modified 27-12-2014 - JORGE - estandarización estructura de controller
 * @modified 16-01-2015 - JORGE - Se modifica metodo para obtener listado de datos
 */
public class StateViewListCtrl implements Controller {
	
	protected final Log logger = LogFactory.getLog(getClass());

	private RetailService retailService;
	private UserService userService;
	private String view;
	private String ACTIVE;
	private String NO_ACTIVE;
	private Long PAIS;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public void setRetailService(RetailService retailService) {
		this.retailService = retailService;
	}
	public void setView(String view) {
		this.view = view;
	}
	public void setACTIVE(String aCTIVE) {
		ACTIVE = aCTIVE;
	}
	public void setNO_ACTIVE(String nO_ACTIVE) {
		NO_ACTIVE = nO_ACTIVE;
	}
	public void setPAIS(Long pAIS) {
		PAIS = pAIS;
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		logger.debug("---- HANDLEREQUEST ----");
		/*-------------------------------------------------------*/
		logger.debug("----- USER CONTEXT -----");
		// Se obtiene el contexto del usuario
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserAcegi useracegi = (UserAcegi) auth.getPrincipal();
		/*-------------------------------------------------------*/
		logger.debug("----- USER ACCESS -----");
		// Se obtienen los datos de acceso para el usuario
		List<Access> accesslist = userService.getAccessByIdUser(useracegi.getId_user());
		// Se obtiene el primer acceso por defecto
		Access useraccess = accesslist.get(0);
		// Se obtiene el id del supplier
		/*-------------------------------------------------------*/
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
		// Se obtiene el listado de estados
		List<StateQtyCityDTO> list = retailService.getAllStateQtyCityByIdCountry(PAIS);
		logger.info("list="+list.size());
		/*-------------------------------------------------------*/
		
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("useracegi", useracegi);
		model.put("nttdto", nttdto);
		model.put("useraccess", useraccess);
		
		model.put("list", list);

		model.put("ACTIVE", ACTIVE);
		model.put("NO_ACTIVE", NO_ACTIVE);
		
		return new ModelAndView(view, model);
	}

}
