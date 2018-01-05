package com.community.dev.controller;

import java.util.Date;

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

import com.community.dev.persistence.Article;
import com.community.dev.persistence.User;
import com.community.dev.service.ArticleService;
import com.community.dev.service.UserService;
import com.community.dev.util.LoginUtility;

@Controller
@RequestMapping("/articles")
public class ArticleController {

	private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);

	@Autowired
	private ArticleService articleService;

	@Autowired
	private UserService userService;

	@GetMapping
	public String list(Model model) {
		model.addAttribute("articles", articleService.findAll());
		return "/articles/list";
	}

	@GetMapping("/createForm")
	public String createForm(Article article) {

		if (LoginUtility.getLoggedInUserEmail() == null) {
			return "redirect:/users/login";
		}

		return "/articles/createForm";
	}

	@PostMapping
	public String create(Article article) {

		User user = userService.findByUserEmail(LoginUtility.getLoggedInUserEmail());

		if (user == null) {
			logger.info("user not found: " + LoginUtility.getLoggedInUserEmail());
			return "/users/login";
		}

		article.setAuthor(user);
		article.setCreateDatetime(new Date());
		articleService.create(article);

		return "redirect:/articles";
	}

	@PutMapping
	public String update(Article article) {
		articleService.save(article);
		return "redirect:/articles";
	}

	@GetMapping("/{articleId}/updateForm")
	public String updateForm(@PathVariable Long articleId, Model model) {
		model.addAttribute("user", articleService.findByArticleId(articleId));
		return "/articles/updateForm";
	}

	@GetMapping("/{articleId}")
	public String form(@PathVariable Long articleId, Model model) {
		model.addAttribute("article", articleService.findByArticleId(articleId));
		return "/articles/form";
	}

}
