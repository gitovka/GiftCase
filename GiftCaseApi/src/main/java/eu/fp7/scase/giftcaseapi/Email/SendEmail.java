package eu.fp7.scase.giftcaseapi.Email;

import java.util.*;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import eu.fp7.scase.giftcaseapi.Users.JavaUsersModel;
import eu.fp7.scase.giftcaseapi.utilities.HibernateController;

public class SendEmail {
	
	public boolean sendNotification(String recipient, int sender) {

		HibernateController call = new HibernateController();
		List<JavaUsersModel> users = call.getUserByUsersId(sender);

		JavaUsersModel userSender = users.get(0);
		String lastName = userSender.getlastName();
		String firstName = userSender.getfirstName();

		List<JavaUsersModel> usersRecipient = call
				.getUserByFacebookId(recipient);
		String email = usersRecipient.get(0).getemail();
		String firstNameRecipient = usersRecipient.get(0).getfirstName();

		String subject = "Gift for you!";
		String text = "Dear "
				+ firstNameRecipient
				+ ",\n \n"
				+ firstName
				+ " "
				+ lastName
				+ " send you a proposal of gift! You can deny it or accept it.\n\nBest Regards,\nGiftCase";

		if (email == null) {
			return false;
		} else {
			return sendEmail(email, subject, text);
		}
	}

	private static boolean sendEmail(String email, String subject, String text) {

		final String username = "giftcase.scase@gmail.com";
		final String password = "gifter#2016";

		Properties props = new Properties();
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		props.put("mail.smtp.auth", true);
		props.put("mail.smtp.starttls.enable", true);
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("no-reply@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,	InternetAddress.parse(email));
			message.setSubject(subject);
			message.setText(text);

			Transport.send(message);

			return true;

		} catch (MessagingException e) {
			return false;
		}
	}

}
