package ua.com.javajedi.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.javajedi.db.StatisticsRepository;
import ua.com.javajedi.model.statistics.Statistics;
import ua.com.javajedi.service.StatisticsService;

import java.time.LocalDate;
import java.util.List;

@Service
public class StatisticsServiceImpl implements StatisticsService {
	private final StatisticsRepository statisticsRepository;

	@Autowired
	public StatisticsServiceImpl(StatisticsRepository statisticsRepository) {
		this.statisticsRepository = statisticsRepository;
	}

	@Override
	public boolean save(Statistics statistics) {
		LocalDate now = LocalDate.now();
		if (statistics.getDate().isBefore(now.minusDays(1L))){
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

	@Override
	public List<Statistics> getAllForAdmin() {
		return statisticsRepository.findByDate(LocalDate.now());
	}
}
