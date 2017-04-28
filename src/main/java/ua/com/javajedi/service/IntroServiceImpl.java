package ua.com.javajedi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IntroServiceImpl implements IntroService {
    private ArticleService articleService;
    private ExerciseService exerciseService;
    @Override
    public long findArticlesCount() {
        return articleService.articlesCount();
    }

    @Override
    public long findExercisesCount() {
        return exerciseService.exercisesCount();
    }

    @Autowired
    public void setArticleService(ArticleService articleService){
        this.articleService = articleService;
    }

    @Autowired
    public void setExerciseService(ExerciseService exerciseService){
        this.exerciseService = exerciseService;
    }
}
