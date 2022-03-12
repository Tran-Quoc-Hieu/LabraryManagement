package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.domain.reader.service.ReaderService;

import lombok.extern.slf4j.Slf4j;

@Controller
public class HomeController {
	@Autowired
	private ReaderService readerService;
	
	@GetMapping("/home")
	public String getAdmin(Model model) {
		Authentication ath = SecurityContextHolder.getContext().getAuthentication();
		model.addAttribute("user", readerService.getLogin(ath.getName()));
		return "content/home/home";
	}
}
