package ua.com.javajedi.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.com.javajedi.model.comment.ArticleComment;

import java.util.List;

@Repository
public interface ArticleCommentRepository extends JpaRepository<ArticleComment, Long>{

    @Query(value = "SELECT * FROM article_comment WHERE article_id = ?1", nativeQuery = true)
    List<ArticleComment> findAllByArticleId(@Param("articleId") long articleId);
}
