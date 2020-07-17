package com.retailsbs.logistikapp.web.ctrl.upload;

import java.io.File;
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
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.retailsbs.logistikapp.financial.dto.UpdProductDTO;
import com.retailsbs.logistikapp.financial.service.FinancialService;
import com.retailsbs.logistikapp.supplier.service.SupplierService;
import com.retailsbs.logistikapp.user.domain.Access;
import com.retailsbs.logistikapp.user.domain.Quantities;
import com.retailsbs.logistikapp.user.domain.UserAcegi;
import com.retailsbs.logistikapp.user.dto.AddNotificationDTO;
import com.retailsbs.logistikapp.user.dto.NotificationTodaySearchCriteria;
import com.retailsbs.logistikapp.user.dto.UpdUserDTO;
import com.retailsbs.logistikapp.user.notification.service.UserNotificationService;
import com.retailsbs.logistikapp.user.service.UserService;

public class FileUploadController extends SimpleFormController{
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	private UserService userService;
	private SupplierService supplierService;
	private UserNotificationService userNotificationService;
	private FinancialService financialService;
	private String IMG_PRODUCT;
	private String PATH_ROOT;
	private String PATH_IMAGES;
	public String getPATH_IMAGES() {
		return PATH_IMAGES;
	}
	public void setPATH_IMAGES(String pATH_IMAGES) {
		PATH_IMAGES = pATH_IMAGES;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public void setSupplierService(SupplierService supplierService) {
		this.supplierService = supplierService;
	}
	public void setFinancialService(FinancialService financialService) {
		this.financialService = financialService;
	}
	public void setIMG_PRODUCT(String iMG_PRODUCT) {
		IMG_PRODUCT = iMG_PRODUCT;
	}
	public void setPATH_ROOT(String pATH_ROOT) {
		PATH_ROOT = pATH_ROOT;	
				}

	public FileUploadController(){
		setCommandClass(FileUpload.class);
		setCommandName("fileUploadForm");
	}
 
	public void setUserNotificationService(
			UserNotificationService userNotificationService) {
		this.userNotificationService = userNotificationService;
	}
	protected Map<String, Object> referenceData(HttpServletRequest arg0, Object arg1, Errors arg2) throws ServletRequestBindingException, Exception {
		logger.debug("---- REFERENCEDATA ----");
		/*-------------------------------------------------------*/
		logger.debug("----- USER CONTEXT -----");
		// Se obtiene el contexto del usuario
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserAcegi useracegi = (UserAcegi) auth.getPrincipal();
		/*-------------------------------------------------------*/
		////////////////////////////////////////////////////////////////////////
		//Se obtienen los datos de acceso para el usuario
		List<Access> accesslist = userService.getAccessByIdUser(useracegi.getId_user());
		//Se obtiene el primer acceso por defecto
		Access useraccess = accesslist.get(0);
		////////////////////////////////////////////////////////////////////////
		NotificationTodaySearchCriteria dtoqty = new NotificationTodaySearchCriteria();
		dtoqty.setProfile(useracegi.getProfile());
		dtoqty.setFecha(new Date());
		dtoqty.setId_supplier(useraccess.getId_supplier());
		dtoqty.setId_user(useracegi.getId_user());
		logger.debug("----- HEADER/NOTIFICATION -----");
		Quantities nttdto = userService.getQtyAlertAndMessagesByIdUser(dtoqty);
		//HeaderNotificationDTO nttdto = userService.getHeaderNotificationByIdUser(useracegi.getId_user());
		logger.debug("alert="+nttdto.getAlert_qty()+" | message="+nttdto.getMessage_qty()+" | todo="+nttdto.getTodo_qty());
		/*-------------------------------------------------------*/
		String redireccion = null;
		if(arg0.getParameter("redireccion") != null && !arg0.getParameter("redireccion").equals(""))
			redireccion = ServletRequestUtils.getStringParameter(arg0, "redireccion");
		logger.info("redireccion="+redireccion);
		
		Long id_product = null;
		if(arg0.getParameter("id_product") != null && !arg0.getParameter("id_product").equals(""))
			id_product = ServletRequestUtils.getLongParameter(arg0, "id_product");
		logger.info("id_product="+id_product);
		
		String name_product = null;
		if(arg0.getParameter("name_product") != null && !arg0.getParameter("name_product").equals(""))
			name_product = ServletRequestUtils.getStringParameter(arg0, "name_product");
		logger.info("name_product="+name_product);
		
		String login_user = null;
		if(arg0.getParameter("login_user") != null && !arg0.getParameter("login_user").equals(""))
			login_user = ServletRequestUtils.getStringParameter(arg0, "login_user");
		logger.info("login_user="+login_user);
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("useracegi", useracegi);
		model.put("nttdto", nttdto);
		model.put("id_product", id_product);
		model.put("name_product", name_product);
		model.put("login_user", login_user);
		model.put("useraccess",useraccess);
		model.put("redireccion", redireccion);
		return model;
	}

	protected Object formBackingObject(HttpServletRequest request) throws ServletException {
		logger.debug("---- FORMBACKINGOBJECT ----");
		FileUpload command = new FileUpload();
		return command;
	}
	
	@Override
	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserAcegi useracegi = (UserAcegi) auth.getPrincipal();
		
		Long id_user = useracegi.getId_user();
		
		String login_user = null;
		if(request.getParameter("login_user")!=null && !request.getParameter("login_user").equals(""))
			login_user = ServletRequestUtils.getStringParameter(request, "login_user");
		logger.info("login_user = "+login_user);
		
		Long id_product = null;
		if(request.getParameter("id_product")!=null && !request.getParameter("id_product").equals(""))
			id_product = ServletRequestUtils.getLongParameter(request, "id_product");
		logger.info("id_product = "+id_product);
		
		String name_product = null;
		if(request.getParameter("name_product")!=null && !request.getParameter("name_product").equals(""))
			name_product = ServletRequestUtils.getStringParameter(request, "name_product");
		logger.info("name_product = "+name_product);
		
		String redireccion = "#";
			
		logger.info("redireccion="+redireccion);
		
		String path_image = "/img/";
		
		if(login_user != null) {
			if(useracegi.getProfile().equals("ADM"))
				path_image = PATH_IMAGES + "admin/user/";
			else {
				List<Access> accessList = userService.getAccessByIdUser(id_user);
				Long supplier_id = 0L;
				if (accessList != null && accessList.size() > 0)
					supplier_id = accessList.get(0).getId_supplier();
				path_image = PATH_IMAGES + supplier_id + "/"+ "user" +"/";
			} // if(useracegi.getProfile().equals("ADM"))
		} // if(login_user != null)
		
		if(id_product != null) 
			path_image = IMG_PRODUCT;
		logger.info("path_image="+path_image);
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		
		@SuppressWarnings("unchecked")
		Map <String,CommonsMultipartFile> filemap = multipartRequest.getFileMap();
		
		String filename = null;
		
		if( filemap != null ){
			for (MultipartFile multipartFile : filemap.values()) {
				
				filename = multipartFile.getOriginalFilename();
				if (filename != null && !filename.equals("")){
					logger.debug("filename --> "+ multipartFile.getContentType());			
					String extension = ".png";
					
					if(login_user!=null)
						filename = useracegi.getId_user() + extension;
					else if(id_product!=null)
						filename = id_product + extension;
					
					byte[] in = multipartFile.getBytes();
					
					File out = new File(PATH_ROOT+path_image+filename);
					
					if (out.exists()) {
						out.delete();
					}
					
					File folder = new File (PATH_ROOT+path_image);
					
					boolean creararchivo = folder.mkdirs();
					logger.debug("SE CREO LA RUTA: --->" + creararchivo);
					
					FileCopyUtils.copy(in,out);
					logger.info("ruta de la imagen copy: ---> "+ path_image+filename);			
					if(login_user!=null){
						//se modifica la direccion de la imagen del usuario
						UpdUserDTO dto_usr = new UpdUserDTO();
						dto_usr.setId_user(id_user);
						dto_usr.setImage(path_image+filename);				
						dto_usr.setModified(new Date());				
						dto_usr.setLogin(useracegi.getUserlogin());
						int row = userService.updUser(dto_usr);
						
						// Se genera una notificacion
						if(!useracegi.getProfile().equals("ADM")){
							AddNotificationDTO dtn = new AddNotificationDTO();
							Long id_supplier = userService.getAccessByIdUser(useracegi.getId_user()).get(0).getId_supplier();
							dtn.setCreated(new Date());
							dtn.setIcon("fa fa-user");
							dtn.setId_user(useracegi.getId_user());
							dtn.setId_supplier(id_supplier);
							dtn.setMessage("Imagen del usuario <span class='label label-success'>"+ useracegi.getFullname() +"</span > actualizada");
							dtn.setPriority("1");
							dtn.setLink("profile.htm?id=" + id_user);
							// Se persiste la notificacion
							Long idn = userNotificationService.createNotification(dtn, "005");
							logger.debug("Notification, id="+idn);
						} // if(!useracegi.getProfile().equals("ADM"))
						logger.info("FileUploadController.java - onSubmit - numero de registros modificados="+row);
						logger.info("ruta de la imagen UPD : ---> "+ path_image+filename);
						break;
					} // if(login_user!=null)
					if(id_product != null){
						// modifica la direccion y el nombre de la imagen del producto
						UpdProductDTO dto_pro = new UpdProductDTO();
						dto_pro.setId_product(id_product);
						dto_pro.setImage(path_image+filename);
						
						int row = financialService.updProduct(dto_pro);
						logger.info("FileUploadController.java - onSubmit - numero de registros modificados="+row);
						
						//Se genera una notificacion
						AddNotificationDTO dto_not = new AddNotificationDTO();
						dto_not.setCreated(new Date());
						dto_not.setId_supplier(userService.getAccessByIdUser(useracegi.getId_user()).get(0).getId_supplier());
						dto_not.setIcon("fa fa-barcode");
						dto_not.setId_user(id_user);
						dto_not.setMessage("Imagen del producto <span class='label label-success'>"+ name_product +"</span > actualizada");
						dto_not.setPriority("1");
						
						Long id_not = userService.addNotification(dto_not);
						logger.debug("Notificacion agregada id= "+id_not+" OK!");
					} // if(id_product != null)
				} // if (filename != null && !filename.equals(""))
	        } // for (MultipartFile multipartFile : filemap.values())
		} // if ( filemap != null )
		
		return new ModelAndView("redirect:"+redireccion);
	}
}
