/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.distribuidas.foodbet.mail;

import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author david
 */
public class Mail
{
  private final String from = "foodbet.sports@gmail.com";
  private final String password = "foodbet1";
  public final String  WINNER = "GANADOR";
  public final String  LOSER = "APUESTA PERDIDA";
  
  private InternetAddress[] addressTo;
  private String Subject = "";
  private String MessageMail = "";
  
  public void SEND()
  {
    try
    {
      Properties props = new Properties();
      props.put("mail.smtp.host", "smtp.gmail.com");
      props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.smtp.auth", "true");
      props.put("mail.smtp.user", this.from);
      props.put("mail.smtp.port", "587");
      
      Session session = Session.getDefaultInstance(props, new SMTPAutenticar(getFrom(),getPassword()));
      session.setDebug(false);
      
      MimeMessage mimemessage = new MimeMessage(session);
      InternetAddress addressFrom = new InternetAddress(getFrom());
      mimemessage.setFrom(addressFrom);
      mimemessage.setRecipients(Message.RecipientType.TO, this.addressTo);
      mimemessage.setSubject(getSubject());
      
      MimeBodyPart mimebodypart = new MimeBodyPart();
      mimebodypart.setText(getMessage());
      mimebodypart.setContent(getMessage(), "text/html");
      Multipart multipart = new MimeMultipart();
      multipart.addBodyPart(mimebodypart);
      mimemessage.setContent(multipart);
      mimemessage.setSentDate(new Date());
      Transport.send(mimemessage);
        System.out.println("Correo enviado. Exitosamente!!!");
    }
    catch (MessagingException ex)
    {
      System.out.println(ex);
    }
  }
  
  public String getFrom()
  {
    return this.from;
  }
  
  public String getPassword()
  {
    return this.password;
  }
  
  public void setTo(String mails)
  {
    String[] tmp = mails.split(",");
    this.addressTo = new InternetAddress[tmp.length];
    for (int i = 0; i < tmp.length; i++) {
      try
      {
        this.addressTo[i] = new InternetAddress(tmp[i]);
      }
      catch (AddressException ex)
      {
        System.out.println(ex);
      }
    }
  }
  
  public InternetAddress[] getTo()
  {
    return this.addressTo;
  }
  
  public void setSubject(String value)
  {
    this.Subject = value;
  }
  
  public String getSubject()
  {
    return this.Subject;
  }
  
  public void setMessage(String value)
  {
    this.MessageMail = value;
  }
  
  public String getMessage()
  {
    return this.MessageMail;
  }
}
