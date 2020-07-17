package com.retailsbs.logistikapp.web.ctrl.maps;

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
import com.retailsbs.logistikapp.user.dto.AccessTinyDTO;
import com.retailsbs.logistikapp.user.dto.NotificationTodaySearchCriteria;
import com.retailsbs.logistikapp.user.service.UserService;

public class GeoClientLocationViewCtrl implements Controller {
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	private UserService userService;
	public void setUserService(UserService userService){
		this.userService = userService;
	}
	
	private String view;
	public void setView(String view) {
		this.view = view;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		// TODO Auto-generated method stub
		
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
		
		
		String name = ServletRequestUtils.getStringParameter(arg0, "name", "");
		String code = ServletRequestUtils.getStringParameter(arg0, "code", "");
		String phone = ServletRequestUtils.getStringParameter(arg0, "phone", "");
		String email = ServletRequestUtils.getStringParameter(arg0, "email", "");
		String order = ServletRequestUtils.getStringParameter(arg0, "order", "");
		String shelf = ServletRequestUtils.getStringParameter(arg0, "she", "");
		String retail = ServletRequestUtils.getStringParameter(arg0, "retail", "");
		String cat = ServletRequestUtils.getStringParameter(arg0, "cat", "");
		String vars = "&ret=" + retail + "&cat=" + cat + "&na=" + name +"&co=" + code + "&ph=" + phone + "&em=" + email + "&or=" + order + "&she=" + shelf + "&";
		
		String[] data = arg0.getHeader("referer").split("/");
		String page = data[data.length - 1];
		if (page.contains("storeadd.htm")){
			String idr = page.substring(page.indexOf("idr="), page.indexOf("&"));
			vars += "accion=add&" + idr + "&";
			page += vars;
		}else if(page.contains("storeupd.htm")){
			String idr = page.substring(page.indexOf("ids="), page.indexOf("&"));
			vars += "accion=upd&" + idr + "&";
			page += vars;
		}else{
			if (page.contains("?")){
				page = page.substring(0, page.indexOf("?"));
			}
			if (!page.contains("?")){
				page+= "?" + vars;
			}else{
				page += "&" + vars;
			}
		}
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("useracegi", useracegi);
		model.put("accesssupplier", accesssupplier);
		model.put("nttdto", nttdto);
		model.put("useraccess", useraccess);
		model.put("page", page);
		
		return new ModelAndView(view, model);
	}

}
