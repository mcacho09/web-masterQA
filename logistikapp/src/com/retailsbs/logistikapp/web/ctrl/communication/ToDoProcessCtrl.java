package com.retailsbs.logistikapp.web.ctrl.communication;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.view.RedirectView;

import com.retailsbs.logistikapp.user.dto.UpdToDoDTO;
import com.retailsbs.logistikapp.user.service.UserService;

/**
 * Class controller para procesar todo
 * @author jorge
 * @since 20-01-2015
 */
public class ToDoProcessCtrl implements Controller {

	protected final Log logger = LogFactory.getLog(getClass());
	
	private UserService userService;
	private String view;
	private String ACTIVE;
	private String NO_ACTIVE;

	public void setUserService(UserService userService) {
		this.userService = userService;
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
		/*-------------------------------------------------------*/
		// Se obtiene objeto de dominio del todo
		Long id_todo = ServletRequestUtils.getLongParameter(arg0, "id");
		logger.info("id_todo="+id_todo);
		/*-------------------------------------------------------*/
		// Se obtiene la accion
		String accion = ServletRequestUtils.getStringParameter(arg0, "accion", null);
		logger.info("accion="+accion);

		int rows = 0;
		
		if( accion.equals("delete") ) {
			// Se persiste el objeto
			rows = userService.delToDoById(id_todo);
			logger.debug("Todo, id=" + id_todo + " eliminado " + rows + " ...ok!");
		}
		
		if( accion.equals("complete") ) {
			UpdToDoDTO dto = new UpdToDoDTO();
			dto.setId_todo(id_todo);
			dto.setModified(new Date());
			dto.setActive(NO_ACTIVE);
			// Se persiste el objeto
			rows = userService.updToDo(dto);
			logger.debug("Todo, id=" + id_todo + " actualizado " + rows + " ...ok!");
		}

		if( accion.equals("active") ) {
			UpdToDoDTO dto = new UpdToDoDTO();
			dto.setId_todo(id_todo);
			dto.setModified(new Date());
			dto.setActive(ACTIVE);
			// Se persiste el objeto
			rows = userService.updToDo(dto);
			logger.debug("Todo, id=" + id_todo + " actualizado " + rows + " ...ok!");
		}
		
		
		/*-------------------------------------------------------*/

		return new ModelAndView(new RedirectView(view));
	}

}
