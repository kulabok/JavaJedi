package ua.com.javajedi.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import ua.com.javajedi.model.comment.ArticleComment;
import ua.com.javajedi.model.like.ArticleLike;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "article")
public class Article extends PersistentEntity<Long>{
	@ManyToOne()
	@JoinColumn(name = "userId")
	@Fetch(value = FetchMode.JOIN)
	private User author;
	@Temporal(value = TemporalType.DATE)
	private Date date;
	@ManyToMany(mappedBy = "alreadyRead")
	private List<User> readers;
	@Enumerated(EnumType.STRING)
	private Branch branch;
	private String title;
	@Column(columnDefinition = "text")
	private String content;
	@OneToOne()
	@JoinColumn(name = "artLikeId")
	@Fetch(value = FetchMode.JOIN)
	private ArticleLike artLike;
	@OneToMany(mappedBy = "article")
	private List<ArticleComment> comments;

	public Article() {
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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

	public ArticleLike getartLike() {
		return artLike;
	}

	public void setartLike(ArticleLike artLike) {
		this.artLike = artLike;
	}

	public List<ArticleComment> getComments() {
		return comments;
	}

	public void setComments(List<ArticleComment> comments) {
		this.comments = comments;
	}
}
