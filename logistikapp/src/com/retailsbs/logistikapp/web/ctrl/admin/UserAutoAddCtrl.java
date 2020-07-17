package com.retailsbs.logistikapp.web.ctrl.admin;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.retailsbs.logistikapp.manager.user.ManagerUserService;
import com.retailsbs.logistikapp.manager.user.dto.AutoRegistroDTO;

public class UserAutoAddCtrl extends SimpleFormController {

	protected final Log logger = LogFactory.getLog(getClass());

	private ManagerUserService managerUserService;
	private String AUTOADDURL;

	public void setManagerUserService(ManagerUserService managerUserService) {
		this.managerUserService = managerUserService;
	}
	public void setAUTOADDURL(String aUTOADDURL) {
		AUTOADDURL = aUTOADDURL;
	}

	protected Object formBackingObject(HttpServletRequest request) throws ServletException {
		AutoRegistroDTO command = new AutoRegistroDTO();
		return command;
	}

	protected Map<String, Object> referenceData(HttpServletRequest arg0, Object arg1, Errors arg2) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("AUTOADDURL", AUTOADDURL);
		return model;
	}
	
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws ServletException {
		logger.debug("---- ONSUBMIT ----");

		AutoRegistroDTO dto = (AutoRegistroDTO) command;
		
		managerUserService.autoregistro(dto);

		logger.debug("Usuario " + dto.getUserlogin() + " Registrado");
		
		return new ModelAndView(getSuccessView());

	}

}
