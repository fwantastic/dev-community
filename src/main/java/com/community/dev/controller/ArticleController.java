package com.community.dev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.community.dev.persistence.Article;
import com.community.dev.service.ArticleService;

@Controller
@RequestMapping("/articles")
public class ArticleController {

	@Autowired
	private ArticleService articleService;

	@GetMapping("")
	public String list(Model model) {
		model.addAttribute("articles", articleService.findAll());
		return "/articles/list";
	}

	@GetMapping("/createForm")
	public String createForm(Article article) {
		return "/articles/createForm";
	}

	@PostMapping
	public String create(Article article) {
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

}
