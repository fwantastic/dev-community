package com.community.dev.controller;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.community.dev.persistence.User;
import com.community.dev.service.UserService;
import com.community.dev.util.LoginUtility;

@Controller
@RequestMapping("/users")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@GetMapping
	public String list(Model model) {

		model.addAttribute("users", userService.findAll());

		return "users/list";

	}

	@GetMapping("/createForm")
	public String createForm(User user) {

		return "users/createForm";

	}

	@PostMapping
	public String create(User user) {

		userService.create(user);

		return "redirect:/users";

	}

	@GetMapping("/update")
	public String update() {

		User user = userService.findByUserEmail(LoginUtility.getLoggedInUserEmail());

		if (user == null) {
			logger.info("user not found: " + LoginUtility.getLoggedInUserEmail());
			return "users/login";
		}

		return "redirect:/users/" + user.getUserId() + "/updateForm";

	}

	@GetMapping("/{userId}/updateForm")
	public String updateForm(@PathVariable Long userId, Model model) {

		model.addAttribute("user", userService.findByUserId(userId));

		return "users/updateForm";

	}

	@PutMapping
	public String update(User user) {

		User existingUser = userService.findByUserId(user.getUserId());

		Validate.notNull(existingUser, "User not found");

		existingUser.setUserNickname(user.getUserNickname());
		existingUser.setPassword(user.getPassword());
		existingUser.setIsActive(user.getIsActive());

		userService.save(existingUser);

		return "redirect:/users";

	}

	@GetMapping("/{userId}/active")
	public String active(@PathVariable Long userId) {

		makeUserActive(userId, true);

		return "redirect:/users/{userId}/updateForm";

	}

	@GetMapping("/{userId}/inactive")
	public String inactive(@PathVariable Long userId) {

		makeUserActive(userId, false);

		return "redirect:/users/{userId}/updateForm";

	}

	private void makeUserActive(Long userId, boolean activeState) {

		User existingUser = userService.findByUserId(userId);

		Validate.notNull(existingUser, "User not found");

		existingUser.setIsActive(activeState);
		userService.save(existingUser);

	}

}
