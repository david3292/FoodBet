/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.distribuidas.foodbet.mail;


import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;


/**
 *
 * @author david
 */
public class SMTPAutenticar
  extends Authenticator
{
  private String SMTP_AUTH_USER = "";
  private String SMTP_AUTH_PWD = "";
  
  public SMTPAutenticar() {}
  
  public SMTPAutenticar(String user, String pass)
  {
    this.SMTP_AUTH_USER = user;
    this.SMTP_AUTH_PWD = pass;
  }
  
  @Override
  public PasswordAuthentication getPasswordAuthentication()
  {
    return new PasswordAuthentication(this.SMTP_AUTH_USER, this.SMTP_AUTH_PWD);
  }
}

