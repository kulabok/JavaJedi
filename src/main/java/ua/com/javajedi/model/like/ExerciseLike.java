package ua.com.javajedi.model.like;

import ua.com.javajedi.model.Exercise;
import ua.com.javajedi.model.PersistentEntity;
import ua.com.javajedi.model.User;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ex_like")
public class ExerciseLike extends PersistentEntity<Long> {
	@OneToOne(mappedBy = "exLike")
	private Exercise exercise;
	@ManyToMany(mappedBy = "exeLike")
	private List<User> users;
	private long value;

	public ExerciseLike() {
	}

	public Exercise getExercise() {
		return exercise;
	}

	public void setExercise(Exercise exercise) {
		this.exercise = exercise;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public long getValue() {
		return value;
	}

	public void setValue(long value) {
		this.value = value;
	}
}
