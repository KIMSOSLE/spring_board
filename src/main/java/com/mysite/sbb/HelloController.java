package com.mysite.sbb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // HelloController가 controller 역할을 함
public class HelloController {

	@GetMapping("/hello") // 매소드를 URL 매핑
	@ResponseBody
	public String hello() {
		return "Hello World ~!!";
	}
}