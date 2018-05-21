package com.zyc.service;

import org.springframework.stereotype.Service;

import com.zyc.domain.Likes;

@Service
public interface LikesService {
	Likes saveLike(Likes like);
	
	void removeLike(Long id);
	
	Likes updateLike(Likes like);
	
	Likes getLikeById(Long user_id, Long article_id);
}
