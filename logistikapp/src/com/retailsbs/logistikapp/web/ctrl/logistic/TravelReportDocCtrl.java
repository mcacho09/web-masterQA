package com.retailsbs.logistikapp.web.ctrl.logistic;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import com.retailsbs.logistikapp.logistic.domain.Travel;
import com.retailsbs.logistikapp.logistic.dto.WayBillDTO;
import com.retailsbs.logistikapp.logistic.service.LogisticService;
import com.retailsbs.logistikapp.user.domain.User;
import com.retailsbs.logistikapp.user.service.UserService;

public class TravelReportDocCtrl implements Controller {

	protected final Log logger = LogFactory.getLog(getClass());

	private UserService userService;
	private LogisticService logisticService;
	private String view;
	

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setView(String view) {
		this.view = view;
	}
	public void setLogisticService(LogisticService logisticService) {
		this.logisticService = logisticService;
	}

	

	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		logger.debug("---- HANDLE/REQUEST ----");
		/*-------------------------------------------------------*/
		logger.debug("----- USER CONTEXT -----");
		String fileName = "";
		Travel travel;
		List<WayBillDTO> waybills;
		User chofer;
		ReportDownloadDTO info;
		Long id_travel = null;
		List<ReportDownloadDTO> resultados = new ArrayList<ReportDownloadDTO>();
		boolean control = false;
		
		
		/*-------------------------------------------------------*/
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat sdestados = new SimpleDateFormat("dd/MM HH:mm");
		
		//id_travel = ServletRequestUtils.getRequiredLongParameter(arg0, "idt");
		
		//obtención de valor o valores "id_travel"
		String[] ids = arg0.getParameterValues("idt");
		
		long clients = 0;
		long visited = 0;
		double percent = 0;
	
		if(!ids[0].contains(",")){
			id_travel = Long.parseLong(ids[0]);
			
			travel = logisticService.getTravelById(id_travel);
			waybills = logisticService.getWayBillByIdTravel(id_travel);
			chofer = userService.getUserById(travel.getId_user());
		
			info = new ReportDownloadDTO(travel,waybills,chofer);
			info.setIniciado(info.getTravel().getStarted());
			info.setFinalizado(info.getTravel().getFinished());
						
			int visitados = 0;
			for (WayBillDTO i : waybills){
				if (i.getStatus().equals("S")) visitados++;
			}

			int porVisitados = (visitados == 0) ? visitados : ((visitados * 100) / waybills.size());

			info.setVisitados(visitados);
			info.setPorcentaje(porVisitados+" %");
			resultados.add(info);
			
			fileName = "report-de-viaje-" + info.getTravel().getName() + "-" + System.currentTimeMillis() + ".csv";
			control = false;
			
		}else{
			
			String[] todo = ids[0].split(",");
			Long[] id_travels = new Long[todo.length];
			
			for(int i=0; i < todo.length; i++ )
				id_travels[i] = Long.parseLong(todo[i]);
			
			fileName = "reportes-de-viajes-" + System.currentTimeMillis() + ".csv";
			
			for(int ai=0; ai< id_travels.length; ai++){
				travel = logisticService.getTravelById(id_travels[ai]);
				waybills = logisticService.getWayBillByIdTravel(id_travels[ai]);
				chofer = userService.getUserById(travel.getId_user());


				info = new ReportDownloadDTO(travel,waybills,chofer);
				info.setIniciado(info.getTravel().getStarted());
				info.setFinalizado(info.getTravel().getFinished());
				int visitados = 0;
				for (WayBillDTO i : waybills){
					if (i.getStatus().equals("S")) visitados++;
				}

				int porVisitados = (visitados == 0) ? visitados : ((visitados * 100) / waybills.size());

				info.setVisitados(visitados);
				info.setPorcentaje(porVisitados+" %");
				resultados.add(info);
				
				visited += visitados;
				clients += waybills.size();
			}
			
			percent = (visited == 0? 0 : ((visited * 100) /  clients));
			control = true;
		}
		
		/***** CREANDO LIBRO DE EXCEL *****/
		
		//Se crea el libro
		logger.info("Creando libro");
		
		String contentDisposition = "attachment" +";filename=\"" + fileName + "\"";
		
		arg1.setHeader("Content-disposition", contentDisposition);
		arg1.setContentType("application/csv");
		
		String profile = ServletRequestUtils.getRequiredStringParameter(arg0, "dol");
		
		logger.debug("PERFIL USERACEGI ==> "+profile);
		String nexttravel = "*****,*****,*****,*****,*****,*****,*****,*****,*****,*****,*****";
		Map<String, Object> model = new HashMap<String, Object>();
        model.put("resultados", resultados);
        model.put("profile",profile);
        model.put("generado", sdf.format(new Date()));
        model.put("salto", " ");  
        model.put("nexttravel",nexttravel);
        model.put("control", control);
        model.put("clients", clients);
        model.put("percent", percent);
        model.put("visited", visited);
						
       
		return new ModelAndView(view, model);
	}

}
