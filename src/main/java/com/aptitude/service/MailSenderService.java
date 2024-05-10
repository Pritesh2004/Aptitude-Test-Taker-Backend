package com.aptitude.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSenderService {

	@Autowired
	public JavaMailSender mailSender;
	
	public void sendEmail(String toEmail, String subject, String body) {
		
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("patilpritesh7277@gmail.com");
		message.setTo(toEmail);
		message.setSubject(subject);
		message.setText(body);
		
		mailSender.send(message);
		
		System.out.print("Message send successfully");
	}
}
