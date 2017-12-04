package fpt.k9.foodquality.dao;

import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Repository;

import fpt.k9.foodquality.model.User;

@Repository
public class Email {
	private String randomPass()
	{
		String pass="";
		char[] chars = "abcdefghijklmnopqrstuvwxyz1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 12; i++) {
		    char c = chars[random.nextInt(chars.length)];
		    sb.append(c);
		}
		pass = sb.toString();
		return pass;
	}
	public String sendEmail(User receiver)
	{
		String result;
		
		final String username = "foodqualitydev@gmail.com";
		final String password = "12345678@Ab";
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		receiver.setUpass(randomPass());
		Session session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username,password);
				}
			});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("foodqualitydev@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(receiver.getUmail()));
			message.setSubject("Request to reset password");
			message.setText("Dear " + receiver.getUname()+",\n"+
					"** This is an automated message -- please do not reply as you will not receive a response. **\n"+
					"This message is in response to your request to reset your account password.\n"+
					"Your password is:"+receiver.getUpass()+"\n"+
					"Thank you.\nFood Quality Dev Team.\n");
			Transport.send(message);
			result = "Done!";
			UserDAO us = new UserDAO();
			us.changpwd(receiver);

		} catch (MessagingException e) {
			result = "Error!";
			throw new RuntimeException(e);
		}
		return result;
	}

}
