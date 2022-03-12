package com.example.controller.login;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.form.SignupForm;


@Controller
@RequestMapping("/signup")
public class SignupController {

	@GetMapping("")
	public String getSignup(Model model, @ModelAttribute SignupForm form) {
		return "login/signup";
	}

	@ExceptionHandler(DataAccessException.class)
	public String dataAccessExceptionHandler(DataAccessException e, Model model) {
		// Set an empty string
		model.addAttribute("error", "");
		// Register message in Model
		model.addAttribute("message", "An exception occurred in SignupController");
		// Register HTTP error code(500) in Model
		model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);
		return "error";
	}

	@ExceptionHandler(Exception.class)
	public String exceptionHandler(Exception e, Model model) {
		// Set an empty string
		model.addAttribute("error", "");
		// Register message in Model
		model.addAttribute("message", "An exception occurred in SignupController");
		// Register HTTP error code(500) in Model
		model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);
		return "error";
	}
}
