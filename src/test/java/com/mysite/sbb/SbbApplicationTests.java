package com.mysite.sbb;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SbbApplicationTests {

	@Autowired // 필드, 생성자, 메서드 등을 자동 연결
	// 스프링의 '의존성 주입(DI)'을 통해 QuestionRepository의 객체를 주입
	private QuestionRepository questionRepository;

	@Test
	void testJpa() {

		// 데이터 저장
		Question q1 = new Question();
		q1.setSubject("sbb가 무엇인가요?");
		q1.setContent("sbb를 통해서 스프링 부트 프로젝트를 공부하는 걸로 알고 있는데 sbb에 대해 알고 싶습니다.");
		q1.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q1); // 첫번째 Question 저장

		Question q2 = new Question();
		q2.setSubject("스프링 부트 모델 질문입니다.");
		q2.setContent("id는 자동으로 생성되는 걸까요?");
		q2.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q2); // 두번째 Question 저장

		// findAll()
//		List<Question> all = this.questionRepository.findAll(); // 모든 엔티티 조회
//		assertEquals(2, all.size()); // 두 객체 또는 값이 같은지 확인
//
//		Question q = all.get(0);
//		assertEquals("sbb가 무엇인가요?", q.getSubject());

		// findById()
//		Optional<Question> op = this.questionRepository.findById(1);
//		// Optional → null 값을 유연하게 처리하기 위한 클래스
//		if (op.isPresent()) { // isPresent()로 값이 존재하는지 확인 → 있으면 true, 없으면 false
//			Question q = op.get();
//			assertEquals("sbb가 무엇인가요?", q.getSubject());
//		}

		// findBySubject()
//		Question q = this.questionRepository.findBySubject("sbb가 무엇인가요?");
//		assertEquals(1, q.getId());

		// findBySubjectAndContent()
//		Question q = this.questionRepository.findBySubjectAndContent("스프링 부트 모델 질문입니다.", "id는 자동으로 생성되는 걸까요?");
//		assertEquals(2, q.getId());

		// findBySubjectLike()
		List<Question> qList = this.questionRepository.findBySubjectLike("sbb%");
		Question q = qList.get(0);
		assertEquals("sbb가 무엇인가요?", q.getSubject());
	}
}