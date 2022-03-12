package com.example.controller.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.form.ForgotForm;

@Controller
@RequestMapping("/forgot")
public class forgetController {
	
	@GetMapping("")
	public String getPage(@ModelAttribute ForgotForm form) {
		return "login/forgot";
	}
}
