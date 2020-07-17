package com.retailsbs.logistikapp.web.ctrl.logistic;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

public class TravelRepeatCtrl implements Controller {
	
	protected final Log logger = LogFactory.getLog(getClass());

	private LogisticService logisticService;
	public void setLogisticService(LogisticService logisticService) {
		this.logisticService = logisticService;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		// TODO Auto-generated method stub
		Long id_travel = ServletRequestUtils.getLongParameter(arg0, "idt", 0);
		Long noRep = ServletRequestUtils.getLongParameter(arg0, "rep", 0);
		int type = ServletRequestUtils.getIntParameter(arg0, "type", 0);
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");// 2015/07/16 12:40
		Date schedule = null;
		for (long i = 0; i < noRep; i++){
			Travel travel = logisticService.getTravelById(id_travel);
			Travel temp = travel;
			temp.setId_travel(null);
			if (travel.getSchedule()!= null && !travel.getSchedule().equals("") && i == 0){
				schedule = travel.getSchedule() ;
			}
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(schedule);
			switch(type){
				case 1:{
					calendar.add(Calendar.DAY_OF_YEAR, 1);
				}break;
				case 2:{
					calendar.add(Calendar.WEEK_OF_YEAR, 1);
				}break;
				case 3:{
					calendar.add(Calendar.MONTH, 1);
				}break;
				case 4:{
					calendar.add(Calendar.WEEK_OF_YEAR, 2);
				}break;
			}
			schedule = calendar.getTime();
			temp.setSchedule(schedule);
			Long new_id_travel = logisticService.addTravel(temp);
			List<WayBillDTO> waybills = logisticService.getWayBillByIdTravel(id_travel);
			WayBillDTO waybill = null;
			for (WayBillDTO w : waybills){
				waybill = w;
				waybill.setId_waybill(null);
				waybill.setCheckin(null);
				waybill.setStatus("N");
				waybill.setId_travel(new_id_travel);
				waybill.setComment("");
				waybill.setName(null);
				waybill.setOutrange("N");
				logisticService.addWayBill(waybill);
			}
		}
		
		return new ModelAndView("redirect:travellist.htm");
	}

}
