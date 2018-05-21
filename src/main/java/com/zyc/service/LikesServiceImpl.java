package com.zyc.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zyc.domain.Likes;
import com.zyc.repository.LikesRepository;

@Service
public class LikesServiceImpl implements LikesService{

	@Autowired
	private LikesRepository likesRepository;
	
	@Transactional
	@Override
	public Likes saveLike(Likes like) {
		return likesRepository.save(like);
	}

	@Transactional
	@Override
	public void removeLike(Long id) {
		likesRepository.deleteById(id);
	}

	@Transactional
	@Override
	public Likes updateLike(Likes like) {
		return likesRepository.save(like);
	}

	@Override
	public Likes getLikeById(Long user_id, Long article_id) {
		return likesRepository.findByuser_idAndarticle_id(user_id, article_id);
	}
}
