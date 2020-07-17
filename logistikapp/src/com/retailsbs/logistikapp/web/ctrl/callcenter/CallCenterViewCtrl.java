package com.retailsbs.logistikapp.web.ctrl.callcenter;

import java.text.SimpleDateFormat;
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

import com.retailsbs.logistikapp.logistic.dto.TravelByIdStoreDTO;
import com.retailsbs.logistikapp.logistic.dto.TravelByIdStoreSearch;
import com.retailsbs.logistikapp.logistic.service.LogisticService;
import com.retailsbs.logistikapp.manager.logistic.ManagerLogisticService;
import com.retailsbs.logistikapp.manager.logistic.dto.StoreByNameAddressLogistic;
import com.retailsbs.logistikapp.manager.logistic.dto.StoreTravelDTO;
import com.retailsbs.logistikapp.user.domain.Access;
import com.retailsbs.logistikapp.user.domain.Quantities;
import com.retailsbs.logistikapp.user.domain.UserAcegi;
import com.retailsbs.logistikapp.user.dto.NotificationTodaySearchCriteria;
import com.retailsbs.logistikapp.user.service.UserService;

/**
 * Class controller para la vista call center
 * @author Juan Carlos Ramos Campos
 * @since 19-10-2015
 */
public class CallCenterViewCtrl implements Controller {
	
	protected final Log logger = LogFactory.getLog(getClass());

	private UserService userService;
	private LogisticService logisticService;
	private ManagerLogisticService managerlogisticService;
	private String view;
	
	public void setManagerlogisticService(
			ManagerLogisticService managerlogisticService) {
		this.managerlogisticService = managerlogisticService;
	}
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
		// obtener parametros
		Integer busqueda = null;
		if(arg0.getParameter("busqueda")!=null && !arg0.getParameter("busqueda").equals("") && !arg0.getParameter("busqueda").equals("'"))
			busqueda = ServletRequestUtils.getIntParameter(arg0, "busqueda");
		logger.debug("busqueda="+busqueda);
		
		Long id_supplier = userService.getAccessByIdUser(useracegi.getId_user()).get(0).getId_supplier();
		logger.debug("id_supplier="+id_supplier);
		
		String dir_name = null;
		if(arg0.getParameter("dir_name")!= null && !arg0.getParameter("dir_name").equals(""))
			dir_name = ServletRequestUtils.getStringParameter(arg0, "dir_name");
		logger.debug("dir_name="+dir_name);
		
		String notravel = null;
		if(arg0.getParameter("notravel")!=null && !arg0.getParameter("notravel").equals(""))
			notravel = ServletRequestUtils.getStringParameter(arg0, "notravel");
		logger.debug("notravel = "+notravel);
		
		SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");
		String schedule = sd.format(new Date());
		logger.debug("schedule="+schedule);
		
		Map<String,Object> model = new HashMap<String,Object>();
		List<StoreTravelDTO> list = new ArrayList<StoreTravelDTO>();
		//Obtener tiendas
		if(busqueda!=null){
			StoreByNameAddressLogistic dto_str = new StoreByNameAddressLogistic();
			
			dto_str.setId_supplier(id_supplier);
			if(busqueda==1)
				dto_str.setDireccion(dir_name);
			if(busqueda==2)
				dto_str.setNombre(dir_name);
			dto_str.setSchedule(sd.parse(schedule));
			
			list = managerlogisticService.getStoreByNameAddressAndTravels(dto_str);
			logger.debug("list="+list.size());
			
			model.put("list", list);
		}
		
		// === se obtienen los viajes de cada tienda ===
		TravelByIdStoreSearch dto_tra = new TravelByIdStoreSearch();
		dto_tra.setSchedule(sd.parse(schedule));
		
		List<TravelByIdStoreDTO> list_travels = logisticService.getTravelByIdStore(dto_tra);
		logger.debug("list_travels="+list_travels.size());
		// === /se obtienen los viajes de cada tienda ===
		

		model.put("useracegi", useracegi);
		model.put("useraccess",useraccess);
		model.put("nttdto", nttdto);
		
		model.put("dir_name", dir_name);
		model.put("busqueda", busqueda);
		model.put("notravel", notravel);

		return new ModelAndView(view, model);
	}
}
