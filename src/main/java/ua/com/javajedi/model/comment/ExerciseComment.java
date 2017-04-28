package ua.com.javajedi.model.comment;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import ua.com.javajedi.model.Exercise;
import ua.com.javajedi.model.User;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "exercise_comment")
public class ExerciseComment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "e_comment_id")
    private long eCommentId;
    @ManyToOne()
    @JoinColumn(name = "userId")
    @Fetch(value = FetchMode.JOIN)
    private User author;
    @ManyToOne()
    @JoinColumn(name = "exerciseId")
    @Fetch(value = FetchMode.JOIN)
    private Exercise exercise;
    @Temporal(value = TemporalType.DATE)
    private Date date;
    private long likes;
    @Column(columnDefinition = "text")
    private String content;

    public ExerciseComment(){}

    public long geteCommentId() {
        return eCommentId;
    }

    public void seteCommentId(long eCommentId) {
        this.eCommentId = eCommentId;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getLikes() {
        return likes;
    }

    public void setLikes(long likes) {
        this.likes = likes;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
