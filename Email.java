/**
author: Kamogelo Tsipa 
Git: KamoKG
Repositry: https://github.com/KamoKG/Java-To-Do-List-App
description: Java app for sending Daily To-Do List via email as reminder   
requirements: jre: 1.8.0
packages : javax.mail.jar 
Class: Email 
Class description - Email class to compose and send email via smtp using JavaMail API
Compile: Make run
*/
import java.util.ArrayList;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import com.sun.mail.smtp.*;
import java.util.ArrayList;
import java.lang.Object;

public class Email {
	 /**
	 * @params filename Text file name.
	 * @todo Reads text file line by line and add each line to ArrayList.
	 * @return Nothing will be returned.
	 */
	 public static void main(String [] args) {
	 	try {
	 	 	 File path = new File(args[0]); 
	 	 	 ArrayList<String> file = new ArrayList<String>(); 
	 	 	 FileReader fileR = new FileReader(path);
	 	 	 BufferedReader buffR = new BufferedReader(fileR);
	 	 	 StringBuffer stringB = new StringBuffer();
	 	 	 String line;

	 	 	 while((line = buffR.readLine()) != null) {
	 	 	 	 file.add(line);
	 	 	 } 
			 
			
	 	 	 if(file.size() != 0) {
	 	 	 	 System.out.println("Sending to do list: ");
				 Email e = new Email();
	 	 	 	 e.sendMail(file);
	 	 	 }
	 	 }

	 	 catch(IOException e) {
	 	 	 e.printStackTrace();
	 	 }
	 }	
	
	 /**
	 * @params list ArrayList of content of email from text file.
	 * @todo Connect to mail servers, compose and send an email.
	 * @return Nothing will be returned.
	 */
	 public static void sendMail(ArrayList<String> list) {
	 	 String to = /*--Reciever email address--*/;
	 	 String host = /*--Sender mail host server: e.g. "smtp.gmail.com"--*/; 
	 	 String from = /*--Sender email address--*/;
	 	 final String user = /*--Sender email address--*/;
	 	 final String pass = /*--Sender password--*/;

	 	 Properties props = new Properties();
	 	 props.put("mail.smtp.host", host);
	 	 props.put("mail.smtp.auth", "true");
	 	 props.put("mail.smtp.starttls.enable", "true");
	 	 props.put("mail.smtp.port", /*--Mail host outgoing port no. e.g. "587"--*/);
	 	 Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
	 	 	 protected PasswordAuthentication getPasswordAuthentication() {
	 	 	 	 return new PasswordAuthentication(user, pass);
	 	 	 }
	 	 });

	 	 try {
		 	 Message msg = new MimeMessage(session);
		 	 msg.setFrom(new InternetAddress(from));
		 	 msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		 	 msg.setSubject("Alert for the day");;
		 	 StringBuilder sb = new StringBuilder();
		 	 sb.append("Event to do:").append(System.lineSeparator());
		 	 for(int i = 0; i < list.size(); i++) {
				 sb.append(list.get(i)).append(System.lineSeparator());
		 	 }

		 	 msg.setText(sb.toString());
		 	 Transport.send(msg);
		 	 System.out.println("To do list sent successfully...");
	 	 } 

	 	 catch (MessagingException e) {
	 	 	 e.printStackTrace();
	 	 }
	 }
}
