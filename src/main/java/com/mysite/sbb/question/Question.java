package com.mysite.sbb.question;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import com.mysite.sbb.answer.Answer;
import com.mysite.sbb.user.SiteUser;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Question {

	@Id // primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// 데이터를 저장할 때 해당 속성에 값을 따로 세팅하지 않아도 1씩 자동으로 증가하여 저장
	private Integer id; // 추후 null 체크 예정으로 Integer 사용

	@Column(length = 200) // 칼럼 길이 설정
	private String subject;

	@Column(columnDefinition = "TEXT") // 칼럼 글자수 제한 삭제
	private String content;

	// createDate → Table 매핑 시 create_date 변경됨
	private LocalDateTime createDate;

	@OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
	// mappedBy → 참조 엔티티의 속성명
	// CascadeType.REMOVE → 게시글 삭제하면 하위 답변들도 함께 삭제
	private List<Answer> answerList; // 해당 질문에 달린 답변 목록 저장

	@ManyToOne
	private SiteUser author;

	private LocalDateTime modifyDate;

	@ManyToMany
	private Set<SiteUser> voter;
}