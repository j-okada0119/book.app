package com.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.book.form.PasswordForm;
import com.book.security.UserDetailsServiceImpl;

@Controller
public class ChangePasswordController {
	
	@Autowired
	UserDetailsServiceImpl userDetailsServiceImpl;
	
	@RequestMapping("/editPassword")
	public String editPassword(Model model) {
		
		model.addAttribute("passwordForm", new PasswordForm());
		
		return "editPassword";
	}
	
	@RequestMapping("/forceEditPassword")
	public String forceEditPassword(Model model) {
		
		model.addAttribute("passwordForm", new PasswordForm());
		
		return "forceEditPassword";
	}
	
	@RequestMapping(value = "/savePassword", params = "save", method=RequestMethod.POST)
	public String savePassword(Model model, @ModelAttribute PasswordForm passwordForm) {
		
		userDetailsServiceImpl.savePassword(passwordForm.getNewPassword());
		
		return "editComplete";
	}

	@RequestMapping(value = "/savePassword", params = "back", method=RequestMethod.POST)
	public String backDisplay(Model model, @ModelAttribute PasswordForm passwordForm) {
		return "top";
	}
}
