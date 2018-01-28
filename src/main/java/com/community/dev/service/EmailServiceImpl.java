package com.community.dev.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class EmailServiceImpl implements EmailService {

	@Autowired
	public JavaMailSender emailSender;

	@Value("${spring.mail.sender}")
	private String senderEmail;

	@Override
	public void sendSimpleMessage(String subject, String message) {
		SimpleMailMessage simpleMessage = new SimpleMailMessage();
		simpleMessage.setTo(senderEmail);
		simpleMessage.setSubject(subject);
		simpleMessage.setText(message);
		emailSender.send(simpleMessage);
	}

}
