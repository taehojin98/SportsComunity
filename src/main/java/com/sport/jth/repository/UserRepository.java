package com.sport.jth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sport.jth.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	User findByUsername(String username);
	
	List<User> findByEmail(String email);
	
	User findByUsernameAndEmail(String username, String email);
}
