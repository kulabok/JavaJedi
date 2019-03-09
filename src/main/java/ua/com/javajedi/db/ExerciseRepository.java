package ua.com.javajedi.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.com.javajedi.model.Exercise;

import java.util.List;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
	@Query(value = "SELECT COUNT(*) FROM exercise", nativeQuery = true)
	long exercisesCount();

	Exercise findByTitle(String title);

	@Query(value = "SELECT * FROM exercise WHERE NOT id IN " +
		"(SELECT already_done_id FROM users_already_done WHERE readers_id = ?1) ", nativeQuery = true)
	List<Exercise> findAllUndone(long userId);

	@Query(value = "SELECT * FROM exercise as e left join users_already_done as r ON " +
		"e.id = r.already_done_id WHERE readers_id = ?1", nativeQuery = true)
	List<Exercise> findAllDone(long userId);


}
