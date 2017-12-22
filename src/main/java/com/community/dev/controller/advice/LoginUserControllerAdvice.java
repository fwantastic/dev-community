package com.community.dev.controller.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.community.dev.util.LoginUtility;

@ControllerAdvice
public class LoginUserControllerAdvice {

	@ModelAttribute("loggedInUser")
	public String getLoggedInUser() {

		return LoginUtility.getLoggedInUserEmail();

	}

}
