package co.jjortiz.aplicacion;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import co.alertroom.ws.vo.UsuarioVo;
import co.jjortiz.entidades.Usuario;

import java.util.Properties;

public class EnvioEmail {
	private final Properties properties = new Properties();
	
	private String password ="alertroom1907902";

	private Session session;

	private void init() {

		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.port","587");
		properties.put("mail.smtp.mail.sender","alertroomapplication@gmail.com");
		properties.put("mail.smtp.user", "alertroomapplication@gmail.com");
		properties.put("mail.smtp.auth", "true");

		session = Session.getDefaultInstance(properties);
	}
	
	public void sendEmail(Usuario usuario){

		init();
		try{
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress((String)properties.get("mail.smtp.mail.sender")));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(usuario.getEmail()));
			message.setSubject("Bienvenido a Alert Room");
			message.setText("\r\n"  
					+ "Armenia -  Quindio "
					+"\r\n"  
					+"\r\n"  
					+ "Nos complace informarle Sr(a) "+ usuario.getNombres()+ " "+usuario.getApellidos()+" que ya se encuentra registrado en nuestro sistema, "
					+ "para loguearse por favor ingrese con su número de documento y de igual forma digite este en el campo de contrasena, por último le recomendamos "
					+ "que actualice la contraseña inmediatamente se loguee, para evitar que otra persona ingrese a su cuenta, gracias por hacer parte de Alert Room."
					+"\r\n"
					+"\r\n"
					+ "Alerta: Este mensaje ha sido enviado automáticamente, por favor no responder.\r\n" + 
					"\r\n" + 
					"\r\n" + 
					"Alert Room\r\n" + 
					" Desarrollado en Armenia Quindío (2020)\r\n" + 
					" Desarrollo enfocado al Servicio Nacional de Aprendizaje SENA\r\n" + 
					"\r\n" + 
					"Contacto\r\n" + 
					" Correo : development.CompanyAQ@gmail.com\r\n" + 
					" Tel (+57) : 300 590 5557  - 318 260 11 47");
			Transport t = session.getTransport("smtp");
			t.connect((String)properties.get("mail.smtp.user"), password);
			t.sendMessage(message, message.getAllRecipients());
			t.close();
		}catch (MessagingException me){
                        //Aqui se deberia o mostrar un mensaje de error o en lugar
                        //de no hacer nada con la excepcion, lanzarla para que el modulo
                        //superior la capture y avise al usuario con un popup, por ejemplo.
			return;
		}
		
	}
}
