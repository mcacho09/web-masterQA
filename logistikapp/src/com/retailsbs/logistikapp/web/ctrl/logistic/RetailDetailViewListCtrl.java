package com.retailsbs.logistikapp.web.ctrl.logistic;

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
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.retailsbs.logistikapp.retail.domain.RetailDept;
import com.retailsbs.logistikapp.retail.dto.RetailDTO;
import com.retailsbs.logistikapp.retail.exception.RetailNotFoundException;
import com.retailsbs.logistikapp.retail.exception.StoreNotFoundException;
import com.retailsbs.logistikapp.retail.service.RetailService;
import com.retailsbs.logistikapp.user.domain.Access;
import com.retailsbs.logistikapp.user.domain.Quantities;
import com.retailsbs.logistikapp.user.domain.UserAcegi;
import com.retailsbs.logistikapp.user.dto.NotificationTodaySearchCriteria;
import com.retailsbs.logistikapp.user.exception.AccessNotFoundException;
import com.retailsbs.logistikapp.user.exception.UserNotFoundException;
import com.retailsbs.logistikapp.user.service.UserService;

/**
 * Class controller para la vista detalle de comercios
 * @author Juan Carlos Ramos Campos
 * @since 26-03-2015
 * @modified Jorge - Se modifica nombre de clase
 */
public class RetailDetailViewListCtrl implements Controller {
	
	protected final Log logger = LogFactory.getLog(getClass());

	private UserService userService;
	private RetailService retailService;
	private String view;
	private String ACTIVE;

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

	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1)
			throws UserNotFoundException, ServletRequestBindingException, StoreNotFoundException, RetailNotFoundException, AccessNotFoundException {
		logger.info("---- HANDLEREQUEST ----");
		
		/*-------------------------------------------------------*/
		logger.info("----- USER CONTEXT -----");
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
		// Se obtiene el parametro de id y objeto de dominio de Store
		Long id_retail = ServletRequestUtils.getLongParameter(arg0, "id");
		logger.debug("id_retail="+id_retail);
		RetailDTO retail = retailService.getRetailExtById(id_retail);
		logger.debug("retail="+retail.getName()+", id="+retail.getId_retail());
		//se obtienen los departamentos del retail
		List<RetailDept> list_retaildept = retailService.getRetailDeptsByIdRetail(id_retail);
		logger.debug("list_retaildept="+list_retaildept.size());
		/*-------------------------------------------------------*/
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("useracegi", useracegi);
		model.put("nttdto", nttdto);
		
		model.put("retail", retail);
		model.put("list_retaildept", list_retaildept);

		model.put("useraccess", useraccess);
		model.put("ACTIVE", ACTIVE);

		return new ModelAndView(view, model);
	}

}
