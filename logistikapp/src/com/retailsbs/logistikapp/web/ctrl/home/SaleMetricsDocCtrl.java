package com.retailsbs.logistikapp.web.ctrl.home;

import java.text.SimpleDateFormat;
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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.retailsbs.logistikapp.financial.dto.GetMetricsSaleDTO;
import com.retailsbs.logistikapp.financial.dto.MetricsSaleDTO;
import com.retailsbs.logistikapp.financial.dto.MetricsSaleReportDTO;
import com.retailsbs.logistikapp.financial.service.FinancialService;
import com.retailsbs.logistikapp.user.domain.Access;
import com.retailsbs.logistikapp.user.domain.UserAcegi;
import com.retailsbs.logistikapp.user.service.UserService;

public class SaleMetricsDocCtrl implements Controller {

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
		logger.debug("REPORT/METRICAS VENTA ==> USER CONTEXT");
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		UserAcegi useracegi = (UserAcegi) auth.getPrincipal();
		String profile = useracegi.getProfile();
		logger.debug("REPORT/METRICAS VENTA ==> profile=" + profile);
		List<Access> useraccess = userService.getAccessByIdUser(useracegi.getId_user());
		Access access = useraccess.get(0);
		Long id_supplier = access.getId_supplier();
		logger.debug("REPORT/METRICAS VENTA ==> id_supplier=" + id_supplier);

		/*-------------------------------------------------------*/
		// Se obtiene rango de fechas
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		SimpleDateFormat sdf3 = new SimpleDateFormat("yyyyMMdd-HHmmss");

		// Se controlan parametros fini y ffin
		Date hoy = new Date();
		Date fini = null;
		Date ffin = null;
		try {
			if (arg0.getParameter("fini") != null
					&& !arg0.getParameter("fini").equals("")
					&& arg0.getParameter("ffin") != null
					&& !arg0.getParameter("ffin").equals("")) {
				fini = sdf1.parse(arg0.getParameter("fini"));
				ffin = sdf1.parse(arg0.getParameter("ffin"));
			} else {
				// Rango por defecto para usuarios con perfi chofer
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DATE, -3);
				Date after = cal.getTime();
				fini = sdf2.parse(sdf1.format(after));
				ffin = sdf2.parse(sdf1.format(hoy));
			}
		} catch (Exception e) {
			logger.error("REPORT/METRICAS VENTA ==> Error en formato de fechas, se dejan valores por defecto");
			logger.error("REPORT/METRICAS VENTA ==> fini="
					+ arg0.getParameter("fini"));
			logger.error("REPORT/METRICAS VENTA ==> ffin="
					+ arg0.getParameter("ffin"));
			// Por defecto el rango de fechas es el dia actual
			fini = sdf2.parse(sdf1.format(hoy));
			ffin = sdf2.parse(sdf1.format(hoy));
		}
		logger.debug("REPORT/TRX ==> fini=" + sdf1.format(fini));
		logger.debug("REPORT/TRX ==> ffin=" + sdf1.format(ffin));

		String fileName = "reporte-metricas-venta-" + sdf3.format(new Date()) + ".csv";

		// Se inicializa objeto para criterio de busqueda

		GetMetricsSaleDTO msdto = new GetMetricsSaleDTO();
		msdto.setFinalDate(ffin);
		msdto.setId_supplier(id_supplier);
		msdto.setInitDate(fini);

		MetricsSaleDTO metrics = financialService.getMetricsSale(msdto);

		List<MetricsSaleReportDTO> list = financialService
				.getMetricsSaleReport(msdto);

		// Se crea el libro
		logger.debug("REPORT/METRICAS VENTA ==> Creando libro");

		String contentDisposition = "attachment" + ";filename=\"" + fileName
				+ "\"";
		arg1.setHeader("Content-disposition", contentDisposition);
		arg1.setContentType("application/csv");

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("dates", sdf1.format(fini) + " a " + sdf1.format(ffin));
		model.put("metrics", metrics);
		model.put("list", list);

		return new ModelAndView(view, model);
	}
}
