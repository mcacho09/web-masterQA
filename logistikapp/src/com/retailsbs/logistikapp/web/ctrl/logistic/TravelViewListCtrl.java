package com.retailsbs.logistikapp.web.ctrl.logistic;

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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.retailsbs.logistikapp.logistic.domain.Route;
import com.retailsbs.logistikapp.logistic.dto.TravelDTO;
import com.retailsbs.logistikapp.logistic.dto.TravelSearchCriteria;
import com.retailsbs.logistikapp.logistic.service.LogisticService;
import com.retailsbs.logistikapp.user.domain.Access;
import com.retailsbs.logistikapp.user.domain.Quantities;
import com.retailsbs.logistikapp.user.domain.UserAcegi;
import com.retailsbs.logistikapp.user.dto.NotificationTodaySearchCriteria;
import com.retailsbs.logistikapp.user.service.UserService;

/**
 * Controlador de vista para el monitor de viajes
 * @author Jorge Silva
 * @since 01/03/2016 
 * @modified 10/05/2016 - Jorge - Se agrega configuración adicional al usuario con perfil chofer
 */
public class TravelViewListCtrl implements Controller {
	
	protected final Log logger = LogFactory.getLog(getClass());

	private String view;
	private UserService userService;
	private LogisticService logisticService;
	private String TRAVEL_PROGRAM;
	private String TRAVEL_REVIEWED;
	private String TRAVEL_STARTED;
	private String TRAVEL_CREATED;
	
	public void setView(String view) {
		this.view = view;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public void setLogisticService(LogisticService logisticService) {
		this.logisticService = logisticService;
	}
	public void setTRAVEL_PROGRAM(String tRAVEL_PROGRAM) {
		TRAVEL_PROGRAM = tRAVEL_PROGRAM;
	}
	public void setTRAVEL_REVIEWED(String tRAVEL_REVIEWED) {
		TRAVEL_REVIEWED = tRAVEL_REVIEWED;
	}
	public void setTRAVEL_STARTED(String tRAVEL_STARTED) {
		TRAVEL_STARTED = tRAVEL_STARTED;
	}
	public void setTRAVEL_CREATED(String tRAVEL_CREATED) {
		TRAVEL_CREATED = tRAVEL_CREATED;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		logger.debug("---- HANDLE/REQUEST ----");
		/*-------------------------------------------------------*/
		logger.debug("----- USER CONTEXT -----");
		// Se obtiene el contexto del usuario
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserAcegi useracegi = (UserAcegi) auth.getPrincipal();
		String profile = useracegi.getProfile();
		logger.debug("TRAVEL ==> profile="+profile);
		/*-------------------------------------------------------*/

		////////////////////////////////////////////////////////////////////////
		//Se obtienen los datos de acceso para el usuario
		List<Access> accesslist = userService.getAccessByIdUser(useracegi.getId_user());
		//Se obtiene el primer acceso por defecto
		Access useraccess = accesslist.get(0);
		Long id_supplier = useraccess.getId_supplier();
		logger.debug("TRAVEL ==> id_supplier="+id_supplier);
		////////////////////////////////////////////////////////////////////////
		/*-------------------------------------------------------*/
		// Se obtiene la lista de categorias de productos por proveedor
		/*-------------------------------------------------------*/
		NotificationTodaySearchCriteria dtoqty = new NotificationTodaySearchCriteria();
		dtoqty.setProfile(useracegi.getProfile());
		dtoqty.setFecha(new Date());
		dtoqty.setId_supplier(useraccess.getId_supplier());
		dtoqty.setId_user(useracegi.getId_user());
		logger.debug("----- HEADER/NOTIFICATION -----");
		Quantities nttdto = userService.getQtyAlertAndMessagesByIdUser(dtoqty);
		//HeaderNotificationDTO nttdto = userService.getHeaderNotificationByIdUser(useracegi.getId_user());
		logger.debug("HEADER ==> alert=" + nttdto.getAlert_qty() + " | message=" + nttdto.getMessage_qty() + " | todo=" + nttdto.getTodo_qty());
		/*-------------------------------------------------------*/
		
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy"); 
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
		// Definición de estados de viajes
		// CRE, PRO, REV, TRA
		// CRE = Creado
		// PRO = Programado
		// REV = Revisado/Asignado
		// TRA = En Transito
		List<String> status = new ArrayList<String>();
		// Se controla el parametro status
		if ( arg0.getParameter("status") != null && !arg0.getParameter("status").equals("") ) {
			status.add( arg0.getParameter("status") );
		} else {
			// Si no se ha seleccionado ningun estado
			// se define una lista con todos los estados
			// de viajes que serán permitidos en la vista
			if( !useracegi.getProfile().contains("DRI") )
				status.add(TRAVEL_CREATED);  // CRE
			
			status.add(TRAVEL_PROGRAM); // PRO
			status.add(TRAVEL_REVIEWED); // REV
			status.add(TRAVEL_STARTED);  // TRA
		}
		logger.debug("TRAVEL ==> status=" + status.size());
		
		// Se controlan parametros startDateSchedule y endDateSchedule
		Date hoy = new Date();
		Date fini = null;		
		Date ffin = null;
		try {
			if ( arg0.getParameter("fini") != null && !arg0.getParameter("fini").equals("") &&
					arg0.getParameter("ffin") != null && !arg0.getParameter("ffin").equals("") ) {
				fini = sdf2.parse( arg0.getParameter("fini") + " 00:00:00");
				ffin = sdf2.parse( arg0.getParameter("ffin") + " 23:59:59");
			} else {
				if ( !profile.contains("DRI") ) {
					// Por defecto el rango de fechas es el dia actual
					// para todos los usuarios que no sean chofer
					fini = sdf2.parse( sdf1.format(hoy)+" 00:00:00");		
					ffin = sdf2.parse( sdf1.format(hoy)+" 23:59:59");
				} else {
					// Rango por defecto para usuarios con perfi chofer
					Calendar cal = Calendar.getInstance();
					cal.add(Calendar.DATE, -3);
					Date after = cal.getTime();
					fini = sdf2.parse( sdf1.format(after)+" 00:00:00");
					ffin = sdf2.parse( sdf1.format(hoy)+" 23:59:59");
				}
			}
		} catch( Exception e) {
			logger.error("TRAVEL ==> Error en formato de fechas, se dejarán valores por defecto");
			logger.error("TRAVEL ==> fini="+arg0.getParameter("fini"));
			logger.error("TRAVEL ==> ffin="+arg0.getParameter("ffin"));
			// Por defecto el rango de fechas es el dia actual
			fini = sdf2.parse( sdf1.format(hoy)+" 00:00:00");		
			ffin = sdf2.parse( sdf1.format(hoy)+" 23:59:59");
		}
		logger.debug("TRAVEL ==> fini="+sdf1.format(fini));
		logger.debug("TRAVEL ==> ffin="+sdf1.format(ffin));
		
		// Se define criterio de busqueda para obtener el listado de viajes para el proveedor
		TravelSearchCriteria dto = new TravelSearchCriteria();
		dto.setId_supplier(id_supplier);
		dto.setStatus(status);			
		dto.setFini(fini);
		dto.setFfin(ffin);
		// Se controla el perfil de tipo DRI para mostrar sólo los viajes asociados
		if ( useracegi.getProfile().contains("DRI") )
			dto.setId_user(useracegi.getId_user()); 
		
		// Se obtiene una lista de viajes para el proveedor (supplier)
		List<TravelDTO> travels = logisticService.getTravelByCriteria(dto );
		
		logger.debug("TRAVEL ==> travels="+travels.size());
		
		// Se obtiene una lista de rutas para el proveedor (supplier)
		List<Route> routes = logisticService.getAllRouteByIdSupplier(id_supplier);
		logger.debug("TRAVEL ==> routes="+routes.size());

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.MONTH, 6);
		Date last_time = calendar.getTime();
		Date now = new Date();
		Long time = last_time.getTime() - now.getTime();
		Long days = time/1000/60/60/24;
		
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("useracegi", useracegi);
		model.put("useraccess",useraccess);
		model.put("profile", profile);
		model.put("nttdto", nttdto);
		
		model.put("travels", travels);
		model.put("routes", routes);
		model.put("fini", sdf1.format(fini));
		model.put("ffin", sdf1.format(ffin));
		model.put("maxdays", days);

		return new ModelAndView(view, model);
	}

}
