package ua.com.javajedi.service;

import ua.com.javajedi.model.Article;
import ua.com.javajedi.model.User;

import java.util.List;

public interface ArticleService {
    Article add(Article article);
    Article update(Article article);
    Article delete(Article article);
    Article findById(long atricleId);
    List<Article> findAll();
    long articlesCount();
    Article findByTitle(String title, User user);
    List<Article> findAllUnread(long userId);
    List<Article> findAllAlreadyRead(long userId);
}
