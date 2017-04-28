package ua.com.javajedi.service;

import ua.com.javajedi.model.comment.ArticleComment;

import java.util.List;

public interface ArticleCommentService {
    ArticleComment add(ArticleComment articleComment);
    ArticleComment add(String userId, long articleId, String content);
    ArticleComment update(ArticleComment articleComment);
    ArticleComment delete(long articleId);
    ArticleComment findById(ArticleComment articleComment);
    List<ArticleComment> findAll();

    List<ArticleComment> findAllByArticleId(long articleId);
}
