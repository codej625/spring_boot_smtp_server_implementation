package com.web.smtp;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SmtpController {

	@ResponseBody
	@GetMapping(value = "/")
	public String test(@RequestParam Map<String, String> params) {

		System.out.println(params);

		return "smtp 작성해보자";
	}

}
