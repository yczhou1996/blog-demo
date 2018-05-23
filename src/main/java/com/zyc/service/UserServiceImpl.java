package com.zyc.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zyc.domain.User;
import com.zyc.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	@Transactional
	@Override
	public void removeUser(User user) {
		userRepository.delete(user);
	}

	@Transactional
	@Override
	public User updateUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public User getUserById(Long id) {
		return userRepository.getOne(id);
	}

	@Override
	public List<User> listUsers() {
		return userRepository.findAll();
	}
	
	@Override
	public User findByUsername(String username){
		return userRepository.findByUsername(username);
	}
	
	@Override
	public User findByUsername(String username, String password){
		return userRepository.findByUsernameAndPassword(username, password);
	}

	@Override
	public boolean Exists(Long id) {
		return userRepository.existsById(id);
	}
	
}
