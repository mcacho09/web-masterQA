package com.retailsbs.logistikapp.web.ctrl.customer;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.retailsbs.logistikapp.logistic.dto.InfoReportPromotion;
import com.retailsbs.logistikapp.logistic.dto.ParameterReportPromotion;
import com.retailsbs.logistikapp.logistic.service.LogisticService;

public class RepPromotionVisited implements Controller {

	protected final Log logger = LogFactory.getLog(getClass());
	
	private String view;
	private LogisticService logisticService;
	public void setView(String view) {
		this.view = view;
	}
	public void setLogisticService(LogisticService logisticService) {
		this.logisticService = logisticService;
	}
	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		// TODO Auto-generated method stub
		logger.debug("Creando reporte de reppromotionvisited");
		
		String fileName = "reporte-de-clientes-visitados-promocion-" + System.currentTimeMillis() + ".csv";
		String contentDisposition = "attachment" +";filename=\"" + fileName + "\"";
		
		arg1.setHeader("Content-disposition", contentDisposition);
		arg1.setContentType("application/csv");
		
		Long id_retail = ServletRequestUtils.getLongParameter(arg0, "idr",0);
		String fini = ServletRequestUtils.getRequiredStringParameter(arg0, "fini");
		String ffin = ServletRequestUtils.getRequiredStringParameter(arg0, "ffin");
		
		ParameterReportPromotion data = new ParameterReportPromotion();
		data.setId_retail(id_retail);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		data.setFini(sdf.parse(fini));
		data.setFfin(sdf.parse(ffin));
		
		List<InfoReportPromotion> list = logisticService.getTravelWithPromotion(data);
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("fini", fini);
		model.put("ffin", ffin);
		model.put("list", list);
		
		return new ModelAndView(view, model);
	}
	
}
