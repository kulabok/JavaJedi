package ua.com.javajedi.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

@Entity
@Table(name = "answer")
public class Answer extends PersistentEntity<Long>{
	@ManyToOne()
	@JoinColumn(name = "exerciseId")
	@Fetch(value = FetchMode.JOIN)
	private Exercise exercise;
	private String content;
	private boolean isCorrect;

	public Answer() {
	}

	public Exercise getExercise() {
		return exercise;
	}

	public void setExercise(Exercise exercise) {
		this.exercise = exercise;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isCorrect() {
		return isCorrect;
	}

	public void setCorrect(boolean correct) {
		isCorrect = correct;
	}
}
