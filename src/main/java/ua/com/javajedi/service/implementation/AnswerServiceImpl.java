package ua.com.javajedi.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.javajedi.db.AnswerRepository;
import ua.com.javajedi.model.Answer;
import ua.com.javajedi.model.Exercise;
import ua.com.javajedi.model.User;
import ua.com.javajedi.service.AnswerService;
import ua.com.javajedi.service.ExerciseService;
import ua.com.javajedi.service.UserServiceDB;

import java.util.List;
import java.util.Objects;

@Service
public class AnswerServiceImpl implements AnswerService {
	private final AnswerRepository answerRepository;
	private final ExerciseService exerciseService;
	private final UserServiceDB userService;

	@Autowired
	public AnswerServiceImpl(final AnswerRepository answerRepository,
	                         final ExerciseService exerciseService,
	                         final UserServiceDB userService) {
		this.answerRepository = answerRepository;
		this.exerciseService = exerciseService;
		this.userService = userService;
	}

	@Override
	public Answer add(Answer answer) {
		return answerRepository.saveAndFlush(answer);
	}

	@Override
	public Answer update(Answer answer) {
		return answerRepository.saveAndFlush(answer);
	}

	@Override
	public Answer delete(Answer answer) {
		answerRepository.delete(answer);
		return answer;
	}

	@Override
	public Answer findById(Answer answer) {
		return answerRepository.findById(answer.getId()).get();
	}

	@Override
	public List<Answer> findAll() {
		return answerRepository.findAll();
	}

	@Override
	public List<Answer> findAllByExerciseId(long exerciseId) {
		return answerRepository.findAllByExerciseId(exerciseId);
	}

	@Override
	public String resolveAnswer(String exerciseId, String answer, User user) {
		long id = Long.parseLong(exerciseId);
		List<Answer> answers = answerRepository.findAllByExerciseId(id);
		for (Answer a : answers) {
			if (a.isCorrect() && Objects.equals(a.getContent(), answer)) {
				List<Exercise> done = exerciseService.findAllDone(user.getId());
				done.add(exerciseService.findById(id));
				user.setAlreadyDone(done);
				userService.update(user);
				return "Correct!";
			}
		}
		return "No! Study harder!";
	}
}
