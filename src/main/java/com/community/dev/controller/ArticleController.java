package com.community.dev.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.community.dev.constants.UrlConstants;
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

		PageWrapper<Article> page = new PageWrapper<Article>(articleService.findAll(pageable), "/articles");
		model.addAttribute("page", page);

		return "/articles/list";
	}

	@GetMapping("/createForm")
	public String createForm(Article article) {
		if (LoginUtility.getLoggedInUserEmail() == null) {
			return UrlConstants.LOGIN;
		}

		return "/articles/createForm";
	}

	@PostMapping
	public String create(Article article) {
		User user = userService.findByUserEmail(LoginUtility.getLoggedInUserEmail());

		if (user == null) {
			logger.info("user not found: " + LoginUtility.getLoggedInUserEmail());
			return UrlConstants.LOGIN;
		}

		article.setCreateUser(user);
		articleService.save(article);

		return "redirect:/articles";
	}

	@PutMapping
	public String update(Article article) {
		User user = userService.findByUserEmail(LoginUtility.getLoggedInUserEmail());

		if (user == null) {
			logger.info("user not found: " + LoginUtility.getLoggedInUserEmail());
			return UrlConstants.LOGIN;
		}

		Article oldArticle = articleService.findByArticleId(article.getArticleId());

		if (oldArticle == null) {
			logger.info("articleId=[{}] not found.", article.getArticleId());
			return "redirect:/articles";
		} else if (oldArticle.getCreateUser().getUserId().longValue() != user.getUserId().longValue()) {
			logger.info("userId=[{}] cannot modify the articleId=[{}].", user.getUserId(), article.getArticleId());
			return "redirect:/articles";
		}

		oldArticle.setTitle(article.getTitle());
		oldArticle.setContents(article.getContents());
		oldArticle.setTagsArray(article.getTagsArray());

		oldArticle.setUpdateUser(user);

		articleService.save(oldArticle);
		return "redirect:/articles/" + oldArticle.getArticleId();
	}

	@GetMapping("/{articleId}/updateForm")
	public String updateForm(@PathVariable Long articleId, Model model) {
		model.addAttribute("article", articleService.findByArticleId(articleId));
		return "/articles/updateForm";
	}

	@GetMapping("/{articleId}")
	public String form(@PathVariable Long articleId, Model model) {
		Article article = articleService.findByArticleId(articleId);
		model.addAttribute("article", article);

		if (article.getCreateUser().getUserEmail().equals(LoginUtility.getLoggedInUserEmail())) {
			model.addAttribute("allowEdit", true);
		}

		return "/articles/form";
	}

	@GetMapping("tags/{tagIds}")
	public String listByTags(Model model, @PathVariable List<Long> tagIds,
			@PageableDefault(sort = { "articleId" }, direction = Direction.DESC, size = 20) Pageable pageable) {

		PageWrapper<Article> page = new PageWrapper<Article>(articleService.findByTags_TagIdIn(tagIds, pageable),
				"/articles");
		model.addAttribute("page", page);

		return "/articles/list";
	}

}
