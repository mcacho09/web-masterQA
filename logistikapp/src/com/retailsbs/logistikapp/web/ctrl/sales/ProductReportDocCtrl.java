package com.retailsbs.logistikapp.web.ctrl.sales;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

import com.retailsbs.logistikapp.financial.dto.GetMetricsSaleDTO;
import com.retailsbs.logistikapp.financial.dto.MetricsSaleDTO;
import com.retailsbs.logistikapp.financial.dto.ProductDTO;
import com.retailsbs.logistikapp.financial.dto.ProductSearchCriteria;
import com.retailsbs.logistikapp.financial.dto.ReportDTO;
import com.retailsbs.logistikapp.financial.dto.ReportSearchCriteria;
import com.retailsbs.logistikapp.financial.dto.SaleByCategoryCriteria;
import com.retailsbs.logistikapp.financial.dto.SaleByCategoryDTO;
import com.retailsbs.logistikapp.financial.service.FinancialService;
import com.retailsbs.logistikapp.user.domain.Access;
import com.retailsbs.logistikapp.user.domain.UserAcegi;
import com.retailsbs.logistikapp.user.service.UserService;
import com.retailsbs.logistikapp.web.dto.ReportTrxExtDTO;

public class ProductReportDocCtrl implements Controller {

	protected final Log logger = LogFactory.getLog(getClass());
	private UserService userService;
	private FinancialService financialService;
	private String view;
	private List<ProductDTO> products = null;

	private Double iva = 0.16;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public void setView(String view) {
		this.view = view;
	}
	public void setFinancialService(FinancialService financialService) {
		this.financialService = financialService;
	}

	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		logger.debug("REPORT/TRX ==> HANDLE/REQUEST");
		
		/*-------------------------------------------------------*/
		// Se obtiene el contexto del usuario
		logger.debug("REPORT/TRX ==> USER CONTEXT");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserAcegi useracegi = (UserAcegi) auth.getPrincipal();
		String profile = useracegi.getProfile();
		logger.debug("REPORT/TRX ==> profile="+profile);
		List<Access> useraccess = userService.getAccessByIdUser(useracegi.getId_user());
		Access access = useraccess.get(0);
		Long id_supplier = access.getId_supplier();
		logger.debug("REPORT/TRX ==> id_supplier="+id_supplier);

		/*-------------------------------------------------------*/
		// Se obtiene rango de fechas
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy"); 
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		SimpleDateFormat sdf3 = new SimpleDateFormat("yyyyMMdd-HHmmss");
		
		//Se obtiene el id del usuario
		Long id_user = ServletRequestUtils.getLongParameter(arg0, "idu", (useracegi.getProfile().contains("DRI") ? useracegi.getId_user() : 0));
		
		// Se controlan parametros fini y ffin
		Date hoy = new Date();
		Date fini = null;		
		Date ffin = null;
		try {
			if ( arg0.getParameter("fini") != null && !arg0.getParameter("fini").equals("") &&
					arg0.getParameter("ffin") != null && !arg0.getParameter("ffin").equals("") ) {
				fini = sdf2.parse( arg0.getParameter("fini") + " 00:00:00");
				ffin = sdf2.parse( arg0.getParameter("ffin") + " 23:59:59");
			} else {
					// Rango por defecto para usuarios con perfi chofer
					Calendar cal = Calendar.getInstance();
					cal.add(Calendar.DATE, -3);
					Date after = cal.getTime();
					fini = sdf2.parse( sdf1.format(after)+" 00:00:00");
					ffin = sdf2.parse( sdf1.format(hoy)+" 23:59:59");
			}
		} catch( Exception e) {
			logger.error("REPORT/TRX ==> Error en formato de fechas, se dejarán valores por defecto");
			logger.error("REPORT/TRX ==> fini="+arg0.getParameter("fini"));
			logger.error("REPORT/TRX ==> ffin="+arg0.getParameter("ffin"));
			// Por defecto el rango de fechas es el dia actual
			fini = sdf2.parse( sdf1.format(hoy)+" 00:00:00");		
			ffin = sdf2.parse( sdf1.format(hoy)+" 23:59:59");
		}
		logger.debug("REPORT/TRX ==> fini="+sdf1.format(fini));
		logger.debug("REPORT/TRX ==> ffin="+sdf1.format(ffin));

		String fileName = "reporte-trx-resumen-"+ sdf3.format(new Date()) + ".csv";
		
		ProductSearchCriteria dtopdt = new ProductSearchCriteria();
		dtopdt.setId_supplier(id_supplier);
		this.products = financialService.selectProductByCriteria(dtopdt);
		
		// Se retorna lista
		
		//logger.debug("REPORT/TRX ==> subtotal="+this.sub_list.size());
		
		// Se inicializa objeto para criterio de busqueda
		ReportSearchCriteria dto = new ReportSearchCriteria();
		dto.setId_supplier(id_supplier);
		dto.setInvoice(fini);
		dto.setInvoice_fin(ffin);
		String tmpids = ServletRequestUtils.getStringParameter(arg0, "ids", "");
		//System.out.println("Los ids " + tmpids);
		List<Long> tmp = new ArrayList<>();
		if (!tmpids.isEmpty()) {
			String[] ids = tmpids.split(",");
			for (String i : ids) {
				tmp.add(Long.parseLong(i));
			}
			dto.setIds(tmp);
		}

		String tmpidus = ServletRequestUtils.getStringParameter(arg0, "idus", "");
		//System.out.println("Los idus " + tmpidus);
		List<Long> idus = new ArrayList<>();
		if (!tmpidus.isEmpty()) {
			String[] ids = tmpidus.split(",");
			for (String i: ids) {
				idus.add(Long.parseLong(i));
			}
			dto.setIdus(idus);
		} else {
			dto.setId_user(id_user);
		}
		
		List<ReportDTO> list = financialService.getReportByDate(dto);
		
		//System.out.println("list: " + list.size());
		ArrayList<Double> tax_list = new ArrayList<>();
		ArrayList<Double> sub_list = new ArrayList<>();
		
		
		for(int i=0;i<list.size(); i++){
			Double sub = 0.0;
			Double tax = 0.0;
			for(int j=0;j<products.size(); j++){
				if(products.get(j).getId_product().equals(list.get(i).getId_product()) && (products.get(j).getTax()!=null)){
					if(products.get(j).getTax().equals(1)){
						tax = list.get(i).getQty_vta_sug() * iva;
					}
				}
			}
			sub = list.get(i).getQty_vta_sug() - tax;
			tax_list.add(tax);
			sub_list.add(sub);
		}
		
		/*
		for (final ProductDTO product : products) {
			Double tax = 0d;
			Double sub = 0d;
			if(product.getTax() != null){
				if(product.getTax().equals(1)) {
					System.out.println(product.getPrice_sale());
					tax = product.getPrice_sale() * iva;	
				}
			}
			sub = product.getPrice_sale() - tax;
			sub_list.add(sub);
			tax_list.add(tax);
		}
		
		for (final ReportDTO trx : list) {
			if(trx.getId_order().equals(0)){
				
			}
		}
		*/
		
		// Se obtienen las metricas de venta
		GetMetricsSaleDTO dtoSale = new GetMetricsSaleDTO();
		dtoSale.setId_supplier(id_supplier);
		dtoSale.setInitDate(fini);
		dtoSale.setFinalDate(ffin);
		if (tmp.size() > 0) {
			Long[] longs = new Long[tmp.size()];
			dtoSale.setIds(tmp.toArray(longs));
		}
		
		if (idus.size() > 0) {
			Long[] longs = new Long[idus.size()];
			dtoSale.setIdus(idus.toArray(longs));
		} else {
			dtoSale.setId_user(id_user);
		}
		
		MetricsSaleDTO metrics = financialService.getMetricsSale(dtoSale);
		
		// Se obtienen las ventas por categoria
		SaleByCategoryCriteria criteriaSC = new SaleByCategoryCriteria();
		criteriaSC.setId_supplier(id_supplier);
		criteriaSC.setFini(fini);
		criteriaSC.setFfin(ffin);
		
		if (tmp.size() > 0) {
			Long[] longs = new Long[tmp.size()];
			criteriaSC.setIds(tmp.toArray(longs));
		}
		
		if (idus.size() > 0) {
			Long[] longs = new Long[idus.size()];
			criteriaSC.setIdus(idus.toArray(longs));
		} else {
			criteriaSC.setId_user(id_user);
		}
		
		List<SaleByCategoryDTO> categorysales = financialService.getSaleByCategory(criteriaSC);

		// Se crea el libro
		logger.debug("REPORT/TRX ==> Creando libro");

		String contentDisposition = "attachment" + ";filename=\"" + fileName + "\"";
		arg1.setHeader("Content-disposition", contentDisposition);
		arg1.setContentType("application/csv");

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("list", list);
		model.put("metrics", metrics);
		model.put("categorysales", categorysales);
		model.put("sub_list", sub_list);
		model.put("tax_list", tax_list);
		return new ModelAndView(view, model);
	}
}
