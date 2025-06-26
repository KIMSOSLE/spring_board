package com.mysite.sbb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

	@GetMapping("/sbb")
	@ResponseBody
	public String index() {
		return "댜섲'대ㅑㅓ댇러ㅑ";
	}

	@GetMapping("/")
	public String root() {
		return "redirect:/question/list";
		// redirect → 다른 URL로 자동 이동하는 것
	}
}