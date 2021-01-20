package com.guest_table.model;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class mail {
	 public static void main(String[] args) {
		 String host = "smtp.gmail.com";  
	        int port = 587;  
	        final String username = "tea102g1@gmail.com";  
	        final String password = "tea79979";  
	  
	        Properties props = new Properties();  
	        props.put("mail.smtp.host", host);  
	        props.put("mail.smtp.auth", "true");  
	        props.put("mail.smtp.starttls.enable", "true");  
	        props.put("mail.smtp.port", port);  
	          
	        Session session = Session.getInstance(props,new Authenticator(){  
	              protected PasswordAuthentication getPasswordAuthentication() {  
	                  return new PasswordAuthentication(username, password);  
	              }} );  
	           
	        try {  
	  
	        Message message = new MimeMessage(session);  
	        message.setFrom(new InternetAddress("tea102g1@gmail.com"));  
	        message.setRecipients(Message.RecipientType.TO,   
	                        InternetAddress.parse("shady28612458@gmail.com"));  
	        message.setSubject("訂味    重新設定密碼");  
	        message.setText("親愛的會員您好,這是您的暫時密碼{} ,請用此密碼登入修改新的密碼,謝謝");  
	  
	        Transport transport = session.getTransport("smtp");  
	        transport.connect(host, port, username, password);  
	  
	        Transport.send(message);  
	  
	        System.out.println("Done");  
	  
	        } catch (MessagingException e) {  
	            throw new RuntimeException(e);  
	        }  
	    }  
	}  