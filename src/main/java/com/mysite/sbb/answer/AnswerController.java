package com.mysite.sbb.answer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mysite.sbb.question.Question;
import com.mysite.sbb.question.QuestionService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/answer")
@RequiredArgsConstructor
@Controller
public class AnswerController {

	// DI (2개의 객체 주입)
	private final QuestionService questionService;
	private final AnswerService answerService;

	@PostMapping("/create/{id}") // value 생략 가능
	public String createAnswer(Model model, @PathVariable("id") Integer id,
			@RequestParam(value = "content") String content) {
		// @RequestParam(value = "content") String content
		// → (question_detail.html)에서 답변으로 입력한 내용(content)을 사용하기 위해

		Question question = this.questionService.getQuestion(id);
		this.answerService.create(question, content); // 답변 저장
		return String.format("redirect:/question/detail/%s", id);
	}
}