package ua.com.javajedi.model.comment;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import ua.com.javajedi.model.Article;
import ua.com.javajedi.model.PersistentEntity;
import ua.com.javajedi.model.User;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "article_comment")
public class ArticleComment extends PersistentEntity<Long> {
	@ManyToOne()
	@JoinColumn(name = "userId")
	@Fetch(value = FetchMode.JOIN)
	private User author;
	@ManyToOne()
	@JoinColumn(name = "articleId")
	@Fetch(value = FetchMode.JOIN)
	private Article article;
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date date;
	private long likes;
	@Column(columnDefinition = "text")
	private String content;

	public ArticleComment() {
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
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
