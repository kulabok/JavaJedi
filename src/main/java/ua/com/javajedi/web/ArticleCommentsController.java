package ua.com.javajedi.web;

import org.springframework.beans.factory.annotation.Autowired;
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
import ua.com.javajedi.service.ArticleCommentService;
import ua.com.javajedi.service.ArticleService;

import java.util.List;

@Controller
public class ArticleCommentsController {
    private ArticleService articleService;
    private ArticleCommentService articleCommentService;

    @PostMapping(value = "/comments/addComment")
    public ModelAndView addComment(String userId,
                                   String articleId,
                                   String content,
                                   ModelAndView mav){

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        long id = Long.parseLong(articleId);
        articleCommentService.add(userId, id, content);
        Article article = articleService.findById(id);
        List<ArticleComment> comments = articleCommentService.findAllByArticleId(id);

        mav.addObject("user", user);
        mav.addObject("articleByTitle", article);
        mav.addObject("articleComments", comments);

        if (user.getAuthorities().contains(Role.ADMIN)){
            mav.setViewName("adminCabinet");
            return mav;
        }
        mav.setViewName("cabinet");
        return mav;

    }

    @Autowired
    public void setArticleCommentService(ArticleCommentService articleCommentService){
        this.articleCommentService = articleCommentService;
    }

    @Autowired
    public void setArticleService(ArticleService articleService){
        this.articleService = articleService;
    }
}
