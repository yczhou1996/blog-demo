package com.zyc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.zyc.domain.Likes;

@Repository
public interface LikesRepository extends JpaRepository<Likes, Long>{
	@Query("select u from Likes u where u.user_id=:user_id and u.article_id=:article_id")
	Likes findByuser_idAndarticle_id(@Param("user_id") Long user_id,@Param("article_id") Long article_id);
}
