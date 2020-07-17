package com.retailsbs.logistikapp.web.ctrl.maps;

import java.util.ArrayList;
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

import com.retailsbs.logistikapp.logistic.domain.Travel;
import com.retailsbs.logistikapp.logistic.dto.TravelDTO;
import com.retailsbs.logistikapp.logistic.dto.TravelSearchCriteria;
import com.retailsbs.logistikapp.logistic.dto.WayBillDTO;
import com.retailsbs.logistikapp.logistic.service.LogisticService;
import com.retailsbs.logistikapp.user.domain.Access;
import com.retailsbs.logistikapp.user.domain.Quantities;
import com.retailsbs.logistikapp.user.domain.UserAcegi;
import com.retailsbs.logistikapp.user.dto.AccessTinyDTO;
import com.retailsbs.logistikapp.user.dto.NotificationTodaySearchCriteria;
import com.retailsbs.logistikapp.user.service.UserService;

/**
 * Class controller para la vista de geolocalizacion de tiendas para call center
 * @author JUAN
 * @since 28-01-2015
 */
public class GeoCallCenterViewCtrl implements Controller {
	
	protected final Log logger = LogFactory.getLog(getClass());

	private UserService userService;
	private LogisticService logisticService;
	private String view;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setView(String view) {
		this.view = view;
	}
	public void setLogisticService(LogisticService logisticService) {
		this.logisticService = logisticService;
	}


	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		logger.debug("---- HANDLE/REQUEST ----");
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
		logger.debug("----- USER ACCESS -----");
		List<AccessTinyDTO> accesssupplier = userService.getAccessSupplierByIdUser(useracegi.getId_user());
		logger.debug("accesssupplier="+accesssupplier.size());
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
		String lat_center = null;
		if(arg0.getParameter("lat")!=null && !arg0.getParameter("lat").equals(""))
			lat_center = ServletRequestUtils.getStringParameter(arg0, "lat");
		logger.debug("lat_center="+lat_center);
		
		String lon_center = null;
		if(arg0.getParameter("lon")!=null && !arg0.getParameter("lon").equals(""))
			lon_center = ServletRequestUtils.getStringParameter(arg0, "lon");
		logger.debug("lon_center="+lon_center);
		
		String nombre_str = null;
		if(arg0.getParameter("nombre_str")!=null && !arg0.getParameter("nombre_str").equals(""))
			nombre_str = ServletRequestUtils.getStringParameter(arg0, "nombre_str");
		logger.debug("nombre_str="+nombre_str);
		
		Long id_store = null;
		if(arg0.getParameter("id_store")!=null && !arg0.getParameter("id_store").equals(""))
			id_store = ServletRequestUtils.getLongParameter(arg0, "id_store");
		logger.debug("id_store="+id_store);
		
		String dir1 = null;
		if(arg0.getParameter("dir1")!=null && !arg0.getParameter("dir1").equals(""))
			dir1 = ServletRequestUtils.getStringParameter(arg0, "dir1");
		logger.debug("dir1="+dir1);
		
		String dir2 = null;
		if(arg0.getParameter("dir2")!=null && !arg0.getParameter("dir2").equals(""))
			dir2 = ServletRequestUtils.getStringParameter(arg0, "dir2");
		logger.debug("dir2="+dir2);
		
		String postal = null;
		if(arg0.getParameter("postal")!=null && !arg0.getParameter("postal").equals(""))
			postal = ServletRequestUtils.getStringParameter(arg0, "postal");
		logger.debug("postal="+postal);
		
		String returnAdd = null;
		if(arg0.getParameter("returnAdd")!=null && !arg0.getParameter("returnAdd").equals(""))
			returnAdd = ServletRequestUtils.getStringParameter(arg0, "returnAdd");
		logger.debug("returnAdd="+returnAdd);
		
		/*-------------------------------------------------------*/
		String group = null;
		if(arg0.getParameter("group")!=null && !arg0.getParameter("group").equals(""))
			group = ServletRequestUtils.getStringParameter(arg0, "group");
		logger.debug("group="+group);
		
		//obtiene pagina de donde se manda llamar la vista
		String volver = null;
		if(arg0.getParameter("volver")!=null && !arg0.getParameter("volver").equals(""))
			volver = ServletRequestUtils.getStringParameter(arg0, "volver");
		logger.debug("volver="+volver);
		
		String dir_name = null;
		if(arg0.getParameter("dir_name")!=null && !arg0.getParameter("dir_name").equals(""))
			dir_name = ServletRequestUtils.getStringParameter(arg0, "dir_name");
		logger.debug("dir_name="+dir_name);
		
		String busqueda = null;
		if(arg0.getParameter("busqueda")!=null && !arg0.getParameter("busqueda").equals(""))
			busqueda = ServletRequestUtils.getStringParameter(arg0, "busqueda");
		logger.debug("busqueda="+busqueda);
		
		/* ------------------------------------------------------*/
		List<String> status = new ArrayList<String>();
		status.add("PRO");
		status.add("REV");
		status.add("TRA");
		
		//obtener travels de hoy en adelante
		// falta id_store para traer los travel correctos de cada tienda
		TravelSearchCriteria dto_tra = new TravelSearchCriteria();
		dto_tra.setFini(new Date());
		dto_tra.setId_supplier(userService.getAccessByIdUser(useracegi.getId_user()).get(0).getId_supplier());
		dto_tra.setStatus(status);
		//dto_tra.setOrderby("schedule");
		
		List<TravelDTO> list_travel = logisticService.getTravelByCriteria(dto_tra);
		logger.debug("list_travel="+list_travel);
		
		Long id_travel = null;
		if(arg0.getParameter("travel_sel")!=null && !arg0.getParameter("travel_sel").equals(""))
			id_travel = ServletRequestUtils.getLongParameter(arg0, "travel_sel");
		logger.debug("id_travel="+id_travel);

		// Se obtiene parametro id_travel si es null
		String sep="";
		String viaje_name = "";
		ArrayList<WayBillDTO> tiendas = new ArrayList<WayBillDTO>();
		List<Integer> list_orderby = new ArrayList<Integer>(); 
		if(id_travel!=null || list_travel.size()>0){
			if(id_travel==null)
				id_travel = list_travel.get(0).getId_travel();
			List<WayBillDTO> waybills = logisticService.getWayBillByIdTravel(id_travel);
			
			for(int j=0; j<waybills.size(); j++){
				tiendas.add(waybills.get(j));
				//se obtienen orderby de travel seleccionado
				if(id_travel!=null){
					if(waybills.get(j).getCheckin()==null){
						list_orderby.add(waybills.get(j).getOrderby());
					} // waybills.get(j).getCheckin()
				} // id_travel
			} // for(int j=0; j<waybills.size(); j++)
			Travel travel = logisticService.getTravelById(id_travel);
			viaje_name = viaje_name + sep + travel.getName(); 
			sep = ",";
		} // if(id_travel!=null || list_travel.size()>0)
		logger.debug("list_orderby="+list_orderby);
		
		/*-------------------------------------------------------*/
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("useracegi", useracegi);
		model.put("accesssupplier", accesssupplier);
		model.put("nttdto", nttdto);
		
		model.put("viaje_name", viaje_name);
		model.put("tiendas", tiendas);
		model.put("volver", volver);
		model.put("dir_name", dir_name);
		model.put("busqueda", busqueda);
		
		model.put("id_store", id_store);
		model.put("lat_center", lat_center);
		model.put("lon_center", lon_center);
		model.put("nombre_str", nombre_str);
		model.put("dir1", dir1);
		model.put("dir2", dir2);
		model.put("postal", postal);

		model.put("useraccess", useraccess);
		model.put("id_travel", id_travel);
		model.put("group", group);
		model.put("list_travel", list_travel);
		model.put("returnAdd", returnAdd);
		model.put("list_orderby", list_orderby);
		
		if(list_travel.size()==0)
			return new ModelAndView("redirect:callcenter.htm?notravel=1");
		return new ModelAndView(view, model);
	}

}

