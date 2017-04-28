package ua.com.javajedi.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.javajedi.model.comment.ExerciseComment;

@Repository
public interface ExerciseCommentRepository extends JpaRepository<ExerciseComment,Long>{
}
