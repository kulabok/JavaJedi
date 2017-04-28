package ua.com.javajedi.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.javajedi.model.Statistics;

@Repository
public interface StatisticsRepository extends JpaRepository<Statistics,Long>{
}
