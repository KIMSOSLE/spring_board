package com.mysite.sbb.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class SiteUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; // Long → null 가능, 범위 큼, DB 자동 증가 값과 잘 맞음

	@Column(unique = true)
	private String username;

	private String password;

	@Column(unique = true)
	private String email;
}
