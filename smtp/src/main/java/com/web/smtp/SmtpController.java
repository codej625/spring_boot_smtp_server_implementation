package com.web.smtp;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SmtpController {

	@ResponseBody
	@PostMapping("/contact")
	public String mailSender() throws AddressException, MessagingException{
		
	    // naver smtp server를 사용한다.
	    String host = "smtp.naver.com";
	    
	    // naver smtp port
	    int port=465;
	    
	    // 발신자의 메일 주소
	    String username = "zzz@naver.com";  
	    
	    // 발신자의 PASSWORD
	    final String password = "비밀번호";  
	   
	    // 수신자의 메일 주소
	    String recipient = "xxx@xxx.xxx";
	    
	    // 수신자에게 보낼 메일 제목
	    String subject = "제목"; 
	    
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
	    
	    //Session 생성 & 발신자 smtp 서버 로그인 인증 
	    Session session = Session.getInstance(props,  new javax.mail.Authenticator() { 
	    	protected javax.mail.PasswordAuthentication getPasswordAuthentication() {  
	    	return new javax.mail.PasswordAuthentication(username, password);  
	    	}  
	    });  
	    
	    session.setDebug(true); // 디버그 모드 
	    
	    //MimeMessage 생성 & 메일 세팅
	    Message mimeMessage = new MimeMessage(session); 
	    mimeMessage.setFrom(new InternetAddress(username)); // 발신자
	    mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient)); // 수신자
	    
	    // 여러사람에게 메일을 보내고 싶을때?
	    // InternetAddress[] addArray = new InternetAddress[3]; 
	    // addArray[0] = new InternetAddress("aaa@aaa.aaa"); 
	    // addArray[1] = new InternetAddress("bbb@bbb.bbb"); 
	    // addArray[2] = new InternetAddress("ccc@ccc.ccc"); 
	    // message.addRecipients(Message.RecipientType.TO, addArray);


	    mimeMessage.setSubject(subject); // 제목  
	    mimeMessage.setText(body); // 내용  
	    
	    // 평서문이 아닌 html 태그를 보내고 싶다면?
	    // mimeMessage.setContent("<h1>안녕하세용?</h1>","text/html; charset=UTF-8")
	    
	    Transport.send(mimeMessage); // 전송

	    return "index";

}
