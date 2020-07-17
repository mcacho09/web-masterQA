package com.retailsbs.logistikapp.email;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SendEmail {

	protected final Log logger = LogFactory.getLog(getClass());

	public static void SendEmailInfo(EmailDataDTO dto) {

		SimpleDateFormat fecha_ini = new SimpleDateFormat("dd/MM/yyyy");
		String fecha_inicio = fecha_ini.format(dto.getPlan_started());
		String fecha_fin = fecha_ini.format(dto.getPlan_end());

		try {
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com"); // for gmail use
															// smtp.gmail.com
			props.put("mail.smtp.auth", "true");
			props.put("mail.debug", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.port", "465");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class",
					"javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.socketFactory.fallback", "false");

			Session session = Session.getInstance(props,
					new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(
									"soporte@logistikapp.com", "l4g3st3k1pp");
						}
					});

			try {

				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress(dto.getFrom(), dto
						.getName_from()));
				message.setRecipients(Message.RecipientType.TO,
						InternetAddress.parse(dto.getTo()));
				message.setSubject(dto.getSubject());

				// --[ Create the body of the mail
				String text = "Bienvenido a LogistikApp,<br>"
						+ "<br><br>Estimado(a) "
						+ dto.getUsername()
						+ ":<br><br><br><br> Me complace comunicarme con usted para darle la bienvenida a LogistikApp, a continuaci&oacute;n se presentan las caracter&iacute;sticas de su plan."
						+ "<br><br>Usuario: <b>"
						+ dto.getUserlogin()
						+ "</b>"
						+ "<br>Clave: <b>"
						+ dto.getPasswd()
						+ "</b>"
						+ "<br><br>Empresa: <b>"
						+ dto.getName()
						+ "</b>"
						+ "<br><br><br>Fecha de Inicio: <b>"
						+ fecha_inicio
						+ "</b>"
						+ "<br>Fecha de Termino: <b>"
						+ fecha_fin
						+ "</b>"
						+ "<br><br>Direcci&oacute;n para ingresar: <a href='http://b2b.logistikapp.com/?userlogin="
						+ dto.getUserlogin()
						+ "'>LogistikApp Login</a>"
						+ "<br><br>Descarga app para Android: <a href='https://play.google.com/store/apps/details?id=app.loggistikapp.com.logistikapp'>LogistikApp Android</a>"
						+ "<br><br>Consulta nuestra gu&iacute;a de implementaci&oacute;n: <a href='http://logistikapp.com/docs/GUIAIMPLEMENTACION.pdf'>Gu&iacute;a R&aacute;pida</a>"
						+ "<br><br><b>- Dinos como podemos ayudarte</b><br><br><br>soporte@logistikapp.com<br>Tel 449-2-81-21-58";

				// set plain text message
				message.setContent(text, "text/html;charset=utf-8");

				// --[ Ask the Transport class to send our mail message
				Transport.send(message);
				System.out.println("Enviado....Enviando hacia soporte");
				copiasoporte(dto);
			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}

		} catch (Exception E) {
			// logger.debug("Error al mandar correo");
			// logger.debug(E);
		}

	}

	public static void copiasoporte(EmailDataDTO dto) {

		SimpleDateFormat fecha_ini = new SimpleDateFormat("dd/MM/yyyy");
		String fecha_inicio = fecha_ini.format(dto.getPlan_started());
		String fecha_fin = fecha_ini.format(dto.getPlan_end());

		try {
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com"); // for gmail use
															// smtp.gmail.com
			props.put("mail.smtp.auth", "true");
			props.put("mail.debug", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.port", "465");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class",
					"javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.socketFactory.fallback", "false");

			Session session = Session.getInstance(props,
					new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(
									"soporte@logistikapp.com", "l4g3st3k1pp");
						}
					});

			try {

				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress(dto.getFrom(), dto
						.getName_from()));
				message.setRecipients(Message.RecipientType.TO,
						InternetAddress.parse(dto.getBcc()));
				message.setSubject(dto.getSubject());

				// --[ Create the body of the mail
				String text = "Bienvenido a LogistikApp,<br>"
						+ "<br><br>Estimado(a) "
						+ dto.getUsername()
						+ ":<br><br><br><br> Me complace comunicarme con usted para darle la bienvenida a LogistikApp, a continuaci&oacute;n se presentan las caracter&iacute;sticas de su plan."
						+ "<br><br>Usuario: <b>"
						+ dto.getUserlogin()
						+ "</b>"
						+ "<br>Clave: <b>"
						+ dto.getPasswd()
						+ "</b>"
						+ "<br><br>Empresa: <b>"
						+ dto.getName()
						+ "</b>"
						+ "<br><br>Email Registrado: <b>"
						+ dto.getTo()
						+ "</b>"
						+ "<br><br>Tel&eacute;fono: <b>"
						+ dto.getPhone()
						+ "</b>"
						+ "<br><br><br>Fecha de Inicio: <b>"
						+ fecha_inicio
						+ "</b>"
						+ "<br>Fecha de Termino: <b>"
						+ fecha_fin
						+ "</b>"
						+ "<br><br>Direcci&oacute;n para ingresar: <a href='http://b2b.logistikapp.com/?userlogin="
						+ dto.getUserlogin()
						+ "'>LogistikApp Login</a>"
						+ "<br><br>Descarga app para Android: <a href='https://play.google.com/store/apps/details?id=app.loggistikapp.com.logistikapp'>LogistikApp Android</a>"
						+ "<br><br>Consulta nuestra gu&iacute;a de implementaci&oacute;n: <a href='http://logistikapp.com/docs/GUIAIMPLEMENTACION.pdf'>Gu&iacute;a R&aacute;pida</a>"
						+ "<br><br><b>- Dinos como podemos ayudarte</b><br><br><br>soporte@logistikapp.com<br>Tel 449-2-81-21-58";

				// set plain text message
				message.setContent(text, "text/html;charset=utf-8");

				// --[ Ask the Transport class to send our mail message
				Transport.send(message);
				System.out.println("Enviado Correo Soporte");
			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}

		} catch (Exception E) {
			// logger.debug("Error al mandar correo");
			// logger.debug(E);
		}

	}

	public static void SendEmailInterno(EmailDataDTO dto) {

		try {
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com"); // for gmail use
															// smtp.gmail.com
			props.put("mail.smtp.auth", "true");
			props.put("mail.debug", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.port", "465");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class",
					"javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.socketFactory.fallback", "false");

			Session session = Session.getInstance(props,
					new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(
									"soporte@logistikapp.com", "l4g3st3k1pp");
						}
					});

			try {

				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress(dto.getFrom(), dto
						.getName_from()));
				message.setRecipients(Message.RecipientType.TO,
						InternetAddress.parse(dto.getTo()));
				message.setRecipients(Message.RecipientType.BCC,
						InternetAddress.parse(dto.getBcc()));
				message.setSubject(dto.getSubject());

				// --[ Create the body of the mail
				String text = "Bienvenido a LogistikApp,<br>"
						+ "<br><br>Estimado(a) "
						+ dto.getUsername()
						+ ":<br><br><br><br> Me complace comunicarme con usted para darle la bienvenida a LogistikApp, a continuaci&oacute;n se presentan las caracter&iacute;sticas de su plan."
						+ "<br><br>Usuario: <b>"
						+ dto.getUserlogin()
						+ "</b>"
						+ "<br>Clave: <b>"
						+ dto.getPasswd()
						+ "</b>"
						+ "<br><br>Direcci\u00f3n para ingresar: <a href='http://b2b.logistikapp.com/?userlogin="
						+ dto.getUserlogin()
						+ "'>LogistikApp Login</a>"
						+ "<br><br>Descarga app para Android: <a href='https://play.google.com/store/apps/details?id=app.loggistikapp.com.logistikapp'>LogistikApp Android</a>"
						+ "<br><br>Consulta gu&iacute;a de implementaci&oacute;n: <a href='"
						+ (dto.getProfile().contains("DRI") ? "http://www.logistikapp.com/docs/guia-logistikapp-premium-vendedor.pdf"
								: "http://logistikapp.com/docs/GUIAIMPLEMENTACION.pdf")
						+ "'>Gu&iacute;da R&aacute;pida</a>"
						+ "<br><br><b>- Dinos como podemos ayudarte</b><br><br><br>soporte@logistikapp.com<br>Tel 449-2-81-21-58";

				// set plain text message
				message.setContent(text, "text/html;charset=utf-8");

				// --[ Ask the Transport class to send our mail message
				Transport.send(message);
				System.out.println("Enviado");
			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}

		} catch (Exception E) {
			// logger.debug("Error al mandar correo");
			// logger.debug(E);
		}

	}

	public void sendEmailMessageSoporte(EmailDataDTO dto) {

		try {
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com"); // for gmail use
															// smtp.gmail.com
			props.put("mail.smtp.auth", "true");
			props.put("mail.debug", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.port", "465");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class",
					"javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.socketFactory.fallback", "false");

			Session session = Session.getInstance(props,
					new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(
									"soporte@logistikapp.com", "l4g3st3k1pp");
						}
					});

			try {

				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress(dto.getFrom(), dto
						.getName_from()));
				message.setRecipients(Message.RecipientType.TO,
						InternetAddress.parse(dto.getTo()));
				message.setSubject(dto.getSubject());

				if (dto.getFrom().equalsIgnoreCase("Usuario@SinCorreo.com")) {
					dto.setFrom("NO DISPONIBLE");
				}

				if (dto.getPhone().isEmpty()) {
					dto.setPhone("NO DISPONIBLE");
				}

				SimpleDateFormat formateador = new SimpleDateFormat(
						"dd 'de' MMMM 'de' yyyy hh:mm 'hrs'", new Locale("es",
								"MX"));
				Date fech = new Date();

				String fecha = formateador.format(fech);

				String text = "<b>Fecha/Hora: </b>" + fecha + "<br><br>"
						+ "<b>Empresa:</b> " + "<i>" + dto.getName()
						+ "</i><br>" + "<b>Usuario:</b> " + "<i>"
						+ dto.getUsername() + "</i><br>" + "<b>Email:</b> "
						+ "<i>" + dto.getFrom() + "</i><br><br>"
						+ "<b>Tel&eacute;fono:</b> " + "<i>" + dto.getPhone()
						+ "</i><br><br>" + "<b>Mensaje:</b><br><br>" + "''"
						+ dto.getMessage() + "''<br>";

				// +"<br><br><a href='http://localhost:8080/logistikapp/inboxmsge.htm?idr="+dto.getIdMessage()+"'>Acceso al mensaje</a>";

				message.setContent(text, "text/html;charset=utf-8");

				// --[ Ask the Transport class to send our mail message
				Transport.send(message);
				logger.debug("Enviado");
			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}

		} catch (Exception E) {
			// logger.debug("Error al mandar correo");
			// logger.debug(E);
		}

	}

	public static void sendTicket(String toEmail, String toName, String body,
			String retail, String email_retail, String user, String user_email,
			String supplier) {
		
		if (toEmail == null || toEmail.isEmpty())
			return;
		
		try {
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com"); // for gmail use
															// smtp.gmail.com
			props.put("mail.smtp.auth", "true");
			props.put("mail.debug", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.port", "465");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class",
					"javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.socketFactory.fallback", "false");

			Session session = Session.getInstance(props,
					new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(
									"soporte@logistikapp.com", "l4g3st3k1pp");
						}
					});

			try {

				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress(email_retail, supplier));
				message.addRecipients(Message.RecipientType.TO,
						new Address[] { new InternetAddress(toEmail, toName) });
				
				if (user_email != null && !user_email.isEmpty()){
					message.addRecipient(Message.RecipientType.BCC, new InternetAddress(user_email, user));
				}
				
				if (email_retail != null && !email_retail.isEmpty()) {
					message.addRecipient(Message.RecipientType.BCC, new InternetAddress(email_retail, retail));
				}
				
				message.setSubject(supplier + ": Notificación de visita");

				// set plain text message
				message.setContent(body, "text/html;charset=utf-8");

				// --[ Ask the Transport class to send our mail message
				Transport.send(message);
				System.out.println("Enviado....");
			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}

		} catch (Exception E) {
			// logger.debug("Error al mandar correo");
			// logger.debug(E);
			
			System.err.println("Error al mandar correo");
			System.err.println(E.getMessage());
		}
	}

}
