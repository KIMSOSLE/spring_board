package com.mysite.sbb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.HeaderWriter;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration // @Configuration → 해당 클래스가 스프링 설정 클래스임을 의미
@EnableWebSecurity // @EnableWebSecurity → 모든 요청 URL이 스프링 시큐리티의 제어를 받도록 설정
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

	@Bean // @Bean → Spring 컨테이너에 객체를 직접 등록할 때 사용
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
				.requestMatchers(new AntPathRequestMatcher("/**")).permitAll()) // 모든 페이지에 접근 가능
				// permitAll() → 특정 경로에 대한 모든 요청을 허용
				.csrf((csrf) -> csrf.ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**")))
				// h2-console을 보안에서 허용
				.headers((headers) -> headers.addHeaderWriter((HeaderWriter) new XFrameOptionsHeaderWriter(
						XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN)))
				.formLogin((formLogin) -> formLogin.loginPage("/user/login") // 로그인 URL
						.defaultSuccessUrl("/")) // 로그인 성공 시 이동 경로
				.logout((logout) -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/user/logout")) // 로그아웃 URL
						.logoutSuccessUrl("/") // 로그아웃 성공 시 이동 경로
						.invalidateHttpSession(true)); // 세션 제거
		return http.build();

	}

	// 비밀번호 암호화
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// 인증과 인가(권한) 부여 처리
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
}