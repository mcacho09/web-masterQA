package com.retailsbs.logistikapp.web.ctrl.sales;

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

import com.retailsbs.logistikapp.financial.dto.CategoryProductDTO;
import com.retailsbs.logistikapp.financial.dto.CategoryProductSearchCriteria;
import com.retailsbs.logistikapp.financial.dto.ProductDTO;
import com.retailsbs.logistikapp.financial.dto.ProductSearchCriteria;
import com.retailsbs.logistikapp.financial.service.FinancialService;
import com.retailsbs.logistikapp.user.domain.Access;
import com.retailsbs.logistikapp.user.domain.Quantities;
import com.retailsbs.logistikapp.user.domain.UserAcegi;
import com.retailsbs.logistikapp.user.dto.AccessTinyDTO;
import com.retailsbs.logistikapp.user.dto.NotificationTodaySearchCriteria;
import com.retailsbs.logistikapp.user.exception.AccessNotFoundException;
import com.retailsbs.logistikapp.user.exception.UserNotFoundException;
import com.retailsbs.logistikapp.user.service.UserService;

/**
 * Controller para la vista general de product
 * @author JORGE
 * @since 11-12-2014
 * @modified 16-02-2015 - JORGE - Integracion para contador y alertas en barra navegacion
 */
public class ProductViewListCtrl implements Controller {
	
	protected final Log logger = LogFactory.getLog(getClass());

	private UserService userService;
	private FinancialService financialService;
	private String view;
	private String ACTIVE;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public void setFinancialService(FinancialService financialService) {
		this.financialService = financialService;
	}
	public void setView(String view) {
		this.view = view;
	}
	public void setACTIVE(String aCTIVE) {
		ACTIVE = aCTIVE;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws AccessNotFoundException, UserNotFoundException, ServletRequestBindingException {
		logger.debug("----- HANDLE REQUEST -----");
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
		//Falta implementar multi-supplier
		logger.debug("----- ACCESS SUPPLIER -----");
		List<AccessTinyDTO> accesssupplier = userService.getAccessSupplierByIdUser(useracegi.getId_user());
		logger.debug("accesssupplier="+accesssupplier.size());
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
		Long id_category_product = null;
		if(arg0.getParameter("id_category_product")!=null && !arg0.getParameter("id_category_product").equals(""))
			id_category_product = ServletRequestUtils.getLongParameter(arg0, "id_category_product");
		logger.debug("id_category_product="+id_category_product);
		
		//obtiene categorias de producto
		CategoryProductSearchCriteria dto_pro = new CategoryProductSearchCriteria();
		dto_pro.setId_supplier(idsupplier);
		dto_pro.setActive("S");
		
		List<CategoryProductDTO> list_cat = financialService.selectCategoryProductByCriteria(dto_pro);
		logger.debug("list_cat="+list_cat.size());

		// Se obtiene listado de productos
		ProductSearchCriteria dto = new ProductSearchCriteria();
		//Permitir setear una lista de supplier
		//Permitir setear una lista de retail
		//Permitir setear una lista de store
		dto.setId_supplier(idsupplier);
		dto.setId_category_product(id_category_product);
		List<ProductDTO> list = financialService.selectProductByCriteria(dto);
		logger.info("list= "+list.size());

		Map<String,Object> model = new HashMap<String,Object>();
		model.put("useracegi", useracegi);
		model.put("accesssupplier", accesssupplier);
		model.put("nttdto", nttdto);
		model.put("useraccess", useraccess);		
		model.put("list_cat", list_cat);
		model.put("list", list);		
		model.put("ACTIVE", ACTIVE);
		
		return new ModelAndView(view, model);
	}

}
