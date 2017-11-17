package ua.com.javajedi.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import ua.com.javajedi.model.like.ExerciseLike;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "exercise")
public class Exercise {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "exercise_id")
	private long exerciseId;
	@ManyToMany(mappedBy = "alreadyDone")
	private List<User> readers;
	@Enumerated(EnumType.STRING)
	private Branch branch;
	@Column(unique = true)
	private String title;
	@Column(columnDefinition = "text")
	private String content;
	@OneToMany(mappedBy = "exercise")
	private List<Answer> answers;
	@OneToOne()
	@JoinColumn(name = "exLikeId")
	@Fetch(value = FetchMode.JOIN)
	private ExerciseLike exLike;

	public Exercise() {
	}

	public long getExerciseId() {
		return exerciseId;
	}

	public void setExerciseId(long exerciseId) {
		this.exerciseId = exerciseId;
	}

	public List<User> getReaders() {
		return readers;
	}

	public void setReaders(List<User> readers) {
		this.readers = readers;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public ExerciseLike getexLike() {
		return exLike;
	}

	public void setexLike(ExerciseLike exLike) {
		this.exLike = exLike;
	}
}
