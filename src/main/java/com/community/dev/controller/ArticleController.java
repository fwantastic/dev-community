package com.community.dev.controller;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
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
import com.community.dev.util.PageWrapper;

@Controller
@RequestMapping("/articles")
public class ArticleController {

	private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);

	@Autowired
	private ArticleService articleService;

	@Autowired
	private UserService userService;

	@GetMapping
	public String list(Model model,
			@PageableDefault(sort = { "articleId" }, direction = Direction.DESC, size = 20) Pageable pageable) {
		// Page<Article> articles = articleService.findAll(pageable);
		// articles.getcon
		// model.addAttribute("articles", articles.getContent());
		// // articles.get
		// model.addAttribute("currentPageNumber", articles.getNumber());
		// model.addAttribute("totalPages", articles.getTotalPages());
		// model.addAttribute("pageSize", articles.getSize());

		PageWrapper<Article> page = new PageWrapper<Article>(articleService.findAll(pageable), "/articles");
		model.addAttribute("page", page);

		// pageable.page
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
			return "redirect:/users/login";
		}

		article.setAuthor(user);
		articleService.save(article);

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
		Article article = articleService.findByArticleId(articleId);
		model.addAttribute("article", article);

		if (article.getAuthor().getUserEmail().equals(LoginUtility.getLoggedInUserEmail())) {
			model.addAttribute("allowEdit", true);
		}

		return "/articles/form";
	}

}
