package com.community.dev.controller.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.community.dev.service.EmailService;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);

	@Autowired
	private EmailService emailService;

	@ExceptionHandler(value = { Exception.class })
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<Object> unknownException(Exception ex) {
		logger.error(ex.getCause().toString());

		emailService.sendSimpleMessage("ERROR-" + ex.getMessage(), ex.getStackTrace().toString());

		return new ResponseEntity<>("ERROR", HttpStatus.NOT_FOUND);
	}

}
