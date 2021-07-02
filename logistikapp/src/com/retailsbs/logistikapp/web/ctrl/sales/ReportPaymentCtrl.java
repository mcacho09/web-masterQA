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
import com.retailsbs.logistikapp.financial.dto.ReportExtDTO;
import com.retailsbs.logistikapp.financial.dto.ReportExtSearchCriteria;
import com.retailsbs.logistikapp.financial.dto.SaleByCategoryCriteria;
import com.retailsbs.logistikapp.financial.dto.SaleByCategoryDTO;
import com.retailsbs.logistikapp.financial.service.FinancialService;
import com.retailsbs.logistikapp.user.domain.Access;
import com.retailsbs.logistikapp.user.domain.UserAcegi;
import com.retailsbs.logistikapp.user.service.UserService;
import com.retailsbs.logistikapp.web.dto.ReportTrxExtDTO;

public class ReportPaymentCtrl implements Controller {

	private List<ReportTrxExtDTO> list = null;
	private List<ProductDTO> products = null;
	
	protected final Log logger = LogFactory.getLog(getClass());

	private UserService userService;
	private FinancialService financialService;
	private String view;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setView(String view) {
		this.view = view;
	}

	public void setFinancialService(FinancialService financialService) {
		this.financialService = financialService;
	}

	public ModelAndView handleRequest(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		logger.debug("REPORT/TRX ==> HANDLE/REQUEST");
		
		/*-------------------------------------------------------*/
		// Se obtiene el contexto del usuario
		logger.debug("REPORT/TRX ==> USER CONTEXT");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserAcegi useracegi = (UserAcegi) auth.getPrincipal();
		Long id_user = ServletRequestUtils.getLongParameter(arg0, "idu", (useracegi.getProfile().contains("DRI")? useracegi.getId_user() : 0));
		String profile = useracegi.getProfile();
		logger.debug("REPORT/TRX ==> id_user="+id_user);
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
		logger.debug("REPORT/TRX ==> fini="+sdf2.format(fini));
		logger.debug("REPORT/TRX ==> ffin="+sdf2.format(ffin));
		
		/*-------------------------------------------------------*/
		// Se define criterio de busqueda para obtener listado de productos
		ProductSearchCriteria dtopdt = new ProductSearchCriteria();
		dtopdt.setId_supplier(id_supplier);
		// Se obtiene listado de productos
		// Se va a setear una variable global
		this.products = financialService.selectProductByCriteria(dtopdt);
		logger.debug("REPORT/TRX ==> products="+this.products.size());
		
		// Se inicializa listado de objetos
		this.list = new ArrayList<ReportTrxExtDTO>();
		/*-------------------------------------------------------*/
		// Se obtienen datos
		ReportExtSearchCriteria dto = new ReportExtSearchCriteria();
		dto.setFini(fini);
		dto.setFfin(ffin);
		dto.setId_supplier(id_supplier);
		String tmpids = ServletRequestUtils.getStringParameter(arg0, "ids", "");
		System.out.println("Los ids " + tmpids);
		if (!tmpids.isEmpty()) {
			String[] ids = tmpids.split(",");
			List<Long> tmp = new ArrayList<>();
			for (String i : ids) {
				tmp.add(Long.parseLong(i));
			}
			dto.setIds(tmp);
		}
		
		String tmpidus = ServletRequestUtils.getStringParameter(arg0, "idus", "");
		System.out.println("Los idus " + tmpidus);
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
		
		List<ReportExtDTO> pdts = financialService.getReportExtByDate(dto);
		logger.debug("REPORT/TRX ==> pdts="+pdts.size());
		
		ReportExtDTO pdt = null;
		
		// Se define listado para guardar datos para el reporte
		// Se va a definir como variable global
		// List<ReportTrxExtDTO> list = new ArrayList<ReportTrxExtDTO>();
		ReportTrxExtDTO rptext = null;
		
		List<Double> values = null;
		
		int idx0 = 0;
		
		// Se recorre lista de datos para preparar datos para el reporte
		for ( int i=0; i<pdts.size(); i++ ) {
			if (pdts.get(i).getTypetrx() == null) continue;
			if (pdts.get(i).getTypetrx().contentEquals("CTO")) continue;
			pdt = (ReportExtDTO)pdts.get(i);
			logger.debug("REPORT/TRX ==> input ["+i+"] numtrx="+pdt.getNumtrx()+ " | product="+pdt.getId_product()+" | typetrx="+pdt.getTypetrx()+" | quantity="+pdt.getQuantity()+" | price="+pdt.getPrice()+" | cost="+pdt.getCost());
			
			int idx1 = getIndexRpt(pdt.getId_order(), pdt.getTypetrx());
			logger.debug("REPORT/TRX ==> idx1="+idx1);
			
			int idx2 = getIndexPdt(pdt);
			logger.debug("REPORT/TRX ==> idx2="+idx2);
			
			if ( idx1 == -1 ) {
				if (idx2 == -1) continue;
				logger.debug("REPORT/TRX ==> N U E V O");
				rptext = new ReportTrxExtDTO();
				rptext.setId_order(pdt.getId_order());
				rptext.setInvoice(pdt.getInvoice());
				rptext.setNumtrx(pdt.getNumtrx());
				rptext.setId_store(pdt.getId_store());
				rptext.setStoreCode(pdt.getStoreCode());
				rptext.setStoreName(pdt.getStoreName());
				rptext.setId_user(pdt.getId_user());
				rptext.setUserName(pdt.getUserName());
				rptext.setTypetrx(pdt.getTypetrx());
				rptext.setStatustrx(pdt.getStatustrx());
				
				// Se inicializa el listado para guardar valores por producto
				values = new ArrayList<Double>();
				for ( int x=0; x<this.products.size(); x++ )
					values.add(0.0);
				// Se agrega una lista para los contadores por producto
				if ( !pdt.getTypetrx().equals("VTA") )
					values.set( idx2, (double)pdt.getQuantity() );
				else
					values.set( idx2, (double)pdt.getPrice() );
				
				rptext.setValues(values);
				// Se agrega un elemento nuevo
				this.list.add(idx0, rptext);
				logger.debug("REPORT/TRX ==> output/new ["+idx0+"] order="+this.list.get(idx0).getId_order()+
						" | numtrx="+this.list.get(idx0).getNumtrx()+
						" | type="+this.list.get(idx0).getTypetrx()+
						" | value="+this.list.get(idx0).getValues());
				idx0 = idx0 +1;
				
				// Si es una venta se cotrola para agregar un registro de cantidad 
				if ( pdt.getTypetrx().equals("VTA") ) {
					// Se agrega registro de venta cantidad
					rptext = new ReportTrxExtDTO();
					rptext.setId_order(pdt.getId_order());
					rptext.setInvoice(pdt.getInvoice());
					rptext.setNumtrx(pdt.getNumtrx());
					rptext.setId_store(pdt.getId_store());
					rptext.setStoreCode(pdt.getStoreCode());
					rptext.setStoreName(pdt.getStoreName());
					rptext.setId_user(pdt.getId_user());
					rptext.setUserName(pdt.getUserName());
					rptext.setTypetrx("VTQ");
					rptext.setStatustrx(pdt.getStatustrx());
					// Se inicializa el listado para guardar valores por producto
					values = new ArrayList<Double>();
					for ( int x=0; x<this.products.size(); x++ )
						values.add(0.0);
					values.set( idx2, (double)pdt.getQuantity() );
					rptext.setValues(values);
					// Se agrega un elemento nuevo
					this.list.add(idx0, rptext);
					logger.debug("REPORT/TRX ==> output/new ["+idx0+"] order="+this.list.get(idx0).getId_order()+
							" | numtrx="+this.list.get(idx0).getNumtrx()+
							" | type="+this.list.get(idx0).getTypetrx()+
							" | value="+this.list.get(idx0).getValues());
					idx0 = idx0 +1;
					// Se agrega registro de costo venta
					/*rptext = new ReportTrxExtDTO();
					rptext.setId_order(pdt.getId_order());
					rptext.setInvoice(pdt.getInvoice());
					rptext.setNumtrx(pdt.getNumtrx());
					rptext.setId_store(pdt.getId_store());
					rptext.setStoreCode(pdt.getStoreCode());
					rptext.setStoreName(pdt.getStoreName());
					rptext.setId_user(pdt.getId_user());
					rptext.setUserName(pdt.getUserName());
					rptext.setTypetrx("CTO");
					rptext.setStatustrx(pdt.getStatustrx());
					// Se inicializa el listado para guardar valores por producto
					values = new ArrayList<Double>();
					for ( int x=0; x<this.products.size(); x++ )
						values.add(0.0);
					values.set( idx2, (double)pdt.getCost() );
					rptext.setValues(values);
					// Se agrega un elemento nuevo
					this.list.add(idx0, rptext);
					logger.debug("REPORT/TRX ==> output/new ["+idx0+"] order="+this.list.get(idx0).getId_order()+
							" | numtrx="+this.list.get(idx0).getNumtrx()+
							" | type="+this.list.get(idx0).getTypetrx()+
							" | value="+this.list.get(idx0).getValues());
					idx0 = idx0 +1;*/
				}
			} else if (idx2 > -1) {
				// Se actualiza
				logger.debug("REPORT/TRX ==> A C T U A L I Z A R");

				// Se obtienen valores actuales
				rptext = (ReportTrxExtDTO)this.list.get(idx1);
				values = rptext.getValues();
				
				if ( !pdt.getTypetrx().equals("VTA") )
					values.set( idx2, (double)pdt.getQuantity() );
				else
					values.set( idx2, (double)pdt.getPrice() );
				
				rptext.setValues(values);
				// Se actualiza el registro que ya existe
				this.list.set(idx1, rptext);
				logger.debug("REPORT/TRX ==> output/update ["+idx1+"] order="+this.list.get(idx1).getId_order()+
						" | numtrx="+this.list.get(idx1).getNumtrx()+
						" | type="+this.list.get(idx1).getTypetrx()+
						" | value="+this.list.get(idx1).getValues());
				
				if ( pdt.getTypetrx().equals("VTA") ) {
					// Se obtiene indice para registro de cantidad de venta
					int idx11 = getIndexRpt(pdt.getId_order(), "VTQ");
					logger.debug("REPORT/TRX ==> idx11="+idx11);
					
					// Se obtienen valores actuales
					rptext = (ReportTrxExtDTO)this.list.get(idx11);
					values = rptext.getValues();
					values.set( idx2, (double)pdt.getQuantity() );
					// Se actualiza el registro que ya existe
					rptext.setValues(values);
					this.list.set(idx11, rptext);
					logger.debug("REPORT/TRX ==> output/update ["+idx11+"] order="+this.list.get(idx11).getId_order()+
							" | numtrx="+this.list.get(idx11).getNumtrx()+
							" | type="+this.list.get(idx11).getTypetrx()+
							" | value="+this.list.get(idx11).getValues());
					
					// Se obtiene indice para registro de costo venta
					/*int idx12 = getIndexRpt(pdt.getId_order(), "CTO");
					logger.debug("REPORT/TRX ==> idx12="+idx12);
					
					// Se obtienen valores actuales
					rptext = (ReportTrxExtDTO)this.list.get(idx12);
					values = rptext.getValues();
					values.set( idx2, (double)pdt.getCost() );
					// Se actualiza el registro que ya existe
					rptext.setValues(values);
					this.list.set(idx12, rptext);
					logger.debug("REPORT/TRX ==> output/update ["+idx12+"] order="+this.list.get(idx12).getId_order()+
							" | numtrx="+this.list.get(idx12).getNumtrx()+
							" | type="+this.list.get(idx12).getTypetrx()+
							" | value="+this.list.get(idx12).getValues());
					*/
				}
				
			}
			
		}
				
		/*-------------------------------------------------------*/
		Double total = null;
		// Se recorre listado para calcular totales
		for ( int i=0; i<this.list.size(); i++ ) {
			// Obtengo los objetos
			rptext = (ReportTrxExtDTO) this.list.get(i);
			values = rptext.getValues();
			total = 0.0;
			// Se recorre listado de datos para calcular totales
			for ( int j=0; j<values.size(); j++ )
				total = total + (values.get(j) != null ? values.get(j) : 0.0);
			
			// Se setea total y objeto de la lista
			rptext.setTotal(total);
			this.list.set(i, rptext);
		}
		
		/*-------------------------------------------------------*/
		// Se crea archivo CSV
		String fileName = "reporte-trx-producto-" + sdf3.format(hoy) + ".csv";
		String contentDisposition = "attachment" +";filename=\"" + fileName + "\"";
		arg1.setHeader("Content-disposition", contentDisposition);
		arg1.setContentType("application/csv");
		
		// Se obtienen las metricas de venta
		GetMetricsSaleDTO dtoSale = new GetMetricsSaleDTO();
		dtoSale.setId_supplier(id_supplier);
		dtoSale.setInitDate(fini);
		dtoSale.setFinalDate(ffin);
		
		if (!tmpids.isEmpty()) {
			String[] ids = tmpids.split(",");
			List<Long> tmp = new ArrayList<>();
			for (String i : ids) {
				tmp.add(Long.parseLong(i));
			}
			
			if (tmp.size() > 0) {
				Long[] longs = new Long[tmp.size()];
				dtoSale.setIds(tmp.toArray(longs));
			}
		} else {
			dtoSale.setId_user(id_user);
		}
		
		if (!tmpidus.isEmpty()) {
			String[] ids = tmpidus.split(",");
			List<Long> tmp = new ArrayList<>();
			for (String i : ids) {
				tmp.add(Long.parseLong(i));
			}
			
			if (tmp.size() > 0) {
				Long[] longs = new Long[tmp.size()];
				dtoSale.setIdus(tmp.toArray(longs));
			}
		}
		
		MetricsSaleDTO metrics = financialService.getMetricsSale(dtoSale);
		
		// Se obtienen las ventas por categoria
		SaleByCategoryCriteria criteriaSC = new SaleByCategoryCriteria();
		criteriaSC.setId_supplier(id_supplier);
		criteriaSC.setFini(fini);
		criteriaSC.setFfin(ffin);
		
		if (dtoSale.getIds() != null) {
			criteriaSC.setIds(dtoSale.getIds());
		}
		
		if (!tmpidus.isEmpty()) {
			String[] ids = tmpidus.split(",");
			List<Long> tmp = new ArrayList<>();
			for (String i : ids) {
				tmp.add(Long.parseLong(i));
			}
			
			if (tmp.size() > 0) {
				Long[] longs = new Long[tmp.size()];
				criteriaSC.setIdus(tmp.toArray(longs));
			}
		} else {
			
		}
		
		
		List<SaleByCategoryDTO> categorysales = financialService.getSaleByCategory(criteriaSC);
		
		/*-------------------------------------------------------*/
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("products", products);
		model.put("list", list);
		model.put("metrics", metrics);
		model.put("categorysales", categorysales);
		return new ModelAndView(view, model);
	}
	
	private int getIndexRpt(Long ido2, String type2) {
		long ido1;
		String type1;
		for ( int i=0; i<this.list.size(); i++ ) {
			ido1 = this.list.get(i).getId_order();
			type1 = this.list.get(i).getTypetrx();
			// Se compara order + product + typetrx
			if ( ido1 == ido2 && type1.equals(type2) )
				return i;
		}
		return -1;
	}
	
	private int getIndexPdt(ReportExtDTO p) {
		long id1;
		long id2 = p.getId_product();
		for ( int i=0; i<this.products.size(); i++ ) {
			id1 = this.products.get(i).getId_product();
			if ( id1 == id2 )
				return i;
		}
		return -1;
	}
	
}
