package com.retailsbs.logistikapp.web.ctrl.sales;

import java.io.File;

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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.retailsbs.logistikapp.user.domain.UserAcegi;

/**
 * Class controller para subir el archivo que se importa de product
 * @author Juan Carlos Ramos Campos
 * @since 25-03-2015
 */
public class ProductImportCtrl implements Controller {
	
	protected final Log logger = LogFactory.getLog(getClass());

	private String view;
	private String IMPORT_FILE;

	public void setIMPORT_FILE(String iMPORT_FILE) {
		IMPORT_FILE = iMPORT_FILE;
	}
	public void setView(String view) {
		this.view = view;
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

		Long id_categoria = null;
		if(arg0.getParameter("id_categoria")!=null && !arg0.getParameter("id_categoria").equals(""))
			id_categoria = ServletRequestUtils.getLongParameter(arg0, "id_categoria");
		logger.info("id_categoria="+id_categoria);
		
		String readFile = null;
		if(arg0.getParameter("readFile")!=null && !arg0.getParameter("readFile").equals(""))
			readFile = ServletRequestUtils.getStringParameter(arg0, "readFile");
		logger.info("readFile="+readFile);

		HttpSession sess = arg0.getSession();
		sess.setAttribute("PROIMP", "pro");
		sess.setAttribute("pro_id_categoria", id_categoria);
		sess.setAttribute("pro_readFile", readFile);
		
		File directorio = new File(IMPORT_FILE+useracegi.getUserlogin());
		directorio.mkdir();
		
//		borrado de archivos 
		File file = new File(IMPORT_FILE+useracegi.getUserlogin());
		File[] archivos = file.listFiles();
		for(int i=0; i<archivos.length; i++){
			if(!archivos[i].isHidden()){
				File archivoBorrar = new File(IMPORT_FILE+useracegi.getUserlogin()+"/"+archivos[i].getName());
				if(archivoBorrar.delete()) logger.info("archivo "+archivos[i]+"borrado");
			}
		}
		
		String path_file = IMPORT_FILE+useracegi.getUserlogin()+"/";
		logger.info("path_file="+path_file);
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) arg0;
		MultipartFile multipartFile = multipartRequest.getFile("file");
		
		String filename = null;
		
		if ( multipartFile != null ) {
			filename = multipartFile.getOriginalFilename();
			byte[] in = multipartFile.getBytes();
			File out = new File(path_file+filename);
			
			FileCopyUtils.copy(in,out);
			sess.setAttribute("pro_filename", filename);
		}
		
		return new ModelAndView(view);
	}

}
