package com.zyc.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.zyc.domain.Article;
import com.zyc.repository.ArticleRepository;

@Service
public class ArticleServiceImpl implements ArticleService{

	@Autowired
	private ArticleRepository articleRepository;
	
	@Transactional
	@Override
	public Article saveArticle(Article article) {
		return articleRepository.save(article);
	}

	@Transactional
	@Override
	public void removeArticle(Long id) {
		articleRepository.deleteById(id);
	}

	@Override
	public Article updateArticle(Article article) {
		return articleRepository.save(article);
	}

	@Override
	public Article getArticleById(Long id) {
		return articleRepository.getOne(id);
	}

	@Override
	public List<Article> findAll() {
		return articleRepository.findAll();
	}
	
	@Override
	public List<Article> listAllById(Long user_id){
		List<Article> articles = articleRepository.findAllByUserId(user_id);
		return articles;
	}
	
	@Override
	public void readingIncrease(Long id){
		Article article = articleRepository.getOne(id);
		article.setReadSize(article.getReadSize()+1);
		articleRepository.save(article);
	}
	
	@Override
	public void commentIncrease(Long id){
		Article article = articleRepository.getOne(id);
		article.setCommentSize(article.getCommentSize()+1);
		articleRepository.save(article);
	}

	@Override
	@Transactional
	public Page<Article> findAllArticle(Pageable pageable) {
		Page<Article> page = articleRepository.findAllArticle(pageable);
        return page;
	}

	@Override
	@Transactional
	public Page<Article> findAllById(Pageable pageable, Long user_id) {
		Page<Article> page = articleRepository.findAllByUserId(pageable, user_id);
		return page;
	}
}
