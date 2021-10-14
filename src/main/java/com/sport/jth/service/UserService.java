package com.sport.jth.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sport.jth.model.User;
import com.sport.jth.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Transactional
	public void join(User user) {
		String rawPassword = user.getPassword();
		String encPassword = encoder.encode(rawPassword);
		user.setPassword(encPassword);
		user.setRole("USER");
		userRepository.save(user);
	}
	
	@Transactional(readOnly = true)
	public User joinCheck(String username) {
		User user = userRepository.findByUsername(username);
		
		return user;
	}
	
	@Transactional(readOnly = true)
	public User findUser(int id) {
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("해당 사용자를 찾을 수 없습니다.");
		});
		return user;
	}
	
	@Transactional
	public void update(String username, String password) {
		String encPassword = encoder.encode(password);
		User user = userRepository.findByUsername(username);
		user.setPassword(encPassword);
		userRepository.save(user);
	}
	
	@Transactional
	public void delete(int id) {
		userRepository.deleteById(id);
	}
	
	//이메일로 유저 찾기
	@Transactional(readOnly = true)
	public List<User> findByEmail(String email) {
		List<User> userList = userRepository.findByEmail(email);
		
		if(userList == null) {
			return null;
		}
		
		return userList;
	}
	
	//이메일과 아이디를 모두 충족하는 유저 찾기
	@Transactional(readOnly = true)
	public User findByUsernameAndEmail(String username, String email) {
		User user = userRepository.findByUsernameAndEmail(username, email);
		
		if(user == null) {
			return null;
		}
		
		return user;
	}
}
