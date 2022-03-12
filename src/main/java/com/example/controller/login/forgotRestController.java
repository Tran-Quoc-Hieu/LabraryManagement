package com.example.controller.login;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.controller.RestResult;
import com.example.domain.reader.model.MReader;
import com.example.domain.reader.service.ReaderService;
import com.example.form.ForgotForm;

@RestController
@RequestMapping("/forgot")
public class forgotRestController {
	@Autowired
	private ReaderService readerService;
	
	@Autowired
	private MessageSource messageSource;
	
	@PutMapping("")
	public RestResult putPassword(@Validated ForgotForm form, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			Map<String, String> errors = new HashMap<String, String>();
			for (FieldError error : bindingResult.getFieldErrors()) {
				String message = messageSource.getMessage(error, null);
				errors.put(error.getField(), message);
			}
			return new RestResult(90, errors);
		}
		MReader reader = readerService.getLogin(form.getEmail());
		if (reader == null) {
			return new RestResult(10, null);
		}
		reader.setReaderPassword("password");
		readerService.updatePasswordReader(reader);
		return new RestResult(0, null);
	}
}
