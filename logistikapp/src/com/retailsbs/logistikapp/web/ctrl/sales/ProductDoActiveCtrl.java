package com.retailsbs.logistikapp.web.ctrl.sales;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContextHolder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.retailsbs.logistikapp.financial.dto.UpdProductDTO;
import com.retailsbs.logistikapp.financial.service.FinancialService;
import com.retailsbs.logistikapp.supplier.exception.SupplierNotFoundException;
import com.retailsbs.logistikapp.supplier.service.SupplierService;
import com.retailsbs.logistikapp.user.domain.UserAcegi;

/**
 * Controller para cambiar el estado activo del producto
 * @author jorge
 * @since 18-12-2014
 */
public class ProductDoActiveCtrl implements Controller {
	
	protected final Log logger = LogFactory.getLog(getClass());

	public SupplierService supplierService;
	private FinancialService financialService;
	private String view;

	public void setSupplierService(SupplierService supplierService) {
		this.supplierService = supplierService;
	}
	public void setFinancialService(FinancialService financialService) {
		this.financialService = financialService;
	}

	public void setView(String view) {
		this.view = view;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception, SupplierNotFoundException {
		/*-------------------------------------------------------*/
		// Se obtiene el contexto del usuario
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserAcegi ua = (UserAcegi) auth. getPrincipal();
		String login = ua.getUserlogin();
		logger.debug("login="+login);
		
		/*-------------------------------------------------------*/
		// Se obtiene los datos por parametro
		Long id_product = ServletRequestUtils.getLongParameter(arg0, "id");
		logger.info("id_product ="+id_product );
		
		String active = ServletRequestUtils.getStringParameter(arg0, "active");
		logger.info("active="+active);

		/*-------------------------------------------------------*/
		// Se obtiene el objeto de dominio
		UpdProductDTO dto = new UpdProductDTO();
		dto.setActive(active);
		dto.setId_product(id_product);
		dto.setLogin(login);
		dto.setModified(new Date());
		int rows = financialService.updProduct(dto);
		logger.debug("Product id=" + dto.getId_product() + " actualizado " + rows + "...ok!");

		return new ModelAndView(view);
	}

}
