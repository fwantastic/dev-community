package com.community.dev.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.community.dev.constants.UrlConstants;
import com.community.dev.persistence.User;
import com.community.dev.util.LoginUtility;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@GetMapping
	public String adminDefault() {

		if (LoginUtility.getLoggedInUserEmail() == null) {
			return UrlConstants.LOGIN;
		}

		return "admin/list";
	}

	@GetMapping("/login")
	public String login() {
		return "admin/login";
	}

}
