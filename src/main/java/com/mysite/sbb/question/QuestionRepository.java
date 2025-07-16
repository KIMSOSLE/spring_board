package com.mysite.sbb.question;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Integer> {

	Question findBySubject(String subject); // 추상 메서드

	Question findBySubjectAndContent(String subject, String content); // 추상 메서드

	List<Question> findBySubjectLike(String subject);

//	Page<Question> findAll(Pageable pageble);
	// Page<Question> → 조회 결과를 페이지 단위로 묶은 객체(목록+페이징 정보 포함)
	// findAll() → Spring Data JPA가 자동 구현해주는 전체 데이터 조회 메서드
	// Pageable pageble → 페이징 정보를 담는 객체

	// DB에서 Question Entity를 조회한 결과를 페이징하여 반환(중요)
	Page<Question> findAll(Specification<Question> spec, Pageable pageable);
}