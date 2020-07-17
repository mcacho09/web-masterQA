package com.retailsbs.logistikapp.web.ctrl.logistic;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.retailsbs.logistikapp.logistic.domain.Travel;
import com.retailsbs.logistikapp.logistic.dto.WayBillDTO;
import com.retailsbs.logistikapp.logistic.service.LogisticService;

/**
 * Controller para generar archivo csv de la vista de usuario
 * @author Juan Carlos Ramos Campos
 * @since 31-10-2013
 */
public class WayBillCsvCtrl implements Controller {

	private LogisticService logisticService;
	private String view;
	
	public void setLogisticService(LogisticService logisticService) {
		this.logisticService = logisticService;
	}
	public void setView(String view) {
		this.view = view;
	}
	

	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
				
		// Se define criterio de busqueda para obtener viaje
		Long id_travel = ServletRequestUtils.getLongParameter(arg0, "idt");
		Travel travel = null;
		List<WayBillDTO>list = null;
		
		if (id_travel != null && !id_travel.equals("")){
			travel = logisticService.getTravelById(id_travel);
			list = logisticService.getWayBillByIdTravel(travel.getId_travel());
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String contentDisposition = "attachment" + ";fileName=\"" + "hoja_de_ruta-"+travel.getName() + "-" + sdf.format(travel.getSchedule())+ ".csv"+ "\"";
		arg1.setContentType("application/csv");
        arg1.setHeader("Content-Disposition",contentDisposition);
		
		Map<String,Object> model = new HashMap<String,Object>();		
		model.put("list", list);
		model.put("travel", travel);

		return new ModelAndView(view, model);
	}

}
