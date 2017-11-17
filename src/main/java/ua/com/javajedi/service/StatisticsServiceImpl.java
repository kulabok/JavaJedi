package ua.com.javajedi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.javajedi.db.StatisticsRepository;
import ua.com.javajedi.model.statistics.Action;
import ua.com.javajedi.model.statistics.Page;
import ua.com.javajedi.model.statistics.Statistics;

import java.sql.Date;
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
		statisticsRepository.saveAndFlush(statistics);
		return true;
	}

	@Override
	public List<Statistics> getAllStatistics() {
		return statisticsRepository.findAll();
	}

	@Override
	public String getStatisticsByDate(Date date) {
		return "There was " + statisticsRepository.countByDate(date) + " actions " + date.toString();
	}

	@Override
	public String getStatisticsByPage(Page page) {
		return "There was " + statisticsRepository.countByPage(page) + " actions on page " + page.name() + " since the project is running.";
	}

	@Override
	public String getStatisticsByPageAndDate(Page page, Date date) {
		return "There was " + statisticsRepository.countByPageAndDate(page, date) + " actions on page " + page.name() + " " + date.toString();
	}

	@Override
	public String getStatisticsByAction(Action action) {
		return "There was " + statisticsRepository.countByAction(action) + " " + action.name() + " since the project is running.";
	}

	@Override
	public String getStatisticsByActionAndDate(Action action, Date date) {
		return "There was " + statisticsRepository.countByActionAndDate(action, date) + " " + action.name() + " " + date.toString();
	}
}
