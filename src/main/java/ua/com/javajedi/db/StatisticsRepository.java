package ua.com.javajedi.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.javajedi.model.statistics.Action;
import ua.com.javajedi.model.statistics.Page;
import ua.com.javajedi.model.statistics.Statistics;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface StatisticsRepository extends JpaRepository<Statistics, Long> {
	Statistics findByPageAndActionAndDate(Page page, Action action, LocalDate date);
	List<Statistics> findByDate(Date date);
}
