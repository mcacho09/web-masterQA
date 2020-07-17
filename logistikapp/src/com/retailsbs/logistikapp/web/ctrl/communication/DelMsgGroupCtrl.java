package com.retailsbs.logistikapp.web.ctrl.communication;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContextHolder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.retailsbs.logistikapp.user.domain.MessageGroup;
import com.retailsbs.logistikapp.user.domain.UserAcegi;
import com.retailsbs.logistikapp.user.service.UserService;

/**
 * Controlador para borrar las conversaciones de grupos
 * @author Juan Carlos
 * @since 10-08-2015
 */
public class DelMsgGroupCtrl implements Controller {

	protected final Log logger = LogFactory.getLog(getClass());

	private UserService userService;
	private String view;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public void setView(String view) {
		this.view = view;
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		// Se obtiene el contexto del usuario
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserAcegi useracegi = (UserAcegi) auth.getPrincipal();
		logger.info("id_user_asegi = "+useracegi.getId_user());
		logger.debug("---- REFERENCEDATA ---- ");
		/*-------------------------------------------------------*/
		//se obtiene ids_grup de las conversaciones que se borraran
		String ids = arg0.getParameter("idsBorrar");
		String[] lista = ids.split(",");
		
		//borra cada registro de las diferentes tablas
		for(int i=0; i<lista.length; i++){
			// getMessageGroupByIdGroup
			List<MessageGroup> list_msgGrp = userService.getMessageGroupByIdGroup(Long.parseLong(lista[i]));
			logger.debug("list_msgGrp="+list_msgGrp.size());
			// delMessageGrpByIdGrp(id_group)
			int row_mg = userService.delMessageGrpByIdGrp(Long.parseLong(lista[i]));
			logger.debug("row_mg="+row_mg);
			//--------------------------------------------------------
			// delMessageById(id_message)
			for(int j=0; j<list_msgGrp.size(); j++){
				int row_msg = userService.delMessageById(list_msgGrp.get(j).getId_message());
				logger.debug("row_msg="+row_msg);
			} // for(int j=0; j<list_msgGrp.size(); j++)
			//--------------------------------------------------------
			// delUserGrpByIdGrp(id_group)
			int row_ug = userService.delUserGrpByIdGrp(Long.parseLong(lista[i]));
			logger.debug("row_ug="+row_ug);
			//--------------------------------------------------------
			// delGrpById
			int row_grp = userService.delGroupById(Long.parseLong(lista[i]));
			logger.debug("row_grp="+row_grp);
		} // for(int i=0; i<lista.length; i++)
		
		return new ModelAndView(view);
	}

}