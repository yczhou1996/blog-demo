package com.zyc.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.zyc.domain.Comment;

@Service
public interface CommentService {
	Comment saveComment(Comment comment);
	
	void removeComment(Long id);
	
	Comment updateComment(Comment comment);
	
	Comment getCommentById(Long id);
	
	List<Comment> getCommentsById(Long id);
	
	List<Comment> findComments(Long article_id);
	
	Page<Comment> findAllByArticleId(Pageable pageable, Long article_id);
}
