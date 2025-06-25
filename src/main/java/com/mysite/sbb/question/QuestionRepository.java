package com.mysite.sbb.question;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Integer> {

	Question findBySubject(String subject); // 추상 메서드

	Question findBySubjectAndContent(String subject, String content); // 추상 메서드

	List<Question> findBySubjectLike(String subject);
}