package com.community.dev.controller;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
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

@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("")
	public String list(Model model) {
		model.addAttribute("users", userService.findAll());
		return "/users/list";
	}

	@GetMapping("/login")
	public String loginPage(User user) {
		return "/users/login";
	}

	@PostMapping("/login")
	public String login(String userName, String password, HttpSession httpSession) {
		User user = userService.findByUserEmail(userName);

		if (user == null || !StringUtils.equals(user.getPassword(), "")) {
			System.out.println("User name or password is wrong");
			return "redirect:/users/login";
		}

		httpSession.setAttribute("loggedInUser", user);

		return "redirect:/users";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("user");
		return "redirect:/";
	}

	@GetMapping("/createForm")
	public String createForm(User user) {
		return "/users/createForm";
	}

	@PostMapping
	public String create(User user) {
		userService.create(user);
		return "redirect:/users";
	}

	@PutMapping
	public String update(User user) {
		userService.save(user);
		return "redirect:/users";
	}

	@GetMapping("/{userId}/updateForm")
	public String updateForm(@PathVariable Long userId, Model model) {
		model.addAttribute("user", userService.findByUserId(userId));
		return "/users/updateForm";
	}

}
