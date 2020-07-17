package com.retailsbs.logistikapp.web.ctrl.communication;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.retailsbs.logistikapp.user.domain.Calendar;
import com.retailsbs.logistikapp.user.dto.AddNotificationDTO;
import com.retailsbs.logistikapp.user.dto.UpdCalendarDTO;
import com.retailsbs.logistikapp.user.exception.AccessNotFoundException;
import com.retailsbs.logistikapp.user.exception.CalendarNotFoundException;
import com.retailsbs.logistikapp.user.exception.UserNotFoundException;
import com.retailsbs.logistikapp.user.service.UserService;

/**
 * Controller para la vista de notificaciones
 * @author Juan Carlos
 * @since 17-07-2015
 */
public class CalendarAlertaCtrl {

	protected final Log logger = LogFactory.getLog(getClass());

	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * Default Constructor
	 */
	public CalendarAlertaCtrl() {
		
	}
	
	public void alerta() throws UserNotFoundException, AccessNotFoundException, CalendarNotFoundException {
		logger.info("inicio CalendarAlert");
		
		List<Calendar> list = userService.getCalendarNoSend();
		logger.debug("list = "+list.size());
		
		Date start_event = new Date();
		Date date_today = new Date();
		Long dif_dates = 0L; // nos da la diferencia de tiempo en minutos
		SimpleDateFormat sd_date = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		String[] sd_fecha = sd_date.format(date_today).toString().split(" ");
		logger.info("fecha = "+sd_fecha[0]+"hora="+sd_fecha[1]);
		for(int i=0; i<list.size(); i++){
			start_event = list.get(i).getCal_start();
			dif_dates = ((start_event.getTime() - date_today.getTime())/1000)/60;
			if(list.get(i).getCal_alert()!=null){
				if(dif_dates <= list.get(i).getCal_alert()){
					logger.info("Agrega alerta id = "+list.get(i).getId_calendar());
					sd_fecha = sd_date.format(list.get(i).getCal_start()).toString().split(" ");
					
					AddNotificationDTO dto_not = new AddNotificationDTO();
					dto_not.setCreated(new Date());
					dto_not.setIcon("fa fa-calendar");
					dto_not.setId_supplier(list.get(i).getId_supplier());
					if(list.get(i).getCal_level().equals("P")){
						dto_not.setId_user(list.get(i).getId_user());
						dto_not.setLevel("P");
					}
					else{
						dto_not.setId_user(2L);
						dto_not.setProfile("CAL");
					}
					dto_not.setMessage("<b>Recordatorio:</b> <span class='label label-warning'>"+list.get(i).getCal_title()+"</span> el "+sd_fecha[0]+" a las "+sd_fecha[1]);
					dto_not.setLink("home.htm");
					
					Long id_notification = userService.addNotification(dto_not);
					logger.info("id notificacion creado exitosamente = "+id_notification);
					
					//se marca como ya enviado a notificaciones el evento
					UpdCalendarDTO dto = new UpdCalendarDTO();
					dto.setId_calendar(list.get(i).getId_calendar());
					dto.setCal_send("S");
					
					int row = userService.updCalendar(dto);
					logger.info("Numero de registros modificados "+row);
					
				} // if(dif_dates <= list.get(i).getCal_alert())
			} // if(list.get(i).getCal_alert()!=null)
		} // for(int i=0; i<list.size(); i++)
		
		logger.info("fin CalendarAlert");
	}

}
