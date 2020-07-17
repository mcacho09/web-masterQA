package com.retailsbs.logistikapp.web.ctrl.user;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.retailsbs.logistikapp.user.domain.User;
import com.retailsbs.logistikapp.user.dto.AddNotificationDTO;
import com.retailsbs.logistikapp.user.dto.UpdUserDTO;
import com.retailsbs.logistikapp.user.notification.service.UserNotificationService;
import com.retailsbs.logistikapp.user.service.UserService;

/**
 * Controller para la vista forgotpasswd 
 * @author Sergio
 * @since 27-07-2015
 */
public class ForgotPasswdViewCtrl implements Controller {

	
	protected final Log logger = LogFactory.getLog(getClass());

	private UserService userService;
	private UserNotificationService userNotificationService;
	private String view;
	private String CORREO_SOPORTE;
	private String PASSWD_SOPORTE;
	

	public void setCORREO_SOPORTE(String cORREO_SOPORTE) {
		CORREO_SOPORTE = cORREO_SOPORTE;
	}
	public void setPASSWD_SOPORTE(String pASSWD_SOPORTE) {
		PASSWD_SOPORTE = pASSWD_SOPORTE;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public void setUserNotificationService(
			UserNotificationService userNotificationService) {
		this.userNotificationService = userNotificationService;
	}
	public void setView(String view) {
		this.view = view;
	}
	
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		
		boolean send =false;
		boolean found = true;
		
		String email = ServletRequestUtils.getStringParameter(arg0, "email");
		if(email!=null && email!="")
		{
			String newpasswd;
			User usr = userService.getUserByEmail(email);
			if(usr!=null)
			{
				String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
				Random rnd = new Random();

				   StringBuilder sb = new StringBuilder(10);
				   for( int i = 0; i < 10; i++ ) 
				   {
					   sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
				   }
				   newpasswd = sb.toString();
				   
				   UpdUserDTO dto = new UpdUserDTO();
				   dto.setId_user(usr.getId_user());
				   dto.setPasswd(newpasswd);
				   dto.setModified(new Date());
				   dto.setLogin("Sistema Recuperacion");
					

				   try{

				        Properties props = new Properties();
				        props.put("mail.smtp.host", "smtp.gmail.com"); // for gmail use smtp.gmail.com
				        props.put("mail.smtp.auth", "true");
				        props.put("mail.debug", "true"); 
				        props.put("mail.smtp.starttls.enable", "true");
				        props.put("mail.smtp.port", "465");
				        props.put("mail.smtp.socketFactory.port", "465");
				        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
				        props.put("mail.smtp.socketFactory.fallback", "false");

				        Session mailSession = Session.getInstance(props, new javax.mail.Authenticator() {

				            protected PasswordAuthentication getPasswordAuthentication() {
				                return new PasswordAuthentication(CORREO_SOPORTE, PASSWD_SOPORTE);
				            }
				        });

				        mailSession.setDebug(true); // Enable the debug mode
				        
				        Message msg = new MimeMessage( mailSession );

				        //--[ Set the FROM, TO, DATE and SUBJECT fields
				        msg.setFrom( new InternetAddress(CORREO_SOPORTE) );
				        msg.setRecipients( Message.RecipientType.TO,InternetAddress.parse(usr.getEmail()+","+CORREO_SOPORTE) );
				        msg.setSentDate( new Date());
				        msg.setSubject( "[no responder]Nueva clave LogistikApp" );

				        //--[ Create the body of the mail
				       String text = "Tu nueva clave es : <b>" + newpasswd + "</b> favor de cambiarla lo mas pronto posible";

				        // set plain text message
				        msg.setContent(text, "text/html");
				        //--[ Ask the Transport class to send our mail message
				        Transport.send( msg );
				        
				        send=true;
				        
				    
				    }
				   catch(Exception E){
				    	logger.debug("Error al mandar correo");
				    	logger.debug( E );
				    }
				   if(send)
				   {
				   // Se persiste el objeto
				   int rows = userService.updUser(dto);
				   logger.debug("Nueva clave establecida :"+newpasswd);
				   logger.debug("User id=" + dto.getId_user() + " actualizado " + rows + "...ok!");
				   
				   // Se genera una notificaciï¿½n
					AddNotificationDTO dtn = new AddNotificationDTO();
					dtn.setCreated(new Date());
					dtn.setIcon("fa fa-key");
					dtn.setId_user(usr.getId_user());
					dtn.setMessage("Se cambio la contraseña de <span class='label label-primary'>"+usr.getUserlogin()+"</span> ");
					dtn.setPriority("1");
					dtn.setLevel("P");
					dtn.setId_supplier(userService.getAccessByIdUser(usr.getId_user()).get(0).getId_supplier());
					dtn.setLink("profile.htm?id=" + usr.getId_user());
					// Se persiste la notificacion
					Long idn = userNotificationService.createNotification(dtn, "005");
					logger.debug("Notificacion creada, id="+idn);
					/*-------------------------------------------------------*/
				   }
			}
			else // no se encontro correo
			{

				found =false;
			}
		}

		Map<String,Object> model = new HashMap<String,Object>();

		model.put("found", found);
		model.put("send", send);
		// TODO Auto-generated method stub

		
		return new ModelAndView(view, model);
	}

}
