package ua.com.javajedi.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.com.javajedi.model.Answer;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
	@Query(value = "SELECT * FROM answer WHERE exercise_id=?1", nativeQuery = true)
	List<Answer> findAllByExerciseId(long exerciseId);
}
