package com.retailsbs.logistikapp.web.ctrl.sales;

import java.text.ParseException;
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
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.retailsbs.logistikapp.financial.dto.GetMetricsSaleDTO;
import com.retailsbs.logistikapp.financial.dto.MetricsSaleDTO;
import com.retailsbs.logistikapp.financial.dto.ReportDTO;
import com.retailsbs.logistikapp.financial.dto.ReportSearchCriteria;
import com.retailsbs.logistikapp.financial.dto.SaleByCategoryCriteria;
import com.retailsbs.logistikapp.financial.dto.SaleByCategoryDTO;
import com.retailsbs.logistikapp.financial.service.FinancialService;
import com.retailsbs.logistikapp.supplier.service.SupplierService;
import com.retailsbs.logistikapp.user.domain.Access;
import com.retailsbs.logistikapp.user.domain.Quantities;
import com.retailsbs.logistikapp.user.domain.User;
import com.retailsbs.logistikapp.user.domain.UserAcegi;
import com.retailsbs.logistikapp.user.dto.AccessTinyDTO;
import com.retailsbs.logistikapp.user.dto.NotificationTodaySearchCriteria;
import com.retailsbs.logistikapp.user.exception.AccessNotFoundException;
import com.retailsbs.logistikapp.user.exception.UserNotFoundException;
import com.retailsbs.logistikapp.user.service.UserService;

/**
 * Controller para la vista general de productreport
 * 
 * @author Gabriela Jaime
 * @since 16-08-2016
 */
public class ProductViewReportCtrl implements Controller {

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

	public void setSupplierService(SupplierService supplierService) {
	}

	public void setView(String view) {
		this.view = view;
	}

	public void setACTIVE(String aCTIVE) {
		ACTIVE = aCTIVE;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0,
			HttpServletResponse arg1) throws AccessNotFoundException,
			UserNotFoundException, ServletRequestBindingException,
			ParseException {
		logger.debug("----- HANDLE REQUEST -----");
		/*-------------------------------------------------------*/
		logger.debug("----- USER CONTEXT -----");
		// Se obtiene el contexto del usuario
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		UserAcegi useracegi = (UserAcegi) auth.getPrincipal();
		Long id_supplier = userService
				.getAccessByIdUser(useracegi.getId_user()).get(0)
				.getId_supplier();

		/*-------------------------------------------------------*/
		// //////////////////////////////////////////////////////////////////////
		// Se obtienen los datos de acceso para el usuario
		List<Access> accesslist = userService.getAccessByIdUser(useracegi
				.getId_user());
		// Se obtiene el primer acceso por defecto
		Access useraccess = accesslist.get(0);
		// ////////////////////////////////////////////
		logger.debug("----- ACCESS SUPPLIER -----");
		List<AccessTinyDTO> accesssupplier = userService
				.getAccessSupplierByIdUser(useracegi.getId_user());
		logger.debug("accesssupplier=" + accesssupplier.size());
		Long idsupplier = (accesssupplier.get(0)).getId();
		logger.debug("idsupplier=" + idsupplier);
		/*-------------------------------------------------------*/
		NotificationTodaySearchCriteria dtoqty = new NotificationTodaySearchCriteria();
		dtoqty.setProfile(useracegi.getProfile());
		dtoqty.setFecha(new Date());
		dtoqty.setId_supplier(useraccess.getId_supplier());
		dtoqty.setId_user(useracegi.getId_user());
		logger.debug("----- HEADER/NOTIFICATION -----");
		Quantities nttdto = userService.getQtyAlertAndMessagesByIdUser(dtoqty);
		// HeaderNotificationDTO nttdto =
		// userService.getHeaderNotificationByIdUser(useracegi.getId_user());
		logger.debug("alert=" + nttdto.getAlert_qty() + " | message="
				+ nttdto.getMessage_qty() + " | todo=" + nttdto.getTodo_qty());
		/*-------------------------------------------------------*/
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date hoy = new Date();
		Date fini = null;
		Date ffin = null;
		if (arg0.getParameter("fini") != null
				&& !arg0.getParameter("fini").equals("")
				&& arg0.getParameter("ffin") != null
				&& !arg0.getParameter("ffin").equals("")) {
			fini = sdf2.parse(arg0.getParameter("fini") + " 00:00:00");
			ffin = sdf2.parse(arg0.getParameter("ffin") + " 23:59:59");
		} else {
			// Por defecto el rango de fechas es el dia actual
			fini = sdf2.parse(sdf1.format(hoy) + " 00:00:00");
			ffin = sdf2.parse(sdf1.format(hoy) + " 23:59:59");
		}
		logger.debug("REPORT ==> fini=" + sdf1.format(fini));
		logger.debug("REPORT ==> ffin=" + sdf1.format(ffin));

		String profile = useracegi.getProfile();

		// Se obtiene el id del usuario
		Long id_user = ServletRequestUtils.getLongParameter(arg0, "idu", (profile.contains("DRI") ? useracegi.getId_user() : 0));

		/*-------------------------------------------------------*/
		ReportSearchCriteria dto = new ReportSearchCriteria();
		dto.setId_supplier(id_supplier);

		List<String> status = new ArrayList<String>();
		status.add("APR");
		status.add("CAN");
		dto.setStatus(status);
		dto.setInvoice(fini);
		dto.setInvoice_fin(ffin);
		dto.setId_user(id_user);

		// Se obtiene lista de transacciones por dia
		List<ReportDTO> list = financialService.getReportByDate(dto);
		logger.debug("list=" + list);

		// Se obtienen las metricas de venta
		GetMetricsSaleDTO dtoSale = new GetMetricsSaleDTO();
		dtoSale.setId_supplier(id_supplier);
		dtoSale.setId_user(id_user);
		dtoSale.setInitDate(fini);
		dtoSale.setFinalDate(ffin);

		if (list.size() > 0) {
			ArrayList<Long> ids = new ArrayList<>();
			for (ReportDTO i : list) {
				ids.add(i.getId_order());
			}
			
			dtoSale.setIds(ids.toArray(new Long[list.size()]));
		}

		MetricsSaleDTO metrics = financialService.getMetricsSale(dtoSale);

		// Se obtienen las ventas por categoria
		SaleByCategoryCriteria criteriaSC = new SaleByCategoryCriteria();
		criteriaSC.setId_supplier(id_supplier);
		criteriaSC.setFini(fini);
		criteriaSC.setFfin(ffin);
		criteriaSC.setId_user(id_user);

		if (dtoSale.getIds() != null) {
			criteriaSC.setIds(dtoSale.getIds());
		}
		
		List<SaleByCategoryDTO> categorysales = financialService.getSaleByCategory(criteriaSC);
		
		// Se obtiene información del vendedor
		User user = userService.getUserById(id_user);

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("useracegi", useracegi);
		model.put("accesssupplier", accesssupplier);
		model.put("nttdto", nttdto);

		model.put("useraccess", useraccess);
		model.put("list", list);

		// model.put("supplier", supplier);
		model.put("profile", profile);
		model.put("ACTIVE", ACTIVE);
		// model.put("NO_ACTIVE", NO_ACTIVE);
		model.put("fini", sdf1.format(fini));
		model.put("ffin", sdf1.format(ffin));
		model.put("metrics", metrics);
		model.put("categorysales", categorysales);
		model.put("idu", id_user);
		model.put("user", user);

		return new ModelAndView(view, model);
	}

}
