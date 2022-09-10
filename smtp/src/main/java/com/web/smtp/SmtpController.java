package com.web.smtp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SmtpController {

	@GetMapping("/")
	public String testController() {

		return "test";

	}
}
