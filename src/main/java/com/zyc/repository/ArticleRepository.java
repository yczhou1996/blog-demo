package com.zyc.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.zyc.domain.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long>{
	@Query("select u from Article u")
	Page<Article> findAllArticle(Pageable pageable);
	
	@Query("select u from Article u where u.user_id=:user_id")
	Page<Article> findAllByUserId(Pageable pageable, @Param("user_id") Long user_id);
}
