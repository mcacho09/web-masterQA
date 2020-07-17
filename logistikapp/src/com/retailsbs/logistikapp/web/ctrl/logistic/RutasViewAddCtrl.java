package com.retailsbs.logistikapp.web.ctrl.logistic;

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

import com.retailsbs.logistikapp.logistic.dto.AddRouteDTO;
import com.retailsbs.logistikapp.logistic.dto.AddRouteStoreDTO;
import com.retailsbs.logistikapp.logistic.service.LogisticService;
import com.retailsbs.logistikapp.retail.domain.StoreCategory;
import com.retailsbs.logistikapp.retail.dto.AvailableStoreDTO;
import com.retailsbs.logistikapp.retail.dto.AvailableStoreSearchCriteria;
import com.retailsbs.logistikapp.retail.dto.RetailDTO;
import com.retailsbs.logistikapp.retail.dto.RetailSearchCriteria;
import com.retailsbs.logistikapp.retail.service.RetailService;
import com.retailsbs.logistikapp.user.domain.Access;
import com.retailsbs.logistikapp.user.domain.Quantities;
import com.retailsbs.logistikapp.user.domain.UserAcegi;
import com.retailsbs.logistikapp.user.dto.AddNotificationDTO;
import com.retailsbs.logistikapp.user.dto.NotificationTodaySearchCriteria;
import com.retailsbs.logistikapp.user.exception.AccessNotFoundException;
import com.retailsbs.logistikapp.user.exception.UserNotFoundException;
import com.retailsbs.logistikapp.user.notification.service.UserNotificationService;
import com.retailsbs.logistikapp.user.service.UserService;

/**
 * RutasViewAddCtrl
 * @author Sergio Valenzuela
 * @since 20/08/2015
 */
public class RutasViewAddCtrl extends SimpleFormController {
	
	protected final Log logger = LogFactory.getLog(getClass());

	private UserService userService;
	private RetailService retailService;
	private LogisticService logisticService;
	private UserNotificationService userNotificationService;
	private String view;
	
	public void setLogisticService(LogisticService logisticService) {
		this.logisticService = logisticService;
	}
	public void setRetailService(RetailService retailService) {
		this.retailService = retailService;
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
	
	protected Map<String, Object> referenceData(HttpServletRequest arg0, Object arg1, Errors arg2) throws Exception {
		logger.debug("---- HANDLE REQUEST ----");
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
		/*-------------------------------------------------------*/
		/// Se obtiene la accion
		String accion = ServletRequestUtils.getStringParameter(arg0, "accion", null);
		logger.info("accion ="+accion);
		Long id_supplier= userService.getAccessByIdUser(useracegi.getId_user()).get(0).getId_supplier();
		/*AvailableStoreSearchCriteria dto = new AvailableStoreSearchCriteria();
		dto.setIdSupplier(id_supplier);
		
		List<AvailableStoreDTO> listStore = retailService.getAvailableStoreByCriteria(dto);*/
		//logger.debug("listStore="+listStore.size());
		
		// obtiene plazas (retail)
		RetailSearchCriteria dto_ret = new RetailSearchCriteria();
		dto_ret.setId_supplier(id_supplier);
		List<RetailDTO> listRetail = retailService.getRetailByCriteria(dto_ret);
		logger.debug("listRetail="+listRetail.size());
		//obtiene categorias
		List<StoreCategory> listCategory = retailService.getAllStoreCategoryByIdSupplier(id_supplier);
		logger.debug("listCategory="+listCategory.size());
		
		/*  obtiene tiendas dispoibles*/
		
		AvailableStoreSearchCriteria dto = new AvailableStoreSearchCriteria();
		dto.setIdSupplier(id_supplier);
		dto.setIdRetail(listRetail.get(0).getId_retail());

		List<AvailableStoreDTO> listStore = retailService.getAvailableStoreByCriteria(dto);
		logger.debug("listStore="+listStore.size());
		
		
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("useracegi", useracegi);
		model.put("nttdto", nttdto);
		model.put("id_supplier", id_supplier);
		model.put("accion", accion);
		//model.put("listStore", listStore);
		model.put("listRetail", listRetail);
		model.put("listCategory", listCategory);
		model.put("useraccess",useraccess);
		return  model;
	}
	
	protected Object formBackingObject(HttpServletRequest request) throws ServletException {
		AddRouteDTO command = new AddRouteDTO();
		return command;
	}

	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws ServletException, UserNotFoundException, AccessNotFoundException {
		logger.debug("---- ONSUBMIT ----");
		/*-------------------------------------------------------*/
		// Se obtiene el contexto del usuario
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserAcegi ua = (UserAcegi) auth.getPrincipal();

		String ids = ServletRequestUtils.getStringParameter(request, "ids", "0");
		logger.info("ids "+ids);
		
		// agrega una ruta
		AddRouteDTO dto = (AddRouteDTO) command;
		
		dto.setId_supplier(userService.getAccessByIdUser(ua.getId_user()).get(0).getId_supplier());
		dto.setLog_created(new Date());
		dto.setLog_created_login(ua.getUserlogin());
		dto.setStatus("ACT");
		
		logger.debug("color = "+dto.getColor());
		logger.debug("code = "+dto.getCode());
		logger.debug("name = "+dto.getName());
		
		Long id = logisticService.addRoute(dto);
		logger.debug("Route, id=" + id + " creado OK!");
		
		// agrega las tiendas a la ruta creada
		if (!ids.equals("")){
			String[] arrayIds = ids.split("_");
			for (int i = 0; i < arrayIds.length; i++){
				AddRouteStoreDTO dtoRS = new AddRouteStoreDTO();
				dtoRS.setId_route(id);
				dtoRS.setId_store(Long.parseLong(arrayIds[i]));
				dtoRS.setLog_created(new Date());
				dtoRS.setLog_created_login(ua.getUserlogin());
				
				Long idRouteStore = logisticService.addRouteStore(dtoRS);
				logger.debug("Relacion RouteStore, id=" + idRouteStore + " creado OK!");
			}
		}
		
		// notificacion de agregar ruta
		AddNotificationDTO dto_not = new AddNotificationDTO();
		dto_not.setCreated(new Date());
		dto_not.setIcon("fa  fa-road");
		dto_not.setId_supplier(userService.getAccessByIdUser(ua.getId_user()).get(0).getId_supplier());
		dto_not.setId_user(ua.getId_user());
		dto_not.setMessage("Nueva ruta <span class='label label-primary'>"+dto.getName()+"</label>");
		dto_not.setPriority("1");
		dto_not.setProfile(ua.getProfile());
		dto_not.setLink("rutasupd.htm?accion=upd&id_route=" + id);
		
		Long id_notification = userNotificationService.createNotification(dto_not, "002");
		logger.debug("id de notificacion creada " + id_notification);

		return new ModelAndView(getSuccessView());
	}

}
