package com.ibm.w3.service.impl;

import com.ibm.w3.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


@Service
public class MailServiceImpl implements MailService {
	
	@Value("${spring.mail.maillink}")
	private String maillink;
	  
	
    @Autowired
    private JavaMailSender mailSender;
    
    @Autowired
    private JavaMailSender javaMailSender;

    /**
         * 发送简单文本文件 for test
     */
    public void sendSimpleEmail(){
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("1220123963@qq.com");
            message.setTo("hanpxg@cn.ibm.com");
            message.setSubject("hello");
            message.setText("helloha");
            message.setCc("1220123963@qq.com");
            mailSender.send(message);

        }catch (Exception e){
        	e.printStackTrace();
            System.out.println("Send txt fail."+e);
        }
    }

    @Async
    public void sendHTMLMail(String email, String username) throws MessagingException{
    
    	MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("1220123963@qq.com");
            messageHelper.setTo(email);
            messageHelper.setSubject("Welcome to SMC system");
            System.out.println("<a href='"+ maillink + username + "'>Please click here to confirm your sign up!</a>");
            messageHelper.setText("<a href='"+ maillink + username + "'>Please click here to confirm your sign up!</a>", true);
            mailSender.send(mimeMessage);
            System.out.println("Send html success.");
    }

    public void sendNewPasswordEmail(String email, String newpassword){
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("1220123963@qq.com");
            message.setTo("1220123963@qq.com");
            message.setSubject("Your New Password to Login SMC System");
            message.setText("Your New Password >>>> "+ newpassword);
            mailSender.send(message);
    }
        
}
