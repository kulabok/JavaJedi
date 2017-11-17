package ua.com.javajedi.service;

import ua.com.javajedi.model.statistics.Statistics;

import java.util.List;

public interface StatisticsService {
	boolean save(Statistics statistics);

	List<Statistics> getAllForAdmin();
}
