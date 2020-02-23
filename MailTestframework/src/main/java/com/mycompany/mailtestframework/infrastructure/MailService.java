package com.mycompany.mailtestframework.infrastructure;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class MailService implements IMailService{
	
	protected IConfigurationService configurationService;
	
	public MailService (IConfigurationService configurationService){
		this.configurationService = configurationService;
		
	}

	@Override
	public void sendMail() {
		Properties properties = System.getProperties();
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.store.protocol", "smtp");
		properties.put("mail.smtp.auth", "true");
		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(configurationService.getUser(), configurationService.getPassword());
			}
		});
		String recipient = configurationService.getUser();
		String sender = configurationService.getUser();

		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(sender));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
			message.setSubject("Automatically sent Test e-mail");
			message.setText("This is a test mail");
			Transport.send(message);
			System.out.println("Mail successfully sent");
		} catch  (NoSuchProviderException ex) {
				System.out.println("No provider.");
				ex.printStackTrace();
			}catch (MessagingException ex) {
				System.out.println("Could not send a message");
				ex.printStackTrace();
					
			}
	}
	
	@Override
	public void clearInbox() throws MessagingException {
		Properties properties = new Properties();
		properties.setProperty("mail.imap.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		properties.setProperty("mail.imap.socketFactory.fallback", "false");
		properties.setProperty("mail.imap.socketFactory.port",
				String.valueOf("993"));
		properties.put("mail.imap.host", "imap.gmail.com");
		properties.put("mail.imap.port", "993");

		Session session = Session.getDefaultInstance(properties);

		try {
			Store store = session.getStore("imap");
			store.connect(configurationService.getUser(), configurationService.getPassword());

			Folder folderInbox = store.getFolder("INBOX");
			folderInbox.open(Folder.READ_WRITE);

			Message[] arrayMessages = folderInbox.getMessages();

			for (int i = 0; i < arrayMessages.length; i++) {
				Message message = arrayMessages[i];
				message.setFlag(Flags.Flag.DELETED, true);

			}
			boolean expunge = true;
			folderInbox.close(expunge);
			store.close();
			} catch (NoSuchProviderException ex) {
				System.out.println("No provider.");
				ex.printStackTrace();
			} catch (MessagingException ex) {
				System.out.println("Could not connect to the message store.");
				ex.printStackTrace();
			}
}
	}
