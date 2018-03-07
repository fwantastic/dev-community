package com.community.dev.controller.advice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.community.dev.persistence.Tag;
import com.community.dev.service.TagService;
import com.community.dev.util.LoginUtility;

@ControllerAdvice
public class LoginUserControllerAdvice {

	@Autowired
	private TagService tagService;

	@Value("${google.analytics.tracking.id}")
	private String googleAnalyticsTrackingId;

	@ModelAttribute("loggedInUser")
	public String getLoggedInUser() {
		return LoginUtility.getLoggedInUserEmail();
	}

	@ModelAttribute("allTags")
	public List<Tag> getTags() {
		return tagService.findAllByOrderByTagName();
	}

	@ModelAttribute("googleAnalyticsTrackingId")
	public String getGoogleAnalyticsTrackingId() {
		return googleAnalyticsTrackingId;
	}

}
