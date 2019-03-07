package ua.com.javajedi.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.javajedi.db.ArticleCommentRepository;
import ua.com.javajedi.model.comment.ArticleComment;
import ua.com.javajedi.service.ArticleCommentService;
import ua.com.javajedi.service.ArticleService;
import ua.com.javajedi.service.UserServiceDB;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class ArticleCommentServiceImpl implements ArticleCommentService {
	private final ArticleCommentRepository articleCommentRepository;
	private final UserServiceDB userServiceDB;
	private final ArticleService articleService;

	@Autowired
	public ArticleCommentServiceImpl(final ArticleCommentRepository articleRepository,
	                                 final UserServiceDB userServiceDB,
	                                 final ArticleService articleService) {
		this.articleCommentRepository = articleRepository;
		this.userServiceDB = userServiceDB;
		this.articleService = articleService;
	}

	@Override
	public ArticleComment add(ArticleComment articleComment) {
		return articleCommentRepository.saveAndFlush(articleComment);
	}

	@Override
	public ArticleComment add(String userId, long articleId, String content) {
		ArticleComment comment = new ArticleComment();
		comment.setAuthor(userServiceDB.findById(Long.parseLong(userId)));
		comment.setArticle(articleService.findById(articleId));
		comment.setContent(content);
		comment.setDate(Calendar.getInstance().getTime());
		return articleCommentRepository.saveAndFlush(comment);
	}

	@Override
	public ArticleComment update(ArticleComment articleComment) {
		return articleCommentRepository.saveAndFlush(articleComment);
	}

	@Override
	public ArticleComment delete(long articleId) {
		articleCommentRepository.deleteById(articleId);
		return articleCommentRepository.findById(articleId).get();
	}

	@Override
	public ArticleComment findById(ArticleComment articleComment) {
		return articleCommentRepository.findById(articleComment.getaCommentId()).get();
	}

	@Override
	public List<ArticleComment> findAll() {
		return articleCommentRepository.findAll();
	}

	@Override
	public List<ArticleComment> findAllByArticleId(long articleId) {
		List<ArticleComment> comments = articleCommentRepository.findAllByArticleId(articleId);

		if (comments == null) {
			return new ArrayList<>();
		}

		return comments;
	}
}
