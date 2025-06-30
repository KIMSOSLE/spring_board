package com.mysite.sbb.question;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	public String detail(Model model, @PathVariable("id") Integer id) {
		// @PathVariable → 경로 변수를 표시하기 위해 메서드에 매개변수에 사용
		Question question = this.questionService.getQuestion(id);
		model.addAttribute("question", question);
		return "question_detail";
	}

	// 질문등록
	@GetMapping("/create")
	public String questionCreate() {
		return "question_form";
	}

	@PostMapping("/create")
	public String questionCreate(@RequestParam(value = "subject") String subject,
			@RequestParam(value = "content") String content) {
		// TODO 질문 저장 (service에서 처리)
		this.questionService.create(subject, content);
		return "redirect:/question/list"; // 질문 저장 후 질문목록으로 이동
	}
}