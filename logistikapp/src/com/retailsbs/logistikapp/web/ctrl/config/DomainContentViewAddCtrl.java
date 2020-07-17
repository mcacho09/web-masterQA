package com.retailsbs.logistikapp.web.ctrl.config;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContextHolder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.retailsbs.logistikapp.domain.dto.AddDomainContentDTO;
import com.retailsbs.logistikapp.domain.service.DomainService;
import com.retailsbs.logistikapp.user.domain.Access;
import com.retailsbs.logistikapp.user.domain.Quantities;
import com.retailsbs.logistikapp.user.domain.UserAcegi;
import com.retailsbs.logistikapp.user.dto.NotificationTodaySearchCriteria;
import com.retailsbs.logistikapp.user.service.UserService;

public class DomainContentViewAddCtrl extends SimpleFormController {

	protected final Log logger = LogFactory.getLog(getClass());

	private DomainService domainService;
	private UserService userService;
	private String ACTIVE;
	private String NO_ACTIVE;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public void setDomainService(DomainService domainService) {
		this.domainService = domainService;
	}
	public void setACTIVE(String aCTIVE) {
		ACTIVE = aCTIVE;
	}
	public void setNO_ACTIVE(String nO_ACTIVE) {
		NO_ACTIVE = nO_ACTIVE;
	}

	public DomainContentViewAddCtrl() {
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
		logger.debug("----- USER ACCESS -----");
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
		String accion = "Agregar";
		String titulo_estado = "Nuevo Condenido de dominio";
		
		Long id_domain = ServletRequestUtils.getLongParameter(arg0, "domain_id");
		logger.info("id_domain= "+id_domain);
		String domain_name = ServletRequestUtils.getStringParameter(arg0, "domain_name");
		logger.info("domain_name = "+domain_name);
		
		Map<String, Object> model = new HashMap<String, Object>();
		
		model.put("useracegi", useracegi);
		model.put("useraccess",useraccess);
		model.put("nttdto", nttdto);
		model.put("accion", accion);
		model.put("titulo_estado", titulo_estado);
		model.put("ACTIVE", ACTIVE);

		model.put("id_domain", id_domain);
		model.put("domain_name", domain_name);

		return model;
	}

	protected Object formBackingObject(HttpServletRequest request) throws ServletException {
		logger.debug("---- FORMBACKINGOBJECT ----");
		AddDomainContentDTO command = new AddDomainContentDTO();
		return command;
	}

	protected ModelAndView onSubmit(HttpServletRequest request,HttpServletResponse response, Object command, BindException errors) throws ServletException {
		logger.debug("---- ONSUBMIT ----");

		Long id_domain = ServletRequestUtils.getLongParameter(request, "domain_id");
		logger.info("id_domain= "+id_domain);
		
		String domain_name = ServletRequestUtils.getStringParameter(request, "domain_name");
		logger.info("domain_name = "+domain_name);

		String active = ServletRequestUtils.getStringParameter(request, "active", NO_ACTIVE);
		logger.debug("active="+active);

		AddDomainContentDTO dto = (AddDomainContentDTO) command;
		dto.setCreated(new Date());
		dto.setActive(active);
		// Se persiste el objeto
		Long id = domainService.addDomainContent(dto);
		logger.debug("DomainContent, id=" + id + " creado... OK");

		return new ModelAndView(getSuccessView()+"?id_domain="+id_domain+"&domain_name="+domain_name);
	}

}
