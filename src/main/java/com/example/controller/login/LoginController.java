package com.example.controller.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.form.BrSearchForm;

@Controller
public class LoginController {
	
	@GetMapping("/login")
	public String getLogin(@ModelAttribute BrSearchForm form) {
		return "login/login";
	}
}
