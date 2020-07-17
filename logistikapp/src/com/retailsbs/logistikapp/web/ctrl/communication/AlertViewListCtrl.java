package com.retailsbs.logistikapp.web.ctrl.communication;

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

import com.retailsbs.logistikapp.user.domain.Access;
import com.retailsbs.logistikapp.user.domain.Quantities;
import com.retailsbs.logistikapp.user.domain.UserAcegi;
import com.retailsbs.logistikapp.user.dto.NotificationSearchCriteria;
import com.retailsbs.logistikapp.user.dto.NotificationTodaySearchCriteria;
import com.retailsbs.logistikapp.user.notification.domain.ListNotificationDTO;
import com.retailsbs.logistikapp.user.notification.service.UserNotificationService;
import com.retailsbs.logistikapp.user.service.UserService;

/**
 * Controller para la vista de notificaciones
 * @author JORGE
 * @since 28-01-2015
 * @modified 09-02-2015 - JORGE - Se utiliza metodo para obtener notificaciones con cierto criterio
 * @modified 16-02-2015 - JORGE - Integraci�n para contador y alertas en barra navegaci�n
 * @modified 20-02-2015 - JORGE - Se actualiza integraci�n de notificaciones en el header
 */
public class AlertViewListCtrl implements Controller {

	protected final Log logger = LogFactory.getLog(getClass());

	private UserService userService;
	private UserNotificationService userNotificationService;
	private String view;
	private String ACTIVE;
	private String NO_ACTIVE;
	private Integer LIMIT = 25;

	public void setUserService(UserService userService) {
		this.userService = userService;
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
	public void setUserNotificationService(
			UserNotificationService userNotificationService) {
		this.userNotificationService = userNotificationService;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		logger.debug("---- HANDLE REQUEST ----");
		/*-------------------------------------------------------*/
		// Se obtiene el contexto del usuario
		logger.debug("----- USER CONTEXT -----");
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
		// Se obtiene el listado de todas las notificaciones
		NotificationSearchCriteria dto = new NotificationSearchCriteria();
		Integer currentLimit = 0;
		
		if (ServletRequestUtils.getIntParameter(arg0,"limit") != null)
			currentLimit = ServletRequestUtils.getIntParameter(arg0,"limit");
		
		
		currentLimit+=LIMIT;		
		dto.setLimit(currentLimit);
		dto.setProfile(useracegi.getProfile());		
		if (!useracegi.getProfile().equals("ADM"))
			dto.setId_supplier((userService.getAccessByIdUser(useracegi.getId_user()).get(0)).getId_supplier());		
		dto.setId_user(useracegi.getId_user());		
		List<ListNotificationDTO> list = userNotificationService.searchNotification(useracegi.getId_user(), "L");
		
		if (currentLimit > list.size())
			currentLimit = list.size()-1;
		if (list.isEmpty())
			currentLimit -= 1;
		logger.debug("list="+list.size());
		/*-------------------------------------------------------*/

		Map<String,Object> model = new HashMap<String,Object>();
		model.put("useracegi", useracegi);
		model.put("useraccess",useraccess);
		model.put("nttdto", nttdto);		
		model.put("list", list);		
		model.put("ACTIVE", ACTIVE);
		model.put("NO_ACTIVE", NO_ACTIVE);
		model.put("limit", LIMIT);
		model.put("currentlimit",currentLimit);
		
		return new ModelAndView(view, model);
	}

}
