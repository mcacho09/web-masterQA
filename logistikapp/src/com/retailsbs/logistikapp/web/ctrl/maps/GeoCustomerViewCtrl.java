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

import com.retailsbs.logistikapp.retail.domain.Store;
import com.retailsbs.logistikapp.retail.dto.StoreActiveByIdsRetailSearchCriteria;
import com.retailsbs.logistikapp.retail.dto.StoreByIdsSearchCriteria;
import com.retailsbs.logistikapp.retail.service.RetailService;
import com.retailsbs.logistikapp.user.domain.Access;
import com.retailsbs.logistikapp.user.domain.Quantities;
import com.retailsbs.logistikapp.user.domain.UserAcegi;
import com.retailsbs.logistikapp.user.dto.NotificationTodaySearchCriteria;
import com.retailsbs.logistikapp.user.service.UserService;

/**
 * Class controller para la vista de geolocalización de las tiendas de uno o más clientes
 * @author Jorge
 * @since 16-11-2015
 */
public class GeoCustomerViewCtrl implements Controller {
	
	protected final Log logger = LogFactory.getLog(getClass());

	private RetailService retailService;
	private UserService userService;
	private String view;
	
	public void setRetailService(RetailService retailService) {
		this.retailService = retailService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public void setView(String view) {
		this.view = view;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		logger.debug("----- HANDLE REQUEST -----");
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
		
		// Se obtienen los ids de retail para obtener los respectivos ids de store
		String ids_str = ServletRequestUtils.getRequiredStringParameter(arg0, "ids");
		logger.debug("ids=" + ids_str );
		String [] ids_array = ids_str.split(",");
		List<Long> idsretail = new ArrayList<Long>();
		logger.debug("idsretail=" + idsretail.size() );
		long id = 0L;
		for( int i=0; i<ids_array.length; i++ ) {
			try {
				id = Long.parseLong( ids_array[i] );
				idsretail.add( id );
			} catch(Exception e) {
				logger.error("Error en formato numérico de idsret="+ids_array[i]);
			}
		}
		
		StoreActiveByIdsRetailSearchCriteria dto = new StoreActiveByIdsRetailSearchCriteria();
		dto.setIds(idsretail);
		List<Long> idsstore = retailService.getIdStoresActiveByIdsRetail( dto );
		logger.debug("idsstore=" + idsstore);
		
		long[] ids = new long[idsstore.size()];
		for ( int i=0; i<idsstore.size(); i++ )
			ids[i] = idsstore.get(i);
		
		// De la lista de ids se obtiene una lista de store
		StoreByIdsSearchCriteria idto = new StoreByIdsSearchCriteria();
		idto.setIds(ids);
		List<Store> stores = retailService.getStoreByIds(idto);
		logger.debug("stores="+stores.size());
		
		String storesName = "";
		String storesLatitude = "";
		String storesLongitude = "";
		String storesAddress1 = "";
		String storesAddress2 = "";
		String storesShelf = "";
		String sep = "";
		Store store = null;
		for ( int i=0; i<stores.size(); i++ ) {
			store = stores.get(i);
			storesName = storesName + sep + store.getName();
			storesLatitude = storesLatitude + sep + store.getLatitude();
			storesLongitude = storesLongitude + sep + store.getLongitude();
			storesAddress1 = storesAddress1 + sep + store.getAddress1();
			storesAddress2 = storesAddress2 + sep + store.getAddress2();
			storesShelf = storesShelf + sep + store.getShelf();
			sep = "|";
		}
		logger.debug("storesName="+storesName );
		logger.debug("storesLatitude="+storesLatitude);
		logger.debug("storesLongitude="+storesLongitude);
		
		/*-------------------------------------------------------*/
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("useracegi", useracegi);
		model.put("nttdto", nttdto);

		model.put("useraccess", useraccess);
		model.put("storesName", storesName);
		model.put("storesLatitude", storesLatitude);
		model.put("storesLongitude", storesLongitude);
		model.put("storesAddress1", storesAddress1);
		model.put("storesAddress2", storesAddress2);
		model.put("storesShelf", storesShelf);
		
		return new ModelAndView(view, model);
	}

}
