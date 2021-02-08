package com.book.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	@RequestMapping("/loginPage")
	public String login() {
		return "loginPage";
	}

	@RequestMapping("/login-error")
	public String loginError(Model model) {
		model.addAttribute("loginError", true);
		return "loginPage";
	}
	
	@RequestMapping("/top")
	public String top() {
		return "top";
	}
}