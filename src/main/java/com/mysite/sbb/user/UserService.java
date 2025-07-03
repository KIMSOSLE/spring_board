package com.mysite.sbb.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

	private final UserRepository userRepository;

	private final PasswordEncoder passwordEncoder;

	public SiteUser create(String username, String email, String password) {
		SiteUser user = new SiteUser();
		user.setUsername(username);
		user.setEmail(email);
//		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		// @Bean으로 등록된 메서드, 객체 주입을 받았기 때문에 사용 안 함
		user.setPassword(passwordEncoder.encode(password));
		this.userRepository.save(user);
		return user;
	}
}