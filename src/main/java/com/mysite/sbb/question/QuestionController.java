package com.mysite.sbb.question;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mysite.sbb.answer.AnswerForm;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

// @RequestMapping → 프리픽스(prefix 접두사) 지정
@RequestMapping("/question")
@RequiredArgsConstructor // final 또는 @NonNull이 붙은 필드에 의존성 주입(DI)을 위한 생성자 자동 생성
@Controller
public class QuestionController {

	// service에게 repository 기능 부여
	private final QuestionService questionService;

	// 질문목록
	@GetMapping("/list")
	// @ResponseBody 제거 → 뷰 이름을 반환하여 템플릿 렌더링을 하기 위해
	public String list(Model model) {
		// model → 컨트롤러에서 뷰(Thymeleaf 등)로 데이터를 전달하기 위한 인터페이스

		List<Question> questionList = this.questionService.getList();
		model.addAttribute("questionList", questionList);
		// model.addAttribute() → 뷰(HTML 템플릿)에 데이터를 전달하기 위한 메서드

		return "question_list"; // question_list.html 뷰 템플릿을 렌더링하기 위한 반환 값
	}

	// 상세조회
	// @PathVariable("id") Integer id
	@GetMapping(value = "/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id, AnswerForm anserForm) {
		// @PathVariable → 경로 변수를 표시하기 위해 메서드에 매개변수에 사용
		Question question = this.questionService.getQuestion(id);
		model.addAttribute("question", question);
		return "question_detail";
	}

	// 질문등록
	@GetMapping("/create")
	public String questionCreate(QuestionForm questionForm) {
		return "question_form";
	}

	@PostMapping("/create")
	public String questionCreate(@Valid QuestionForm questionForm, BindingResult bindingResult) {
		// @Valid → 유효성 검사 트리거(방아쇠) 역할
		// QuestionForm → 사용자가 입력한 질문 데이터를 담기 위한 DTO(데이터 전달 객체)
		// BindingResult → 유효성 검사 결과 저장

		// Errors는 반드시 Request 객체 바로 뒤에 위치
		if (bindingResult.hasErrors()) { // hasErrors() → Request 객체에 설정한 유효성 검사
			return "question_form"; // 에러가 있을 경우 question_form으로
		}
		this.questionService.create(questionForm.getSubject(), questionForm.getContent());
		return "redirect:/question/list"; // 질문 저장 후 질문목록으로 이동
	}
}