package com.zyc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zyc.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	User findByUsername(String username);
	
	User findByUsernameAndPassword(String username, String password);
}
