package ua.com.javajedi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.javajedi.db.ExerciseRepository;
import ua.com.javajedi.model.Exercise;

import java.util.List;

@Service
public class ExerciseServiceImpl implements ExerciseService {
	private final ExerciseRepository exerciseRepository;

	@Autowired
	public ExerciseServiceImpl(final ExerciseRepository exerciseRepository) {
		this.exerciseRepository = exerciseRepository;
	}

	@Override
	public Exercise add(Exercise exercise) {
		return exerciseRepository.saveAndFlush(exercise);
	}

	@Override
	public Exercise update(Exercise exercise) {
		return exerciseRepository.saveAndFlush(exercise);
	}

	@Override
	public Exercise delete(Exercise exercise) {
		exerciseRepository.delete(exercise);
		return exercise;
	}

	@Override
	public Exercise findById(long id) {
		return exerciseRepository.findOne(id);
	}

	@Override
	public List<Exercise> findAll() {
		return exerciseRepository.findAll();
	}

	@Override
	public long exercisesCount() {
		return exerciseRepository.exercisesCount();
	}

	@Override
	public Exercise findByTitle(String title) {
		return exerciseRepository.findByTitle(title);
	}

	@Override
	public List<Exercise> findAllDone(long userId) {
		return exerciseRepository.findAllDone(userId);
	}

	@Override
	public List<Exercise> findAllUndone(long userId) {
		return exerciseRepository.findAllUndone(userId);
	}

}
