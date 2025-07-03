package com.mysite.sbb.user;

import lombok.Getter;

@Getter
public enum UserRole {

	ADMIN("ROLE_ADMIN"), USER("ROLE_USER");
	// ADMIN, USER 상수 생성, 각각 ROLE_ADMIN, ROLE_USER 값 저장
	// 상수라서 @Setter 불필요

	UserRole(String value) {
		this.value = value;
	}

	private String value;

}