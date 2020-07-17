import java.util.Date;

import com.retailsbs.logistikapp.email.EmailDataDTO;
import com.retailsbs.logistikapp.email.SendEmail;

public class EmailTestJUnit {

	
	public void emailDTO() {
		EmailDataDTO edto = new EmailDataDTO();
		
		edto.setFrom("soporte@logistikapp.com");
		edto.setName_from("Soporte LogistikApp");
		edto.setTo("darmz21@hotmail.com");
		edto.setBcc("soporte@logistikapp.com");
		edto.setSubject("Bienvenido a LogistikApp");
		edto.setUsername("David Rosales Muñoz");
		edto.setUserlogin("daver");
		edto.setPasswd("dave");
		edto.setName("DWare");
		edto.setPlan_started(new Date());
		edto.setPlan_end(new Date());
		edto.setPlan_name("Retail");
		edto.setEmail("darmz21@hotmail.com"); 
		edto.setIdPlan(2L);
		edto.setCustomers(1250);
		edto.setUsers(5);
		edto.setPhone("4491962728");
		
		SendEmail.SendEmailInfo(edto);
	}
	
	public void emailSoporte(){
		
		EmailDataDTO edto = new EmailDataDTO();
		SendEmail sendmail = new SendEmail();
		
		edto.setUsername("Cosmo Fulanito");
		edto.setName("Farmacia Patito");
		edto.setTo("darmz21@hotmail.com");
		edto.setName_from("Soporte (Farmacia Patito)");
		edto.setFrom("cosmefulanito@gmail.com");
		edto.setPhone("");
		edto.setMessage("Hola, tengo un problema con mi servicio, necesito ayuda al respecto");
		edto.setSubject("Ayuda con mi servicio");
		//edto.setIdMessage(177);
		
		sendmail.sendEmailMessageSoporte(edto);				
	}

}
