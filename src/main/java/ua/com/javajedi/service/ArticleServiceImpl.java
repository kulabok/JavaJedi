package ua.com.javajedi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.javajedi.db.ArticleRepository;
import ua.com.javajedi.model.Article;
import ua.com.javajedi.model.User;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    private ArticleRepository articleRepository;
    private UserServiceDB userService;

    @Override
    public Article add(Article article) {
        return articleRepository.saveAndFlush(article);
    }

    @Override
    public Article update(Article article) {
        return articleRepository.saveAndFlush(article);
    }

    @Override
    public Article delete(Article article) {
        articleRepository.delete(article.getArticleId());
        return article;
    }

    @Override
    public Article findById(long articleId) {
        return articleRepository.findOne(articleId);
    }

    @Override
    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    @Override
    public List<Article> findAllUnread(long userId) {
        List<Article> articles = articleRepository.findAllUnread(userId);

        if (articles == null){
            return new ArrayList<>();
        }
        return articles;
    }

    @Override
    public Article findByTitle(String title, User user) {
        Article article = articleRepository.findByTitle(title);
        List<Article> alreadyRead = findAllAlreadyRead(user.getUserId());
        alreadyRead.add(article);
        user.setAlreadyRead(alreadyRead);
        userService.update(user);

        return article;
    }

    @Override
    public List<Article> findAllAlreadyRead(long userId) {
        return articleRepository.findAllAlreadyRead(userId);
    }

    @Override
    public long articlesCount() {
        return articleRepository.articlesCount();
    }

    @Autowired
    public void setArticleRepository(ArticleRepository articleRepository){
        this.articleRepository = articleRepository;
    }

    @Autowired
    public void setUserService(UserServiceDB userService){
        this.userService = userService;
    }
}