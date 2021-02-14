package com.book.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.book.entity.PasswordHistory;
import com.book.form.PasswordForm;

@Controller
public class ChangePasswordController {
	
	@RequestMapping("/editPassword")
	public String editPassword(Model model) {
		
		model.addAttribute("passwordHistory", new PasswordHistory());
		
		return "editPassword";
	}
	
	@RequestMapping("/editComplete")
	public String editComplete() {
		return "editComplete";
	}
	
	@RequestMapping("/forceEditPassword")
	public String forceEditPassword(Model model) {
		
		model.addAttribute("passwordForm", new PasswordForm());
		
		return "forceEditPassword";
	}

}
