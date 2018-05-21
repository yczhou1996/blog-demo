package com.zyc.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.zyc.domain.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{
	
	@Query("select u from Comment u where u.article_id=:article_id")
	Page<Comment> findAllByArticle_id(Pageable pageable,@Param("article_id") Long article_id);
	
	@Query("select u from Comment u where u.article_id=:article_id  ")
	List<Comment> findComments(@Param("article_id") Long article_id);
}
