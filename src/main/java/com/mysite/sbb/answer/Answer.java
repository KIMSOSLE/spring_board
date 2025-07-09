package com.mysite.sbb.answer;

import java.time.LocalDateTime;

import com.mysite.sbb.question.Question;
import com.mysite.sbb.user.SiteUser;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Answer {

	@Id // primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// 데이터를 저장할 때 해당 속성에 값을 따로 세팅하지 않아도 1씩 자동으로 증가하여 저장
	private Integer id; // 추후 null 체크 예정으로 Integer 사용

	@Column(columnDefinition = "TEXT") // 칼럼 글자수 제한 삭제
	private String content;

	private LocalDateTime createDate;

	@ManyToOne // N:1, 답변(자식)이 many, 질문(부모)이 one
	private Question question;

	@ManyToOne
	private SiteUser author;

	private LocalDateTime modifyDate;
}