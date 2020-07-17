package com.retailsbs.logistikapp.web.ctrl.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.retailsbs.logistikapp.logistic.dto.AvailableUserScheduleSearchCriteria;
import com.retailsbs.logistikapp.logistic.dto.UpdTravelDTO;
import com.retailsbs.logistikapp.logistic.exception.UserScheduleNotAvailableException;
import com.retailsbs.logistikapp.logistic.service.LogisticService;


public class TravelValidatorUpdCtrl implements Validator {

	protected final Log logger = LogFactory.getLog(getClass());

	private LogisticService logisticService;
	private String TRAVEL_PROGRAM;    
	
	public void setLogisticService(LogisticService logisticService) {
		this.logisticService = logisticService;
	}
	public void setTRAVEL_PROGRAM(String tRAVEL_PROGRAM) {
		TRAVEL_PROGRAM = tRAVEL_PROGRAM;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean supports(Class travel) {
		return travel.equals(UpdTravelDTO.class);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		UpdTravelDTO dto = (UpdTravelDTO) arg0;
		
		if (dto == null) {
			arg1.reject("error.nullpointer", "Null data received");
		} else {			
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");// 2015/07/16 12:40
			String name = dto.getName();
			Long id_user = dto.getId_user();
			//Long id_route = dto.getId_route();
			String schedule = dto.getSchedule().toString();
			String status = dto.getStatus();
			Date date_schedule = new Date();
			
			if (schedule!=null && !schedule.equals("")){
				try {
					date_schedule = formatter.parse(schedule);
				} catch (ParseException e) {
					arg1.rejectValue("schedule", "error.code", "Formato de fecha inv&aacute;lido");
					e.printStackTrace();
				}
			}
			if (schedule!=null && !schedule.equals("") && id_user != null && !id_user.equals("")){
				try {
					AvailableUserScheduleSearchCriteria dto_usr = new AvailableUserScheduleSearchCriteria();
					dto_usr.setId_user(id_user);
					dto_usr.setSchedule(date_schedule);
					logisticService.getAvailableUserSchedule(dto_usr);
				} catch (UserScheduleNotAvailableException e) {
					arg1.rejectValue("id_user", "error.code", e.getMessage());
				}		
			}
				
			
			logger.debug("name=" + name);
			logger.debug("id_user=" + id_user);
			//logger.debug("id_route=" + id_route);
			logger.debug("fecha=" + schedule.toString());

			// Se controla que se ingrese un valor
			if (name == "") arg1.rejectValue("name", "error.code", "Debes ingresar el nombre");
			//if (id_route == null) arg1.rejectValue("id_route", "error.code", "Debes ingresar una ruta");
			if (status.equals(TRAVEL_PROGRAM)&&id_user == null) arg1.rejectValue("id_user", "error.code", "Debes ingresar un chofer");			
			if (status.equals(TRAVEL_PROGRAM)&&(schedule == null||schedule.equals("")))arg1.rejectValue("schedule", "error.code", "Debes ingresar una fecha");
			

		}

	}

	

	

}
