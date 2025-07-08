package com.mysite.sbb.question;

import java.security.Principal;

import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mysite.sbb.answer.AnswerForm;
import com.mysite.sbb.user.SiteUser;
import com.mysite.sbb.user.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

// @RequestMapping → 프리픽스(prefix 접두사) 지정
@RequestMapping("/question")
@RequiredArgsConstructor // final 또는 @NonNull이 붙은 필드에 의존성 주입(DI)을 위한 생성자 자동 생성
@Controller
public class QuestionController {

	private final QuestionService questionService;
	private final UserService userService;

	// 질문목록
	@GetMapping("/list")
	// @ResponseBody 제거 → 뷰 이름을 반환하여 템플릿 렌더링을 하기 위해
	public String list(Model model, @RequestParam(value = "page", defaultValue = "0") int page) {
		// model → 컨트롤러에서 뷰(Thymeleaf 등)로 데이터를 전달하기 위한 인터페이스

		Page<Question> paging = this.questionService.getList(page);
		model.addAttribute("paging", paging);
		// model.addAttribute() → 뷰(HTML 템플릿)에 데이터를 전달하기 위한 메서드

		return "question_list";
	}

	// 상세조회
	@GetMapping(value = "/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id, AnswerForm anserForm) {
		// @PathVariable → 경로 변수를 표시하기 위해 메서드에 매개변수에 사용
		Question question = this.questionService.getQuestion(id);
		model.addAttribute("question", question);
		return "question_detail";
	}

	// 질문등록
	@PreAuthorize("isAuthenticated()") // 로그인 된 경우만 실행, 로그인 안 된 경우 로그인 페이지로 강제 이동
	@GetMapping("/create")
	public String questionCreate(QuestionForm questionForm) {
		return "question_form";
	}

	@PreAuthorize("isAuthenticated()")
	@PostMapping("/create")
	public String questionCreate(@Valid QuestionForm questionForm, BindingResult bindingResult, Principal principal) {
		// @Valid → 유효성 검사 트리거(방아쇠) 역할
		// QuestionForm → 사용자가 입력한 질문 데이터를 담기 위한 DTO(데이터 전달 객체)
		// BindingResult → 유효성 검사 결과 저장

		// Errors는 반드시 Request 객체 바로 뒤에 위치
		if (bindingResult.hasErrors()) { // hasErrors() → Request 객체에 설정한 유효성 검사
			return "question_form"; // 에러가 있을 경우 question_form으로
		}

		// principal 객체를 통해 사용자명, siteUser 객체 얻음
		SiteUser siteUser = this.userService.getUser(principal.getName());
		// 질문 등록될 때 siteUser 객체를 통해 author_id 저장
		this.questionService.create(questionForm.getSubject(), questionForm.getContent(), siteUser);
		return "redirect:/question/list"; // 질문 저장 후 질문목록으로 이동
	}
}