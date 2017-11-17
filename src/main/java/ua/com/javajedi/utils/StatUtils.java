package ua.com.javajedi.utils;

import ua.com.javajedi.model.statistics.Action;
import ua.com.javajedi.model.statistics.Page;
import ua.com.javajedi.model.statistics.Statistics;

import java.sql.Date;
import java.time.LocalDate;

public final class StatUtils {

	public static Statistics createStatistics(Page page, Action action) {
		Statistics stat = new Statistics();
		stat.setDate(Date.valueOf(LocalDate.now()));
		stat.setPage(page);
		stat.setAction(action);
		return stat;
	}
}
