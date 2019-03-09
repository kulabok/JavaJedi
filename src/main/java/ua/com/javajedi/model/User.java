package ua.com.javajedi.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.security.core.userdetails.UserDetails;
import ua.com.javajedi.model.comment.ArticleComment;
import ua.com.javajedi.model.like.ExerciseLike;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
public class User extends PersistentEntity<Long> implements UserDetails {
	@Column(unique = true)
	private String email;

	@Column(unique = true)
	private String username;
	private String password;
	@ElementCollection(targetClass = Role.class)
	@JoinTable(name = "roles", joinColumns = @JoinColumn(name = "userId"))
	@Column(name = "role", nullable = false)
	@Fetch(FetchMode.JOIN)
	@Enumerated(EnumType.STRING)
	private List<Role> authorities;
	@Column(name = "account_non_expired")
	private boolean accountNonExpired;
	@Column(name = "account_non_locked")
	private boolean accountNonLocked;
	@Column(name = "credentials_non_expired")
	private boolean credentialsNonExpired;
	private boolean enabled;

	@Column(name = "about_me", columnDefinition = "text")
	private String aboutMe;
	@Temporal(value = TemporalType.DATE)
	private Date registrationDate;
	@ManyToMany()
	@JoinColumn(name = "articleId")
	private List<Article> alreadyRead;
	@ManyToMany()
	@JoinColumn(name = "exerciseId")
	private List<Exercise> alreadyDone;
	@ManyToMany(mappedBy = "author")
	private List<Article> myArticles;
	@Enumerated(EnumType.STRING)
	private Rank rank;
	private long score;
	@OneToMany(mappedBy = "author")
	private List<ArticleComment> articleComments;
	@ManyToMany()
	@JoinColumn(name = "exLikeId")
	private List<ExerciseLike> exeLike;
	@ManyToMany()
	@JoinColumn(name = "artLikeId")
	private List<ExerciseLike> articLike;

	public User() {
	}

	public User(String name, String email, String password, String aboutMe) {
		this.username = name;
		this.email = email;
		this.password = password;
		this.aboutMe = aboutMe;
		List<Role> authorities = new ArrayList<>();
		authorities.add(Role.USER);
		this.authorities = authorities;
		this.accountNonExpired = true;
		this.accountNonLocked = true;
		this.credentialsNonExpired = true;
		this.enabled = true;
		this.registrationDate = Calendar.getInstance().getTime();
		this.rank = Rank.PADAVAN;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Role> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<Role> authorities) {
		this.authorities = authorities;
	}

	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getAboutMe() {
		return aboutMe;
	}

	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public List<Article> getAlreadyRead() {
		return alreadyRead;
	}

	public void setAlreadyRead(List<Article> alreadyRead) {
		this.alreadyRead = alreadyRead;
	}

	public List<Exercise> getAlreadyDone() {
		return alreadyDone;
	}

	public void setAlreadyDone(List<Exercise> alreadyDone) {
		this.alreadyDone = alreadyDone;
	}

	public List<Article> getMyArticles() {
		return myArticles;
	}

	public void setMyArticles(List<Article> myArticles) {
		this.myArticles = myArticles;
	}

	public Rank getRank() {
		return rank;
	}

	public void setRank(Rank rank) {
		this.rank = rank;
	}

	public long getScore() {
		return score;
	}

	public void setScore(long score) {
		this.score = score;
	}

	public List<ArticleComment> getArticleComments() {
		return articleComments;
	}

	public void setArticleComments(List<ArticleComment> articleComments) {
		this.articleComments = articleComments;
	}

	public List<ExerciseLike> getExeLike() {
		return exeLike;
	}

	public void setExeLike(List<ExerciseLike> exeLike) {
		this.exeLike = exeLike;
	}

	public List<ExerciseLike> getArticLike() {
		return articLike;
	}

	public void setArticLike(List<ExerciseLike> articLike) {
		this.articLike = articLike;
	}

	@Override
	public String toString() {
		return "User{" +
			"userId=" + super.getId() +
			", email='" + email + '\'' +
			", username='" + username + '\'' +
			", password='" + password + '\'' +
			", authorities=" + authorities.toString() +
			", accountNonExpired=" + accountNonExpired +
			", accountNonLocked=" + accountNonLocked +
			", credentialsNonExpired=" + credentialsNonExpired +
			", enabled=" + enabled +
			", aboutMe='" + aboutMe + '\'' +
			", registrationDate=" + registrationDate +
			", alreadyRead=" + alreadyRead.toString() +
			", alreadyDone=" + alreadyDone.toString() +
			", myArticles=" + myArticles.toString() +
			", rank=" + rank +
			", score=" + score +
			", articleComments=" + articleComments.toString() +
			", exeLike=" + exeLike.toString() +
			", articLike=" + articLike.toString() +
			'}';
	}
}
