package ua.com.javajedi.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import ua.com.javajedi.model.Article;
import ua.com.javajedi.model.Role;
import ua.com.javajedi.model.User;
import ua.com.javajedi.model.comment.ArticleComment;
import ua.com.javajedi.service.ArticleCommentService;
import ua.com.javajedi.service.ArticleService;

import java.util.List;


@Controller
public class ArticlesController {
    private ArticleService articleService;
    private ArticleCommentService articleCommentService;

    @GetMapping(value = "/articles/all")
    @Secured({"USER", "ADMIN"})
    public ModelAndView findAllArticles(ModelAndView mav){

        mav.addObject("articles", articleService.findAll());

        if (getCurrentUser().getAuthorities().contains(Role.ADMIN)){
            mav.addObject("admin", "You are admin!");
        }

        mav.setViewName("cabinet");
        return mav;
    }

    @GetMapping(value = "/articles/unread")
    @Secured({"USER", "ADMIN"})
    public ModelAndView findAllUnread(ModelAndView mav){

        User user = getCurrentUser();

        mav.addObject("unread", articleService.findAllUnread(user.getUserId()));

        if (user.getAuthorities().contains(Role.ADMIN)){
            mav.addObject("admin", "You are admin!");
        }

        mav.setViewName("cabinet");
        return mav;
    }

    @GetMapping(value = "/articles/findByTitle")
    @Secured({"USER", "ADMIN"})
    public ModelAndView findByTitle(String title,
                                    ModelAndView mav){

        Article article = articleService.findByTitle(title, getCurrentUser());
        List<ArticleComment> comments = articleCommentService.findAllByArticleId(article.getArticleId());
        User user = getCurrentUser();

        mav.addObject("user", user);
        mav.addObject("articleByTitle", article);
        mav.addObject("articleComments", comments);

        if (user.getAuthorities().contains(Role.ADMIN)){
            mav.addObject("admin", "You are admin!");
        }

        mav.setViewName("cabinet");
        return mav;
    }

    @Autowired
    public void setArticleService(ArticleService articleService){
        this.articleService = articleService;
    }

    @Autowired
    public void setArticleCommentService(ArticleCommentService articleCommentService){
        this.articleCommentService = articleCommentService;
    }

    private User getCurrentUser(){
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
