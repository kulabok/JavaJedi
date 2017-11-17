package ua.com.javajedi.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import ua.com.javajedi.model.Article;
import ua.com.javajedi.model.Role;
import ua.com.javajedi.model.User;
import ua.com.javajedi.model.comment.ArticleComment;
import ua.com.javajedi.model.statistics.Action;
import ua.com.javajedi.model.statistics.Page;
import ua.com.javajedi.service.ArticleCommentService;
import ua.com.javajedi.service.ArticleService;
import ua.com.javajedi.service.StatisticsService;
import ua.com.javajedi.utils.StatUtils;

import java.util.List;

@Controller
public class ArticleCommentsController {
	private final ArticleService articleService;
	private final ArticleCommentService articleCommentService;
	private final StatisticsService statisticsService;

	@Autowired
	public ArticleCommentsController(final ArticleCommentService articleCommentService,
	                                 final ArticleService articleService,
	                                 final StatisticsService statisticsService) {
		this.articleCommentService = articleCommentService;
		this.articleService = articleService;
		this.statisticsService = statisticsService;
	}

	@PostMapping(value = "/comments/addComment")
	@Secured({"USER", "ADMIN"})
	public ModelAndView addComment(String userId,
	                               String articleId,
	                               String content,
	                               ModelAndView mav) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		long id = Long.parseLong(articleId);
		articleCommentService.add(userId, id, content);
		Article article = articleService.findById(id);
		List<ArticleComment> comments = articleCommentService.findAllByArticleId(id);
		mav.addObject("user", user);
		mav.addObject("articleByTitle", article);
		mav.addObject("articleComments", comments);
		if (user.getAuthorities().contains(Role.ADMIN)) {
			mav.addObject("admin", "You are admin!");
		}
		mav.setViewName("cabinet");
		statisticsService.save(StatUtils.createStatistics(Page.CABINET, Action.ADD_COMMENT));
		return mav;
	}

}
