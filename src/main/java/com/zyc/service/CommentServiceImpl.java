package com.zyc.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.zyc.domain.Comment;
import com.zyc.repository.CommentRepository;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepository commentRepository;
	
	@Transactional
	@Override
	public Comment saveComment(Comment comment) {
		return commentRepository.save(comment);
	}

	@Transactional
	@Override
	public void removeComment(Long id) {
		commentRepository.deleteById(id);
	}

	@Transactional
	@Override
	public Comment updateComment(Comment comment) {
		return commentRepository.save(comment);
	}

	@Transactional
	@Override
	public Comment getCommentById(Long id) {
		return commentRepository.getOne(id);
	}

	@Transactional
	@Override
	public List<Comment> getCommentsById(Long article_id) {
		return  null;
	}

	@Transactional
	@Override
	public Page<Comment> findAllByArticleId(Pageable pageable, Long article_id) {
		Page<Comment> page = commentRepository.findAllByArticle_id(pageable, article_id);
		return page;
	}

	@Transactional
	@Override
	public List<Comment> findComments(Long article_id) {
		List<Comment> comments = commentRepository.findComments(article_id);
		return comments;
	}
}
