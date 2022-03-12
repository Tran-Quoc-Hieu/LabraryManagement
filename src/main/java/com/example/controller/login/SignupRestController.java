package com.example.controller.login;

import java.util.HashMap;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.controller.RestResult;
import com.example.domain.reader.model.MReader;
import com.example.domain.reader.service.ReaderService;
import com.example.form.SignupForm;

@RestController
@RequestMapping("/signup")
public class SignupRestController {
	@Autowired
	private ReaderService readerService;

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private MessageSource messageSource;
	
	@PostMapping("")
	public RestResult postSignup(@Validated SignupForm form,BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			Map<String, String> errors = new HashMap<String, String>();
			
			for (FieldError error : bindingResult.getFieldErrors()) {
				String message = messageSource.getMessage(error, null);
				errors.put(error.getField(), message);
			}
			return new RestResult(90, errors);
		}
		MReader user = mapper.map(form, MReader.class);
		readerService.addUser(user);
		return new RestResult(0, null);
	}
}
