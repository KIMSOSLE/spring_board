package com.mysite.sbb.question;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // final 또는 @NonNull이 붙은 필드에 의존성 주입(DI)을 위한 생성자 자동 생성
@Controller
public class QuestionController {

	private final QuestionRepository questionRepository; // 객체 주입

	@GetMapping("/question/list")
	// @ResponseBody 제거 → 뷰 이름을 반환하여 템플릿 렌더링을 하기 위해
	public String list(Model model) {
		// model → 컨트롤러에서 뷰(Thymeleaf 등)로 데이터를 전달하기 위한 인터페이스

		List<Question> questionList = this.questionRepository.findAll();
		model.addAttribute("questionList", questionList);
		// model.addAttribute() → 뷰(HTML 템플릿)에 데이터를 전달하기 위한 메서드

		return "question_list"; // question_list.html 뷰 템플릿을 렌더링하기 위한 반환 값
	}
}