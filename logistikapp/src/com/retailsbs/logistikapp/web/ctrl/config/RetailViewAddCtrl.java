package com.retailsbs.logistikapp.web.ctrl.config;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContextHolder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.retailsbs.logistikapp.retail.domain.RetailCategory;
import com.retailsbs.logistikapp.retail.domain.State;
import com.retailsbs.logistikapp.retail.dto.AddRetailDTO;
import com.retailsbs.logistikapp.retail.dto.RetailCategoryActiveSearchCriteria;
import com.retailsbs.logistikapp.retail.service.RetailService;
import com.retailsbs.logistikapp.user.domain.Access;
import com.retailsbs.logistikapp.user.domain.Quantities;
import com.retailsbs.logistikapp.user.domain.UserAcegi;
import com.retailsbs.logistikapp.user.dto.AccessTinyDTO;
import com.retailsbs.logistikapp.user.dto.AddNotificationDTO;
import com.retailsbs.logistikapp.user.dto.NotificationTodaySearchCriteria;
import com.retailsbs.logistikapp.user.exception.AccessNotFoundException;
import com.retailsbs.logistikapp.user.notification.service.UserNotificationService;
import com.retailsbs.logistikapp.user.service.UserService;

/**
 * Class controller de la vista de agregar retailers (clientes)
 * @author Juan Carlos Ramos Campos
 * @since 25-11-2014
 * @modified 13-01-2015 - JORGE - estandarización estructura de controller
 * @modified 29-01-2015 - JORGE - Se obtiene el listado de retail category
 * @modified 16-02-2015 - JORGE - Integracion para contador y alertas en barra navegacion
 */
public class RetailViewAddCtrl extends SimpleFormController {

	protected final Log logger = LogFactory.getLog(getClass());

	private UserService userService;
	private RetailService retailService;
	private UserNotificationService userNotificationService;
	private String ACTIVE;
	private String NO_ACTIVE;
	private Integer ORDERBY;
	private Integer PAIS;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public void setRetailService(RetailService retailService) {
		this.retailService = retailService;
	}
	public void setUserNotificationService(
			UserNotificationService userNotificationService) {
		this.userNotificationService = userNotificationService;
	}
	public void setACTIVE(String aCTIVE) {
		ACTIVE = aCTIVE;
	}
	public void setNO_ACTIVE(String nO_ACTIVE) {
		NO_ACTIVE = nO_ACTIVE;
	}
	public void setPAIS(Integer pAIS) {
		PAIS = pAIS;
	}
	public void setORDERBY(Integer oRDERBY) {
		ORDERBY = oRDERBY;
	}

	public RetailViewAddCtrl() {
		setSessionForm(true);
		setBindOnNewForm(true);
	}

	protected Map<String, Object> referenceData(HttpServletRequest arg0, Object arg1, Errors arg2) throws Exception{
		logger.debug("---- REFERENCEDATA ----");
		/*-------------------------------------------------------*/
		logger.debug("----- USER CONTEXT -----");
		// Se obtiene el contexto del usuario
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserAcegi useracegi = (UserAcegi) auth.getPrincipal();
		/*-------------------------------------------------------*/
		logger.debug("----- USER ACCESS -----");
		List<AccessTinyDTO> accesssupplier = userService.getAccessSupplierByIdUser(useracegi.getId_user());
		logger.debug("accesssupplier="+accesssupplier.size());
		Long idsupplier = ( accesssupplier.get(0) ).getId();
		logger.debug("idsupplier="+idsupplier);
		/*-------------------------------------------------------*/
		// Se obtienen los datos de acceso para el usuario
		List<Access> accesslist = userService.getAccessByIdUser(useracegi.getId_user());
		// Se obtiene el primer acceso por defecto
		Access useraccess = accesslist.get(0);
		logger.debug("----- HEADER/NOTIFICATION -----");
        NotificationTodaySearchCriteria dtoqty = new NotificationTodaySearchCriteria();
        dtoqty.setProfile(useracegi.getProfile());
        dtoqty.setFecha(new Date());
        dtoqty.setId_supplier(useraccess.getId_supplier());
        dtoqty.setId_user(useracegi.getId_user());
        
        Quantities nttdto = userService.getQtyAlertAndMessagesByIdUser(dtoqty);
        //HeaderNotificationDTO nttdto = userService.getHeaderNotificationByIdUser(useracegi.getId_user());

		logger.debug("alert="+nttdto.getAlert_qty()+" | message="+nttdto.getMessage_qty()+" | todo="+nttdto.getTodo_qty());
		/*-------------------------------------------------------*/
		// Por defecto, se obtiene una lista de estados para el pais
		List<State> states = retailService.getAllStatesByIdCountry( (long)PAIS );
		logger.debug("states="+states.size());
		/*-------------------------------------------------------*/
		// Se obtiene un listado de todas las categorias de comercios
		RetailCategoryActiveSearchCriteria dto = new RetailCategoryActiveSearchCriteria();
		dto.setActive("S");
		dto.setId_supplier(idsupplier);
		dto.setId_retail_category(null);
		List<RetailCategory> categories = retailService.getRetailsCategoryActiveByCriteria(dto);
		logger.debug("-------------------------------------------------------------------------------------categories="+categories);
		/*-------------------------------------------------------*/
		// Se obtiene la accion
		String accion = ServletRequestUtils.getStringParameter(arg0, "accion", null);
		/*-------------------------------------------------------*/
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("useracegi", useracegi);
		model.put("useraccess", useraccess);
		model.put("accesssupplier", accesssupplier);
		model.put("nttdto", nttdto);
		model.put("idsupplier", idsupplier);
		
		model.put("accion", accion);
		model.put("categories", categories);
		model.put("states", states);
		
		model.put("ACTIVE", ACTIVE);
		
		return model;
	}

	protected Object formBackingObject(HttpServletRequest request) throws ServletException {
		logger.debug("---- FORMBACKINGOBJECT ----");
		AddRetailDTO command = new AddRetailDTO();	
	
		HttpSession sess = request.getSession();
		String SESSION = (String)sess.getAttribute("RTADD");
		//==========================================================
		
		
		String name = "";
		String code = "";
		Long id_state;
		Integer orderby;
		String active;
		Long id_retail_category;
		if(SESSION!=null && SESSION.equals("rt")){			
			if (sess.getAttribute("rt_name") != null && !((String)sess.getAttribute("rt_name")).equals("")){
				name = (String)sess.getAttribute("rt_name");
				command.setName(name);		
			}			
			if (sess.getAttribute("rt_code") != null && !((String)sess.getAttribute("rt_code")).equals("")){
				code = (String)sess.getAttribute("rt_code");
				command.setCode(code);		
			}
			if (sess.getAttribute("rt_id_state") != null && !((String)sess.getAttribute("rt_id_state")).equals("")){
				logger.info( "ID_STATE ---> "+ sess.getAttribute("rt_id_state"));
				
				id_state = Long.parseLong((String)sess.getAttribute("rt_id_state"));
				command.setId_state(id_state);		
			}						
			if (sess.getAttribute("rt_orderby") != null && !((String)sess.getAttribute("rt_orderby")).equals("")){
				orderby = Integer.parseInt((String)sess.getAttribute("rt_orderby"));
				command.setOrderby(orderby);		
			}
			if (sess.getAttribute("rt_active") != null && !((String)sess.getAttribute("rt_active")).equals("")){
				active = (String)sess.getAttribute("rt_active");
				command.setActive(active);				
			}		
			if (sess.getAttribute("rt_id_retail_category") != null && !((String)sess.getAttribute("rt_id_retail_category")).equals("")){
				id_retail_category = Long.parseLong((String)sess.getAttribute("rt_id_retail_category"));
				command.setId_retail_category(id_retail_category);;				
			}		
		}		
		if(SESSION!=null && SESSION.equals("rt")){
			sess.removeAttribute("STRIMP");
			sess.removeAttribute("rt_name");
			sess.removeAttribute("rt_code");
			sess.removeAttribute("rt_id_state");
			sess.removeAttribute("rt_orderby");
			sess.removeAttribute("rt_active");			
			sess.removeAttribute("rt_id_retail_category");
		}
		logger.info("name = "+name);		
		logger.info("code = "+code);	
		
		return command;
	}

	protected ModelAndView onSubmit(HttpServletRequest request,HttpServletResponse response, Object command, BindException errors) throws ServletException, AccessNotFoundException {
		logger.debug("---- ONSUBMIT ----");
		/*-------------------------------------------------------*/
		logger.debug("----- USER CONTEXT -----");
		// Se obtiene el contexto del usuario
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserAcegi useracegi = (UserAcegi) auth.getPrincipal();
		/*-------------------------------------------------------*/
		logger.debug("----- USER ACCESS -----");
		List<AccessTinyDTO> accesssupplier = userService.getAccessSupplierByIdUser(useracegi.getId_user());
		logger.debug("accesssupplier="+accesssupplier.size());
		Long idsupplier = ( accesssupplier.get(0) ).getId();
		logger.debug("idsupplier="+idsupplier);
		/*-------------------------------------------------------*/

		// Se controlan los parametros active y orderby
		String active = ServletRequestUtils.getStringParameter(request, "active", NO_ACTIVE);
		Integer orderby = ServletRequestUtils.getIntParameter(request, "orderby", ORDERBY);

		// Se setea dto para agregar un nuevo objeto de dominio Retail
		AddRetailDTO dto = (AddRetailDTO) command;
		dto.setCreated(new Date());
		dto.setActive(active);
		dto.setOrderby(orderby);
		dto.setId_country((long)PAIS);
		dto.setLogin( useracegi.getUserlogin() );
		// Se persiste el objeto
		Long id = retailService.addRetail(dto);
				
		logger.debug("Retail, id=" + id + " creado... OK");
		
		/*-------------------------------------------------------*/
		// Se setea de dto para agregar un objeto de dominio Notification
		AddNotificationDTO dtn = new AddNotificationDTO();
		dtn.setCreated(new Date());
		dtn.setIcon("fa fa-building");
		dtn.setId_user(useracegi.getId_user());
		dtn.setId_supplier(idsupplier);
		dtn.setMessage("Nuevo cliente <span class='label label-primary'>"+dto.getName()+"</span>");
		dtn.setPriority("1");
		dtn.setLink("store.htm?id=" + id);
		// Se persiste la notificacion
		Long idn = userNotificationService.createNotification(dtn, "002");
		logger.debug("Notification, id="+idn);
		/*-------------------------------------------------------*/

		return new ModelAndView(getSuccessView());
	}

}
