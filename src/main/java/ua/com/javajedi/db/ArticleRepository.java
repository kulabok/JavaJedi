package ua.com.javajedi.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.com.javajedi.model.Article;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
	@Query(value =
		"SELECT * FROM article WHERE NOT id IN " +
			"(SELECT already_read_id FROM users_already_read WHERE readers_id=?1)", nativeQuery = true)
	List<Article> findAllUnread(@Param("userId") long userId);

	Article findByTitle(String title);

	@Query(value = "SELECT COUNT(*) FROM article", nativeQuery = true)
	long articlesCount();

	@Query(value =
		"SELECT * FROM article AS l LEFT JOIN users_already_read AS r ON " +
			"l.id = r.already_read_id WHERE r.readers_id=?1 GROUP BY l.id", nativeQuery = true)
	List<Article> findAllAlreadyRead(@Param("userId") long userId);
}
