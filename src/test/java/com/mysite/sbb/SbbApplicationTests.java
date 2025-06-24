package com.mysite.sbb;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.transaction.Transactional;

@SpringBootTest
class SbbApplicationTests {

	@Autowired // 필드, 생성자, 메서드 등을 자동 연결
	// 스프링의 '의존성 주입(DI)'을 통해 QuestionRepository의 객체를 주입
	private QuestionRepository questionRepository;

	@Autowired
	private AnswerRepository answerReposiotry;

	// testJpa()에 대하여 DB 세션 유지시킴
	@Transactional // 모든 DB 작업이 성공해야 커밋되고, 하나라도 실패하면 모두 실패
	@Test
	void testJpa() {

		// 질문 데이터 저장
//		Question q1 = new Question();
//		q1.setSubject("sbb가 무엇인가요?");
//		q1.setContent("sbb에 대해 알고 싶습니다.");
//		q1.setCreateDate(LocalDateTime.now());
//		this.questionRepository.save(q1); // 첫번째 Question 저장
//
//		Question q2 = new Question();
//		q2.setSubject("스프링 부트 모델 질문입니다.");
//		q2.setContent("id는 자동으로 생성되는 걸까요?");
//		q2.setCreateDate(LocalDateTime.now());
//		this.questionRepository.save(q2); // 두번째 Question 저장

		// 질문 데이터 조회
		// findAll()
//		List<Question> all = this.questionRepository.findAll(); // 모든 엔티티 조회
//		assertEquals(2, all.size()); // 두 객체 또는 값이 같은지 확인
//
//		Question q = all.get(0);
//		assertEquals("sbb가 무엇인가요?", q.getSubject());

		// findById()
//		Optional<Question> oq = this.questionRepository.findById(1);
//		// Optional → null 값을 유연하게 처리하기 위한 클래스
//		if (op.isPresent()) { // isPresent()로 값이 존재하는지 확인 → 있으면 true, 없으면 false
//			Question q = oq.get();
//			assertEquals("sbb가 무엇인가요?", q.getSubject());
//		}

		// findBySubject()
//		Question q = this.questionRepository.findBySubject("sbb가 무엇인가요?");
//		assertEquals(1, q.getId());

		// findBySubjectAndContent()
//		Question q = this.questionRepository.findBySubjectAndContent("스프링 부트 모델 질문입니다.", "id는 자동으로 생성되는 걸까요?");
//		assertEquals(2, q.getId());

		// findBySubjectLike()
//		List<Question> qList = this.questionRepository.findBySubjectLike("sbb%");
//		// 'sbb%' → sbb로 시작하는 문자열
//		Question q = qList.get(0);
//		assertEquals("sbb가 무엇인가요?", q.getSubject());

		// 질문 데이터 수정
//		Optional<Question> oq = this.questionRepository.findById(1);
//		assertTrue(oq.isPresent());
//		Question q = oq.get();
//		q.setSubject("수정된 제목");
//		this.questionRepository.save(q);

		// 질문 데이터 삭제
//		assertEquals(2, this.questionRepository.count());
//		Optional<Question> oq = this.questionRepository.findById(1);
//		assertTrue(oq.isPresent());
//		Question q = oq.get();
//		this.questionRepository.delete(q);
//		assertEquals(1, this.questionRepository.count());

		// 답변 데이터 저장
//		Optional<Question> oq = this.questionRepository.findById(2);
//		assertTrue(oq.isPresent());
//		Question q = oq.get();
//
//		Answer a = new Answer();
//		a.setContent("답변드립니다.");
//		a.setQuestion(q); // 어떤 질문의 답변인지 알기 위해 Question 객체 필요 → QUESTION_ID
//		a.setCreateDate(LocalDateTime.now());
//		this.answerReposiotry.save(a);

		// 답변 데이터 조회
//		Optional<Answer> oa = this.answerReposiotry.findById(1);
//		assertTrue(oa.isPresent());
//		Answer a = oa.get();
//		assertEquals(2, a.getQuestion().getId());
//		// a.getQuestion() → 답변에 연결된 Question 객체

		// '질문 데이터를 통해 답변 데이터 찾기' & 답변 데이터를 통해 질문데이터 찾기
		Optional<Question> oq = this.questionRepository.findById(2);
		assertTrue(oq.isPresent());
		Question q = oq.get();

		List<Answer> aList = q.getAnswerList(); // 여기까지 일반적으로 DB 세션 유지됨. 이후 끊어짐

		assertEquals(1, aList.size());
		assertEquals("답변드립니다.", aList.get(0).getContent());
	}
}