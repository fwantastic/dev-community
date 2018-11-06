package com.community.dev.service;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class EmailServiceImpl implements EmailService {

	private static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

	@Autowired
	public JavaMailSender emailSender;

	@Value("${spring.mail.username}")
	private String emailUsername;

	@Value("${spring.mail.sender}")
	private String senderEmail;

	@Override
	public void sendSimpleMessage(String subject, String message) {

		if (StringUtils.isBlank(emailUsername)) {
			logger.warn("senderEmail is null. Email will not be sent.");
			return;
		}

		SimpleMailMessage simpleMessage = new SimpleMailMessage();
		simpleMessage.setTo(senderEmail);
		simpleMessage.setSubject(subject);
		simpleMessage.setText(message);
		emailSender.send(simpleMessage);
	}

}
