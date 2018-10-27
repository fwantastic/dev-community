package com.community.dev.controller;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class StatusController {

	@GetMapping("/status")
	public String contactus() throws JSONException {

		JSONObject status = new JSONObject();
		status.put("STATUS", "OK");

		return status.toString();
	}

}
