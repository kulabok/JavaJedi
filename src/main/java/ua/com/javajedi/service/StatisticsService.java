package ua.com.javajedi.service;

import ua.com.javajedi.model.statistics.Action;
import ua.com.javajedi.model.statistics.Page;
import ua.com.javajedi.model.statistics.Statistics;

import java.sql.Date;
import java.util.List;

public interface StatisticsService {
	boolean save(Statistics statistics);
	List<Statistics> getAllStatistics();
	String getStatisticsByDate(Date date);
	String getStatisticsByPage(Page page);
	String getStatisticsByPageAndDate(Page page, Date date);
	String getStatisticsByAction(Action action);
	String getStatisticsByActionAndDate(Action action, Date date);
}
