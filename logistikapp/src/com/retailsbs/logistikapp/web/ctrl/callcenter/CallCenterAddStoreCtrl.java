package com.retailsbs.logistikapp.web.ctrl.callcenter;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
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

import com.retailsbs.logistikapp.logistic.domain.Travel;
import com.retailsbs.logistikapp.logistic.domain.WayBill;
import com.retailsbs.logistikapp.logistic.dto.WayBillDTO;
import com.retailsbs.logistikapp.logistic.dto.WayBillSearchCriteria;
import com.retailsbs.logistikapp.logistic.service.LogisticService;
import com.retailsbs.logistikapp.retail.domain.Store;
import com.retailsbs.logistikapp.retail.service.RetailService;
import com.retailsbs.logistikapp.supplier.exception.SupplierNotFoundException;
import com.retailsbs.logistikapp.user.domain.UserAcegi;
import com.retailsbs.logistikapp.user.dto.AddNotificationDTO;
import com.retailsbs.logistikapp.user.notification.service.UserNotificationService;
import com.retailsbs.logistikapp.user.service.UserService;

/**
 * Agregar tiendas en viajes ya creados
 * @author Juan Carlos Ramos
 * @since 23-10-2015
 */
public class CallCenterAddStoreCtrl implements Controller {

	protected final Log logger = LogFactory.getLog(getClass());

	public LogisticService logisticService;
	public RetailService retailService;
	public UserNotificationService userNotificationService;
	public UserService userService;
	private String view;

	public void setLogisticService(LogisticService logisticService) {
		this.logisticService = logisticService;
	}

	public void setRetailService(RetailService retailService) {
		this.retailService = retailService;
	}

	public void setView(String view) {
		this.view = view;
	}

	public void setUserNotificationService(
			UserNotificationService userNotificationService) {
		this.userNotificationService = userNotificationService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception, SupplierNotFoundException {
		// Se obtiene el contexto del usuario
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserAcegi ua = (UserAcegi) auth.getPrincipal();
		String login = ua.getUserlogin();
		logger.debug("login=" + login);
		/*-------------------------------------------------------*/
		
		String returnAdd = "true";
		
		Long id_store = null;
		if(arg0.getParameter("id_store")!=null && !arg0.getParameter("id_store").equals(""))
			id_store = ServletRequestUtils.getLongParameter(arg0, "id_store");
		logger.debug("id_store="+id_store);

		Long id_travel = null;
		if(arg0.getParameter("travel_sel")!=null && !arg0.getParameter("travel_sel").equals(""))
			id_travel = ServletRequestUtils.getLongParameter(arg0, "travel_sel");
		logger.debug("id_travel="+id_travel);
		
		String group = null;
		if(arg0.getParameter("group")!=null && !arg0.getParameter("group").equals(""))
			group = ServletRequestUtils.getStringParameter(arg0, "group");
		logger.debug("group="+group);
		
		String nocheck = null;
		if(arg0.getParameter("nocheck")!=null && !arg0.getParameter("nocheck").equals(""))
			nocheck = ServletRequestUtils.getStringParameter(arg0, "nocheck");
		logger.debug("nocheck="+nocheck);
		
		String newstr = null;
		if(arg0.getParameter("newstr")!=null && !arg0.getParameter("newstr").equals(""))
			newstr = ServletRequestUtils.getStringParameter(arg0, "newstr");
		logger.debug("newstr="+newstr);
		
		String dis1 = null;
		if(arg0.getParameter("dis1")!=null && !arg0.getParameter("dis1").equals(""))
			dis1 = ServletRequestUtils.getStringParameter(arg0, "dis1");
		logger.debug("dis1="+dis1);
		
		String dis2 = null;
		if(arg0.getParameter("dis2")!=null && !arg0.getParameter("dis2").equals(""))
			dis2 = ServletRequestUtils.getStringParameter(arg0, "dis2");
		logger.debug("dis2="+dis2);
		
		Double lat = null; 
		if(arg0.getParameter("lat")!=null && !arg0.getParameter("lat").equals(""))
			lat = ServletRequestUtils.getDoubleParameter(arg0, "lat");
		logger.debug("lat="+lat);
		
		Double lon = null; 
		if(arg0.getParameter("lon")!=null && !arg0.getParameter("lon").equals(""))
			lon = ServletRequestUtils.getDoubleParameter(arg0, "lon");
		logger.debug("lon="+lon);
		
		String nombre_str = null; 
		if(arg0.getParameter("nombre_str")!=null && !arg0.getParameter("nombre_str").equals(""))
			nombre_str = ServletRequestUtils.getStringParameter(arg0, "nombre_str");
		logger.debug("nombre_str="+nombre_str);
		
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
		
		Integer orden = null;
		if(arg0.getParameter("orden")!= null && !arg0.getParameter("orden").equals("")){
			orden = Integer.parseInt(ServletRequestUtils.getStringParameter(arg0, "orden"));
		}
		logger.debug("orden="+orden);
		
		Integer orderby = null;
		WayBillSearchCriteria wbc = new WayBillSearchCriteria();
		wbc.setId_store(id_store);
		wbc.setId_travel(id_travel);
		
		List<WayBillDTO> list_wbc = logisticService.getWayBillByCriteria(wbc);
		if(list_wbc.size()==0){
			// ------------------------------------------------------MANUAL-------------------------------------------------------
			if(group.equals("manual")){
				orderby = orden;
				List<WayBillDTO> list = logisticService.getWayBillByIdTravel(id_travel);
				for(int i=0; i<list.size(); i++){
					if(list.get(i).getOrderby()>=orden){
						WayBill dto_wbmanual = new WayBill();
						dto_wbmanual.setId_waybill(list.get(i).getId_waybill());
						dto_wbmanual.setOrderby(list.get(i).getOrderby()+1);
						// se persiste el objeto
						logisticService.updWayBillById(dto_wbmanual);
					} // if(list.get(i).getOrderby()>=orden)
				} // for(int i=0; i<list.size(); i++)
				// Se agrega nuevo waybill con la tienda nueva
				WayBill dto = new WayBill();
				dto.setId_store(id_store);
				dto.setId_travel(id_travel);
				dto.setOrderby(orderby);
				dto.setLog_created(new Date());
				dto.setLog_created_login(login);
				dto.setStatus("N");
				
				
				
				Long id_waybill = logisticService.addWayBill(dto);
				logger.debug("waybill con id "+id_waybill+" agregado ok");
				
			} // if(group.equals("MANUAL"))
			// ------------------------------------------------------FINAL-------------------------------------------------------
			// obtener orden en que ira la tienda
			if(group.equals("final")){
				List<WayBillDTO> list_waybill = new ArrayList<WayBillDTO>();
				list_waybill = logisticService.getWayBillByIdTravel(id_travel);
				logger.debug("list_waybill="+list_waybill.size());
				orderby = list_waybill.get(list_waybill.size()-1).getOrderby();
				orderby = orderby+1;
				logger.debug("orderby = "+orderby);
				
				WayBill dto = new WayBill();
				dto.setId_store(id_store);
				dto.setId_travel(id_travel);
				dto.setOrderby(orderby);
				dto.setLog_created(new Date());
				dto.setLog_created_login(login);
				dto.setStatus("N");
				
				Long id_waybill = logisticService.addWayBill(dto);
				logger.debug("waybill con id "+id_waybill+" agregado ok");
				
			} // if(group.equals("final"))
			// ------------------------------------------------------PROXIMO-------------------------------------------------------
			List<Double> list_dis1 	= new ArrayList<Double>(); 
			List<Double> list_dis2  = new ArrayList<Double>(); 
			List<Long> list_ids 	= new ArrayList<Long>(); 
			if(group.equals("proximo")){
				String[] a = dis1.split("/");
				for(int i=1; i<a.length; i++){
					String[] b = a[i].split(" ");
					list_dis1.add(Double.parseDouble(b[0]));
				} // for(int i=1; i<a.length; i++)
				logger.debug("list_dis1="+list_dis1.size());
				
				String[] h = dis2.split("/");
				for(int i=1; i<h.length; i++){
					String[] x = h[i].split(" ");
					list_dis2.add(Double.parseDouble(x[0]));
				} // for(int i=1; i<a.length; i++)
				logger.debug("list_dis2="+list_dis2.size());
				
				String[] ids = nocheck.split(";");
				for(int s=0; s<ids.length; s++){
					String[] as = ids[s].split("_");
					list_ids.add(Long.parseLong(as[0]));
				}
				logger.debug("list_ids="+list_ids.size());
				//Se compararan las distancias entre los puntos para saber en donde poner la nueva tienda
				int count = 0;
				for(int y=0; y<list_dis1.size(); y++){
					if(list_dis2.get(y)<list_dis1.get(y)){
						WayBillSearchCriteria dto_w = new WayBillSearchCriteria();
						dto_w.setId_travel(id_travel);
						dto_w.setId_store(list_ids.get(y+1));
						
						List<WayBillDTO> list_wb =  logisticService.getWayBillByCriteria(dto_w);
						logger.debug("list_wb="+list_wb.size());
						orderby = list_wb.get(0).getOrderby();
						break;
					} // if(list_dis2.get(y)>list_dis1.get(y))
					count++;
				} // for(int y=0; y<list_dis1.size(); y++)
				// si no se obtiene el valor de orderby porque ningun valor de list2 es menor que list1 correspondiente
				if(orderby==null){
					Double dist_men = list_dis2.get(0);
					int cont_indx = 0;
					for(int i=1; i<list_dis2.size(); i++){
						if(list_dis2.get(i)<dist_men){
							dist_men = list_dis2.get(i);
							cont_indx = i;
						}else
							cont_indx = 0;
					} // for(int i=1; i<list_dis2.size(); i++)
					// se obtiene valor de orderby
					count = cont_indx;
					WayBillSearchCriteria dto_w = new WayBillSearchCriteria();
					dto_w.setId_travel(id_travel);
					dto_w.setId_store(list_ids.get(cont_indx+1));
					
					List<WayBillDTO> list_wb =  logisticService.getWayBillByCriteria(dto_w);
					logger.debug("list_wb="+list_wb.size());
					
					orderby = list_wb.get(0).getOrderby();
				} // if orderby
				
				
				WayBill dto = new WayBill();
				dto.setId_store(id_store);
				dto.setId_travel(id_travel);
				dto.setOrderby(orderby);
				dto.setLog_created(new Date());
				dto.setLog_created_login(login);
				dto.setStatus("N");
				
				Long id_waybill = logisticService.addWayBill(dto);
				logger.debug("waybill con id "+id_waybill+" agregado ok");
				
				for(int y=count; y<list_dis1.size(); y++){
					orderby++;
					WayBillSearchCriteria dto_u = new WayBillSearchCriteria();
					dto_u.setId_travel(id_travel);
					dto_u.setId_store(list_ids.get(y+1));
					List<WayBillDTO> list_u = logisticService.getWayBillByCriteria(dto_u);
					
					WayBill wb_upd = new WayBill();
					wb_upd.setId_waybill(list_u.get(0).getId_waybill());
					wb_upd.setOrderby(orderby);
					
					logisticService.updWayBillById(wb_upd);
				} // for(int y=count; y<list_dis1.size(); y++)
			} // if(group.equals("proximo"))
			
			Travel travel = logisticService.getTravelById(id_travel);
			Store store = retailService.getStoreById(id_store);
			
			// notificacion de agregar ruta
			AddNotificationDTO dto_not = new AddNotificationDTO();
			dto_not.setCreated(new Date());
			dto_not.setIcon("fa  fa-road");
			dto_not.setId_supplier(userService.getAccessByIdUser(ua.getId_user()).get(0).getId_supplier());
			dto_not.setId_user(ua.getId_user());
			dto_not.setMessage("Se agrego al cliente <span class='label label-warning'>" + store.getName() + "</span> en el viaje <b>" + "</b>");
			dto_not.setPriority("1");
			dto_not.setProfile(ua.getProfile());
			dto_not.setLink("travelonway.htm?idt=" + id_travel);
			List<Integer> users = new LinkedList<Integer>();
			users.add(travel.getId_user().intValue());
			
			Long id_notification = userNotificationService.createNotificationWithList(dto_not, users);
			logger.debug("id de notificacion creada " + id_notification);
		}
		
		return new ModelAndView(view+"?returnAdd="+returnAdd+"&lat="+lat+"&lon="+lon+"&dir1="+dir1+"&dir2="+dir2+"&postal="+postal+"&travel_sel="+id_travel);
	}

}
