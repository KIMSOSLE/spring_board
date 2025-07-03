package com.mysite.sbb.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserSecurityService implements UserDetailsService {
	// UserDetailsService → 스프링 시큐리티에서 사용자 정보를 가져오기 위한 인터페이스

	private final UserRepository userRepository; // UserRepository → 사용자 정보를 DB에서 조회하는 JPA 인터페이스

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// loadUserByUsername() → 로그인 시 아이디(username)로 사용자 인증 정보 반환
		Optional<SiteUser> _siteUser = this.userRepository.findByusername(username);
		if (_siteUser.isEmpty()) {
			throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
		}

		SiteUser siteUser = _siteUser.get();
		List<GrantedAuthority> authorities = new ArrayList<>();
		// GrantedAuthority → 사용자 권한을 나타내는 Spring Security 객체
		if ("admin".equals(username)) {
			authorities.add(new SimpleGrantedAuthority(UserRole.ADMIN.getValue()));
		} else {
			authorities.add(new SimpleGrantedAuthority(UserRole.USER.getValue()));
		}
		return new User(siteUser.getUsername(), siteUser.getPassword(), authorities);
	}
}