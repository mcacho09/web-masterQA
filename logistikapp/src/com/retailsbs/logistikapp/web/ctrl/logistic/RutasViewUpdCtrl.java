package com.retailsbs.logistikapp.web.ctrl.logistic;

import java.util.ArrayList;
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

import com.retailsbs.logistikapp.logistic.domain.Route;
import com.retailsbs.logistikapp.logistic.dto.UpdRouteDTO;
import com.retailsbs.logistikapp.logistic.exception.RouteNotFoundException;
import com.retailsbs.logistikapp.logistic.service.LogisticService;
import com.retailsbs.logistikapp.retail.domain.Store;
import com.retailsbs.logistikapp.retail.domain.StoreCategory;
import com.retailsbs.logistikapp.retail.dto.AvailableStoreByIdStoreCategoryIdRoute;
import com.retailsbs.logistikapp.retail.dto.AvailableStoreByIdSupplierIdRoute;
import com.retailsbs.logistikapp.retail.dto.RetailDTO;
import com.retailsbs.logistikapp.retail.dto.RetailSearchCriteria;
import com.retailsbs.logistikapp.retail.dto.StoreByRouteDTO;
import com.retailsbs.logistikapp.retail.service.RetailService;
import com.retailsbs.logistikapp.user.domain.Access;
import com.retailsbs.logistikapp.user.domain.Quantities;
import com.retailsbs.logistikapp.user.domain.UserAcegi;
import com.retailsbs.logistikapp.user.dto.NotificationTodaySearchCriteria;
import com.retailsbs.logistikapp.user.exception.AccessNotFoundException;
import com.retailsbs.logistikapp.user.exception.UserNotFoundException;
import com.retailsbs.logistikapp.user.service.UserService;

/**
 * RutasViewUpdCtrl
 * @author Sergio Valenzuela
 * @since 20/08/2015
 */
public class RutasViewUpdCtrl extends SimpleFormController {
	
	protected final Log logger = LogFactory.getLog(getClass());

	private UserService userService;
	private RetailService retailService;
	private LogisticService logisticService;
	
	
	public void setLogisticService(LogisticService logisticService) {
		this.logisticService = logisticService;
	}

	public void setRetailService(RetailService retailService) {
		this.retailService = retailService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
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
		logger.debug("----- DATA -----");
		/// Se obtiene la accion
		String accion = ServletRequestUtils.getStringParameter(arg0, "accion", null);
		logger.info("accion = "+accion);
		Long id_route = ServletRequestUtils.getLongParameter(arg0, "id_route");
		// obtiene tiendas que pertenecen a esta ruta
		List<StoreByRouteDTO> listStoreMarked = retailService.getStoreByRoute(id_route);
		logger.debug("id Route = "+id_route);
		long selected_retail = listStoreMarked.get(0).getId_retail();
			
		
		/*-------------------------------------------------------*/
		Long id_supplier= userService.getAccessByIdUser(useracegi.getId_user()).get(0).getId_supplier();
		//		List<Store> listStore = retailService.getAviableStoreByIdSupplier(id_supplier);
		
		AvailableStoreByIdSupplierIdRoute dto = new AvailableStoreByIdSupplierIdRoute();
		dto.setId_route(id_route);
		dto.setId_supplier(id_supplier);
		
		List<Store> listStore = retailService.getAvailableStoreByIdSupplierAndIdRoute(dto);
		// obtiene plazas (retail)
		RetailSearchCriteria dto_ret = new RetailSearchCriteria();
		dto_ret.setId_supplier(id_supplier);
		List<RetailDTO> listRetail = retailService.getRetailByCriteria(dto_ret);
		logger.debug("listRetail="+listRetail.size());
		//obtiene categorias
		List<StoreCategory> listCategory = retailService.getAllStoreCategoryByIdSupplier(id_supplier);
		logger.debug("listCategory="+listCategory.size());

		AvailableStoreByIdStoreCategoryIdRoute dto_pruj = new AvailableStoreByIdStoreCategoryIdRoute();
		dto_pruj.setIdRoute(id_route);
		dto_pruj.setIdStorecategory(1L);
		
		Double lat = 0.0;
		Double lon = 0.0;

		if(listStoreMarked.size() > 0){
			for (int i = 0, size = listStoreMarked.size(); i < size; i++) {
				if (listStoreMarked.get(i).getLatitude() != 0 && listStoreMarked.get(i).getLongitude() != 0) {
					lat = listStoreMarked.get(i).getLatitude();
					lon = listStoreMarked.get(i).getLongitude();
					break;
				}
			}
		}else{
			lat = 21.88072496638374;
			lon = -102.2961151599884;
		}
		
		
		String listaBase="";
		for (StoreByRouteDTO sto : listStoreMarked){
			listaBase = listaBase.concat("<tr><td><input type='checkbox' onclick='javascript:addStoreMap("+sto.getId_store()+");' id='"+sto.getId_store()+"' checked/>"+sto.getName()+"</td><td class='text-center'><span class='badge badge-info'><span>"+sto.getCode()+"</span></td></tr>");
		}
		
		Route ruta = logisticService.getRouteById(id_route);
		
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("useracegi", useracegi);
		model.put("nttdto", nttdto);
		model.put("listStore", listStore);
		model.put("listStoreMarked", listStoreMarked);
		model.put("contLocales", listStoreMarked.size());
		model.put("id_supplier", id_supplier);
		model.put("listaBase", listaBase);
		model.put("ruta", ruta);
		model.put("accion", accion);
		model.put("lat", lat);
		model.put("lon", lon);
		model.put("id_route", id_route);
		model.put("listRetail", listRetail);
		model.put("listCategory", listCategory);
		model.put("useraccess",useraccess);
		model.put("selected_retail",selected_retail);
				
		return  model;
	}
	
	protected Object formBackingObject(HttpServletRequest request) throws ServletException, RouteNotFoundException {

		Long id_ruta = null;
		if(request.getParameter("id_route")!=null && !request.getParameter("id_route").equals(""))
			id_ruta = ServletRequestUtils.getLongParameter(request, "id_route");
		logger.debug("id_route="+id_ruta);
		
		Route route = logisticService.getRouteById(id_ruta);
		
		UpdRouteDTO command = new UpdRouteDTO();
		
		command.setCode(route.getCode());
		command.setColor(route.getColor());
		command.setId_route(route.getId_route());
		command.setId_supplier(route.getId_supplier());
		command.setLog_created(route.getLog_created());
		command.setLog_created_login(route.getLog_created_login());
		command.setLog_modified(route.getLog_modified());
		command.setLog_modified_login(route.getLog_modified_login());
		command.setName(route.getName());
		command.setStatus(route.getStatus());
		
		return command;
	}

	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws ServletException, UserNotFoundException, AccessNotFoundException, RouteNotFoundException {
		logger.debug("---- ONSUBMIT ----");
		/*-------------------------------------------------------*/
		// Se obtiene el contexto del usuario
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserAcegi ua = (UserAcegi) auth.getPrincipal();
		
		String userLogin =ua.getLogin();
		logger.info("userLogin "+userLogin);
		
		Long id_route = null;
		if(request.getParameter("id_route") != null && !request.getParameter("id_route").equals(""))
			id_route = ServletRequestUtils.getLongParameter(request, "id_route");
		logger.info("id_route "+id_route);
		
		String oldListS = null;
		if(request.getParameter("oldList") != null && !request.getParameter("oldList").equals(""))
			oldListS = ServletRequestUtils.getStringParameter(request, "oldList", null);
		logger.info("oldList "+oldListS);
		
		String newListS = null;
		if(request.getParameter("ids") != null && !request.getParameter("ids").equals(""))
			newListS = ServletRequestUtils.getStringParameter(request, "ids", null);
		logger.info("newList "+newListS);
		
		UpdRouteDTO dto = (UpdRouteDTO) command;
		
		dto.setId_route(id_route);
		dto.setId_supplier(userService.getAccessByIdUser(ua.getId_user()).get(0).getId_supplier());
		dto.setLog_created(new Date());
		dto.setLog_modified_login(userLogin);
		//dto.setStatus("CRE");
		
		logger.debug("id = "+dto.getId_route());
		logger.debug("color = "+dto.getColor());
		logger.debug("code = "+dto.getCode());
		logger.debug("name = "+dto.getName());
		
		int row = logisticService.updRouteById(dto);
		logger.debug(row + "registro(s) actualizado(s) OK!");
		String[] newarrayIds = null;
		String[] oldarrayIds = null;
		List<Long> oldList = new ArrayList<Long>();
		List<Long> newList = new ArrayList<Long>();
		
		if(newListS != null){
			newarrayIds = newListS.split("_");

			for(int i=0; i<newarrayIds.length; i++){
				if(!newarrayIds[i].equals(""))
					newList.add(Long.parseLong(newarrayIds[i]));
			}
		} // if(newListS != null && oldListS != null)
		
		if(oldListS != null){
			oldarrayIds = oldListS.split("_");
			oldList = new ArrayList<Long>();
			
			for(int i=0; i<oldarrayIds.length; i++)
				oldList.add(Long.parseLong(oldarrayIds[i]));
		} // if(oldListS != null){
		
		int rows = logisticService.updateRouteStoreByList(oldList, newList, id_route, userLogin);
		logger.debug(rows + " Registro(s) Actualizado(s)  OK!");
		
		return new ModelAndView(getSuccessView());
	}

}
