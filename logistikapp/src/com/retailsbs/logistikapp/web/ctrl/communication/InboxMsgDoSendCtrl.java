package com.retailsbs.logistikapp.web.ctrl.communication;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContextHolder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.retailsbs.logistikapp.email.EmailDataDTO;
import com.retailsbs.logistikapp.email.SendEmail;
import com.retailsbs.logistikapp.supplier.domain.Supplier;
import com.retailsbs.logistikapp.supplier.exception.SupplierNotFoundException;
import com.retailsbs.logistikapp.supplier.service.SupplierService;
import com.retailsbs.logistikapp.user.domain.Access;
import com.retailsbs.logistikapp.user.domain.User;
import com.retailsbs.logistikapp.user.domain.UserAcegi;
import com.retailsbs.logistikapp.user.dto.AttachmentDTO;
import com.retailsbs.logistikapp.user.dto.SendMessageDTO;
import com.retailsbs.logistikapp.user.service.UserService;

/**
 * Controller para enviar un mensajhe
 * @author JORGE
 * @since 27-01-2015
 */
public class InboxMsgDoSendCtrl implements Controller {

	protected final Log logger = LogFactory.getLog(getClass());

	private UserService userService;
	private SupplierService supplierService;
	private String view;
	private String PATH_ROOT;
	private String PATH_IMAGES;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public SupplierService getSupplierService() {
		return supplierService;
	}

	public void setSupplierService(SupplierService supplierService) {
		this.supplierService = supplierService;
	}

	public void setView(String view) {
		this.view = view;
	}
	public void setPATH_IMAGES(String pATH_IMAGES) {
		PATH_IMAGES = pATH_IMAGES;
	}
	public void setPATH_ROOT(String pATH_ROOT) 
	{
		PATH_ROOT = pATH_ROOT;
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception, SupplierNotFoundException {
		logger.debug("---- REFERENCEDATA ---- ");
		/*-------------------------------------------------------*/
		// Se obtienen los datos del usuario //
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserAcegi useracegi = (UserAcegi) auth.getPrincipal();
		List<Access> access = userService.getAccessByIdUser(useracegi.getId_user());
		logger.debug("access="+access.size());		
		
		// Se obtiene los datos por parametro
		Long id_user = ServletRequestUtils.getLongParameter(arg0, "id_user");
		logger.info("id_user=" + id_user);
		
		Long id_user_to = ServletRequestUtils.getLongParameter(arg0, "id_user_to");
		logger.info("id_user_to=" + id_user_to);

		Long id_supplier = userService.getAccessByIdUser(id_user).get(0).getId_supplier();
		
		String message = ServletRequestUtils.getStringParameter(arg0, "message");
		logger.info("message=" + message);
		
		boolean ismobile = ServletRequestUtils.getBooleanParameter(arg0, "ismobile", false);
		logger.info("ismobile=" + ismobile);
		
		Long id_user_message = ServletRequestUtils.getLongParameter(arg0, "id_user_message");
		logger.info("id_user_message=" + id_user_message);
		ServletRequestUtils.getStringParameter(arg0, "attachFile ");
		
		
		//Se recibe archivo adjunto

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) arg0;
		
		
		@SuppressWarnings("unchecked")
		Map <String,CommonsMultipartFile> filemap = multipartRequest.getFileMap();
		String path_file_message = PATH_IMAGES + id_supplier + "/"+ "message" +"/";
		List<AttachmentDTO> attachment_files= new ArrayList<AttachmentDTO>(); 
		userService.setPATH_ROOT(PATH_ROOT);
		int cont = 0;
		
		for (MultipartFile multipartFile : filemap.values()) {
			cont++;
			String filename = cont + multipartFile.getOriginalFilename();
			if (filename != null && !filename.equals("")&&!filename.equals("1")){
				byte[] in = multipartFile.getBytes();
				filename ="idmessage=="+filename.replace("==", "--");				
				AttachmentDTO attachment = new AttachmentDTO();
				attachment.setCreated(new Date());
				attachment.setFile(path_file_message+filename);
				attachment.setFolder(PATH_ROOT+path_file_message);
				attachment.setIn(in);
				attachment_files.add(attachment);
				
			}
        }
		
		
		// Se setea el dto del mensaje
		if ((message!=null&&!message.equals("")) || attachment_files.size()>0){
			SendMessageDTO dto = new SendMessageDTO();
			dto.setId_user(id_user);
			dto.setId_user_to(id_user_to);
			dto.setMessage(message);
			dto.setAttachment_files(attachment_files);			
			// Se persiste el objeto
			id_user_message = userService.sendMesssage(dto);			
			User destinatario = userService.getUserById(id_user_to);
									
			if(destinatario.getProfile().equalsIgnoreCase("sop")){		
				
				EmailDataDTO edto = new EmailDataDTO();
				SendEmail send = new SendEmail();
				User user = userService.getUserById(useracegi.getId_user());						
				Long id_supp = null;
				for (Access i : access){				
					id_supp = i.getId_supplier();
					logger.debug("ID SUPPLIER ==> "+ id_supp);					
				}				
				Supplier supplier = supplierService.getSupplierById(id_supp);				
				logger.debug("SUPPLIER NAME ==>" + supplier.getName());
				
				
				edto.setUsername(useracegi.getFullname());				
				edto.setName(supplier.getName());
				edto.setTo("soporte@logistikapp.com");
				edto.setName_from(useracegi.getUsername()+" ("+supplier.getName()+")");				
				if(user.getEmail() != null){
					 	edto.setFrom(user.getEmail());	
					}else{
						edto.setFrom("Usuario@SinCorreo.com");	
					}	
				
				edto.setPhone(user.getPhone1());
				edto.setMessage(message);
				edto.setSubject("Mensaje enviado soporte desde App");
				//edto.setIdMessage(id_user_to);
				logger.debug("Enviando" + edto);
				send.sendEmailMessageSoporte(edto);
			}
		}		

		if (id_user_message!= null)
			return new ModelAndView(view+"?idr="+id_user_message);
		else
			return new ModelAndView("redirect:inboxnew.htm");
		
	}
}
