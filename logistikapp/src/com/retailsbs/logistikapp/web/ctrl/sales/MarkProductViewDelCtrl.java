package com.retailsbs.logistikapp.web.ctrl.sales;

import java.util.Date;
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

import com.retailsbs.logistikapp.financial.domain.Brand;
import com.retailsbs.logistikapp.financial.service.FinancialService;
import com.retailsbs.logistikapp.user.domain.UserAcegi;
import com.retailsbs.logistikapp.user.dto.AccessTinyDTO;
import com.retailsbs.logistikapp.user.dto.AddNotificationDTO;
import com.retailsbs.logistikapp.user.notification.service.UserNotificationService;
import com.retailsbs.logistikapp.user.service.UserService;

/**
 * Controlador para eliminar marcas
 * @author Jazz
 * @since 10-08-2016
 */
public class MarkProductViewDelCtrl implements Controller {

	protected final Log logger = LogFactory.getLog(getClass());

	private String view;
	private UserService userService;
	private UserNotificationService userNotificationService;
	private FinancialService financialService;

	public void setView(String view) {
		this.view = view;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public void setUserNotificationService(
			UserNotificationService userNotificationService) {
		this.userNotificationService = userNotificationService;
	}
	public void setFinancialService(FinancialService financialService) {
		this.financialService = financialService;
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
		Long id_supplier = ( accesssupplier.get(0) ).getId();
		logger.debug("idsupplier="+id_supplier);
		/*-------------------------------------------------------*/
		// obtienen los ids de las categorias a eliminar
		String idsParam = ServletRequestUtils.getStringParameter(arg0, "ids");
		logger.debug("ids="+idsParam);
		String[] idsArray = idsParam.split(",");
				
		// Inicializacion de variables
		Long id_brand= 0L;
		Brand brand = null;
	
		String[] names = new String [idsArray.length];
		AddNotificationDTO dtn = null;
		Long idn = 0L;
		
		// Ciclo por cada categoria
		for ( int i = 0; i < idsArray.length; i++ ) {
			id_brand = Long.parseLong( idsArray[i] );
			brand = financialService.getBrandById(id_brand);
			//guarda el nombre de la categoria para la notificacion
			names[i] = brand.getName();
		}
		//Eliminar las categorias
		for (int i = 0; i < idsArray.length; i++ ) {
			id_brand = Long.parseLong( idsArray[i] );
			financialService.delBrandByIdBrand(id_brand);		
			//logger.debug("categorias eliminados: "+rows);
		}
		
		for(int i = 0; i < names.length; i++){
			/*-------------------------------------------------------*/
			// Se setea de dto para agregar un objeto de dominio Notification
			dtn = new AddNotificationDTO();
			dtn.setCreated(new Date());
			dtn.setIcon("fa fa-building");
			dtn.setId_user(useracegi.getId_user());
			dtn.setId_supplier(id_supplier);
			dtn.setMessage("Marca <span class='label label-danger'>"+names[i]+"</span> eliminado");
			dtn.setPriority("1");
			dtn.setLink("alertlist.htm");
			// Se persiste la notificacion
			idn = userNotificationService.createNotification(dtn, "001");
			logger.debug("Notification, id="+idn);
			/*-------------------------------------------------------*/
			
		}
		return new ModelAndView(view);
	}

}