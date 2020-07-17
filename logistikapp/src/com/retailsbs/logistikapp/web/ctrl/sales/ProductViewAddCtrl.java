package com.retailsbs.logistikapp.web.ctrl.sales;

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

import com.retailsbs.logistikapp.financial.dto.AddProductDTO;
import com.retailsbs.logistikapp.financial.dto.BrandProductDTO;
import com.retailsbs.logistikapp.financial.dto.BrandProductSearchCriteria;
import com.retailsbs.logistikapp.financial.dto.CategoryProductDTO;
import com.retailsbs.logistikapp.financial.dto.CategoryProductSearchCriteria;
import com.retailsbs.logistikapp.financial.service.FinancialService;
import com.retailsbs.logistikapp.user.domain.Access;
import com.retailsbs.logistikapp.user.domain.Quantities;
import com.retailsbs.logistikapp.user.domain.UserAcegi;
import com.retailsbs.logistikapp.user.dto.AccessTinyDTO;
import com.retailsbs.logistikapp.user.dto.AddNotificationDTO;
import com.retailsbs.logistikapp.user.dto.NotificationTodaySearchCriteria;
import com.retailsbs.logistikapp.user.exception.AccessNotFoundException;
import com.retailsbs.logistikapp.user.exception.UserNotFoundException;
import com.retailsbs.logistikapp.user.notification.service.UserNotificationService;
import com.retailsbs.logistikapp.user.service.UserService;

/**
 * Controller para la vista para agregar productos
 * @author jorge
 * @since 11-12-2014
 * @modified 29-12-2014 - JORGE - estandarizaci�n estructura de controller
 * @modified 16-02-2015 - JORGE - Integraci�n para contador y alertas en barra navegaci�n
 * @modified 10-08-2016 - Jazz  - Brand/Flag/
 */
public class ProductViewAddCtrl extends SimpleFormController {

	protected final Log logger = LogFactory.getLog(getClass());

	private UserService userService;
	private UserNotificationService userNotificationService;
	private FinancialService financialService;
	private String ACTIVE;
	private String NO_ACTIVE;
	private Integer ORDERBY;
	
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
	public void setACTIVE(String aCTIVE) {
		ACTIVE = aCTIVE;
	}
	public void setNO_ACTIVE(String nO_ACTIVE) {
		NO_ACTIVE = nO_ACTIVE;
	}
	public void setORDERBY(Integer oRDERBY) {
		ORDERBY = oRDERBY;
	}
	
	public ProductViewAddCtrl() {
		setSessionForm(true);
		setBindOnNewForm(true);
	}

	protected Map<String, Object> referenceData(HttpServletRequest arg0, Object arg1, Errors arg2) throws Exception {
		logger.debug("---- REFERENCEDATA ----");
		/*-------------------------------------------------------*/
		logger.debug("----- USER CONTEXT -----");
		// Se obtiene el contexto del usuario
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserAcegi useracegi = (UserAcegi) auth.getPrincipal();
		////////////////////////////////////////////////////////////////////////
		//Se obtienen los datos de acceso para el usuario
		List<Access> accesslist = userService.getAccessByIdUser(useracegi.getId_user());
		//Se obtiene el primer acceso por defecto
		Access useraccess = accesslist.get(0);
		////////////////////////////////////////////////////////////////////////
		logger.debug("----- USER ACCESS -----");
		List<AccessTinyDTO> accesssupplier = userService.getAccessSupplierByIdUser(useracegi.getId_user());
		logger.debug("accesssupplier="+accesssupplier.size());
		// TODO se debe cambiar por el proveedor activo y s�lo de los que tiene acceso
		Long idsupplier = ( accesssupplier.get(0) ).getId();
		logger.debug("idsupplier="+idsupplier);
		/*-------------------------------------------------------*/
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
		// Se obtiene la accion
		String accion = ServletRequestUtils.getStringParameter(arg0, "accion", null);
		logger.debug("accion="+accion);
		/*-------------------------------------------------------*/
		// Se obtiene una lista de categorias de productos asociado a un proveedor
		CategoryProductSearchCriteria dto = new CategoryProductSearchCriteria();
		dto.setId_supplier(idsupplier);
		dto.setActive(ACTIVE);
		List<CategoryProductDTO> list = financialService.selectCategoryProductByCriteria(dto);
		logger.debug("list="+list.size());
		/*-------------------------------------------------------*/
		// Se obtiene una lista de marcas de productos asociado a un proveedor
		BrandProductSearchCriteria dtb = new BrandProductSearchCriteria();
		dtb.setId_supplier(idsupplier);
		List<BrandProductDTO> listb = financialService.selectBrandProductByCriteria(dtb);
		logger.debug("listb="+listb.size());
		/*-------------------------------------------------------*/
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("useracegi", useracegi);
		model.put("accesssupplier", accesssupplier);
		model.put("nttdto", nttdto);
		model.put("useraccess", useraccess);
		model.put("accion", accion);
		model.put("list", list);
		model.put("listb", listb);
		model.put("ACTIVE", ACTIVE);
		return model;
	}

	protected Object formBackingObject(HttpServletRequest request) throws ServletException {
		logger.debug("---- FORMBACKINGOBJECT ----");
		AddProductDTO command = new AddProductDTO();
		return command;
	}

	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws ServletException, UserNotFoundException, AccessNotFoundException {
		logger.debug("---- ONSUBMIT ----");
		// Se obtiene el contexto del usuario
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserAcegi useracegi = (UserAcegi) auth.getPrincipal();
		
		// Se controlan los parametros active y orderby
		String active = ServletRequestUtils.getStringParameter(request, "active", NO_ACTIVE);
		//Integer orderby = ServletRequestUtils.getIntParameter(request, "orderby", ORDERBY);
		//String image = ServletRequestUtils.getStringParameter(request, "image", NO_ACTIVE);
		
		// Se setea y se persiste el objeto para crear el objeto de dominio Product
		AddProductDTO dto = (AddProductDTO) command;
		
		dto.setCreated(new Date());
		
		dto.setLogin(useracegi.getUserlogin());
		
		// Se persiste el objeto
		Long id = financialService.addProduct(dto);
		logger.debug("Product, id=" + id + " creado OK!");

		/*-------------------------------------------------------*/
		// se obtiene id_supplier
		Long id_sup = userService.getAccessByIdUser(useracegi.getId_user()).get(0).getId_supplier();
		logger.debug("id_sup = "+id_sup);
		// Se genera una notificacion
		AddNotificationDTO dtn = new AddNotificationDTO();
		dtn.setCreated(new Date());
		dtn.setIcon("fa fa-cube");
		dtn.setId_user(useracegi.getId_user());
		dtn.setMessage("Nuevo producto <span class='label label-primary'>"+dto.getName_short()+"</span> creado");
		dtn.setPriority("1");
		dtn.setId_supplier(id_sup);
		dtn.setLink("alertlist.htm");
		// Se persiste la notificacion
		Long idn = userNotificationService.createNotification(dtn, "");
		logger.debug("Notification, id="+idn);
		/*-------------------------------------------------------*/
		
		return new ModelAndView(getSuccessView());
	}

}
