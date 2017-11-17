package ua.com.javajedi.service;

import ua.com.javajedi.model.Answer;
import ua.com.javajedi.model.User;

import java.util.List;

public interface AnswerService {
	Answer add(Answer answer);

	Answer update(Answer answer);

	Answer delete(Answer answer);

	Answer findById(Answer answer);

	List<Answer> findAll();

	List<Answer> findAllByExerciseId(long exerciseId);

	String resolveAnswer(String exerciseId, String answer, User user);
}
