package com.mysite.sbb.question;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mysite.sbb.DataNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // final 또는 @NonNull이 붙은 필드에 의존성 주입(DI)을 위한 생성자 자동 생성
@Service
public class QuestionService {

	private final QuestionRepository questionRepository;

	public List<Question> getList() {
		return this.questionRepository.findAll();
	}

	public Question getQuestion(Integer id) {

		// Optional → 값이 있을 수도 있고 없을 수도 있는 컨테이너 객체
		Optional<Question> question = this.questionRepository.findById(id);
		if (question.isPresent()) { // 값이 존재하는지 확인
			return question.get(); // 값이 존재하면 get()으로 값 꺼냄
		} else { // 존재하지 않다면 예외 발생
			throw new DataNotFoundException("question not found");
		}
	}

	public void create(String subject, String content) {
		Question question = new Question();
		question.setSubject(subject);
		question.setContent(content);
		question.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(question); // question 객체 저장
	}
}