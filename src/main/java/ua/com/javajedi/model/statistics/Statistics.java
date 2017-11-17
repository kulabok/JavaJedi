package ua.com.javajedi.model.statistics;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "statistics")
public class Statistics {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "stat_id")
	private long statId;
	private Date date;
	private long visitors;
	@Enumerated(EnumType.STRING)
	private Page page;
	@Enumerated(EnumType.STRING)
	private Action action;

	public Statistics() {
	}

	public long getStatId() {
		return statId;
	}

	public void setStatId(long statId) {
		this.statId = statId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
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
			"\"statId\":\"" + statId +
			"\", \"date\":\"" + date +
			"\", \"visitors\":\"" + visitors +
			"\", \"page\":\"" + page.name() +
			"\", \"action\":\"" + action.name() +
			"\"}";
	}
}
