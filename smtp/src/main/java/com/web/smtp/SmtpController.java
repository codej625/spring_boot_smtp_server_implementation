package com.web.smtp;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SmtpController {

	// logger
	private final Logger log = LoggerFactory.getLogger(getClass());

	static Session getMailSession;
	static Properties mailServerProperties;
	static MimeMessage generateMailMessage;

	@PostMapping("/mail")
	@ResponseBody
	public void mailSender() throws AddressException, MessagingException {
		
		// sender(발신자), recipient(수신자) 

		// naver smtp server
		String smtp_server = "smtp.naver.com";
		// naver smtp port(ssl x)
		int smtp_port = 465;

		// sender email
		String username = "dkwksla2@naver.com";
		// sender password
		final String password = "";

		// recipient email
		String recipient = "dkwksla@naver.com";
		// 수신자에게 보낼 email subject
		String subject = "hello! nice to meet you!";
		// 수신자에게 보낼 email contents
		String contents = "contents";

		// smtp server setting
		Properties props = System.getProperties();
		// smtp server
		props.put("mail.smtp.host", smtp_server);
		// smtp port
		props.put("mail.smtp.port", smtp_port);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.ssl.enable", "true");
		// ssl port 연결
		props.put("mail.smtp.ssl.trust", smtp_server);

		// session 생성 & sender smtp server login
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected javax.mail.PasswordAuthentication getPasswordAuthentication() {

				return new javax.mail.PasswordAuthentication(username, password);
			}
		});

		// debug mode
		session.setDebug(true);

		// MimeMessage 생성 & mail setting
		Message mimeMessage = new MimeMessage(session);
		mimeMessage.setFrom(new InternetAddress(username)); // sender
		mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient)); // recipient

		// 여러사람에게 메일을 보내고 싶을때?
		// InternetAddress[] addArray = new InternetAddress[3];
		// addArray[0] = new InternetAddress("aaa@aaa.aaa");
		// addArray[1] = new InternetAddress("bbb@bbb.bbb");
		// addArray[2] = new InternetAddress("ccc@ccc.ccc");
		// message.addRecipients(Message.RecipientType.TO, addArray);

		mimeMessage.setSubject("test"); // title
		mimeMessage.setText("pass"); // contents

		// text가 아닌 html 태그를 보내고 싶다면?
		// mimeMessage.setContent("<h1>안녕하세용?</h1>","text/html; charset=UTF-8")

		Transport.send(mimeMessage); // send

	}
}
