package com.mysite.sbb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

	@GetMapping("/sbb")
	@ResponseBody
	public String index() {
//		System.out.println("index");
		return "댜섲'대ㅑㅓ댇러ㅑ"; // "index" → 템플릿 파일
	}
}