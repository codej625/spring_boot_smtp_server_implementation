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

//	@GetMapping("/")
//	public String testController() {
//
//		log.info(" >> testController start");
//
//		return "test";
//
//	}

	@PostMapping("/mail")
	@ResponseBody
	public void mailSender() throws AddressException, MessagingException {

		// naver smtp server를 사용한다.
		String host = "smtp.naver.com";

		// naver smtp port
		int port = 465;

		// 발신자의 메일 주소
		String username = "dkwksla2@naver.com";

		// 발신자의 PASSWORD
		final String password = "!aawlsdn77";

		// 수신자의 메일 주소
		String recipient = "dkwksla@naver.com";

		// 수신자에게 보낼 메일 제목
		String subject = "hello! nice to meet you!";

		// 수신자에게 보낼 메일 내용
		String contents = "내용";

		// SMTP 서버 설정 정보 세팅
		Properties props = System.getProperties();
		// smtp 서버
		props.put("mail.smtp.host", host);
		// smtp 포트
		props.put("mail.smtp.port", port);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.ssl.trust", host);

		// Session 생성 & 발신자 smtp 서버 로그인 인증
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
				return new javax.mail.PasswordAuthentication(username, password);
			}
		});

		session.setDebug(true); // 디버그 모드

		// MimeMessage 생성 & 메일 세팅
		Message mimeMessage = new MimeMessage(session);
		mimeMessage.setFrom(new InternetAddress(username)); // 발신자
		mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient)); // 수신자

		// 여러사람에게 메일을 보내고 싶을때?
		// InternetAddress[] addArray = new InternetAddress[3];
		// addArray[0] = new InternetAddress("aaa@aaa.aaa");
		// addArray[1] = new InternetAddress("bbb@bbb.bbb");
		// addArray[2] = new InternetAddress("ccc@ccc.ccc");
		// message.addRecipients(Message.RecipientType.TO, addArray);

		mimeMessage.setSubject("test"); // 제목
		mimeMessage.setText("pass"); // 내용

		// 평서문이 아닌 html 태그를 보내고 싶다면?
		// mimeMessage.setContent("<h1>안녕하세용?</h1>","text/html; charset=UTF-8")

		Transport.send(mimeMessage); // 전송

	}
}
