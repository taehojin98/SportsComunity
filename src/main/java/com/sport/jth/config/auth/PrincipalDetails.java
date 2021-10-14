package com.sport.jth.config.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.sport.jth.model.User;


//시큐리티가 /login 주소 요청이 오면 낚아채서 로그인을 진행시킨다.
//로그인 진행이 완료가 되면 시큐리티 session을 만들어 준다. (Security ContextHolder)
//오브젝트 -> Authentication 타입 객체
//Authentication 안에 User 정보가 있어야 한다.
//User 오브젝트 타입 -> UserDetails 타입 객체

//Security Session -> Authentication -> UserDetails(PrincipalDetails)

public class PrincipalDetails implements UserDetails {
	
	private User user; //컴포지션
	
	//일반 로그인
	public PrincipalDetails(User user) {
		this.user = user;
	}
	
	
	//해당 User의 권한을 리턴하는 곳
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collect = new ArrayList<>();
		collect.add(new GrantedAuthority() {

			@Override
			public String getAuthority() {
				return user.getRole();
			}
		});
		
		return collect;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		
		//사이트에서 1년 동안 회원이 로그인을 안하면 휴먼 계정으로 전환 함
		//현재 시간 - 로그인 시간 => 1년 초과하면 return false;
		
		return true;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


}
