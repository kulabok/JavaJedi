package ua.com.javajedi.service;

import ua.com.javajedi.model.Exercise;

import java.util.List;

public interface ExerciseService {
	Exercise add(Exercise exercise);

	Exercise update(Exercise exercise);

	Exercise delete(Exercise exercise);

	Exercise findById(long id);

	List<Exercise> findAll();

	long exercisesCount();

	Exercise findByTitle(String title);

	List<Exercise> findAllDone(long userId);

	List<Exercise> findAllUndone(long userId);
}
