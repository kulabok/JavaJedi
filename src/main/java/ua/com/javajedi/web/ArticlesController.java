package ua.com.javajedi.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import ua.com.javajedi.model.Article;
import ua.com.javajedi.model.Role;
import ua.com.javajedi.model.User;
import ua.com.javajedi.model.comment.ArticleComment;
import ua.com.javajedi.model.statistics.Action;
import ua.com.javajedi.model.statistics.Page;
import ua.com.javajedi.model.statistics.Statistics;
import ua.com.javajedi.service.ArticleCommentService;
import ua.com.javajedi.service.ArticleService;
import ua.com.javajedi.service.StatisticsService;

import java.util.List;

import static ua.com.javajedi.utils.SecurityUtils.getCurrentUser;


@Controller
public class ArticlesController {
	private final ArticleService articleService;
	private final ArticleCommentService articleCommentService;
	private final StatisticsService statisticsService;

	@Autowired
	public ArticlesController(final ArticleService articleService,
	                          final ArticleCommentService articleCommentService,
	                          final StatisticsService statisticsService) {
		this.articleService = articleService;
		this.articleCommentService = articleCommentService;
		this.statisticsService = statisticsService;
	}

	@GetMapping(value = "/articles/all")
	@Secured({"USER", "ADMIN"})
	public ModelAndView findAllArticles(ModelAndView mav) {
		mav.addObject("articles", articleService.findAll());
		if (getCurrentUser().getAuthorities().contains(Role.ADMIN)) {
			mav.addObject("admin", "You are admin!");
		}
		mav.setViewName("cabinet");
		statisticsService.save(Statistics.of(Page.CABINET, Action.GET_ARTICLES_ALL));
		return mav;
	}

	@GetMapping(value = "/articles/unread")
	@Secured({"USER", "ADMIN"})
	public ModelAndView findAllUnread(ModelAndView mav) {
		User user = getCurrentUser();
		mav.addObject("unread", articleService.findAllUnread(user.getUserId()));
		if (user.getAuthorities().contains(Role.ADMIN)) {
			mav.addObject("stats", statisticsService.getAllForAdmin());
		}
		mav.setViewName("cabinet");
		statisticsService.save(Statistics.of(Page.CABINET, Action.GET_ARTICLES_UNREAD));
		return mav;
	}

	@GetMapping(value = "/articles/findByTitle")
	@Secured({"USER", "ADMIN"})
	public ModelAndView findByTitle(String title,
	                                ModelAndView mav) {
		Article article = articleService.findByTitle(title, getCurrentUser());
		List<ArticleComment> comments = articleCommentService.findAllByArticleId(article.getArticleId());
		mav.addObject("user", getCurrentUser());
		mav.addObject("articleByTitle", article);
		mav.addObject("articleComments", comments);
		if (getCurrentUser().getAuthorities().contains(Role.ADMIN)) {
			mav.addObject("stats", statisticsService.getAllForAdmin());
		}
		mav.setViewName("cabinet");
		statisticsService.save(Statistics.of(Page.CABINET, Action.READ_ARTICLE));
		return mav;
	}
}
