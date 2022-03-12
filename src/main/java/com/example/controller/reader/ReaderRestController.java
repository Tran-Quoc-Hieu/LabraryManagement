package com.example.controller.reader;

import java.util.HashMap;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.controller.RestResult;
import com.example.domain.reader.model.MReader;
import com.example.domain.reader.service.ReaderService;
import com.example.form.ReaderForm;
import com.example.form.ReaderUpForm;
import com.example.form.UserPasswordForm;

@RestController
@RequestMapping("/reader")
public class ReaderRestController {
	@Autowired
	private ReaderService readerService;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private MessageSource messageSource;
	
	@PostMapping("/add")
	public RestResult postBook(@Validated ReaderForm form, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			Map<String, String> errors = new HashMap<String, String>();
			
			for (FieldError error : bindingResult.getFieldErrors()) {
				String message = messageSource.getMessage(error, null);
				errors.put(error.getField(), message);
			}
			return new RestResult(90, errors);
		}
		readerService.addReader(mapper.map(form, MReader.class));
		return new RestResult(0, null);
	}
	
	@PutMapping("/update")
	public RestResult updateReader(@Validated ReaderUpForm form, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			Map<String, String> errors = new HashMap<String, String>();
			
			for (FieldError error : bindingResult.getFieldErrors()) {
				String message = messageSource.getMessage(error, null);
				errors.put(error.getField(), message);
			}
			return new RestResult(90, errors);
		}
		MReader user = mapper.map(form, MReader.class);
		if (readerService.getLogin(user.getReaderEmail()) == null
				|| readerService.getLogin(user.getReaderEmail()).getReaderId() == user.getReaderId()) {
			readerService.updateReader(user);
			return new RestResult(0, null);
		}else {
			return new RestResult(10, null);
		}
	}
	
	@PutMapping("/update/password")
	public RestResult updatePasswordReader(@Validated UserPasswordForm form, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			Map<String, String> errors = new HashMap<String, String>();
			
			for (FieldError error : bindingResult.getFieldErrors()) {
				String message = messageSource.getMessage(error, null);
				errors.put(error.getField(), message);
			}
			return new RestResult(90, errors);
		}
		MReader user = new MReader();
		user.setReaderId(form.getReaderId());
		user.setReaderPassword(form.getPassword());
		readerService.updatePasswordReader(user);
		return new RestResult(0, null);
	}
	
	@DeleteMapping("/delete")
	public int deleteReader(@ModelAttribute ReaderForm form) {
		return readerService.deleteReader(form.getReaderId());
	}
}
