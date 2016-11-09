package org.bglogin.web.utils;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.Session;


public class MailUtils {

	public static void inviaMail(String[] recipients, String subject, String message , String from) throws MessagingException
	{
	  boolean debug = false;
	 
	  // Impostazioni SMTP
	  Properties props = new Properties();
	  props.put("mail.smtp.host", "ADSPMCAS01.Assembly.local");
	 
	  // istanzio un oggetto Session
	  	final String EmailUser = "assembly\\stinti";
		final String EmailPassword = "Assembly12!";

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
				  protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(
						EmailUser,EmailPassword);
				     }
			   });
	  session.setDebug(debug);
	 
	  // creo l'oggetto Message partendo da Session
	  Message msg = new MimeMessage(session);
	 
	  // Definisco mittente
	  InternetAddress addressFrom = new InternetAddress(from);
	  msg.setFrom(addressFrom);
	 
	  // Destinatari
	  InternetAddress[] addressTo = new InternetAddress[recipients.length]; 
	  for (int i = 0; i < recipients.length; i++)
	  {
	    addressTo[i] = new InternetAddress(recipients[i]);
	  }
	  msg.setRecipients(Message.RecipientType.TO, addressTo);
	    
	  // OPZIONALE: è possibile definire anche dei custom headers...
	  msg.addHeader("MyHeaderName", "myHeaderValue");
	 
	  // Imposto il subject, il contenuto ed il content type (testo semplice)
	  msg.setSubject(subject);
	  msg.setContent(message, "text/HTML");
	  
	  // Spedisco
	  Transport.send(msg);
	}

}
