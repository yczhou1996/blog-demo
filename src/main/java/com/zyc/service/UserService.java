package com.zyc.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zyc.domain.User;

@Service
public interface UserService {
	
	User saveUser(User user);
	
	void removeUser(User user);
	
	User updateUser(User user);
	
	User getUserById(Long id);
	
	List<User> listUsers();
	
	User findByUsername(String username);
	
	User findByUsername(String username, String password);
	
	boolean Exists(Long id);
}
