package ua.com.javajedi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.javajedi.db.StatisticsRepository;
import ua.com.javajedi.model.statistics.Statistics;

import java.sql.Date;
import java.time.LocalDate;

@Service
public class StatisticsServiceImpl implements StatisticsService {
	private final StatisticsRepository statisticsRepository;

	@Autowired
	public StatisticsServiceImpl(StatisticsRepository statisticsRepository) {
		this.statisticsRepository = statisticsRepository;
	}

	@Override
	public boolean save(Statistics statistics) {
		Date now = Date.valueOf(LocalDate.now());
		if (statistics.getDate().before(Date.valueOf(LocalDate.of(now.getYear(), now.getMonth(), now.getDay() - 1)))){
			return false;
		}
		Statistics old = statisticsRepository.findByPageAndActionAndDate(statistics.getPage(), statistics.getAction(), statistics.getDate());
		if (old != null) {
			old.setVisitors(old.getVisitors() + 1);
			statisticsRepository.saveAndFlush(old);
			return true;
		}
		statistics.setVisitors(1);
		statisticsRepository.saveAndFlush(statistics);
		return true;
	}
}
