package com.retailsbs.logistikapp.web.ctrl.logistic;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContextHolder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.retailsbs.logistikapp.logistic.service.LogisticService;
import com.retailsbs.logistikapp.user.domain.UserAcegi;
import com.retailsbs.logistikapp.user.dto.AccessTinyDTO;
import com.retailsbs.logistikapp.user.service.UserService;
/**
 * Controlador para eliminar rutas
 * @author Juan Carlos Ramos
 * @since 09-10-2015 
 */
public class RutasDelCtrl implements Controller {
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	private LogisticService logisticService;
	private UserService userService;
	private String view;

	public void setLogisticService(LogisticService logisticService) {
		this.logisticService = logisticService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setView(String view) {
		this.view = view;
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		logger.debug("---- HANDLE/REQUEST ----");
		/*-------------------------------------------------------*/
		logger.debug("----- USER CONTEXT -----");
		// Se obtiene el contexto del usuario
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserAcegi useracegi = (UserAcegi) auth.getPrincipal();
		/*-------------------------------------------------------*/
		logger.debug("----- USER ACCESS -----");
		List<AccessTinyDTO> accesssupplier = userService.getAccessSupplierByIdUser(useracegi.getId_user());
		logger.debug("accesssupplier="+accesssupplier.size());
		/*-------------------------------------------------------*/
		
		Long id_route = null;
		if(arg0.getParameter("id_route")!=null && !arg0.getParameter("id_route").equals(""))
			id_route = ServletRequestUtils.getLongParameter(arg0, "id_route");
		logger.debug("id_route="+id_route);
		
		//===== Eliminar las relaciones de ruta y tiendas
		int row_rs = logisticService.delRouteStoreByIdRoute(id_route);
		logger.debug("numero de route_store eliminadas="+row_rs);

		return new ModelAndView(view+"?accion=upd&id_route="+id_route);
	}


	
}
