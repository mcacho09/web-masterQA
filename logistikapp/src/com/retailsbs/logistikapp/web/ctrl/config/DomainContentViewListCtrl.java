package com.retailsbs.logistikapp.web.ctrl.config;

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
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.retailsbs.logistikapp.domain.dto.DomainContentDTO;
import com.retailsbs.logistikapp.domain.dto.DomainContentSearchCriteria;
import com.retailsbs.logistikapp.domain.service.DomainService;
import com.retailsbs.logistikapp.user.domain.Access;
import com.retailsbs.logistikapp.user.domain.Quantities;
import com.retailsbs.logistikapp.user.domain.UserAcegi;
import com.retailsbs.logistikapp.user.dto.NotificationTodaySearchCriteria;
import com.retailsbs.logistikapp.user.service.UserService;

/**
 * Class controller para la vista administraci�n de dmomain cont
 * @author Juan Carlos Ramos Campos
 * @since 17-12-2014
 */
public class DomainContentViewListCtrl implements Controller {
	
	protected final Log logger = LogFactory.getLog(getClass());

	public DomainService domainService;
	public UserService userService;
	private String view;
	private String ACTIVE;
	private String NO_ACTIVE;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public void setDomainService(DomainService domainService) {
		this.domainService = domainService;
	}
	public void setView(String view) {
		this.view = view;
	}
	public void setACTIVE(String aCTIVE) {
		ACTIVE = aCTIVE;
	}
	public void setNO_ACTIVE(String nO_ACTIVE) {
		NO_ACTIVE = nO_ACTIVE;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
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
		//Se obtiene id_domain del c ual se va a mostrar el contenido
		Long id_domain = ServletRequestUtils.getLongParameter(arg0, "id_domain");
		logger.debug("id_domain=" + id_domain);
		//Se obtiene nombre del dominio
		String domain_name = ServletRequestUtils.getStringParameter(arg0, "domain_name");
		logger.debug("domain_name=" + domain_name);
		
		//Se hace la busqueda de domainCont dependiendo del id_domain
		DomainContentSearchCriteria dto = new DomainContentSearchCriteria();
		dto.setId_domain(id_domain);
		
		List<DomainContentDTO> list_domain_content = domainService.getDomainContentByCriteria(dto);
		logger.info("list_domain_content = "+list_domain_content.size());
		
		Map<String,Object> model = new HashMap<String,Object>();

		model.put("useracegi", useracegi);
		model.put("useraccess",useraccess);
		model.put("nttdto", nttdto);
		model.put("ACTIVE", ACTIVE);
		model.put("NO_ACTIVE", NO_ACTIVE);
		
		model.put("list_domain_content", list_domain_content);
		model.put("id_domain", id_domain);
		model.put("domain_name", domain_name);

		return new ModelAndView(view, model);
	}

}
