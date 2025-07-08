package com.mysite.sbb.answer;

import java.security.Principal;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mysite.sbb.question.Question;
import com.mysite.sbb.question.QuestionService;
import com.mysite.sbb.user.SiteUser;
import com.mysite.sbb.user.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/answer")
@RequiredArgsConstructor
@Controller
public class AnswerController {

	private final QuestionService questionService;
	private final AnswerService answerService;
	private final UserService userService;

	@PreAuthorize("isAuthenticated()") // 로그인 된 경우만 실행, 로그인 안 된 경우 로그인 페이지로 강제 이동
	@PostMapping("/create/{id}")
	public String createAnswer(Model model, @PathVariable("id") Integer id, @Valid AnswerForm answerForm,
			BindingResult bindingResult, Principal principal) {

		Question question = this.questionService.getQuestion(id);
		// principal 객체를 통해 사용자명, siteUser 객체 얻음
		SiteUser siteUser = this.userService.getUser(principal.getName());

		if (bindingResult.hasErrors()) {
			model.addAttribute("question", question);
			return "question_detail";
		}

		// 답변 등록될 때 siteUser 객체를 통해 author_id 저장
		this.answerService.create(question, answerForm.getContent(), siteUser);
		return String.format("redirect:/question/detail/%s", id);
	}
}