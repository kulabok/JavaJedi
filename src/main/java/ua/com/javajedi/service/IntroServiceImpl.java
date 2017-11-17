package ua.com.javajedi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IntroServiceImpl implements IntroService {
	private final ArticleService articleService;
	private final ExerciseService exerciseService;

	@Autowired
	public IntroServiceImpl(final ArticleService articleService, final ExerciseService exerciseService) {
		this.articleService = articleService;
		this.exerciseService = exerciseService;
	}

	@Override
	public long findArticlesCount() {
		return articleService.articlesCount();
	}

	@Override
	public long findExercisesCount() {
		return exerciseService.exercisesCount();
	}

}
