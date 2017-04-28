package ua.com.javajedi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.javajedi.db.ArticleCommentRepository;
import ua.com.javajedi.model.comment.ArticleComment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class ArticleCommentServiceImpl implements ArticleCommentService {
    private ArticleCommentRepository articleCommentRepository;
    private UserServiceDB userServiceDB;
    private ArticleService articleService;

    @Autowired
    public void setArticleRepository(ArticleCommentRepository articleRepository){
        this.articleCommentRepository = articleRepository;
    }

    @Autowired
    public void setUserServiceDB(UserServiceDB userServiceDB){
        this.userServiceDB = userServiceDB;
    }

    @Autowired
    public void setArticleService(ArticleService articleService){
        this.articleService = articleService;
    }

    @Override
    public ArticleComment add(ArticleComment articleComment) {
        return articleCommentRepository.saveAndFlush(articleComment);
    }

    @Override
    public ArticleComment add(String userId, long articleId, String content) {
        ArticleComment comment = new ArticleComment();
        comment.setAuthor(userServiceDB.findById(Long.parseLong(userId)));
        comment.setArticle(articleService.findById(articleId));
        comment.setContent(content);
        comment.setDate(Calendar.getInstance().getTime());
        return articleCommentRepository.saveAndFlush(comment);
    }

    @Override
    public ArticleComment update(ArticleComment articleComment) {
        return articleCommentRepository.saveAndFlush(articleComment);
    }

    @Override
    public ArticleComment delete(long articleId) {
        articleCommentRepository.delete(articleId);
        return articleCommentRepository.findOne(articleId);
    }

    @Override
    public ArticleComment findById(ArticleComment articleComment) {
        return articleCommentRepository.findOne(articleComment.getaCommentId());
    }

    @Override
    public List<ArticleComment> findAll() {
        return articleCommentRepository.findAll();
    }

    @Override
    public List<ArticleComment> findAllByArticleId(long articleId) {
        List<ArticleComment> comments = articleCommentRepository.findAllByArticleId(articleId);

        if (comments == null){
            return new ArrayList<>();
        }

        return comments;
    }
}
