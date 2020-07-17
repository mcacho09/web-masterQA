package com.retailsbs.logistikapp.web.ctrl.communication;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
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

import com.retailsbs.logistikapp.user.domain.Access;
import com.retailsbs.logistikapp.user.domain.Calendar;
import com.retailsbs.logistikapp.user.domain.Quantities;
import com.retailsbs.logistikapp.user.domain.UserAcegi;
import com.retailsbs.logistikapp.user.dto.AddCalendarDTO;
import com.retailsbs.logistikapp.user.dto.AddNotificationDTO;
import com.retailsbs.logistikapp.user.dto.CalendarDTO;
import com.retailsbs.logistikapp.user.dto.EventSearchCriteria;
import com.retailsbs.logistikapp.user.dto.NotificationTodaySearchCriteria;
import com.retailsbs.logistikapp.user.dto.UpdCalendarDTO;
import com.retailsbs.logistikapp.user.notification.service.UserNotificationService;
import com.retailsbs.logistikapp.user.service.UserService;

/**
 * Class controller para la vista calendar 
 * @author Sergio Valenzuela
 * @since 10-07-2015
 */
public class CalendarViewCtrl implements Controller {
	
	protected final Log logger = LogFactory.getLog(getClass());

	private UserService userService;
	private UserNotificationService userNotificationService;
	private String view;
	private String LABEL_PERSONAL;
	private String LABEL_GLOBAL;
	private String DRIVER_PROFILE;
	
	public void setDRIVER_PROFILE(String dRIVER_PROFILE) {
		DRIVER_PROFILE = dRIVER_PROFILE;
	}
	public void setLABEL_PERSONAL(String lABEL_PERSONAL) {
		LABEL_PERSONAL = lABEL_PERSONAL;
	}
	public void setLABEL_GLOBAL(String lABEL_GLOBAL) {
		LABEL_GLOBAL = lABEL_GLOBAL;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public void setView(String view) {
		this.view = view;
	}
	public void setUserNotificationService(
			UserNotificationService userNotificationService) {
		this.userNotificationService = userNotificationService;
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		
		logger.debug("---- HANDLE REQUEST ----");
		/*-------------------------------------------------------*/
		logger.debug("----- USER CONTEXT -----");
		// Se obtiene el contexto del usuario
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserAcegi useracegi = (UserAcegi) auth.getPrincipal();
		Long id_user = useracegi.getId_user();
		/*-------------------------------------------------------*/
		// Se obtienen los datos de acceso para el usuario
		List<Access> accesslist = userService.getAccessByIdUser(useracegi.getId_user());
		// Se obtiene el primer acceso por defecto
		Access useraccess = accesslist.get(0);
		logger.debug("----- NOTIFICATION -----");
		//TODO Obtener notificaciones para solo para empresa = supplier
        NotificationTodaySearchCriteria dtoqty = new NotificationTodaySearchCriteria();
        dtoqty.setProfile(useracegi.getProfile());
        dtoqty.setFecha(new Date());
        dtoqty.setId_supplier(useraccess.getId_supplier());
        dtoqty.setId_user(useracegi.getId_user());
        
        Quantities nttdto = userService.getQtyAlertAndMessagesByIdUser(dtoqty);
        //HeaderNotificationDTO nttdto = userService.getHeaderNotificationByIdUser(useracegi.getId_user());

		logger.debug("alert="+nttdto.getAlert_qty()+" | message="+nttdto.getMessage_qty()+" | todo="+nttdto.getTodo_qty());
		/*-------------------------------------------------------*/
		
		Long id_sup = userService.getAccessByIdUser(useracegi.getId_user()).get(0).getId_supplier();
		Long id_usr = useracegi.getId_user();
		Map<String,Object> model = new HashMap<String,Object>();
		//se obtiene lista de store_category
		
		EventSearchCriteria dto = new EventSearchCriteria();
		dto.setId_supplier(id_sup);
		dto.setId_user(id_usr);
		
		String accion = ServletRequestUtils.getStringParameter(arg0, "action");
		if ( accion!=null)
		{
			
			logger.info("Accion ="+ accion);
			
			String title = ServletRequestUtils.getStringParameter(arg0, "title");
			logger.info("title ="+ title);
			
			String start = ServletRequestUtils.getStringParameter(arg0, "start");
			logger.info("start ="+ start);
			
			String end = ServletRequestUtils.getStringParameter(arg0, "end");
			logger.info("end ="+ end);
			
			String level = ServletRequestUtils.getStringParameter(arg0, "levelChar");
			logger.info("level ="+ level);
			/*
			String description = ServletRequestUtils.getStringParameter(arg0, "description");
			logger.info("alert ="+ description);
			*/
			Integer alert = ServletRequestUtils.getIntParameter(arg0, "alert");
			logger.info("alert ="+ alert);
			
			if ( arg0.getParameter("action").equals("agregar") ){
				if(title==null || title=="")
					title="Nuevo Evento";
				
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm");// 2015/07/16 12:40
				//Date d1 = df.parse(testDateString);
				
				AddCalendarDTO dtoAdd = new AddCalendarDTO();
				dtoAdd.setCreated(new Date());
				dtoAdd.setId_user(id_usr);
				dtoAdd.setId_supplier(id_sup);
				dtoAdd.setCal_title(title);
				dtoAdd.setCal_start(formatter.parse(start));
				dtoAdd.setCal_end(formatter.parse(end));
				dtoAdd.setCal_level(level);
				dtoAdd.setCal_alert(alert);
				dtoAdd.setCal_send("N");

				Long id = userService.addCalendar(dtoAdd);
				logger.debug("Evento, id=" + id + " creado... OK");
				

				// Se genera una notificacion
				AddNotificationDTO dtn = new AddNotificationDTO();
				dtn.setCreated(new Date());
				dtn.setIcon("glyphicon glyphicon-calendar");
				dtn.setId_user(useracegi.getId_user());
				dtn.setMessage("Evento <span class='label label-primary'>"+dtoAdd.getCal_title()+"</span> Creado");
				dtn.setPriority("1");
				dtn.setId_supplier(id_sup);
				dtn.setLevel(level);
				dtn.setLink("home.htm");
				// Se persiste la notificacion
				Long idn = null;
				if (level.equals("P")){
					List<Integer> usuarios = new LinkedList<Integer>();
					usuarios.add(useracegi.getId_user().intValue());
					idn = userNotificationService.createNotificationWithList(dtn, usuarios);
				}else{
					idn = userNotificationService.createNotification(dtn, "001");
				}
				logger.debug("Evento, id="+id + (level.equals("P")?" personal":"global"));
				logger.debug("Notification == " + idn);
				/*-------------------------------------------------------*/
				
			}
			if ( arg0.getParameter("action").equals("modificar") )
			{
				Long evId = ServletRequestUtils.getLongParameter(arg0, "evId");
				logger.info("---------------------------------------------------------------------------------------------------evId ="+ evId);
				
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm");// 2015/07/16 12:40
				//Date d1 = df.parse(testDateString);
				
				Calendar event = userService.getCalendarById(evId);				
				
				UpdCalendarDTO dtoUpd = new UpdCalendarDTO();
				dtoUpd.setId_calendar(evId);
				dtoUpd.setId_user(id_usr);
				dtoUpd.setId_supplier(id_sup);
				dtoUpd.setCal_title(title);
				dtoUpd.setCal_start(formatter.parse(start));
				dtoUpd.setCal_end(formatter.parse(end));
				dtoUpd.setCal_level(level);
				dtoUpd.setCal_alert(alert);
				dtoUpd.setModified(new Date());
				dtoUpd.setLogin(useracegi.getUserlogin());
				int rows = userService.updCalendar(dtoUpd);
				logger.debug("Evento id=" + dtoUpd.getId_calendar() + " actualizado " + rows + "...ok!");
				

				// Se genera una notificaci�n
				AddNotificationDTO dtn = new AddNotificationDTO();
				dtn.setCreated(new Date());
				dtn.setIcon("glyphicon glyphicon-calendar");
				dtn.setId_user(useracegi.getId_user());
				dtn.setMessage("Evento <span class='label label-primary'>"+event.getCal_title()+"</span> Modificado");
				dtn.setPriority("1");
				dtn.setId_supplier(id_sup);
				dtn.setLevel(level);
				dtn.setLink("home.htm");
				// Se persiste la notificacion
				Long idn = null;
				if (level.equals("P")){
					List<Integer> usuarios = new LinkedList<Integer>();
					usuarios.add(useracegi.getId_user().intValue());
					idn = userNotificationService.createNotificationWithList(dtn, usuarios);
				}else{
					idn = userNotificationService.createNotification(dtn, "001");
				}
				logger.debug("Notification == " + idn);
				/*-------------------------------------------------------*/
				
			}
			if ( arg0.getParameter("action").equals("eliminar") )
			{
				Long evId = ServletRequestUtils.getLongParameter(arg0, "evId");
				logger.info("---------------------------------------------------------------------------------------------------evId ="+ evId);
				
				Calendar event = userService.getCalendarById(evId);
				
				
				int rows = userService.delCalendarById(evId);
				logger.debug("Evento id=" + evId + " eliminado " + rows + "...ok!");
				

				// Se genera una notificaci�n
				AddNotificationDTO dtn = new AddNotificationDTO();
				dtn.setCreated(new Date());
				dtn.setIcon("glyphicon glyphicon-calendar");
				dtn.setId_user(useracegi.getId_user());
				dtn.setMessage("Evento <span class='label label-primary'>"+event.getCal_title()+"</span> Eliminado");
				dtn.setPriority("1");
				dtn.setId_supplier(id_sup);
				dtn.setLevel(level);
				dtn.setLink("home.htm");
				// Se persiste la notificacion
				Long idn = null;
				if (!level.equals("P")){
					idn = userNotificationService.createNotification(dtn, "001");
				}else{
					List<Integer> usuarios = new LinkedList<Integer>();
					usuarios.add(useracegi.getId_user().intValue());
					idn = userNotificationService.createNotificationWithList(dtn, usuarios);
				}
				logger.debug("Notification == " + idn);
				/*-------------------------------------------------------*/
			}
		} // if ( accion!=null)
		
		String perfil = useracegi.getProfile();		
		Long id_supplier = useraccess.getId_supplier();
		
		List<CalendarDTO> events = userService.getEventByCriteria(dto);
		logger.info("eventos ="+events.size());
		
		model.put("useracegi", useracegi);
		model.put("useraccess", useraccess);
		model.put("nttdto", nttdto);
		model.put("events", events);
		model.put("id_sup", id_sup);
		model.put("id_usr", id_usr);
		model.put("LABEL_PERSONAL", LABEL_PERSONAL);
		model.put("LABEL_GLOBAL", LABEL_GLOBAL);
		model.put("DRIVER_PROFILE", DRIVER_PROFILE);
		
		model.put("id_supplier", id_supplier);
		model.put("perfil", perfil);
		model.put("id_user", id_user);
		
		
		return new ModelAndView(view, model);
	}

}
