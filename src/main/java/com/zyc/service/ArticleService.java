package com.zyc.service;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.zyc.domain.Article;

@Service
public interface ArticleService {
	Article saveArticle(Article article);
	
	void removeArticle(Long id);
	
	Article updateArticle(Article article);
	
	Article getArticleById(Long id);
	
	List<Article> findAll();
	
	List<Article> listAllById(Long user_id);
	
	void readingIncrease(Long id);
	
	void commentIncrease(Long id);
	
	Page<Article> findAllArticle(Pageable pageable);
	
	Page<Article> findAllById(Pageable pageable,Long user_id);
}
