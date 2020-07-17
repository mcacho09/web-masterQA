package com.retailsbs.logistikapp.web.ctrl.logistic;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContextHolder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.retailsbs.logistikapp.supplier.service.SupplierService;
import com.retailsbs.logistikapp.user.domain.UserAcegi;
import com.retailsbs.logistikapp.user.dto.AccessTinyDTO;
import com.retailsbs.logistikapp.user.service.UserService;

/**
 * Class controller para la vista administraci�n de empresas
 * @author Juan Carlos Ramos Campos
 * @since 25-11-2014
 */
public class StoreImportCtrl implements Controller {
	
	protected final Log logger = LogFactory.getLog(getClass());

	private UserService userService;
	private SupplierService supplierService;
	private String view;
	private String IMPORT_FILE;

	public void setIMPORT_FILE(String iMPORT_FILE) {
		IMPORT_FILE = iMPORT_FILE;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public void setView(String view) {
		this.view = view;
	}
	public SupplierService getSupplierService() {
		return supplierService;
	}
	public void setSupplierService(SupplierService supplierService) {
		this.supplierService = supplierService;
	}
	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		logger.debug("---- HANDLE/REQUEST ----");
		/*-------------------------------------------------------*/
		logger.debug("----- USER CONTEXT -----");
		// Se obtiene el contexto del usuario
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserAcegi useracegi = (UserAcegi) auth.getPrincipal();
		/*-------------------------------------------------------*/
		logger.debug("----- USER ACCESS -----");
		List<AccessTinyDTO> accesssupplier = userService.getAccessSupplierByIdUser(useracegi.getId_user());
		logger.debug("accesssupplier="+accesssupplier.size());
		/*-------------------------------------------------------*/

		Long id_state = null;
		if(arg0.getParameter("id_state")!=null && !arg0.getParameter("id_state").equals(""))
			id_state = ServletRequestUtils.getLongParameter(arg0, "id_state");
		logger.info("id_state = "+id_state);
		
		Long id_city = null;
		if(arg0.getParameter("id_city")!=null && !arg0.getParameter("id_city").equals(""))
			id_city = ServletRequestUtils.getLongParameter(arg0, "id_city");
		logger.info("id_city = "+id_city);
		
		Long id_categoria = null;
		if(arg0.getParameter("id_categoria")!=null && !arg0.getParameter("id_categoria").equals(""))
			id_categoria = ServletRequestUtils.getLongParameter(arg0, "id_categoria");
		logger.info("id_categoria = "+id_categoria);
		
		String readFile = null;
		if(arg0.getParameter("readFile")!=null && !arg0.getParameter("readFile").equals(""))
			readFile = ServletRequestUtils.getStringParameter(arg0, "readFile");
		logger.info("readFile"+readFile);

		//=================================================================================
		HttpSession sess = arg0.getSession();
		sess.setAttribute("STRIMP", "str");
		sess.setAttribute("str_id_state", id_state);
		sess.setAttribute("str_id_city", id_city);
		sess.setAttribute("str_id_categoria", id_categoria);
		sess.setAttribute("str_readFile", readFile);
		//=================================================================================
		
		Long id_supplier = userService.getAccessByIdUser(useracegi.getId_user()).get(0).getId_supplier();
		
		File directorio = new File(IMPORT_FILE+id_supplier+"/");
		boolean crear  = directorio.mkdirs();
		logger.debug("Se creo el directorio --->  " + crear);		
//		borrado de archivos 
		File file = new File(IMPORT_FILE+id_supplier+"/");
		File[] archivos = file.listFiles();
		
		
		for(int i=0; i<archivos.length; i++){
			if(!archivos[i].isHidden()){
				File archivoBorrar = new File(IMPORT_FILE+id_supplier+"/"+archivos[i].getName());
				if(archivoBorrar.delete()) logger.info("archivo "+archivos[i].getName()+" borrado");
			}
		}
		
		String path_file = IMPORT_FILE+id_supplier+"/";
		logger.info("path_file="+path_file);
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) arg0;
		
		@SuppressWarnings("unchecked")
		Map <String,CommonsMultipartFile> filemap = multipartRequest.getFileMap();
		String filename = null;
		
		if ( filemap != null ) {
			
			for (MultipartFile multipartFile : filemap.values()) {
				filename = multipartFile.getOriginalFilename();
				if (filename != null && !filename.equals(""))
				{
					filename = multipartFile.getOriginalFilename();
					byte[] in = multipartFile.getBytes();
					File out = new File(path_file+filename);
					FileCopyUtils.copy(in,out);
					sess.setAttribute("str_filename", filename);
					
				}
	        }
			
			
		}
		
		return new ModelAndView(view);
	}

}
