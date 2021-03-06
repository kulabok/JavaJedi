package ua.com.javajedi.model.like;

import ua.com.javajedi.model.Article;
import ua.com.javajedi.model.PersistentEntity;
import ua.com.javajedi.model.User;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "art_like")
public class ArticleLike extends PersistentEntity<Long> {
	@OneToOne(mappedBy = "artLike")
	private Article article;
	@ManyToMany(mappedBy = "articLike")
	private List<User> users;
	private long value;

	public ArticleLike() {
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
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
