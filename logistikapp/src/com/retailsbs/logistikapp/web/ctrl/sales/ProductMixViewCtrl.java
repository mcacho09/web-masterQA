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
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.retailsbs.logistikapp.financial.dto.ProductByCategoryStoreToUpdateCriteria;
import com.retailsbs.logistikapp.financial.dto.ProductByCategoryStoreToUpdateDTO;
import com.retailsbs.logistikapp.financial.service.FinancialService;
import com.retailsbs.logistikapp.retail.domain.StoreCategory;
import com.retailsbs.logistikapp.retail.service.RetailService;
import com.retailsbs.logistikapp.user.domain.Access;
import com.retailsbs.logistikapp.user.domain.Quantities;
import com.retailsbs.logistikapp.user.domain.UserAcegi;
import com.retailsbs.logistikapp.user.dto.AccessTinyDTO;
import com.retailsbs.logistikapp.user.dto.NotificationTodaySearchCriteria;
import com.retailsbs.logistikapp.user.service.UserService;

/**
 * ProductMix
 * @author jorge
 * @since 17/05/2015
 */
public class ProductMixViewCtrl implements Controller {
	
	protected final Log logger = LogFactory.getLog(getClass());

	private UserService userService;
	private RetailService retailService;
	private FinancialService financialService;
	private String view;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public void setView(String view) {
		this.view = view;
	}
	public void setRetailService(RetailService retailService) {
		this.retailService = retailService;
	}
	public void setFinancialService(FinancialService financialService) {
		this.financialService = financialService;
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
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
		logger.debug("----- NOTIFICATION -----");
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
		
		// Se obtienen los accesos del usuario
		List<AccessTinyDTO> accesssupplier = userService.getAccessSupplierByIdUser(useracegi.getId_user());
		
		/*-------------------------------------------------------*/
		// Se obtiene el listado de categorias de locales
		Long id_supplier = ( accesssupplier.get(0) ).getId();
		List<StoreCategory> category_list = retailService.getAllStoreCategoryByIdSupplier(id_supplier);
		logger.debug("category_list = "+category_list.size());
		/*-------------------------------------------------------*/
		
		Long idCat = ServletRequestUtils.getLongParameter(arg0, "idcat", 0l);
		
		ProductByCategoryStoreToUpdateCriteria criteria = new ProductByCategoryStoreToUpdateCriteria();
		criteria.setId_supplier(id_supplier);
		if (idCat == 0 && category_list.size() > 0) {
			idCat = category_list.get(0).getId_store_category();
		}
		criteria.setId_store_category(idCat);
		List<ProductByCategoryStoreToUpdateDTO> product_list = financialService.getProductByCategoryStoreToUpdate(criteria);
		
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("useracegi", useracegi);
		model.put("useraccess", useraccess);
		model.put("nttdto", nttdto);
		model.put("category_list", category_list);
		model.put("product_list", product_list);
		model.put("idCat", idCat);
		return new ModelAndView(view, model);
	}

}
