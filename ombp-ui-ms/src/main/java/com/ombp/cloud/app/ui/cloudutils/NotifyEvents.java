package com.ombp.cloud.app.ui.cloudutils;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ombp.cloud.model.alert.EmailDetails;


public class NotifyEvents {
	
	private static Logger LOG = LoggerFactory.getLogger(NotifyEvents.class);
	
	public static synchronized boolean trySendingEmail(int retryCount, EmailDetails emailDetails) 
	{
		boolean result = false;
		
		if(retryCount>0) 
		{
			try {
				sendEmail(retryCount, emailDetails);
				result = true;
			}
			catch(Exception e) 
			{
				LOG.error("", e);
			}
		}
		
		return result;
	}
	
	
	private static void sendEmail(int retryCount, EmailDetails emailDetails)
			throws MessagingException 
	{
		if(retryCount > 0) 
		{
			try {
				send(emailDetails);
			} catch (MessagingException e) {
				if(retryCount==1) 
				{
					LOG.error("XXXXXXXXXXXXXXXXXXXXX:::Exception while sending email...", e);
					throw new MessagingException(e.getMessage());
				}
				
				retryCount = retryCount-1;
				sendEmail(retryCount, emailDetails);
			}
		}
	}
	
	private static void send(EmailDetails emailDetails) throws MessagingException {
		LOG.info("Inside Send Email...");
		
		Properties properties = new Properties();
        properties.setProperty("mail.smtp.host", "smtp.zoho.in");
        properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.setProperty("mail.smtp.socketFactory.fallback", "false");
        properties.setProperty("mail.smtp.port", "465");
        properties.setProperty("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth", "true");
        //properties.put("mail.debug", "true");
        properties.put("mail.store.protocol", "pop3");
        properties.put("mail.transport.protocol", "smtp");
        //properties.put("mail.debug.auth", "true");
        properties.setProperty( "mail.pop3.socketFactory.fallback", "false");
        Session session = Session.getDefaultInstance(properties,new javax.mail.Authenticator() 
        {   @Override
            protected PasswordAuthentication getPasswordAuthentication() 
            {   return new PasswordAuthentication(emailDetails.getUsername(), emailDetails.getPassword());
            }
        });

		// compose message

		List<InternetAddress> toRecepients = new ArrayList<InternetAddress>();
		List<InternetAddress> ccRecepients = new ArrayList<InternetAddress>();
		List<InternetAddress> bccRecepients = new ArrayList<InternetAddress>();

		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(emailDetails.getFromEmailAddress()));
		
		if(emailDetails.getTo()!=null && !emailDetails.getTo().isEmpty()) {
			for(String to : emailDetails.getTo())
			toRecepients.add(new InternetAddress(to));
			message.addRecipients(Message.RecipientType.TO, toRecepients.toArray(new InternetAddress[] {}));
		}

		if(emailDetails.getCc()!=null && !emailDetails.getCc().isEmpty()) {
			for(String cc : emailDetails.getCc())
			ccRecepients.add(new InternetAddress(cc));
			message.addRecipients(Message.RecipientType.CC, ccRecepients.toArray(new InternetAddress[] {}));
		}	
		
		if(emailDetails.getBcc()!=null && !emailDetails.getBcc().isEmpty()) {
			for(String bcc : emailDetails.getBcc())
			bccRecepients.add(new InternetAddress(bcc));
			message.addRecipients(Message.RecipientType.BCC, bccRecepients.toArray(new InternetAddress[] {}));
		}
		
		message.setSubject(emailDetails.getSubject());
		
		BodyPart textPart = new MimeBodyPart();
		textPart.setContent(emailDetails.getMessage(), "text/html");
		
		MimeBodyPart attachmentPart = null;
		
		if(emailDetails.isAttachmentPresent()) 
		{
			attachmentPart = new MimeBodyPart();

			DataSource source = new FileDataSource(System.getProperty("user.dir") + emailDetails.getAttachmentFilePath());
			attachmentPart.setDataHandler(new DataHandler(source));
			attachmentPart.setFileName(emailDetails.getAttachmentFileNameInEmail());
		}
		
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(textPart);
		
		if(emailDetails.isAttachmentPresent()) 
		{
			multipart.addBodyPart(attachmentPart);
		}
		message.setContent(multipart);
		
		// send message
		Transport.send(message);
		
		String sentTo = emailDetails.getTo()!=null ? String.join(";", emailDetails.getTo()) : "";
		String sentCc = emailDetails.getCc()!=null ? String.join(";", emailDetails.getCc()) : "";
		String sentBcc = emailDetails.getBcc()!=null ? String.join(";", emailDetails.getBcc()) : "";
		
		LOG.info("Email sent successfully to :::" + sentTo + ", CC :::" + sentCc + ", BCC :::" + sentBcc
				+ " with subject as ::: " + emailDetails.getSubject() + "::::");
	}

	
}
