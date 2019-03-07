package ua.com.javajedi.model.statistics;

import ua.com.javajedi.model.PersistentEntity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "statistics")
public class Statistics extends PersistentEntity<Long> {
	private LocalDate date;
	private long visitors;
	@Enumerated(EnumType.STRING)
	private Page page;
	@Enumerated(EnumType.STRING)
	private Action action;

	public Statistics() {
	}

	public static Statistics of(Page page, Action action) {
		Statistics stat = new Statistics();
		stat.setDate(LocalDate.now());
		stat.setPage(page);
		stat.setAction(action);
		return stat;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public long getVisitors() {
		return visitors;
	}

	public void setVisitors(long visitors) {
		this.visitors = visitors;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	@Override
	public String toString() {
		return "{" +
			"\"statId\":\"" + super.getId() +
			"\", \"date\":\"" + date +
			"\", \"visitors\":\"" + visitors +
			"\", \"page\":\"" + page.name() +
			"\", \"action\":\"" + action.name() +
			"\"}";
	}
}
