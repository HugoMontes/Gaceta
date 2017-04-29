package bo.gaceta.rcb.presentacion.utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

/**
 * Created by Fundempresa on 17/4/2017.
 */
public class EnvioEmail {

    public String envioMail(String destinatario,String asunto, String mensaje) {
        final String usermail = "gacetadecomercio@gmail.com";
        final String passmail = "fundempresa2017";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.user", usermail);
        props.put("mail.smtp.password", passmail);
        Session session = Session.getInstance(props, null);

        try {
            MimeMessage mesage = new MimeMessage(session);
            System.out.println("Port: " + session.getProperty("mail.smtp.port"));
            InternetAddress from = new InternetAddress(usermail);
            mesage.setSubject(asunto);
            mesage.setFrom(from);
            mesage.addRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));

            Multipart multipart = new MimeMultipart();
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart = new MimeBodyPart();
            String htmlMessage = mensaje;
            messageBodyPart.setContent(htmlMessage, "text/html");
            multipart.addBodyPart(messageBodyPart);
            mesage.setContent(multipart);

            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com", usermail, passmail);
            System.out.println("Transport: " + transport.toString());
            transport.sendMessage(mesage, mesage.getAllRecipients());
            return ("Mensaje enviado");
        } catch (MessagingException e) {
            return e.getMessage();
        }
    }

    public String envioMailEstado(String destinatario, String txtMensaje) {
        final String usermail = "gacetadecomercio@gmail.com";
        final String passmail = "fundempresa2017";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.user", usermail);
        props.put("mail.smtp.password", passmail);
        Session session = Session.getInstance(props, null);

        try {
            MimeMessage mesage = new MimeMessage(session);
            System.out.println("Port: " + session.getProperty("mail.smtp.port"));
            InternetAddress from = new InternetAddress(usermail);
            mesage.setSubject("Estado Publicaci&oacute;n  - Gaceta Electronica R.C.B.");
            mesage.setFrom(from);
            mesage.addRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));

            Multipart multipart = new MimeMultipart();
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart = new MimeBodyPart();
            String htmlMessage = txtMensaje;
            messageBodyPart.setContent(htmlMessage, "text/html");
            multipart.addBodyPart(messageBodyPart);
            mesage.setContent(multipart);

            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com", usermail, passmail);
            System.out.println("Transport: " + transport.toString());
            transport.sendMessage(mesage, mesage.getAllRecipients());
            return ("Mensaje enviado");
        } catch (MessagingException e) {
            return e.getMessage();
        }
    }
}
