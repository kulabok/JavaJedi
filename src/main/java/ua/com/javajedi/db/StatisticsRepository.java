package ua.com.javajedi.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.javajedi.model.statistics.Action;
import ua.com.javajedi.model.statistics.Page;
import ua.com.javajedi.model.statistics.Statistics;

import java.sql.Date;
import java.util.List;

@Repository
public interface StatisticsRepository extends JpaRepository<Statistics, Long> {
	Statistics findByPageAndActionAndDate(Page page, Action action, Date date);
	Long countByDate(Date date);
	Long countByPage(Page page);
	Long countByPageAndDate(Page page, Date date);
	Long countByAction(Action action);
	Long countByActionAndDate(Action action, Date date);
}
