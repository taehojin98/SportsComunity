package com.sport.jth.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sport.jth.model.User;
import com.sport.jth.repository.UserRepository;

@Service
public class PrincipalDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("여기야");
		User principal = userRepository.findByUsername(username);
		System.out.println("username : " + username);
		System.out.println("loadUserByUsername : " + principal);
		if(principal != null) {
			return new PrincipalDetails(principal);
		} 
		return null;
	}

	public PrincipalDetailsService() {
		// TODO Auto-generated constructor stub
	}

	public PrincipalDetailsService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}
	
	
}
