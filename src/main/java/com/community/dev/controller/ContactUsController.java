package com.community.dev.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.community.dev.business.ContactUs;
import com.community.dev.service.EmailService;

@Controller
@RequestMapping
public class ContactUsController {

	@Autowired
	private EmailService emailService;

	@GetMapping("/contactus")
	public String contactus() {
		return "/contactus/contactus";
	}

	@PostMapping("/contactus")
	public String sendEmail(ContactUs contactUs) {
		if (StringUtils.isNoneBlank(contactUs.getEmail(), contactUs.getSubject(), contactUs.getMessage())) {
			emailService.sendSimpleMessage(contactUs.getEmail() + "-" + contactUs.getSubject(), contactUs.getMessage());
		}
		return "/contactus/contactusSuccess";
	}

}
