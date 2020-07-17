package com.retailsbs.logistikapp.web.ctrl.admin;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.retailsbs.logistikapp.financial.domain.CategoryProduct;
import com.retailsbs.logistikapp.financial.domain.Product;
import com.retailsbs.logistikapp.financial.service.FinancialService;
import com.retailsbs.logistikapp.logistic.domain.Route;
import com.retailsbs.logistikapp.logistic.domain.Travel;
import com.retailsbs.logistikapp.logistic.service.LogisticService;
import com.retailsbs.logistikapp.retail.domain.Retail;
import com.retailsbs.logistikapp.retail.domain.RetailCategory;
import com.retailsbs.logistikapp.retail.domain.StoreCategory;
import com.retailsbs.logistikapp.retail.service.RetailService;
import com.retailsbs.logistikapp.supplier.service.SupplierService;
import com.retailsbs.logistikapp.user.service.UserService;
/**
 * Controlador para eliminar supplier
 * @author Juan Carlos Ramos
 * @since 24-11-2015 
 */
public class SupplierDelCtrl implements Controller {
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	private SupplierService supplierService;
	private LogisticService logisticService;
	private RetailService retailService;
	private FinancialService financialService;
//	private FinantialService finantialService;
	private UserService userService;
	private String view;

	public void setRetailService(RetailService retailService) {
		this.retailService = retailService;
	}
	public void setLogisticService(LogisticService logisticService) {
		this.logisticService = logisticService;
	}
	public void setSupplierService(SupplierService supplierService) {
		this.supplierService = supplierService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public void setView(String view) {
		this.view = view;
	}
	public void setFinancialService(FinancialService financialService) {
		this.financialService = financialService;
	}
	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		logger.debug("---- HANDLE/REQUEST ----");
		//obtienen los ids supplier que se borraran
		String[] ids_sup = null;
		String ids = null;
		List<Long> ids_supplier = new ArrayList<Long>();
		if(arg0.getParameter("idsSupp")!=null && !arg0.getParameter("idsSupp").equals("")){
			ids = ServletRequestUtils.getStringParameter(arg0, "idsSupp");
			ids_sup = ids.split(",");
			for(int i=0; i<ids_sup.length; i++)
				ids_supplier.add(Long.parseLong(ids_sup[i]));
		}
		logger.debug("ids_sup="+ids_sup);
		logger.debug("ids_supplier="+ids_supplier);
		int row = 0;
		//ciclo para eliminar uno por uno cada supplier y los datos necesario a borrar
		List<CategoryProduct> list_CatPro = new ArrayList<CategoryProduct>();
		List<Product> list_product = new ArrayList<Product>();
		List<Route> list_route = new ArrayList<Route>();
		List<Travel> list_travel = new ArrayList<Travel>();
		List<StoreCategory> list_store_cat = new ArrayList<StoreCategory>();
		List<Retail> list_retail = new ArrayList<Retail>();
		List<RetailCategory> list_retail_cat = new ArrayList<RetailCategory>();
		for(int i=0; i<ids_supplier.size(); i++){
			logger.debug("id_supplier a eliminar = "+ids_supplier.get(i));
		// 1. eliminar calendar
			row = userService.delCalendarByIdSupplier(ids_supplier.get(i));
			logger.debug("numero de calendar eliminados = "+row);
		// 2. Eliminar notificaciones
			row = userService.delNotificationByIdSupplier(ids_supplier.get(i));
			logger.debug("numero de notification eliminadas = "+row);
		// 3. Eliminar Acces
			row = userService.delAccessByIdSupplier(ids_supplier.get(i));
			logger.debug("numero de access eliminadas = "+row);
		// 4. Eliminar Cost
//			row = finantialService.delCostByIdSupplier(ids_supplier.get(i));
//			logger.debug("numero de cost eliminados = "+row);
		// 5. obtener order  por id_supplier
//			finantialService.getOrderByIdSupplier(ids_supplier.get(i));
//			delOrderDetailByIdOrder();
//			delOrderByIdSupplier()ids_supplier.get(i);
		// 6. obtener categoryProduct por id_supplier
			list_CatPro = financialService.getCategoryProductByIdSupplier(ids_supplier.get(i));
			logger.debug("list_CatPro="+list_CatPro.size());
			// Eliminacion de ----- CATEGORY PRODUCT ---- 
			for(int cp=0; cp<list_CatPro.size(); cp++){
				// obtener productos por id_category_product
				list_product = financialService.getProductByIdCategoryProduct(list_CatPro.get(cp).getId_category_product());
				logger.debug("list_product="+list_product.size());
				//se borra orderDetail por id_product
//				finantialService.delOrderDetailByIdProduct--pendiente
				// Eliminar product by id_category_product
				row = financialService.delProductByIdCategoryProduct(list_CatPro.get(cp).getId_category_product());
				logger.debug("numero de productos eliminados = "+row);
			} // cp<list_CatPro.size()
			// Eliminar categoryProduct por id_supplier
			row = financialService.delCategoryProductByIdSupplier(ids_supplier.get(i));
			logger.debug("numero de categoryProduct eliminados = "+row);
		// 7. obtencion de ruta por medio de id_supplier
			list_route = logisticService.getAllRouteByIdSupplier(ids_supplier.get(i));
			logger.debug("list_route="+list_route.size());
			// ELIMINACION DE ROUTE Y DATOS RELACIONADOS
			for(int rut=0; rut<list_route.size(); rut++){
				// eliminacion de routeStore by id route
				row = logisticService.delRouteStoreByIdRoute(list_route.get(rut).getId_route());
				logger.debug("numero de routeStore eliminados = "+row);
				// se obtienen los viajes por id_route
				list_travel = logisticService.getTravelByIdRoute(list_route.get(rut).getId_route());
				logger.debug("list_travel="+list_travel.size());
				for(int tra=0; tra<list_travel.size(); tra++){
					// se eliminan los waybill por id_travel
					row = logisticService.delWayBillByIdTravel(list_travel.get(tra).getId_travel());
					logger.debug("numero de way bill eliminados = "+row);
				} // for tra<list_travel.size()
				//se elimina n travel by id_route
				row = logisticService.delTravelByIdRoute(list_route.get(rut).getId_route());
				logger.debug("numero de travel eliminados ok! "+row);
			} // for rut<list_route.size()
			// se elimina la ruta por id_supplier
			row = logisticService.delRouteByIdSupplier(ids_supplier.get(i));
			logger.debug("numero de route eliminados ok! "+row);
		// 8. se obtiene store category por id_supplier
			list_store_cat = retailService.getAllStoreCategoryByIdSupplier(ids_supplier.get(i));
			logger.debug("list_store_cat="+list_store_cat.size());
			// ELIMINACION DE STORE CATEGORY Y DATOS NECESARIOS A BORRAR
			for(int sc=0; sc<list_store_cat.size(); sc++){
				// se elimina store por id_store_cat
				row =  retailService.delStoreByIdStoreCategory(list_store_cat.get(sc).getId_store_category());
				logger.debug("numero de store category eliminaados ok! "+row);
			} // for sc<list_store_cat.size()
			row = retailService.delStoreCategoryByIdSupplier(ids_supplier.get(i));
			logger.debug("numero de store category eliminados ok! "+row);
		// 9. se obtiene retail by id_supplier 
			list_retail = retailService.getRetailByIdSupplier(ids_supplier.get(i));
			logger.debug("list_retail="+list_retail.size());
			// SE ELIMINA RETAIL Y LOS DATOS NECESARIOS
			for(int ret=0; ret<list_retail.size(); ret++){
				row = retailService.delRetailDeptByIdRetail(list_retail.get(ret).getId_retail());
				logger.debug("numero de retailDept eliminados ok! "+row);
			} // for ret<list_retail.size()
			// eliminacion de retail por id_supplier
			row = retailService.delRetailByIdSupplier(ids_supplier.get(i));
			logger.debug("numero de retails eliminados ok! "+row);
		// 10. Se obtiene RetailCategory por id_supplier
			list_retail_cat = retailService.getAllRetailCategoryByIdSupplier(ids_supplier.get(i));
			logger.debug("list_retail_cat="+list_retail_cat.size());
			//SE ELIMINA RETAIL CATEGORY Y DATOS DECESARIOS
//			for(int rc=0; rc<list_retail_cat.size(); rc++){
//				//delRetailCategoryGoalByIdRetailCategory;
//			} // for rc<list_retail_cat.size()
			row = retailService.delRetailCategoryByIdSupplier(ids_supplier.get(i));
			logger.debug("numero de retailCategory eliminados ok! "+row);
		// 11. eliminar supplier por id_supplier
			row = supplierService.delSupplierById(ids_supplier.get(i));
			logger.debug("supplier eliminado ok! "+row);
		}// for i<ids_supplier.size()
		

		return new ModelAndView(view);
	}


	
}
