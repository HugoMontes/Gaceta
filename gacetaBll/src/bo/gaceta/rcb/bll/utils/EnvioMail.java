package bo.gaceta.rcb.bll.utils;


import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

public class EnvioMail {


  public String envioMail(String destinatario, String codigoEmpresa, String password) {
    final String usermail = "gacetadecomercio@gmail.com";
    final String passmail = "fundempresa2017";

    Properties props = new Properties();
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.port", "587");
    props.put("mail.smtp.user", usermail);
    props.put("mail.smtp.password", passmail);
    Session session = Session.getInstance(props,null);

    if (codigoEmpresa.substring(0, 1).equals("0")) {
      destinatario = "daniel.zerain@gmail.com";
    }
    try {
      MimeMessage mesage = new MimeMessage(session);
      System.out.println("Port: "+session.getProperty("mail.smtp.port"));
      InternetAddress from = new InternetAddress(usermail);
      mesage.setSubject("Activacion de Cuenta - Gaceta Electronica R.C.B.");
      mesage.setFrom(from);
      mesage.addRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));

      String message = "";
      message += "<i>Se&ntilde;or empresaria(o), se habilit&oacute; su cuenta correctamente</i><br>";
      message += "<b></b><br>";
      message += "<i>El C&oacute;digo de Usuario es: " + codigoEmpresa + "</i><br>";
      message += "<p/>";
      message += "<i>La contrase&ntilde;a asignada es: " + password + "</i><br>";
      message += "<p/>";
      message += "<i>Recuerde cambiar la contrase&ntilde;a en su primer ingreso</i><br>";
      message += "<p>Ingrese a la gaceta presionando el siguiente link:</p>";
      message += "<a href='http://www.gacetadecomercio.gob.bo:8080/gaceta/login.xhtml'><span>www.gacetadecomercio.gob.bo:8080/gaceta/login.xhtml</span></a>";

      System.out.println("++++++++++++++++++++++++ CODIGO: "+codigoEmpresa);
      System.out.println("++++++++++++++++++++++++ PASSWORD: "+password);

      Multipart multipart = new MimeMultipart();
      BodyPart messageBodyPart = new MimeBodyPart();
      messageBodyPart = new MimeBodyPart();
      String htmlMessage = message;
      messageBodyPart.setContent(htmlMessage, "text/html");
      multipart.addBodyPart(messageBodyPart);
      mesage.setContent(multipart);

      Transport transport = session.getTransport("smtp");
      transport.connect("smtp.gmail.com", usermail, passmail);
      System.out.println("Transport: "+transport.toString());
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
    Session session = Session.getInstance(props,null);

    try {
      MimeMessage mesage = new MimeMessage(session);
      System.out.println("Port: "+session.getProperty("mail.smtp.port"));
      InternetAddress from = new InternetAddress(usermail);
      mesage.setSubject("Estado Publicación  - Gaceta Electrónica R.C.B.");
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
      System.out.println("Transport: "+transport.toString());
      transport.sendMessage(mesage, mesage.getAllRecipients());
      return ("Mensaje enviado");
    } catch (MessagingException e) {
      return e.getMessage();
    }
  }

}
